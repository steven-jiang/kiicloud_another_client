package com.kiicloud.platform.extension.rest.exception;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class KiiExceptionEntity {

	/*
	 * errorCode	string	Error code GROUP_NOT_FOUND
message	string	The error message
groupID	string	The groupID of the group that was not found
appID	
	 */
	
	private String errorCode;
	
	private String message;
	
	private String userID;
	
	private String groupID;
	
	private String appID;

	public KiiExceptionEntity(){
		
	}
	
	public KiiExceptionEntity(Map<String, Object> result) {
		userID=(String) result.remove("userID");
		groupID=(String)result.remove("groupID");
		appID=(String)result.remove("appID");
		plainResult=result;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getGroupID() {
		return groupID;
	}

	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	private Map<String,Object> plainResult=new HashMap<String,Object>();

	public Map<String, Object> getPlainResult() {
		return plainResult;
	}

	public void setPlainResult(Map<String, Object> plainResult) {
		this.plainResult = plainResult;
	}
	
	
	
	
}
