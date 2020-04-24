package model;

import java.util.Date;

/**
 * @Decription 学生提交作业记录ORM映射
 * @authorEmail 1076030424@qq.com
 */
public class HomeworkCmit {
	private Integer id;
	//作业名字
	private String homeworkName;
	//上传文件地址
	private String filePath;
	//文件名
	private String fileName;
	//上传时间
	private Date uploadDate;
	//上传学生
	private Integer student;
	//评阅信息
	private String readAdvice;
	//老师
	private Integer teacher;
	//读日期
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
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
}
