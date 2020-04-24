package service;


import java.util.List;

import dao.HomeworkCmitDao;
import dao.HomeworkCmitDaoimpl;
import model.HomeworkCmit;
import service.HomeworkCmitService;

public class HomeworkCmitServiceImpl implements HomeworkCmitService {
	HomeworkCmitDao homeworkCmitDao = new HomeworkCmitDaoimpl();
	@Override
	public int insert(HomeworkCmit homeworkCmit) {
		return homeworkCmitDao.insert(homeworkCmit);
	}

	@Override
	public int delete(int id) {
		return homeworkCmitDao.delete(id);
	}

	@Override
	public int update(HomeworkCmit homeworkCmit) {
		return homeworkCmitDao.update(homeworkCmit);
	}

	@Override
	public List<HomeworkCmit> queryAll() {
		return homeworkCmitDao.queryAll();
	}

	@Override
	public HomeworkCmit queryOne(int id) {
		return homeworkCmitDao.queryOne(id);
	}

	@Override
	public List<HomeworkCmit> queryPageHomeworkCmits(Integer rows, Integer page) {
		return homeworkCmitDao.queryPageHomeworkCmits(rows, page);
	}

	@Override
	public List<HomeworkCmit> queryPageStuHomeworkCmits(int i, Integer stuHomeworkPg, Integer studentId) {
		// TODO Auto-generated method stub
		return homeworkCmitDao.queryPageStuHomeworkCmits(i,stuHomeworkPg,studentId);
	}

	@Override
	public List<HomeworkCmit> queryStuSelfAll(int studentId) {
		// TODO Auto-generated method stub
		return homeworkCmitDao.queryStuSelfAll(studentId);
	}

}
