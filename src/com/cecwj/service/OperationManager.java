package com.cecwj.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import com.cecwj.dao.InspectDao;
import com.cecwj.dao.OperationDao;
import com.cecwj.model.DisProcess;
import com.cecwj.model.Inspect;
import com.cecwj.model.Operation;

import org.springframework.stereotype.Component;

@Component("operationManager")
public class OperationManager {
	private OperationDao operationDao;

	

	public OperationDao getOperationDao() {
		return operationDao;
	}
	@Resource
	public void setOperationDao(OperationDao operationDao) {
		this.operationDao = operationDao;
	}
	public Operation getoperationById(int id) {
		return this.operationDao.getoperationById(id);
	}
	public void add(Operation operation) {
		this.operationDao.add(operation);
	}
	public void delete(Operation operation) {
		this.operationDao.delete(operation);
	}

	public void update(Operation operation) {
		this.operationDao.update(operation);
	}
	public List<Operation> getOperationBypid(int pid, int id) {
		 return this.operationDao.getOperationBypid(pid, id);
	}
	public List<Operation> getAllOperationBypid(int pid, int id) {
		 return this.operationDao.getAllOperationBypid(pid, id);
	}
	public List<Operation> getAllOperationBypid(int pid ) {
		 return this.operationDao.getAllOperationBypid(pid);
	}
}
