package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import dto.CommentsDTO;
import services.BoardListService;

/**
 * Servlet implementation class BoardCommentListController
 */
@WebServlet("/CommentList")
public class BoardCommentListController extends HttpServlet {
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
		int boardNum= (int) request.getAttribute("boardNum");
		
		System.out.println(boardNum);
		System.out.println("코멘트 리스트");

		BoardListService listsvc = new BoardListService();

		ArrayList<CommentsDTO> commentlist = listsvc.CommentList(boardNum);

		System.out.println(commentlist.get(0).getBoardNum());

		RequestDispatcher dispatcher = request.getRequestDispatcher("writeDetail");
		request.setAttribute("commentlist", commentlist);
		
		dispatcher.forward(request, response);
	}
}


