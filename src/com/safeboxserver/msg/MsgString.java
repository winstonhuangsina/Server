package com.safeboxserver.msg;

public class MsgString {

	public final static String DATABASE_URL = "jdbc:mysql://sqld.duapp.com:4050/";
	public final static String DATABASE_NAME = "XbWKkruxpZAgFXHkixLj";
	public final static String DATABASE_USERNAME = "Bh2RqnYUjGfjvUAAZIh0hWO2";// 用户名(api
	public final static String DATABASE_PASSWORD = "11jwocioh7V0oV0m1DGLzpFEGjZTc0bE";// 密码(secret
	public final static String DATABASE_DRIVER_NAME = "com.mysql.jdbc.Driver";

	public final static String LOCAL_DATABASE_URL = "jdbc:mysql://localhost:3306/safebox";
	public final static String LOCAL_DATABASE_NAME = "safebox";
	public final static String LOCAL_DATABASE_USERNAME = "root";// 用户名(api
	public final static String LOCAL_DATABASE_PASSWORD = "";// 密码(secret
	
	
	// URL on HttpClientToServer to Romote DB
	public final static String PARAMS_USERNAME = "PARAMS_USERNAME";
	public final static String PARAMS_PASSWORD = "PARAMS_PASSWORD";
	public final static String PARAMS_EXCEPTION = "PARAMS_EXCEPTION";
	public final static String PARAMS_ACTION = "PARAMS_ACTION";
	public final static String PARAMS_QUERY = "PARAMS_QUERY";
	public final static String PARAMS_INSERT = "PARAMS_INSERT";
	public final static String PARAMS_SEND_EXCEPTION = "PARAMS_SEND_EXCEPTION";
	
	public final static String SUCCESS = "SUCCESS";
	public final static String FAILED = "FAILED";
	
	// Trigger to switch local server and romote server;
	public final static boolean LOCAL_DATABASE_TRIGGER = false;
	
	

	// ACCOUNT数据库常量
	public final static String ACCOUNT_INFO_TABLE = "account_info_table";
	public final static String ACCOUNT_ID = "account_id";
	public final static String SITE_NAME = "site_name";
	public final static String ACCOUNT_PASSWORD = "account_password";
	public final static String ACCOUNT_NAME = "account_name";
	public final static String ACCOUNT_TYPE = "account_type";
	public final static String IS_LOCKED = "is_locked";

	// 用户名数据库常量
	public final static String USER_INFO_TABLE = "user_info_table";
	public final static String USER_ID = "user_id";
	public final static String USER_PASSWORD = "user_password";
	public final static String USER_NAME = "user_name";

}
