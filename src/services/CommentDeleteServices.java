package services;

import static dao.BoardDAO.getInstance;
import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BoardDAO;

public class CommentDeleteServices {

	public int CommentDel(int commentNum) {
		// 공통부분
				BoardDAO dao2 = getInstance();
				Connection con = getConnection();
				dao2.setConnection(con);
				// 여기까지
				
				int delResult = dao2.CommentDel(commentNum);

				if(delResult>0) {
					commit(con);
				} else {
					rollback(con);
				}
				close(con);
				System.out.println("boardDeleteService :" + delResult);
				return delResult;
	}

}
