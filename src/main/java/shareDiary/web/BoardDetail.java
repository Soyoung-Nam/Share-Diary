package shareDiary.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shareDiary.dao.BoardDAO;
import shareDiary.util.Util;

@WebServlet("/boardDetail")
public class BoardDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public BoardDetail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("bno") != null 
				&& Util.str2Int(request.getParameter("bno")) != 0){
			
			int bno = Util.str2Int(request.getParameter("bno"));
			
			BoardDAO dao = BoardDAO.getInstance();
			HashMap<String, Object> dto = dao.detail(bno);
			
			//댓글 불러오기
			if(((int)dto.get("commentcount")) > 0) {
				ArrayList<HashMap<String, Object>> commentList = dao.commentList(bno);
				request.setAttribute("commentList", commentList);
			}
			
			//게시글 상세보기 불러오기
			RequestDispatcher rd = request.getRequestDispatcher("./boardDetail.jsp");
			request.setAttribute("dto", dto);
			rd.forward(request, response);			
		} else {
			response.sendRedirect("./error?code=bnoerror");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
