package dao;

import java.util.List;

import model.Teacher;

/**
 * @Decription
 * @authorEmail 1076030424@qq.com
 */
public interface TeacherDao {
	public int insert(Teacher teacher);
	public int delete(int id);
	public int update(Teacher teacher);
	public List<Teacher> queryAll();
	public Teacher queryOne(int id);
}
