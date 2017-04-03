/*    */ package com.cecwj.service;
/*    */ 
/*    */ import com.cecwj.dao.ConditionDao;
/*    */ import com.cecwj.model.Condition;
/*    */ import com.cecwj.model.serialize.JsonBase;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ @Component("conditionManager")
/*    */ public class ConditionManager
/*    */ {
/*    */   private ConditionDao conditionDao;
/*    */   
/*    */   public ConditionDao getConditionDao()
/*    */   {
/* 18 */     return this.conditionDao;
/*    */   }
/*    */   
/*    */   @Resource
/* 22 */   public void setConditionDao(ConditionDao conditionDao) { this.conditionDao = conditionDao; }
/*    */   
/*    */   public void addCondition(Condition condition)
/*    */   {
/* 26 */     this.conditionDao.add(condition);
/*    */   }
/*    */   
/*    */   public void delete(int id) {
/* 30 */     this.conditionDao.delete(id);
/*    */   }
/*    */   
/*    */   public void getConditions(int page, int pid, JsonBase result) {
/* 34 */     int totalPage = this.conditionDao.getTotalPage(pid);
/* 35 */     if (page > totalPage) {
/* 36 */       result.setMsg("指定页数错误");
/* 37 */       result.setSuccess(false);
/*    */     } else {
/* 39 */       int total = this.conditionDao.getTotalNum(pid);
/* 40 */       List<Condition> list = this.conditionDao.getConditions(page, pid);
/* 41 */       result.setResults(list);
/* 42 */       result.setTotal(total);
/* 43 */       result.setSuccess(true);
/*    */     }
/*    */   }
/*    */   
/* 47 */   public Condition getConditionById(int id) { return this.conditionDao.getConditionById(id); }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\service\ConditionManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */