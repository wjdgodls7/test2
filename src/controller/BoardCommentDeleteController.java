package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.CommentsDTO;
import services.BoardDeleteServices;
import services.CommentDeleteServices;

@WebServlet("/CommentDelete")
public class BoardCommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);

	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("1.Comment controller");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		int commentNum = Integer.parseInt(request.getParameter("commentNum"));
		String nick = request.getParameter("nick");
		String loginnick = request.getParameter("loginnick");
		String level = request.getParameter("level");
		int BoardNum = Integer.parseInt(request.getParameter("BoardNum"));

		CommentDeleteServices CDS = new CommentDeleteServices();

		PrintWriter out = response.getWriter();

		int delResult = CDS.CommentDel(commentNum);

		if(delResult>0) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("writeDetail2");
			request.setAttribute("num", BoardNum);
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("BoardWriteFail.jsp");
		}


	}

}
