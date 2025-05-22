package ch11;

public class B { // A의 객체가 할 수 있는것들 다 가능
	// has(포함)
	public A a;
	public int y;
	public void m2() {
		System.out.println("m2");
	}
	
	public static void main(String[] args) {
		B b = new B(); // b는 B의 객체 - // A의 객체가 할 수 있는것들 다 가능
		b.a = new A();
		b.a.x = 1;
		b.y = 2;
		b.m2();
		b.a.m1();
		b.a.name = "goodee";
	}
}
