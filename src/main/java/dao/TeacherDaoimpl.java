package dao;

import java.sql.Connection;
import java.util.List;

import model.Teacher;
import utils.JDBCUtils;

/**
 * @Decription
 * @authorEmail 1076030424@qq.com
 */
public class TeacherDaoimpl extends BaseDao<Teacher> implements TeacherDao {

	@Override
	public int insert(Teacher teacher) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "INSERT INTO `tb_teacher`(`teacher_pk_id`,`teacher_name`,`teacher_password`,`teacher_sex`,`teacher_age`) VALUES(?,?,?,?,?)";
		return update(connect, sql, teacher.getId(), teacher.getTeacherName(), teacher.getTeacherPassword(),
				teacher.getSex(), teacher.getAge());

	}

	@Override
	public int delete(int id) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "DELETE FROM `tb_teacher` WHERE `teacher_pk_id` = ?";
		return update(connect, sql, id);

	}

	@Override
	public int update(Teacher teacher) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "UPDATE  `tb_teacher` SET `teacher_name` = ?,`teacher_password` = ?,`teacher_sex` = ?,`teacher_age` = ? WHERE `teacher_pk_id` = ?";
		return update(connect, sql, teacher.getTeacherName(), teacher.getTeacherPassword(), teacher.getSex(),
				teacher.getAge(), teacher.getId());

	}

	@Override
	public List<Teacher> queryAll() {
		Connection connect = JDBCUtils.getConnection();
		String sql = "SELECT `teacher_pk_id` id,`teacher_name` teacherName,`teacher_password` teacherPassword,`teacher_sex` sex,`teacher_age` age FROM `tb_teacher`";
		return queryAll(connect, sql);

	}

	@Override
	public Teacher queryOne(int id) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "SELECT `teacher_pk_id` id,`teacher_name` teacherName,`teacher_password` teacherPassword,`teacher_sex` sex,`teacher_age` age FROM `tb_teacher` where `teacher_pk_id` = ?";
		return super.queryOne(connect, sql, id);

	}

}
