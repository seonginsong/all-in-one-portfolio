package ch11;
// is(상속관계)
public class C extends A {
	public C() {
		super(); // 부모생성자 A 호출
	}
	
	public int z;
	public void m3() {
		System.out.println("m3");
	}
	
	
	public static void main(String[] args) {
		C c = new C();
		c.x = 1;
		c.m1();
		c.z = 2;
		c.m3();
		c.name = "goodee";
	}
}
