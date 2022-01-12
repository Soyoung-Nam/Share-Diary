package shareDiary.web;

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
import shareDiary.diary.DiaryDAO;
import shareDiary.util.Util;

@WebServlet("/index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Index() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;  //기본 페이지 1페이지로 설정
		
		ArrayList<HashMap<String, Object>> openDiaryList = null;
		try {
			openDiaryList = DiaryDAO.getInstance().openDiaryList(10, 5);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<HashMap<String, Object>> boardList = null;
		boardList = BoardDAO.getInstance().boardList(page, 10); 
		//리스트 갯수 상관 없으니 페이지는 1로 임의설정할게요! -샛별
		
		//log남기기
		HashMap<String, Object> log = new HashMap<String, Object>();
		log.put("ip", Util.getIP(request));
		log.put("id", request.getParameter("id"));
		log.put("target", "index");
		log.put("etc", request.getHeader("user-agent"));
		LogDAO.getInstance().insertLog(log);
		
		RequestDispatcher rd = request.getRequestDispatcher("./index.jsp");
		request.setAttribute("boardList", boardList);
		request.setAttribute("openDiaryList", openDiaryList);

		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
