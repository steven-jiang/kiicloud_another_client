package com.kiicloud.platform.extension.rest.entity.query.condition;

import com.kiicloud.platform.extension.rest.commons.JsonConvert;
import com.kiicloud.platform.extension.rest.entity.query.ConditionType;

public class Equal extends SimpleCondition{

	@Override
	public ConditionType getType() {
		return ConditionType.eq;
	}
	
	public Equal(){
		
	}
	
	public Equal(String field,String val){
		this();
		setField(field);
		Object valObj = JsonConvert.convertVal(val);
		setValue(valObj);
	}
	
	private Object value;


	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	
	//{ "type":"eq",  "field":"name",  "value":"John Doe" }

	

}
