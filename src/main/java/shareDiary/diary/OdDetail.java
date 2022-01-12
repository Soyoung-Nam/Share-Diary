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

/**
 * Servlet implementation class odDetail
 */
@WebServlet("/odDetail")
public class OdDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OdDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("dNo") != null 
				&& Util.str2Int(request.getParameter("dNo")) != 0
				){
			int dNo = Util.str2Int(request.getParameter("dNo"));
			//BoardDAO dao = new BoardDAO();
			DiaryDAO dao = DiaryDAO.getInstance();
			HashMap<String, Object> dto = dao.detail(dNo);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("./odDetail.jsp");
			request.setAttribute("dto", dto);
			rd.forward(request, response);			
		} else {
			response.sendRedirect("./odView");
		
		
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
