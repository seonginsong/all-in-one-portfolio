package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Item;

// Table : item CRUD
public class ItemDao {
	public void insertItem(Item item) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into item (qnum, inum, content) values (?, ?, ?)";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, item.getQnum());
		stmt.setInt(2, item.getInum());
		stmt.setString(3, item.getContent());
		int row = stmt.executeUpdate();
		if(row == 1) {
			System.out.println("ItemDao.insertItem - 입력 성공");
		} else {
			System.out.println("ItemDao.insertItem - 입력 실패");
		}
		conn.close();
	}
	
	
	// totalCnt룰 구하는 메서드(삭제시 필요)
	public int getTotalCountByQnum(int qnum) throws ClassNotFoundException, SQLException {
	    int totalCount = 0;
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    
	    String sql = "SELECT SUM(count) AS totalCount FROM item GROUP BY qnum HAVING qnum = ?";
	    
	    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll", "root", "java1234");
	    stmt = conn.prepareStatement(sql);
	    stmt.setInt(1, qnum);
	    rs = stmt.executeQuery();
	    
	    if (rs.next()) {
	        totalCount = rs.getInt("totalCount");  
	    }
	    
	    conn.close();
	    return totalCount;
	}
	// 수정하기 위한 qnum에 해당하는 아이템 목록, questionOneResult(결과보기)
	public ArrayList<Item> selectItem(int qnum) throws ClassNotFoundException, SQLException {
		ArrayList<Item> list = new ArrayList<>();
		
		Class.forName("com.mysql.cj.jdbc.Driver");
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
		String sql = "select inum, content, count from item where qnum = ? order by inum asc";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, qnum);
		rs = stmt.executeQuery();
		// 외부 JDBC 라이브러리에 의존하는 ResultSet을 ArrayList타입으로 변경
	    while(rs.next()) {
	    	Item i = new Item();
	    	i.setInum(rs.getInt("inum"));
	    	i.setContent(rs.getString("content"));
	    	i.setCount(rs.getInt("count"));
	    	list.add(i);
	    }
		return list;
	}
	
	// 삭제 메서드
	public void deleteItem(int num) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "delete from item where qnum=?";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, num);
		stmt.executeUpdate();
	}
	
	// 투표를 했을때 투표인원수 추가하는 메서드
	public void updateItemCountPlus(int qnum, int inum) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "update item set count = count+1 where qnum=? and inum=?";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, qnum);
		stmt.setInt(2, inum);
		int row = stmt.executeUpdate();
		if(row == 1) {
			System.out.println("ItemDao.updateItemCountPlus#입력성공");
		} else {
			System.out.println("ItemDao.updateItemCountPlus#입력실패");
		}
	}
	
}