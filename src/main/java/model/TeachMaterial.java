package model;

import java.util.Date;



/**
 * @Decription 教学材料ORM映射
 * @authorEmail 1076030424@qq.com
 */
public class TeachMaterial {

	private int id;
	//标题
	private String headline;
	//内容
	private String materialContent;
	//文件地址
	private String materialPath;
	//文件名
	private String materialName;
	//上传时间
	private Date uploadDate;
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
		return materialPath;
	}
	public void setMaterialPath(String materialPath) {
		this.materialPath = materialPath;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
}
