package services;

import static dao.MemberDAO.getInstance;
import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MemberDAO;
import dto.MemberDTO;

public class MemberListServices {

	public ArrayList<MemberDTO> memberList() {
		MemberDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<MemberDTO> list= dao.memberList();
		
		close(con);
		
		return list;
	}

}
