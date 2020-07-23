package com.kh.model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.model.vo.Member;

// Dao (Data Access Object) : DB 직접적으로 접근하는 담당
// 1) DB에 접속(연결)하여
// 2) SQL문 실행 및 결과 받기 (select문 : 조회된 ResultSet 반환, DML문 : 처리된 행의 개수(정수))
// 3) 만약 DML문이었다면 commit, rollback 하기
// 4) 결과 반환
public class MemberDao {
	
	// 사용자의 회원가입 요청 
	public int insertMember(Member m) {
		
		// 처리된 결과 (처리된 행의 개수)를 받아줄 변수
		int result = 0;
		// DB의 연결 정보를 담는 객체 선언
		Connection conn = null;
		// SQL문을 전송해서 실행 후 결과
		Statement stmt = null;
		
		// 실행할 SQL문 --> 끝에 세미콜론 있으면 안 됨
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USER_NO.NEXTVAL, "
						+ "'" + m.getUserId() + "', "
						+ "'" + m.getUserPw() + "', "
						+ "'" + m.getUserName() + "', "
						+ "'" + m.getGender() + "', "
						+ m.getAge() + ", "
						+ "'" + m.getEmail() + "', "
						+ "'" + m.getPhone() + "', "
						+ "'" + m.getAddress() + "', "
						+ "'" + m.getHobby() + "', SYSDATE)"
						;
		// System.out.println(sql);
		
		
		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection 객체 생성 (DB에 연결 --> url, 계정명,계정비밀번호)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "JDBC", "JDBC");
			
			// 3) Statement 객체 생성
			stmt = conn.createStatement();
			
			// 4, 5) SQL문 해당 DB에 전달 후 실행 --> 결과 받기
			result = stmt.executeUpdate(sql); // --> insert, update, delete일 경우 executeUpdate method 사용
			
			// 6-2) 트랜잭션 처리
			if (result > 0) { // 성공 --> commit
				conn.commit();
			} else { // 실패 --> rollback
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 7) 다 쓴 JDBC용 객체 자원 반납(close) 단, 생성된 역순으로
			try{
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}
	
	// 사용자의 전체 회원 조회 요청 처리
	public ArrayList<Member> selectList() {
		
		// 0) 필요한 변수 세팅
		// 처리된 결과(조회된 회원들 == 여러 행)들을 담아줄 ArrayList생성
		ArrayList<Member> list = new ArrayList<>();
		
		// DB의 연결정보를 담는 객체 선언
		Connection conn = null;
		
		// SQL문 실행 후 결과받는 객체 선언
		Statement stmt = null;
		
		// SQL Select문 실행 후 결과값들이 실질적으로 담길 ResultSet 객체 선언
		ResultSet rset = null;
		
		// 실행할 sql문
		String sql = "SELECT * FROM MEMBER"; // 세미콜론 x
		
		// ---------------------------------------------------------------------------------------------
		
		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 외부 제공 class from ojdbc6.jar
			
			// 2) Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "JDBC", "JDBC");
			
			// 3) Statement 객체 생성
			stmt = conn.createStatement();
			
			// 4, 5) SQL문 전송해서 실행 후 결과받기
			rset = stmt.executeQuery(sql); // select문은 executeQuery 메소드 이용
											// insert, update, delete문은 executeUpdate 메소드 이용
			
			// 6-1) ResultSet에 담겨있는 데이터 하나씩 하나씩 뽑아서 주섬주섬담기
			while(rset.next()) { // 행을 하나씩 넘기는 method. 더 이상 넘길 행이 없으면 False
				
				Member m = new Member();
				
				// 현재 rset의 커서가 가리키고 있는 행의 데이터들 뽑아서 담기
				m.setUserNo(rset.getInt("USER_NO"));
				m.setUserId(rset.getString("USER_ID"));
				m.setUserPw(rset.getString("USER_PW"));
				m.setUserName(rset.getString("USER_NAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));
				
				// ArrayList에 회원 한 행씩 추가
				list.add(m);
				
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 7) 다 쓴 JDBC용 객체 자원반납 (생성 역순)
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return list;
	}
	
	// 사용자의 아이디 검색 요청 처리
	public Member selectByUserId(String userId) {
		
		Member m = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		String sql = "SELECT * FROM MEMBER WHERE USER_ID = '" + userId + "'";
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC", "JDBC");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			if(rset.next()) {
				m = new Member();
				m.setUserNo(rset.getInt("USER_NO"));
				m.setUserId(rset.getString("USER_ID"));
				m.setUserPw(rset.getString("USER_PW"));
				m.setUserName(rset.getString("USER_NAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));
				
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return m;
		
	}
	
	// 사용자의 이름 검색 요청 처리
	public ArrayList<Member> selectByUserName(String userName) {
		
		ArrayList<Member> m = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		String sql = "SELECT * FROM MEMBER WHERE USER_NAME LIKE '%" + userName + "%'";
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			while(rset.next()) {
				
				m.add(new Member(rset.getInt("USER_NO"),
											rset.getString("USER_ID"),
											rset.getString("USER_PW"),
											rset.getString("USER_NAME"),
											rset.getString("GENDER"),
											rset.getInt("AGE"),
											rset.getString("EMAIL"),
											rset.getString("PHONE"),
											rset.getString("ADDRESS"),
											rset.getString("HOBBY"),
											rset.getDate("ENROLLDATE")));
				
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return m;
		
	}
	
	// 회원 정보 변경
	public int updateMember(Member m) {
		
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		String sql = "UPDATE MEMBER "
							+ "SET USER_PW = '" + m.getUserPw() + "', "
							+ "EMAIL = '" + m.getEmail() + "', "
							+ "PHONE = '" + m.getPhone() + "', "
							+ "ADDRESS = '" + m.getAddress() + "' "
							+ "WHERE USER_ID = '" + m.getUserId() + "'";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC"	);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			if (result > 0) { // 성공 --> commit
				conn.commit();
			} else { // 실패 --> rollback
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try{
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// 회원 탈퇴
	public int deleteMember() {
		
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		String sql = "DELETE FROM ~";
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try{
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
}



