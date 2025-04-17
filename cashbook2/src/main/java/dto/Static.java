package dto;

public class Static {
	private int amount;
	private int year;
	private int month;
	private String kind;
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	@Override
	public String toString() {
		return "Static [amount=" + amount + ", year=" + year + ", month=" + month + ", kind=" + kind + "]";
	}
}
