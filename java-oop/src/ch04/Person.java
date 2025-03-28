package ch04;

public class Person {
	public void drive() {
		Car c1 = new Car();
		c1.move(); // this -> c1 (시동 꺼져있음)
		
		Car c2 = new Car();
		c2.onOff = true;
		c2.move(); // this -> c2 (시동 켜져있음)
	}
}
