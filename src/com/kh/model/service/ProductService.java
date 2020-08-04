package com.kh.model.service;
import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.model.dao.ProductDao;
import com.kh.model.vo.Product;
import com.kh.model.vo.ProductIO;

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
	
	// subMenu
	public ArrayList<ProductIO> searchIO() {
		Connection conn = getConnection();
		ArrayList<ProductIO> list = pd.searchIO(conn);
		close(conn);
		return list;
	}

	public ArrayList<ProductIO> searchInput() {
		Connection conn = getConnection();
		ArrayList<ProductIO> list = pd.searchInput(conn);
		close(conn);
		return list;

	}

	public ArrayList<ProductIO> searchOutput() {
		Connection conn = getConnection();
		ArrayList<ProductIO> list = pd.searchOutput(conn);
		close(conn);
		return list;
	}

	public int productInput(String id, int amount) {
		Connection conn = getConnection();
		int result = pd.productInput(conn, id, amount);
		if (result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int productOutput(String id, int amount) {
		Connection conn = getConnection();
		int result = pd.productOutput(conn, id, amount);
		if (result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
}
