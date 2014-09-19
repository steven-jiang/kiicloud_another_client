package com.kiicloud.platform.extension.rest.entity;

import org.apache.commons.lang.math.RandomUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoPoint {
/*
 *     "_type": "point",
    "lat": 10.0,
    "lon": 11.1

 */
	
	@JsonProperty("_type")
	public String getType(){
		return "point";
	}
	
	private float lat;
	
	private float lon;

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLon() {
		return lon;
	}

	public void setLon(float lon) {
		this.lon = lon;
	}
	
	public static GeoPoint  getDemo(){
		GeoPoint point=new GeoPoint();
		
	    point.lat=40.0f*(RandomUtils.nextFloat()-0.5f);
	    point.lon=116f*(RandomUtils.nextFloat()-0.5f);
	    
	    return point;
	}
}
