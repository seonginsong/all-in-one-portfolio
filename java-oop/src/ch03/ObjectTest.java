package ch03;

public class ObjectTest {

	public static void main(String[] args) {
		// 첫 번째 자동차 객체 생성 및 설정
		Car car;
		car = new Car();
		car.onOff = true;
		car.name = "그랜저";
		car.color = "black";
		car.move();
		
		 // 두 번째 자동차 객체 생성 및 설정
		Car car2 = new Car();
		car2.onOff = false;
		car2.name = "K9";
		car2.color = "gray";
		car.move();
		
		
		// Doctor 객체 생성 및 설정
		Doctor doctor = new Doctor();
		doctor.person = new Person();// 포함 관계
		doctor.person.age = 35;
		doctor.person.name = "홍길동";
		doctor.person.phone = "01011112222";
		doctor.major = "내과";
		// Doctor2 객체 생성 및 설정
		Doctor2 doctor2 = new Doctor2();
		doctor2.name = "고길동";
		doctor2.major = "외과";
	}

}
