package shareDiary.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shareDiary.dao.LoginDAO;
import shareDiary.dto.LoginDTO;

@WebServlet("/changepw")
public class ChangePW extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public ChangePW() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션 가져오기
		HttpSession session = request.getSession();
		if (session.getAttribute("id") != null
				&& request.getParameter("pw") != null) {
			LoginDAO dao = LoginDAO.getInstance();
			LoginDTO dto = LoginDTO.getInstance();
			dto.setId((String) session.getAttribute("id"));
			dto.setPw(request.getParameter("pw"));
			dto.setName((String) session.getAttribute("name"));
			
			int result = dao.passwordReset(dto);
			if(result == 1) {
				response.sendRedirect("./logout");//성공 (08/11 logout으로 변경했습니다.)
			} else {
				response.sendRedirect("./error?code=resultnerror");
				System.out.println("result error");				
			}
		} else {
			//에러일때
			response.sendRedirect("./error?code=sessionerror");
			System.out.println("session error");
		}
	}

}
