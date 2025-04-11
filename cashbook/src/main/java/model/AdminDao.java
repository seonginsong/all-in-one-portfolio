package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Admin;

public class AdminDao {
	public Admin selectAdmin(String id) throws ClassNotFoundException, SQLException {
		Admin a = null;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select admin_id, admin_pw from admin where admin_id=?";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, id);
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			a = new Admin();
			a.setAdminId(rs.getString("admin_id"));
			a.setAdminPw(rs.getString("admin_pw"));
		}
		return a;
	}
	
	public int selectLogin(String id, String pw) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select admin_id, admin_pw from admin where admin_id=?";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, id);
		rs = stmt.executeQuery();
		
		int loginOk = 0;
		if(rs.next()) {
			if(rs.getString("admin_pw").equals(pw)) {
				System.out.println("로그인성공");
				loginOk = 1;
			} else {
				loginOk = 0;
			}
		}
		return loginOk;
	}
	
	public void updatePw(String nextPw1, String id) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		
		PreparedStatement stmt = null;
		String sql = "update admin set admin_pw=? where admin_id=?";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, nextPw1);
		stmt.setString(2, id);
		stmt.executeUpdate();
	}
}
