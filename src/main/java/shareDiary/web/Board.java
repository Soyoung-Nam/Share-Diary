package shareDiary.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shareDiary.dao.LogDAO;
import shareDiary.dao.BoardDAO;
import shareDiary.util.Util;

@WebServlet("/board")
public class Board extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Board() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;  //기본 페이지 1페이지로 설정
		if(request.getParameter("page") != null) { 
			//넘어오는 값이 있으면 값으로 페이지 설정
			page = Util.str2Int(request.getParameter("page"));
		}
		
		ArrayList<HashMap<String, Object>> list = null;
		ArrayList<String> cateList = BoardDAO.getInstance().cateList();
		
		String cateEng = "" , category = "";
		
		if(request.getParameter("category") == null 
				|| request.getParameter("category").equals("allcate")) {
			list = BoardDAO.getInstance().boardList((page - 1) * 10, 10);
			//카테고리 선택 없으면 그냥 리스트 보이기
		} else {
			cateEng = request.getParameter("category");
			
			switch (cateEng) {
			case "daily":
				category = "일상";
				break;
			case "humor":
				category = "유머";
				break;
			case "movie":
				category = "영화";
				break;

			default:
				category = "error";
				System.out.println("category error!");
				break;
			}
			
			list = BoardDAO.getInstance().selectCate(category, (page - 1) * 10);
		}
		
		//log남기기
		HashMap<String, Object> log = new HashMap<String, Object>();
		log.put("ip", Util.getIP(request));
		log.put("id", request.getParameter("id"));
		log.put("target", "board");
		log.put("etc", request.getHeader("user-agent"));
		LogDAO.getInstance().insertLog(log);
		
		RequestDispatcher rd = request.getRequestDispatcher("./board.jsp");
		request.setAttribute("dto", list);
		request.setAttribute("cateList", cateList);
		request.setAttribute("category", category);
		
		//페이징
		if(list.size() > 0) {
			request.setAttribute("totalCount", list.get(0).get("totalcount"));
		}
		request.setAttribute("page", page);
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Util.str2Int(request.getParameter("page"));
		}
		String searchValue = request.getParameter("searchvalue");
		String searchText = request.getParameter("searchtext");
		
		System.out.println(searchValue);
		System.out.println(searchText);
		
		ArrayList<HashMap<String, Object>> dto = BoardDAO.getInstance().search(searchValue, searchText, (page - 1) * 10);
		
		RequestDispatcher rd = request.getRequestDispatcher("./board.jsp");
		if(dto.size() > 0) {
			request.setAttribute("totalCount", dto.get(0).get("totalcount"));
		} else {
			System.out.println("dto error");
			response.sendRedirect("./error?code=dtosize");
		}
		request.setAttribute("page", page);
		request.setAttribute("dto", dto);
		rd.forward(request, response);
	}
}
