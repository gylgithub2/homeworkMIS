package dao;

import java.sql.Connection;
import java.util.List;

import model.Admin;
import utils.JDBCUtils;

/**
 * @Decription
 * @authorEmail 1076030424@qq.com
 */
public class AdminDaoimpl extends BaseDao<Admin> implements AdminDao {

	@Override
	public int insert(Admin admin) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "INSERT INTO `tb_admin`(`admin_pk_id`,`admin_name`,`admin_password`) VALUES(?,?,?)";
		return update(connect,sql,admin.getId(),admin.getAdminName(),admin.getAdminPassword());
	}

	@Override
	public int delete(int id) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "DELETE FROM `tb_admin` WHERE `admin_pk_id` = ?";
		return update(connect,sql,id);
	}

	@Override
	public int update(Admin admin) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "UPDATE  `tb_admin` SET `admin_name` = ?,`admin_password` = ? WHERE `admin_pk_id` = ?";
		return update(connect,sql,admin.getAdminName(),admin.getAdminPassword(),admin.getId());
	}

	@Override
	public List<Admin> queryAll() {
		Connection connect = JDBCUtils.getConnection();
		String sql = "SELECT `admin_pk_id` id,`admin_name` adminName,`admin_password` adminPassword FROM `tb_admin`";
		return queryAll(connect,sql);
	}
	@Override
	public Admin queryOne(int id) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "SELECT `admin_pk_id` id,`admin_name` adminName,`admin_password` adminPassword FROM `tb_admin` where `admin_pk_id` = ?";
		return super.queryOne(connect, sql,id);
	}

}
