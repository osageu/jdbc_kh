package com.kh.model.dao;
import java.sql.*;
import java.util.ArrayList;
import com.kh.model.vo.Member;
import static com.kh.common.JDBCTemplate.*;
public class MemberDao {
	
	public int insertMember(Connection conn, Member m) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USER_NO.NEXTVAL, ?,?,?,?,?,?,?,?,?, SYSDATE)";
		try {
			pstmt = conn.prepareStatement(sql); // 여기서 예외 발생 가능 --> Null
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPw());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public ArrayList<Member> searchAll(Connection conn) {
		
		ArrayList<Member> list = new ArrayList<>();
		Statement stmt = null;
		ResultSet rset = null;
		String sql = "SELECT * FROM MEMBER ORDER BY 1";
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			while(rset.next()) {
				Member m = new Member();
				m.setUserNo(rset.getInt(1));
				m.setUserId(rset.getString(2));
				m.setUserPw(rset.getString(3));
				m.setUserName(rset.getString(4));
				m.setGender(rset.getString(5));
				m.setAge(rset.getInt(6));
				m.setEmail(rset.getString(7));
				m.setPhone(rset.getString(8));
				m.setAddress(rset.getString(9));
				m.setHobby(rset.getString(10));
				m.setEnrollDate(rset.getDate(11));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return list;
	}
	
	public Member searchId(Connection conn, String userId) {
		
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "SELECT * FROM MEMBER WHERE USER_ID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(rset.getInt(1),
										 rset.getString(2),
										 rset.getString(3),
										 rset.getString(4),
										 rset.getString(5),
										 rset.getInt(6),
										 rset.getString(7),
										 rset.getString(8),
										 rset.getString(9),
										 rset.getString(10),
										 rset.getDate(11));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return m;
		
	}
	
	public ArrayList<Member> searchName(Connection conn, String userName){
		
		ArrayList<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null; // '%USER_NAME%'
		String sql = "SELECT * FROM MEMBER WHERE USER_NAME LIKE ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+userName+"%");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = new Member();
				m.setUserNo(rset.getInt(1));
				m.setUserId(rset.getString(2));
				m.setUserPw(rset.getString(3));
				m.setUserName(rset.getString(4));
				m.setGender(rset.getString(5));
				m.setAge(rset.getInt(6));
				m.setEmail(rset.getString(7));
				m.setPhone(rset.getString(8));
				m.setAddress(rset.getString(9));
				m.setHobby(rset.getString(10));
				m.setEnrollDate(rset.getDate(11));
				list.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int updateMember(Connection conn, String a, String b, String c, String d) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBER SET USER_PW = ?,"
													 + "EMAIL = ?,"
													 + "PHONE = ? WHERE USER_ID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b);
			pstmt.setString(2, c);
			pstmt.setString(3, d);
			pstmt.setString(4, a);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteMember(Connection conn, String id, String pw) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM MEMBER WHERE USER_ID = ? AND USER_PW = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return result;
		
	}
	
	public Member login (Connection conn, String id, String pw) {
		
		Member m = new Member();
		Statement stmt = null;
		ResultSet rset = null;
		String sql = "SELECT * FROM MEMBER WHERE USER_ID = '" + id +"' AND "
																	+ "USER_PW = '" + pw + "'";
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			if (rset.next()) {
				m.setUserNo(rset.getInt(1));
				m.setUserId(rset.getString(2));
				m.setUserPw(rset.getString(3));
				m.setUserName(rset.getString(4));
				m.setGender(rset.getString(5));
				m.setAge(rset.getInt(6));
				m.setEmail(rset.getString(7));
				m.setPhone(rset.getString(8));
				m.setAddress(rset.getString(9));
				m.setHobby(rset.getString(10));
				m.setEnrollDate(rset.getDate(11));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return m;
	}
	
}










