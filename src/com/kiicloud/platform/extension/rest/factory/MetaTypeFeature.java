package com.kiicloud.platform.extension.rest.factory;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import org.apache.commons.lang.StringUtils;

public class MetaTypeFeature implements Feature{
	private String customType;
	
	private final String header;
	
	private MetaTypeFeature(){
		header="content-type";
	}
	private MetaTypeFeature(String type){
		if(StringUtils.isNotBlank(type)){
			this.customType="application/vnd."+type+"+json";
		}
		header="content-type";
	}
	
	private MetaTypeFeature(String type,String header){
		this.customType="application/vnd."+type+"+json";
		this.header=header;
	}
	
	public static final Feature getObjFeature(){
		return new MetaTypeFeature("${APP_ID}.mydata");
	}
	
	public static final Feature getQueryFeature(){
		return new MetaTypeFeature("kii.QueryRequest");
	}

	public static final Feature getFeature(String type){
		return new MetaTypeFeature(type);
	}
	
	public static final Feature getKiiFeature(String type){
		return new MetaTypeFeature("kii."+type);
	}
	
	public static final Feature getAcceptFeature(String type){
		return new MetaTypeFeature("kii."+type,"Accept");
	}
	@Override
	public boolean configure(FeatureContext context) {
		context.property("custom_type", customType);
		context.property("header",header);
		return true;
	}
	public static final Feature getContentType(String string) {
		MetaTypeFeature feature=new MetaTypeFeature();
		feature.customType=string;
		return feature;
	}
	
	
}