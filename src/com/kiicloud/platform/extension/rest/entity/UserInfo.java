package com.kiicloud.platform.extension.rest.entity;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;

public class UserInfo {

	/*
	 * userID	string	The user ID
internalUserID	long	The user internal ID
loginName	string	The login name
displayName	string	The name to display for the user
country	string	The country of the user
locale	string	The locale of the user
emailAddress	string	The e-mail address of the user
emailAddressVerified	boolean	Indicates whether the e-mail address is verified or not
phoneNumber	string	The phone number of the user
phoneNumberVerified
	 */
	
	private String userID;
	private long internalUserID;
	private String loginName;
	private String displayName;
	private String country;
	
	private String locale;
	
	private String emailAddress;
	
	private boolean emailAddressVerified;
	
	private String phoneNumber;
	
	private boolean phoneNumerVerified;
	
	private String password;
	
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public long getInternalUserID() {
		return internalUserID;
	}

	public void setInternalUserID(long internalUserID) {
		this.internalUserID = internalUserID;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public boolean isEmailAddressVerified() {
		return emailAddressVerified;
	}

	public void setEmailAddressVerified(boolean emailAddressVerified) {
		this.emailAddressVerified = emailAddressVerified;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isPhoneNumerVerified() {
		return phoneNumerVerified;
	}

	public void setPhoneNumerVerified(boolean phoneNumerVerified) {
		this.phoneNumerVerified = phoneNumerVerified;
	}
	
	private final Map<String,Object>  additionProps=new HashMap<String,Object>();
	
	public Object getCustomProperty(String propName){
		return additionProps.get(propName);
	}
	
	@JsonAnySetter
	public void setCustomProp(String propName,Object val){
		additionProps.put(propName, val);
	}
}
