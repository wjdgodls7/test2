package services;

import static dao.BoardDAO.getInstance;
import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import dto.BoardDTO;

public class BoardHitServices {
	public int BoardHitCount() {
		BoardDAO dao2 = getInstance();
		Connection con = getConnection();
		dao2.setConnection(con);
		
		int boardCount = dao2.BoardHitCount();
		
		
		close(con);
		
		return boardCount;
	}

	public ArrayList<BoardDTO> BoardHit(int startRow, int endRow) {
		BoardDAO dao2 = getInstance();
		Connection con = getConnection();
		dao2.setConnection(con);

		ArrayList<BoardDTO> list = dao2.BoardHit(startRow, endRow);

		
		close(con);
		
		return list;
	}
}
