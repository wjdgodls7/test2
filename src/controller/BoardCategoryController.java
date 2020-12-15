package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.BoardDTO;
import dto.PageDTO;
import services.BoardCategoryServices;
import services.BoardListServices;

/**
 * Servlet implementation class MemberWriteListController
 */
@WebServlet("/BoardCategoryController")
public class BoardCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BoardCategoryController() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
			
		int page = 1;
		int limit = 5;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		String subject = request.getParameter("subject");
		HttpSession ss = request.getSession();
		ss.setAttribute("subject", subject);
		System.out.println(subject);

		BoardCategoryServices boardCatory = new BoardCategoryServices();
		
		int boardCount = boardCatory.BoardCategoryCount(subject);
		
		int startRow = (page-1) * limit + 1;
		int endRow = page * limit;
		
		ArrayList<BoardDTO> list = boardCatory.BoardCategory(startRow, endRow, subject);
		
		int maxPage = (int)((double)boardCount / limit + 0.9);
		int startPage = (((int)((double)page / 5 + 0.9))-1)* 5 + 1;
		
		int endPage = startPage + 5 -1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		PageDTO paging = new PageDTO();
		
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		paging.setBoardCount(boardCount);
		
		request.setAttribute("paging", paging);
		request.setAttribute("boardList", list);
		request.setAttribute("subject", subject);
		
		
		if(subject.equals("all")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("writeList");
			dispatcher.forward(request, response);
		}else  if(subject.equals("Notice")) {		
			RequestDispatcher dispatcher = request.getRequestDispatcher("BoardNotice.jsp");
			dispatcher.forward(request, response);
			
		} else if (subject.equals("PartBoard")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("BoardPart.jsp");
			dispatcher.forward(request, response);
			
		} else if (subject.equals("FreeBoard")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("BoardFree.jsp");
			dispatcher.forward(request, response);
			
		} else if (subject.equals("QNABoard")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("BoardQna.jsp");
			dispatcher.forward(request, response);
			
		}
		
		

	}
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);

	}

}
