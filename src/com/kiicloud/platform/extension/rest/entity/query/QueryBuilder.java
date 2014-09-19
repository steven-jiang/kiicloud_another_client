package com.kiicloud.platform.extension.rest.entity.query;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryBuilder {
	
	private final Logger log=LoggerFactory.getLogger(QueryBuilder.class);

	public QueryBuilder(){
		queryParam.setBestEffortLimit(5);
	}
	
//	private SingleCol condition=new SingleCol();
//	
	private final QueryParam queryParam=new QueryParam();
	
	private final ClauseParam clause=new ClauseParam();
	
	
	//==========================

	
	//=====================================
	public QueryBuilder orderBy(String field){
		clause.setOrderBy(field);
		return this;
	}
	
	public QueryBuilder asc(){
		clause.setDescending(false);
		return this;
	}
	
	public QueryBuilder desc(){
		clause.setDescending(true);
		return this;
	}
	
	public QueryBuilder pageKey(String key){
		queryParam.setPaginationKey(key);
		return this;
	}

	
	
	public QueryParam build(ConditionBuilder condition ){
		
//		if(condition instanceof LogicCol){
//			condition=((LogicCol) condition).getFinalCondition();
//		}
		clause.setClause(condition.getFinalCondition());
		queryParam.setBucketQuery(clause);
		try {
			return  (QueryParam) BeanUtils.cloneBean(queryParam);
		} catch (Exception e) {
			log.error("clone fail",e);
			throw new IllegalArgumentException(e);
		} 
	}
	
	
}
