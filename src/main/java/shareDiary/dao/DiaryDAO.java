package shareDiary.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import shareDiary.db.DBConnection;
import shareDiary.util.Util;

public class DiaryDAO {
	private DiaryDAO() {
		
	}
	
	private static DiaryDAO instance = new DiaryDAO();
	
	public static DiaryDAO getInstance() {
		return instance;
	}
	
	public ArrayList<HashMap<String, Object>> DiaryList(HttpServletRequest request) throws SQLException {
		ArrayList<HashMap<String, Object>> DiaryList = null;
	
//		
//		HttpSession session = request.getSession(); 
//		String id = (String)session.getAttribute("id");
		String id = "tesius";
		
		Connection con = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT dNo, dDate, dTitle, dFeel, dWeather, dFileName FROM diary WHERE id=? ORDER BY dNo DESC ";
	
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs != null) {
				DiaryList = new ArrayList<HashMap<String, Object>>();
				while (rs.next()) {

					HashMap<String, Object> map = new HashMap<String, Object>();

					map.put("no", rs.getInt("dNo"));
					map.put("date", rs.getDate("dDate"));
					map.put("title", rs.getString("dTitle"));
					map.put("feel", rs.getString("dFeel"));
					map.put("weather", rs.getString("dWeather"));
					map.put("FileName", rs.getString("dFileName"));
					// boardList.add(dto)
					DiaryList.add(map);
				}
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(rs, pstmt, con);
		}
		return DiaryList;
		
	}
	
	
	public ArrayList<HashMap<String, Object>> SelectedDiaryList(HttpServletRequest request , String date1, String date2) throws SQLException {
		ArrayList<HashMap<String, Object>> DiaryList = null;
		
		
//		HttpSession session = request.getSession(); 
//		String id = (String)session.getAttribute("id");

		String id = "tesius";
		
		Connection con = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT  dNo, dDate, dTitle, dFeel, dWeather, dFileName FROM diary WHERE id=? AND DATE(dDate) BETWEEN ? AND ? ORDER BY dNo DESC ";
		Date dateStart = Date.valueOf(date1);
		Date dateEnd = Date.valueOf(date2);
	
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setDate(2, dateStart);
			pstmt.setDate(3,  dateEnd);
			rs = pstmt.executeQuery();

			if (rs != null) {
				DiaryList = new ArrayList<HashMap<String, Object>>();
				while (rs.next()) {

					HashMap<String, Object> map = new HashMap<String, Object>();

					map.put("no", rs.getInt("dNo"));
					map.put("date", rs.getDate("dDate"));
					map.put("title", rs.getString("dTitle"));
					map.put("feel", rs.getString("dFeel"));
					map.put("weather", rs.getString("dWeather"));
					map.put("FileName", rs.getString("dFileName"));
					// boardList.add(dto)
					DiaryList.add(map);
				}
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(rs, pstmt, con);
		}
		return DiaryList;
		
	}
	
	
	
	public ArrayList<HashMap<String, Object>> openDiaryList(HttpServletRequest request) throws SQLException {
		ArrayList<HashMap<String, Object>> DiaryList = null;
	
		
		
		Connection con = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT dNo, dDate, dTitle, dFeel, dWeather, dFileName FROM diary WHERE dAllow = ? ORDER BY dNo DESC ";
	
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "open");
			rs = pstmt.executeQuery();

			if (rs != null) {
				DiaryList = new ArrayList<HashMap<String, Object>>();
				while (rs.next()) {

					HashMap<String, Object> map = new HashMap<String, Object>();

					map.put("no", rs.getInt("dNo"));
					map.put("date", rs.getDate("dDate"));
					map.put("title", rs.getString("dTitle"));
					map.put("feel", rs.getString("dFeel"));
					map.put("weather", rs.getString("dWeather"));
					map.put("FileName", rs.getString("dFileName"));
					// boardList.add(dto)
					DiaryList.add(map);
				}
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(rs, pstmt, con);
		}
		return DiaryList;
		
	}
	
	
	
	
	
	public ArrayList<HashMap<String, Object>> exchangeDiaryList(HttpServletRequest request) throws SQLException {
		ArrayList<HashMap<String, Object>> DiaryList = null;
	
//		
//		HttpSession session = request.getSession(); 
//		String id = (String)session.getAttribute("id");
		String id = "tesius";
		
		Connection con = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT eNo, eDate, eTitle, eFeel, eWeather, eFileName, recieve FROM exchangeDiary WHERE send=? OR recieve = ? ORDER BY eNo DESC ";
	
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();

			if (rs != null) {
				DiaryList = new ArrayList<HashMap<String, Object>>();
				while (rs.next()) {

					HashMap<String, Object> map = new HashMap<String, Object>();

					map.put("no", rs.getInt("eNo"));
					map.put("date", rs.getDate("eDate"));
					map.put("title", rs.getString("eTitle"));
					map.put("feel", rs.getString("eFeel"));
					map.put("weather", rs.getString("eWeather"));
					map.put("FileName", rs.getString("eFileName"));
					map.put("recieve", rs.getString("recieve"));
					// boardList.add(dto)
					DiaryList.add(map);
				}
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(rs, pstmt, con);
		}
		return DiaryList;
		
	}
	
	
	
	
	
	
	public HashMap<String, Object> detail(int dNo) {
		HashMap<String, Object> dto = null;
		Connection con = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM diary WHERE dNo=?";

		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dNo);
			rs = pstmt.executeQuery();
			
			
			if (rs.next()) {
				dto = new HashMap<String, Object>();
				dto.put("no", rs.getInt("dNo"));
				dto.put("title", rs.getString("dTitle"));
				dto.put("date", rs.getString("dDate"));
				dto.put("content", rs.getString("dContent"));
				dto.put("feel", rs.getString("dFeel"));
				dto.put("weather", rs.getString("dWeather"));
				dto.put("file", rs.getString("dFileName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(rs, pstmt, con);
		}
		return dto;
	}
	
	
	
	
	public int write(HashMap<String, Object> map) {
		int result = 0;

		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO diary (dDate, dAllow, dTitle, dContent, id, dFeel, dWeather, ip, dFileName) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";


		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1,  (Date) map.get("D"));
			pstmt.setString(2, (String)map.get("allow"));
			pstmt.setString(3, (String) map.get("title"));
			pstmt.setString(4, (String) map.get("content"));
			pstmt.setString(5, (String) map.get("id"));
			pstmt.setString(6, (String) map.get("feel"));
			pstmt.setString(7, (String) map.get("weather"));
			pstmt.setString(8, (String) map.get("ip"));
			pstmt.setString(9, (String) map.get("file"));
			
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(null, pstmt, conn);
		}
		return result;
	}

	
	
	
	
	
	public int delete(int dNo, String id) {
		int result = 0;
		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM diary WHERE dNo=? " + "AND id=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dNo);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
			System.out.println(result + " !!!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	
				

	
	
	
	
	
	
	
	
	
	
	
}
