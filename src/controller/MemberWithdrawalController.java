package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import services.MemberDeleteServices;

@WebServlet("/Memberfire")
public class MemberWithdrawalController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberWithdrawalController() {
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
		String nick = request.getParameter("nick");
		int result = 0;
		MemberDeleteServices mds = new MemberDeleteServices();
		result = mds.memberDelete(id, nick);
		

		if (result>0) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원 추방 완료');</script>");
			out.println("<script>location.href='Main.jsp';</script>");	
		}
		
	}
}

