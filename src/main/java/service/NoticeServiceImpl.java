package service;


import java.util.List;

import dao.NoticeDao;
import dao.NoticeDaoimpl;
import model.Notice;
import service.NoticeService;

public class NoticeServiceImpl implements NoticeService {
	NoticeDao noticeDao = new NoticeDaoimpl();
	@Override
	public int insert(Notice notice) {
		// TODO Auto-generated method stub
		return noticeDao.insert(notice);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return noticeDao.delete(id);
	}

	@Override
	public int update(Notice notice) {
		// TODO Auto-generated method stub
		return noticeDao.update(notice);
	}

	@Override
	public List<Notice> queryAll() {
		// TODO Auto-generated method stub
		return noticeDao.queryAll();
	}

	@Override
	public Notice queryOne(int id) {
		// TODO Auto-generated method stub
		return noticeDao.queryOne(id);
	}

	@Override
	public List<Notice> queryPageNotices(Integer rows, Integer page) {
		return noticeDao.queryPageNotices(rows, page);
	}

}
