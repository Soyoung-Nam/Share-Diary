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

import shareDiary.diary.DiaryDAO;

import shareDiary.dao.LogDAO;

import shareDiary.util.*;

@WebServlet("/diaryView")
public class diaryView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public diaryView() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

		HashMap<String, Object> log = new HashMap<String, Object>();
	      log.put("ip", Util.getIP(request));
	      log.put("id", request.getParameter("id"));
	      log.put("target", "diaryView");
	      log.put("etc", request.getHeader("user-agent"));
	      LogDAO.getInstance().insertLog(log);
		
		
		HttpSession session = request.getSession();
		String  id = (String) session.getAttribute("id");
		
		
		DiaryDAO dao = DiaryDAO.getInstance();
		
		
		RequestDispatcher rd = request.getRequestDispatcher("./diaryView.jsp");
		ArrayList<HashMap<String, Object>> list;
		try {
			list = dao.DiaryList(request);
			request.setAttribute("dto", list);
			request.setAttribute("id", id);
			rd.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
