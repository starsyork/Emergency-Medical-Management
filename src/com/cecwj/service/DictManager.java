/*    */ package com.cecwj.service;
/*    */ 
/*    */ import com.cecwj.dao.DictDao;
/*    */ import com.cecwj.model.Dict;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class DictManager
/*    */ {
/*    */   private DictDao dictDao;
/*    */   
/*    */   public DictDao getDictDao()
/*    */   {
/* 18 */     return this.dictDao;
/*    */   }
/*    */   
/*    */   @Resource
/* 22 */   public void setDictDao(DictDao dictDao) { this.dictDao = dictDao; }
/*    */   
/*    */   public List<Dict> getDicts(String term, String type2)
/*    */   {
/* 26 */     return this.dictDao.getDicts(term, type2);
/*    */   }
/*    */   public List<Dict> getDicts(String type2)
/*    */   {
/* 26 */     return this.dictDao.getDicts(type2);
/*    */   }
/*    */   
/*    */   public Dict getTermsByContent(String content)
/*    */   {
/* 31 */     return this.dictDao.getDictsByContent(content);
/*    */   }
/*    */   public Dict getTermsByContentA(String content)
/*    */   {
/* 31 */     return this.dictDao.getDictsByContentA(content);
/*    */   }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\service\DictManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */