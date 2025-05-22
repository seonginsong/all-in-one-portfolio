package ch06;
import java.util.ArrayList;

public class Car {
	// 기본 모양
		public int num;
		public boolean is;
		//생성자 오버로딩
		public Car() {}
			
		public Car(int num,boolean is) {
			this();
			this.num = num;
			this.is = is;
}
// this
	public void  print() {
		System.out.println(this.num);
		}
	
	}
/*
 
	//책 247p
	public int num;
	public boolean is;
	public String str;
	public ArrayList<String> list;	
	
	//public int num = 1;
	//public boolean is = true;
	//public String arr = null;
	//public ArrayList<String> list;	
	
	//초기화를 안해도 된다
	
	//생성자가 없으면 컴파일러가 기계어로 변경할때 
	//기본 생성자(매개값이없는) 모양으로 자동으로 추가한다
	//생성자
	/* public Car() {
		this.num = 1;
		this.is = true;
		this.arr = "test";
		this.list = new ArrayList<String>();
		list.add("t");
		}

	// 책 255p
	//기본 생성자 모양으로 자동으로 추가
		/*public Car() {
			//필드 초기화 코드도 자동으로 추가
			//필드는 초기화의 규칙이 필요하다
			this.num = 1;
			/*
			this.is = false;
			this.arr = null;
			this.list = null;
			list.add("t");

	//책 260p
	// 일반생성자	
		public Car(int num) {
			// 컴파일러가 일반생성자가 존재하므로 기본생성자를 추가 하지않는다.
			// 생성자안에 필드초기화 코드가 없다 --> 그러니 추가
			this.num = num;
		}
		/* 위에랑 같은거
		public Car(int x) {
			this.num = x;	//변수라는건 이안에서만 사용할수있다. num 이란건 존재하지않는데 위에 코드를 보면 자바가 알아서 this 를 추가한다
		}

		public Car(boolean is) {
			// 컴파일러가 일반생성자가 존재하므로 기본생성자를 추가 하지않는다.
			// 생성자안에 필드초기화 코드가 없다 --> 그러니 추가
			this.is = is; //
		}	
		public Car(int num, boolean is) {
				// 컴파일러가 일반생성자가 존재하므로 기본생성자를 추가 하지않는다.
				// 생성자안에 필드초기화 코드가 없다 --> 그러니 추가
			this.num = num;
			this.is = is;	//
		}

		//메소드 오버로딩
		public void test() {}
		public void tset (int x) {}
}

*/