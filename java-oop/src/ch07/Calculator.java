package ch07;
//캡슐화 해야한다
public class Calculator {
	public Calculator() {
		this.power = false;
	}
	public boolean power;
	// static 을 쓰냐 안쓰냐는 this 를 쓰냐 안쓰냐에 따라서 
	public int num;
	
	public void setPower() {
		this.power = !this.power;
	}
	public void setNum(int num) {
		// 계산기 power 가 on(true)일때만 실행
		if(this.power) {  // if(this.power == true) 두개다 같은거
		this.num = num;
		}else {
			System.out.println("계산기가 OFF 상태");
		}
}
	
	// this.num * (0.5 ~ 1.5)
	public double setRateNum(double rate) {
		double result = 0;
		if(!this.power) { // this.power == false , !this.power 같은거
			System.out.println("계산기가 OFF 상태");
		}else {
			if(!(rate >= 0.5 && rate <= 1.5)) {
				System.out.println("입력값 ERROR");
			}else {
				result = this.num * rate;
			}
		}
		return result;
	}
	
	
	
	// this.num 이 짝수인지 홀수인지 알고 싶은 메소드
	public String checkNum() {
		// 계산기 power 가 on(true)일때만 실행
		String result = "";
		if(this.power == false) { // !this.power
			System.out.println("계산기가 OFF 상태");
			result = "ERROR";
		}else {
			if(this.num % 2 == 0) {
				result = "짝수";
			}else {
				result = "홀수";
			}	
		}
		return result;
	}
}