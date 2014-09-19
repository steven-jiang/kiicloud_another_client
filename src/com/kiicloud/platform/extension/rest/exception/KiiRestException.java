package com.kiicloud.platform.extension.rest.exception;

import java.util.Map;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.ClientResponseContext;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class KiiRestException extends ProcessingException{

	private String errorType;
	
	private int status;
	
	private KiiExceptionEntity entity;
	
	private final ObjectMapper objMap=new ObjectMapper();
	
	public KiiRestException(Exception e){
		super(e);
	}
	
	public KiiRestException(String errorType,ClientResponseContext responseContext){
		super(errorType);
		
		this.errorType=errorType;
		
		this.status=responseContext.getStatus();
		
		try {
			String json=IOUtils.toString(responseContext.getEntityStream());
			
			Map<String,Object>  result=objMap.readValue(json,Map.class);
			this.entity=new KiiExceptionEntity(result);
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}

	public String getErrorType() {
		return errorType;
	}

	public int getStatus() {
		return status;
	}

	public KiiExceptionEntity getEntity() {
		return entity;
	}
	
	
	
//	private String errorCode;
//	
//	private String message;
	
	
}
