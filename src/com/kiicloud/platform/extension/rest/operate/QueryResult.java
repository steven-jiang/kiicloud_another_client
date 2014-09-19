package com.kiicloud.platform.extension.rest.operate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;







public  class QueryResult<T>{
	
	private String queryDescription;
	
//	private List<JsonString> results;
	

	public String getQueryDescription() {
		return queryDescription;
	}

	public void setQueryDescription(String queryDescription) {
		this.queryDescription = queryDescription;
	}

	
//	public List<JsonString> getResults() {
//		return results;
//	}
//
//	public void setResults(List<JsonString> results) {
//		this.results = results;
//	}

	private String nextPaginationKey;
	
	public String getNextPaginationKey() {
		return nextPaginationKey;
	}

	public void setNextPaginationKey(String nextPaginationKey) {
		this.nextPaginationKey = nextPaginationKey;
	}
	
	private List<T> result=new ArrayList<T>();
	
	public List<T> getResults(){
		return result;
	};
	
	public  void setResults(List<T>  list){
		this.result=list;
	};
	
	@JsonIgnore
	public boolean hasNext(){
		return StringUtils.isNotBlank(nextPaginationKey);
	}
}
