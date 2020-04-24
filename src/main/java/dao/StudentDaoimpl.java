package dao;

import java.sql.Connection;
import java.util.List;

import model.Student;
import utils.JDBCUtils;

/**
 * @Decription
 * @authorEmail 1076030424@qq.com
 */
public class StudentDaoimpl extends BaseDao<Student> implements StudentDao {

	@Override
	public int insert(Student student) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "INSERT INTO `tb_student`(`student_pk_id`,`student_name`,`student_class`,`student_password`,`student_sex`,`student_age`) VALUES(?,?,?,?,?,?)";
		return update(connect, sql, student.getId(), student.getStudentName(), student.getStudentClass(),
				student.getStudentPassword(), student.getSex(), student.getAge());

	}

	@Override
	public int delete(int id) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "DELETE FROM `tb_student` WHERE `student_pk_id` = ?";
		return update(connect, sql, id);

	}

	@Override
	public int update(Student student) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "UPDATE  `tb_student` SET `student_name` = ?,`student_class` = ? ,`student_password` = ?,`student_sex` = ?,`student_age` = ? WHERE `student_pk_id` = ?";
		return update(connect, sql, student.getStudentName(), student.getStudentClass(), student.getStudentPassword(),
				student.getSex(), student.getAge(), student.getId());

	}

	@Override
	public List<Student> queryAll() {
		Connection connect = JDBCUtils.getConnection();
		String sql = "SELECT `student_pk_id` id,`student_name` studentName,`student_class` studentClass,`student_password` studentPassword,`student_sex` sex,`student_age` age FROM `tb_student`";
		return queryAll(connect, sql);

	}

	@Override
	public Student queryOne(int id) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "SELECT `student_pk_id` id,"
				+ "`student_name` studentName,"
				+ "`student_class` studentClass,"
				+ "`student_password` studentPassword,"
				+ "`student_sex` sex,`student_age`"
				+ " age FROM `tb_student` where `student_pk_id` = ?";
		return super.queryOne(connect, sql, id);

	}

	@Override
	public Student login(String studentName, String studentPassword) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "SELECT `student_pk_id` id,"
				+ "`student_name` studentName,"
				+ "`student_class` studentClass,"
				+ "`student_password` studentPassword,"
				+ "`student_sex` sex,`student_age`"
				+ " age FROM `tb_student` WHERE `student_name` = ? AND `student_password`=?";
		return login(connect, sql,studentName,studentPassword);
	}
	@Override
	public List<Student> queryPageStudents(Integer rows, Integer page, String dimText) {
		Connection connect = JDBCUtils.getConnection();
		int startIndex = (page-1)*rows;
		String sql = "SELECT `student_pk_id` id,"
				+ "`student_name` studentName,"
				+ "`student_class` studentClass,"
				+ "`student_password` studentPassword,"
				+ "`student_sex` sex,`student_age`"
				+ " age FROM `tb_student`";
		StringBuffer str= new StringBuffer();
		str.append(sql);
		if(dimText == null) {
			str.append("LIMIT ?,?");
		return queryPage(connect,str.toString(),startIndex,rows);
	}else{
		str.append("WHERE `student_name` LIKE ? LIMIT ?,?");
		return queryPage(connect,str.toString(),"%"+dimText+"%",startIndex,rows);
	}
	}
}
