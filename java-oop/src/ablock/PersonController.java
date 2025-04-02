package ablock;

public class PersonController {
	public static void main(String[] args) {
		Person p = new Person();
		
		// p.first();
		// p.second();
		p.deleteMember(); // first, second를 따로 만들어서 백업하기전 삭제할 위험이 있기에 새로운 캡슐화된 메서드 생성
	}
}
