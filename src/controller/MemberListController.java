package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MemberDTO;
import services.MemberListServices;

@WebServlet("/memberList")
public class MemberListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		MemberListServices mls = new MemberListServices();
		ArrayList<MemberDTO> list = mls.memberList();
		PrintWriter out = response.getWriter();
		
		
		if (list.get(0).getUserBirth()!=null) {
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("MemberListForm.jsp");
			rd.forward(request, response);
		}else {
			out.println("<script>alert('땡땡!!');</script>");
			out.println("<script>location.href='index.html';</script>");
		}
				
	}
}
