package com.kh.view;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.ProductController;
import com.kh.model.vo.Product;

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
	
	
	
	
	
	
	
	
}
