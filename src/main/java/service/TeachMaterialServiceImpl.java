package service;


import java.util.List;

import dao.TeachMaterialDao;
import dao.TeachMaterialDaoimpl;
import model.TeachMaterial;
import service.TeachMaterialService;

public class TeachMaterialServiceImpl implements TeachMaterialService {
	TeachMaterialDao teachMaterialDao = new TeachMaterialDaoimpl();
	@Override
	public int insert(TeachMaterial teachMaterial) {
		// TODO Auto-generated method stub
		return teachMaterialDao.insert(teachMaterial);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return teachMaterialDao.delete(id);
	}

	@Override
	public int update(TeachMaterial teachMaterial) {
		// TODO Auto-generated method stub
		return teachMaterialDao.update(teachMaterial);
	}

	@Override
	public List<TeachMaterial> queryAll() {
		// TODO Auto-generated method stub
		return teachMaterialDao.queryAll();
	}

	@Override
	public TeachMaterial queryOne(int id) {
		// TODO Auto-generated method stub
		return teachMaterialDao.queryOne(id);
	}

	@Override
	public List<TeachMaterial> queryPageTeachMaterials(Integer rows, Integer page) {
		// TODO Auto-generated method stub
		return teachMaterialDao.queryPageTeachMaterials(rows, page);
	}

}
