package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


@WebServlet("/Football")
public class Football extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("football.html");
		rd.include(request, response);
	
	}


	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection con;
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportz", "root", "soumya@143");
			java.sql.Statement stmt = con.createStatement();
			String sql = "select product_name,product_description,product_category,product_price,count,product_id,product_image from product where product_category='football';";
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(sql);
			System.out.println(rs.toString());
			JSONArray array = new JSONArray();
			while (rs.next()) {
				JSONObject record = new JSONObject();
				record.put("product_name", rs.getString("product_name"));
				record.put("product_description", rs.getString("product_description"));
				record.put("product_category", rs.getString("product_category"));
				record.put("product_price", rs.getInt("product_price"));
				record.put("count", rs.getInt("count"));
				record.put("product_id", rs.getInt("product_id"));
				record.put("product_image", rs.getString("product_image"));
				array.add(record);
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("products", array);
//			System.out.println(array);

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
