package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.MemberDTO;

import static db.JdbcUtil.*;

public class MemberDAO {
	private static MemberDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	// getInstance메소드
	public static MemberDAO getInstance() {
		if (dao == null) {
			dao = new MemberDAO();
		}
		return dao;
	}

	// setConnection 메소드
	public void setConnection(Connection con) {
		this.con = con;
	}

	public int memberJoin(MemberDTO member) {
		String sql = "INSERT INTO MEMBERT VALUES(?,?,?,?,?,TO_DATE(?,'YYYY-MM-DD'),?,?,?,?,DEFAULT,1)";
		int result = 0;
		
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member.getUserId());
				pstmt.setString(2, member.getUserNick());
				pstmt.setString(3, member.getUserPw());
				pstmt.setString(4, member.getUserName());
				pstmt.setString(5, member.getUserTeam());
				pstmt.setString(6, member.getUserBirth());
				pstmt.setString(7, member.getUserGender());
				pstmt.setString(8, member.getUserPhone());
				pstmt.setString(9, member.getUserAdd());
				pstmt.setString(10, member.getUserEmail());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
		return result;
	}

	public String logIn(MemberDTO dto) {
		String sql = "SELECT * FROM MEMBERT WHERE USER_ID=? AND USER_PASSWORD=? AND USER_STATE='1'";
		String userNick = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, dto.getUserId());
			pstmt.setNString(2, dto.getUserPw());
			rs= pstmt.executeQuery();
			if (rs.next()) {
				userNick = rs.getNString(2);
				dto.setUserId(rs.getNString(1));
				dto.setUserNick(rs.getNString(2));
				dto.setUserLevel(rs.getNString(11));
			}else {userNick = null;}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return userNick;
	}
	public int memberDelete(String id, String nick) {
		String sql = "UPDATE MEMBERT SET USER_STATE ='0' WHERE USER_ID=? AND USER_NICK=?";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, id);
			pstmt.setNString(2, nick);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int memberModi(String id, MemberDTO member) {
		String sql ="UPDATE MEMBERT SET USER_NICK=?, USER_NAME=?,USER_TEAM=?,USER_GENDER=?,USER_PHONE=?, USER_ADDR=?, USER_EMAIL=?, USER_PASSWORD=? WHERE USER_ID=?";
		int result = 0;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, member.getUserNick());
			pstmt.setNString(2, member.getUserName());
			pstmt.setNString(3, member.getUserTeam());
			pstmt.setNString(4, member.getUserGender());
			pstmt.setNString(5, member.getUserPhone());
			pstmt.setNString(6, member.getUserAdd());
			pstmt.setNString(7, member.getUserEmail());
			pstmt.setNString(8, member.getUserPw());
			pstmt.setNString(9, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println(result);
		return result;
	}

	public int idCheck(String id) {
		String sql="SELECT * FROM MEMBERT WHERE USER_ID=?";
		int result = 0;
		try {


			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs=pstmt.executeQuery();

			if(rs.next()) {
				result=1;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		System.out.println(result);
		return result;
	}

	public int NickCheck(String nick) {
		String sql="SELECT * FROM MEMBERT WHERE USER_NICK=?";
		int result = 0;
		try {

			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, nick);
			rs=pstmt.executeQuery();

			if(rs.next()) {
				result=1;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return result;
	}
	public ArrayList<MemberDTO> memberList() {
		String sql = "SELECT * FROM MEMBERT WHERE USER_STATE='1'";
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setUserId(rs.getNString(1));
				dto.setUserNick(rs.getNString(2));
				dto.setUserName(rs.getNString(4));
				dto.setUserTeam(rs.getNString(5));
				dto.setUserBirth(rs.getNString(6).substring(0,10));
				dto.setUserGender(rs.getNString(7));
				dto.setUserPhone(rs.getNString(8));
				dto.setUserAdd(rs.getNString(9));
				dto.setUserEmail(rs.getNString(10));
				list.add(dto);
			}


		} catch (SQLException e) {

			e.printStackTrace();
		}
		return list;
	}

	public MemberDTO memberview(String id) {
		String sql ="SELECT * FROM MEMBERT WHERE USER_ID=? AND USER_STATE='1'";
		MemberDTO dto = new MemberDTO();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto.setUserId(rs.getNString(1));
				dto.setUserNick(rs.getNString(2));
				dto.setUserName(rs.getNString(4));
				dto.setUserTeam(rs.getNString(5));
				dto.setUserBirth(rs.getNString(6).substring(0,10));
				dto.setUserGender(rs.getNString(7));
				dto.setUserPhone(rs.getNString(8));
				dto.setUserAdd(rs.getNString(9));
				dto.setUserEmail(rs.getNString(10));
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		return dto;
	}
	public int history(MemberDTO dto, int login) {
		String sql="INSERT INTO HISTORY VALUES(DEFAULT,?,?)";
		int result = 0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, login);
			pstmt.setNString(2, dto.getUserId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return result;
	}

	public int history(String id, int logout) {
		String sql="INSERT INTO HISTORY VALUES(DEFAULT,?,?)";
		int result = 0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, logout);
			pstmt.setNString(2, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return result;
	
	}
}
