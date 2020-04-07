package dao;

import java.util.List;

import model.HomeworkSet;

/**
 * @Decription
 * @authorEmail 1076030424@qq.com
 */
public interface HomeworkSetDao {
	public int insert(HomeworkSet Homeworkset);
	public int delete(int id);
	public int update(HomeworkSet Homeworkset);
	public List<HomeworkSet> queryAll();
	public HomeworkSet queryOne(int id);
}
