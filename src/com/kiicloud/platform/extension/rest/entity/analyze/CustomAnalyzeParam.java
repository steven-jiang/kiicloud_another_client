package com.kiicloud.platform.extension.rest.entity.analyze;

import java.util.List;

import com.kiicloud.platform.extension.rest.entity.query.condition.Equal;

public class CustomAnalyzeParam {

	
	private String groupBy;
	
	private List<Equal> havingList;
	
//	
//	
//	private Number sum(List<Number> list){
//		Number sum=0;
//		for(Number val:list){
//			sum+=val;
//		}
//		return sum;
//	}
	
	public static enum CollectFun{
		
		Min,Max,Sum,Count,Ave;
		
	}
	
}
