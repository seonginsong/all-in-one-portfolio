package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.Board;
import dto.Paging;

public class BoardDao {
	// 상세페이지 검색
	public Board selectBoardOne(int num) throws ClassNotFoundException, SQLException {
		Board b = null;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from board where num = ?";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, num);
		rs = stmt.executeQuery();
		
		// rs - > board
		if(rs.next()) {
			b = new Board();
			b.setNum(rs.getInt("num"));
			b.setName(rs.getString("name"));
			b.setSubject(rs.getString("subject"));
			b.setContent(rs.getString("content"));
			b.setPos(rs.getInt("pos"));
			b.setRef(rs.getInt("ref"));
			b.setDepth(rs.getInt("depth"));
			b.setRegdate(rs.getString("regdate"));
			b.setIp(rs.getString("ip"));
			b.setCount(rs.getInt("count"));
		}
		return b;
		
	}
	
	// 총 카운트(페이징)
	public int getTotalBoard(String searchWord) throws ClassNotFoundException, SQLException {
		int cnt = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) cnt from board where subject like ?";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		System.out.println(stmt);
		stmt.setString(1, "%" + searchWord + "%");
		rs = stmt.executeQuery();
		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}
		
		conn.close();
		return cnt;
	} 
	
	public ArrayList<Board> selectBoardList(Paging p, String searchWord) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from board where subject like ? order by ref desc, pos asc limit ?, ?";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + searchWord + "%");
		stmt.setInt(2, p.getBeginRow());
		stmt.setInt(3, p.getRowPerPage());
		System.out.println(stmt);
		rs = stmt.executeQuery();
		ArrayList<Board> list = new ArrayList<>();
		
		while(rs.next()) {
			Board b = new Board();
			b.setNum(rs.getInt("num"));
			b.setName(rs.getString("name"));
			b.setSubject(rs.getString("subject"));
			b.setPos(rs.getInt("pos"));
			b.setRef(rs.getInt("ref"));
			b.setDepth(rs.getInt("depth"));
			b.setCount(rs.getInt("count"));
			list.add(b);
		}		
		return list;		
	}

	// 새글입력(부모글)
	public void insertBoard(Board b) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; // 입력직후 PK값을 반환받기 위해
		String sql = "insert into board(name, subject, content, ref, pass, ip) values (?, ?, ?, ?, ?, ?)";
		
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll", "root", "java1234");
		conn.setAutoCommit(false); // executeUpdate()시마다 자동 커밋기능을 false
		stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // ref==0이면 입력직후 pk을 반환받기 위해
		stmt.setString(1, b.getName());
		stmt.setString(2, b.getSubject());
		stmt.setString(3, b.getContent());
		stmt.setInt(4, b.getRef());
		stmt.setString(5, b.getPass());
		stmt.setString(6, b.getIp());
		
		
		int row = stmt.executeUpdate(); // ref = 0이면 입력직후 pk을 반환받아서 ref값을 동일하게
		rs = stmt.getGeneratedKeys();
		int pk = 0;
		if(rs.next()) {
			pk = rs.getInt(1);
		}
		
		System.out.println("BoardDao.insertBoard#num: "+pk);
		
		PreparedStatement stmt2 = null;
		String sql2 = "update board set ref=? where num = ?";
		stmt2 = conn.prepareStatement(sql2);
		// update쿼리가 실패하면 이전의 insert도 롤백 : conn.rollback();
		stmt2.setInt(1,  pk);
		stmt2.setInt(2,  pk);
		stmt2.executeUpdate();
		
		conn.commit(); // conn.setAutoCommit(false); 코드때문에 필요
		conn.close();
	}
	
	// 답글입력(자식글) 위치, 부모자식관계
	public void insertBoardReply(Board b) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll", "root", "java1234");
		// 트랜잭션(2개 이상의 CUD쿼리를 한 묶음처럼 처리하고자 할때)
		conn.setAutoCommit(false); // executeUpdate()시마다 자동 커밋기능을 false
		
		// ref가 같고 pos값이 현재 글보다 크거나 같다면 +1, depth+1
		PreparedStatement stmt2 = null;
		String sql2 = "update board set pos = pos+1 where ref = ? and pos >= ?";
		stmt2 = conn.prepareStatement(sql2);
		stmt2.setInt(1, b.getRef());
		stmt2.setInt(2, b.getPos());
		int row2 = stmt2.executeUpdate();
		
		// 답글 입력
		PreparedStatement stmt = null;
		String sql = "insert into board(name, subject, content, ref, pos, depth, pass, ip) values (?, ?, ?, ?, ?, ?, ?, ?)";
		stmt = conn.prepareStatement(sql); // ref==0이면 입력직후 pk을 반환받기 위해
		stmt.setString(1, b.getName());
		stmt.setString(2, b.getSubject());
		stmt.setString(3, b.getContent());
		stmt.setInt(4, b.getRef());
		stmt.setInt(5, b.getPos());
		stmt.setInt(6, b.getDepth());
		stmt.setString(7, b.getPass());
		stmt.setString(8, b.getIp());
		
		int row = stmt.executeUpdate();
		
		
		conn.commit(); // conn.setAutoCommit(false); 코드때문에 필요
		conn.close();
	}
	// 글 삭제
	public void deleteBoard(int num, String pass) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll", "root", "java1234");
		conn.setAutoCommit(false); // executeUpdate()시마다 자동 커밋기능을 false
		
		PreparedStatement stmt = null;
		String sql = "delete from board where num = ? and pass = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, num);
		stmt.setString(2, pass);
		int row = stmt.executeUpdate();
		
		// 자식글들 수정
		PreparedStatement stmt2 = null;
		String sql2 = "update board set subject = '부모글이 삭제되었습니다' where ref = ?";
		stmt2 = conn.prepareStatement(sql2);
		stmt2.setInt(1, num);
		
		stmt2.executeUpdate();
		
		conn.commit();
		conn.close();
	}
	
	// 글수정
	public void updateBoard(Board b, int num, String pass) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll", "root", "java1234");
		
		PreparedStatement stmt = null;
		String sql = "update board set name = ?, subject = ?, content = ? where num = ? and pass = ?";
		stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, b.getName());
		stmt.setString(2, b.getSubject());
		stmt.setString(3, b.getContent());
		stmt.setInt(4, num);
		stmt.setString(5, pass);
		stmt.executeUpdate();
	}
	

}
