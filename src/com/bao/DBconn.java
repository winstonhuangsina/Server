package com.bao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.safeboxserver.msg.MsgString;

//import com.mysql.jdbc.Statement;

public class DBconn {

	Connection conn = null;
	PreparedStatement statement = null;
	
	public DBconn(){
		if(MsgString.LOCAL_DATABASE_TRIGGER){
			intialLocalDB();
		}
		else{
			initialNetworkDB();
		}
	}
	
	
	public void initialNetworkDB() {
		try {
			String connName = MsgString.DATABASE_URL + MsgString.DATABASE_NAME;
			Class.forName(MsgString.DATABASE_DRIVER_NAME);
			conn = DriverManager.getConnection(connName,
					MsgString.DATABASE_USERNAME, MsgString.DATABASE_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
	}
	
	public void intialLocalDB() {
		try {
			Class.forName(MsgString.DATABASE_DRIVER_NAME);
			String url = MsgString.LOCAL_DATABASE_URL;
			conn = DriverManager.getConnection(url, MsgString.LOCAL_DATABASE_USERNAME, MsgString.LOCAL_DATABASE_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
	}
	
	
	public ResultSet doPost(String username, String password) {

		ResultSet rs = null;
		if (conn != null) {
			String sql = " select * from user_info_table";
			try {
				System.out.println("select from user_info_table by "+ username +" and "+password);
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, username);
				ps.setString(2, password);
				rs = ps.executeQuery();
				return rs;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else return null;
		return rs;
	}
	
	 // execute selection language  
	
	 ResultSet querySQL(String username, String password) { 
	    	String sql = " select * from user_info_table where user_name = '"+ username + "' and " + " user_password = '" + password + "'";
	        System.out.println(sql);
	    	ResultSet rs = null;  
	        try {  
	            statement = conn.prepareStatement(sql);  
	            rs = statement.executeQuery(sql);  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }  
	        return rs;  
	    }  
	
    ResultSet selectSQL() { 
    	String sql = " select * from user_info_table";
        ResultSet rs = null;  
        try {  
            statement = conn.prepareStatement(sql);  
            rs = statement.executeQuery(sql);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return rs;  
    }  
	
	public boolean insertSQL(String sql) {  
        try {  
        	statement = conn.prepareStatement(sql);  
            statement.executeUpdate();  
            return true;  
        } catch (SQLException e) {  
            System.out.println("插入数据库时出错：");  
            e.printStackTrace();  
        } catch (Exception e) {  
            System.out.println("插入时出错：");  
            e.printStackTrace();  
        }  
        return false;  
    }  
	
	public boolean insertSQLByUsernamePsw(String username, String password) {  
        try {  
        	String sql = "INSERT INTO user_info_table (user_name, user_password) VALUES ('" + username + "', '" + password + "')";
        	System.out.println(sql);
        	statement = conn.prepareStatement(sql);  
            statement.executeUpdate();  
            return true;  
        } catch (SQLException e) {  
            System.out.println("插入数据库时出错：");  
            e.printStackTrace();  
        } catch (Exception e) {  
            System.out.println("插入时出错：");  
            e.printStackTrace();  
        }  
        return false;  
    } 

	//execute delete language  
    boolean deleteSQL(String sql) {  
        try {  
            statement = conn.prepareStatement(sql);  
            statement.executeUpdate();  
            return true;  
        } catch (SQLException e) {  
            System.out.println("插入数据库时出错：");  
            e.printStackTrace();  
        } catch (Exception e) {  
            System.out.println("插入时出错：");  
            e.printStackTrace();  
        }  
        return false;  
    }  
    //execute update language  
    boolean updateSQL(String sql) {  
        try {  
            statement = conn.prepareStatement(sql);  
            statement.executeUpdate();  
            return true;  
        } catch (SQLException e) {  
            System.out.println("插入数据库时出错：");  
            e.printStackTrace();  
        } catch (Exception e) {  
            System.out.println("插入时出错：");  
            e.printStackTrace();  
        }  
        return false;  
    }  


}
