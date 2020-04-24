package service;


import java.util.List;

import dao.LeaveMSGDao;
import dao.LeaveMSGDaoimpl;
import model.LeaveMSG;
import service.LeaveMSGService;

public class LeaveMSGServiceImpl implements LeaveMSGService {
	LeaveMSGDao leaveMSGDao = new LeaveMSGDaoimpl();
	@Override
	public int insert(LeaveMSG leaveMSG) {
		// TODO Auto-generated method stub
		return leaveMSGDao.insert(leaveMSG);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return leaveMSGDao.delete(id);
	}

	@Override
	public int update(LeaveMSG leaveMSG) {
		// TODO Auto-generated method stub
		return leaveMSGDao.update(leaveMSG);
	}

	@Override
	public List<LeaveMSG> queryAll() {
		// TODO Auto-generated method stub
		return leaveMSGDao.queryAll();
	}

	@Override
	public LeaveMSG queryOne(int id) {
		// TODO Auto-generated method stub
		return leaveMSGDao.queryOne(id);
	}

	@Override
	public List<LeaveMSG> queryPageLeaveMSGs(Integer rows, Integer page) {
		return leaveMSGDao.queryPageLeaveMSGs(rows, page);
	}

}
