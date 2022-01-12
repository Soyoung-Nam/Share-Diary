package shareDiary.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import shareDiary.db.DBConnection;
import shareDiary.dto.LoginDTO;
import shareDiary.util.Util;

public class LoginDAO {
	private LoginDAO() { }
	private static LoginDAO instance = new LoginDAO();
	public static LoginDAO getInstance() {
		return instance;
	}
	
	public int join(LoginDTO dto) {
		int joinus = 0;
		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO login (id, name, pw, email, birthdate) "
				+ "VALUES (?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getPw());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getDate());
			
			joinus = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(null, pstmt, conn);
		}
		return joinus;
	}
	
	public HashMap<String, Object> login(String id, String pw) {
		HashMap<String, Object> login = null;
		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT id, name, grade FROM login WHERE pw=? AND id=? AND grade > 2";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				login = new HashMap<String, Object>();
				login.put("id", rs.getString("id"));
				login.put("name", rs.getString("name"));
				login.put("grade", rs.getInt("grade"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(rs, pstmt, conn);
		}
		return login;
	}

	
	public String idpw(String email) {
		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		String result = null;
		ResultSet rs = null;
		String sql = "SELECT id FROM login WHERE email=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(rs, pstmt, conn);
		}
		return result;
	}
	
	public int passwordReset(LoginDTO dto) {
		int result = 0;
		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		String sql = "UPDATE login SET pw=? WHERE id=? AND name=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getName());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(null, pstmt, conn);
		}
		return result;
	}
	
	public LoginDTO myinfo(String id, String pw) {
		LoginDTO dto = null;
		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM login WHERE id=? AND pw=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto = LoginDTO.getInstance();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setEmail(rs.getString("email"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(rs, pstmt, conn);
		}
		return dto;
	}
	
	public int idCheck(String id) {
		int result = 1;
		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM login WHERE id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(rs, pstmt, conn);
		}
		return result;
	}
}
