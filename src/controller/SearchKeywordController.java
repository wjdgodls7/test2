package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import services.SearchKeywordServices;

/**
 * Servlet implementation class MemberWriteListController
 */
@WebServlet("/searchKeyword")
public class SearchKeywordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SearchKeywordController() {
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
		String search2 = request.getParameter("search");

		SearchKeywordServices sks = new SearchKeywordServices();
		
		int boardCount = sks.getCount(search2);
		System.out.println(boardCount);
		int startRow = (page-1) * limit + 1;
		int endRow = page * limit;
		
		ArrayList<BoardDTO> list = sks.search(search2, startRow, endRow);
		
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
		
		System.out.println(paging.getMaxPage());
		System.out.println(paging.getPage());
		System.out.println(list.size());
		if (list.size()>0) {
			RequestDispatcher rd = request.getRequestDispatcher("SearchForm.jsp");
			request.setAttribute("paging", paging);
			request.setAttribute("boardList", list);
			request.setAttribute("search", search2);
			rd.forward(request, response);
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('검색결과가 없습니다.');</script>");
			out.println("<script>location.href='writeList';</script>");
			
		}
		
		

	}
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);

	}

}
