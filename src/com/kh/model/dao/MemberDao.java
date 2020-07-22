package com.kh.model.dao;
import java.sql.*;

import com.kh.model.vo.Member;

// Dao (Data Access Object) : DB 직접적으로 접근하는 담당
// 1) DB에 접속(연결)하여
// 2) SQL문 실행 및 결과 받기 (select문 : 조회된 ResultSet 반환, DML문 : 처리된 행의 개수(정수))
// 3) 만약 DML문이었다면 commit, rollback 하기
// 4) 결과 반환
public class MemberDao {
	
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
			System.out.println(result);
			
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
	
}
