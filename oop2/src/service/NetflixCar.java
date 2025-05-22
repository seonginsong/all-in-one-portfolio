package service;

public class NetflixCar extends Car {

	@Override
	public void on() {
		System.out.println("인터넷접속확인");
		System.out.println("넷플릭스 접속");
		System.out.println("사용");
	}

}
