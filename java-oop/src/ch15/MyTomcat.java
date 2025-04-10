package ch15;

public class MyTomcat {
	// 클라이언트(웹브라우저)의 요청이 있을때 마다 service()호출되는 구조
	public void service(Jsp jsp) { // jsp객체 a.jsp, b.jsp,...jsp파일
		jsp.post(); // jsp -> html
	}
	
	public static void main(String[] args) {
		MyTomcat t = new MyTomcat();
		// 클라이언트(웹브라우저)의 요청이 있을때마다 service() 호출되는 구조 
		t.service(new Ajsp()); // Jsp의 자식객체를 입력
		t.service(new LoginAction());
	}
}
