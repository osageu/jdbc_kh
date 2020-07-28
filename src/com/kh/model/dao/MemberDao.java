package com.kh.model.dao;
import static com.kh.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.model.vo.Member;
public class MemberDao {
	
	// field
	private Properties prop = new Properties();
	
	// constructor
	public MemberDao() { 
		// 사용자가 어떤 서비스 요청할 때마다 매 번 new MemberDao().XXX(); 한 것..
		// 따라서 query.properties 파일에 기록된 값을 매 번 실시간으로 prop 읽어들일 수 있는 구문을 여기서 기술!!
		try {
			prop.load(new FileReader("resources/query.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// method
	public int insertMember(Connection conn, Member m) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		//String sql = "INSERT INTO MEMBER VALUES(SEQ_USER_NO.NEXTVAL, ?,?,?,?,?,?,?,?,?, SYSDATE)";
		String sql = prop.getProperty("insertMember");
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
		String sql = prop.getProperty("searchAll");
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
		String sql = prop.getProperty("searchId");
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
		String sql = prop.getProperty("searchName");
		
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
		String sql = prop.getProperty("updateMember");
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
		String sql = prop.getProperty("deleteMember");
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
		
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("login");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rset = pstmt.executeQuery();
			if (rset.next()) {
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
										 rset.getDate(11)
										 );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return m;
	}
	
}
/*
 * 회원 탈퇴 정책이 바껴서 탈퇴 요청 시 Member 테이블로부터 삭제가 아닌	
 * DEL_FLAG 컬럼값을 기존에 'N'에서 'Y'로 변경!
 * 
 * 또한, 회원 조회 서비스 시 탈퇴한 회원들은 제외하고 조회하게끔!
 * 로그인 기능, 변경 기능도 안 되게 할 것..
 */









