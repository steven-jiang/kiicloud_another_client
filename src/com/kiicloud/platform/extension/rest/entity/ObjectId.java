package com.kiicloud.platform.extension.rest.entity;

import java.util.Date;

public class ObjectId {

	
	/*
	 * objectID	string	The ID of the created object
createdAt	long	The creation date of the object in Unix epoch represented in milliseconds.
dataType	string	The data type of the object

	 */
	
	private String objectID;
	
	private Date createdAt;
	
	private String  dataType;
	
	private int version;

	public String getObjectID() {
		return objectID;
	}

	public void setObjectID(String objectID) {
		this.objectID = objectID;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	
}
