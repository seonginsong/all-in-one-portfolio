package ch03;

public class Car {
	 // 기본 생성자 (자동으로 생성되지만 명시적으로 선언)
	public Car() {} // 없으면 자동으로 만들어짐
	 // 자동차의 속성 (필드)
	public boolean onOff;
	public String name;
	public String color;
	// 자동차의 동작 (메서드)
	public void move() {
		System.out.println("move");
	}
}
