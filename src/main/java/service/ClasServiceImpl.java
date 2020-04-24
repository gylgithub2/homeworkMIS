package service;


import java.util.List;

import dao.ClasDao;
import dao.ClasDaoimpl;
import model.Clas;
import service.ClasService;

public class ClasServiceImpl implements ClasService{
	ClasDao clasDao = new ClasDaoimpl();
	@Override
	public int insert(Clas clas) {
		return clasDao.insert(clas);
	}

	@Override
	public int delete(int id) {
		return clasDao.delete(id);
	}

	@Override
	public int update(Clas clas) {
		return clasDao.update(clas);
	}

	@Override
	public List<Clas> queryAll() {
		return clasDao.queryAll();
	}

	@Override
	public Clas queryOne(int id) {
		return clasDao.queryOne(id);
	}

	@Override
	public List<Clas> queryPageClasses(Integer rows, Integer page, String dimText) {
		return clasDao.queryPageClasses(rows, page, dimText);
	}

}
