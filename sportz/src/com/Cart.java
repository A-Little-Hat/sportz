package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie allCookies[] = request.getCookies();
		if(allCookies!=null) {
        	for (Cookie cookie : allCookies) {
				if(cookie.getName().equals("email") && cookie.getValue() != "") {
					RequestDispatcher rd = request.getRequestDispatcher("./cart.html");
					rd.include(request, response);
				}
        	}
		}else {
//        		response.sendRedirect("./SignIn");
        		RequestDispatcher rd = request.getRequestDispatcher("./SignIn");
				rd.forward(request, response);
        	}
		}


	@SuppressWarnings("unchecked")
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
		try {
			Connection con;
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportz", "root", "soumya@143");
			java.sql.Statement stmt = con.createStatement();
			String sql = "select product_name,price,product_image,product_id,quantity from cart where email='"+email+"';";
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(sql);
			JSONArray array = new JSONArray();
			while (rs.next()) {
				JSONObject record = new JSONObject();
				record.put("product_name", rs.getString("product_name"));
				record.put("product_image", rs.getString("product_image"));
				record.put("product_price", rs.getInt("price"));
				record.put("quantity", rs.getInt("quantity"));
				record.put("product_id", rs.getInt("product_id"));
				array.add(record);
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("products", array);
			System.out.println(array);

			response.addHeader("Access-Control-Allow-Origin", "*");
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			ServletOutputStream out = response.getOutputStream();
			out.print(jsonObject.toJSONString());
		} catch (Exception e) {
			System.out.print(e);
		}

	}
}