package service;

import java.util.List;

import model.Admin;

public interface AdminService {
	public List<Admin> queryPageAdmins(Integer rows, Integer page, String dimText);
	public Admin login(String adminName, String adminPassword);
	public int insert(Admin admin);
	public int delete(int id);
	public int update(Admin admin);
	public List<Admin> queryAll();
	public Admin queryOne(int id);
}
