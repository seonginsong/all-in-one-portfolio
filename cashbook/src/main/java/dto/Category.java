package dto;

public class Category {
	/*public Category() {
		super(); // Object();
		// new 생성자 : new heap 영역에 this 필드를 생성하고 초기화
		this.category_no = 0;
		this.kind = null;
		this.title = null;
		this.createdate = null;
	}*/
	private int categoryNo;
	private String kind;
	private String title;
	private String createdate;
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
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
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	@Override
	public String toString() {
		return "Category [categoryNo=" + categoryNo + ", kind=" + kind + ", title=" + title + ", createdate="
				+ createdate + "]";
	}
}
