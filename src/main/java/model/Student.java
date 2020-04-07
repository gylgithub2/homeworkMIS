package model;

/**
 * @Decription
 * @authorEmail 1076030424@qq.com
 */
public class Student {

	/**
	 * 主键
	 */
	private Integer id;
	private String studentName;
	private String studentPassword;
	/**
	 * 性别 1/男  2/女
	 */
	private Integer sex;
	private Integer age;
	/**
	 * 班级
	 */
	private Integer studentClass;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentPassword() {
		return studentPassword;
	}
	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Integer getStudentClass() {
		return studentClass;
	}
	public void setStudentClass(Integer studentClass) {
		this.studentClass = studentClass;
	}
}
