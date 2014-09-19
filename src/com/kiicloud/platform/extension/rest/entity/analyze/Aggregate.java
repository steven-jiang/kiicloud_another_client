package com.kiicloud.platform.extension.rest.entity.analyze;

public class Aggregate {

	/*
	 *  { "valueOf" : "*", "type": "string", "with": "count"},

	 */
	
	private String valueOf;
	
	private FieldType type;
	
	private CollectFun with;
	
	
	
	public String getValueOf() {
		return valueOf;
	}



	public void setValueOf(String valueOf) {
		this.valueOf = valueOf;
	}



	public FieldType getType() {
		return type;
	}



	public void setType(FieldType type) {
		this.type = type;
	}



	public CollectFun getWith() {
		return with;
	}



	public void setWith(CollectFun with) {
		this.with = with;
	}



	public static enum CollectFun{
		count,max,min,sum,avg;
	}
}
