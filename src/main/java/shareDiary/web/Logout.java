package shareDiary.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Logout() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그아웃 하기
		HttpSession session = request.getSession();
		if (session.getAttribute("id") != null) {
			session.removeAttribute("id");
		}
		if (session.getAttribute("name") != null) {
			session.removeAttribute("name");
		}
		if (session.getAttribute("grade") != null) {
			session.removeAttribute("grade");
		}
		
		//로그아웃 되면 페이지 이동
		response.sendRedirect("./index");//아직 어디로 보낼지 몰라서 일단은 확인차 다시 로그인페이지로
		//index페이지로 바꿨습니다! 샛별
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
