package model;

import java.util.ArrayList;
// Model
public class GuguModel {
	public ArrayList<String> getDanList(int dan) {
		ArrayList<String> list = new ArrayList<>();
		for(int i=1; i<=9; i++) {
			String str = dan + "*" + i + "=" + (dan*i);
			list.add(str);
		}
		return list;
	}
}
