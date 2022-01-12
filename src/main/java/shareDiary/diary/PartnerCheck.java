package shareDiary.diary;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shareDiary.dao.LogDAO;
import shareDiary.util.Util;

/**
 * Servlet implementation class PartnerCheck
 */
@WebServlet("/PartnerCheck")
public class PartnerCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PartnerCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HashMap<String, Object> log = new HashMap<String, Object>();
	      log.put("ip", Util.getIP(request));
	      log.put("id", request.getParameter("id"));
	      log.put("target", "partnerCheck");
	      log.put("etc", request.getHeader("user-agent"));
	      LogDAO.getInstance().insertLog(log);
		
			
			
		DiaryDAO dao = DiaryDAO.getInstance();
			
			
		RequestDispatcher rd = request.getRequestDispatcher("./partner.jsp");
		ArrayList<HashMap<String, Object>> list;
		try {
			list = dao.partnerList(request);
			request.setAttribute("dto", list);
			rd.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
