package shareDiary.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection dbConn() {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://localhost:3306/practice";
			//DB서버 수정입니다
			String id = "guest";
			String pw = "0123";
			conn = DriverManager.getConnection(url,id,pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
