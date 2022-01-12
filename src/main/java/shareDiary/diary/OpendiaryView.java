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

import shareDiary.dao.BoardDAO;
import shareDiary.dao.LogDAO;
import shareDiary.util.Util;

/**
 * Servlet implementation class OpendiaryView
 */
@WebServlet("/OpendiaryView")
public class OpendiaryView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpendiaryView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int page = 1;  //湲곕낯 �럹�씠吏� 1�럹�씠吏�濡� �꽕�젙
		int totalNum = 10;
		if(request.getParameter("page") != null) { 
			//�꽆�뼱�삤�뒗 媛믪씠 �엳�쑝硫� 媛믪쑝濡� �럹�씠吏� �꽕�젙
			page = Util.str2Int(request.getParameter("page"));
			System.out.println(page);
			
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("./odView.jsp");
		request.setAttribute("page", page);
		request.setAttribute("totalnum", totalNum);
		

		ArrayList<HashMap<String, Object>> list = null;

		try {
			list = DiaryDAO.getInstance().openDiaryList((page)*10, totalNum);
			if(list.size() > 0) {
				request.setAttribute("totalCount", list.get(0).get("totalcount"));
			}
			
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
