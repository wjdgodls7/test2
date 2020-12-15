package services;

import static dao.MemberDAO.getInstance;
import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MemberDAO;
import dto.MemberDTO;

public class MemberViewSerivces {

	public MemberDTO memberView(String id) {
		MemberDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		MemberDTO result = dao.memberview(id);
		
		close(con);
		return result;
	}



}
