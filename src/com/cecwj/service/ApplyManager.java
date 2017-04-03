package com.cecwj.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cecwj.dao.ApplyDao;
import com.cecwj.model.Apply;
import com.cecwj.model.Apply.TypeA;
import com.cecwj.model.Apply.TypeB;

import javax.annotation.Resource;

@Component("applyManager")
public class ApplyManager {
	private ApplyDao applyDao;

	public ApplyDao getApplyDao() {
		return applyDao;
	}
	@Resource
	public void setApplyDao(ApplyDao applyDao) {
		this.applyDao = applyDao;
	}
	
	public List<Apply>getAllApplyByPid(int pid, TypeA type){
		List<Apply> apply = this.applyDao.getAllApplyByPid(pid,type);
		return apply;
	}
	public List<Apply>getApplyByPid(int pid, TypeA type){
		List<Apply> apply = this.applyDao.getApplyByPid(pid,type);
		return apply;
	}
	public Apply getApply(int id){
		Apply apply = this.applyDao.getApply(id);
		return apply;
	}
	public List<Apply> getApplyByStatus(TypeB flag,TypeA inspection, TypeA examine ) {
		List<Apply> apply = this.applyDao.getApplyPid(flag,inspection,examine);
		return apply;
	}
	public Apply getApplyA(int pid) {
		Apply apply = this.applyDao.getApplyA(pid);
		return apply;
	}
	public void update(Apply apply) {
		 this.applyDao.update(apply);			
	}
	public void add(Apply apply) {
		this.applyDao.add(apply);
		
	}
	public List<Apply> getSurgyApply(TypeB flag, TypeA ops) {
		List<Apply> apply = this.applyDao.getSurgyApply(flag,ops);
		return apply;
	}
	public List<Apply> getApplyByStatusA(TypeB flag, TypeA type) {
		List<Apply> apply = this.applyDao.getSurgyApplyA(flag,type);
		return apply;
	}
	public List<Apply> testUndealt(TypeA type) {
		List<Apply> apply = this.applyDao.getUndealt(type);
		return apply;
	}
}
