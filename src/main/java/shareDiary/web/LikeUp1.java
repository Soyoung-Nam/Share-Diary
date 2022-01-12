package shareDiary.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shareDiary.dao.BoardDAO;
import shareDiary.util.Util;

@WebServlet("/likeUp1")
public class LikeUp1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LikeUp1() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("bno") != null
			&& Util.str2Int(request.getParameter("bno")) != 0) {
				
			int bno = Util.str2Int(request.getParameter("bno"));
			String value = request.getParameter("value");
					
			HashMap<String, Object> dto = new HashMap<String, Object>();
			dto.put("bno", request.getParameter("bno"));
					
			BoardDAO dao = BoardDAO.getInstance();
			int count = dao.likeUp1(dto, value);
				
			if(count == 1) {
				response.sendRedirect("./boardDetail?bno="+bno);
			} else {
				response.sendRedirect("./error?code=daoerror");
			}
			} else {
				response.sendRedirect("./error?code=bnoerror");
			}
		}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
