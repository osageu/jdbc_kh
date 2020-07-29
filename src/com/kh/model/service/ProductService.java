package com.kh.model.service;
import static com.kh.common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.ArrayList;

import com.kh.model.dao.ProductDao;
import com.kh.model.vo.Product;

public class ProductService {
	
	// field
	ProductDao pd = new ProductDao();
	
	// method
	public ArrayList<Product> searchAll() {
		Connection conn = getConnection();
		ArrayList<Product> list = pd.searchAll(conn);
		close(conn);
		return list;
	}

	public int addProduct(Product p) {
		Connection conn = getConnection();
		int result = pd.addProduct(conn, p);
		close(conn);
		return result;
	}

	public int updateProduct(String id, int stock) {
		Connection conn = getConnection();
		int result = pd.updateProduct(conn, id, stock);
		if (result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteProduct(String id) {
		Connection conn = getConnection();
		int result = pd.deleteProduct(conn, id);
		if (result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public ArrayList<Product> searchProduct(String name) {
		Connection conn = getConnection();
		ArrayList<Product> list = pd.searchProduct(conn, name);
		close(conn);
		return list;
	}
}
