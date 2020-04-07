package model;

import java.util.Date;

/**
 * @Decription
 * @authorEmail 1076030424@qq.com
 */
public class LeaveMSG {
	private Integer id;
	private String msgContent;
	private Date upDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public Date getUpDate() {
		return upDate;
	}
	public void setUpDate(Date upDate) {
		this.upDate = upDate;
	}
}
