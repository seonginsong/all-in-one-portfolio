import service.Car;
import service.SmartCar;

public class CarMain {

	public static void main(String[] args) {
		// 서로 통신하는 접점 역할은 interface가 아니어도 된다
		Car c = new SmartCar(); 
		c.on();
	}

}
