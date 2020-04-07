package dao;

import java.sql.Connection;
import java.util.List;

import model.Notice;
import utils.JDBCUtils;

/**
 * @Decription
 * @authorEmail 1076030424@qq.com
 */
public class NoticeDaoimpl  extends BaseDao<Notice> implements NoticeDao{

	@Override
	public int insert(Notice notice) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "INSERT INTO `tb_notice`(`notice_pk_id`,`headline`,`notice_content`,`update`) VALUES(?,?,?,?)";
		return update(connect,sql,notice.getId(),notice.getHeadline(),notice.getNoticeContent(),notice.getUpDate());
	
	}

	@Override
	public int delete(int id) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "DELETE FROM `tb_notice` WHERE `notice_pk_id` = ?";
		return update(connect,sql,id);
	
	}

	@Override
	public int update(Notice notice) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "UPDATE  `tb_notice` SET `headline` = ?,`notice_content` = ?,`update` = ? WHERE `notice_pk_id` = ?";
		return update(connect,sql,notice.getHeadline(),notice.getNoticeContent(),notice.getUpDate(),notice.getId());
	
	}

	@Override
	public List<Notice> queryAll() {
		Connection connect = JDBCUtils.getConnection();
		String sql = "SELECT `notice_pk_id` id,`headline` headline,`notice_content` noticeContent,`update` upDate "
				+ " FROM `tb_notice`";
		return queryAll(connect,sql);
	
	}

	@Override
	public Notice queryOne(int id) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "SELECT `notice_pk_id` id,`headline` headline,`notice_content` noticeContent,`update` upDate "
				+ " FROM `tb_notice` where `notice_pk_id` = ?";
		return queryOne(connect,sql,id);
	}

}
