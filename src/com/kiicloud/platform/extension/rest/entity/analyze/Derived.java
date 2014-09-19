package com.kiicloud.platform.extension.rest.entity.analyze;

public class Derived {

	/*
	 *     {"name": "word_count", "source": "tweet.size", "type":"int"},
    {"name": "country", "source": "label.location", "type":"string"},
    {"name": "picture_size", "source": "picture.size", "type":"int"}

	 */
	
	private String name;
	
	private String source;
	
	private FieldType type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public FieldType getType() {
		return type;
	}

	public void setType(FieldType type) {
		this.type = type;
	}
	
	
}
