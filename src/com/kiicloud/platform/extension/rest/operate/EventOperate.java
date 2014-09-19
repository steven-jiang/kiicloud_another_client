package com.kiicloud.platform.extension.rest.operate;

import java.util.Date;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.kiicloud.platform.extension.rest.entity.EventEntity;
import com.kiicloud.platform.extension.rest.factory.MetaTypeFeature;

@Component
public class EventOperate{
	
	@Qualifier("appTarget")
	@Autowired
	private  WebTarget appTarget;

	
	public  <T extends EventEntity> void addEvent(T entity){
		///apps/{appID}/events
		
		entity.setUploadedAt(new Date());

		appTarget.path("/events").register(MetaTypeFeature.getKiiFeature("EventRecord"))
		.request().post(Entity.json(entity));
		
	}
	
	
}
