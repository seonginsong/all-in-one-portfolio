package service;

// 추상 클래스보다 더 추상적인 타입
public interface IMyservice {
	// 클래스
	// static, 필드, 메서드
	
	// 추상클래스
	// static, 필드, 메서드, 추상메서드
	
	// 인터페이스
	// static, 추상메서드
	public static int X = 10;
	public static void m1() {
		
	}
	public /*abstract 생략가능*/ void text();
	//private, protect 등은 쓸 수가 없음 - 인터페이스는 쓰라고 만든거라 사용이 제한이 되는 것들은 사용불가 public, abstract만 사용가능 
}
