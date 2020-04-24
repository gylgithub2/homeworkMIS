package model;

/**
 * @Decription 管理员ORM映射
 * @authorEmail 1076030424@qq.com
 */
public class Admin {

	private int id;
	private String adminName;
	private String adminPassword;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
}
