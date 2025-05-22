package ch101;

public class Main {

	public static void main(String[] args) {
		System.out.println("hello"); // 모든 코드는 예외를 발생시킬 수 있다.
		
		//System.out.println(5/0); 
		try {
			System.out.println(5/0);
		} catch(Exception e) {
			System.out.println("0으로 나눌 수 없습니다");
		}
		
		// try catch 순서
		try {
			System.out.println("hello");
			System.out.println(5/0);
			System.out.println("by");
		} catch(Exception e) {
			System.out.println("0으로 나눌 수 없습니다");
			return;
		} finally {
			System.out.println("finaly");
		}
		
		
		
		
		System.out.println(args.length); // args = null;이라고 null을 지정해준다면 에러발생 
		
	}

}
