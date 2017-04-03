package com.cecwj.service;

import com.cecwj.dao.DisProcessDao;
import com.cecwj.model.DisProcess;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class DisProcessManager {
	private DisProcessDao disProcessDao;

	public DisProcessDao getDisProcessDao() {
		return this.disProcessDao;
	}

	@Resource
	public void setDisProcessDao(DisProcessDao disProcessDao) {
		this.disProcessDao = disProcessDao;
	}

	public DisProcess getDisProcessById(int id) {
		return this.disProcessDao.getDisProcessById(id);
	}

	public List<DisProcess> getDisProcessByPid(int page, int pid) {
		List<DisProcess> disProcesss = this.disProcessDao.getDisProcessByPid(
				page, pid);
		int total = this.disProcessDao.getDisProcessTotalByPid(pid);
		return disProcesss;
	}
	public List<DisProcess> getDisProcessByPid(int pid) {
		List<DisProcess> disProcesss = this.disProcessDao.getDisProcessByPid(
				 pid);
		int total = this.disProcessDao.getDisProcessTotalByPid(pid);
		return disProcesss;
	}
	public void add(DisProcess disProcess) {
		this.disProcessDao.add(disProcess);
	}

	public void delete(DisProcess disProcess) {
		this.disProcessDao.delete(disProcess);
	}

	public void update(DisProcess disProcess) {
		this.disProcessDao.update(disProcess);
	}

	public int getDisProcessTotalByPid(int pid) {
		return this.disProcessDao.getDisProcessTotalByPid(pid);
	}
}
