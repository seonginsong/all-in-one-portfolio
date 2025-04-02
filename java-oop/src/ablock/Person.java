package ablock;

public class Person {
	// 정보은닉
	private void first() {
		System.out.println("backup");
	}
	// 정보은닉
	private void second() {
		System.out.println("delete");
	}
	//캡슐화
	public void deleteMember() {
		this.first();
		this.second();
	}
}
