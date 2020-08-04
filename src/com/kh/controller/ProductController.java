package com.kh.controller;
import java.util.ArrayList;

import com.kh.model.service.ProductService;
import com.kh.model.vo.Product;
import com.kh.model.vo.ProductIO;

public class ProductController {
	
	// field
	ProductService ps = new ProductService();
	
	// method
	// mainMenu
	public ArrayList<Product> searchAll() {
		ArrayList<Product> list = ps.searchAll();
		return list;
	}
	public int addProduct(Product p) {
		int result = ps.addProduct(p);
		return result;
	}
	
	public int updateProduct(String id, int stock) {
		int result = ps.updateProduct(id, stock);
		return result;
	}
	
	public int deleteProduct(String id) {
		int result = ps.deleteProduct(id);
		return result;
	}
	
	public ArrayList<Product> searchProduct(String name) {
		ArrayList<Product> list = ps.searchProduct(name);
		return list;
	}
	
	// subMenu
	public ArrayList<ProductIO> searchIO() {
		ArrayList<ProductIO> list = ps.searchIO();
		return list;
	}
	
	public ArrayList<ProductIO> searchInput() {
		ArrayList<ProductIO> list = ps.searchInput();
		return list;
	}
	
	public ArrayList<ProductIO> searchOutput() {
		ArrayList<ProductIO> list = ps.searchOutput();
		return list;
	}
	
	public int productInput(String id, int amount) {
		int result = ps.productInput(id, amount);
		return result;
	}
	
	public int productOutput(String id, int amount) {
		int result = ps.productOutput(id, amount);
		return result;
	}
	
}
