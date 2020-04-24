package model;

import java.util.Date;

/**
 * @Decription 公告信息ORM映射
 * @authorEmail 1076030424@qq.com
 */
public class Notice {
	private Integer id;
	//标题
	private String headline;
	//内容
	private String noticeContent;
	//上传时间
	private Date uploadDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
}
