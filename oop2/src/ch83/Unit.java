package ch83;

public abstract class Unit { // new Unit(); 코드를 방지하기 위해서 추상클래스로
    protected int hp;
    protected String name;
    protected void move() {
        System.out.println(this.name+" Go!");
    }
}