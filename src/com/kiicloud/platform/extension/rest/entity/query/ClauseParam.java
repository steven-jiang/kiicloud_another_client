package com.kiicloud.platform.extension.rest.entity.query;

import com.kiicloud.platform.extension.rest.entity.query.condition.All;

public class ClauseParam {

	private Condition clause=new All();

	private String orderBy;
	
	private boolean descending;

	public Condition getClause() {
		return clause;
	}

	public void setClause(Condition clause) {
		this.clause = clause;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isDescending() {
		return descending;
	}

	public void setDescending(boolean descending) {
		this.descending = descending;
	}
	
	
	
}
