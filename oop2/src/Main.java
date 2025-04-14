import service.ITv;
import service.OttTv;

public class Main {

	public static void main(String[] args) {
		ITv tv;
		tv = new OttTv(); // new ITv(); 일 경우 - 추상클래스 객체X, interface도 객체 X -> 추상클래스를 가지고 있기때문에 객체를 만들수가 없음
		// 부모타입(클래스, 추상클래스, 인터페이스)에 자식객체를 대입 -> 다형성
		tv.onOff(); // ITv를 통해서 SmartTv의 onOff를 호출
		
		// 중간에 통신하는 접점역할(interface)을 없애면
		OttTv stv = new OttTv();
		stv.onOff();
	}

}
