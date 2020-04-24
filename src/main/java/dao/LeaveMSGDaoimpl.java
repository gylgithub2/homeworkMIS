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
		String sql = "INSERT INTO `tb_leavemsg`(`leaveMSG_pk_id`,`user`,`headline`,`msg_content`,`update`) VALUES(?,?,?,?,?)";
		return update(connect,sql,leaveMSG.getId(),leaveMSG.getUser(),leaveMSG.getHeadline(),leaveMSG.getMsgContent(),leaveMSG.getUploadDate());
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
		String sql = "UPDATE  `tb_leavemsg` SET `msg_content` = ?,`user`=?,`headline`=?,`update` = ? WHERE `leaveMSG_pk_id` = ?";
		return update(connect,sql,leaveMSG.getMsgContent(),leaveMSG.getUser(),leaveMSG.getHeadline(),leaveMSG.getUploadDate(),leaveMSG.getId());
	}

	@Override
	public List<LeaveMSG> queryAll() {
		Connection connect = JDBCUtils.getConnection();
		String sql = "SELECT `leaveMSG_pk_id` id,`user`,`headline`,`msg_content` msgContent,`update` uploadDate"
				+ " FROM `tb_leavemsg`";
		return queryAll(connect,sql);
	
	}

	@Override
	public LeaveMSG queryOne(int id) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "SELECT `leaveMSG_pk_id` id,`user`,`headline`,`msg_content` msgContent,`update` uploadDate"
				+ " FROM `tb_leavemsg` where `leaveMSG_pk_id` = ?";
		return queryOne(connect,sql,id);
	}

	@Override
	public List<LeaveMSG> queryPageLeaveMSGs(Integer rows, Integer page) {
		Connection connect = JDBCUtils.getConnection();
		int startIndex = (page-1)*rows;
		String sql = "SELECT `leaveMSG_pk_id` id,`user`,`headline`,`msg_content` msgContent,`update` uploadDate"
				+ " FROM `tb_leavemsg` LIMIT ?,?";
		return queryPage(connect,sql,startIndex,rows);
	}

}
