package service;

public class OttTv implements ITv{
	@Override
	public void onOff() {
		System.out.println("OTT 서비스에 로그인하기");
		System.out.println("서비스 시작 TV ON!");
	}

}
