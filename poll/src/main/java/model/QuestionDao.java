package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import dto.Paging;
import dto.Question;

// Table : question CRUD
public class QuestionDao {
	// question 테이블 데이터를 조회 - select
	public ArrayList<Question> selectQuestionList(Paging p) throws ClassNotFoundException, SQLException {
		ArrayList<Question> list = new ArrayList<>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select num, title, startdate, enddate, createdate, type from question limit ?, ?";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, p.getBeginRow());
		stmt.setInt(2, p.getRowPerPage());
		System.out.println(stmt);
		rs = stmt.executeQuery();
		while(rs.next()) {
			Question q = new Question();
			q.setNum(rs.getInt("num"));
			q.setTitle(rs.getString("title"));
			q.setStartdate(rs.getString("startdate"));
			q.setEnddate(rs.getString("enddate"));
			q.setCreatedate(rs.getString("createdate"));
			q.setType(rs.getInt("type"));
			list.add(q);
		}
		conn.close();
		return list;
	}
	
	// 입력 후 자동으로 생성된 키 값을 반환받기 위해 반환타입 int로 설정
	public int insertQuestion(Question question) throws ClassNotFoundException, SQLException {
		int pk = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		// 입력이지만 키 값을 받아올 때 사용
		ResultSet rs = null;
		String sql = "insert into question (title, startdate, enddate, type) values (?, ?, ?, ?)";
		// Statement.RETURN_GENERATED_KEYS 옵션 : insert 후 select max(pk) from ... 실행
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll", "root", "java1234");
		stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, question.getTitle());
		stmt.setString(2, question.getStartdate());
		stmt.setString(3, question.getEnddate());
		stmt.setInt(4, question.getType());
		int row = stmt.executeUpdate(); // insert
		rs = stmt.getGeneratedKeys(); // select max(num) from question
		if(rs.next()) {
			pk = rs.getInt(1);
		}
		conn.close();
		return pk;
		
		}
	
	// 데이터의 전체 행 수를 구하는 메서드
	public int getTotalQuestion() throws ClassNotFoundException, SQLException {
		int cnt = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) cnt from question";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		System.out.println(stmt);
		rs = stmt.executeQuery();
		rs.next();
		cnt = rs.getInt("cnt");
		conn.close();
		return cnt;
	}
	
	public void deleteQuestion(int num) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt1 = null;
		String sql1 = "delete from question where num = ?";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll", "root", "java1234");
		stmt1 = conn.prepareStatement(sql1);
		stmt1.setInt(1, num);
		stmt1.executeUpdate();
		    
	}
	
	
	public void updateQuestion(Question q) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "update question set title = ?, startdate = ?, enddate = ?, type = ? where num = ?";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, q.getTitle());
		stmt.setString(2, q.getStartdate());
		stmt.setString(3, q.getEnddate());
		stmt.setInt(4, q.getType());
		stmt.setInt(5, q.getNum());
		stmt.executeUpdate();
	}
	
	public void updateEnddate(String enddate, int qnum) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "update question set enddate = ? where num = ?";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, enddate);
		stmt.setInt(2, qnum);
		stmt.executeUpdate();
	}
	
	public Question selectQuestion(int num) throws ClassNotFoundException, SQLException {
		Question question = new Question();
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "select num, title, startdate, enddate, type from question where num = ?";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll", "root", "java1234");
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, num);
		rs = stmt.executeQuery();
		rs.next();
		question.setNum(rs.getInt("num"));
		question.setTitle(rs.getString("title"));
		question.setStartdate(rs.getString("startdate"));
		question.setEnddate(rs.getString("enddate"));
		question.setType(rs.getInt("type"));
		
		return question;
	}
	/*강사님
	public ArrayList<HashMap<String, Object>> selectQuestionList1() throws ClassNotFoundException, SQLException {
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll", "root", "java1234");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT q.num, q.title, q.startdate, q.enddate, t.cnt FROM question q INNER JOIN (SELECT qnum, SUM(COUNT) cnt FROM item GROUP BY qnum) t ON q.num = t.qnum";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("num", rs.getInt("num"));
			m.put("title", rs.getString("title"));
			m.put("startdate", rs.getString("startdate"));
			m.put("enddate", rs.getString("enddate"));
			m.put("cnt", rs.getInt("cnt"));
			list.add(m);
		}
		
		return list;
	}
	*/
	
	
	
}