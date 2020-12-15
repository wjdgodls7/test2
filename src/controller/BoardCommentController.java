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
import services.BoardCommentServices;

@WebServlet("/comment")
public class BoardCommentController extends HttpServlet {
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
		
		
		String commentText = request.getParameter("commentText");
		String id = request.getParameter("id");
		String nick = request.getParameter("nick");
		int boardNum = Integer.parseInt(request.getParameter("num"));
		

		CommentsDTO dto = new CommentsDTO(); 
		dto.setComments(commentText);
		dto.setId(id);
		dto.setNick(nick);
		dto.setBoardNum(boardNum);
		
		BoardCommentServices BCS = new BoardCommentServices();
		int writeResult = BCS.CommentWrite(dto);
		request.setAttribute("boardNum", boardNum);
		PrintWriter out = response.getWriter();
		
		
		if(writeResult>0) {
			out.println("<script>alert('글 작성 성공!');</script>");
			RequestDispatcher dispatcher = request.getRequestDispatcher("CommentList");
			dispatcher.forward(request, response);
		} else {
			out.println("<script>alert('글을 입력해 주세요!');</script>");
			out.println("<script>history.back();</script>");
		}
	}
}
