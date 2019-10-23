package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	public static Connection getConnect() {
		Connection conn = null;
		String user = "hr";
		String passwd = "hr";
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 오라클 url 연결
		String driver = "oracle.jdbc.driver.OracleDriver"; // 오라클에서 제공하는 jdbc드라이버 중 오라클 드라이버를 사용하는 구문

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
