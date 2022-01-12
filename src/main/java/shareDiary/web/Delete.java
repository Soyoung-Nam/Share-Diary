package shareDiary.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shareDiary.dao.BoardDAO;
import shareDiary.util.Util;


@WebServlet("/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Delete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("bno") != null
			&& Util.str2Int(request.getParameter("bno")) != 0) {
			
			int bno = Util.str2Int(request.getParameter("bno"));
			
			BoardDAO dao = BoardDAO.getInstance();
			int result = dao.delete(bno);
			if(result == 1 ) {
				response.sendRedirect("./board");
			} else {
				response.sendRedirect("error?code=sqlerror");
				System.out.println("sql 에러");
			}
			} else {
				response.sendRedirect("error?code=bnoerror");
				System.out.println("값 에러");
			}
		}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
