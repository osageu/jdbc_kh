package com.kh.controller;
import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

public class MemberController {
	
	MemberDao md = new MemberDao();
	public void insertMember(Member m) {
		
		int result = new MemberDao().insertMember(m);
		
		if (result > 0 ) {
			new MemberMenu().displaySuccess("성공적으로 회원가입 되었슴돠!");
		} else {
			new MemberMenu().displayFail("회원가입에 실패했슴돠!");
		}
		
	}
	
	public ArrayList<Member> searchAll() {
		return md.searchAll();
	}
	
	public ArrayList<Member> searchId(String id) {
		ArrayList<Member> list = md.searchAll();
		ArrayList<Member> copy = new ArrayList<>();
		for ( int i = 0 ; i < list.size() ; i++) {
			if (list.get(i).getUserId().equals(id)) {
				copy.add(list.get(i));
			}
		}
		return copy;
	}
	
	public ArrayList<Member> searchName(String name) {
		ArrayList<Member> list = md.searchAll();
		ArrayList<Member> copy = new ArrayList<>();
		for ( int i = 0 ; i < list.size() ; i++) {
			if (list.get(i).getUserName().contains(name)) {
				copy.add(list.get(i));
			}
		}
		return copy;
	}
	
	public void updateMember(String id, String pw, String email, String phone) {
		
		int result = md.updateMember(id, pw, email, phone);
		if (result > 0) {
			new MemberMenu().displaySuccess("변경 성공");
		} else {
			new MemberMenu().displayFail("변경 실패");
		}
		
	}
	
	public void deleteMember(String id, String pw) {
		int result = md.deleteMember(id, pw);
		if (result > 0) {
			new MemberMenu().displaySuccess("삭제 성공");
		} else {
			new MemberMenu().displayFail("삭제 실패");
		}
	}
	
	public ArrayList<Member> login(String id, String pw) {
		ArrayList<Member> list = md.searchAll();
		ArrayList<Member> copy = new ArrayList<>();
		for ( int i = 0 ; i < list.size() ; i++) {
			if (list.get(i).getUserId().equals(id) && list.get(i).getUserPw().equals(pw)) {
				copy.add(list.get(i));
			}
		}
		return copy;
	}
	
}













