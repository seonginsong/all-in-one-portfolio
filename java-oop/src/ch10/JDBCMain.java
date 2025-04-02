package ch10;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dto.Actor;

public class JDBCMain {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// sql 테이블을 입력 : jsp와 똑같음. 라이브러리를 추가 : 프로젝트 우클릭 빌드패스 라이브러리 클래스패스
		
		// 1) 키보드를 통해 매개값 입력받기
		Scanner scanner = new Scanner(System.in);
		System.out.println("firstName을 입력하세요: ");
		String firstName = scanner.nextLine();
		System.out.println("lastName을 입력하세요: ");
		String lastName = scanner.nextLine();
		System.out.println("firstName: "+firstName);
		System.out.println("lastName: "+lastName);
		scanner.close();
		
		// 2)
		Actor actor = new Actor();
		actor.setFirstName(firstName);
		actor.setLastName(lastName);
		
		// 3) 입력 insert 모듈(메서드)호출
		ActorDAO actorDao = new ActorDAO();
		int row = actorDao.insertActor(actor);
		
		// 4)조회 select 모듈(메서드) 호출
		ArrayList<Actor> list = actorDao.selectActorList(5);
		
		// 5) 출력
		for(Actor a : list) {
			System.out.println(a.getActorId()+"\t"+a.getFirstName()+"\t"+a.getLastName()+"\t"+a.getLastUpdate());
		}
		
		
		/*
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		int row = 0;
		String sql = "insert into actor(first_name, last_name) values (?, ?)";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila","root","java1234");
		
		
		
		System.out.println("firstName: "+firstName);
		System.out.println("lastName: "+lastName);
		
		// 쿼리 실행
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, firstName);
		stmt.setString(2, lastName);
		System.out.println(stmt);
		
		row = stmt.executeUpdate();
		if(row == 1) {
			System.out.println("입력성공");
		}
		*/
		
		
		
		/*
		PreparedStatement stmt2 = null;
		ResultSet rs2 = null;
		String sql2 = "select actor_id actorId, first_name firstName, last_name lastName, last_update lastUpdate from actor order by actor_id desc limit 5";
		stmt2 = conn.prepareStatement(sql2);
		rs2 = stmt2.executeQuery();
		System.out.println("actorId\tfirstName\tlastName\tlastUpdate");
		while(rs2.next()) {
			System.out.print(rs2.getInt("actorId")+"\t"+rs2.getString("firstName")+"\t"+rs2.getString("lastName")+"\t"+rs2.getString("lastUpdate"));
		}
		conn.close();
		*/
	}

}
