package shareDiary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import shareDiary.db.DBConnection;
import shareDiary.util.Util;

public class CommentDAO {
	//싱글턴
	private CommentDAO() { 
		
	}
	private static CommentDAO instance = new CommentDAO();

	public static CommentDAO getInstance() {
		return instance;
	}
	
	//댓글쓰기
	public int commentInput(HashMap<String, Object> map) {
		int result = 0;
		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO boardcomment (bno, no, bccontent) VALUES (?, (SELECT no FROM login WHERE id=?), ?)";
	    try {
	    	pstmt = conn.prepareStatement(sql);
	    	pstmt.setInt(1, Util.str2Int((String) map.get("bno")));
	    	//pstmt.setString(2, "queen"); //0810샛별
	    	pstmt.setString(2, (String) map.get("id")); //id세션 받으면 주석해제
	    	pstmt.setString(3, (String) map.get("comment"));
	    	result = pstmt.executeUpdate();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         Util.closeAll(null, pstmt, conn);
	      }
	      return result;
	   }

	//댓글 삭제하기
	public int commentDelete(int bcno, int bno) {
		int result = 0;
		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM boardcomment WHERE bcno=? AND bno=?";
		   
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bcno);
			pstmt.setInt(2, bno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(null, pstmt, conn);
		}
		return result;
		}
	
	//댓글 수정하기
	public int commentModify(HashMap<String, Object> dto) {
		int result = 0;
		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		String sql = "UPDATE boardcomment SET bccontent=? WHERE bcno=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String) dto.get("content"));
			pstmt.setInt(2, Util.str2Int((String) dto.get("bcno")));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(null, pstmt, conn);
		}
		return result;
	}
	
	//댓글 좋아요 수 올리기
	public int likeUp(HashMap<String, Object> dto) {
		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		String sql = "UPDATE boardcomment SET bclike=bclike+1 WHERE bcno=? AND bno=?";
		int count = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Util.str2Int((String) dto.get("bcno")));
			pstmt.setInt(2, Util.str2Int((String) dto.get("bno")));
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(null, pstmt, conn);
		}
		return count;
	   }
}
