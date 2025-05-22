package ch82;

import java.util.ArrayList;

public class CartTest {

	public static void main(String[] args) {
		Car c = new Car();
		c.list = new ArrayList<ITire>();
		ITire frontRightTire = new HankookTire();
		c.list.add(frontRightTire);
		ITire frontLeftTire = new HankookTire();
		c.list.add(frontLeftTire);
		ITire backRightTire = new NexenTire();
		c.list.add(backRightTire);
		ITire backLeftTire = new KumhoTire();
		c.list.add(backLeftTire);
		c.move();
	}

}
