package service;

import java.util.List;

import model.Clas;

/**
 * @Decription
 * @authorEmail 1076030424@qq.com
 */
public interface ClasService {
	public List<Clas> queryPageClasses(Integer rows, Integer page, String dimText);
	public int insert(Clas clas);
	public int delete(int id);
	public int update(Clas clas);
	public List<Clas> queryAll();
	public Clas queryOne(int id);
}
