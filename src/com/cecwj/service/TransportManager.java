package com.cecwj.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import com.cecwj.dao.InspectDao;
import com.cecwj.dao.TransportDao;
import com.cecwj.model.DisProcess;
import com.cecwj.model.Inspect;
import com.cecwj.model.Transport;

import org.springframework.stereotype.Component;

@Component("transportManager")
public class TransportManager {
	private TransportDao transportDao;

	


	public TransportDao getTransportDao() {
		return transportDao;
	}
	@Resource
	public void setTransportDao(TransportDao transportDao) {
		this.transportDao = transportDao;
	}
	public Transport gettransportById(int id) {
		return this.transportDao.gettransportById(id);
	}
	public void add(Transport transport) {
		this.transportDao.add(transport);
	}
	public void delete(Transport transport) {
		this.transportDao.delete(transport);
	}

	public void update(Transport transport) {
		this.transportDao.update(transport);
	}
	public List<Transport> getTransportBypid(int pid, int id) {
		// TODO Auto-generated method stub
		 return this.transportDao.getTransportBypid(pid, id);
	}
	public List<Transport> getAllTransportBypid(int pid, int id) {
		// TODO Auto-generated method stub
		 return this.transportDao.getAllTransportBypid(pid, id);
	}
	public Transport getTransportByApply( int id) {
		// TODO Auto-generated method stub
		 return this.transportDao.getTransportByApply(id);
	}
	public List<Transport> getTransportByTid(Integer tid) {
		// TODO Auto-generated method stub
		 return this.transportDao.getTransportByTid(tid);
	}

}
