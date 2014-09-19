package com.kiicloud.platform.extension.rest.entity.query.condition;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import com.kiicloud.platform.extension.rest.entity.query.Condition;
import com.kiicloud.platform.extension.rest.entity.query.ConditionType;


//@XmlSeeAlso({IntRange.class,Equal.class,FieldExist.class,
//	GeoBox.class,GeoDistance.class,InCollect.class,PrefixLike.class,
//	FloatRange.class,StringRange.class})
//@XmlTransient
public abstract  class SimpleCondition implements  Condition {
	
	public SimpleCondition(){
		super();
	}
	

	private String field;
	
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	

}
