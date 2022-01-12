package shareDiary.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shareDiary.dao.LoginDAO;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글처리
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		LoginDAO dao = LoginDAO.getInstance();
		//개인정보를 map에 담아오는 메소드
		HashMap<String, Object> member = dao.login(id, pw);
		
		//아이디 비번이 일치한 경우
		if (member != null) {
			//세션만들기
			HttpSession session = request.getSession();
			session.setAttribute("id", member.get("id"));
			session.setAttribute("name", member.get("name"));
			//등급이 9등급일 경우
			if ((int)member.get("grade") == 9) {
				session.setAttribute("grade", member.get("grade"));
			}

			//아이디 비번 일치시 페이지 이동
			response.sendRedirect("./index");
		} else {
			//아이디나 비번이 틀리다면
			response.sendRedirect("./logincheck.jsp");
		}
	}
	 
}
