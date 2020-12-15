package services;

import static dao.BoardDAO.getInstance;
import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import dto.BoardDTO;

public class BoardHitCategoryServices {
	public int BoardHitCategoryCount(String subject) {
		BoardDAO dao2 = getInstance();
		Connection con = getConnection();
		dao2.setConnection(con);
		
		int boardCount = dao2.BoardHitCategoryCount(subject);
		
		
		close(con);
		
		return boardCount;
	}

	public ArrayList<BoardDTO> BoardHitCategory(int startRow, int endRow, String subject) {
		BoardDAO dao2 = getInstance();
		Connection con = getConnection();
		dao2.setConnection(con);
		
		ArrayList<BoardDTO> list = dao2.BoardHitCategory(startRow, endRow, subject);
		close(con);
		
		return list;
	}
}
