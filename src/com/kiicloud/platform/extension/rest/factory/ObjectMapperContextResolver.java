package com.kiicloud.platform.extension.rest.factory;

import javax.ws.rs.ext.ContextResolver;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

//import org.codehaus.jackson.map.DeserializationConfig;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.codehaus.jackson.map.SerializationConfig.Feature;


@Component
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper>{

	final ObjectMapper defaultObjectMapper;
 
    public ObjectMapperContextResolver() {
        defaultObjectMapper = createDefaultMapper();
    }
 
    @Override
    public ObjectMapper getContext(Class<?> type) {
        
        return defaultObjectMapper;
        
    }
    
    public ObjectMapper getObjectMapper(){
    	return defaultObjectMapper;
    }
 
    private static ObjectMapper createDefaultMapper() {
        final ObjectMapper result = new ObjectMapper();
        result.setSerializationInclusion(Include.NON_NULL);
        result.configure(SerializationFeature.INDENT_OUTPUT, true);
        result.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        result.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS,true);
        return result;
    }
 

}
