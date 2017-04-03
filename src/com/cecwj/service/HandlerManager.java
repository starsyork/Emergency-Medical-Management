/*    */ package com.cecwj.service;
/*    */ 
/*    */ import com.cecwj.dao.HandlerDao;
/*    */ import com.cecwj.model.Handler;
/*    */ import com.cecwj.model.serialize.HandlerName;
/*    */ import com.cecwj.model.serialize.JsonBase;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component("handlerManager")
/*    */ public class HandlerManager
/*    */ {
/*    */   private HandlerDao handlerDao;
/*    */   
/*    */   public HandlerDao getHandlerDao()
/*    */   {
/* 25 */     return this.handlerDao;
/*    */   }
/*    */   
/*    */   @Resource
/* 29 */   public void setHandlerDao(HandlerDao handlerDao) { this.handlerDao = handlerDao; }
/*    */   
/*    */   public void addHandler(Handler handler)
/*    */   {
/* 33 */     this.handlerDao.addHandler(handler);
/*    */   }
/*    */   
/*    */   public void deleteHandler(int id) {
/* 37 */     this.handlerDao.deleteHandler(id);
/*    */   }
/*    */   
/*    */   public void deleteHandlers(int id) {
/* 41 */     this.handlerDao.deleteHandlers(id);
/*    */   }
/*    */   
/*    */   public void getHandlers(int oid, int page, JsonBase result) {
/* 45 */     int totalPage = this.handlerDao.getTotalPage(oid);
/* 46 */     if (totalPage == 0) {
/* 47 */       result.setMsg("当前医嘱的执行记录为空");
/* 48 */       result.setSuccess(true);
/* 49 */       result.setTotal(0);
/*    */     }
/* 51 */     else if (totalPage < page) {
/* 52 */       result.setMsg("指定页数错误");
/* 53 */       result.setSuccess(false);
/*    */     } else {
/* 55 */       List<Handler> list = this.handlerDao.getHandlers(page, oid);
/* 56 */       List<HandlerName> handlers = new ArrayList();
/* 57 */       for (Handler handler : list) {
/* 58 */         HandlerName e = new HandlerName(handler);
/* 59 */         handlers.add(e);
/*    */       }
/* 61 */       int total = this.handlerDao.getTotalNum(oid);
/* 62 */       result.setResults(handlers);
/* 63 */       result.setTotal(total);
/* 64 */       result.setSuccess(true);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\service\HandlerManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */