package ch11;

public class D extends C { // extends A -> extends C
	public int k;
	// public int x;가 있으면 밑의 d.x는 super.super.x 가 아니라 this.x
	// A + C
	public D() {
		super(); // this()는 자기자신 super()는 부모생성자 호출 = C호출, C는 A를 호출
		this.k = 0;
		// A() : super.super.x, super.super.name, super.super.m1()
		// C() : super.z, super.m3()
		// D() : k
	}
	
	public static void main(String[] args) {
		D d = new D();
		d.x = 1; // this.x가 없다 -> super.x가 없다 -> super.super.x
		d.name = "goodee";
		d.m1();
		d.m3();
		d.z = 2;
		d.k = 3;
	}
}
// 포함은 형변환사용불가 -- 상속으로