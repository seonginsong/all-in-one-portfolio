package bblock;
import ablock.One;
public class Four extends One { // : One을 상속받겠다. ( 기존에 있는 클래스를 똑같이 받지 않고 활용하는 방법 )
	public void oneTest() {
		this.a = 1; // public : 모든 곳에서 사용가능
		this.b = 2; // protected : 다른 패키지에서 상속을 받는다면 사용가능 그 외로는 동일 패키지 내에서만 사용가능
		//this.c = 3; // default : 동일 패키지 내에서만 사용가능
		//this.d = 4; // private : 같은 패키지의 this로는 사용 가능해도 다른패키지에서 상속된 this는 사용가능(동일클래스에서만)
	}
}
