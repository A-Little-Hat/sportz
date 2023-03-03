package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/AddToWishlist")
public class AddToWishlist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		
		try {
			
			String email = "";	
			

			boolean flag = false;

			Cookie allCookies[] = request.getCookies();
			if (allCookies != null) {
				for (Cookie cookie : allCookies) {
					if (cookie.getName().equals("email") && cookie.getValue() != "") {
						email = cookie.getValue();
						System.out.println(email);
						flag = true;
					}
				}
			}
			if (!flag) {
				System.out.println("hello world ");
				request.getRequestDispatcher("./SignIn").include(request, response);
			}else {
				System.out.println("nbdhuedhd");
				
				Connection con;
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportz", "root", "soumya@143");
//				java.sql.Statement stmt = con.createStatement();
//				 
				 String sql = "select product_id,product_price,product_image,product_name from product where product_id in (select product_id from wishlist where email = '"+email+"');";
				 PreparedStatement pstm;
				 pstm = con.prepareStatement(sql);
				ResultSet rs = pstm.executeQuery();
//				
				JSONArray array = new JSONArray();
				while (rs.next()) {
					System.out.println("tolar ta");
					JSONObject record = new JSONObject();
					record.put("product_name", rs.getString("product_name"));
					record.put("product_image", rs.getString("product_image"));
					record.put("product_price", rs.getInt("product_price"));
					record.put("product_id", rs.getInt("product_id"));
					array.add(record);
				}
				System.out.println(array.toString());
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("products", array);
				System.out.println(array);

				response.addHeader("Access-Control-Allow-Origin", "*");
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				ServletOutputStream out = response.getOutputStream();
				out.print(jsonObject.toJSONString());
			}
			
		} catch (Exception e) {
			
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String email = "";

			boolean flag = false;

			String parameter = request.getQueryString();
			String a[] = parameter.split("=");

			System.out.println(a[1]);
			int id = Integer.parseInt(a[1]);

			Cookie allCookies[] = request.getCookies();
			if (allCookies != null) {
				for (Cookie cookie : allCookies) {
					if (cookie.getName().equals("email") && cookie.getValue() != "") {
						email = cookie.getValue();
						System.out.println("hello");
						System.out.println(email);
						flag = true;
					}
				}
			}
			if (!flag) {
				System.out.println("hello world ");
				request.getRequestDispatcher("./SignIn").include(request, response);
			} else {
				Connection con;
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportz", "root", "soumya@143");
				String sql = "SELECT product_id from wishlist where product_id = " + id + ";";
				PreparedStatement pr = con.prepareStatement(sql);
				ResultSet rs = pr.executeQuery();
				int product_id = 0;
				while (rs.next()) {
					product_id = rs.getInt("product_id");
				}

				if (product_id > 0) {

					System.out.println("else part");

					response.setStatus(200);

				} else {

					sql = "INSERT INTO wishlist values ('" + email + "'," + id + ");";
					pr = con.prepareStatement(sql);
					pr.executeUpdate();
				}
				response.setStatus(201);
			}
		} catch (Exception e) {
			System.out.print(e);
		}

		doGet(request, response);
	}

}
