package ch02;


public class EnumTest {

	public static void main(String[] args) {
		char gender = 'M'; // M, F가 아닌 다른 값이 대입될 수 있다.
		
		if(gender == 'ㅁ') {
			System.out.println("남자");
		} else if(gender == 'F') {
			System.out.println("여자");
		} else {
			System.out.println("잘못된 값입니다");
		}
		 // Enum 사용 - 잘못된 값이 대입될 가능성을 방지할 수 있음
		Gender gender2 = null;
		gender2 = Gender.MALE;

		if(gender2 == Gender.MALE) {
			System.out.println("남자");
		} else {
			System.out.println("여자");
		}
		 // Enum을 switch문으로 처리하면 가독성이 좋아질 수 있음
		switch(gender2) {
		case Gender.MALE:
			System.out.println("남자");
			break;
		case Gender.FEMALE:
			System.out.println("여자");
			break;
		}
	}
}
