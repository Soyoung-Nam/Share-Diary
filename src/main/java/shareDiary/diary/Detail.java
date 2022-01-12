package shareDiary.diary;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shareDiary.util.Util;



@WebServlet("/detail")
public class Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Detail() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("dNo") != null 
				&& Util.str2Int(request.getParameter("dNo")) != 0
				){
			int dNo = Util.str2Int(request.getParameter("dNo"));
			//BoardDAO dao = new BoardDAO();
			DiaryDAO dao = DiaryDAO.getInstance();
			HashMap<String, Object> dto = dao.detail(dNo);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("./diaryDetail.jsp");
			request.setAttribute("dto", dto);
			rd.forward(request, response);			
		} else {
			response.sendRedirect("./diaryView");
		
		
		}
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
