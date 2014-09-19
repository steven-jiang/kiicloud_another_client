package com.kiicloud.platform.extension.rest.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class EventEntity {
	
	/*
	 * _deviceID		string	
_type		string	
_triggeredAt		long	
_uploadedAt	
	 */

	
	abstract protected String getEventType();
	
	private String deviceID;
	
	private Date tiggeredAt;
	
	private Date uploadedAt;

	@JsonProperty("_deviceID")
	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	@JsonProperty("_type")
	public String getType() {
		return getEventType();
	}

	

	@JsonProperty("_triggeredAt")
	public Date getTiggeredAt() {
		return tiggeredAt;
	}

	public void setTiggeredAt(Date tiggeredAt) {
		this.tiggeredAt = tiggeredAt;
	}

	@JsonProperty("_uploadedAt")
	public Date getUploadedAt() {
		return uploadedAt;
	}

	public void setUploadedAt(Date uploadedAt) {
		this.uploadedAt = uploadedAt;
	}
	
	
}
