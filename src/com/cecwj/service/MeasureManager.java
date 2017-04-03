/*    */ package com.cecwj.service;
/*    */ 
/*    */ import com.cecwj.dao.MeasureDao;
/*    */ import com.cecwj.model.Measure;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ public class MeasureManager
/*    */ {
/*    */   private MeasureDao measureDao;
/*    */   
/*    */   public MeasureDao getMeasureDao()
/*    */   {
/* 15 */     return this.measureDao;
/*    */   }
/*    */   
/*    */   @Resource
/* 19 */   public void setMeasureDao(MeasureDao measureDao) { this.measureDao = measureDao; }
/*    */   
/*    */   public Measure getMeasureById(int id)
/*    */   {
/* 23 */     return this.measureDao.getMeasure(id);
/*    */   }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\service\MeasureManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */