package model;

import java.util.Date;

/**
 * @Decription
 * @authorEmail 1076030424@qq.com
 */
public class TeachMaterial {

	private int id;
	private String headline;
	private String materialContent;
	private String materrialPath;
	private String materialName;
	private Date upDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getMaterialContent() {
		return materialContent;
	}
	public void setMaterialContent(String materialContent) {
		this.materialContent = materialContent;
	}
	public String getMaterrialPath() {
		return materrialPath;
	}
	public void setMaterrialPath(String materrialPath) {
		this.materrialPath = materrialPath;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public Date getUpDate() {
		return upDate;
	}
	public void setUpDate(Date upDate) {
		this.upDate = upDate;
	}
}
