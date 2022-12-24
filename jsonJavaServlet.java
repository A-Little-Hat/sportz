package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.mysql.cj.xdevapi.Statement;
import org.json.simple.*;

import redis.clients.jedis.Jedis;


//import demo.DatabaseConnection;

@WebServlet("/LoginDemo")
public class LoginDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("hit");

		try {
			Connection con;
		 	Class.forName("com.mysql.cj.jdbc.Driver");
		 	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","soumya@143");
		 	java.sql.Statement stmt = con.createStatement();
			String sql = "select * from user;";
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(sql);
			JSONArray array = new JSONArray();
			while (rs.next()) {
				JSONObject record = new JSONObject();
				   record.put("name", rs.getString("uname"));
				   record.put("password", rs.getString("psd"));
				   array.add(record);
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("Users_data", array);
			System.out.println(array);
			Jedis jedis = new Jedis();
			try {
				jedis.lpush("userData", "jsonObject.toString()");
			} catch (Exception e) {
				
			}
			response.addHeader("Access-Control-Allow-Origin", "*");
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
//		    request.getRequestDispatcher("res.json").include(request, response);
		    ServletOutputStream out = response.getOutputStream();
			out.print(jsonObject.toJSONString());
		} catch (Exception e) {
			System.out.print(e);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("uname");
		String psd = request.getParameter("psw");
		 try {
			 	Connection con;
			 	Class.forName("com.mysql.cj.jdbc.Driver");
			 	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","soumya@143");
	            PreparedStatement st = con.prepareStatement("insert into user values(?, ?)");
	            st.setString(1, name);
	            st.setString(2, psd);
	            st.executeUpdate();
	            st.close();
	            con.close();
	            PrintWriter out = response.getWriter();
	            out.println("<html><body><b>Successfully Inserted"
	                        + "</b></body></html>");
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
