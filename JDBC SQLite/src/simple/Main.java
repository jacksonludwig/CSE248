package simple;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:StudentDB.sqlite");
		Statement statement = conn.createStatement();
		statement.executeUpdate("drop table if exists Student");
		statement.executeUpdate("create table student (FirstName VARCHAR(50), LastName VARCHAR(50))");
		statement.executeUpdate("insert into Student (FirstName, LastName) Values ('Adam', 'Smith')");
		statement.executeUpdate("insert into Student (FirstName, LastName) Values ('Bill', 'Smith')");
		ResultSet rs = statement.executeQuery("Select * from Student");
		while(rs.next()) {
			System.out.println(rs.getString("FirstName"));
			System.out.println(rs.getString("LastName"));
		}
		conn.close();
		
	}

}
