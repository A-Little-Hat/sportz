package com;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("unused")
public class GetValuesFromDB {
		
	String sql;
	
	public GetValuesFromDB(String sql) {
		
		this.sql = sql;
	}
	
	
	
	public ResultSet getValues() throws SQLException, ClassNotFoundException {
		
		Connection con;
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportz", "root", "soumya@143");
		java.sql.Statement stmt = con.createStatement();
		ResultSet rs = ((java.sql.Statement) stmt).executeQuery(sql);
		return rs;
		
	}
}