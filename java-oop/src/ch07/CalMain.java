package ch07;

public class CalMain {
	public static void main(String[] args) {
		Calculator c = new Calculator();
		// c.power = true; 쓰지마라 
		System.out.println(c.power); // false
		c.setPower();
		System.out.println(c.power); // true
		
		// c.setPower();
		// System.out.println(c.power); // false
		
		
		
		c.setNum(5);
		System.out.println(c.num); // 5
		String result = c.checkNum();
		
		System.out.println(result);	//홀수
		
		System.out.println(c.setRateNum(0.7));
	
	}
}