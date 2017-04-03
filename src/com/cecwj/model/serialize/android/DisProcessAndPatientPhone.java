/*    */ package com.cecwj.model.serialize.android;
/*    */ 
/*    */ import com.cecwj.model.DisProcess;
/*    */ 
/*    */ public class DisProcessAndPatientPhone
/*    */ {
/*    */   private String name;
/*    */   private String docName;
/*    */   private String woundType;
/*    */   private String addTime;
/*    */   private String description;
/*    */   
/*    */   public DisProcessAndPatientPhone(com.cecwj.model.Patient patient, String docName, DisProcess disProcess)
/*    */   {
/* 15 */     this.name = patient.getName();
/* 16 */     this.docName = docName;
/* 17 */     this.woundType = patient.getWoundType();
/* 18 */     this.addTime = disProcess.getAddTime();
/* 19 */     this.description = disProcess.getDescription();
/*    */   }
/*    */   
/*    */   public String getName() {
/* 23 */     return this.name;
/*    */   }
/*    */   
/* 26 */   public void setName(String name) { this.name = name; }
/*    */   
/*    */   public String getDocName() {
/* 29 */     return this.docName;
/*    */   }
/*    */   
/* 32 */   public void setDocName(String docName) { this.docName = docName; }
/*    */   
/*    */   public String getWoundType() {
/* 35 */     return this.woundType;
/*    */   }
/*    */   
/* 38 */   public void setWoundType(String woundType) { this.woundType = woundType; }
/*    */   
/*    */   public String getAddTime() {
/* 41 */     return this.addTime;
/*    */   }
/*    */   
/* 44 */   public void setAddTime(String addTime) { this.addTime = addTime; }
/*    */   
/*    */   public String getDescription() {
/* 47 */     return this.description;
/*    */   }
/*    */   
/* 50 */   public void setDescription(String description) { this.description = description; }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\serialize\android\DisProcessAndPatientPhone.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */