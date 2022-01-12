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
import javax.servlet.http.HttpSession;

import shareDiary.dao.LogDAO;
import shareDiary.util.Util;

@WebServlet("/admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LogDAO dao = LogDAO.getInstance();

	public Admin() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 0811샛별
		HttpSession session = request.getSession();
		if (session.getAttribute("grade") == null) {
			response.sendRedirect("./index.jsp");
		} else {

			int page = 1;

			ArrayList<HashMap<String, Object>> list = null;
			if (request.getParameter("page") != null) {
				page = Util.str2Int(request.getParameter("page"));
			}
			if (request.getParameter("ip") == null && request.getParameter("target") == null) {
				list = dao.logList((page - 1) * 10);

			} else if (request.getParameter("ip") != "" && request.getParameter("target") != "") {
				String ip = request.getParameter("ip");
				String target = request.getParameter("target");
				list = dao.selectIpTarget(ip, target, (page - 1) * 10);

			} else if (request.getParameter("ip") != "" && request.getParameter("target") == "") {
				String ip = request.getParameter("ip");
				list = dao.selectIP(ip, (page - 1) * 10);

			} else if (request.getParameter("target") != "" && request.getParameter("ip") == "") {
				String target = request.getParameter("target");
				list = dao.selectTarget(target, (page - 1) * 10);
			}

			ArrayList<String> ipList = dao.list("log_ip");

			ArrayList<String> targetList = dao.list("log_target");

			RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
			request.setAttribute("list", list);
			request.setAttribute("ipList", ipList);
			request.setAttribute("targetList", targetList);
			request.setAttribute("ip", request.getParameter("ip"));
			request.setAttribute("target", request.getParameter("target"));

			if (list != null && list.size() > 0) {
				request.setAttribute("totalCount", list.get(0).get("totalcount"));
			}
			
			//log남기기
			HashMap<String, Object> log = new HashMap<String, Object>();
			log.put("ip", Util.getIP(request));
			log.put("id", request.getParameter("id"));
			log.put("target", "admin");
			log.put("etc", request.getHeader("user-agent"));
			LogDAO.getInstance().insertLog(log);

			request.setAttribute("page", page);
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 0811샛별
		HttpSession session = request.getSession();
		if ((Util.str2Int((String) request.getAttribute("grade")) != 9)
				|| session.getAttribute("grade") == null) {
			response.sendRedirect("./error?code=sessiongradeerror");
		} else {

			int page = 1;
			if (request.getParameter("page") != null) {
				page = Util.str2Int(request.getParameter("page"));
			}

			String searchName = request.getParameter("searchname");
			String search = request.getParameter("search");

			ArrayList<HashMap<String, Object>> list = dao.search(searchName, search, page);

			RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
			request.setAttribute("list", list);
			if (list != null && list.size() > 0) {
				request.setAttribute("totalCount", list.get(0).get("totalcount"));
			}
			request.setAttribute("page", page);
			rd.forward(request, response);
		}
	}
}
