/*    */ package com.cecwj.service;
/*    */ 
/*    */ import com.cecwj.dao.PConditionDao;
/*    */ import com.cecwj.model.PCondition;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component("pConditionManager")
/*    */ public class PConditionManager
/*    */ {
/*    */   private PConditionDao pConditionDao;
/*    */   
/*    */   public PConditionDao getpConditionDao()
/*    */   {
/* 18 */     return this.pConditionDao;
/*    */   }
/*    */   
/*    */   @Resource
/* 22 */   public void setpConditionDao(PConditionDao pConditionDao) { 
				this.pConditionDao = pConditionDao; 
			}
/*    */   
/*    */   public void addCondition(PCondition condition)
/*    */   {
/* 26 */     this.pConditionDao.add(condition);
/*    */   }
/*    */   
/*    */   public void update(PCondition condition) {
/* 30 */     this.pConditionDao.update(condition);
/*    */   }
/*    */   
/*    */   public void delete(int id) {
/* 34 */     this.pConditionDao.delete(id);
/*    */   }
/*    */   
/*    */   public List<PCondition> getConditions(int page, int pid)
/*    */   {
/* 39 */     int total = this.pConditionDao.getTotalNum(pid);
/* 40 */     List<PCondition> list = this.pConditionDao.getPConditions(page, pid);
/* 41 */     return list;
/*    */   }
/*    */   /*    */   public List<PCondition> getConditions( int pid)
/*    */   {
/* 39 */     int total = this.pConditionDao.getTotalNum(pid);
/* 40 */     List<PCondition> list = this.pConditionDao.getPConditions(pid);
/* 41 */     return list;
/*    */   }
/*    */   
/* 44 */   public int getTotalNum(int pid) { return this.pConditionDao.getTotalNum(pid); }
/*    */   
/*    */   public PCondition getConditionById(int id) {
/* 47 */     return this.pConditionDao.getPConditionById(id);
/*    */   }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\service\PConditionManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */