package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Receit;

public class ReceitDao {
	// 추가
	public void insertReceit(Receit receit) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		
		PreparedStatement stmt = null;
		String sql = "insert into receit(cash_no, filename) values (?, ?)";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, receit.getCashNo());
		stmt.setString(2, receit.getFilename());
		int row = stmt.executeUpdate();
		
		conn.close();
	}

	// 삭제
	public void deleteReceit(int cashNo) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		
		PreparedStatement stmt = null;
		String sql = "delete from receit where cash_no = ?";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, cashNo);
		
		int row = stmt.executeUpdate();
		
		conn.close();
	}
	
	// receit 있는지 확인하는 메서드
	public int selectReceitCount(int cashNo) throws ClassNotFoundException, SQLException {
		int cnt = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "java1234");
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		String sql = "select count(*) cnt from receit where cash_no = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, cashNo);
		rs = stmt.executeQuery();
		
		if(rs.next()){
			cnt = rs.getInt("cnt");
		}
		return cnt;
	}
	
	// receit filename 메서드
	public String selectReceitFilename(int cashNo) throws ClassNotFoundException, SQLException {
		String filename = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "java1234");
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		String sql = "select filename from receit where cash_no = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, cashNo);
		rs = stmt.executeQuery();
		
		if(rs.next()){
			filename = rs.getString("filename");
		}
		return filename;
	}
}
