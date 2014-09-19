package com.kiicloud.platform.extension.rest.operate;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

@Component
public class JsonService {
	
	private ObjectMapper objMap;
	
	@PostConstruct
	public void init(){
		objMap=new ObjectMapper();
	}
	
	public <T> List<T> convertToList(String json,Class<T> cls){
		
		try {
		    TypeFactory t = TypeFactory.defaultInstance();
			List<T>  list= objMap.readValue(json, t.constructCollectionLikeType(List.class, cls));
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		} 
		
	}

}
