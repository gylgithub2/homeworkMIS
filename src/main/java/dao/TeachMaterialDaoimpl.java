package dao;

import java.sql.Connection;
import java.util.List;

import model.TeachMaterial;
import utils.JDBCUtils;

/**
 * @Decription
 * @authorEmail 1076030424@qq.com
 */
public class TeachMaterialDaoimpl extends BaseDao<TeachMaterial> implements TeachMaterialDao {

	@Override
	public int insert(TeachMaterial teachMaterial) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "INSERT INTO `tb_teachmaterial`(`teach_material_pk_id`,`headline`,`material_content`,`material_path`,`material_name`,`update`) VALUES(?,?,?,?,?,?)";
		return update(connect, sql, teachMaterial.getId(), teachMaterial.getHeadline(),
				teachMaterial.getMaterialContent(), teachMaterial.getMaterrialPath(), teachMaterial.getMaterialName(),
				teachMaterial.getUploadDate());

	}

	@Override
	public int delete(int id) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "DELETE FROM `tb_teachmaterial` WHERE `teach_material_pk_id` = ?";
		return update(connect, sql, id);

	}

	@Override
	public int update(TeachMaterial teachMaterial) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "UPDATE  `tb_teachmaterial` SET `headline` = ?,`material_content` = ?,`material_path` = ?,`material_name` = ?,`update` = ? WHERE `teach_material_pk_id` = ?";
		return update(connect, sql, teachMaterial.getHeadline(), teachMaterial.getMaterialContent(),
				teachMaterial.getMaterrialPath(), teachMaterial.getMaterialName(), teachMaterial.getUploadDate(),
				teachMaterial.getId());

	}

	@Override
	public List<TeachMaterial> queryAll() {
		Connection connect = JDBCUtils.getConnection();
		String sql = "SELECT `teach_material_pk_id` id,"
				+ "`headline` headline,"
				+ "`material_content` materialContent,"
				+ "`material_path` materialPath,"
				+ "`material_name` materialName,"
				+ "`update` uploadDate FROM `tb_teachmaterial`";
		return queryAll(connect, sql);

	}

	@Override
	public TeachMaterial queryOne(int id) {
		Connection connect = JDBCUtils.getConnection();
		String sql = "SELECT `teach_material_pk_id` id,"
				+ "`headline` headline,"
				+ "`material_content` materialContent,"
				+ "`material_path` materialPath,`material_name` materialName,`update` uploadDate FROM `tb_teachmaterial` where `teach_material_pk_id`=?";
		return super.queryOne(connect, sql, id);

	}

	@Override
	public List<TeachMaterial> queryPageTeachMaterials(Integer rows, Integer page) {
		Connection connect = JDBCUtils.getConnection();
		int startIndex = (page-1)*rows;
		String sql = "SELECT `teach_material_pk_id` id,"
				+ "`headline` headline,"
				+ "`material_content` materialContent,"
				+ "`material_path` materialPath,`material_name` materialName,"
				+ "`update` uploadDate FROM `tb_teachmaterial` LIMIT ?,?";
		return queryPage(connect,sql,startIndex,rows);
	}
}
