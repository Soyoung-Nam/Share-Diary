package shareDiary.diary;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection dbConn() {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://1.239.16.47:3306/practice";
			String id = "tesius";
			String pw = "tesis1251";
			conn = DriverManager.getConnection(url,id,pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
