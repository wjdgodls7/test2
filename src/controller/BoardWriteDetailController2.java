package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import dto.CommentsDTO;
import services.BoardCommentServices;
import services.BoardDetailServices;

/**
 * Servlet implementation class MemberWriteDetailController
 */
@WebServlet("/writeDetail2")
public class BoardWriteDetailController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardWriteDetailController2() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		BoardDetailServices detail = new BoardDetailServices();
		BoardCommentServices bcs = new BoardCommentServices();
		
		int num =(int) request.getAttribute("num");
		
		BoardDTO dto = detail.BoardDetail(num);
		List<CommentsDTO> list = bcs.commnetList(num);
		
		if(dto != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("BoardDetail.jsp");
			request.setAttribute("commentList",list);
			request.setAttribute("detail", dto);
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect("BoardList.jsp");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);

	}

}
