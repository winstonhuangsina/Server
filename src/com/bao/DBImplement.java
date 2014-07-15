package com.bao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.safeboxserver.msg.MsgString;

public class DBImplement {
	DBconn dbconn = null;
	boolean result = false;
	
	public DBImplement(){
		dbconn = new DBconn();
	}
	
	public String querySql(String username, String password){
		String user_id = "";
		try {
			ResultSet rs = dbconn.querySQL(username, password);
			if(rs.next()){
				result = true;
				System.out.println("before getString ");
				//userId = rs.getString(MsgString.USER_ID);
				user_id = rs.getString("user_id");
				//if(userId!=""){
					//System.out.println("userId = ");
					System.out.println("user_id = "+user_id);
				//}
				//if(user_Id!=""){
					//System.out.println("user_Id = ");
					//System.out.println("user_Id = "+user_Id);
				//}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return user_id;
	}
	
	public boolean querySqlResult(){
		return result;
	}
	
	
	public boolean insertSql(String username, String password){
		//DBconn dbconn = new DBconn();
		return dbconn.insertSQLByUsernamePsw(username, password);
	}
	
}
