/*    */ package com.cecwj.service;
/*    */ 
/*    */ import com.cecwj.dao.BedDao;
/*    */ import com.cecwj.model.Bed;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ //床位管理
/*    */ @Component("bedManager")
/*    */ public class BedManager{
/*    */   private BedDao bedDao;
/*    */   
/*    */   public BedDao getBedDao(){
/* 18 */     return this.bedDao;
/*    */   }
/*    */   
/*    */   @Resource
/* 22 */   public void setBedDao(BedDao bedDao) { 
				this.bedDao = bedDao; 
			}
/*    */   
/*    */   public List<Bed> getBeds(int sid) {
/* 26 */     return this.bedDao.getBeds(sid);
/*    */   }
/*    */   
/*    */   public List<Bed> getBedsWeb(int sid) {
/* 30 */     return this.bedDao.getBedsWeb(sid);
/*    */   }
/*    */   
/*    */   public List<Bed> getBedsAdmin(int sid) {
/* 34 */     return this.bedDao.getBedsAdmin(sid);
/*    */   }
/*    */   
/*    */   public List<Bed> getUnAllocBed(int sid) {
/* 38 */     return this.bedDao.getUnAllocBed(sid);
/*    */   }
/*    */   
/* 41 */   public Bed getBedById(int id) { 
				return this.bedDao.getBedById(id); 
/*    */  		}
/*    */   public void updateBed(Bed bed) {
/* 44 */     this.bedDao.updateBed(bed);
/*    */   }
/*    */   
/* 47 */   public Bed getBedByPid(int pid) {
				return this.bedDao.getBedByPid(pid); 
				}
/*    */   
/*    */   public void add(Bed bed) {
/* 50 */     Bed temp = this.bedDao.getDeletedBed();
/* 51 */     if (temp == null) {
/* 52 */       this.bedDao.add(bed);
/*    */     } else {
/* 54 */       temp.setFlag(1);
/* 55 */       temp.setSectionid(bed.getSectionid());
/* 56 */       this.bedDao.updateBed(temp);
/*    */     }
/*    */   }
/*    */   
/* 60 */   public void delete(Bed bed) { bed.setFlag(0);
/* 61 */     this.bedDao.updateBed(bed);
/*    */   }
/*    */   
/*    */   public int getBedsNum(int sid) {
/* 65 */     return this.bedDao.getBedsNum(sid);
/*    */   }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\service\BedManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */