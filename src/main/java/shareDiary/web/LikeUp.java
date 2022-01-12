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

@WebServlet("/likeUp")
public class LikeUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LikeUp() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("bcno") != null
			&& request.getParameter("bno") != null
			&& Util.str2Int(request.getParameter("bcno")) != 0
			&& Util.str2Int(request.getParameter("bno")) != 0) {
			
			int bno = Util.str2Int(request.getParameter("bno"));
				
			HashMap<String, Object> dto = new HashMap<String, Object>();
			dto.put("bcno", request.getParameter("bcno"));
			dto.put("bno", request.getParameter("bno"));
				
			CommentDAO dao = CommentDAO.getInstance();
			int count = dao.likeUp(dto);
			
			if(count == 1) {
				response.sendRedirect("./boardDetail?bno="+bno);
			} else {
				response.sendRedirect("./error?code=commdaoerror");
			}
			} else {
				response.sendRedirect("./error?code=bcnoerror");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
