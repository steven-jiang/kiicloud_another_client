package com.kiicloud.platform.extension.rest.entity.query.condition;

import com.kiicloud.platform.extension.rest.entity.query.ConditionType;

public class PrefixLike extends SimpleCondition{

	@Override
	public ConditionType getType() {
		return ConditionType.prefix;
	}
	
	
	public PrefixLike(){
		
	}
	
	public PrefixLike(String field,String val){
		this();
		setField(field);
		setPrefix(val.substring(1,val.length()-1));
	}
	
	private String prefix;
	

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}


	
	
	
}
