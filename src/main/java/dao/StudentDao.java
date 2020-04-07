package dao;

import java.util.List;

import model.Student;

/**
 * @Decription
 * @authorEmail 1076030424@qq.com
 */
public interface StudentDao {
	public int insert(Student student);
	public int delete(int id);
	public int update(Student student);
	public List<Student> queryAll();
	public Student queryOne(int id);
}
