package com.kiicloud.platform.extension.rest.factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class AppConfigStore {
	
	@Autowired
	private ResourceLoader loader;
	
	
	private final Map<String,AppInfo> infoMap=new HashMap<String,AppInfo>();
	
	@PostConstruct
	public void init(){
		
		AppInfo defau=null;
		try {
			BufferedReader reader=new BufferedReader(new InputStreamReader(loader.getResource("classpath:kiicloud.properties").getInputStream()));
			
			String line=null;
			while((line=reader.readLine())!=null){
				line=line.trim();
				String prefix=StringUtils.substringBefore(line, ".");
				String keyvalue=StringUtils.substringAfter(line, ".");

				if("kii".equals(prefix)){
					prefix="default";
					keyvalue=line;
				}
				AppInfo info=infoMap.get(prefix);
				if(info==null){
					info=new AppInfo();
					infoMap.put(prefix,info);
					if(defau==null){
						defau=info;
					}
				}
				
				
				
				/*
				 * us.kii.app_id=ab324972
us.kii.app_key=99e0be3821c30b0b09fc4c59e83e37ef
us.kii.client_id=1d0d604208638838ce8af217b552eea1
us.kii.client_key=d09ad99181c5e1cc017a192663ecea3fd1cf5ba2746a8fb94d69dd9264e5042e
us.kii.address=api
				 */
				
				String key=StringUtils.trim(StringUtils.substringBefore(keyvalue, "="));
				String value=StringUtils.trim(StringUtils.substringAfter(keyvalue, "="));
				
				if("kii.app_id".equals(key)){
					info.appID=value;
				}else if("kii.app_key".equals(key)){
					info.appKey=value;
				}else if("kii.client_id".equals(key)){
					info.clientID=value;
				}else if("kii.client_key".equals(key)){
					info.clientKey=value;
				}else if("kii.address".equals(key)){
					info.url="https://"+value+".kii.com";
				}
				
				
				
			}
			if(!infoMap.containsKey("default")){
				infoMap.put("default", defau);
			}
			
			infoLocal.set(infoMap.get("default"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	static class AppInfo{
		String url;
		String appID;
		String appKey;
		String clientID;
		String clientKey;
	}
	
	
	private final ThreadLocal<AppInfo> infoLocal=new ThreadLocal<AppInfo>();
	
	public void select(String name){
		AppInfo info=infoMap.get(name);
		if(info!=null){
			infoLocal.set(info);
		}
	}
	
	public void selectDefault(){
		infoLocal.set(infoMap.get("default"));
	}
	
	 AppInfo getAppInfo(){
		return infoLocal.get();
	}

	public String getAppUrl(){
		return "$(SITE)/api/apps/$(APP_ID)";
	}
	
//	public String getAuthUrl(){
//		return getAppInfo().url+"/api/oauth2/token";
//	}
	
	public ClientRequestFilter getAppFilter(){
		return filter;
	}
	
	
	public void setAppInfo(String appID,String appKey,String url){
		AppInfo appInfo=this.infoLocal.get();
		if(appInfo==null){
			appInfo=new AppInfo();
		}
		appInfo.appID=appID;
		appInfo.appKey=appKey;
		appInfo.url="https://"+url+".kii.com";
		this.infoLocal.set(appInfo);
	}
	
	public void setAdminInfo(String clientID,String clientSec){
		AppInfo appInfo=this.infoLocal.get();

		appInfo.clientID=clientID;
		appInfo.clientKey=clientSec;
		this.infoLocal.set(appInfo);

	}
	
	public void setFullUrl(String url){
		this.infoLocal.get().url=url;
	}
	
	private final HeaderFilter filter=new HeaderFilter();
	
	private class HeaderFilter implements ClientRequestFilter{ 

		private final static String APP_ID_NAME = "x-kii-appid";
		private final static String APP_KEY_NAME = "x-kii-appkey";

		@Override
		public void filter(ClientRequestContext requestContext)
				throws IOException {
			if (!requestContext.getHeaders().containsKey(APP_ID_NAME)) {
				requestContext.getHeaders().add(APP_ID_NAME, getAppInfo().appID);
			}
			if (!requestContext.getHeaders().containsKey(APP_KEY_NAME)) {
				requestContext.getHeaders().add(APP_KEY_NAME, getAppInfo().appKey);
			}

		}

	}

}
