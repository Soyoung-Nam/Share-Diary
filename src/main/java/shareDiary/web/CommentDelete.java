package shareDiary.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shareDiary.dao.CommentDAO;
import shareDiary.util.Util;

@WebServlet("/commentDelete")
public class CommentDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public CommentDelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("bcno") != null
			&& request.getParameter("bno") != null
			&& Util.str2Int(request.getParameter("bcno")) != 0
			&& Util.str2Int(request.getParameter("bno")) != 0) {
			
			int bcno = Util.str2Int(request.getParameter("bcno"));
			int bno = Util.str2Int(request.getParameter("bno"));
				
			CommentDAO dao = CommentDAO.getInstance();
			int result = dao.commentDelete(bcno, bno);
			
			if(result == 1 ) {
				response.sendRedirect("./boardDetail?bno="+bno);
			} else {
				response.sendRedirect("error?code=sqlerror");
				System.out.println("sql 에러");
			}
			} else {
				response.sendRedirect("error?code=bcnoerror");
				System.out.println("값 에러");
			}
		}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
