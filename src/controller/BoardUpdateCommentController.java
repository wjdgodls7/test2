package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import dto.CommentsDTO;
import dto.MemberDTO;
import services.UpdateServices;



/**
 * Servlet implementation class BoardUpdateCommentController
 */
@WebServlet("/updateComment")
public class BoardUpdateCommentController extends HttpServlet {
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
		
		String aftercomment= request.getParameter("aftercomment");
		String id= request.getParameter("id");
		String nick= request.getParameter("nick");
//		String time= request.getParameter("time");
		int commentNum = Integer.parseInt(request.getParameter("commentNum"));
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		
		System.out.println(aftercomment);
		System.out.println(commentNum);
		System.out.println(boardNum);
		

		CommentsDTO comment = new CommentsDTO();
		comment.setBoardNum(boardNum);
		comment.setComments(aftercomment);
		comment.setId(id);
		comment.setNick(nick);
		comment.setNum(commentNum);
//		comment.setTime(time);
		System.out.println("1차 검사");
		
		UpdateServices ups = new UpdateServices();
		int upResult = ups.updateComment(comment);
		
		if(upResult>0) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("writeDetail2");
			request.setAttribute("num", boardNum);
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("");
		}
		
		
	}


}
