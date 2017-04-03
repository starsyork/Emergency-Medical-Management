package com.cecwj.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cecwj.dao.ApplyDao;
import com.cecwj.dao.PsychologyDao;
import com.cecwj.model.Apply;
import com.cecwj.model.Apply.TypeA;
import com.cecwj.model.Psychology;

import javax.annotation.Resource;

@Component("psychologyManager")
public class PsychologyManager {
	private PsychologyDao psychologyDao;

	
	public PsychologyDao getPsychologyDao() {
		return psychologyDao;
	}
	@Resource
	public void setPsychologyDao(PsychologyDao psychologyDao) {
		this.psychologyDao = psychologyDao;
	}
	public List<Psychology> getPsychologyByPid(int pid) {
		// TODO Auto-generated method stub
		return this.psychologyDao.getPsychologyByPid(pid);
	}
	public void addPsychology(Psychology psychology) {
		// TODO Auto-generated method stub
		this.psychologyDao.add(psychology);
	}
	public void deletePsychology(Psychology psychology) {
		// TODO Auto-generated method stub
		this.psychologyDao.delete(psychology);
	}
	public Psychology getPsychologyById(int id) {
		// TODO Auto-generated method stub
			Psychology psychology=this.psychologyDao.get(id);
			return psychology;
	}
	public void update(Psychology psychology) {
		// TODO Auto-generated method stub
		this.psychologyDao.update(psychology);
	}


	


}
