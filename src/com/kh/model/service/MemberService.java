package com.kh.model.service;
import java.sql.*;
import java.util.ArrayList;
import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;
import static com.kh.common.JDBCTemplate.*;
public class MemberService {
	
	public int insertMember(Member m) {
		Connection conn = getConnection();
		int result = new MemberDao().insertMember(conn, m);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public ArrayList<Member> searchAll() {
		
		Connection conn = getConnection();
		ArrayList<Member> a = new MemberDao().searchAll(conn);
		close(conn);
		return a;
	}
	
	public Member searchId(String userId) {
		
		Connection conn = getConnection();
		Member m = new MemberDao().searchId(conn, userId);
		close(conn);
		return m;
		
	}
	
	public ArrayList<Member> searchName(String userName) {
		
		Connection conn = getConnection();
		ArrayList<Member> list = new MemberDao().searchName(conn, userName);
		close(conn);
		return list;
		
	}
	
	public int updateMember(String a, String b, String c, String d) {
		
		Connection conn = getConnection();
		int result = new MemberDao().updateMember(conn, a,b,c,d);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
		
	}
	
	public int deleteMember(String id, String pw) {
		
		Connection conn = getConnection();
		int result = new MemberDao().deleteMember(conn, id, pw);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
		
	}
	
	public Member login(String id, String pw) {
		
		Connection conn = getConnection();
		Member m = new MemberDao().login(conn, id, pw);
		close(conn);
		return m;
		
	}
	
}










