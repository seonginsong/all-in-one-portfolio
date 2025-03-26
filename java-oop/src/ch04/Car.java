package ch04;

public class Car {
	public Car() {
		
	}
	public boolean onOff;
	public void move() {
		if(this.onOff) {
			System.out.println("자동차 Move!");
		} else {
			System.out.println("시동부터...");
		}
	}
}
