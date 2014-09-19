package com.kiicloud.platform.extension.rest.entity.analyze;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.WebTarget;

import org.apache.commons.lang.StringUtils;

public class AnalyzeParam {

	/*
	 * group	No	string	Must match one of the group by column name specified in the associated rule. If no group is provided, result data will be reaggregated and group name will be DEFAULT.
startDate	No	DateParamType	Description : Used to filter result set. Works in pair with 'endDate' param. If one is missing, validation error will occur.
endDate	No	DateParamType	Used to filter result set. Works in pair with 'startDate' param. If one is missing, validation error will occur.
start-time	No	long	Deprecated. Used only when both startDate and endDate are missing. Works in pair with 'end-time' param. If both startDate and endDate are missing and either start-time or end-time is missing, validation error will occur.
end-time	No	long	Deprecated. Used only when both startDate and endDate are missing. Works in pair with 'start-time' param. If both startDate and endDate are missing and either start-time or end-time is missing, validation error will occur.
filterN.name	No	string	Works with filterN.value. (N is integral number. So actual param name is like filter001.name.) filterN.name must match one of the group by column name specified in the associated rule. Multiple pairs are combined with AND. If any one is missing from the pair, validation error will occur.
filterN.value	No	string	Works with filterN.name. (N is integral number. So actual param name is like filter001.value.) If value can't be converted to the column type in the associated rule, validation error will occur.
cumulative	No	
	 */
	
	private String group;
	
	private Date startDate;
	
	private Date endDate;
	
	private final Map<String,Object>  filterMap= new HashMap<String,Object>();
	
	private Boolean cumulative=null;

	

	public void setGroup(String group) {
		this.group = group;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public void setCumulative(boolean cumulative) {
		this.cumulative = cumulative;
	}

	
	public void addFilter(String name,Object val){
		filterMap.put(name, val);
	}
	
	public WebTarget fillTarget(WebTarget target){
		
		if(StringUtils.isNotBlank(group)){
			target=target.queryParam("group", group);
		}
		//2012-12-1
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		
		if(startDate!=null){
			target=target.queryParam("startDate", format.format(startDate))
			.queryParam("endDate", format.format(endDate));
		}
		if(!filterMap.isEmpty()){
			int idx=0;
			for(Map.Entry<String, Object> entry:filterMap.entrySet()){
				target=target
						.queryParam("filter"+idx+".name",entry.getKey())
						.queryParam("filter"+idx+".value", entry.getValue());
			}
		}
		
		if (cumulative!=null){
			target=target.queryParam("cumulative", cumulative);
		}
		
		return target;
		
	}
	
	
	private int ruleName;

	public String getRuleID() {
		return String.valueOf(ruleName);
	}

	public void setRuleID(int id) {
		this.ruleName = id;
	}
	
	
	
	
}
