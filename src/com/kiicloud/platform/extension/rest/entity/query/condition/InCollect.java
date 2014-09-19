package com.kiicloud.platform.extension.rest.entity.query.condition;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.kiicloud.platform.extension.rest.commons.JsonConvert;
import com.kiicloud.platform.extension.rest.entity.query.ConditionType;

public class InCollect extends SimpleCondition {

	
	@Override
	public ConditionType getType() {
		return ConditionType.in;
	}
	public InCollect(){
		
	}
	public InCollect(String field,String val){
		this();
		String[] valArray=StringUtils.split(
				val.trim().substring(1, val.length()-1),",");
		
		List list=new ArrayList();
		for(String subVal:valArray){
			list.add(JsonConvert.convertVal(subVal));
		}
		setField(field);
		setValues(list);
	}

	/*
	 * {
  "type" : "in",
  "field" : "lastName",
  "values" : [ "Garcia", "Smith", "Lopez", "Simpson" ]
}

	 */
	
	private List<Object>  values=new ArrayList<Object>();

	
	public List<Object> getValues() {
		return values;
	}

	public void setValues(List<Object> values) {
		this.values = values;
	}
	
	
	

}
