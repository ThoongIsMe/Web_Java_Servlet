package utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

import utils.JDBCUtils;

public class JDBCUtils {
	static Connection conn = null;

	public static Connection getConnection() {

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			String url = "jdbc:mysql://localhost:3306/qlsv?useSSL=false";
		    String username = "root";
		    String password = "123456";

			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Connection Error " + e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeConnection()

	{
		try {
			if (conn != null) {
				System.out.println("Close Connection!");
				conn.close();
			}

		} catch (SQLException e) {
			System.out.println("Connection Error...");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException {

		JDBCUtils.getConnection();
	}

	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

	public static Date getSQLDate(LocalDate date) {
		return java.sql.Date.valueOf(date);
	}

	public static LocalDate getUtilDate(Date sqlDate) {
		return sqlDate.toLocalDate();
	}

}
