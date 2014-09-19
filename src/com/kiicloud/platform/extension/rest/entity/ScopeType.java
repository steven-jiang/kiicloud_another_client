package com.kiicloud.platform.extension.rest.entity;

public  enum ScopeType{
	APP(""),GROUP("/groups/$(USER_SIGN)"),USER("/users/$(USER_SIGN)");
	
	private final String name;
	
	ScopeType(String name){
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
}
