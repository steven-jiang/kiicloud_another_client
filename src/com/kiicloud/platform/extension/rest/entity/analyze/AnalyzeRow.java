package com.kiicloud.platform.extension.rest.entity.analyze;

import java.util.Date;
import java.util.List;

public class AnalyzeRow {
	
	private Date createAt;
	
	private List<List<Object>>  data;

	public Date getCreatedAt() {
		return createAt;
	}

	public void setCreatedAt(Date createBy) {
		this.createAt = createBy;
	}

	public List<List<Object>> getData() {
		return data;
	}

	public void setData(List<List<Object>> data) {
		this.data = data;
	}

	
	
}
