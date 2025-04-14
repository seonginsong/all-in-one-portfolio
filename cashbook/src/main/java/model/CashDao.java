package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Cash;
import dto.Category;


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
			c.setCashDate(rs.getString("cashdate"));
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
			c.setCashDate(rs.getString("cashdate"));
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
		conn.close();
		return sum;
	}
	// cash 추가하기 위한 kind의 categoryno 구하는 메서드
	public int selectCategoryNoByCashKindTitle(String kind, String title) throws ClassNotFoundException, SQLException {
		int cgno = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "select category_no from category where kind = ? and title = ?";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, kind);
		stmt.setString(2, title);
		
		rs = stmt.executeQuery();
		if(rs.next()) {
			cgno = rs.getInt("category_no");
		}
		conn.close();
		return cgno;
	}
	
	//하나 뽑기
	public Cash selectCashOne(int cashNo) throws SQLException, ClassNotFoundException {
		Cash c = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select cs.cash_no, cs.category_no, cs.cash_date, cs.amount, cs.memo, cs.color, cg.kind, cg.title from cash cs inner join category cg on cg.category_no = cs.category_no where cash_no = ?";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, cashNo);
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			c = new Cash();
			c.setCashNo(rs.getInt("cash_no"));
			c.setCategoryNo(rs.getInt("category_no"));
			c.setCashDate(rs.getString("cash_date"));
			c.setAmount(rs.getInt("amount"));
			c.setMemo(rs.getString("memo"));
			c.setColor(rs.getString("color"));
			c.setKind(rs.getString("kind"));
			c.setTitle(rs.getString("title"));
			}
		conn.close();
		return c;
	}
	
	// cash 추가
	public void insertCash(Cash c) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String sql = "insert into cash(cash_date, amount, memo, color, category_no) values(?, ?, ?, ?, ?)";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, c.getCashDate());
		stmt.setInt(2, c.getAmount());
		stmt.setString(3, c.getMemo());
		stmt.setString(4, c.getColor());
		stmt.setInt(5, c.getCategoryNo());
		stmt.executeUpdate();
		conn.close();
	}
	
	// 삭제
	public void deleteCash(int cashNo) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String sql = "delete from cash where cash_no = ?";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, cashNo);
		stmt.executeUpdate();
		conn.close();
	}
}
