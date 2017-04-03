/*    */ package com.cecwj.model.serialize;
/*    */ 
/*    */ import com.cecwj.model.TermAll;
/*    */ 
/*    */ public class DrugAndUnit
/*    */ {
/*    */   private String unit;
/*    */   private String content;
/*    */   
/*    */   public DrugAndUnit(TermAll termAll)
/*    */   {
/* 12 */     if (termAll != null) {
/* 13 */       this.unit = termAll.getUnits();
/* 14 */       this.content = termAll.getName();
/*    */     }
/*    */   }
/*    */   
/*    */   public DrugAndUnit(com.cecwj.model.Dict dict) {
/* 19 */     this.unit = null;
/* 20 */     this.content = dict.getName();
/*    */   }
/*    */   
/*    */   public String getUnit()
/*    */   {
/* 25 */     return this.unit;
/*    */   }
/*    */   
/*    */   public void setUnit(String unit) {
/* 29 */     this.unit = unit;
/*    */   }
/*    */   
/*    */   public String getContent() {
/* 33 */     return this.content;
/*    */   }
/*    */   
/*    */   public void setContent(String content) {
/* 37 */     this.content = content;
/*    */   }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\serialize\DrugAndUnit.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */