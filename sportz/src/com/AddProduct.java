package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("hello madarboard");

		response.sendRedirect("./AdminDashboard");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String description = request.getParameter("pdesc");
		String category = request.getParameter("category");
		String imgurl = request.getParameter("purl");
		String name = request.getParameter("pname");
		String pprice = request.getParameter("pprice");
		String pcount = request.getParameter("pcount");

		if (description == "" || category ==  ""|| pprice == "" || imgurl == ""
				|| name == "" || pcount == "") {
			System.out.println("heloo");
			response.sendRedirect("./AdminDashboard");

		}else {
			
			int price = Integer.parseInt(pprice);
			int count = Integer.parseInt(pcount);
			try {
				Connection con;
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportz", "root", "soumya@143");
				String sql = "insert into product values (null," + "'" + name + "','" + description + "','" + category
						+ "'," + price + "," + count + ",'" + imgurl + "');";
				PreparedStatement pr = con.prepareStatement(sql);
				int x = pr.executeUpdate();
				response.sendRedirect("./AdminDashboard");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
