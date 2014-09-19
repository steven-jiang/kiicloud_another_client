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
import org.springframework.stereotype.Component;

import com.kiicloud.platform.extension.rest.entity.BucketInfo;
import com.kiicloud.platform.extension.rest.entity.KiiEntity;
import com.kiicloud.platform.extension.rest.entity.ObjectId;
import com.kiicloud.platform.extension.rest.entity.query.CommQueryResult;
import com.kiicloud.platform.extension.rest.entity.query.QueryParam;
import com.kiicloud.platform.extension.rest.factory.MetaTypeFeature;
import com.kiicloud.platform.extension.rest.factory.TokenManager;


@Component
public class CommDataService {
	
	
	private final Logger log=LoggerFactory.getLogger(CommDataService.class);
	
	@Qualifier("appTarget")
	@Autowired
	private WebTarget appTarget;
	
	
	public ObjectId addBucket(BucketInfo info,Map<String,Object> entity){
		
		return appTarget.path(info.getObjectPath())
				.register(MetaTypeFeature.getObjFeature())
				.request()
				.post(Entity.json(entity))
				.readEntity(ObjectId.class);
		
	}
	
	public CommQueryResult queryPageData(QueryParam param,BucketInfo info){
		
		
		
		return appTarget
					.path(info.getQueryPath())
					.register(MetaTypeFeature.getQueryFeature())
					.request()
					.post(Entity.json(param))
					.readEntity(CommQueryResult.class);
		
	}
	
	public List<Map<String,Object>> queryAllData(QueryParam query,BucketInfo info){
		
		
		
		List<Map<String,Object>> pagingResult=new ArrayList<Map<String,Object>>();

		do{
			Map<String,Object> result= appTarget
					.path(info.getQueryPath())
					.register(MetaTypeFeature.getQueryFeature())
					.request()
					.post(Entity.json(query))
					.readEntity(Map.class);
			
			pagingResult.addAll(  (List<Map<String,Object>>)result.get("results"));
			query.setPaginationKey((String) result.get("nextPaginationKey"));
		}while(StringUtils.isNotBlank(query.getPaginationKey()));
		
		return pagingResult;
	}
	
	public Map<String,Object> getBucketInMap(BucketInfo bucketName,String id){
		return appTarget
				.path(bucketName.getObjectPath(id))
				.request()
				.get()
				.readEntity(Map.class);
	}
	
	public <T extends KiiEntity>  T updateObject(BucketInfo bucketName,T entity,Class<T> cls){
		 return appTarget.path(bucketName.getObjectPath(entity.get_id()))
					.register(MetaTypeFeature.getObjFeature())
					.request()
					.header("If-Match", ""+entity.get_version())
					.put(Entity.json(entity))
					.readEntity(cls);
		 
		
	}
	
	public void updateAllField(BucketInfo bucketName,Map<String,Object>  updateVals,String objID){
		Map map= appTarget.path(bucketName.getObjectPath(objID))
				.request()
				.put(Entity.json(updateVals))
				.readEntity(Map.class);
		
	}
	
	public void updateAllFieldWithVer(BucketInfo bucketName,Map<String,Object>  updateVals,String objID,int version){
		Map map= appTarget.path(bucketName.getObjectPath(objID))
				.request()
				.header("If-Match", version)
				.put(Entity.json(updateVals))
				.readEntity(Map.class);
		
	}
	
	public void updateFields(BucketInfo bucketName,Map<String,Object>  updateVals,String objID){
		Map map= appTarget.path(bucketName.getObjectPath(objID))
				.request()
				.header("X-HTTP-Method-Override","PATCH")
				.post(Entity.json(updateVals))
				.readEntity(Map.class);
		
	}
	
	public void updateFieldsWithVer(BucketInfo bucketName,Map<String,Object>  updateVals,String objID,int version){
		Map map= appTarget.path(bucketName.getObjectPath(objID))
				.request()
				.header("X-HTTP-Method-Override","PATCH")
				.header("If-Match", version)
				.post(Entity.json(updateVals))
				.readEntity(Map.class);
	}
	

	public void deleteObject(BucketInfo bucketName,String objID,int version){
		appTarget.path(bucketName.getObjectPath(objID))
		.request()
		.header("If-Match", ""+version)
		.delete();
	}

	public void deleteBucket(BucketInfo info) {
		appTarget.path(info.getBucketPath())
		.request().delete();
	}

}
