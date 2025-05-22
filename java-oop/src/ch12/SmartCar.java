package ch12;

public class SmartCar extends Car {
	// 최상위 부모 : Object(super.super) > Car(super)
	public String display;
	
	@Override
	public void onOff() {
		System.out.println("버튼사용");
	}
	
	public static void main(String[] args) {
		SmartCar sc = new SmartCar();
		// sc.color = "빨간색"; // super
		sc.onOff(); // this(SmartCar에 onOff가 있으므로)
	}
	
	
}
