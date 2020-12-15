package services;

import static dao.BoardDAO.getInstance;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.close;

import java.sql.Connection;

import dao.BoardDAO;
import dto.CommentsDTO;

public class BoardCommentModifyServices {

	public void CommentMoCo(CommentsDTO comment) {

		
		BoardDAO dao2 = getInstance();
		Connection con = getConnection();
		dao2.setConnection(con);
		
		dao2.CommentMoCo(comment);
		close(con);
		
	}



}
