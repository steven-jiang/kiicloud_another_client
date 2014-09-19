package com.kiicloud.platform.extension.rest.operate;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.kiicloud.platform.extension.rest.entity.UserInfo;
import com.kiicloud.platform.extension.rest.entity.query.Condition;
import com.kiicloud.platform.extension.rest.entity.query.QueryParam;
import com.kiicloud.platform.extension.rest.entity.query.UserQuery;
import com.kiicloud.platform.extension.rest.exception.KiiRestException;
import com.kiicloud.platform.extension.rest.factory.MetaTypeFeature;
import com.kiicloud.platform.extension.rest.factory.ObjectMapperContextResolver;


@Component
public abstract class UserOperate<T extends UserInfo> {

	private  WebTarget appTarget;
	
	private WebTarget  userTarget;
	
	@Qualifier("appTarget")
	@Autowired
	public void setAppTarget(WebTarget target){
		this.appTarget=target;
		
		this.userTarget=appTarget.path("/users");
	}

	protected abstract Class<T> getUserCls();
	
	protected abstract TypeReference<List<T>>  getTypeReference();
	
	public T getMe() {
		return appTarget
				.register(
						MetaTypeFeature.getKiiFeature("UserDataRetrievalResponse"))
				.path("/me").request().get().readEntity(getUserCls());
	}

	public T signUp(T userInfo) {

		return userTarget
				.register(MetaTypeFeature.getKiiFeature("RegistrationRequest"))
				.request().post(Entity.json(userInfo))
				.readEntity(getUserCls());

	}

	
	@Autowired
	private ObjectMapperContextResolver  resolver;
	
	
	
	public T  queryUserByAccount(String value){
		
		for(AccountType account:AccountType.values()){
			
			try{
			T t= queryUserByField(account,value);
			return t;
			}catch(Exception e){
				e.printStackTrace();
				continue;
			}
		}
		
		return null;
	}
	

	public static enum AccountType{
		EMAIL, PHONE,LOGIN_NAME;
	}
	public T queryUserByID(String id){
		return userTarget.path("/"+id).request().get(getUserCls());
	}
	
	private T queryUserByField(AccountType field,String value){
		return userTarget.path("/"+field.name()+":"+value).request().get(getUserCls());

	}
	
	public void updateUser(T userInfo){
		 appTarget.path(userInfo.getUserID()).register(MetaTypeFeature.getKiiFeature("UserUpdateRequest"))
		.request().post(Entity.json(userInfo)).readEntity(getUserCls());
		
	}
	
	public List<T> getAllUser(){
		
		List<T> list=new ArrayList<T>();
		
		QueryResult<T> query=new QueryResult<T>();
		do{
			query=queryUser(null,query.getNextPaginationKey());
			list.addAll(query.getResults());
			
		}while(query.hasNext());
		return list;
		
	}

	public QueryResult<T> queryUser(Condition condition,String pageKey){
		
		QueryParam param=new UserQuery();
		param.setCondition(condition);
		param.setPaginationKey(pageKey);
		
		JsonNode node= userTarget.path("/query")
				.register(MetaTypeFeature.getKiiFeature("userqueryrequest"))
				.request().post(Entity.json(param))
				.readEntity(JsonNode.class);
		
		
		TypeReference<List<T>> typeRef = getTypeReference();
		
		QueryResult<T> result=new QueryResult<T>();
		
		
		JsonNode nodeKey=node.get("nextPaginationKey");
		if(nodeKey!=null){
			result.setNextPaginationKey(nodeKey.asText());
		}else{
			result.setNextPaginationKey(null);
		}
		
        try {
		
        	List<T> list=resolver.getObjectMapper().readValue(node.get("results").traverse(),typeRef);
        	result.setResults(list);
        	return result;
        } catch (Exception e) {
			throw new KiiRestException(e);
		}
		
		 

	}



	
}
