/*    */ package com.cecwj.service;
/*    */ 
/*    */ import com.cecwj.dao.SectionDao;
/*    */ import com.cecwj.model.Section;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component("sectionManager")
/*    */ public class SectionManager
/*    */ {
/*    */   private SectionDao sectionDao;
/*    */   
/*    */   public SectionDao getSectionDao()
/*    */   {
/* 19 */     return this.sectionDao;
/*    */   }
/*    */   
/*    */   @Resource
/* 23 */   public void setSectionDao(SectionDao sectionDao) { this.sectionDao = sectionDao; }
/*    */   
/*    */   public void addSection(Section section)
/*    */   {
/* 27 */     this.sectionDao.add(section);
/*    */   }
/*    */   
/*    */   public void deleteSection(int id) {
/* 31 */     this.sectionDao.delete(id);
/*    */   }
/*    */   
/* 34 */   public Section getSectionById(int sid) { return this.sectionDao.getSectionById(sid); }
/*    */   
/*    */   public Section getSectionByName(String secName) {
/* 37 */     return this.sectionDao.getSectionByName(secName);
/*    */   }
/*    */   
/*    */   public List<Section> getAllSection() {
/* 41 */     List<Section> sections = this.sectionDao.getAllSection();
/* 42 */     return sections;
/*    */   }
/*    */   
/*    */   public void update(Section section) {
/* 46 */     this.sectionDao.update(section);
/*    */   }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\service\SectionManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */