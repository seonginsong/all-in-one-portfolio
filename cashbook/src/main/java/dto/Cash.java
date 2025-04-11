package dto;

public class Cash {
	private int cashNo;
	private int categoryNo;
	private String cash_date;
	private int amount;
	private String memo;
	private String color;
	private String createdate;
	private String updatedate;
	private String title;
	private String kind;

	public String getKind() {
	    return kind;
	}
	public void setKind(String kind) {
	    this.kind = kind;
	}

	public String getTitle() {
	    return title;
	}
	public void setTitle(String title) {
	    this.title = title;
	}
	public int getCashNo() {
		return cashNo;
	}
	public void setCashNo(int cashNo) {
		this.cashNo = cashNo;
	}
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getCash_date() {
		return cash_date;
	}
	public void setCash_date(String cash_date) {
		this.cash_date = cash_date;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	@Override
	public String toString() {
		return "Cash [cashNo=" + cashNo + ", categoryNo=" + categoryNo + ", cash_date=" + cash_date + ", amount="
				+ amount + ", memo=" + memo + ", color=" + color + ", createdate=" + createdate + ", updatedate="
				+ updatedate + "]";
	}
}
