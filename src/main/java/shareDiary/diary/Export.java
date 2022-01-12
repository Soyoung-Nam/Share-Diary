package shareDiary.diary;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Redirect;

import shareDiary.util.Util;

/**
 * Servlet implementation class Export
 */
@WebServlet("/Export")
public class Export extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Export() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//sql�뿉�꽌 �뙆�씪濡� �궡蹂대깄�땲�떎.
		Connection con = DBConnection.dbConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT dDate, dTitle, dFeel, dWeather, dContent FROM diary WHERE id= ?  INTO OUTFILE ? fields terminated by '\n';";
		HttpSession session = request.getSession();
		String  id = (String) session.getAttribute("id");

		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			id = id+".txt";
			//String route = "/var/lib/mysql/export/"+id;
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(rs, pstmt, con);
			System.out.println(rs);
		}
		
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//�궡蹂대궦 �뙆�씪�쓣 �꽌踰꾨줈 蹂듭궗�빀�땲�떎.
		File sourceFile = new File("/var/lib/mysql/practice/"+id);
		File newFile = new File("var/lib/tomcat9/shareDiary/export/" + id +".txt");
		
		Files.copy(sourceFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		
		sourceFile.delete();
		
		
		//蹂듭궗�븳 �뙆�씪�쓽 留곹겕瑜� �깮�꽦�빀�땲�떎. 
		String exportTarget = "./export/"+id ;

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String data = "<html>";
		data += "<body>";
		data+= "\r\n"
				+ "<div id=\"banner\"><c:import url=\"banner.jsp\"/></div>\r\n"
				+ "\r\n"
				+ "<div id=\"article\">\r\n"
				+ "\r\n"
				+ "<div id=\"menu\"><c:import url=\"menu.jsp\"/></div>\r\n"
				+ "<div id = \"cotentbox\">";
		data += "<a href= '" + exportTarget	+"' download>�씪湲곗옣 蹂닿��븯湲�</a>";
		data += "<a href='1.239.16.47:8080/shareDiary/diaryView'>紐⑸줉�쑝濡� �룎�븘媛�湲�</a> </div></div>";
		data += "</body>";
		data += "</html>";
		out.print(data);
		
		
		
	}

}
