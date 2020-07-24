package com.kh.controller;
import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

public class MemberController {
	
	public void insertMember(Member m) {
		
		int result = new MemberDao().insertMember(m);
		
		if (result > 0 ) {
			new MemberMenu().displaySuccess("성공적으로 회원가입 되었슴돠!");
		} else {
			new MemberMenu().displayFail("회원가입에 실패했슴돠!");
		}
		
	}
	
}
