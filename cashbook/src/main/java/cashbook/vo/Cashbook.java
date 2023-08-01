package cashbook.vo;

public class Cashbook {
	private int cashbookNo;
	private String memberId;
	private int price;
	private String category;
	private String cashbookDate;
	private String memo;
	private String updatedate;
	private String createdate;
	/**
	 * @return the cashbookNo
	 */
	public int getCashbookNo() {
		return cashbookNo;
	}
	/**
	 * @param cashbookNo the cashbookNo to set
	 */
	public void setCashbookNo(int cashbookNo) {
		this.cashbookNo = cashbookNo;
	}
	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}
	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the cashbookDate
	 */
	public String getCashbookDate() {
		return cashbookDate;
	}
	/**
	 * @param cashbookDate the cashbookDate to set
	 */
	public void setCashbookDate(String cashbookDate) {
		this.cashbookDate = cashbookDate;
	}
	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}
	/**
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/**
	 * @return the updatedate
	 */
	public String getUpdatedate() {
		return updatedate;
	}
	/**
	 * @param updatedate the updatedate to set
	 */
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	/**
	 * @return the createdate
	 */
	public String getCreatedate() {
		return createdate;
	}
	/**
	 * @param createdate the createdate to set
	 */
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	@Override
	public String toString() {
		return "Cashbook [cashbookNo=" + cashbookNo + ", memberId=" + memberId + ", price=" + price + ", category="
				+ category + ", cashbookDate=" + cashbookDate + ", memo=" + memo + ", updatedate=" + updatedate
				+ ", createdate=" + createdate + "]";
	}
	
	
}
