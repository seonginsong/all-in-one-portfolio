package ch14;

public abstract class Pet { // 메서드가 미완성이기때문에 객체를 설정할 수 없어야함, but 완성된 메서드도 가질 수 있고 필드도 설정 가능
	public abstract void move();// 미완성 메서드 = 추상메서드 
	public void move2() {}
	int x;
}
