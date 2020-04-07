package dao;

import java.sql.Connection;
import java.util.List;

import model.Clas;
import utils.JDBCUtils;

/**
 * @Decription
 * @authorEmail 1076030424@qq.com
 */
public class ClasDaoimpl extends BaseDao<Clas> implements ClasDao {
	@Override
	public int insert(Clas clas) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "INSERT INTO `tb_class`(`class_pk_id`,`class_name`) VALUES(?,?)";
		return update(connect,sql,clas.getId(),clas.getClassName());
	}

	@Override
	public int delete(int id) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "DELETE FROM `tb_class` WHERE `class_pk_id` = ?";
		return update(connect,sql,id);
	}

	@Override
	public int update(Clas clas) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "UPDATE  `tb_class` SET `class_name` = ? WHERE `class_pk_id` = ?";
		return update(connect,sql,clas.getClassName(),clas.getId());
	}

	@Override
	public List<Clas> queryAll() {
		Connection connect = JDBCUtils.getConnection();
		String sql = "SELECT `class_pk_id` id,`class_name` className FROM `tb_class`";
		return queryAll(connect,sql);
	}
	@Override
	public Clas queryOne(int id) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "SELECT `class_pk_id` id,`class_name` className FROM `tb_class` where `class_pk_id` = ?";
		return super.queryOne(connect, sql,id);
	}


}
