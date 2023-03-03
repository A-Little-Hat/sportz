package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/DeleteFromList")
public class DeleteFromList extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get a dhuk6e");
		String email="";
		Cookie allCookies[] = request.getCookies();
		if(allCookies!=null) {
	    	for (Cookie cookie : allCookies) {
				if(cookie.getName().equals("email") && cookie.getValue() != "") {
					email=cookie.getValue();
				}
	    	}
    	}
		int prodId=0;
		String parameter = request.getQueryString();
		String a[] = parameter.split("=");

		System.out.println(a[1]);
		prodId = Integer.parseInt(a[1]);
		try {
			Connection con;
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportz", "root", "soumya@143");
			java.sql.Statement stmt = con.createStatement();
			String sql = "delete from cart where email='"+email+"' and product_id = "+prodId+";";
			PreparedStatement pstm = con.prepareStatement(sql);
			int x = pstm.executeUpdate();
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.setStatus(200);
		} catch (Exception e) {
			System.out.print(e);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email="";
		Cookie allCookies[] = request.getCookies();
		if(allCookies!=null) {
	    	for (Cookie cookie : allCookies) {
				if(cookie.getName().equals("email") && cookie.getValue() != "") {
					email=cookie.getValue();
				}
	    	}
    	}
//		email="soumyasarkar309@gmail.com";
		int prodId=0;
		String parameter = request.getQueryString();
		String a[] = parameter.split("=");

		System.out.println(a[1]);
		prodId = Integer.parseInt(a[1]);
		try {
			Connection con;
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportz", "root", "soumya@143");
			java.sql.Statement stmt = con.createStatement();
			String sql = "delete from wishlist where email='"+email+"' and product_id = "+prodId+";";
			System.out.println(sql);
			PreparedStatement pstm = con.prepareStatement(sql);
			int x = pstm.executeUpdate();
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.setStatus(200);
		} catch (Exception e) {
			System.out.print(e);
		}
	}

}
