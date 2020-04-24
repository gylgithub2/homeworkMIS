package dao;

import java.util.List;

import model.HomeworkCmit;

/**
 * @Decription 学生作业提交
 * @authorEmail 1076030424@qq.com
 */
public interface HomeworkCmitDao {
	/**
	 * 
	 * @Decription 分页查询
	 */
	public List<HomeworkCmit> queryPageHomeworkCmits(Integer rows, Integer page);
	public int insert(HomeworkCmit homeworkCmit);
	public int delete(int id);
	public int update(HomeworkCmit homeworkCmit);
	public List<HomeworkCmit> queryAll();
	public HomeworkCmit queryOne(int id);
	/**
	 * @Decription 获取学生的作业分页记录
	 */
	public List<HomeworkCmit> queryPageStuHomeworkCmits(int i, Integer stuHomeworkPg, Integer studentId);
	/**
	 * @Decription 获取学生自己的作业记录
	 */
	public List<HomeworkCmit> queryStuSelfAll(int studentId);
}
