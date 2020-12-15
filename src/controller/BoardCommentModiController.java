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
import services.BoardCommentModifyServices;

@WebServlet("/CommentModify")
public class BoardCommentModiController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		int commentNum = Integer.parseInt(request.getParameter("commentNum"));
		String nick = request.getParameter("nick");
		String loginnick = request.getParameter("loginnick");
		String level = request.getParameter("level");
		String commentText = request.getParameter("commentText");
		int BoardNum = Integer.parseInt(request.getParameter("BoardNum"));

		PrintWriter out = response.getWriter();



		CommentsDTO comment = new CommentsDTO();
		comment.setNum(commentNum);

		BoardCommentModifyServices bcms = new BoardCommentModifyServices();
		bcms.CommentMoCo(comment);

		RequestDispatcher dispatcher = request.getRequestDispatcher("BoardCommentForm.jsp");
		request.setAttribute("commentNum", comment.getNum());
		request.setAttribute("commentText", comment.getComments());
		request.setAttribute("nick", comment.getNick());
		request.setAttribute("time", comment.getTime());
		request.setAttribute("id", comment.getId());
		request.setAttribute("BoardNum", comment.getBoardNum());

		dispatcher.forward(request, response);

	}

}
