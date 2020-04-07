package model;

import java.util.Date;

/**
 * @Decription
 * @authorEmail 1076030424@qq.com
 */
public class HomeworkCmit {
	private Integer id;
	private String homeworkName;
	private String filePath;
	private String fileName;
	private Date upDate;
	private Integer student;
	private String readAdvice;
	private Integer teacher;
	private Date readDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getHomeworkName() {
		return homeworkName;
	}
	public void setHomeworkName(String homeworkName) {
		this.homeworkName = homeworkName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Date getUpDate() {
		return upDate;
	}
	public void setUpDate(Date upDate) {
		this.upDate = upDate;
	}
	public Integer getStudent() {
		return student;
	}
	public void setStudent(Integer student) {
		this.student = student;
	}
	public String getReadAdvice() {
		return readAdvice;
	}
	public void setReadAdvice(String readAdvice) {
		this.readAdvice = readAdvice;
	}
	public Integer getTeacher() {
		return teacher;
	}
	public void setTeacher(Integer teacher) {
		this.teacher = teacher;
	}
	public Date getReadDate() {
		return readDate;
	}
	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}
}
