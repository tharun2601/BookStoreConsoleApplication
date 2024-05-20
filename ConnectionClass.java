import java.sql.*;

public class ConnectionClass {
	
	
	String url = "jdbc:mysql://localhost:3306/BookApplicationDB";
	String username = "root";
	String password = "Th@run26()";
	
	public Connection getConn() {
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			return conn;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
