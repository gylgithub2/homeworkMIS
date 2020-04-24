package dao;

import java.util.List;

import model.Admin;

/**
 * @Decription
 * @authorEmail 1076030424@qq.com
 */
public interface AdminDao {
	//分页查询
	public List<Admin> queryPageAdmins(Integer rows, Integer page, String dimText);
	//登录方法
	public Admin login(String adminName, String adminPassword);
	public int insert(Admin admin);
	public int delete(int id);
	public int update(Admin admin);
	public List<Admin> queryAll();
	
	public Admin queryOne(int id);
}
