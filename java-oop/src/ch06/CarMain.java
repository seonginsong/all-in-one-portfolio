package ch06;

public class CarMain {
	// 기본 모양
	public static void main(String[] args) {
		Car c1 = new Car();
		Car c2 = new Car(7,true);
		c1.print();
		c2.print();
		
	}
}	
	/*
		public class Car {
			public int num;
			public boolean is;
			
			public Car() {}
			public Car(int num,boolean is) {
				this.num = num;
				this.is = is;
	}
	// this
		public void  print() {
			System.out.println(this.num);
		}

	
	
	
/*	
	public static void main(String[] args) {
		/*
		Car c = new Car();
			System.out.println(c.num);
			System.out.println(c.is);
	
		Car c2 = new Car(7);
			System.out.println(c2.num);
			System.out.println(c2.is);
		
		Car c3 = new Car(true);
			System.out.println(c3.num);
			System.out.println(c3.is);
		Car c4 = new Car(99,true);
			System.out.println(c4.num);
			System.out.println(c4.is);	
	
	
	}
}

*/