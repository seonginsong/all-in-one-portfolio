package dto;

public class Member {
	private String id;
	private int age;
	private String pw;
	// 캡슐화(set필드이름-필드가장앞은 대문자(입력값), get필드이름(입력값 없으므로 빈괄호)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		if(age < 0) {
			age = 0;
		}
		this.age = age;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
}
