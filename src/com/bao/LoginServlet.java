package com.bao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.safeboxserver.msg.MsgString;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("=========doGet========");
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String action = req.getParameter(MsgString.PARAMS_ACTION);
		String username = "";
		String password = "";
		if(action.equals(MsgString.PARAMS_QUERY) || action.equals(MsgString.PARAMS_INSERT)){
			username = req.getParameter(MsgString.PARAMS_USERNAME);
			password = req.getParameter(MsgString.PARAMS_PASSWORD);
			System.out.println("=========username========"+username);
			System.out.println("=========password========"+password);
		}
		
		
		/*resp.setContentType("text/html");
		  req.setCharacterEncoding("UTF-8");
		  resp.setCharacterEncoding("UTF-8");
		*/
		
		DBImplement dbImp = new DBImplement();
		if(action.equals(MsgString.PARAMS_QUERY)){
			String result = dbImp.querySql(username, password);
			System.out.println("doPost result ");
			System.out.println("doPost result = " + result);
			if(dbImp.querySqlResult()){
				out.println(result);
			}else{
				out.println(MsgString.FAILED);
			}
		}else if(action.equals(MsgString.PARAMS_INSERT)){
			if(dbImp.insertSql(username, password)){
				out.println(MsgString.SUCCESS);
			}else{
				out.println(MsgString.FAILED);
			}
		}/*else if(action.equals(MsgString.PARAMS_SEND_EXCEPTION)){
			if(dbImp.insertSql(username, password)){
				out.println(MsgString.SUCCESS);
			}else{
				out.println(MsgString.FAILED);
			}
			System.out.println("dopost params exception: " + req.getParameter(MsgString.PARAMS_EXCEPTION));
			out.println(MsgString.SUCCESS);
		}*/
		
		
		
		
		
		//EncryptDecrypt crypt = new EncryptDecrypt();
		
		/*//加密   
		System.out.println("加密前：" + username);  
		byte[] encryptResult = crypt.encrypt(username, password); 
		//System.out.println("加密后：" + encryptResult);
		//解密   
		byte[] decryptResult = crypt.decrypt(encryptResult,password);  
		System.out.println("解密后：" + new String(decryptResult));  
		
		
		//加密   
		System.out.println("加密前：" + username);  
		byte[] encryptResult = crypt.encrypt(username, password);  
		System.out.println("加密后 byte[]：" + encryptResult); 
		String encryptResultStr = crypt.ByteToString(encryptResult);  
		System.out.println("加密后 by ByteToString：" + encryptResultStr);  
		//解密   
		byte[] decryptFrom = crypt.StringToByte(encryptResultStr); 
		System.out.println("解密 by ByteToString：" + encryptResultStr);
		byte[] decryptResult = crypt.decrypt(decryptFrom,password);  
		System.out.println("解密后：" + new String(decryptResult));  
		*/
		
		
	         /*
	        String keyStr = "350102198910102321胡某某";  
	 
	        String plainText = "this is a string will be AES_Encrypt";
	         
	        String encText = crypt.encrypt(keyStr, plainText);
	        String decString = crypt.decrypt(keyStr, encText);
	         
	        System.out.println(encText); 
	        System.out.println(decString); 
	 
		
		try {
			while(result.next()){
			System.out.println("result: "+result.getString(1)+result.getString(2));
			
			
			
//		     ServletRequest session = null;;
//		     session.getAttribute("username",username);
			     PrintWriter out = resp.getWriter();
			     out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			     out.println("<HTML>");
			     out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
			     out.println("  <BODY>");
			     out.print("    This is login JSP");
			     out.println(username+"登陆成功");
			     out.println("  </BODY>");
			     out.println("</HTML>");
			     out.flush();
			     out.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

}
