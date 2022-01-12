package shareDiary.diary;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shareDiary.util.Util;

@WebServlet("/DiaryDelete")
public class DiaryDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public DiaryDelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		HttpSession session = request.getSession(); 
		String id = (String) session.getAttribute("id");

		
		if(request.getParameter("no") != null 
				&& Util.str2Int(request.getParameter("no")) != 0
				){
			int dNo = Util.str2Int(request.getParameter("no"));
			//BoardDAO dao = new BoardDAO();
			DiaryDAO dao = DiaryDAO.getInstance();
			int result = dao.delete(dNo, id);
		} else {
			response.setContentType("text/html; charset=UTF-8"); 
			PrintWriter writer = response.getWriter(); 
			writer.println("<script>alert('이 일기를 잊어버릴수 없습니다!'); location.href='"+ "./diaryView" +"';</script>"); 
			writer.close();
		}
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter writer = response.getWriter(); 
		writer.println("<script>alert('좀 전의 일기가 기억나지 않게 되었습니다...'); location.href='"+ "./diaryView" +"';</script>"); 
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
