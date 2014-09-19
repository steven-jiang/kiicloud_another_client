package com.kiicloud.platform.extension.rest.operate;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kiicloud.platform.extension.rest.entity.UserInfo;

@Component
public class CommUserOperate extends UserOperate<UserInfo> {

	@Override
	protected Class<UserInfo> getUserCls() {
		return UserInfo.class;
	}

	@Override
	protected TypeReference<List<UserInfo>> getTypeReference() {
		return new TypeReference<List<UserInfo>>(){};
	}

	

}
