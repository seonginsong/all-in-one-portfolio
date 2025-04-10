package ch14;

public class Main {

	public static void main(String[] args) {
		Person ssi = new Person();
		
		Dog d = new Dog();
		ssi.withWalk(d);
		
		Snake s = new Snake();
		ssi.withWalk(s);
		
		Kangaroo k = new Kangaroo();
		ssi.withWalk(k);
	}

}
