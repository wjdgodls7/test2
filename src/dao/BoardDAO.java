package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.BoardDTO;
import dto.CommentsDTO;

import static db.JdbcUtil.*;

public class BoardDAO {
	private static BoardDAO dao2;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	// getInstance메소드
	public static BoardDAO getInstance() {
		if (dao2 == null) {
			dao2 = new BoardDAO();
		}
		return dao2;
	}

	// setConnection 메소드
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public int Write(BoardDTO board) {
		System.out.println("3.dao");
		String sql = "INSERT INTO BOARD VALUES (?,SEQ_5TH.NEXTVAL,?,?,?,0,?,SYSDATE,?,0)";
		

		int writeResult = 0;

		try {
			System.out.println("4.DB");
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, board.getNick());
			pstmt.setString(2, board.getSubject());
			pstmt.setString(3, board.getTitle());
			pstmt.setString(4, board.getContent());
			pstmt.setString(5, board.getFile());
			pstmt.setString(6, board.getId());
			
			writeResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println(board.getNick());
		System.out.println(board.getSubject());
		System.out.println("5.dao : " + writeResult);
		return writeResult;
	}

	public int Write2(BoardDTO board) {
		System.out.println("3.dao");
		String sql = "INSERT INTO BOARD VALUES (?,SEQ_5TH.NEXTVAL,?,?,?,0,?,SYSDATE,?,1)";
		

		int writeResult = 0;

		try {
			System.out.println("4.DB");
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, board.getNick());
			pstmt.setString(2, board.getSubject());
			pstmt.setString(3, board.getTitle());
			pstmt.setString(4, board.getContent());
			pstmt.setString(5, board.getFile());
			pstmt.setString(6, board.getId());
			
			writeResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println(board.getNick());
		System.out.println(board.getSubject());
		System.out.println("5.dao : " + writeResult);
		return writeResult;
	}

