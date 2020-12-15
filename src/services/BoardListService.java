package services;

import static dao.BoardDAO.getInstance;
import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import dto.CommentsDTO;

public class BoardListService {

	public ArrayList<CommentsDTO> CommentList(int boardNum) {
		// 공통부분
		BoardDAO dao2 = getInstance();
		Connection con = getConnection();
		dao2.setConnection(con);
		// 여기까지
		
		ArrayList<CommentsDTO> commentlist = dao2.CommentList(boardNum);
		close(con);
		
		return commentlist;
	}

}
