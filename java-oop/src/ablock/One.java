package ablock;

public class One {
	public int a; // public : 모든 곳에서 사용가능(다른 패키지에서 사용한다면 import는 필수)
	protected int b; // protected : 다른 패키지에서 상속을 받는다면 사용가능 그 외로는 동일 패키지 내에서만 사용가능
	int c; // default : 동일 패키지 내에서만 사용가능
	private int d; // private : 같은 패키지의 this로는 사용 가능해도 다른패키지에서 상속된 this는 사용가능(동일클래스에서만)
	
	public void test() {
		// 필드가 있는 동일한 클래스에서는 접근제한자가 무엇이 됐든 사용 가능
		this.a = 1;
		this.b = 2;
		this.c = 3;
		this.d = 4;
	}
}
