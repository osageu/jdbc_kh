package com.kh.controller;
import java.util.ArrayList;
import com.kh.model.service.ProductService;
import com.kh.model.vo.Product;

public class ProductController {
	
	// field
	ProductService ps = new ProductService();
	
	// method
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
	
}
