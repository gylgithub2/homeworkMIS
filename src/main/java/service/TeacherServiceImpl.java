package service;


import java.util.List;

import dao.TeacherDao;
import dao.TeacherDaoimpl;
import model.Teacher;
import service.TeacherService;

public class TeacherServiceImpl implements TeacherService {
	TeacherDao teacherDao = new TeacherDaoimpl();
	@Override
	public int insert(Teacher teacher) {
		// TODO Auto-generated method stub
		return teacherDao.insert(teacher);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return teacherDao.delete(id);
	}

	@Override
	public int update(Teacher teacher) {
		// TODO Auto-generated method stub
		return teacherDao.update(teacher);
	}

	@Override
	public List<Teacher> queryAll() {
		// TODO Auto-generated method stub
		return teacherDao.queryAll();
	}

	@Override
	public Teacher queryOne(int id) {
		// TODO Auto-generated method stub
		return teacherDao.queryOne(id);
	}

	@Override
	public Teacher login(String teacherName, String teacherPassword) {
		// TODO Auto-generated method stub
		return teacherDao.login(teacherName, teacherPassword);
	}

	@Override
	public List<Teacher> queryPageTeachers(Integer rows, Integer page, String dimText) {

		return teacherDao.queryPageTeachers(rows, page, dimText);
	}

}
