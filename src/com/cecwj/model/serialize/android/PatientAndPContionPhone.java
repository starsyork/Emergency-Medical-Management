/*    */ package com.cecwj.model.serialize.android;
/*    */ 
/*    */ import com.cecwj.model.PCondition;
/*    */ 
/*    */ public class PatientAndPContionPhone
/*    */ {
/*    */   private String name;
/*    */   private String docName;
/*    */   private String woundType;
/*    */   private float pulse;
/*    */   private float diastolic;
/*    */   private float systolic;
/*    */   private float temperature;
/*    */   private String comment;
/*    */   private String time;
/*    */   
/*    */   public PatientAndPContionPhone(com.cecwj.model.Patient patient, String docName, PCondition pCondition)
/*    */   {
/* 19 */     this.name = patient.getName();
/* 20 */     this.docName = docName;
/* 21 */     this.woundType = patient.getWoundType();
/* 22 */     this.pulse = pCondition.getPulse();
/* 23 */     this.systolic = pCondition.getSystolic();
/* 24 */     this.temperature = pCondition.getTemperature();
/* 25 */     this.comment = pCondition.getComment();
/* 26 */     this.time = pCondition.getTime();
/*    */   }
/*    */   
/*    */   public PatientAndPContionPhone(com.cecwj.model.Patient patient, PCondition pCondition) {
/* 30 */     this.name = patient.getName();
/* 31 */     this.woundType = patient.getWoundType();
/* 32 */     this.pulse = pCondition.getPulse();
/* 33 */     this.systolic = pCondition.getSystolic();
/* 34 */     this.temperature = pCondition.getTemperature();
/* 35 */     this.comment = pCondition.getComment();
/* 36 */     this.time = pCondition.getTime();
/*    */   }
/*    */   
/*    */   public String getName() {
/* 40 */     return this.name;
/*    */   }
/*    */   
/* 43 */   public void setName(String name) { this.name = name; }
/*    */   
/*    */   public String getDocName() {
/* 46 */     return this.docName;
/*    */   }
/*    */   
/* 49 */   public void setDocName(String docName) { this.docName = docName; }
/*    */   
/*    */   public String getWoundType() {
/* 52 */     return this.woundType;
/*    */   }
/*    */   
/* 55 */   public void setWoundType(String woundType) { this.woundType = woundType; }
/*    */   
/*    */   public float getPulse() {
/* 58 */     return this.pulse;
/*    */   }
/*    */   
/* 61 */   public void setPulse(float pulse) { this.pulse = pulse; }
/*    */   
/*    */   public float getDiastolic() {
/* 64 */     return this.diastolic;
/*    */   }
/*    */   
/* 67 */   public void setDiastolic(float diastolic) { this.diastolic = diastolic; }
/*    */   
/*    */   public float getSystolic() {
/* 70 */     return this.systolic;
/*    */   }
/*    */   
/* 73 */   public void setSystolic(float systolic) { this.systolic = systolic; }
/*    */   
/*    */   public float getTemperature() {
/* 76 */     return this.temperature;
/*    */   }
/*    */   
/* 79 */   public void setTemperature(float temperature) { this.temperature = temperature; }
/*    */   
/*    */   public String getComment() {
/* 82 */     return this.comment;
/*    */   }
/*    */   
/* 85 */   public void setComment(String comment) { this.comment = comment; }
/*    */   
/*    */   public String getTime() {
/* 88 */     return this.time;
/*    */   }
/*    */   
/* 91 */   public void setTime(String time) { this.time = time; }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\serialize\android\PatientAndPContionPhone.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */