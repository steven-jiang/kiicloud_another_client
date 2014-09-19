package com.kiicloud.platform.extension.rest.operate;

import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.kiicloud.platform.extension.rest.factory.MetaTypeFeature;

@Component
public class ServiceDeployOperate {

	
	@Qualifier("appTarget")
	@Autowired
	private  WebTarget appTarget;
	
	public String deployJsFile(String file){
		
		Map<String,Object> map=appTarget
			.path("/server-code")
			.register(MetaTypeFeature.getContentType("application/javascript"))
			.request().post(Entity.text(file)).readEntity(Map.class);
		
		return (String)map.get("versionID");
	}
	
	public void deployHookFile(String file,String versionID){
		appTarget.path("/hooks/versions/"+versionID)
		.register(MetaTypeFeature.getKiiFeature("HooksDeploymentRequest"))
				
				.request().put(Entity.text(file)).close();
		
	}
	
	public void setCurrent(String version){
		
	}
}
