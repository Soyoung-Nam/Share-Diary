package shareDiary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import shareDiary.util.Util;

import shareDiary.db.DBConnection;

public class BoardDAO {
	// 싱글톤 기법으로 하겠습니다!! -샛별
	private BoardDAO() {
	}

	private static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
		return instance;
	}

	// 게시판 글 불러오기
	public ArrayList<HashMap<String, Object>> boardList(int page, int totalNum) {
		ArrayList<HashMap<String, Object>> list = null;
		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM boardview LIMIT ?, ?;";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, page);
			pstmt.setInt(2, totalNum);
			rs = pstmt.executeQuery();

			list = new ArrayList<HashMap<String, Object>>();

			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("totalcount", rs.getInt("totalcount"));
				map.put("bno", rs.getInt("bno"));
				map.put("btitle", rs.getString("btitle"));
				map.put("bthumbnail", rs.getString("bthumbnail"));
				// 썸네일 추가했습니다. -샛별
				map.put("bcount", rs.getInt("bcount"));
				map.put("bdate", rs.getString("bdate"));
				map.put("category", rs.getString("category"));
				map.put("commentcount", rs.getInt("commentcount"));
				map.put("name", rs.getString("name"));
				map.put("blike", rs.getInt("blike"));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(rs, pstmt, conn);
		}

		return list;
	}

	// 게시판 글쓰기
	public int boardWrite(HashMap<String, Object> map) {
		int result = 0;
		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO board (category, btitle, bcontent, bip, bfilename, no, bthumbnail) VALUES (?, ?, ?, ?, ?, (SELECT no FROM login WHERE id=?), ?) ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String) map.get("category"));
			pstmt.setString(2, (String) map.get("btitle"));
			pstmt.setString(3, (String) map.get("bcontent"));
			pstmt.setString(4, (String) map.get("bip"));
			pstmt.setString(5, (String) map.get("bfilename"));
			pstmt.setString(6, (String) map.get("id"));
