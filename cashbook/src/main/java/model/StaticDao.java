package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Static;

public class StaticDao {
	// 전체 수입/지출 총액
	public int selectStaticAll(String kind) throws ClassNotFoundException, SQLException {
		int sum = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select sum(amount), kind from cash c inner join category cg on c.category_no = cg.category_no"
					+" where cg.kind like ? group by cg.kind";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + kind + "%");
		rs = stmt.executeQuery();
		if(rs.next()) {
			sum = rs.getInt("sum(amount)");
		}
		conn.close();
		return sum;
	}
	
	// 년도별 수입/지출 총액
	public ArrayList<Static> selectStaticByYear() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "java1234");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "select year(cash_date), kind, sum(amount) from category ct"
					+" inner join cash cs on ct.category_no = cs.category_no"
					+" group by year(cash_date), ct.kind order by year(cash_date)";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		ArrayList<Static> list = new ArrayList<>();
		while(rs.next()) {
			Static s = new Static();
			s.setYear(rs.getInt("year(cash_date)"));
			s.setKind(rs.getString("kind"));
			s.setAmount(rs.getInt("sum(amount)"));
			list.add(s);
		}
		conn.close();
		return list;
	}
	
	// 월별 수입/지출 총액
	public ArrayList<Static> selectStaticByMonth() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "java1234");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "select month(cash_date), kind, sum(amount) from category ct"
					+" inner join cash cs on ct.category_no = cs.category_no"
					+" group by month(cash_date), ct.kind order by month(cash_date)";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		ArrayList<Static> list = new ArrayList<>();
		while(rs.next()) {
			Static s = new Static();
			s.setMonth(rs.getInt("month(cash_date)"));
			s.setKind(rs.getString("kind"));
			s.setAmount(rs.getInt("sum(amount)"));
			list.add(s);
		}
		conn.close();
		return list;
	}
	
	// 특정 년도의 월별 수입/지출 총액
	public ArrayList<Static> selectStaticBySYear(int year) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "java1234");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "select month(cash_date), kind, sum(amount) from category ct"
					+" inner join cash cs on ct.category_no = cs.category_no"
					+" where year(cash_date) = ? group by month(cash_date), ct.kind order by month(cash_date)";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, year);
		rs = stmt.executeQuery();
		ArrayList<Static> list = new ArrayList<>();
		while(rs.next()) {
			Static s = new Static();
			s.setMonth(rs.getInt("month(cash_date)"));
			s.setKind(rs.getString("kind"));
			s.setAmount(rs.getInt("sum(amount)"));
			list.add(s);
		}
		conn.close();
		return list;
	}
	
	// 중복 없는 연도만 가져오기
	public ArrayList<Integer> selectDistinctYears() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "java1234");
		String sql = "SELECT DISTINCT YEAR(cash_date) y FROM cash ORDER BY y DESC";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Integer> list = new ArrayList<>();
		while (rs.next()) {
			list.add(rs.getInt("y"));
		}
		conn.close();
		return list;
	}
}
