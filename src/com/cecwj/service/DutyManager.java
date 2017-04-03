package com.cecwj.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cecwj.dao.ApplyDao;
import com.cecwj.dao.DutyDao;
import com.cecwj.dao.PsychologyDao;
import com.cecwj.model.Apply;
import com.cecwj.model.Apply.TypeA;
import com.cecwj.model.Duty;
import com.cecwj.model.Psychology;

import javax.annotation.Resource;

@Component("dutyManager")
public class DutyManager {
	private DutyDao dutyDao;

	
	public DutyDao getDutyDao() {
		return dutyDao;
	}
	@Resource
	public void setDutyDao(DutyDao dutyDao) {
		this.dutyDao = dutyDao;
	}
	public List<Duty> getDutyByPid(int pid) {
		return null;
		// TODO Auto-generated method stub
//		return this.psychologyDao.getPsychologyByPid(pid);
	}
	public void addDuty(Duty duty) {
		// TODO Auto-generated method stub
		this.dutyDao.add(duty);
	}
	public void deleteDuty(Duty duty) {
		// TODO Auto-generated method stub
		this.dutyDao.delete(duty);
	}
	public Duty getDutyById(int id) {
		// TODO Auto-generated method stub
		Duty duty=this.dutyDao.get(id);
			return duty;
	}
	public void updateDuty(Duty duty) {
		// TODO Auto-generated method stub
		this.dutyDao.update(duty);
	}
	public List<Duty> getDutyBySection(int section) {
		List<Duty> duty = this.dutyDao.getDutyBySection(section);
		return duty;
	}


	


}
