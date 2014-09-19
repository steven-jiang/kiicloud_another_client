package com.kiicloud.platform.extension.rest.entity.query.condition;

import javax.xml.bind.annotation.XmlTransient;

import com.kiicloud.platform.extension.rest.entity.query.Condition;

@XmlTransient
public abstract class LogicCol implements Condition {

	public LogicCol() {

	}
	


	public abstract LogicCol addClause(Condition clause);

}
