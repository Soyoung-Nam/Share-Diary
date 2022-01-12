package shareDiary.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shareDiary.dao.LogDAO;
import shareDiary.dao.LoginDAO;
import shareDiary.dto.LoginDTO;
import shareDiary.util.Util;

@WebServlet("/myinfo")
public class Myinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Myinfo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("get으로 들어오나?");
		RequestDispatcher rd = request.getRequestDispatcher("./passwordCheck.jsp");
		request.setAttribute("check", "1");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (request.getParameter("pw") != null && session.getAttribute("id") != null && session.getAttribute("name") != null) {
			LoginDAO dao = LoginDAO.getInstance();
			//변수
			String pw = request.getParameter("pw");
			String id = (String)session.getAttribute("id");
			LoginDTO dto = dao.myinfo(id, pw);
				
			if (dto != null) {
				//DTO -> HashMap 샛별0811
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("ip", Util.getIP(request));
				map.put("target", "Myinfo");
				map.put("etc", dto.getPw() + "를 입력했습니다.");
				if (session.getAttribute("id") != null) {
					map.put("id", id);
				}
				LogDAO.getInstance().insertLog(map);
				
				//페이지이동
				RequestDispatcher rd = request.getRequestDispatcher("./myinfo.jsp");
				request.setAttribute("check", "2");
				request.setAttribute("dto", dto);
				rd.forward(request, response);
				}
			} else {
					response.sendRedirect("./error?code=paramerror");
			}
		}
}