	public int BoardDel(int Num) {
		int delResult = 0;
		String sql = "DELETE BOARD WHERE BOARD_NUM=?";
		System.out.println(Num);
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Num);
			delResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		System.out.println("boardDAO :" + delResult);
		return delResult;
	}



	public int BoardCount() {
		String sql = "SELECT COUNT(*) FROM (SELECT BOARDLIST.*, ROW_NUMBER() OVER(ORDER BY BOARD_NUM DESC) AS RN FROM(SELECT * FROM BOARD WHERE BOARD_STATE =0)BOARDLIST)";
		
		int boardCount = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return boardCount;
	}

	public ArrayList<BoardDTO> BoardList(int startRow, int endRow) {
		String sql = "SELECT * FROM (SELECT BOARDLIST.*, ROW_NUMBER() OVER(ORDER BY BOARD_NUM DESC) AS RN FROM(SELECT * FROM BOARD WHERE BOARD_STATE =1)BOARDLIST) WHERE RN BETWEEN ? AND ? AND RN <= 5 \r\n"
					 + "UNION ALL \r\n"
					 + "SELECT * FROM (SELECT BOARDLIST.*, ROW_NUMBER() OVER(ORDER BY BOARD_NUM DESC) AS RN FROM(SELECT * FROM BOARD WHERE BOARD_STATE =0)BOARDLIST) WHERE RN BETWEEN ? AND ?";
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				
				dto.setNick(rs.getNString(1));
				dto.setNum(rs.getInt(2));
				dto.setSubject(rs.getNString(3));
				dto.setTitle(rs.getNString(4));
				dto.setContent(rs.getNString(5));
				dto.setHit(rs.getInt(6));
				dto.setFile(rs.getNString(7));
				dto.setDate(rs.getNString(8));
				dto.setId(rs.getNString(9));
				
			
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
	
		return list;
	}

	public BoardDTO BoardDetail(int num) {
		String sql = "SELECT * FROM BOARD WHERE BOARD_NUM = ?";
		BoardDTO dto = new BoardDTO();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setNick(rs.getNString(1));
				dto.setNum(rs.getInt(2));
				dto.setSubject(rs.getNString(3));
				dto.setTitle(rs.getNString(4));
				dto.setContent(rs.getNString(5));
				dto.setHit(rs.getInt(6));
				dto.setFile(rs.getNString(7));
				dto.setDate(rs.getNString(8));
				dto.setId(rs.getNString(9));			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return dto;
	}

	public int BoardHit(int num) {
		String sql = "UPDATE BOARD SET BOARD_HIT = BOARD_HIT+1 WHERE BOARD_NUM = ? ";
		int hit = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			hit = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return hit;
	}

	public int MyBoardCount(String userNick) {
		String sql = "SELECT COUNT(*) FROM BOARD WHERE BOARD_NICK = ?";
		int boardCount = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setNString(1, userNick);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return boardCount;
	}

	public ArrayList<BoardDTO> MyBoardList(int startRow, int endRow, String userNick) {
		String sql = "SELECT * FROM (SELECT BOARDLIST.*, ROW_NUMBER() OVER(ORDER BY BOARD_NUM DESC) AS RN FROM(SELECT * FROM BOARD WHERE BOARD_NICK =?)BOARDLIST) WHERE RN BETWEEN ? AND ?";
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, userNick);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			System.out.println(userNick + startRow + endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BoardDTO dto = new BoardDTO();
				dto.setNick(rs.getNString(1));
				dto.setNum(rs.getInt(2));
				dto.setSubject(rs.getNString(3));
				dto.setTitle(rs.getNString(4));
				dto.setContent(rs.getNString(5));
				dto.setHit(rs.getInt(6));
				dto.setFile(rs.getNString(7));
				dto.setDate(rs.getNString(8));
				dto.setId(rs.getNString(9));
				
			
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
	
		return list;
	}
	

	public int BoardCategoryCount(String subject) {
		String sql = "SELECT COUNT(*) FROM BOARD WHERE BOARD_SUBJECT = ?";
		int boardCount = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setNString(1, subject);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return boardCount;
	}

	public ArrayList<BoardDTO> BoardCategory(int startRow, int endRow, String subject) {
		String sql = "SELECT * FROM (SELECT BOARDLIST.*, ROW_NUMBER() OVER(ORDER BY BOARD_NUM DESC) AS RN FROM(SELECT * FROM BOARD WHERE BOARD_SUBJECT =?)BOARDLIST) WHERE RN BETWEEN ? AND ?";
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, subject);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rs = pstmt.executeQuery();
			System.out.println(subject + startRow + endRow);
			while(rs.next()) {
				
				BoardDTO dto = new BoardDTO();
				dto.setNick(rs.getNString(1));
				dto.setNum(rs.getInt(2));
				dto.setSubject(rs.getNString(3));
				dto.setTitle(rs.getNString(4));
				dto.setContent(rs.getNString(5));
				dto.setHit(rs.getInt(6));
				dto.setFile(rs.getNString(7));
				dto.setDate(rs.getNString(8));
				dto.setId(rs.getNString(9));
				
			
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
	
		return list;
	}

	public int BoardHitCount() {
		String sql = "SELECT COUNT(*) FROM (SELECT BOARDLIST.*, ROW_NUMBER() OVER(ORDER BY BOARD_NUM DESC) AS RN FROM(SELECT * FROM BOARD WHERE BOARD_STATE =0)BOARDLIST)";
		
		int boardCount = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return boardCount;
	}

	public ArrayList<BoardDTO> BoardHit(int startRow, int endRow) {
		String sql = "SELECT * FROM (SELECT BOARDLIST.*, ROW_NUMBER() OVER(ORDER BY BOARD_HIT DESC) AS RN FROM(SELECT * FROM BOARD WHERE BOARD_STATE =0)BOARDLIST) WHERE RN BETWEEN ? AND ?";
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				
				dto.setNick(rs.getNString(1));
				dto.setNum(rs.getInt(2));
				dto.setSubject(rs.getNString(3));
				dto.setTitle(rs.getNString(4));
				dto.setContent(rs.getNString(5));
				dto.setHit(rs.getInt(6));
				dto.setFile(rs.getNString(7));
				dto.setDate(rs.getNString(8));
				dto.setId(rs.getNString(9));
				
			
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
	
		return list;
	}
	
	public int BoardHitCategoryCount(String subject) {
		String sql = "SELECT COUNT(*) FROM (SELECT BOARDLIST.*, ROW_NUMBER() OVER(ORDER BY BOARD_NUM DESC) AS RN FROM(SELECT * FROM BOARD WHERE BOARD_SUBJECT = ?)BOARDLIST)";
		int boardCount = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setNString(1, subject);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return boardCount;
	}

	public ArrayList<BoardDTO> BoardHitCategory(int startRow, int endRow, String subject) {
		String sql = "SELECT * FROM (SELECT BOARDLIST.*, ROW_NUMBER() OVER(ORDER BY BOARD_HIT DESC) AS RN FROM(SELECT * FROM BOARD WHERE BOARD_SUBJECT = ?)BOARDLIST) WHERE RN BETWEEN ? AND ?";
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, subject);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rs = pstmt.executeQuery();
			System.out.println(subject + startRow + endRow);
			while(rs.next()) {
				
				BoardDTO dto = new BoardDTO();
				dto.setNick(rs.getNString(1));
				dto.setNum(rs.getInt(2));
				dto.setSubject(rs.getNString(3));
				dto.setTitle(rs.getNString(4));
				dto.setContent(rs.getNString(5));
				dto.setHit(rs.getInt(6));
				dto.setFile(rs.getNString(7));
				dto.setDate(rs.getNString(8));
				dto.setId(rs.getNString(9));
				
			
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
	
		return list;
	}


	public int modify(BoardDTO board, int bnum) {
		String sql = "UPDATE BOARD SET BOARD_SUBJECT=?, BOARD_TITLE =?, BOARD_CONTENT=?, BOARD_FILE=? WHERE BOARD_NUM=?";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, board.getSubject());
			pstmt.setNString(2, board.getTitle());
			pstmt.setNString(3, board.getContent());
			pstmt.setNString(4, board.getFile());
			pstmt.setInt(5, bnum);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int getCount(String search2) {
		String sql = "SELECT COUNT(*) FROM (SELECT BOARDLIST.*, ROW_NUMBER() OVER(ORDER BY BOARD_NUM DESC) AS RN FROM(SELECT * FROM BOARD WHERE BOARD_TITLE LIKE '%'||?||'%')BOARDLIST)";

		int boardCount = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setNString(1, search2);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardCount = rs.getInt(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return boardCount;
	}
	
	public ArrayList<BoardDTO> search(String search2, int startRow, int endRow) {
	String sql = "SELECT * FROM (SELECT BOARDLIST.*, ROW_NUMBER() OVER(ORDER BY BOARD_NUM DESC) AS RN FROM(SELECT * FROM BOARD WHERE BOARD_TITLE LIKE '%'||?||'%')BOARDLIST)WHERE RN BETWEEN ? AND ?";
	ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();

	try {
		pstmt = con.prepareStatement(sql);
		pstmt.setNString(1, search2);
		pstmt.setInt(2, startRow);
		pstmt.setInt(3, endRow);
		
		
		rs = pstmt.executeQuery();
		
		
		while(rs.next()) {
			
			BoardDTO dto = new BoardDTO();

			dto.setNick(rs.getNString(1));
			dto.setNum(rs.getInt(2));
			dto.setSubject(rs.getNString(3));
			dto.setTitle(rs.getNString(4));
			dto.setContent(rs.getNString(5));
			dto.setHit(rs.getInt(6));
			dto.setFile(rs.getNString(7));
			dto.setDate(rs.getNString(8));
			dto.setId(rs.getNString(9));
			
		
			list.add(dto);
		}
	} catch (SQLException e) {
		e.printStackTrace();
		
	} finally {
		close(pstmt);
		close(rs);
	}
		return list;
	}
	//========================코멘트 쓰기 메서드 시작=========================
	public int CommentWrite(CommentsDTO comments) {
		System.out.println("3.dao");
		System.out.println(comments.getComments());
		System.out.println(comments.getId());
		System.out.println(comments.getNick());


		String sql = "INSERT INTO COMMENTS VALUES (COMMENT_NUM.NEXTVAL,?,SYSDATE,?,?,?)";

		int writeResult = 0;
		try {
			System.out.println("4.DB");
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, comments.getComments());
			pstmt.setString(2, comments.getId());
			pstmt.setString(3, comments.getNick());
			pstmt.setInt(4, comments.getBoardNum());
			writeResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		System.out.println(comments.getNick());
		System.out.println(comments.getId());
		System.out.println(comments.getNick());
		System.out.println("5.dao : " + writeResult);
		return writeResult;
	}
	//----------------------------코멘트 쓰기 메서드 끝---------------------------------------
	
	//============================코멘트 리스트 메서드 시작====================================
	public ArrayList<CommentsDTO> CommentList(int num) {
		String sql = "SELECT * FROM COMMENTS WHERE COMMENT_BOARD_NUM = ?";
		ArrayList<CommentsDTO> commentlist = new ArrayList<CommentsDTO>();

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				CommentsDTO comment =  new CommentsDTO();

				comment.setNum(rs.getInt(1));
				comment.setComments(rs.getString(2));
				comment.setTime(rs.getString(3));
				comment.setId(rs.getString(4));
				comment.setNick(rs.getString(5));
				comment.setBoardNum(rs.getInt(6));

				commentlist.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return commentlist;

	}
	//----------------------------코멘트 리스트 메서드 끝-----------------------------
	
	//=======================코멘트 삭제 메서드 시작=======================
	public int CommentDel(int commentNum) {
		int delResult = 0;
		String sql = "DELETE COMMENTS WHERE COMMENT_NUM=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commentNum);
			delResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		System.out.println("boardDAO :" + delResult);
		return delResult;
	}
	//----------------------코멘트 삭제 메서드 끝-----------------------------------

	//=======================코멘트 이전 댓글 보여주는 메서드 시작=================
	public void CommentMoCo(CommentsDTO comment) {
		String sql = "SELECT * FROM COMMENTS WHERE COMMENT_NUM = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comment.getNum());

			rs = pstmt.executeQuery();

			if(rs.next()) {
				comment.setNum(rs.getInt(1));
				comment.setComments(rs.getString(2));
				comment.setTime(rs.getString(3));
				comment.setId(rs.getString(4));
				comment.setNick(rs.getString(5));
				comment.setBoardNum(rs.getInt(6));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
	}
	//------------------------코멘트 이전 댓글 보여주는 메서드 끝----------------------
	
	
	//=====================코멘트 수정하는 메서드 시작==========================
	public int updateComment(CommentsDTO comment) {
		String sql = "UPDATE COMMENTS SET COMMENT_NUM=?, COMMENT_COMMENT=?, COMMENT_TIME=SYSDATE, COMMENT_ID=?, COMMENT_NICK=?, COMMENT_BOARD_NUM=? WHERE COMMENT_NUM=?";
		int upResult = 0;
		System.out.println("3차 검사");
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, comment.getNum());
			pstmt.setString(2, comment.getComments());
			pstmt.setString(3, comment.getId());
			pstmt.setString(4, comment.getNick());
			pstmt.setInt(5, comment.getBoardNum());
			pstmt.setInt(6, comment.getNum());
			upResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return upResult;
	}
	//-------------------------코멘트 수정하는 메서드 끝-------------------------------------




}
