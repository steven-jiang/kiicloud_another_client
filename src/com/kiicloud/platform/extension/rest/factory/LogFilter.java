package com.kiicloud.platform.extension.rest.factory;

import java.io.IOException;
import java.util.Map;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LogFilter implements ClientResponseFilter{

	private final static Logger log=LoggerFactory.getLogger(LogFilter.class);
	
	@Override
	public void filter(ClientRequestContext requestContext,
			ClientResponseContext responseContext) throws IOException {
		  log.info("response status:"+responseContext.getStatus());
		  
		  for(String key:responseContext.getHeaders().keySet()){
			  String value=responseContext.getHeaderString(key);
			  
			  log.info("response.header:"+key+"="+value);
		  }
		  
	}
	
}