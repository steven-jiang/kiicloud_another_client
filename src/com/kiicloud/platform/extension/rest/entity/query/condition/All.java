package com.kiicloud.platform.extension.rest.entity.query.condition;

import com.kiicloud.platform.extension.rest.entity.query.Condition;
import com.kiicloud.platform.extension.rest.entity.query.ConditionType;

public class All implements Condition {
	
	public All(){
		
	}

	@Override
	public ConditionType getType() {
		return ConditionType.all;
	}

}
