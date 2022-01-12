package shareDiary.diary;


import java.sql.Date;
import java.util.HashMap;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import shareDiary.util.Util;
/**
 * Servlet implementation class ExchangeDiaryWriting
 */
@WebServlet("/ExchangediaryWriting")
public class ExchangeDiaryWriting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExchangeDiaryWriting() {
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
		
		request.setCharacterEncoding("UTF-8");
		String savePath = request.getServletContext().getRealPath("");	
		savePath = savePath + "";
		int maxSize = 1024 * 1024 * 10;//10MB
		MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy() );

//		HttpSession session = request.getSession();
//		String  id = (String) session.getAttribute("id");
		String id = "tesius";
		
		if ((multi.getParameter("title")).length() == 0 
				|| (multi.getParameter("content")).length() == 0) {
			response.sendRedirect("./edView.jsp");// 에러페이지로 


		} else {
			String date = multi.getParameter("date");
			String title = multi.getParameter("title");
			String rawcontent = multi.getParameter("content");
			String regex = "\\<.*?\\>";//서머노트에서 태그까지 저장되기 떄문에 태그들을 없애는 코드 추가.
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
			map.put("content", content);
			map.put("weather", weather);
			map.put("feel", feel);
			map.put("id", id);
			map.put("ip", Util.getIP(request));
			map.put("file", realFile);
			
			
			DiaryDAO dao = DiaryDAO.getInstance();
			int result = dao.edWrite(map);
			
			System.out.println(result);
			
			response.sendRedirect("./edView.jsp");

			
			
		}
	}

}
