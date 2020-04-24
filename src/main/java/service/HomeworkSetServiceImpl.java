package service;

import java.util.List;

import dao.HomeworkSetDao;
import dao.HomeworkSetDaoimpl;
import model.HomeworkSet;
import service.HomeworkSetService;

public class HomeworkSetServiceImpl implements HomeworkSetService {
	HomeworkSetDao homeworkSetDao = new HomeworkSetDaoimpl();
	@Override
	public int insert(HomeworkSet Homeworkset) {
		// TODO Auto-generated method stub
		return homeworkSetDao.insert(Homeworkset);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return homeworkSetDao.delete(id);
	}

	@Override
	public int update(HomeworkSet Homeworkset) {
		// TODO Auto-generated method stub
		return homeworkSetDao.update(Homeworkset);
	}

	@Override
	public List<HomeworkSet> queryAll() {
		// TODO Auto-generated method stub
		return homeworkSetDao.queryAll();
	}

	@Override
	public HomeworkSet queryOne(int id) {
		// TODO Auto-generated method stub
		return homeworkSetDao.queryOne(id);
	}

	@Override
	public List<HomeworkSet> queryPageHomeworkSets(Integer rows, Integer page) {
		// TODO Auto-generated method stub
		return homeworkSetDao.queryPageHomeworkSets(rows, page);
	}

}
