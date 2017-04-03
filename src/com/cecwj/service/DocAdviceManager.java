/*    */ package com.cecwj.service;
/*    */ 
/*    */ import com.cecwj.dao.DocAdviceDao;
/*    */ import com.cecwj.model.DocAdvice;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class DocAdviceManager
/*    */ {
/*    */   private DocAdviceDao docAdviceDao;
/*    */   
/*    */   public DocAdviceDao getDocAdviceDao()
/*    */   {
/* 19 */     return this.docAdviceDao;
/*    */   }
/*    */   
/*    */   @Resource
/* 23 */   public void setDocAdviceDao(DocAdviceDao docAdviceDao) { this.docAdviceDao = docAdviceDao; }
/*    */   
/*    */   public DocAdvice getDocAdviceById(int id)
/*    */   {
/* 27 */     return this.docAdviceDao.getDocAdviceById(id);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public List<DocAdvice> getDocAdviceByPid(int page, int pid)
/*    */   {
/* 38 */     return this.docAdviceDao.getDocAdviceByPid(page, pid);
/*    */   }
/*    */   /*    */   public List<DocAdvice> getDocAdviceByPid( int pid)
/*    */   {
/* 38 */     return this.docAdviceDao.getDocAdviceByPid( pid);
/*    */   }
/*    */   public List<DocAdvice> getDocAdviceByPidAndNoStop(int pid) {
/* 42 */     List<DocAdvice> docAdvices = this.docAdviceDao.getDocAdviceByPidAndNoStop(pid);
/* 43 */     return docAdvices;
/*    */   }
/*    */   
/*    */   public List<DocAdvice> getDocAdviceByPidAndStop(int pid) {
/* 47 */     List<DocAdvice> docAdvices = this.docAdviceDao.getDocAdviceByPidAndStop(pid);
/* 48 */     return docAdvices;
/*    */   }
/*    */   
/*    */ 
/*    */   public void add(DocAdvice docAdvice)
/*    */   {
/* 54 */     this.docAdviceDao.add(docAdvice);
/*    */   }
/*    */   
/*    */   public void delete(DocAdvice docAdvice) {
/* 58 */     this.docAdviceDao.delete(docAdvice);
/*    */   }
/*    */   
/*    */   public void update(DocAdvice docAdvice) {
/* 62 */     this.docAdviceDao.update(docAdvice);
/*    */   }
/*    */   
/*    */   public List<DocAdvice> testNewAdvice(int pid, int page)
/*    */   {
/* 67 */     return this.docAdviceDao.getDocAdviceByFlag(pid, page);
/*    */   }
/*    */   
/*    */   public List<DocAdvice> getDocAdviceByFlag(int pid, int page)
/*    */   {
/* 72 */     return this.docAdviceDao.getDocAdviceByFlag(pid, page);
/*    */   }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\service\DocAdviceManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */