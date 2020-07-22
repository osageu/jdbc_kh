package com.kh.controller;
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
			new MemberMenu().displaySuccess("성공");
		} else{
			new MemberMenu().displayFail("실패");
		}
		return result;
		
	}
	
}
