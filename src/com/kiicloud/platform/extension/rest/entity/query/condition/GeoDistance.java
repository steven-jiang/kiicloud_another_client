package com.kiicloud.platform.extension.rest.entity.query.condition;

import com.kiicloud.platform.extension.rest.entity.query.ConditionType;
import com.kiicloud.platform.extension.rest.entity.query.LatlonPoint;

public class GeoDistance extends SimpleCondition {

	@Override
	public ConditionType getType() {
		return ConditionType.geodistance;
	}
	
	/*
	 * "center": {
      "_type": "point",
      "lat": 11.0,
      "lon": 1.0
  }
  "radius": 1023,
  "putDistanceInto
	 */

	private LatlonPoint center;
	
	private int radius;
	
	private String putDisanceInto;

	public LatlonPoint getCenter() {
		return center;
	}

	public void setCenter(LatlonPoint center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public String getPutDisanceInto() {
		return putDisanceInto;
	}

	public void setPutDisanceInto(String putDisanceInto) {
		this.putDisanceInto = putDisanceInto;
	}
	
	
	
	
}

