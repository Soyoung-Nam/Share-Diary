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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ExchangeDiaryView
 */
@WebServlet("/ExchangeDiaryView")
public class ExchangeDiaryView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExchangeDiaryView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(); 
		String id = (String)session.getAttribute("id");

		
		DiaryDAO dao = DiaryDAO.getInstance();
		
		
		RequestDispatcher rd = request.getRequestDispatcher("./edView.jsp");
		ArrayList<HashMap<String, Object>> list;
		try {
			list = dao.exchangeDiaryList(request);
			request.setAttribute("dto", list);
			request.setAttribute("id", id);
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
