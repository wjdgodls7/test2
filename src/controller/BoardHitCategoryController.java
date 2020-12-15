package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
import services.BoardHitServices;
import services.BoardCategoryServices;
import services.BoardHitCategoryServices;
import services.BoardListServices;

/**
 * Servlet implementation class MemberWriteListController
 */
@WebServlet("/BoardHitCategoryController")
public class BoardHitCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BoardHitCategoryController() {
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
		String hits = request.getParameter("hit");
		String subject = request.getParameter("subject");
		System.out.println(hits);
		System.out.println(subject);
		
		BoardHitCategoryServices boardhit = new BoardHitCategoryServices();
		
		int boardCount = boardhit.BoardHitCategoryCount(subject);
		
		int startRow = (page-1) * limit + 1;
		int endRow = page * limit;
		
		ArrayList<BoardDTO> list = boardhit.BoardHitCategory(startRow, endRow, subject);
		
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
		request.setAttribute("hit2", hits);
		request.setAttribute("subject", subject);
		
		
		if(hits.equals("alll")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("writeList");
			dispatcher.forward(request, response);
		}else if(subject.equals("Notice")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("BoardNoticeMaxList.jsp");
			dispatcher.forward(request, response);
		}
		else if(subject.equals("PartBoard")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("BoardPartMaxList.jsp");
			dispatcher.forward(request, response);
		}
		else if(subject.equals("FreeBoard")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("BoardFreeMaxList.jsp");
			dispatcher.forward(request, response);
		}
		else if(subject.equals("QNABoard")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("BoardQnaMaxList.jsp");
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
