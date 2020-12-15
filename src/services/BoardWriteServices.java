package services;

import static dao.BoardDAO.getInstance;
import static db.JdbcUtil.*;


import java.sql.Connection;

import dao.BoardDAO;
import dto.BoardDTO;

public class BoardWriteServices {
	
	public int SerivceCode(BoardDTO board) {
		int writeResult = 0;
		switch (board.getServicecode()) {
		case 1:
			writeResult = Write(board);
			break;
		case 2:
			writeResult = Write2(board);
			break;
		}
		return writeResult;
	}

	private int Write(BoardDTO board) {
		System.out.println("2.service");
		// 공통부분
		BoardDAO dao2 = getInstance();
		Connection con = getConnection();
		dao2.setConnection(con);
		// 여기까지
		
		int writeResult = dao2.Write(board);
		System.out.println("6.service : " + writeResult);
		
		if(writeResult>0) {
			commit(con);
			close(con);
		} else {
			rollback(con);
			close(con);
		}
		
		return writeResult;
	}
	private int Write2(BoardDTO board) {
		BoardDAO dao2 = getInstance();
		Connection con = getConnection();
		dao2.setConnection(con);
		
		int writeResult2 = dao2.Write2(board);
		System.out.println("6.service : " + writeResult2);
		
		if(writeResult2>0) {
			commit(con);
			close(con);
		} else {
			rollback(con);
			close(con);
		}
		
		return writeResult2;
	}

}
