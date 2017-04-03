/*    */ package com.cecwj.service;
/*    */ 
/*    */ import com.cecwj.dao.ExaminationMainDao;
/*    */ import com.cecwj.model.ExaminationMain;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class ExaminationMainManager
/*    */ {
/*    */   private ExaminationMainDao examinationMainDao;
/*    */   
/*    */   public ExaminationMainDao getExaminationMainDao()
/*    */   {
/* 17 */     return this.examinationMainDao;
/*    */   }
/*    */   
/*    */   @Resource
/* 21 */   public void setExaminationMainDao(ExaminationMainDao examinationMainDao) { this.examinationMainDao = examinationMainDao; }
/*    */   
/*    */   public List<ExaminationMain> getExaminationMain(int page, int pid)
/*    */   {
/* 25 */     return this.examinationMainDao.getExaminationMain(page, pid);
/*    */   }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\service\ExaminationMainManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */