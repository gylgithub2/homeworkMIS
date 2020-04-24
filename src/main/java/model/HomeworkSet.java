package model;

import java.util.Date;

/**
 * @Decription 老师布置作业记录ORM映射
 * @authorEmail 1076030424@qq.com
 */
public class HomeworkSet {
	private Integer id;
	//作业标题
	private String headline;
	//作业文件保存地址
	private String homeworkPath;
	//作业名字
	private String homeworkName;
	//创建时间
	private Date setDate;
	//教师
	private Integer teacher;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getHomeworkPath() {
		return homeworkPath;
	}
	public void setHomeworkPath(String homeworkPath) {
		this.homeworkPath = homeworkPath;
	}
	public String getHomeworkName() {
		return homeworkName;
	}
	public void setHomeworkName(String homeworkName) {
		this.homeworkName = homeworkName;
	}
	public Date getSetDate() {
		return setDate;
	}
	public void setSetDate(Date setDate) {
		this.setDate = setDate;
	}
	public Integer getTeacher() {
		return teacher;
	}
	public void setTeacher(Integer teacher) {
		this.teacher = teacher;
	}
	/**
	 * @return the headline
	 */
	public String getHeadline() {
		return headline;
	}
	/**
	 * @param headline the headline to set
	 */
	public void setHeadline(String headline) {
		this.headline = headline;
	}
}
