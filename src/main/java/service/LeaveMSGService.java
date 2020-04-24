package service;

import java.util.List;

import model.LeaveMSG;

/**
 * @Decription
 * @authorEmail 1076030424@qq.com
 */
public interface LeaveMSGService {
	public List<LeaveMSG> queryPageLeaveMSGs(Integer rows, Integer page);
	public int insert(LeaveMSG leaveMSG);
	public int delete(int id);
	public int update(LeaveMSG leaveMSG);
	public List<LeaveMSG> queryAll();
	public LeaveMSG queryOne(int id);
}
