package shareDiary.diary;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import shareDiary.util.*;
import shareDiary.dao.LogDAO;

/**
 * Servlet implementation class DiaryWriting
 */
@WebServlet("/DiaryWriting")
public class DiaryWriting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DiaryWriting() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("./diaryWriting.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		request.setCharacterEncoding("UTF-8");
		String savePath = request.getServletContext().getRealPath("");	
		savePath = savePath + "";
		int maxSize = 1024 * 1024 * 10;//10MB
		MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy() );

		HttpSession session = request.getSession();
		String  id =(String) session.getAttribute("id");
		
		if ((multi.getParameter("title")).length() == 0 
				|| (multi.getParameter("content")).length() == 0) {
			response.sendRedirect("./error?code=�젣紐�, �궡�슜 誘몄엯�젰");

		

		} else {
			String date = multi.getParameter("date");
			String title = multi.getParameter("title");
			String allow = multi.getParameter("allow");
			String rawcontent = multi.getParameter("content");
			String regex = "\\<.*?\\>";//�꽌癒몃끂�듃�뿉�꽌 �깭洹멸퉴吏� ���옣�릺湲� �뻹臾몄뿉 �깭洹몃뱾�쓣 �뾾�븷�뒗 肄붾뱶 異붽�.
			String content = rawcontent.replaceAll(regex, "");
			
			String weather = multi.getParameter("weather");
			String feel = multi.getParameter("feel");
			

			
			
			String realFile = null;
			
			if(multi.getOriginalFileName("file") != null) {			
				realFile = multi.getFilesystemName("file");
			}
			
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("date", date);
			map.put("title", title);
			map.put("allow", allow);
			map.put("content", content);
			map.put("weather", weather);
			map.put("feel", feel);
			map.put("id", id);
			map.put("ip", Util.getIP(request));
			map.put("file", realFile);
			
			
			DiaryDAO dao = DiaryDAO.getInstance();
			int result = dao.write(map);
			
			
			
			
			
			HashMap<String, Object> log = new HashMap<String, Object>();
		      log.put("ip", Util.getIP(request));
		      log.put("id", request.getParameter("id"));
		      log.put("target", "diaryWritng");
		      log.put("etc", request.getHeader("user-agent"));
		      LogDAO.getInstance().insertLog(log);
			
			
			
			
			
			response.sendRedirect("diaryView.jsp");

			
			
		}
		
		
		
		
		
	}
}
