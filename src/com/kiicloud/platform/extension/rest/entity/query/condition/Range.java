package com.kiicloud.platform.extension.rest.entity.query.condition;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;

import com.kiicloud.platform.extension.rest.commons.JsonConvert;
import com.kiicloud.platform.extension.rest.entity.query.ConditionType;

@XmlRootElement
public class Range extends SimpleCondition {
	
	
	
	public static Range great(String field,String val){
		Object valObj=JsonConvert.convertVal(val);
		return new Range(field,valObj,false,null,false);
	}
	
	public static Range greatAndEq(String field,String val){
		Object valObj=JsonConvert.convertVal(val);
		return new Range(field,valObj,true,null,false);
	}
	
	public static Range less(String field,String val){
		Object valObj=JsonConvert.convertVal(val);
		return new Range(field,null,false,valObj,false);
	}
	
	public static Range lessAndEq(String field,String val){
		Object valObj=JsonConvert.convertVal(val);
		return new Range(field,null,true,valObj,true);
	}
	
	public static Range generRange(String field,String val){
		
		//[0 1]  or (0  1) or [0 1) 
		String[] valPair=StringUtils.split(
				StringUtils.substring(val, 1, val.length()-1));
		boolean withLow= (val.charAt(0) == '[' );
		boolean withUpp= (val.charAt(val.length()-1) == ']' );
		
		Object low=JsonConvert.convertVal(valPair[0]);
		Object upp=JsonConvert.convertVal(valPair[1]);
		
		return new Range(field,low,withLow,upp,withUpp);
	}
	
	public Range(){
		
	}
	
	private Range(String field,Object low,boolean withLow,Object upper,boolean withUpp){
		setField(field);
		setLowerIncluded(withLow );
		setUpperIncluded(withUpp);
		
		setLowerLimit(low);
		setUpperLimit(upper);
		
		
	}

//	@XmlElement
//	@Override
//	public ConditionType getType() {
//		return ConditionType.range;
//	}
	
	
	protected Object upperLimit;
	
	private Boolean upperIncluded;
	
	protected Object lowerLimit;
	
	private Boolean lowerIncluded;




	public Boolean isUpperIncluded() {
		return upperIncluded;
	}

	public void setUpperIncluded(Boolean upperIncluded) {
		this.upperIncluded = upperIncluded;
	}

	

	public Boolean isLowerIncluded() {
		return lowerIncluded;
	}

	public void setLowerIncluded(Boolean lowerIncluded) {
		this.lowerIncluded = lowerIncluded;
	}
	
	public Object getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(Object upperLimit) {
		this.upperLimit = upperLimit;
	}
	
	public Object getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(Object lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	@Override
	public ConditionType getType() {
		return ConditionType.range;
	}

	

}
