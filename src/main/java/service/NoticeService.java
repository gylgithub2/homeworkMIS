package service;

import java.util.List;

import model.Notice;

/**
 * @Decription
 * @authorEmail 1076030424@qq.com
 */
public interface NoticeService {
	public List<Notice> queryPageNotices(Integer rows, Integer page);
	public int insert(Notice notice);
	public int delete(int id);
	public int update(Notice notice);
	public List<Notice> queryAll();
	public Notice queryOne(int id);
}
