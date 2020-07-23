package com.kh.controller;
import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

// Controller : View를 통해 요청한 기능 처리
// 				   해당 메소드로 전달된 데이터를 가공처리한 후 Dao로 전달 (Dao 메소드 호출)
// 				   Dao로 부터 반환받은 결과에 따라 View를 결정
public class MemberController {
	
	private MemberDao md = new MemberDao();
	/**
	 * @param user
	 */
	public int insertMember(Member user) {
		
		int result = md.insertMember(user);
		if(result > 0) {
			new MemberMenu().displaySuccess("회원 가입 성공");
		} else{
			new MemberMenu().displayFail("회원 가입 실패");
		}
		return result;
		
	}
	
	// 사용자의 전체 회원 조회 요청 처리
	public ArrayList<Member> selectList() {
		ArrayList<Member> list = md.selectList();
		if (list.isEmpty() == true) {
			new MemberMenu().displayNoData("조회된 데이터가 없습니다.");
		} else {
			new MemberMenu().displayMemberList(list);
		}
		return list;
	}
	
	// 사용자의 아이디 검색 요청 처리
	public void selectByUserId(String userId) {
		
		Member m = md.selectByUserId(userId);
		if (m == null) {
			new MemberMenu().displayNoData("조회된 데이터가 없습니다.");
		} else {
			new MemberMenu().displayMemberList(m);
		}
	}
	// 사용자의 이름 검색 요청 처리
	public void selectByUserName(String userName) {
		
		ArrayList<Member> m = md.selectByUserName(userName);
		if (m.isEmpty() == true) {
			new MemberMenu().displayNoData(userName + "으로 조회된 데이터가 없습니다.");
		} else {
			new MemberMenu().displayMemberList(m);
		}
	}
	
	// 회원 정보 변경
	public void updateMember(Member m) {
		
		int result = md.updateMember(m);
		if (result > 0) {
			new MemberMenu().displaySuccess("회원 정보 변경 성공");
		} else {
			new MemberMenu().displayFail("회원 정보 변경 실패");
		}
		
	}
	
	// 회원 탈퇴
	public void deleteMember() {
		
		int result = md.deleteMember();
		if (result > 0) {
			new MemberMenu().displaySuccess("회원 삭제 성공");
		} else {
			new MemberMenu().displayFail("회원 삭제 실패");
		}
		
	}
	
}
