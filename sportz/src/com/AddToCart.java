package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	@SuppressWarnings({ "resource" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String email = "";
			String product_name = null, product_image = null;
			boolean flag = false;
			int price = 0;
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
				String sql = "SELECT quantity from cart where product_id = " + id + " and email = '" + email + "';";
				PreparedStatement pr = con.prepareStatement(sql);
				ResultSet rs = pr.executeQuery();
				int quantity = 0;
				while (rs.next()) {
					quantity = rs.getInt("quantity");
				}

				if (quantity > 0) {

					System.out.println("else part");

					quantity = quantity + 1;
					sql = "UPDATE cart SET quantity = " + quantity + " WHERE product_id = " + id + " AND email = '"
							+ email + "';";
					pr = con.prepareStatement(sql);
					pr.executeUpdate();

				} else {

					sql = "SELECT product_name,product_price,product_image from product where product_id = " + id + ";";
					pr = con.prepareStatement(sql);
					ResultSet rs1 = pr.executeQuery();
					while (rs1.next()) {
						product_name = rs1.getString("product_name");
						product_image = rs1.getString("product_image");
						price = rs1.getInt("product_price");
					}
					sql = "INSERT INTO cart values ('" + email + "'," + id + ",'" + product_name + "',1," + price + ",'"
							+ product_image + "');";
					pr = con.prepareStatement(sql);
					pr.executeUpdate();
				}
				response.setStatus(201);
			}
		} catch (Exception e) {
			System.out.print(e);
		}

	}
}
