package com.kiicloud.platform.extension.rest.exception;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;

public class ExceptionFilter implements ClientResponseFilter{

	@Override
	public void filter(ClientRequestContext requestContext,
			ClientResponseContext responseContext) throws IOException {
		
		// TODO Auto-generated method stub
		int status=responseContext.getStatus();
		
		if(status>=200  && status <300){
			return;
		}
		
		if(status>=400){
			String errorType=responseContext.getHeaderString("Content-Type");
			
			KiiRestException excep=new KiiRestException(errorType,responseContext);
			
			throw excep;
		}
	}

}
