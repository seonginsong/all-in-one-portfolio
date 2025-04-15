package ch92;

public class Main {
	public void exe(Parent parent) {
		parent.work();
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		Parent parent = new Parent();
		main.exe(parent);
		
		Parent parent2 = new Child();
		main.exe(parent2); // 다형성
		
		main.exe(new Child());
		
		Parent parent3 = new Parent() { // 일회성 객체, 익명 객체(클래스의 이름이 없음) -> 클래스를 따로 만들 필요가 없음
			@Override
			void work() {
				System.out.println("밥 먹다");
			}
		};
		main.exe(parent3);
		
		main.exe(new Parent() { // 익명객체의 변수를 만들 필요가 없다 
			@Override
			void work() {
				System.out.println("밥 먹다");
			}
		});
	}

}
// public class A 이렇게 클래스를 만들 경우에는 대표되는 클래스가 있어야 하기 때문에 public은 만들 수 없음
class Parent {
	void work() {
		System.out.println("일(직업)한다");
	}
}

class Child extends Parent { // 부모(extends, implements) 자식 관계
	@Override
	void work() {
		System.out.println("공부한다");
	}
}