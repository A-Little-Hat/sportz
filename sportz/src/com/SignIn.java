package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignIn")
public class SignIn extends HttpServlet {

  private static final long serialVersionUID = 1L;

  public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
    // Static getInstance method is called with hashing SHA
    MessageDigest md = MessageDigest.getInstance("SHA-256");

    // digest() method called
    // to calculate message digest of an input
    // and return array of byte
    return md.digest(input.getBytes(StandardCharsets.UTF_8));
  }

  public static String toHexString(byte[] hash) {
    // Convert byte array into signum representation
    BigInteger number = new BigInteger(1, hash);

    // Convert message digest into hex value
    StringBuilder hexString = new StringBuilder(number.toString(16));

    // Pad with leading zeros
    while (hexString.length() < 64) {
      hexString.insert(0, '0');
    }

    return hexString.toString();
  }
  public void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
//		System.out.println("/signUp.html");
		response.setContentType("text/html");
		request.getRequestDispatcher("signIn.html").include(request, response);
	}

  protected void doPost(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws ServletException, IOException {
    // TODO Auto-generated method stub
    response.setContentType("text/html");

    try {
      String email = request.getParameter("email");

      String psd = request.getParameter("password");
      String password = toHexString(getSHA(psd));

      String emailFromDatabase = null;
      Connection con;
      Class.forName("com.mysql.cj.jdbc.Driver");
      con =
        DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/sportz",
          "root",
          "soumya@143"
        );
      PreparedStatement stmt = null;
      String st =
        "select email from customer where email ='" +
        email +
        "' and password = '" +
        password +
        "'";
      stmt = con.prepareStatement(st);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        emailFromDatabase = rs.getString("email");
        if (emailFromDatabase.equals(email)) {
          Cookie cookie1 = new Cookie("email", email);
          cookie1.setMaxAge(60000);
          response.addCookie(cookie1);
          response.sendRedirect("./");
        }
      } else {
        response.sendRedirect("./SignIn");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
