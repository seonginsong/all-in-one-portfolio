package dto;

public class Month {
	private int lastDate;
	private int dayOfWeek;
	public int getLastDate() {
		return lastDate;
	}
	public void setLastDate(int lastDate) {
		this.lastDate = lastDate;
	}
	public int getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public int getStartBlank() {
		return this.dayOfWeek - 1;
	}
	public int getTotalCell() {
		int endBlank = 0;
		int totalCell = this.getStartBlank() + this.lastDate + endBlank;
		if(totalCell % 7 != 0) {
			endBlank = 7 - (totalCell % 7);
			totalCell = totalCell + endBlank;
		} 
		return totalCell;
	}
}
