package ch09;

import java.util.ArrayList;
import java.util.HashMap;

public class MethodEx2Main {

	public static void main(String[] args) {
		MethodEx2 m2 = new MethodEx2();
		
		Student s9 = new Student();
		s9.num = 99;
		s9.name = "티치";
		HashMap<String, Object> map9 = m2.m9a(s9);
		System.out.println(map9.get("num"));
		System.out.println(map9.get("name"));
		System.out.println("============================================================");
		
		ArrayList<String> nameList = m2.m8a("루피", "조로", "상디");
		for(String n : nameList) {
			System.out.println(n);
		}
		
		
		System.out.println("============================================================");
		ArrayList<HashMap<String, Object>> list7 = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("num", 11);
		map1.put("name", "샹크스");
		list7.add(map1);
		
		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put("num", 21);
		map2.put("name", "버기");
		list7.add(map2);
		
		HashMap<String, Object> map3 = new HashMap<String, Object>();
		map3.put("num", 7);
		map3.put("name", "프랑키");
		list7.add(map3);
		
		Student[] arr7 = m2.m7a(list7);
		for(Student s : arr7) {
			System.out.println(m2.m25a(s));
		}
		System.out.println("============================================================");
		Student student = m2.m6a(8, "로빈");
		System.out.println(m2.m25a(student));
				
		int num = 452;
		String[] result2 = m2.m5b(num);
		for(String s : result2) {
			System.out.println(s);
		}
		
		int[] result99 = m2.m5a(num);
		for(int i : result99) {	
		System.out.println(i);
		}
		
		
		//
		ArrayList<String> list = new ArrayList<String>();
		list.add("101");
		list.add("200");
		list.add("999");
		int[] result = m2.m4a(list);
		if(result != null) {
			for(int n : result) {
				System.out.println(n);
			}
		}
		
		Student s = new Student();
		s.num = 2;
		s.name = "조로";
		
		Student s2 = new Student();
		s2.num = 3;
		s2.name = "나미";
		
		Student s3 = new Student();
		s3.num = 3;
		s3.name = "나미";
		
		// Student 변수를 비교하는 메서드
		System.out.println(s2 == s3); // false
		System.out.println(s2.equals(s3)); // false
		System.out.println(m2.m24c(s2, s3)); // true
		System.out.println(m2.m24c(s, s2)); // false
		
		
		// s 변수를 디버깅
		System.out.println(s.num + ","+ s.name);
		System.out.println(m2.m25a(s2));
		
		// 반환타입 : int
		// 매개타입 배열
		int [] arr = new int[7];
		System.out.println(m2.m24b(arr));
		System.out.println(m2.m24a(arr));
		}

}
