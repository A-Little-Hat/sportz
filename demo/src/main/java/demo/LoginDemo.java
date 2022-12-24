package demo;

import java.io.IOException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.mysql.cj.xdevapi.Statement;
import org.json.simple.*;

//import demo.DatabaseConnection;

@WebServlet("/LoginDemo")
public class LoginDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			Connection con;
		 	Class.forName("com.mysql.cj.jdbc.Driver");
		 	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","soumya@143");
//            PreparedStatement st = con.prepareStatement("select * from user");
//            st.execute();
//            System.out.print(st);
		 	java.sql.Statement stmt = con.createStatement();
			String sql = "select * from user;";
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(sql);
//			System.out.println(rs);
			// Loop through the result set to
			// retrieve the individual data items.
			JSONArray array = new JSONArray();
			while (rs.next()) {
				JSONObject record = new JSONObject();
				   record.put("name", rs.getString("uname"));
				   record.put("password", rs.getString("psd"));
				   array.add(record);
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("Users_data", array);
			System.out.println(jsonObject);
		
		} catch (Exception e) {
			System.out.print(e);
		}
	}

//		RequestDispatcher rd =  request.getRequestDispatcher("login.html");
//		rd.forward(request, response);
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("uname");
		String psd = request.getParameter("psw");
//		
		
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
//		doGet(request, response);
	}

}
