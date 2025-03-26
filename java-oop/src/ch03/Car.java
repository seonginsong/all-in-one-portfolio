package ch03;

public class Car {
	public Car() {} // 없으면 자동으로 만들어짐
	
	public boolean onOff;
	public String name;
	public String color;
	
	public void move() {
		System.out.println("move");
	}
}
