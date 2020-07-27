package com.kh.model.service;
import java.sql.*;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;

public class MemberService {
	
	public int insertMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().insertMember(conn, m);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	public ArrayList<Member> searchAll() {
		
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Member> a = new MemberDao().searchAll(conn);
		JDBCTemplate.close(conn);
		return a;
	}
	
	public Member searchId(String userId) {
		
		Connection conn = JDBCTemplate.getConnection();
		Member m = new MemberDao().searchId(conn, userId);
		JDBCTemplate.close(conn);
		return m;
		
	}
	
	public ArrayList<Member> searchName(String userName) {
		
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Member> list = new MemberDao().searchName(conn, userName);
		JDBCTemplate.close(conn);
		return list;
		
	}
	
	public int updateMember(String a, String b, String c, String d) {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().updateMember(conn, a,b,c,d);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
		
	}
	
	public int deleteMember(String id, String pw) {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().deleteMember(conn, id, pw);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
		
	}
	
	public Member login(String id, String pw) {
		
		Connection conn = JDBCTemplate.getConnection();
		Member m = new MemberDao().login(conn, id, pw);
		JDBCTemplate.close(conn);
		return m;
		
	}
	
}










