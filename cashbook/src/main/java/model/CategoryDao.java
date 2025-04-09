package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Category;

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
}
