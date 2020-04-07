package model;

import java.util.Date;

/**
 * @Decription
 * @authorEmail 1076030424@qq.com
 */
public class HomeworkSet {
	private Integer id;
	private String homeworkPath;
	private String homeworkName;
	private Date setDate;
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
}
