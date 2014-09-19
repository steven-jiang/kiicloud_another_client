package com.kiicloud.platform.extension.rest.entity.query;

import com.kiicloud.platform.extension.rest.entity.query.condition.All;

/**
 * the package of restful's queryParam
 * 
 * @see  http://documentation.kii.com/rest/#data_management-manage_objects-group_scope-query_for_objects
 * @author ethan
 *
 */
public class QueryParam {
	
	/*
	 *   "paginationKey":"asd12ijdfasdfjadfjgk",
  "bestEffortLimit":10

	 */

	private int bestEffortLimit=50;
	
	private String paginationKey;
	
	private ClauseParam bucketQuery=new ClauseParam();
	

	public int getBestEffortLimit() {
		return bestEffortLimit;
	}

	public void setBestEffortLimit(int bestEffortLimit) {
		this.bestEffortLimit = bestEffortLimit;
	}

	public String getPaginationKey() {
		return paginationKey;
	}

	public void setPaginationKey(String paginationKey) {
		this.paginationKey = paginationKey;
	}

	public ClauseParam getBucketQuery() {
		return bucketQuery;
	}

	public void setBucketQuery(ClauseParam bucketQuery) {
		this.bucketQuery = bucketQuery;
	}



	public void setCondition(Condition cond) {
		if(cond==null){
			cond=new All();
		}
		bucketQuery.setClause(cond);
		
	}
	

	
	
}
