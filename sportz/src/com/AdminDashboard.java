package com;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@SuppressWarnings("serial")
@WebServlet("/AdminDashboard")
public class AdminDashboard extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		
		int flag = 0;
		Cookie cookie[] = request.getCookies();
		if(cookie.length != 0) {
			System.out.println("okie");
			for (Cookie cookie2 : cookie) {
				if(cookie2.getName().equals("admin") && cookie2.getValue().equals("true")) {
					System.out.println("setcookie23");
					flag = 1;
					request.getRequestDispatcher("./adminDashboard.html").include(request, response);
				}
			}
		}
		if(flag == 0){
			request.getRequestDispatcher("./Admin").forward(request, response);
		}
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}