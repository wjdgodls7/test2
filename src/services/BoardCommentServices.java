package services;

import static dao.BoardDAO.getInstance;
import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import java.util.List;

import dao.BoardDAO;

import dto.CommentsDTO;

public class BoardCommentServices {
	public int CommentWrite(CommentsDTO comments) {
		System.out.println("2.service");
		System.out.println("댓글내용"+ comments.getComments());
		System.out.println("댓글아이디"+ comments.getNick());
		System.out.println("댓글닉"+ comments.getId());


		// 공통부분
		BoardDAO dao2 = getInstance();
		Connection con = getConnection();
		dao2.setConnection(con);
		// 여기까지

		int writeResult = dao2.CommentWrite(comments);
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

	public List<CommentsDTO> commnetList(int num) {
		// 공통부분
		BoardDAO dao2 = getInstance();
		Connection con = getConnection();
		dao2.setConnection(con);
		// 여기까지
		
		List<CommentsDTO> list = dao2.CommentList(num);
		close(con);
		return list;
	}
}
