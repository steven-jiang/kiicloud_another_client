package com.kiicloud.platform.extension.rest.entity.query.condition;

import java.util.ArrayList;
import java.util.List;

import com.kiicloud.platform.extension.rest.entity.query.Condition;
import com.kiicloud.platform.extension.rest.entity.query.ConditionType;

public class OrLogic extends LogicCol {

	@Override
	public ConditionType getType() {
		return ConditionType.or;
	}

	private List<Condition> clauses = new ArrayList<Condition>();

	public List<Condition> getClauses() {
		return clauses;
	}

	public void setClauses(List<Condition> clauses) {
		this.clauses = clauses;
	}

	@Override
	public LogicCol addClause(Condition clause) {
		this.clauses.add(clause);
		return this;
	}

}
