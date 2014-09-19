package com.kiicloud.platform.extension.rest.entity.analyze;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kiicloud.platform.extension.rest.entity.ScopeType;

public class ConvertsionRule {
	
	/*
	 * 
{
  "scope": "app",
  "bucket": "sandbox",
  "target": "OBJECT",
  "derived": [
    {"name": "word_count", "source": "tweet.size", "type":"int"},
    {"name": "country", "source": "label.location", "type":"string"},
    {"name": "picture_size", "source": "picture.size", "type":"int"}
  ]
}
	 */

	private ScopeType  scope;
	
	private String bucket;
	
	private TargetType target;
	
	private List<Derived>  derived=new ArrayList<Derived>();
	
	private String id;
	
	
	
	@JsonProperty("_id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ScopeType getScope() {
		return scope;
	}

	public void setScope(ScopeType scope) {
		this.scope = scope;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public TargetType getTarget() {
		return target;
	}

	public void setTarget(TargetType target) {
		this.target = target;
	}

	public List<Derived> getDerived() {
		return derived;
	}

	public void addDerived(String name,String source,FieldType type){
		Derived d=new Derived();
		d.setName(name);
		d.setSource(source);
		d.setType(type);
		this.derived.add(d);
	}
	public void setDerived(List<Derived> derived) {
		this.derived = derived;
	}

	//=============
//	public static enum ScopeType{
//		APP,USER,GROUP;
//	}
	
	public static enum TargetType{
		OBJECT,BUCKET;
	}
}
