package com.kiicloud.platform.extension.rest.commons;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConvert {

	
	private static final ObjectMapper objMap=new ObjectMapper();
	
	public static String getJson(Map<String,Object> map){
		try {
			return objMap.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
	}
	
	public static Object convertVal(String val) {
		
		val=val.trim();
		if(val.startsWith("'")){
			//string
			return StringUtils.substring(val, 1, val.length()-1);
		}else if(val.startsWith("@")){
			//date
			long dateTime=Long.parseLong(StringUtils.substring(val, 1));
			return new Date(dateTime);
		}else {
			//number,just in compare oper.
			try{
				return NumberUtils.createInteger(val);
			}catch(NumberFormatException e){
				try{
					return NumberUtils.createFloat(val);
				}catch(NumberFormatException ex){
					throw new IllegalArgumentException(ex);
				}
			}
		}
	}
	
}
