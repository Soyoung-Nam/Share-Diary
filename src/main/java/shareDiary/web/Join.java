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

@WebServlet("/join")
public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Join() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글처리
		request.setCharacterEncoding("UTF-8");
		if (request.getParameter("id") != null 
				&& request.getParameter("name") != null
				&& request.getParameter("pw1") != null
				&& request.getParameter("pw2") != null
				&& request.getParameter("email") != null
				&& request.getParameter("pw1").equals(request.getParameter("pw2"))) {
			
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String pw1 = request.getParameter("pw1");
			String email = request.getParameter("email");
			String date = request.getParameter("date");
			
			//<, > 치환작업
			name = name.replaceAll("<", "&lt;");
			name = name.replaceAll(">", "&gt;");
			name = name.replaceAll("/", "&#47;");	
			
			//DAO > DTO에 담기
			LoginDTO dto = LoginDTO.getInstance();
			dto.setId(id);
			dto.setName(name);
			dto.setPw(pw1);
			dto.setEmail(email);
			dto.setDate(date);
			//DB작업
			LoginDAO dao = LoginDAO.getInstance(); //DAO에 join으로 보내기
			int count = dao.join(dto);
			
			//가입완료 (일단은 어디로 보낼지 몰라서 가입완료 페이지로...)
			RequestDispatcher rd = request.getRequestDispatcher("./joinUs.jsp");
			request.setAttribute("count", count);
			request.setAttribute("id", id);
			
			rd.forward(request, response);
			
		} else {
			response.sendRedirect("./error?code=paramerror");//에러
		}
				
	}

}
