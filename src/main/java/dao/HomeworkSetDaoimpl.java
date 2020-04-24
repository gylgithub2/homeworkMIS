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
	public int insert(HomeworkSet homeworkset) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "INSERT INTO `tb_homewkset`(`set_pk_id`,`headline`,`homework_path`"
				+ ",`homework_name`,`set_date`,`teacher`) VALUES(?,?,?,?,?,?)";
		return update(connect, sql, homeworkset.getId(),homeworkset.getHeadline(), homeworkset.getHomeworkPath(), homeworkset.getHomeworkName(),
				homeworkset.getSetDate(), homeworkset.getTeacher());

	}

	@Override
	public int delete(int id) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "DELETE FROM `tb_homewkset` WHERE `set_pk_id` = ?";
		return update(connect, sql, id);
	}

	@Override
	public int update(HomeworkSet homeworkset) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "UPDATE  `tb_homewkset` SET `headline`=?,`homework_path` = ?,`homework_name` = ? ,`set_date` = ?,`teacher` = ? WHERE `set_pk_id` = ?";
		return update(connect, sql,homeworkset.getHeadline(), homeworkset.getHomeworkPath(), homeworkset.getHomeworkName(),
				homeworkset.getSetDate(), homeworkset.getTeacher(), homeworkset.getId());

	}

	@Override
	public List<HomeworkSet> queryAll() {
		Connection connect = JDBCUtils.getConnection();
		String sql = "SELECT `set_pk_id` id,`headline`,`homework_path` homeworkPath,`homework_name` homeworkName,`set_date` setDate,"
				+ "`teacher` teacher  FROM `tb_homewkset`";
		return queryAll(connect, sql);
	}

	@Override
	public HomeworkSet queryOne(int id) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "SELECT `set_pk_id` id,`headline`,`homework_path` homeworkPath,`homework_name` homeworkName,"
				+ "`set_date` setDate,`teacher` teacher  FROM `tb_homewkset` where `set_pk_id` = ?";
		return queryOne(connect,sql,id);
	}

	@Override
	public List<HomeworkSet> queryPageHomeworkSets(Integer rows, Integer page) {
		Connection connect = JDBCUtils.getConnection();
		int startIndex = (page-1)*rows;
		String sql = "SELECT `set_pk_id` id,`headline`,`homework_path` homeworkPath,`homework_name` homeworkName,`set_date` setDate,"
				+ "`teacher` teacher  FROM `tb_homewkset`LIMIT ?,?";
		return queryPage(connect,sql,startIndex,rows);

	}
}
