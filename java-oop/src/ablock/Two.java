package ablock;

public class Two {
	public void oneTest() {
		// 실제로 One 이라는 필드는 4개의 값을 갖고있지만 d는 정보은닉이 되어서 사용할 수 없음
		One one = new One();
		one.a = 1;
		one.b = 2;
		one.c = 3;
		//one.d = 4;
	}
}
