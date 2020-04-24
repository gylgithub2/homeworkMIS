package service;


import java.util.List;

import dao.StudentDao;
import dao.StudentDaoimpl;
import model.Student;
import service.StudentService;

public class StudentServiceImpl implements StudentService {
	StudentDao studentDao = new StudentDaoimpl();
	@Override
	public int insert(Student student) {
		// TODO Auto-generated method stub
		return studentDao.insert(student);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return studentDao.delete(id);
	}

	@Override
	public int update(Student student) {
		// TODO Auto-generated method stub
		return studentDao.update(student);
	}

	@Override
	public List<Student> queryAll() {
		// TODO Auto-generated method stub
		return studentDao.queryAll();
	}

	@Override
	public Student queryOne(int id) {
		// TODO Auto-generated method stub
		return studentDao.queryOne(id);
	}

	@Override
	public Student login(String studentName, String studentPassword) {
		// TODO Auto-generated method stub
		return studentDao.login(studentName, studentPassword);
	}

	@Override
	public List<Student> queryPageStudents(Integer rows, Integer page, String dimText) {
		// TODO Auto-generated method stub
		return studentDao.queryPageStudents(rows, page, dimText);
	}

	

}
