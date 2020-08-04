package com.kh.view;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import com.kh.controller.ProductController;
import com.kh.model.vo.Product;
import com.kh.model.vo.ProductIO;

public class ProductMenu {
	
	// field
	private Scanner sc = new Scanner(System.in);
	ProductController pc = new ProductController();
	
	// method
	public void mainMenu() {
		while(true) {
			System.out.println("\n==== MainMenu ====");
			System.out.println("1. 전체 조회");
			System.out.println("2. 상품 추가");
			System.out.println("3. 상품 수정(상품id로 조회하고 수정)");
			System.out.println("4. 상품 삭제(상품id로 조회하고 삭제)");
			System.out.println("5. 상품 검색(상품 이름으로 키워드 검색)");
			System.out.println("6. 상품 입출고 메뉴");
			System.out.println("0. 프로그램 종료하기");
			System.out.print("Select Menu : ");
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
			case 1 : searchAll(); break;
			case 2 : addProduct(); break;
			case 3 : updateProduct(); break;
			case 4 : deleteProduct(); break;
			case 5 : searchProduct(); break;
			case 6 : subMenu(); break;
			case 0 : System.out.println("Exit Program.."); return;
			default : System.out.println("Select Again~!!"); break;
			}
			
		}
		
	}
	
	public void searchAll() {
		ArrayList<Product> list = pc.searchAll();
		for (Product p : list) {
			System.out.println(p);
		}
	}
	
	public void addProduct() {
		Product p = new Product();
		System.out.print("상품 아이디 : ");
		p.setProductId(sc.nextLine());
		System.out.print("상품명 : ");
		p.setpName(sc.nextLine());
		System.out.print("상품 가격 : ");
		p.setPrice(sc.nextInt());
		sc.nextLine();
		System.out.print("상품 상세정보 : ");
		p.setDescription(sc.nextLine());
		System.out.print("재고 : ");
		p.setStock(sc.nextInt());
		sc.nextLine();
		int result = pc.addProduct(p);
		if (result > 0) {
			System.out.println("수행 성공!!");
		} else {
			System.out.println("수행 실패..");
		}
	}
	
	public void updateProduct() {
		System.out.print("수정할 상품 아이디 : ");
		String id = sc.nextLine();
		System.out.print("수정할 재고 : ");
		int stock = sc.nextInt();
		sc.nextLine();
		int result = pc.updateProduct(id, stock);
		if (result > 0) {
			System.out.println("수행 성공!!");
		} else {
			System.out.println("수행 실패..");
		}
	}
	
	public void deleteProduct() {
		System.out.print("삭제할 상품 아이디 : ");
		String id = sc.nextLine();
		int result = pc.deleteProduct(id);
		if (result > 0) {
			System.out.println("수행 성공!!");
		} else {
			System.out.println("수행 실패..");
		}
	}
	
	public void searchProduct() {
		System.out.print("검색할 상품 이름 : ");
		String name = sc.nextLine();
		ArrayList<Product> list = pc.searchProduct(name);
		for (Product p : list) {
			System.out.println(p);
		}
	}
	
	public void subMenu() {
		while(true) {
			System.out.println("\n=== 입출고 메뉴 ===");
			System.out.println("1. 전체 입출고 내역 조회");
			System.out.println("2. 입고 내역만 조회");
			System.out.println("3. 출고 내역만 조회");
			System.out.println("4. 상품 입고");
			System.out.println("5. 상품 출고");
			System.out.println("9. 메인 메뉴로 돌아가기");
			System.out.print("번호 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1 : searchIO(); break;
			case 2 : searchInput(); break;
			case 3 : searchOutput(); break;
			case 4 : productInput(); break;
			case 5 : productOutput(); break;
			case 9 : return; 
			default : System.out.println("다시 고르세요~"); break;
			}
		}
	}
	
	public void searchIO() {
		ArrayList<ProductIO> list = pc.searchIO();
		ArrayList<String> column = new ArrayList<>();
		column.add("입출고번호\t상품ID\t상품명\t\t입출고일\t\t입출고수량\t입출고상태");
		System.out.println();
		if (list.isEmpty()) {
			System.out.println("조회된 결과가 없습니다.");
		} else {
			System.out.println("=========================== 입출고 리스트 ===========================");
			System.out.println(column.get(0));
			for(int i = 0 ; i < list.size() ; i++) {
				System.out.println(list.get(i));
			}
		}
		
	}
	
	public void searchInput() {
		ArrayList<ProductIO> list = pc.searchInput();
		ArrayList<String> column = new ArrayList<>();
		column.add("입출고번호\t상품ID\t상품명\t\t입출고일\t\t입출고수량\t입출고상태");
		System.out.println();
		if (list.isEmpty()) {
			System.out.println("조회된 결과가 없습니다.");
		} else {
			System.out.println("=========================== 입출고 리스트 ===========================");
			System.out.println(column.get(0));
			for(int i = 0 ; i < list.size() ; i++) {
				System.out.println(list.get(i));
			}
		}
	}
	
	public void searchOutput() {
		ArrayList<ProductIO> list = pc.searchOutput();
		ArrayList<String> column = new ArrayList<>();
		column.add("입출고번호\t상품ID\t상품명\t\t입출고일\t\t입출고수량\t입출고상태");
		System.out.println();
		if (list.isEmpty()) {
			System.out.println("조회된 결과가 없습니다.");
		} else {
			System.out.println("=========================== 입출고 리스트 ===========================");
			System.out.println(column.get(0));
			for(int i = 0 ; i < list.size() ; i++) {
				System.out.println(list.get(i));
			}
		}
	}
	
	public void productInput() {
		System.out.println();
		System.out.print("입고할 상품ID : ");
		String id = sc.nextLine();
		System.out.print("입고할 수량 : ");
		int amount = sc.nextInt();
		sc.nextLine();
		int result = pc.productInput(id, amount);
		if (result > 0) {
			System.out.println("수정이 완료되었습니다!!!");
		} else {
			System.out.println("수정에 실패하였습니다..");
		}
	}
	
	public void productOutput() {
		System.out.println();
		System.out.print("출고할 상품ID : ");
		String id = sc.nextLine();
		System.out.print("출고할 수량 : ");
		int amount = sc.nextInt();
		sc.nextLine();
		int result = pc.productOutput(id, amount);
		if (result > 0) {
			System.out.println("수정이 완료되었습니다!!!");
		} else {
			System.out.println("수정에 실패하였습니다..");
		}
	}
	
}
