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

import org.json.simple.*;

@WebServlet("/Products")
public class Products extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("products.html");
				rd.include(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("hello");
		
		
		
		try {
			Connection con;
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportz", "root", "soumya@143");
			java.sql.Statement stmt = con.createStatement();
			String sql = "select * from product;";
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(sql);
			JSONArray array = new JSONArray();
			while (rs.next()) {
				JSONObject record = new JSONObject();
				record.put("product_name", rs.getString("product_name"));
				record.put("product_id", rs.getString("product_description"));
				record.put("product_category", rs.getString("product_category"));
				record.put("product_price", rs.getInt("product_price"));
				record.put("count", rs.getInt("count"));
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
		
		
//		String name = request.getParameter("uname");
//		String psd = request.getParameter("psw");
//		try {
//			Connection con;
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "soumya@143");
//			PreparedStatement st = con.prepareStatement("insert into user values(?, ?)");
//			st.setString(1, name);
//			st.setString(2, psd);
//			st.executeUpdate();
//			st.close();
//			con.close();
//			PrintWriter out = response.getWriter();
//			out.println("<html><body><b>Successfully Inserted"
//					+ "</b></body></html>");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
