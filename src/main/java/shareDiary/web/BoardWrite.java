package shareDiary.web;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import shareDiary.dao.BoardDAO;
import shareDiary.util.Util;

@WebServlet("/boardWrite")
public class BoardWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardWrite() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("./boardWrite.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		String savePath = request.getServletContext().getRealPath("/") + "upload/";
		int maxSize = 1024 * 1024 * 10;
		// 사이즈는 일단 10mb로 정해놨습니다.

		MultipartRequest mpRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());

		String btitle = mpRequest.getParameter("btitle");
		String bcontent = mpRequest.getParameter("bcontent");
		
		String cateEng = mpRequest.getParameter("category");
		String category = "";
		
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

		if (btitle.length() == 0 || bcontent.length() == 0) {
			System.out.println("write length error:411");
			// 제목이나 내용의 길이가 0일 경우 error code=411
			response.sendRedirect("./boardWrite?code=411");
		} else if(session.getAttribute("id") == null
				|| session.getAttribute("name") == null) {
			System.out.println("login error:511");
			//id나 name이 없을 경우(로그인 에러) : code=511
			response.sendRedirect("./login?code=511");
			/* 로그인은 주석처리 해놓을게요~~-샛별 <!-- 0810샛별 --> */
		} else { // write 성공시
			//String fileUName = null; // fileUserName 유저 저장 이름
			String fileSName = null; // fileSaveName 실제 저장 이름
			bcontent = Util.str2Replace(bcontent); // 특문 처리
			if (mpRequest.getOriginalFileName("file") != null) {
				//fileUName = mpRequest.getOriginalFileName("file");// fileUserName 유저 저장 이름
				fileSName = mpRequest.getFilesystemName("file");// fileSaveName 실제 저장 이름

				// 썸네일 결정 후 주석 풀어주세요~~-샛별
				String thumbnailPath = request.getSession().getServletContext().getRealPath("/") + "thumbnail/";
				BufferedImage inputImg = ImageIO.read(new File(savePath + fileSName));
				String[] imgs = { "gif", "png", "jpg", "jpeg", "bmp" }; // 확장자 필요시 추가

				for (String format : imgs) {
					BufferedImage outputImg = new BufferedImage(80, 60, BufferedImage.TYPE_INT_RGB);
					Graphics2D gr2d = outputImg.createGraphics();
					gr2d.drawImage(inputImg, 0, 0, 80, 60, null);

					// 파일 쓰기
					File thumb = new File(thumbnailPath, fileSName);

					// 그래픽스로 그린 그림을 실제로 넣어주기
					FileOutputStream fos = new FileOutputStream(thumb);
					ImageIO.write(outputImg, format, thumb);
					fos.close();
				}
			}

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("btitle", btitle);
			map.put("bcontent", bcontent);
			map.put("id", session.getAttribute("id"));
			map.put("bip", Util.getIP(request));
			map.put("bfilename", fileSName);
			map.put("bthumbnail", fileSName);
			map.put("category", category);

			int result = BoardDAO.getInstance().boardWrite(map);

			if (result == 1) {
				response.sendRedirect("./board");
			} else {
				response.sendRedirect("./error?code=500");
				System.out.println("sql error:500");
			}
		}
	}
}
