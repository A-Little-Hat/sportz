package com;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


@WebServlet("/Rugby")
public class Rugby extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("rugby.html");
		rd.include(request, response);
	
	}


	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			GetValuesFromDB rs = new GetValuesFromDB("select * from product where product_category='rugby';");
			JSONArray array = new JSONArray();
			while (rs.getValues().next()) {
				JSONObject record = new JSONObject();
				record.put("product_name", rs.getValues().getString("product_name"));
				record.put("product_description", rs.getValues().getString("product_description"));
				record.put("product_category", rs.getValues().getString("product_category"));
				record.put("product_price", rs.getValues().getInt("product_price"));
				record.put("count", rs.getValues().getInt("count"));
				record.put("product_id", rs.getValues().getInt("product_id"));
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
		
	}
	}


