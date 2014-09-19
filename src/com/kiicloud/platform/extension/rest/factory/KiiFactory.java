package com.kiicloud.platform.extension.rest.factory;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.filter.LoggingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.kiicloud.platform.extension.rest.exception.ExceptionFilter;


//@Configuration
public class KiiFactory {
	
	private static final Logger logger=LoggerFactory.getLogger(KiiFactory.class);

	private static final java.util.logging.Logger javaLogger=java.util.logging.Logger.getLogger("kiicloud");
	
	
	public KiiFactory(){
		
	    
	}
	
	@Autowired
	private ApplicationContext context;

	
	@Autowired
	private KiiJacksonFeature jacksonFeature;
	
	@Autowired
	private KiiFilter kiiFilter;
	
	@Autowired
	private AppConfigStore factory;
	
	private ClientBuilder clientBuilder;
	
	@PostConstruct
	public void init(){
		
//		appUrl="https://"+url+".kii.com/api/apps/$<APP_ID>";

		clientBuilder=ClientBuilder.newBuilder()
				.register(jacksonFeature)
				.register(new LoggingFilter(javaLogger,true))
				.register(ExceptionFilter.class)
				.register(kiiFilter)
				.register(factory.getAppFilter());
		
	}

	
	

	@Bean(name="nologinTarget")
	@Scope("prototype")
	public WebTarget getnoLoginTarget(){
		

		return clientBuilder
				.build()
				.target("$(SITE)/api/");


	}
	
	
	@Bean(name="appTarget")
	@Scope("prototype")
	@Lazy
	public WebTarget getUserTarget(){
		Client client=clientBuilder
		.build();
		
		TokenManager mang=context.getBean(TokenManager.class);
		if(mang!=null){
			client.register(mang.getFilter());
		}
		return client
				.target(factory.getAppUrl());
	}
	

	

}
