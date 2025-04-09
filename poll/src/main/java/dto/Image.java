package dto;

public class Image {
	private int num;
	private String memo;
	private String filename;
	private String createdate;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	@Override
	public String toString() {
		return "ImageDao [num=" + num + ", memo=" + memo + ", filename=" + filename + ", createdate=" + createdate
				+ "]";
	}
}
