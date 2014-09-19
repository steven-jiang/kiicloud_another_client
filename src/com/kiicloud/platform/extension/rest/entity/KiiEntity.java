package com.kiicloud.platform.extension.rest.entity;

import java.util.Date;
import java.util.Map;

public class KiiEntity {

	/*
	 * _id ... The object's internal identifier assigned by Kii Cloud or explicitly by you.
_version ... The version of the object.
_dataType ... The data type of the object.
_owner ... The user ID of the object owner.
_created ... The object's created date in UNIX time (msec)
_modified ... The object's last updated date in UNIX time (msec)

	 */
	
//	public KiiEntity(Map<String,Object> dataMap){
//	    _created=(Date) dataMap.get("_created");
//		_dataType=(String)dataMap.get("_dataType");
//		_id=(String)(dataMap.get("_id"));
//		
//	}
	
	private String _id;
	
	private int _version;
	
	private String _dataType;
	
	private String _owner;
	
	private Date _created;
	
	private Date _modified;
	
	

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public int get_version() {
		return _version;
	}

	public void set_version(int _version) {
		this._version = _version;
	}

	public String get_dataType() {
		return _dataType;
	}

	public void set_dataType(String _dataType) {
		this._dataType = _dataType;
	}

	public String get_owner() {
		return _owner;
	}

	public void set_owner(String _owner) {
		this._owner = _owner;
	}

	public Date get_created() {
		return _created;
	}

	public void set_created(Date _created) {
		this._created = _created;
	}

	public Date get_modified() {
		return _modified;
	}

	public void set_modified(Date _modified) {
		this._modified = _modified;
	}
	
	
	
}
