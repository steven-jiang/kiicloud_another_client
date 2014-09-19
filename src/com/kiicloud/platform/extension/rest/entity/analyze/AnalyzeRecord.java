package com.kiicloud.platform.extension.rest.entity.analyze;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnalyzeRecord {
/*
 *     "name" : 0,
    "data" : [ 236, 236, 236,  (...snip...)   , 236 ],
    "pointStart" : 1357862400000,
    "pointInterval" : 86400000

 */
	private Object name;
	
	private List<Object>  data=new ArrayList<Object>();
	
	private Date pointStart;
	
	private long pointInterval;

	public Object getName() {
		return name;
	}

	public void setName(Object name) {
		this.name = name;
	}

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

	public Date getPointStart() {
		return pointStart;
	}

	public void setPointStart(Date pointStart) {
		this.pointStart = pointStart;
	}

	public long getPointInterval() {
		return pointInterval;
	}

	public void setPointInterval(long pointInterval) {
		this.pointInterval = pointInterval;
	}
	
	
	
}
