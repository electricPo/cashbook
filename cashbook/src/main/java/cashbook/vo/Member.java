package cashbook.vo;

public class Member {
	private String memberId;
	private String memberPw;
	private String updatedate;
	private String createdate;
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
	 * @return the memberPw
	 */
	public String getMemberPw() {
		return memberPw;
	}
	/**
	 * @param memberPw the memberPw to set
	 */
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
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
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Member(String memberId, String memberPw, String updatedate, String createdate) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.updatedate = updatedate;
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPw=" + memberPw + ", updatedate=" + updatedate
				+ ", createdate=" + createdate + "]";
	}
	
	
}
