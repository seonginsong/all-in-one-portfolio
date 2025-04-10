package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Category;
import dto.Paging;

public class CategoryDao {
	// 총 카운트(페이징)
	public int getTotalCategory(String searchWord, String kind) throws ClassNotFoundException, SQLException {
		int cnt = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "select count(*) cnt from category where title like ? and kind like ?";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + searchWord + "%");
		stmt.setString(2, "%" + kind + "%");
		rs = stmt.executeQuery();
		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}
		conn.close();
		return cnt;
	} 
	
	// 리스트 뽑기
	public ArrayList<Category> selectCategoryList(Paging p, String searchWord, String kind) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select category_no, kind, title, createdate from category where kind like ? and title like ? order by category_no desc limit ?, ?";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + kind + "%");
		stmt.setString(2, "%" + searchWord + "%");
		stmt.setInt(3, p.getBeginRow());
		stmt.setInt(4, p.getRowPerPage());
		rs = stmt.executeQuery();
		ArrayList<Category> list = new ArrayList<>();
		
		while(rs.next()) {
			Category c = new Category();
			c.setCategory_no(rs.getInt("category_no"));
			c.setKind(rs.getString("kind"));
			c.setTitle(rs.getString("title"));
			c.setCreatedate(rs.getString("createdate"));
			list.add(c);
		}
		return list;
	}
	//하나 뽑기
	public Category selectCategoryOne(int cgno) throws SQLException, ClassNotFoundException {
		Category c = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select category_no, kind, title, createdate from category where category_no = ?";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, cgno);
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			c = new Category();
			c.setKind(rs.getString("kind"));
			c.setTitle(rs.getString("title"));
			c.setCreatedate(rs.getString("createdate"));
		}
		return c;
	}
	
	// category 추가
	public void insertCategory(Category c) throws ClassNotFoundException, SQLException {
		int row = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String sql = "insert into category(kind, title) values(?, ?)";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, c.getKind());
		stmt.setString(2, c.getTitle());
		row = stmt.executeUpdate();
		
		conn.close();
	}
	// 카테고리 삭제
	public void deleteCategory(int cgno) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "java1234");
		
		PreparedStatement stmt = null;
		String sql = "delete from category where category_no = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, cgno);
		stmt.executeUpdate();
		
		conn.close();
	}
	// 카테고리 제목 수정
	public void updateCategoryTitle(String nextTitle, int cgno) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "java1234");
		
		PreparedStatement stmt = null;
		String sql = "update category set title = ? where category_no = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, nextTitle);
		stmt.setInt(2, cgno);
		stmt.executeUpdate();
		
		conn.close();
	}
	// 제목과 kind가 동시에 같은지 확인
	public int selectCntKindTitle(String title, String kind) throws ClassNotFoundException, SQLException {
		int cnt = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "java1234");
		ResultSet rs = null;
		PreparedStatement stmt = null;
		String sql = "select count(*) cnt from category where title = ? and kind = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, title);
		stmt.setString(2, kind);
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}
		conn.close();
		return cnt;
	}
}
