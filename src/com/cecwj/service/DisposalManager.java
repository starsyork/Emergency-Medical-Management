/*    */ package com.cecwj.service;
/*    */ 
/*    */ import com.cecwj.dao.DisposalDao;
/*    */ import com.cecwj.model.Disposal;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class DisposalManager
/*    */ {
/*    */   private DisposalDao disposalDao;
/*    */   
/*    */   public DisposalDao getDisposalDao()
/*    */   {
/* 17 */     return this.disposalDao;
/*    */   }
/*    */   
/*    */   @Resource
/* 21 */   public void setDisposalDao(DisposalDao disposalDao) 
			{
				this.disposalDao = disposalDao;
			}
/*    */   
/*    */   public void addDisposal(Disposal disposal)
/*    */   {
/* 25 */     this.disposalDao.addDisposal(disposal);
/*    */   }
/*    */   
/*    */   public Disposal getDisposal(int id) {
/* 29 */     return this.disposalDao.getDisposal(id);
/*    */   }
/*    */   
/*    */   public int getTotalNum(int pid) {
/* 33 */     return this.disposalDao.getTotalNum(pid);
/*    */   }
/*    */   
/*    */   public List<Disposal> getDisposals(int pid, int page) {
/* 37 */     return this.disposalDao.getDisposals(pid, page);
/*    */   }
/*    */   public List<Disposal> getDisposalsA(int pid) {
/* 37 */     return this.disposalDao.getDisposalsA(pid);
/*    */   }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\service\DisposalManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */