package com.kiicloud.platform.extension.rest.entity.analyze;

import java.util.List;

public class AnalyzeResultInTab {
	
	private List<ResultLabel> labels;
	
	private List<AnalyzeRow> snapshots;

	public List<ResultLabel> getLabels() {
		return labels;
	}

	public void setLabels(List<ResultLabel> labels) {
		this.labels = labels;
	}

	public List<AnalyzeRow> getSnapshots() {
		return snapshots;
	}

	public void setSnapshots(List<AnalyzeRow> snapshots) {
		this.snapshots = snapshots;
	}
	
	

}
