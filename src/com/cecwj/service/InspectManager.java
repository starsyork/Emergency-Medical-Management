package com.cecwj.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import com.cecwj.dao.InspectDao;
import com.cecwj.model.DisProcess;
import com.cecwj.model.Inspect;

import org.springframework.stereotype.Component;

@Component("inspectManager")
public class InspectManager {
	private InspectDao inspectDao;

	
	public InspectDao getInspectDao() {
		return inspectDao;
	}
	@Resource
	public void setInspectDao(InspectDao inspectDao) {
		this.inspectDao = inspectDao;
	}

	public Inspect getinspectById(int id) {
		return this.inspectDao.getinspectById(id);
	}
	public void add(Inspect inspect) {
		this.inspectDao.add(inspect);
	}
	public void delete(Inspect inspect) {
		this.inspectDao.delete(inspect);
	}

	public void update(Inspect inspect) {
		this.inspectDao.update(inspect);
	}
	public List<Inspect> getInspectBypid(int pid, int id) {
		 return this.inspectDao.getInspectBypid(pid, id);
	}
	public List<Inspect> getAllInspectBypid(int pid, int id) {
		 return this.inspectDao.getAllInspectBypid(pid, id);
	}
	public List<Inspect> getAllInspectBypid( int id) {
		 return this.inspectDao.getAllInspectBypid( id);
	}
}
