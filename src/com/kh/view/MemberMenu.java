package com.kh.view;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.MemberController;
import com.kh.model.vo.Member;

public class MemberMenu {
	
	// field
	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();
	
	// method
	public void mainMenu() {
		
		while (true) {
			
			System.out.println("\n==== 회원 관리 프로그램 ====");
			System.out.println("1. 회원 가입");
			System.out.println("2. 회원 전체 조회");
			System.out.println("3. 회원 아이디로 검색");
			System.out.println("4. 회원 이름(키워드)로 검색");
			System.out.println("5. 회원 정보 변경");
			System.out.println("6. 회원 탈퇴");
			System.out.println("7. 로그인");
			System.out.println("0. 프로그램 종료");
			System.out.print("메뉴 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1 : insertMember(); break;
			case 2 : searchAll(); break;
			case 3 : searchId(); break;
			case 4 : searchName(); break;
			case 5 : updateMember(); break;
			case 6 : deleteMember(); break;
			case 7 : login(); break;
			case 0 : System.out.print("정말로 끝내겠습니까? : ( y / n )");
						if (sc.nextLine().toUpperCase().charAt(0) == 'Y') {
							System.out.println("프로그램을 종료합니다.");
							return;
						} else {
							break;
						}
			default : System.out.println("잘못된 번호입니다. 다시 입력하세요");
			}
			
		}
		
	}
	
	public void searchAll() {
		ArrayList<Member> list = mc.searchAll();
		System.out.println("\n************** 전체 회원 조회 결과 **************");
		for (Member a : list) {
			System.out.println(a);
		}
	}
	
	public void searchId() {
		ArrayList<Member> list = mc.searchId(inputMemberId());
		System.out.println("\n************** 아이디 조회 결과 **************");
		for (Member a : list) {
			System.out.println(a);
		}
	}
	
	public void searchName() {
		ArrayList<Member> list = mc.searchName(inputMemberName());
		System.out.println("\n************** 회원 정보 조회 결과 **************");
		for (Member a : list) {
			System.out.println(a);
		}
	}
	
	// 사용자에게 값을 입력받는 화면들
	// 1. 회원 가입
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
		
		mc.insertMember(new Member(id, pw, name, gender, age, email, phone, address, hobby));
		
	}
	// 2. 회원 아이디 입력
	public String inputMemberId() {
		System.out.print("\n회원 아이디 입력 : ");
		return sc.nextLine();
	}
	// 3. 회원 이름 입력
	public String inputMemberName() {
		System.out.print("\n회원 이름 입력 : ");
		return sc.nextLine();
	}
	// 4. 회원 정보 수정
	public void updateMember() {
		System.out.println("\n==== 회원 정보 변경 ====");
		Member m = new Member();
		m.setUserId(inputMemberId());
		System.out.print("변경할 비밀번호 : ");
		m.setUserPw(sc.nextLine());
		System.out.print("변경할 이메일 : ");
		m.setEmail(sc.nextLine());
		System.out.print("변경할 전화번호(-제외) : ");
		m.setPhone(sc.nextLine());
//		System.out.print("변경할 주소 : ");
//		m.setAddress(sc.nextLine());
		
		mc.updateMember(m.getUserId(), m.getUserPw(), m.getEmail(), m.getPhone());
	}
	
	// 5. 회원탈퇴
	public void deleteMember() {
		
		System.out.println("\n*************** 회원 탈퇴 ****************\n");
		System.out.print("탈퇴할 아이디 : ");
		String id = sc.nextLine();
		System.out.print("탈퇴할 비밀번호 : ");
		String pw = sc.nextLine();
		
		mc.deleteMember(id, pw);
	}
	
	// 6. 로그인
	public void login() {

		System.out.println("\n*************** 로그인 ****************\n");
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pw = sc.nextLine();

		ArrayList<Member> a = mc.login(id, pw);
		for (Member e : a) {
			System.out.println(e);
		}
	}
	
	
	// 사용자가 서비스 요청 후 보게될 화면들
	// 1. 서비스 요청 성공
	public void displaySuccess(String message) {
		System.out.println("\n서비스 요청 성공 : " + message);
	}
	// 2. 서비스 요청 실패
	public void displayFail(String message) {
		System.out.println("\n서비스 요청 실패 : " + message);
	}
	// 3. 조회 결과 없을 때
	public void displayNoData(String message) {
		System.out.println("\n" + message);
	}
	// 4. 한 행만 조회되었을 때
	public void displayMember(Member m) {
		System.out.println("\n조회된 회원 정보는 다음과 같습니다.");
		System.out.println(m);
	}
	// 5. 여러 행이 조회되었을 대
	public void displayMemberList(ArrayList<Member> list) {
		System.out.println("\n조회된 회원 정보는 다음과 같습니다.");
		for (Member m : list) {
			System.out.println(m);
		}
	}
}
