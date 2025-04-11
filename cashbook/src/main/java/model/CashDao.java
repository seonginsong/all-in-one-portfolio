package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Cash;


public class CashDao {
	// 카테고리 삭제시 cash의 수 구하기
	public int selectCountCash(int cgno) throws ClassNotFoundException, SQLException {
		int cnt = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) cnt from cash where category_no=?";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, cgno);
		rs = stmt.executeQuery();
		
		if(rs.next()){
			cnt = rs.getInt("cnt");
		}
		conn.close();
		return cnt;
	}
	
	// 월 전체의 Cash 데이터 조회
	public ArrayList<Cash> selectCashByMonth(int y, int m) throws Exception {
		ArrayList<Cash> list = new ArrayList<>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT cs.cash_no cashno, cg.kind, cg.title, cs.category_no categoryno, "
		            + "cs.cash_date cashdate, cs.amount, cs.memo, cs.color "
		            + "FROM cash cs "
		            + "INNER JOIN category cg ON cs.category_no = cg.category_no "
		            + "WHERE YEAR(cs.cash_date) = ? AND MONTH(cs.cash_date) = ?";
		
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, y);
		stmt.setInt(2, m);
		rs = stmt.executeQuery();

		while (rs.next()) {
			Cash c = new Cash();
			c.setCashNo(rs.getInt("cashno"));
			c.setKind(rs.getString("kind"));
			c.setTitle(rs.getString("title"));
			c.setCategoryNo(rs.getInt("categoryno"));
			c.setCash_date(rs.getString("cashdate"));
			c.setAmount(rs.getInt("amount"));
			c.setMemo(rs.getString("memo"));
			c.setColor(rs.getString("color"));
			list.add(c);
		}
		conn.close();
		return list;
	}
	
	// 일 전체의 cash 데이터 조회
	public ArrayList<Cash> selectCashByDate(int y, int m, int d, String kind) throws Exception {
		ArrayList<Cash> list = new ArrayList<>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT cs.cash_no cashno, cg.kind, cg.title, cs.category_no categoryno, "
		            + "cs.cash_date cashdate, cs.amount, cs.memo, cs.color "
		            + "FROM cash cs "
		            + "INNER JOIN category cg ON cs.category_no = cg.category_no "
		            + "WHERE YEAR(cs.cash_date) = ? AND MONTH(cs.cash_date) = ? AND DAY(cs.cash_date) = ? AND cg.kind like ?";
		
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, y);
		stmt.setInt(2, m);
		stmt.setInt(3, d);
		stmt.setString(4, "%" + kind + "%");
		rs = stmt.executeQuery();

		while (rs.next()) {
			Cash c = new Cash();
			c.setCashNo(rs.getInt("cashno"));
			c.setKind(rs.getString("kind"));
			c.setTitle(rs.getString("title"));
			c.setCategoryNo(rs.getInt("categoryno"));
			c.setCash_date(rs.getString("cashdate"));
			c.setAmount(rs.getInt("amount"));
			c.setMemo(rs.getString("memo"));
			c.setColor(rs.getString("color"));
			list.add(c);
		}
		conn.close();
		return list;
	}
	
	// 일별 수입지출통계
	public int selectSumAmountByDate(int y, int m, int d, String kind) throws ClassNotFoundException, SQLException {
		int sum = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT sum(cs.amount), cg.kind FROM cash cs INNER JOIN category cg ON cs.category_no = cg.category_no"
					+" where year(cs.cash_date) = ? and month(cs.cash_date) = ? and day(cs.cash_date) = ? and kind like ? GROUP BY cg.kind";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, y);
		stmt.setInt(2, m);
		stmt.setInt(3, d);
		stmt.setString(4, "%" + kind + "%");
		
		rs = stmt.executeQuery();

		if(rs.next()) {
			sum = rs.getInt("sum(cs.amount)");
		}
		return sum;
	}
}
