package com.kiicloud.platform.extension.rest.entity.query.condition;

import com.kiicloud.platform.extension.rest.entity.query.ConditionType;
import com.kiicloud.platform.extension.rest.entity.query.LatlonPoint;

public class GeoBox extends SimpleCondition {

	@Override
	public ConditionType getType() {
		return ConditionType.geobox;
	}
	
	private Box box;
	
	

	public Box getBox() {
		return box;
	}



	public void setBox(Box box) {
		this.box = box;
	}



	public static class Box {
		private LatlonPoint ne;

		private LatlonPoint sw;

		public LatlonPoint getNe() {
			return ne;
		}

		public void setNe(LatlonPoint ne) {
			this.ne = ne;
		}

		public LatlonPoint getSw() {
			return sw;
		}

		public void setSw(LatlonPoint sw) {
			this.sw = sw;
		}

	}

}
