package com.kiicloud.platform.extension.rest.entity;

import org.apache.commons.lang.StringUtils;


public class BucketInfo{
		
		public String getQueryPath() {
			return getBucketPath()+"/query";
		}
		
		public String getObjectPath(String id) {
			return getObjectPath()+"/"+id;
		}
		
		public String getObjectPath() {
			return getBucketPath()+"/objects";
		}
		
		public String getBucketPath() {
			return scope.getName()+"/buckets/"+bucketName;
		}
		
		private ScopeType scope;
		
		private String bucketName;

		private BucketInfo(String name,ScopeType type){
			this.bucketName=name;
			this.scope=type;
		}

		
		public static BucketInfo getInfo(String name){
			String bucketName=name;
			ScopeType type=ScopeType.APP;
			if(name.contains(":")){
				String[] array=StringUtils.split(name,":");
				bucketName=array[1];
				type=ScopeType.valueOf(array[0]);
			}
			
			return new BucketInfo(bucketName,type);
		}


	}