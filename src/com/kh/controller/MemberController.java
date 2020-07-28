package com.kh.controller;
import java.util.ArrayList;
import com.kh.model.service.MemberService;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

public class MemberController {
	
	public void insertMember(Member m) {
		
		int result = new MemberService().insertMember(m);
		if (result > 0) {
			new MemberMenu().displaySuccess("회원가입 성공!!");
		} else {
			new MemberMenu().displayFail("회원가입 실패..");
		}
	}
	
	public void searchAll() {
		ArrayList<Member> list =new MemberService().searchAll();
		if (list.isEmpty()) {
			new MemberMenu().displayFail("조회된 데이터가 없습니다.");
		} else {
			new MemberMenu().displayMemberList(list);
		}
	}
	
	public void searchId(String userId) {
		Member m = new MemberService().searchId(userId);
		if(m == null) {
			new MemberMenu().displayNoData(userId + "에 해당되는 조회결과 없음");
		} else {
			new MemberMenu().displayMember(m);
		}
	}
	
	public void searchName(String userName) {
		ArrayList<Member> list = new MemberService().searchName(userName);
		if(list.isEmpty()) {
			new MemberMenu().displayNoData(userName + "에 해당되는 조회결과 없음");
		} else {
			new MemberMenu().displayMemberList(list);
		}
	}
	
	public void updateMember(String a, String b, String c, String d) {
		int result = new MemberService().updateMember(a,b,c,d);
		if(result > 0) {
			new MemberMenu().displaySuccess("성공");
		} else {
			new MemberMenu().displayFail("실패");
		}
	}
	
	public void deleteMember(String id, String pw) {
		int result = new MemberService().deleteMember(id, pw);
		if(result > 0) {
			new MemberMenu().displaySuccess("성공");
		} else {
			new MemberMenu().displayFail("실패");
		}
	}
	
	public void login(String id, String pw) {
		Member m = new MemberService().login(id, pw);
		if(m == null) {
			new MemberMenu().displayNoData("조회 결과 없음");
		} else {
			new MemberMenu().displayMember(m);
		}
	}
	
}
