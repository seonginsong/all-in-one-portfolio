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
	// 수정하기 위한 qnum에 해당하는 아이템 목록
	public ArrayList<Item> selectItem(int qnum) throws ClassNotFoundException, SQLException {
		ArrayList<Item> list = new ArrayList<>();
		
		Class.forName("com.mysql.cj.jdbc.Driver");
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
		String sql = "select inum, content from item where qnum = ?";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, qnum);
		rs = stmt.executeQuery();
	    while(rs.next()) {
	    	Item i = new Item();
	    	i.setInum(rs.getInt("inum"));
	    	i.setContent(rs.getString("content"));
	    	list.add(i);
	    	
	    }
		
		return list;
	}
}