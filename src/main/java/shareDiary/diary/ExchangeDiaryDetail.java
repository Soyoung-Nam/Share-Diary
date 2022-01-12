package shareDiary.diary;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import shareDiary.util.Util;

/**
 * Servlet implementation class ExchangeDaryWriting
 */
@WebServlet("/EdDetail")
public class ExchangeDiaryDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExchangeDiaryDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String  id = (String) session.getAttribute("id");


		DiaryDAO dao = DiaryDAO.getInstance();
		HashMap<String, Object> dto = dao.edetail(id);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("./edWriting.jsp");
		request.setAttribute("dto", dto);
		rd.forward(request, response);			
	
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
