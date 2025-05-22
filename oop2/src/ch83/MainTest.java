package ch83;

import java.util.ArrayList;

public class MainTest {
    public static void main(String[] args){
        ArrayList<Unit> list = new ArrayList<>();
    	list.add(new Marine());
    	list.add(new Tank());
    	list.add(new Firebat());
    	Medic medic = new Medic();
    	list.add(medic);
    	for(Unit u : list) {
    		medic.cure(u);
    	}
    }
}
