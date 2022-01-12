package shareDiary.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shareDiary.dao.CommentDAO;
import shareDiary.util.Util;

@WebServlet("/commentModify")
public class CommentModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentModify() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글처리
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		int bno = Util.str2Int(request.getParameter("bno"));
		
		HashMap<String, Object> dto = new HashMap<String, Object>();
		dto.put("content", request.getParameter("content"));
		dto.put("bno", request.getParameter("bno"));
		dto.put("bcno", request.getParameter("bcno"));
		

		CommentDAO dao = CommentDAO.getInstance();
		int result = dao.commentModify(dto);
		
		if(result == 1) {
			response.sendRedirect("./boardDetail?bno="+bno);
		} else {
			response.sendRedirect("./error?code=sqlerror");
			System.out.println("sql error!");
		}
	}
}
