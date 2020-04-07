package dao;

import java.sql.Connection;
import java.util.List;

import model.HomeworkCmit;
import utils.JDBCUtils;

/**
 * @Decription
 * @authorEmail 1076030424@qq.com
 */
public class HomeworkCmitDaoimpl extends BaseDao<HomeworkCmit> implements HomeworkCmitDao{

	@Override
	public int insert(HomeworkCmit homeworkCmit) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "INSERT INTO `tb_homewkcmit`(`cmit_pk_id`,`homework_name`,`file_path`"
				+ ",`file_name`,`up_date`,`student`,`read_advice`,`teacher`,`read_date`) VALUES(?,?,?,?,?,?,?,?,?)";
		return update(connect,sql,homeworkCmit.getId(),homeworkCmit.getHomeworkName(),
				homeworkCmit.getFilePath(),homeworkCmit.getFileName(),homeworkCmit.getUpDate()
				,homeworkCmit.getStudent(),homeworkCmit.getReadAdvice(),homeworkCmit.getTeacher(),homeworkCmit.getReadDate());

	}

	@Override
	public int delete(int id) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "DELETE FROM `tb_homewkcmit` WHERE `cmit_pk_id` = ?";
		return update(connect,sql,id);
	
	}

	@Override
	public int update(HomeworkCmit homeworkCmit) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "UPDATE  `tb_homewkcmit` SET `homework_name` = ?,`file_path` = ? ,`file_name` = ?,`up_date` = ?,`student` = ?"
				+ ",`read_advice` = ?,`teacher` = ?,`read_date` = ? WHERE `cmit_pk_id` = ?";
		return update(connect,sql,homeworkCmit.getHomeworkName(),
				homeworkCmit.getFilePath(),homeworkCmit.getFileName(),homeworkCmit.getUpDate()
				,homeworkCmit.getStudent(),homeworkCmit.getReadAdvice(),homeworkCmit.getTeacher(),
				homeworkCmit.getReadDate(),homeworkCmit.getId());

	}

	@Override
	public List<HomeworkCmit> queryAll() {
		Connection connect = JDBCUtils.getConnection();
		String sql = "SELECT `cmit_pk_id` id,"
				+ "`homework_name` homeworkName,"
				+ "`file_path` filePath,"
				+ "`file_name` fileName,"
				+ "`up_date` upDate,"
				+ "`student` student,"
				+ "`read_advice` readAdvice,"
				+ "`teacher` teacher,"
				+ "`read_date` readDate"
				+ " FROM `tb_homewkcmit`";
		return queryAll(connect,sql);
	
	}

	@Override
	public HomeworkCmit queryOne(int id) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "SELECT `cmit_pk_id` id,"
				+ "`homework_name` homeworkName,"
				+ "`file_path` filePath,"
				+ "`file_name` fileName,"
				+ "`up_date` upDate,"
				+ "`student` student,"
				+ "`read_advice` readAdvice,"
				+ "`teacher` teacher,"
				+ "`read_date` readDate"
				+ " FROM `tb_homewkcmit` where `cmit_pk_id` = ?";
		return queryOne(connect, sql,id);
	}

}
