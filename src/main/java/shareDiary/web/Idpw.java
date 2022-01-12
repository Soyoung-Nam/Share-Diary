package shareDiary.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shareDiary.dao.LoginDAO;
import shareDiary.dto.LoginDTO;

@WebServlet("/idpw")
public class Idpw extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LoginDAO dao = LoginDAO.getInstance();
	
    public Idpw() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./idpw.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		RequestDispatcher rd = request.getRequestDispatcher("./idpw.jsp");
		if (request.getParameter("email") != null) {
			String email = request.getParameter("email");
			String result = dao.idpw(email);
			
			if (result != null) {
				request.setAttribute("result", "1");//아이디 찾기
				request.setAttribute("id", result);
			} else {
				//아이디 없을 경우
				request.setAttribute("result", "2");
			}
			
		} else if (request.getParameter("id") != null
				&& request.getParameter("name") != null
				&& request.getParameter("pw1") != null
				&& request.getParameter("pw2") != null
				&& request.getParameter("pw1").equals(request.getParameter("pw2"))) {
			//비밀번호 설정 DTO
			LoginDTO dto = LoginDTO.getInstance();
			dto.setId(request.getParameter("id"));
			dto.setName(request.getParameter("name"));
			dto.setPw(request.getParameter("pw1"));
			
			int result = dao.passwordReset(dto);
			if (result == 1) {
				//비밀번호 변경 성공이면
				request.setAttribute("result", "3");//정상
				System.out.println(result);
			} else {
				//비밀번호 변경 실패면
				request.setAttribute("result", "4");//실패
			}			
		} else {
			request.setAttribute("result", "5");//에러
		}
		rd.forward(request, response);
		
	}

}
