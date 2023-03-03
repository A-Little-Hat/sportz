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

/**
 * Servlet implementation class SendOrderToUserDashBoard
 */
@WebServlet("/SendOrderToUserDashBoard")
public class SendOrderToUserDashBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendOrderToUserDashBoard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			@SuppressWarnings("unused")
			java.sql.Statement stmt = con.createStatement();
			String sql = "select orderId,customerName,orderPhoneNumber,orderAddress,userEmail,pincode,product from userorder where userEmail='"+email+"';";
			PreparedStatement pr=con.prepareStatement(sql);
			ResultSet rs =pr.executeQuery();
			System.out.println(rs);
			JSONArray array = new JSONArray();
			while (rs.next()) {
				JSONObject record = new JSONObject();
				record.put("orderId", rs.getInt("orderId"));
				record.put("customerName", rs.getString("customerName"));
				record.put("orderPhoneNumber", rs.getString("orderPhoneNumber"));
				record.put("orderAddress", rs.getString("orderAddress"));
				record.put("userEmail", rs.getString("userEmail"));
				record.put("pincode", rs.getString("pincode"));
				record.put("product", rs.getString("product"));
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
