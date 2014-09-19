package com.kiicloud.platform.extension.rest.entity.query.condition;

import com.kiicloud.platform.extension.rest.entity.query.Condition;
import com.kiicloud.platform.extension.rest.entity.query.ConditionType;

public class NotLogic extends LogicCol {

	@Override
	public ConditionType getType() {
		return ConditionType.not;
	}

	private Condition clause;

	public Condition getClause() {
		return clause;
	}

	public void setClause(Condition clause) {
		this.clause = clause;
	}

	@Override
	public LogicCol addClause(Condition clause) {
		this.clause=clause;
		return this;
	}
	
	
}

