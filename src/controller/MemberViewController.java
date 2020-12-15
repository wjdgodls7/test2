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
import services.MemberViewSerivces;

@WebServlet("/memberView")
public class MemberViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberViewController() {
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

		String id = request.getParameter("id");

		MemberDTO memberView = new MemberDTO();
		MemberViewSerivces mvs = new MemberViewSerivces();

		memberView = mvs.memberView(id);
		PrintWriter out = response.getWriter();

		if(memberView != null) {
			request.setAttribute("memberView", memberView);
			RequestDispatcher rd = request.getRequestDispatcher("MemberViewForm.jsp");
			rd.forward(request, response);
		}else {
			out.println("<script>alert('로그아웃 되었습니다 !');</script>");
			out.println("<script>location.href='index.html';</script>");
		}
	}
}
