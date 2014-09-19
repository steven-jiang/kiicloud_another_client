package com.kiicloud.platform.extension.rest.entity.query;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class  PagingResult<T>  {

	private List<T> results=new ArrayList<T>();
	
	private QueryParam queryParam;
	


	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public QueryParam getQueryParam() {
		return queryParam;
	}

	public void setQueryParam(QueryParam queryParam) {
		this.queryParam = queryParam;
	}

	public boolean isLastPage() {
		return StringUtils.isEmpty(queryParam.getPaginationKey());
	}

	
	private String nextPaginationKey;
	
	
	

	public String getNextPaginationKey() {
		return nextPaginationKey;
	}

	public void setNextPaginationKey(String nextPaginationKey) {
		this.nextPaginationKey = nextPaginationKey;
	}

	public boolean hasNext() {
		// TODO Auto-generated method stub
		return StringUtils.isNotBlank(nextPaginationKey);
	}

}
