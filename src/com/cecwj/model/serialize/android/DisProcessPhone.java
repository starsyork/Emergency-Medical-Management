/*    */ package com.cecwj.model.serialize.android;
/*    */ 
/*    */ import com.cecwj.model.DisProcess;
/*    */ 
/*    */ public class DisProcessPhone
/*    */ {
/*    */   private int id;
/*    */   private String description;
/*    */   private String addTime;
/*    */   
/*    */   public DisProcessPhone(DisProcess disProcess) {
/* 12 */     this.addTime = disProcess.getAddTime();
/* 13 */     this.description = disProcess.getDescription();
/* 14 */     this.id = disProcess.getId();
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 18 */     return this.description;
/*    */   }
/*    */   
/* 21 */   public void setDescription(String description) { this.description = description; }
/*    */   
/*    */   public String getAddTime() {
/* 24 */     return this.addTime;
/*    */   }
/*    */   
/* 27 */   public void setAddTime(String addTime) { this.addTime = addTime; }
/*    */   
/*    */   public int getId() {
/* 30 */     return this.id;
/*    */   }
/*    */   
/* 33 */   public void setId(int id) { this.id = id; }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\serialize\android\DisProcessPhone.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */