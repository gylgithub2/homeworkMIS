package dao;

import java.util.List;

import model.HomeworkCmit;

/**
 * @Decription
 * @authorEmail 1076030424@qq.com
 */
public interface HomeworkCmitDao {
	public int insert(HomeworkCmit homeworkCmit);
	public int delete(int id);
	public int update(HomeworkCmit homeworkCmit);
	public List<HomeworkCmit> queryAll();
	public HomeworkCmit queryOne(int id);
}
