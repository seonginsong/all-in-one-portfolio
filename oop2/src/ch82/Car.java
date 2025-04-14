package ch82;

import java.util.ArrayList;

public class Car {
	// 필드에 다형성을 사용
	public ArrayList<ITire> list;
	
	public void move() {
		for(ITire t : this.list) {
			t.roll();
		}
	}
	/*
	public ArrayList<HankookTire> tire1;
	public ArrayList<KumhoTire> tire2;  
	public ArrayList<NexenTire> tire3;  
	*/                                  
}                                       
                                        