package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings("serial")
@WebServlet("/Register")
public class Register extends HttpServlet {
	 public static byte[] getSHA(String input) throws NoSuchAlgorithmException
	    {
	        MessageDigest md = MessageDigest.getInstance("SHA-256");
	        return md.digest(input.getBytes(StandardCharsets.UTF_8));
	    }

	    public static String toHexString(byte[] hash)
	    {
	        BigInteger number = new BigInteger(1, hash);
	        StringBuilder hexString = new StringBuilder(number.toString(16));
	        while (hexString.length() < 64)
	        {
	            hexString.insert(0, '0');
	        }
	 
	        return hexString.toString();
	    }
	public void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		System.out.println("/signUp.html");
		response.setContentType("text/html");
		request.getRequestDispatcher("signUp.html").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
//		ServletOutputStream out = response.getOutputStream();

		try {		

			String name = request.getParameter("Name");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			String psd = request.getParameter("password");
			String password = toHexString(getSHA(psd));
			System.out.println(name+"\n" + phone+"\n"  + password+"\n" + email+"\n" + address);
			Connection con;
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportz", "root", "soumya@143");
			PreparedStatement st = con.prepareStatement("insert into customer values(?, ?, ?, ?, ?)");
			st.setString(1, name);
			st.setString(2, phone);
			st.setString(3, email);
			st.setString(4, address);
			st.setString(5, password);
			st.executeUpdate();
			st.close();
			con.close();
			
			String fileText = "<div\r\n"
					+ "      style=\"display: grid; place-items: center; width: 100%; min-height: 100vh\"\r\n"
					+ "    >\r\n"
					+ "      <div\r\n"
					+ "        style=\"\r\n"
					+ "          border-radius: 8px;\r\n"
					+ "          box-shadow: -5px 10px 30px -6px rgba(0, 0, 0, 0.75);\r\n"
					+ "          -webkit-box-shadow: -5px 10px 30px -6px rgba(0, 0, 0, 0.75);\r\n"
					+ "          -moz-box-shadow: -5px 10px 30px -6px rgba(0, 0, 0, 0.75);\r\n"
					+ "          display: grid;\r\n"
					+ "          place-items: center;\r\n"
					+ "          padding: 1.5em;\r\n"
					+ "        \"\r\n"
					+ "      >\r\n"
					+ "        <div style=\"display: grid; place-items: center; padding: 1.5em\">\r\n"
					+ "          <h5\r\n"
					+ "            style=\"\r\n"
					+ "              color: rgba(7, 6, 6, 0.877);\r\n"
					+ "              font-size: large;\r\n"
					+ "              font-weight: 900;\r\n"
					+ "              font-family: Georgia, 'Times New Roman', Times, serif;\r\n"
					+ "            \"\r\n"
					+ "          >\r\n"
					+ "            Thank you for registering.\r\n"
					+ "          </h5>\r\n"
					+ "				<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"100\" height=\"100\" viewBox=\"0 0 512 512\"><title>ionicons-v5-e</title><path d=\"M448,256c0-106-86-192-192-192S64,150,64,256s86,192,192,192S448,362,448,256Z\" style=\"fill:none;stroke:#000;stroke-miterlimit:10;stroke-width:32px\"/><polyline points=\"368 192 256.13 320 208.18 272\" style=\"fill:none;stroke:#000;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px\"/><line x1=\"191.95\" y1=\"320\" x2=\"144\" y2=\"272\" style=\"fill:none;stroke:#000;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px\"/><line x1=\"305.71\" y1=\"192\" x2=\"254.16\" y2=\"251\" style=\"fill:none;stroke:#000;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px\"/></svg>"
					+ "          <p\r\n"
					+ "            style=\"\r\n"
					+ "              margin-bottom: 1.5em;\r\n"
					+ "              line-height: 1.5em;\r\n"
					+ "              font-size: 1em;\r\n"
					+ "              color: rgba(0, 0, 0, 0.637);\r\n"
					+ "            \"\r\n"
					+ "          >\r\n"
					+ "            Hello "+ name +". Welcome to sportz.\r\n"
					+ "          </p>\r\n"
					+ "          <a\r\n"
					+ "            type=\"button\"\r\n"
					+ "            href=\"http://localhost:8080/sportz/SignIn\"\r\n"
					+ "            style=\"\r\n"
					+ "              border-radius: 10px;\r\n"
					+ "              padding-inline: 1.5em;\r\n"
					+ "              padding-block: 0.7em;\r\n"
					+ "              font-size: larger;\r\n"
					+ "              font-family: fangsong;\r\n"
					+ "              text-decoration: none;\r\n"
					+ "              color: #ffff;\r\n"
					+ "              background-color: rgb(13 13 14);\r\n"
					+ "              text-align: center;\r\n"
					+ "            \"\r\n"
					+ "            >Sign In</a\r\n"
					+ "          >\r\n"
					+ "        </div>\r\n"
					+ "      </div>\r\n"
					+ "    </div>\r\n"
					+ "";
			
//			out.println(fileText);
//			response.sendRedirect("./SignIn");
//			response.setStatus(200);
			PrintWriter pw = response.getWriter();
			pw.print(fileText);
			
		} catch (Exception e) {
			response.sendError(500);
			throw new IOException(e.getMessage());
		} 
	}

}
