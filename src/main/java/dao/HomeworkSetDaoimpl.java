package dao;

import java.sql.Connection;
import java.util.List;

import model.HomeworkSet;
import utils.JDBCUtils;

/**
 * @Decription
 * @authorEmail 1076030424@qq.com
 */
public class HomeworkSetDaoimpl extends BaseDao<HomeworkSet> implements HomeworkSetDao {

	@Override
	public int insert(HomeworkSet Homeworkset) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "INSERT INTO `tb_homewkset`(`set_pk_id`,`homework_path`"
				+ ",`homework_name`,`set_date`,`teacher`) VALUES(?,?,?,?,?)";
		return update(connect, sql, Homeworkset.getId(), Homeworkset.getHomeworkPath(), Homeworkset.getHomeworkName(),
				Homeworkset.getSetDate(), Homeworkset.getTeacher());

	}

	@Override
	public int delete(int id) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "DELETE FROM `tb_homewkset` WHERE `set_pk_id` = ?";
		return update(connect, sql, id);
	}

	@Override
	public int update(HomeworkSet Homeworkset) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "UPDATE  `tb_homewkset` SET `homework_path` = ?,`homework_name` = ? ,`set_date` = ?,`teacher` = ? WHERE `set_pk_id` = ?";
		return update(connect, sql, Homeworkset.getHomeworkPath(), Homeworkset.getHomeworkName(),
				Homeworkset.getSetDate(), Homeworkset.getTeacher(), Homeworkset.getId());

	}

	@Override
	public List<HomeworkSet> queryAll() {
		Connection connect = JDBCUtils.getConnection();
		String sql = "SELECT `set_pk_id` id,`homework_path` homeworkPath,`homework_name` homeworkName,`set_date` setDate,"
				+ "`teacher` teacher  FROM `tb_homewkset`";
		return queryAll(connect, sql);
	}

	@Override
	public HomeworkSet queryOne(int id) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "SELECT `set_pk_id` id,`homework_path` homeworkPath,`homework_name` homeworkName,"
				+ "`set_date` setDate,`teacher` teacher  FROM `tb_homewkset` where `set_pk_id` = ?";
		return queryOne(connect,sql,id);
	}

}
