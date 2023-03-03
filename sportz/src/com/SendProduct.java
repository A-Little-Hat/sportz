package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("serial")
@WebServlet("/SendProduct")
public class SendProduct extends HttpServlet {
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection con;
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportz", "root", "soumya@143");
			@SuppressWarnings("unused")
			java.sql.Statement stmt = con.createStatement();
			String sql = "select product_id,product_name,product_description,product_category,product_price,count,product_image from product;";
			PreparedStatement pr=con.prepareStatement(sql);
			ResultSet rs =pr.executeQuery();
			System.out.println(rs);
			JSONArray array = new JSONArray();
			while (rs.next()) {
				JSONObject record = new JSONObject();
				record.put("product_name", rs.getString("product_name"));
				record.put("product_description", rs.getString("product_description"));
				record.put("product_category", rs.getString("product_category"));
				record.put("product_price", rs.getInt("product_price"));
				record.put("product_image", rs.getString("product_image"));
				record.put("count", rs.getInt("count"));
				record.put("product_id", rs.getInt("product_id"));
				array.add(record);
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("products", array);
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
