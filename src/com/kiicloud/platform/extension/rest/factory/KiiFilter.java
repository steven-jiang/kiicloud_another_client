package com.kiicloud.platform.extension.rest.factory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kiicloud.platform.extension.rest.commons.StringTemplate;


@Component
public class KiiFilter  implements ClientRequestFilter {
	
	private static final Logger log=LoggerFactory.getLogger(KiiFilter.class);

  	@Autowired
	private AppConfigStore factory;
  	
  	
	
	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		
		

		Map<String,String> params=new HashMap<String,String>();
		params.put("APP_ID",factory.getAppInfo().appID);
		params.put("SITE",factory.getAppInfo().url);

		URI uri=requestContext.getUri();
		
		String fullUrl=StringTemplate.generUrl(uri.toString(), params);
//		String fullHost=StringTemplate.generUrl(uri.getHost(), params);
		log.info("full url:"+fullUrl);
		try {
		
			URI newUri=new URI(fullUrl);
			requestContext.setUri(newUri);

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String customType=(String) requestContext.getConfiguration().getProperty("custom_type");
		String header=(String) requestContext.getConfiguration().getProperty("header");

		if(StringUtils.isNotBlank(customType)){
			requestContext.getHeaders().remove(header);
			requestContext.getHeaders().add(header,StringTemplate.generByMap(customType, params));
		}

		
	}

}