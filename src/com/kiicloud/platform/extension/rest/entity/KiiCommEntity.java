package com.kiicloud.platform.extension.rest.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class KiiCommEntity {
	
	private String id;
	
	private int version;
	
	private String dataType;
	
	private String owner;
	
	private Date created;
	
	private Date modified;

	private String bucketName;
	
	private Map<String,Object> dataMap=new HashMap<String,Object>();
	
	
	
	public Map<String, Object> getDataMap() {
		return dataMap;
	}


	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	
	@JsonAnySetter
	public void setCommValue(String key,Object val){
		dataMap.put(key,val);
	}


//	public String getBucketName() {
//		return bucketName;
//	}
//
//
//	public void setBucketName(String bucketName) {
//		this.bucketName = bucketName;
//	}

	
	public KiiCommEntity(){
		
	}

	public KiiCommEntity(Map<String,Object>  dataMap,String bucketName){
		  created=new Date((Long) dataMap.remove("_created"));
		  dataType=(String)dataMap.remove("_dataType");
		  id=(String)(dataMap.remove("_id"));
		  version=Integer.parseInt((String) dataMap.remove("_version"));
		  modified=new Date((Long) dataMap.remove("_modified"));
		  owner=(String)dataMap.remove("_owner");
		  
		  this.bucketName=bucketName;
		  
		  this.dataMap=new HashMap<String,Object>(dataMap);
		  return;
	}


	@JsonProperty("_id")
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	@JsonProperty("_version")
	public int getVersion() {
		return version;
	}


	public void setVersion(int version) {
		this.version = version;
	}

	@JsonProperty("_dataType")
	public String getDataType() {
		return dataType;
	}


	public void setDataType(String dataType) {
		this.dataType = dataType;
	}


	@JsonProperty("_owner")
	public String getOwner() {
		return owner;
	}


	public void setOwner(String owner) {
		this.owner = owner;
	}


	@JsonProperty("_created")
	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}


	@JsonProperty("_modified")
	public Date getModified() {
		return modified;
	}


	public void setModified(Date modified) {
		this.modified = modified;
	}
	
	@Override
	public String toString(){
		return "objID:"+id+" version:"+version+" dataMap:"+dataMap;
	}
	

}
