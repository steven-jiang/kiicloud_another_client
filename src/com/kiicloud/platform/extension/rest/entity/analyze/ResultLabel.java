package com.kiicloud.platform.extension.rest.entity.analyze;

public class ResultLabel {
	
	private String label;
	
	private LabelType type;
	
	
	
	public String getLabel() {
		return label;
	}



	public void setLabel(String label) {
		this.label = label;
	}



	public LabelType getType() {
		return type;
	}



	public void setType(LabelType type) {
		this.type = type;
	}



	public static enum LabelType {
		DIMENSION,FACT;
	}

}
