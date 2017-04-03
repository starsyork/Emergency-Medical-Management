/*    */ package com.cecwj.service;
/*    */ 
/*    */ import com.cecwj.dao.HospitalDao;
/*    */ import com.cecwj.model.Hospital;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component("hospitalManager")
/*    */ public class HospitalManager
/*    */ {
/*    */   private HospitalDao hospitalDao;
/*    */   
/*    */   public HospitalDao getHospitalDao()
/*    */   {
/* 19 */     return this.hospitalDao;
/*    */   }
/*    */   
/*    */   @Resource
/* 23 */   public void setHospitalDao(HospitalDao hospitalDao) { this.hospitalDao = hospitalDao; }
/*    */   
/*    */   public void addHospital(Hospital hospital)
/*    */   {
/* 27 */     this.hospitalDao.add(hospital);
/*    */   }
/*    */   
/*    */   public void deleteHospital(int id) {
/* 31 */     this.hospitalDao.delete(id);
/*    */   }
/*    */   
/* 34 */   public Hospital getHospitalById(int sid) { return this.hospitalDao.getHospitalById(sid); }
/*    */   
/*    */   public Hospital getHospitalByName(String secName) {
/* 37 */     return this.hospitalDao.getHospitalByName(secName);
/*    */   }
/*    */   
/*    */   public List<Hospital> getAllHospital() {
/* 41 */     List<Hospital> hospitals = this.hospitalDao.getAllHospital();
/* 42 */     return hospitals;
/*    */   }
/*    */   
/*    */   public void update(Hospital hospital) {
/* 46 */     this.hospitalDao.update(hospital);
/*    */   }
/*    */ }



