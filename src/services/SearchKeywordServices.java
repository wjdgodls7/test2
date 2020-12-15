package services;

import static dao.BoardDAO.getInstance;
import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import dto.BoardDTO;

public class SearchKeywordServices {
	
	public int getCount(String search2) {
		BoardDAO dao2 = getInstance();
		Connection con = getConnection();
		dao2.setConnection(con);
		
		int Count = dao2.getCount(search2);
		close(con);
	
		return Count;
	}

	public ArrayList<BoardDTO> search(String search2, int startRow, int endRow) {
		BoardDAO dao2 = getInstance();
		Connection con = getConnection();
		dao2.setConnection(con);
		
		ArrayList<BoardDTO> list = dao2.search(search2, startRow, endRow);
		close(con);
		return list;
	}



}
