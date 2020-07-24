package com.kh.view;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.MemberController;
import com.kh.model.vo.Member;

// View : 사용자가 보는 화면, 요청이 이뤄짐
public class MemberMenu {
	
	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();
	public void mainMenu() {
		
		while(true) {
			
			System.out.println("\n== 회원 관리 프로그램 ==");
			System.out.println("1. 회원 가입");
			System.out.println("2. 회원 전체 조회");
			System.out.println("3. 회원 아이디로 검색");
			System.out.println("4. 회원 이름으로 검색");
			System.out.println("5. 회원 정보 변경");
			System.out.println("6. 회원 탈퇴");
			System.out.println("0. 프로그램 종료");
			System.out.print("번호 선택 : ");
			int select = sc.nextInt();
			sc.nextLine();
			
			switch(select) {
			case 1 : insertMember(); break;
			case 2 : mc.selectList(); break;
			case 3 : String userId = inputMemberId();
						mc.selectByUserId(userId);
						break;
			case 4 : mc.selectByUserName(inputMemberName());
						break;
			case 5 : updateMember(); break;
			case 6 : deleteMember(); break;
			case 0 : System.out.println("♡~~Exit program~~♡"); return;
			default : System.out.println("Wrong Number. Select Again~♡");
			}
			
		}
		
	}
	
	public void insertMember() {
		
		System.out.println("\n==== 회원가입 ====");
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pw = sc.nextLine();
		System.out.print("이름 : ");
		String name = sc.nextLine();
		System.out.print("성별(M/F) : ");
		String gender = sc.nextLine().toUpperCase();
		System.out.print("나이 : ");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.print("이메일 : ");
		String email = sc.nextLine();
		System.out.print("전화번호(-빼고 입력) : ");
		String phone = sc.nextLine();
		System.out.print("주소 : ");
		String address = sc.nextLine();
		System.out.print("취미(,로 공백없이 나열) : ");
		String hobby = sc.nextLine();
		
		Member user = new Member(id, pw, name, gender, age, email, phone, address, hobby);
		
		mc.insertMember(user);
		
	}
	
	public void updateMember() {
		
		Member m = new Member();
		
		m.setUserId(inputMemberId());
		System.out.print("변경할 암호 : ");
		m.setUserPw(sc.nextLine());
		System.out.print("변경할 이메일 : ");
		m.setEmail(sc.nextLine());
		System.out.print("변경할 전화번호(-빼고 입력) : ");
		m.setPhone(sc.nextLine());
		System.out.print("변경할 주소 : ");
		m.setAddress(sc.nextLine());
		
		mc.updateMember(m);
		
	}
	
	public void deleteMember() {
		System.out.print("아이디를 입력하시오 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호를 입력하시오 : ");
		String pw = sc.nextLine();
		mc.deleteMember(id,pw);
	}

	public String inputMemberId() {
		System.out.print("회원 아이디 입력 : ");
		String userId = sc.nextLine();
		return userId;
	}
	public String inputMemberName() {
		System.out.print("회원 이름 입력 : ");
		String userName = sc.nextLine();
		return userName;
	}
	
	// 서비스 요청 처리 후 사용자가 보게될 응답화면
	// DML
	public void displaySuccess(String message) {
		System.out.println("서비스 요청 : " + message);
	}
	public void displayFail(String message) {
		System.out.println("서비스 요청 : " + message);
	}
	
	// SELECT
	public void displayNoData(String message) {
		System.out.println(message);
	}
	public void displayMemberList(ArrayList<Member> list) {
		System.out.println("\n 조회된 데이터는 다음과 같습니다.\n");
		for (Member a : list) {
			System.out.println(a);
		}
	}
	public void displayMemberList(Member m) {
		System.out.println("\n 조회된 데이터는 다음과 같습니다.\n");
		System.out.println(m);
	}
	
	
	
}
