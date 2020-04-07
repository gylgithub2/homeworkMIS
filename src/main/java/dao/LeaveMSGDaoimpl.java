package dao;

import java.sql.Connection;
import java.util.List;

import model.LeaveMSG;
import utils.JDBCUtils;

/**
 * @Decription
 * @authorEmail 1076030424@qq.com
 */
public class LeaveMSGDaoimpl extends BaseDao<LeaveMSG> implements LeaveMSGDao {

	@Override
	public int insert(LeaveMSG leaveMSG) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "INSERT INTO `tb_leavemsg`(`leaveMSG_pk_id`,`msg_content`,`update`) VALUES(?,?,?)";
		return update(connect,sql,leaveMSG.getId(),leaveMSG.getMsgContent(),leaveMSG.getUpDate());
	}

	@Override
	public int delete(int id) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "DELETE FROM `tb_leavemsg` WHERE `leaveMSG_pk_id` = ?";
		return update(connect,sql,id);
	
	}

	@Override
	public int update(LeaveMSG leaveMSG) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "UPDATE  `tb_leavemsg` SET `msg_content` = ?,`update` = ? WHERE `leaveMSG_pk_id` = ?";
		return update(connect,sql,leaveMSG.getMsgContent(),leaveMSG.getUpDate(),leaveMSG.getId());
	}

	@Override
	public List<LeaveMSG> queryAll() {
		Connection connect = JDBCUtils.getConnection();
		String sql = "SELECT `leaveMSG_pk_id` id,`msg_content` msgContent,`update` upDate"
				+ " FROM `tb_leavemsg`";
		return queryAll(connect,sql);
	
	}

	@Override
	public LeaveMSG queryOne(int id) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "SELECT `leaveMSG_pk_id` id,`msg_content` msgContent,`update` upDate"
				+ " FROM `tb_leavemsg` where `leaveMSG_pk_id` = ?";
		return queryOne(connect,sql,id);
	}

}
