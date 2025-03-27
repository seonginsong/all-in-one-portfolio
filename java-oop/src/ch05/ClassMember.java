package ch05;

public class ClassMember {
	// 1. static // 책 243p 
	// 1-1) static method ( 독립적인 개체 , 객체를 생성하지 않고도 호출할 수 있는 메서드 , class 와 무관하고 공간과 이름만 빌려다쓸뿐이다.)
		public static void sFun() {
			System.out.println("sFun");
		}
	// 1-2) static field	// 클래스 변수(정적 변수, static 변수)라고도 하며, 클래스 로딩 시 한 번만 메모리에 할당되는 변수, 모든 객체가 공유하며, 객체를 생성하지 않아도 접근 가능.

		public static int x = 1+1;
		
		public static int[] arr = new int[] {1,2,3};	//자바는 오른쪽에 문법이 올수 없고 식이 와야한다
		// public static int[] arr = null;
		// public static int[] arr = new int[3];
		
		// 1-3) static block (클래스가 로드될 때 한 번 실행되는 블록으로, static 변수 초기화나 특정 설정을 할 때 사용)
		static {
			ClassMember.arr = new int[3];	//  == public static int[] arr = new int[3];
				for (int i=0; i<ClassMember.arr.length; i=i+1) {
					int rNum = (int)(Math.random()+10) + 1;		// Math.random() <ㅡ 난수생성
						ClassMember.arr[i] = rNum;
				}
		}




		//2.메소드 
		public void fun() {
			System.out.println("Fun()"); // 기능 역활을 하기위해서는 객체가 필요하다	
		}
		// ClassMember cm = new ClassMember(); cm.fun <<< 실행하기 위해서는 필요하다 
		
		//3. 필드  //필드는 객체의 속성(데이터)을 저장하는 역할 // 필드는 크게 **인스턴스 필드(Instance Field)**와 **정적 필드(Static Field)** 나뉜다
		public String name;
		
		//4. 생성자 // 첫번째 역활은 초기화이다
				public ClassMember() {	// 없으면 기본형태로 자동으로 생성된다 (컴파일러)
					//필드 초기화 코드가 없다면 자동으로 코드가 구현된다 (컴파일러)
					this.name = null;
				}
				public class InClass{} // 내부 클래스 ( 클래스안에 클래스를 만듬)
				public enum Inenum { 	// 내부 이넘
					x,y
				}
				
				public interface InInterface{} // 내부 인터페이스
				public @interface InAnnotation{} //내부 에노테이션
}
