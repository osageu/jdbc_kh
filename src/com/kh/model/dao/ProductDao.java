package com.kh.model.dao;
import static com.kh.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.model.vo.Product;
import com.kh.model.vo.ProductIO;
public class ProductDao {
	
	// field
	Properties prop = new Properties();
	public ProductDao() {
		try {
			prop.load(new FileReader("resources/query.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// method
	public ArrayList<Product> searchAll(Connection conn) {
		ArrayList<Product> list = new ArrayList<>();
		Statement stmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("READ");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			while(rset.next()) {
				Product p = new Product();
				p.setProductId(rset.getString(1));
				p.setpName(rset.getString(2));
				p.setPrice(rset.getInt(3));
				p.setDescription(rset.getString(4));
				p.setStock(rset.getInt(5));

				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return list;
	}

	public int addProduct(Connection conn, Product p) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("ADD");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getProductId());
			pstmt.setString(2, p.getpName());
			pstmt.setInt(3, p.getPrice());
			pstmt.setString(4, p.getDescription());
			pstmt.setInt(5, p.getStock());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateProduct(Connection conn, String id, int stock) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("UPDATE");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, stock);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteProduct(Connection conn, String id) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("DELETE");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Product> searchProduct(Connection conn, String name) {
		ArrayList<Product> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("SEARCH");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product();
				p.setProductId(rset.getString(1));
				p.setpName(rset.getString(2));
				p.setPrice(rset.getInt(3));
				p.setDescription(rset.getString(4));
				p.setStock(rset.getInt(5));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
		
	}
	
	// subMenu
	public ArrayList<ProductIO> searchIO(Connection conn) {
		ArrayList<ProductIO> list = new ArrayList<>();
		Statement stmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("SEARCHIO");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			while(rset.next()) {
				ProductIO p = new ProductIO();
				p.setIoNum(rset.getInt(1));
				p.setProductId(rset.getString(2));
				if(rset.getString(3).equals("ibmPC")) {
					p.setpName(rset.getString(3) + "\t");
				} else {
					p.setpName(rset.getString(3));
				}
				p.setIoDate(rset.getDate(4));
				p.setAmount(rset.getInt(5));
				p.setStatus(rset.getString(6));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}

	public ArrayList<ProductIO> searchInput(Connection conn) {
		ArrayList<ProductIO> list = new ArrayList<>();
		Statement stmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("SEARCHINPUT");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			while(rset.next()) {
				ProductIO p = new ProductIO();
				p.setIoNum(rset.getInt(1));
				p.setProductId(rset.getString(2));
				if(rset.getString(3).equals("ibmPC")) {
					p.setpName(rset.getString(3) + "\t");
				} else {
					p.setpName(rset.getString(3));
				}
				p.setIoDate(rset.getDate(4));
				p.setAmount(rset.getInt(5));
				p.setStatus(rset.getString(6));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}

	public ArrayList<ProductIO> searchOutput(Connection conn) {
		ArrayList<ProductIO> list = new ArrayList<>();
		Statement stmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("SEARCHOUTPUT");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			while(rset.next()) {
				ProductIO p = new ProductIO();
				p.setIoNum(rset.getInt(1));
				p.setProductId(rset.getString(2));
				if(rset.getString(3).equals("ibmPC")) {
					p.setpName(rset.getString(3) + "\t");
				} else {
					p.setpName(rset.getString(3));
				}
				p.setIoDate(rset.getDate(4));
				p.setAmount(rset.getInt(5));
				p.setStatus(rset.getString(6));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}

	public int productInput(Connection conn, String id, int amount) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql  = prop.getProperty("PRODUCTINPUT");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, amount);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int productOutput(Connection conn, String id, int amount) {
		ArrayList<Product> list = searchAll(conn);
		int result = 0;
		PreparedStatement pstmt = null;
		String sql  = prop.getProperty("PRODUCTOUTPUT");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, amount);
			int a = 0;
			for (int i = 0 ; i < list.size() ; i++) {
				if (list.get(i).getProductId().equals(id)) {
					a = i;
				}
			}
			if(amount > list.get(a).getStock()) {
				result = 0;
			} else {
				result = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
}
