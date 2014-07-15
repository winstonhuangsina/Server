package com.bao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncryptServiceServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		System.out.println("=========doGet========");
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = resp.getWriter();
		System.out.println("=========doPost========");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println("=========username========"+username);
		System.out.println("=========password========"+password);
		
		EncryptDecrypt crypt = new EncryptDecrypt();
		
		//加密   
		/*System.out.println("加密前：" + username);  
		byte[] encryptResult = crypt.encrypt(username, password); 
		System.out.println("加密后：" + new String(encryptResult));
		//解密   
		byte[] decryptResult = crypt.decrypt(encryptResult,password);  
		System.out.println("解密后：" + new String(decryptResult));  */
		
		
		/*
		DBConnTest dbconn = new DBConnTest();
		ResultSet result = dbconn.doPost(username, password);
		System.out.println(result);
		
		try {
			while(result.next()){
			System.out.println("result: "+result.getString(1)+result.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
	}
	
	



}
