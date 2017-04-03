			package com.cecwj.service;
			
			import com.cecwj.dao.PatientDao;
			import com.cecwj.dao.SectionDao;
			import com.cecwj.model.Patient;
import com.cecwj.model.Section;
import com.cecwj.model.Volunteer;
			import com.cecwj.model.serialize.DoctorSta;
			import com.cecwj.model.serialize.JsonBase;
			import com.cecwj.model.serialize.PatientSection;
			import com.cecwj.model.serialize.PatientSta;
import com.cecwj.model.serialize.RTSta;

import java.sql.Timestamp;
			import java.util.ArrayList;
			import java.util.List;
			import javax.annotation.Resource;
import org.springframework.stereotype.Component;
			
			@Component("patientManager")
			public class PatientManager
			{
				private PatientDao patientDao;
			  	private SectionDao sectionDao;
			  
			  	public PatientDao getPatientDao()
			  {
			 return this.patientDao;
			  }
			  
			  @Resource
			  	public void setPatientDao(PatientDao patientDao) {
			 this.patientDao = patientDao;
			  }
			  
			  	public SectionDao getSectionDao() { return this.sectionDao; }
			  
			
			  	@Resource
			 	public void setSectionDao(SectionDao sectionDao) { this.sectionDao = sectionDao; }
			  
			  	public void addPatient(Patient patient) {
			  		this.patientDao.add(patient);						
			  }
			  
			  	public Patient getPatientByRFID(String rfid) { return this.patientDao.findByRFID(rfid); }
			  
			  	public void updatePatient(Patient patient) {
			 this.patientDao.update(patient);
			  }
			  
			  	public List<Patient> getPatientByDid(int page, int uid) {
				 List<Patient> patients = this.patientDao.getPatientByDid(page, uid);
				 return patients;
			  }
			  
			  	public int getPatientTotalByDid(int uid){
			  		return this.patientDao.getPatientTotalByDid(uid);
			  }
			  
			
			  	public List<Patient> getPatientBySid(int page, int sid){
				 List<Patient> patients = this.patientDao.getPatientBySid(page, sid);
				 return patients;
			  }
			  						
			
			  	public List<Patient> getPatientUndealt(int sid)
			  {
			 List<Patient> list = this.patientDao.getPatientUndealt(sid);
			 return list;
			  }
			  												
			  	public List<Patient> getPatientPhoneBySid(int sid)
			  {
			 List<Patient> list = this.patientDao.getPatientPhoneBySid(sid);
			 return list;
			  }
			  									
			  	public void getPatientUndealtA(int sid, JsonBase result)
			  {
					 List<Patient> list = new ArrayList();
					 list = this.patientDao.getPatientUndealtA(sid);
					 List<PatientSection> patientSection = new ArrayList();
					 for (Patient p : list) {
					   com.cecwj.model.Section section = this.sectionDao.getSectionById(p.getSid());
					   PatientSection ps = new PatientSection(p, section);
					   patientSection.add(ps);
					    }
					 result.setResults(patientSection);
					 result.setTotal(list.size());
					 result.setSuccess(true);
			  }
			  						
			  	public List<Patient> getAllUndealt()
			  {
				 List<Patient> list = this.patientDao.getAllUndealt();
				 return list;
			  }
			  
			  	public Patient getPatientById(int id) { 
			  		return this.patientDao.getPatientById(id); 
			  	}
			  
			  	public int testUndealt(int sid)
			  {
			 List<Patient> list = this.patientDao.getPatientUndealt(sid);
			// List<Patient> listA = this.patientDao.getPatientUndealtA(sid);
			List<Patient> listA = this.patientDao.getPatientUndealt(sid);
			 if ((list.size() < 1) && (listA.size() < 1))
			   return 0;
			 if ((list.size() > 0) && (listA.size() < 1))
			   return 1;
			 if ((list.size() < 1) && (listA.size() > 0))
			   return 2;
			 return 3;
			  }
			  	public List<Patient>  testUndealtPhone(int sid){
			  		 List<Patient> list = this.patientDao.getPatientUndealt(sid);
			  		return list;
			  	}
			  	public PatientSta getPatientStatis(int sid) {
						 PatientSta ps = new PatientSta();
						 long machine = this.patientDao.getPatientStatis(sid, Patient.Wound_type.MACHINE).longValue();
						 long pressasphyxia = this.patientDao.getPatientStatis(sid, Patient.Wound_type.PRESSASPHYXIA).longValue();
						 long drowning = this.patientDao.getPatientStatis(sid, Patient.Wound_type.DROWNING).longValue();
						 long burn = this.patientDao.getPatientStatis(sid, Patient.Wound_type.BURN).longValue();
						 long coldinjury = this.patientDao.getPatientStatis(sid, Patient.Wound_type.COLDINJURY).longValue();
						 long poisoning = this.patientDao.getPatientStatis(sid, Patient.Wound_type.POISONING).longValue();
						 long total = machine + pressasphyxia + drowning + burn + coldinjury + poisoning;
						 ps.setMachine(machine);
						 ps.setPressasphyxia(pressasphyxia);
						 ps.setDrowning(drowning);
						 ps.setBurn(burn);
						 ps.setColdinjury(coldinjury);
						 ps.setPoisoning(poisoning);
						 ps.setTotal(total);
						 return ps;
			  }
			  
			  	public DoctorSta getDoctorStatis(int uid) {
					 DoctorSta ds = new DoctorSta();
					 long wound = this.patientDao.getPatientStatis(uid, Patient.Status.ENTER).longValue();
					 long heal = this.patientDao.getPatientStatis(uid, Patient.Status.OUT).longValue();
					 ds.setHeal(heal);
					 ds.setWound(wound);
					 ds.setTotal(heal + wound);
					 return ds;
			  }
			  	
			  	public List<Patient> getPatientByDidAndFlag(int uid) {
			  		return this.patientDao.getPatientByDidAndFlag(uid);
			  }
			
			  	
			  	
				public long getPatientTimeStatis(int sid,Timestamp time) {				
					 long total = this.patientDao.getPatientStatistime(sid, time).longValue();					
					 return total;
				}
				public Patient getFirstPatientTime() {				
					Patient patient = this.patientDao.getFirstPatientTime();					
					 return patient;
				}
				public Patient getLastPatientTime() {				
					Patient patient = this.patientDao.getLastPatientTime();					
					 return patient;
				}
				
				public Patient getPatientByTime() {				
					Patient patient = this.patientDao.getPatientByTime();					
					 return patient;
				}

				public long getRTSta(int Sid, Timestamp timestamp1,Timestamp timestamp2) {
						 
					 long total=this.patientDao.getRTSta(Sid, timestamp1,timestamp2).longValue();;
						  
					return total;
				}
			
			}

