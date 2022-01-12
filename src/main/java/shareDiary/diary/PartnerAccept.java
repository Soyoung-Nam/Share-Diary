package shareDiary.diary;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PartnerAccept
 */
@WebServlet("/PartnerAccept")
public class PartnerAccept extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PartnerAccept() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		RequestDispatcher rd = request.getRequestDispatcher("./partner.jsp");
		
		
		
		HttpSession session = request.getSession();
		String  id =(String) session.getAttribute("id");
		
		String send = request.getParameter("send");
		
		
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("send", send);
		map.put("id", id);
		
		

		DiaryDAO dao = DiaryDAO.getInstance();
		dao.partnerAccept(map);
		
		rd.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
