package services;

import static dao.BoardDAO.getInstance;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BoardDAO;
import dto.CommentsDTO;

public class UpdateServices {

	public int updateComment(CommentsDTO comment) {
		// 공통부분
		BoardDAO dao2 = getInstance();
		Connection con = getConnection();
		dao2.setConnection(con);
		// 여기까지
		System.out.println("2차 검사");
		int upResult = dao2.updateComment(comment);

		if(upResult>0) {
			commit(con);
		} else {
			rollback(con);
		}

		return upResult;
	}

}
