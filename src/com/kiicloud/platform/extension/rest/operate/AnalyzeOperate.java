package com.kiicloud.platform.extension.rest.operate;

import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.kiicloud.platform.extension.rest.commons.StringTemplate;
import com.kiicloud.platform.extension.rest.entity.analyze.Aggregation;
import com.kiicloud.platform.extension.rest.entity.analyze.AnalyzeParam;
import com.kiicloud.platform.extension.rest.entity.analyze.AnalyzeResultInGroup;
import com.kiicloud.platform.extension.rest.entity.analyze.AnalyzeResultInTab;
import com.kiicloud.platform.extension.rest.entity.analyze.ConvertsionRule;
import com.kiicloud.platform.extension.rest.exception.KiiRestException;
import com.kiicloud.platform.extension.rest.factory.MetaTypeFeature;
import com.kiicloud.platform.extension.rest.factory.ObjectMapperContextResolver;

@Component
public class AnalyzeOperate {

	@Qualifier("appTarget")
	@Autowired
	private  WebTarget appTarget;
	
	@Autowired
	private ObjectMapperContextResolver  resolver;
	
	private static final String urlTmp="/analytics/${0}/data";
	
	
	public AnalyzeResultInGroup getAnalyzeResultInGroup(AnalyzeParam param){
		String url=StringTemplate.gener(urlTmp, param.getRuleID());
		
		
		return param.fillTarget(appTarget.path(url))
				.register(MetaTypeFeature.getAcceptFeature("GroupedAnalyticResult"))
				.request().get(AnalyzeResultInGroup.class);
		
	}
	
	public AnalyzeResultInTab getAnalyzeResultInTab(AnalyzeParam param){
		String url=StringTemplate.gener(urlTmp, param.getRuleID());
		
		
		return param.fillTarget(appTarget.path(url))
				.register(MetaTypeFeature.getAcceptFeature("TabularAnalyticResult"))
				.request().get(AnalyzeResultInTab.class);
		
	}
	
	public void addAnalyzeRule(ConvertsionRule rule,Aggregation aggRule){
		
		String ruleID = addAppConversionRule(rule);
		
		aggRule.setConversionRuleID(ruleID);
		
		addAnalyzeRule(aggRule);
		
		
	}


	public void addAnalyzeRule(Aggregation aggRule) {
		appTarget.path("/aggregation-rules").register(MetaTypeFeature.getKiiFeature("AggregationRule"))
		.request().post(Entity.json(aggRule));
	}


	public String addAppConversionRule(ConvertsionRule rule) {
		Map<String, String> map=appTarget.path("/conversion-rules")
		.register(MetaTypeFeature.getKiiFeature("AppDataConversionRule"))
		.request().post(Entity.json(rule)).readEntity(Map.class);
		return map.get("_id");
	}
	
	

	
	public  List<ConvertsionRule> getConvertRules(){

		JsonNode node= appTarget.path("/conversion-rules")
		.register(MetaTypeFeature.getKiiFeature("AppDataConversionRule"))
		.request().get().readEntity(JsonNode.class);
		
		
		TypeReference<List<ConvertsionRule>> typeRef = new TypeReference<List<ConvertsionRule>>(){};
        try {
			return resolver.getObjectMapper().readValue(node.traverse(), typeRef);
		} catch (Exception e) {
			throw new KiiRestException(e);
		} 
	}
	
	public List<Aggregation> getAllAggregationRules(){
		
		
		
		JsonNode node= appTarget.path("/aggregation-rules")
		.register(MetaTypeFeature.getKiiFeature("AggregationRuleList"))
		.request().get().readEntity(JsonNode.class);
		
		
		TypeReference<List<Aggregation>> typeRef = new TypeReference<List<Aggregation>>(){};
        try {
			return resolver.getObjectMapper().readValue(node.traverse(), typeRef);
		} catch (Exception e) {
			throw new KiiRestException(e);
		} 

	}
	
	
}
