package com.cecwj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cecwj.dao.DoctorDao;
import com.cecwj.model.serialize.JsonBase;
import com.cecwj.model.serialize.DoctorInfo;
import com.cecwj.model.Doctor;

import javax.annotation.Resource;
@Component("doctorManager")
public class DoctorManager {
	private DoctorDao doctorDao;
	
	public DoctorDao getDoctorDao()
	  {
	     return this.doctorDao;
    }
	 @Resource
	public void setDoctorDao(DoctorDao doctorDao) {
		this.doctorDao = doctorDao;
	}
	 
	  public Doctor getdoctorById(int id) {
		     return this.doctorDao.getdoctorById(id);
		  }
	  public Doctor getdoctorByIdA(int id) {
		     return this.doctorDao.getdoctorByIdA(id);
		  }
	  public Doctor getdoctorByName(String string) {
		     return this.doctorDao.getdoctorByName(string);
		  }
	  public List<Doctor> getAllDoctor() {
		       List<Doctor> doctor = this.doctorDao.getAllDoctor();
		       return doctor;
		     }
	  
	    public void getAllDoctorA(JsonBase result)
	     {
	       List<Doctor> doctors = this.doctorDao.getAllDoctor();
	       List<DoctorInfo> us = new ArrayList();
	      // int total=0;
	      for (Doctor doctor : doctors) {
	        DoctorInfo u = new DoctorInfo(doctor);
	           us.add(u);
	       //  total=total+1;
	       }
	      int total = this.doctorDao.getAllDoctorcount();
	       result.setResults(us);
	       result.setTotal(total);
	       result.setSuccess(true);
	     }
		public void add(Doctor doctor) {
			
			 this.doctorDao.add(doctor);
		}
		public void delete(Doctor doctor) {
            
			this.doctorDao.delete(doctor);

			
		}
   
}
