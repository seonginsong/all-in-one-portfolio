package ch83;

public class Medic extends Unit implements ICure {
    public Medic(){
        this.name="메딕";
    }
    public void cure(Unit unit){
    //  unit.hp++;
    	if(unit instanceof ICure) {
    		unit.hp++;
    		System.out.println(unit.name+"을 치료하다");
    	}
    }
}
