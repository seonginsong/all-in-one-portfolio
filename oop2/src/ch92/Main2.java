package ch92;

public class Main2 {

	public static void main(String[] args) {
		Main2 main2 = new Main2();
		main2.exe((name)->System.out.println(name+"일하다")); // 기능만 넘길때 사용하는 문법 - 람다(-Lambda)
		main2.exe(new IParent() { // class 익명 implements IParent
			@Override
			public void work(String name) {
			System.out.println("공부한다");
		}
		});
	}
	public void exe(IParent parent) {
		parent.work("구디");
	}
}

@FunctionalInterface
interface IParent { // functionalInterface는 하나만 가능
	void work(String name);
}