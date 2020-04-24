package service;


import java.util.List;

import dao.AdminDao;
import dao.AdminDaoimpl;
import model.Admin;
import service.AdminService;

public class AdminServiceImpl implements AdminService{
	AdminDao adminDao = new AdminDaoimpl();
	@Override
	public int insert(Admin admin) {
		return adminDao.insert(admin);
	}

	@Override
	public int delete(int id) {
		return adminDao.delete(id);
	}

	@Override
	public int update(Admin admin) {
		return adminDao.update(admin);
	}

	@Override
	public List<Admin> queryAll() {
		return adminDao.queryAll();
	}

	@Override
	public Admin queryOne(int id) {
		return adminDao.queryOne(id);
	}

	@Override
	public Admin login(String adminName, String adminPassword) {
		return adminDao.login(adminName, adminPassword);
	}

	@Override
	public List<Admin> queryPageAdmins(Integer rows, Integer page, String dimText) {
		return adminDao.queryPageAdmins(rows, page, dimText);
	}

}
