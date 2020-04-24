package service;

import java.util.List;

import model.HomeworkCmit;

/**
 * @Decription
 * @authorEmail 1076030424@qq.com
 */
public interface HomeworkCmitService {
	public List<HomeworkCmit> queryPageHomeworkCmits(Integer rows, Integer page);
	public int insert(HomeworkCmit homeworkCmit);
	public int delete(int id);
	public int update(HomeworkCmit homeworkCmit);
	public List<HomeworkCmit> queryAll();
	public HomeworkCmit queryOne(int id);
	/**
	 * @Decription 获取学生个人的提交作业
	 */
	public List<HomeworkCmit> queryPageStuHomeworkCmits(int i, Integer stuHomeworkPg, Integer studentId);
	/**
	 * @Decription
	 */
	public List<HomeworkCmit> queryStuSelfAll(int studentId);
}