//			pstmt.setString(6, "queen"); //0810샛별
			pstmt.setString(7, (String) map.get("bthumbnail"));

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(null, pstmt, conn);
		}
		return result;
	}

	// 게시글 count 올리기 -- detail SELECT 구문에서 try문 가장 아래쪽에 countUp(bno); 해주시면 됩니다! -샛별
	public void countUp(int bno) {
		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET bcount=bcount+1 WHERE bno=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(null, pstmt, conn);
		}

	}

	// 카테고리 드롭다운 리스트
	public ArrayList<String> cateList() {
		ArrayList<String> cateList = null;
		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT DISTINCT category FROM boardview";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs != null) {
				cateList = new ArrayList<String>();
				while (rs.next()) {
					cateList.add(rs.getString("category"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(rs, pstmt, conn);
		}
		return cateList;
	}

	// 카테고리별 모아보기
	public ArrayList<HashMap<String, Object>> selectCate(String category, int page) {
		ArrayList<HashMap<String, Object>> list = null;
		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM boardview WHERE category=? limit ?, 10";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setInt(2, page);
			rs = pstmt.executeQuery();

			if (rs != null) {
				list = new ArrayList<HashMap<String, Object>>();
				while (rs.next()) {
					HashMap<String, Object> map = new HashMap<String, Object>();

					map.put("totalcount", rs.getInt("totalcount"));
					map.put("bno", rs.getInt("bno"));
					map.put("btitle", rs.getString("btitle"));
					map.put("bthumbnail", rs.getString("bthumbnail"));
					map.put("bcount", rs.getInt("bcount"));
					map.put("bdate", rs.getString("bdate"));
					map.put("category", rs.getString("category"));
					map.put("commentcount", rs.getInt("commentcount"));
					map.put("name", rs.getString("name"));
					map.put("blike", rs.getInt("blike"));
					list.add(map);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(rs, pstmt, conn);
		}

		return list;
	}

	// 게시글 검색
	public ArrayList<HashMap<String, Object>> search(String searchValue, String searchText, int page) {
		ArrayList<HashMap<String, Object>> list = null;
		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT (SELECT count(*) FROM board WHERE " + searchValue
				+ " LIKE CONCAT('%', ?, '%')) AS totalcount, "
				+ "category, commentcount, bno, btitle, bdate, name, bthumbnail, bcount, blike FROM boardview WHERE "
				+ searchValue + " LIKE CONCAT('%', ?, '%') limit ?, 10";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchText);
			pstmt.setString(2, searchText);
			pstmt.setInt(3, page);

			rs = pstmt.executeQuery();

			if (rs != null) {
				list = new ArrayList<HashMap<String, Object>>();
				while (rs.next()) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("totalcount", rs.getInt("totalcount"));
					map.put("bno", rs.getInt("bno"));
					map.put("btitle", rs.getString("btitle"));
					map.put("bthumbnail", rs.getString("bthumbnail"));
					map.put("bcount", rs.getInt("bcount"));
					map.put("bdate", rs.getString("bdate"));
					map.put("category", rs.getString("category"));
					map.put("commentcount", rs.getInt("commentcount"));
					map.put("name", rs.getString("name"));
					map.put("blike", rs.getInt("blike"));
					list.add(map);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(rs, pstmt, conn);
		}
		return list;
	}

	// 게시글 상세보기 불러오기
	public HashMap<String, Object> detail(int bno) {
		HashMap<String, Object> dto = null;
		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM boardview WHERE bno=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new HashMap<String, Object>();
				dto.put("category", rs.getString("category"));
				dto.put("commentcount", rs.getInt("commentcount"));
				dto.put("bno", rs.getInt("bno"));
				dto.put("btitle", rs.getString("btitle"));
				dto.put("bcontent", rs.getString("bcontent"));
				dto.put("bdate", rs.getString("bdate"));
				dto.put("bcount", rs.getInt("bcount"));
				dto.put("bip", rs.getString("bip"));
				dto.put("no", rs.getInt("no"));
				dto.put("id", rs.getString("id"));
				dto.put("name", rs.getString("name"));
				dto.put("bfilename", rs.getString("bfilename"));
				dto.put("bthumbnail", rs.getString("bthumbnail"));
				dto.put("blike", rs.getString("blike"));
				dto.put("bhate", rs.getString("bhate"));
			}
			countUp(bno);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(rs, pstmt, conn);
		}
		return dto;
	}

	// 게시판 댓글 불러오기
	public ArrayList<HashMap<String, Object>> commentList(int bno) {
		ArrayList<HashMap<String, Object>> commentList = null;
		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM commentview WHERE bno=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			commentList = new ArrayList<HashMap<String, Object>>();

			while (rs.next()) {
				HashMap<String, Object> dto = new HashMap<String, Object>();
				dto.put("bcno", rs.getInt("bcno"));
				dto.put("bno", rs.getInt("bno"));
				dto.put("no", rs.getInt("no"));
				dto.put("id", rs.getString("id"));
				dto.put("name", rs.getString("name"));
				dto.put("bccontent", rs.getString("bccontent"));
				dto.put("bcdate", rs.getString("bcdate"));
				dto.put("bcip", rs.getString("bcip"));
				dto.put("bclike", rs.getString("bclike"));
				commentList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(rs, pstmt, conn);
		}
		return commentList;
	}

	// 게시물 좋아요 수 올리기
	public int likeUp1(HashMap<String, Object> dto, String value) {
		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET " + value + "=" + value + " +1 WHERE bno=?";
		int count = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Util.str2Int((String) dto.get("bno")));
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(null, pstmt, conn);
		}
		return count;
	}

	// 게시물 삭제하기
	public int delete(int bno) {
		int result = 0;
		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM board WHERE bno=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(null, pstmt, conn);
		}
		return result;
	}

	// 게시물 수정하기
	public int modify(HashMap<String, Object> map, int bno) {
		int result = 0;
		Connection conn = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET category=?, btitle=?, bcontent=?, bfilename=?, bthumbnail=? WHERE bno=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String) map.get("category"));
			pstmt.setString(2, (String) map.get("btitle"));
			pstmt.setString(3, (String) map.get("bcontent"));
			pstmt.setString(4, (String) map.get("bfilename"));
			pstmt.setString(5, (String) map.get("bthumbnail"));
			pstmt.setInt(6, bno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(null, pstmt, conn);
		}
		return result;
	}

}
