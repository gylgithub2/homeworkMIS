package service;

import java.util.List;

import model.TeachMaterial;



/**
 * @Decription
 * @authorEmail 1076030424@qq.com
 */
public interface TeachMaterialService {
	public List<TeachMaterial> queryPageTeachMaterials(Integer rows, Integer page);
	public int insert(TeachMaterial teachMaterial);
	public int delete(int id);
	public int update(TeachMaterial teachMaterial);
	public List<TeachMaterial> queryAll();
	public TeachMaterial queryOne(int id);
}
