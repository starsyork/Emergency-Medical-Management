/*    */ package com.cecwj.service;
/*    */ 
/*    */ import com.cecwj.dao.InjuryDao;
/*    */ import com.cecwj.model.Injury;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class InjuryManager
/*    */ {
/*    */   private InjuryDao injuryDao;
/*    */   
/*    */   public InjuryDao getInjuryDao()
/*    */   {
/* 16 */     return this.injuryDao;
/*    */   }
/*    */   
/*    */   @Resource
/* 20 */   public void setInjuryDao(InjuryDao injuryDao) { this.injuryDao = injuryDao; }
/*    */   
/*    */   public void addInjury(Injury injury)
/*    */   {
/* 24 */     this.injuryDao.addInjury(injury);
/*    */   }
/*    */   
/*    */   public Injury getInjury(int pid) {
/* 28 */     return this.injuryDao.getInjury(pid);
/*    */   }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\service\InjuryManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */