package shareDiary.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shareDiary.dao.LoginDAO;


@WebServlet("/idCheck")
public class IDCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IDCheck() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result = 0;
		String id = request.getParameter("id");
		//아이디가 있으면 1
		//DAO가 결과값 가져옴
		LoginDAO dao = LoginDAO.getInstance();
		result = dao.idCheck(id);//아이디가 없으면 0
		
		PrintWriter pw = response.getWriter();
		pw.print(result);
	}

}
