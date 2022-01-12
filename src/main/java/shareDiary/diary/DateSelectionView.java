package shareDiary.diary;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DateSelectionView
 */
@WebServlet("/DateSelectionView")
public class DateSelectionView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DateSelectionView() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("view");
		DiaryDAO dao = DiaryDAO.getInstance();
		
		
		RequestDispatcher rd = request.getRequestDispatcher("./dateSelectionView.jsp");
		ArrayList<HashMap<String, Object>> list;
		try {
			
			String Start = request.getParameter("dateStart");
			String End = request.getParameter("dateEnd");
			list = dao.SelectedDiaryList(request, Start, End);
			request.setAttribute("dto", list);
			rd.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
