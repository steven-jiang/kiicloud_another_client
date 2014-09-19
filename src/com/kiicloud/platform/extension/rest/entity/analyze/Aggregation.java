package com.kiicloud.platform.extension.rest.entity.analyze;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kiicloud.platform.extension.rest.entity.analyze.Aggregate.CollectFun;

public class Aggregation {
	
	/*
	 *  _id: "4b1d2b5c5da23f69544e0331f5ac932f623ed85e",
    "name": "Event count by application and location",
    "aggregate" : { "valueOf" : "*", "type": "string", "with": "count"},
    "source":"event",
    "groupBy": [
        { "name": "appName", "label": "Application Name" "type": "string"},
        { "name": "location","label": "Location", "type": "string" }
    ]
	 */

	
	private String id;
	
	private String name;
	
	private Aggregate  aggregate;
	
	private SourceType source;
	
	private List<GroupByInfo>  groupBy=new ArrayList<GroupByInfo>();
	
	private String conversionRuleID;
	
	
	
	
	public String getConversionRuleID() {
		return conversionRuleID;
	}

	public void setConversionRuleID(String conversionRuleID) {
		this.conversionRuleID = conversionRuleID;
	}

	@JsonProperty("_id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Aggregate getAggregate() {
		return aggregate;
	}

	public void setAggregate(Aggregate aggregate) {
		this.aggregate = aggregate;
	}
	
	public void setAggregate(String name,CollectFun fun,FieldType type){
		this.aggregate=new Aggregate();
		aggregate.setType(type);
		aggregate.setValueOf(name);
		aggregate.setWith(fun);
	}

	public SourceType getSource() {
		return source;
	}

	public void setSource(SourceType source) {
		this.source = source;
	}

	public List<GroupByInfo> getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(List<GroupByInfo> groupBy) {
		this.groupBy = groupBy;
	}
	
	public void addGroupBy(String label,String name,GroupByType type){
		GroupByInfo g=new GroupByInfo();
		g.setLabel(label);
		g.setName(name);
		g.setType(type);
		this.groupBy.add(g);
	}
	
	//==========================
	
	public enum SourceType{
		APP,EVENT;
	}
	
	public enum GroupByType{
		TINYINT,SMALLINT,INT,BIGINT,FLOAT,BOOLEAN,STRING;

	}

	public static class GroupByInfo {
		private String name;
		private String label;
		
		private GroupByType type;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public GroupByType getType() {
			return type;
		}

		public void setType(GroupByType type) {
			this.type = type;
		}
		
		
	}
	
}
