package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

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

@WebServlet("/Thankyou")
public class Thankyou extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unchecked")
	public static JSONArray convert(ResultSet resultSet) throws Exception {
		 
	    JSONArray jsonArray = new JSONArray();
	 
	    while (resultSet.next()) {
	 
	        int columns = resultSet.getMetaData().getColumnCount();
	        JSONObject obj = new JSONObject();
	 
	        for (int i = 0; i < columns; i++) {
	            obj.put(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase(), resultSet.getObject(i + 1));
	        }
	        jsonArray.add(obj);
	    }
//	    JSONObject jsonObject = new JSONObject();
//		jsonObject.put("products", jsonArray);
	    return jsonArray;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		

		String address = request.getParameter("c_address");
		String subaddress = request.getParameter("subAddress");
		String postalZip = request.getParameter("c_postal_zip");
		String emailForm = "";
		String phone = request.getParameter("c_phone");

		String email = "";
		Cookie allCookies[] = request.getCookies();
		if (allCookies != null) {
			for (Cookie cookie : allCookies) {
				if (cookie.getName().equals("email") && cookie.getValue() != "") {
					email = cookie.getValue();
					emailForm = cookie.getValue();
				}
			}
		}
		
		System.out.println(address + postalZip + phone + email);
		try {
			System.out.println("try");
			Connection con;
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportz", "root", "soumya@143");
			String sql = "select product_name,product_id,price,quantity from cart where email='" + email + "';";
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			int product_id = 0;
			String product_name = "";
			String name = "";
			int quantity = 0;
			int price = 0;
			JSONArray array = new JSONArray();
			while (rs.next()) {
				JSONObject record = new JSONObject();
				record.put("product_id", rs.getInt("product_id"));
				record.put("product_name", rs.getString("product_name"));
				record.put("quantity", rs.getInt("quantity"));
				record.put("product_price", rs.getInt("price"));
				array.add(record);
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("products", array);
			System.out.println(array);
			sql = "select name from customer where email = '" + email + "';";
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				name = rs.getString(1);
			}
			sql = "INSERT INTO UserOrder values ("+null+",'" + name + "','" + phone + "','" + address + "','"
					+ emailForm + "','"+ postalZip + "','"+array+"');";
			pstm = con.prepareStatement(sql);
			int a = pstm.executeUpdate();
			System.out.println(a);
			RequestDispatcher rd = request.getRequestDispatcher("thankyou.html");
			rd.include(request, response);
		} catch (Exception e) {
			System.out.print(e);
		}
		
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = "";
		Cookie allCookies[] = request.getCookies();
		if (allCookies != null) {
			for (Cookie cookie : allCookies) {
				if (cookie.getName().equals("email") && cookie.getValue() != "") {
					email = cookie.getValue();
				}
			}
		}
		
		try {
			Connection con;
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportz", "root", "soumya@143");
			java.sql.Statement stmt = con.createStatement();
			
			String sql = "select orderId,customerName,orderPhoneNumber,orderAddress,product, userEmail, pincode from userOrder where userEmail = '"+email+"' ORDER BY orderId DESC LIMIT 1;";
//			String sql = "select product from userOrder;";
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(sql);
			JSONArray array = new JSONArray();
			while (rs.next()) {
//				System.out.println(rs.next());
				JSONObject record = new JSONObject();
				record.put("orderPhoneNumber", rs.getString("orderPhoneNumber"));
				record.put("customerName", rs.getString("customerName"));
				record.put("orderAddress", rs.getString("orderAddress"));
				record.put("userEmail", rs.getString("userEmail"));
				record.put("product", rs.getString("product"));
				record.put("orderId", rs.getInt("orderId"));
				record.put("pincode", rs.getInt("pincode"));
				array.add(record);
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("products", array);
			System.out.println("arrayjkrsfgkspgki");
			System.out.println(array);
			
			sql = "delete from cart where email ='"+email+"';";
			PreparedStatement pstm = con.prepareStatement(sql);
			int x = pstm.executeUpdate();
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
