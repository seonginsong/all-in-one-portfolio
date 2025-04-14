package service;

public class SmartCar extends Car {

	@Override
	public void on() {
		System.out.println("인터넷접속확인");
		System.out.println("사용");
	}
	
}
