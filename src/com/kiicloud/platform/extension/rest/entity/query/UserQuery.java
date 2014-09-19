package com.kiicloud.platform.extension.rest.entity.query;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserQuery extends QueryParam {

	@Override
	@JsonProperty("userQuery")
	public ClauseParam getBucketQuery() {
		return super.getBucketQuery();
	}
	
	
}
