package shareDiary.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shareDiary.dao.CommentDAO;
import shareDiary.util.Util;


@WebServlet("/commentInput")
public class CommentInput extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentInput() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      //한글처리
	      response.setContentType("text/html;charset=UTF-8");
	      request.setCharacterEncoding("UTF-8");
	      HttpSession session = request.getSession();
	            
	      if(request.getParameter("comment") == null) {
	         System.out.println("comment에러");
	      } else if (request.getParameter("bno") == null) {
	         System.out.println(request.getParameter("bno"));
	         System.out.println("bno에러");
	      } else if (Util.str2Int((String)request.getParameter("bno")) == 0){
	    	 System.out.println((String)request.getParameter("bno"));
	         System.out.println("bno int 에러");
	      } else if(session.getAttribute("id") != null
					&& session.getAttribute("name") != null){
	         
	         
	         String comment = request.getParameter("comment");
	         
	         HashMap<String, Object> map = new HashMap<String, Object>();
	         comment = Util.str2Replace(comment);
	         map.put("id", session.getAttribute("id"));
	         map.put("bip", Util.getIP(request));
	         map.put("comment", comment);
	         map.put("bno", request.getParameter("bno"));
	         
	         CommentDAO dao = CommentDAO.getInstance();
	         int result = dao.commentInput(map);
	         
//	         System.out.println(comment);
//	         System.out.println(bno);

	         if(result == 1) {
	            response.sendRedirect("./boardDetail?bno="+request.getParameter("bno"));
	         } else {
	            response.sendRedirect("./error.jsp?code=resulterror");
	            System.out.println("sql 에러");
	         }
	      } else {
	         response.sendRedirect("./error.jsp?code=sessionError");
	         System.out.println("세션값 에러");
	      }
	   }
	}