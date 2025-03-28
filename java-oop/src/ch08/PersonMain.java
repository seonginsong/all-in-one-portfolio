package ch08;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.HashMap;

public class PersonMain {

   public static void main(String[] args) {
      Person p1 = new Person("admin", "1234");
      //p1.id: admin, p1pw: 1234
      p1.m11(); // Hello
      p1.hw11(); // Good Morning
      
      p1.m12(9); // AM
      Calendar c = Calendar.getInstance();
      int h = c.get(Calendar.HOUR); //enum 값
      p1.m12(h); // AM 
      p1.hw12(false); // flag = false
      // 매개변수로 값이 전달
  	  // 참조타입과 기본타입(값) 을 넘길때는 다르다
  	  // 참조타입은 주소를 넘겼을떄 주소를 받은사람이 폭파했을경우 내 주소도 폭파되고
  	  // 기본타입(값)은 값을 넘겼을때 그냥 복사 될뿐이라서 넘겨준 값이 뭘로 바꾸든 
  	  // 내 값은 그대로 이다 

      boolean x = false;
      p1.hw12(x); // flag = false
      System.out.println(x); // flase
      
      // 1-3
      p1.m13b("guest", "1234");
      p1.m13b("admin", "1234");
      
      
      p1.m13a(null);
      p1.m13a("홍길동"); // 홀수
      String name = "신사임당";
      p1.m13a(name); // 짝수
      
      // 1-4
      int[] arr = new int[5]; // 0, 0, 0, 0, 0
      p1.m14a(arr);
      for(int i=0; i<arr.length; i++) {
         System.out.print(arr[i] + ",");
      } 
      System.out.print("");
      
      // 1-5
      String[]names = new String[2];
      names[0] = "루피";
      names[1] = "조로";
      p1.m15a(names);
      
      // 1-6
      Data d = new Data(7, 7);
      p1.m16a(d);
      System.out.println(d.x);
      System.out.println(d.y);
   
   	  // 1-7
	   	Data[] datas = new Data[4];
	   	datas[0] = new Data(13, 100);
	   	datas[1] = new Data(23, 200);
	   	datas[2] = new Data(20, 300);
	   	datas[3] = new Data(33, 400);
	   	p1.m17a(datas);
	   	p1.m17b(datas, 300);
   	
	   // 1-8
	   	ArrayList<Integer> year = new ArrayList<Integer>();
	   	int y = 2000;
	   	for(int i=0; i<50; i++) { // 50개
	   		year.add(y);
	   		y=y+1;
	   	}
	   	p1.m18a(year);
	   	
	   	// 1-9
	   	HashMap<String, Object> paramMap = new HashMap<String, Object>();
	   	paramMap.put("name", "샹크스");
	   	paramMap.put("age",40);
	   	String[] myHobby = {"술", "견문색", "검도"};
	   	paramMap.put("hobby", myHobby);
	   	Data myData = new Data(7,11);
	   	paramMap.put("data", myData);
	   	
	   	p1.m19a(paramMap);
   }
}