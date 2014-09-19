package com.kiicloud.platform.extension.rest.operate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.kiicloud.platform.extension.rest.commons.StringTemplate;
import com.kiicloud.platform.extension.rest.entity.ACLUserSign;
import com.kiicloud.platform.extension.rest.entity.BucketACLType;
import com.kiicloud.platform.extension.rest.entity.KiiEntity;
import com.kiicloud.platform.extension.rest.entity.ObjectId;
import com.kiicloud.platform.extension.rest.entity.ScopeType;
import com.kiicloud.platform.extension.rest.entity.query.Condition;
import com.kiicloud.platform.extension.rest.entity.query.ConditionBuilder;
import com.kiicloud.platform.extension.rest.entity.query.PagingResult;
import com.kiicloud.platform.extension.rest.entity.query.QueryParam;
import com.kiicloud.platform.extension.rest.exception.KiiRestException;
import com.kiicloud.platform.extension.rest.factory.MetaTypeFeature;
import com.kiicloud.platform.extension.rest.factory.ObjectMapperContextResolver;


public abstract class AbsDataOperate<T extends KiiEntity> {

	private final Logger log=LoggerFactory.getLogger(AbsDataOperate.class);
	
//	@Autowired
//	private CommDataService commService;
	
	@Autowired
	private JsonService jsonService;
	
	@Autowired
	private ObjectMapperContextResolver  resolver;


	private WebTarget sysTarget;
	
	@Qualifier("appTarget")
	@Autowired
	public void setAppTarget(WebTarget target){
		this.sysTarget=target;
		this.appTarget=target.path(getScopeType().getName()+"/buckets/"+getBucketName());
	}
	
	protected WebTarget appTarget;
	
	
	
	abstract protected Class<T> getTargetCls();
	
	abstract protected String getBucketName();

	abstract protected TypeReference<List<T>> getTypeReference();
	
    protected ScopeType getScopeType(){
    	return ScopeType.APP;
    };
	
	
	
	
    public void setACLToBucket(BucketACLType type,ACLUserSign sign){
    	setACLToSpecUser(type,sign.name());
    }
    
    public void setACLToSpecUser(BucketACLType type,String userName){
    	///apps/{appID}/users/{accountType}:{address}/acl/{ACLVerb}/UserID:{subjectUserID}
    	String path="/acl/${0}/UserID:${1}";
    	String fullPath=StringTemplate.gener(path, type.name(),userName);
    	
    	appTarget.path(fullPath)
    	.request().put(Entity.text(""));
    	
    }


    /**
     * 删除当前的bucket
     */
	public void deleteBucket(){
		sysTarget.path(getScopeType().getName()+"/buckets/"+getBucketName()).request().delete();
	}

	/**
	 * 将实例添加到bucket
	 * @param  entity 待添加的数据实体
	 * @return 新object的id
	 * @see  http://documentation.kii.com/rest/#data_management-manage_objects-application_scope-create_an_object
	 */
	public ObjectId addToBucket(T entity){
		
		return appTarget.path("/objects")
				.register(MetaTypeFeature.getObjFeature())
				.request()
				.post(Entity.json(entity))
				.readEntity(ObjectId.class);
		
	}
	
	/**
	 * 根据ID获取entity
	 * @param id 指定对象的objectID
	 * @return  完整对象内容
	 * @see http://documentation.kii.com/rest/#data_management-manage_objects-application_scope-retrieve_an_object
	 */
	public T getByID(String id){
		return appTarget
				.path("/objects/"+id)
				.request()
				.get()
				.readEntity(getTargetCls());
	}
	

	

	/**
	 * query by page,th
	 * @param query
	 * @return include the pageKey &  result list.
	 * @see  http://documentation.kii.com/rest/#data_management-manage_objects-application_scope-query_for_objects
	 */
	public PagingResult<T> getResultInPage(QueryParam query){
		
		
		JsonNode node= appTarget
				.path("/query")
				.register(MetaTypeFeature.getQueryFeature())
				.request()
				.post(Entity.json(query))				
				.readEntity(JsonNode.class);
		
		
		PagingResult<T> pagingResult=new PagingResult<T>();
		
		JsonNode pageKey=node.get("nextPaginationKey");
		if(pageKey!=null){
			query.setPaginationKey(pageKey.asText());
			pagingResult.setNextPaginationKey(pageKey.asText());
		}else{
			query.setPaginationKey(null);
		}
		
		pagingResult.setQueryParam(query);
		
		try{
			List<T> list=resolver.getObjectMapper().readValue(node.get("results").traverse(),this.getTypeReference());
			pagingResult.setResults(list);
		}catch(Exception e){
			throw new KiiRestException(e);
		}	
		return pagingResult;
	}
	
	
	/**
	 * 返回bucket中所有对象
	 * @return
	 */
	public List<T> getAllResult(){
		return getAllResult(ConditionBuilder.getAll());
	}
	
	
	/**
	 * 不分页，直接返回所有查询结果
	 * @param cond
	 * @return
	 */
	public List<T> getAllResult(Condition cond){
		//	TypeReference<List<UserInfo>> typeRef = new TypeReference<List<UserInfo>>(){};
		QueryParam query=new QueryParam();
		query.setCondition(cond);
		
		List<T> resultList=new ArrayList<T>();
		
		do{
			JsonNode node=appTarget
				.path("/query")
				.register(MetaTypeFeature.getQueryFeature())
				.request()
				.post(Entity.json(query))				
				.readEntity(JsonNode.class);
		
			JsonNode pageKey=node.get("nextPaginationKey");
			if(pageKey!=null){
				query.setPaginationKey(pageKey.asText());
			}else{
				query.setPaginationKey(null);
			}
			try{
				List<T> list=resolver.getObjectMapper().readValue(node.get("results").traverse(),this.getTypeReference());
				resultList.addAll(list);
			}catch(Exception e){
				throw new KiiRestException(e);
			}
			
		}while(StringUtils.isNotBlank(query.getPaginationKey()));
		
		
		return resultList;
	}
	
    /**
     * 部分更新，只更新map中指定的字段
     * @param map
     * @param id
     * @return
     */
	public T updateFields(Map<String, Object> map, String id) {
		return appTarget.path("/objects/"+id)
				.request()
				.header("X-HTTP-Method-Override","PATCH")
				.post(Entity.json(map))
				.readEntity(getTargetCls());
		
	}


	/**
	 * 指定版本的部分更新，如果版本号不匹配则放弃更新
	 * @param updateVals
	 * @param id
	 * @param version
	 * @return
	 */
	public T updateFieldsWithVer(Map<String, Object> updateVals, String id, int version) {
	   return appTarget.path("/objects/"+id)
				.request()
				.header("X-HTTP-Method-Override","PATCH")
				.header("If-Match", version)
				.post(Entity.json(updateVals))
				.readEntity(getTargetCls());	
	}

	
	
	/**
	 * 删除对象
	 * @param entity
	 */
	public void removeObj(T entity) {
		appTarget.path("/objects/"+entity.get_id())
		.request()
		.header("If-Match", ""+entity.get_version())
		.delete();	
	}
	
	/**
	 * 完整更新，
	 * @param entity
	 * @return
	 */
	public T updateObj(T entity){
		return  appTarget.path("/objects/"+entity.get_id())
				.register(MetaTypeFeature.getObjFeature())
				.request()
				.header("If-Match", ""+entity.get_version())
				.put(Entity.json(entity))
				.readEntity(getTargetCls());
	 
	}
	
	
}
