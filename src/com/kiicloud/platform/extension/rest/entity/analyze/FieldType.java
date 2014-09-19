package com.kiicloud.platform.extension.rest.entity.analyze;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;


@JsonFormat(shape= JsonFormat.Shape.STRING)
public enum FieldType {

	STRING,INT,FLOAT,BOOLEAN;
	
	@Override
	public String toString(){
		return this.name().toLowerCase();
	}
	
	@JsonCreator
	public static FieldType getType(String str){
		return FieldType.valueOf(str.toUpperCase());
	}
	
}
