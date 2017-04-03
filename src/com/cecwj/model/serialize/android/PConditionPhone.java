/*    */ package com.cecwj.model.serialize.android;
/*    */ 
/*    */ import com.cecwj.model.PCondition;
/*    */ 
/*    */ public class PConditionPhone
/*    */ {
/*    */   private String comment;
/*    */   private float pulse;
/*    */   private float diastolic;
/*    */   private float systolic;
/*    */   private float temperature;
/*    */   private float breath;
/*    */   private String time;
/*    */   
/*    */   public PConditionPhone(PCondition pcondition) {
/* 16 */     if (pcondition != null) {
/* 17 */       this.comment = pcondition.getComment();
/* 18 */       this.pulse = pcondition.getPulse();
/* 19 */       this.diastolic = pcondition.getDiastolic();
/* 20 */       this.temperature = pcondition.getTemperature();
/* 21 */       this.systolic = pcondition.getSystolic();
/* 22 */       this.time = pcondition.getTime();
/* 23 */       this.breath = pcondition.getBreath();
/*    */     }
/*    */   }
/*    */   
/* 27 */   public String getComment() { return this.comment; }
/*    */   
/*    */   public void setComment(String comment) {
/* 30 */     this.comment = comment;
/*    */   }
/*    */   
/* 33 */   public float getPulse() { return this.pulse; }
/*    */   
/*    */   public void setPulse(float pulse) {
/* 36 */     this.pulse = pulse;
/*    */   }
/*    */   
/* 39 */   public float getDiastolic() { return this.diastolic; }
/*    */   
/*    */   public void setDiastolic(float diastolic) {
/* 42 */     this.diastolic = diastolic;
/*    */   }
/*    */   
/* 45 */   public float getSystolic() { return this.systolic; }
/*    */   
/*    */   public void setSystolic(float systolic) {
/* 48 */     this.systolic = systolic;
/*    */   }
/*    */   
/* 51 */   public String getTime() { return this.time; }
/*    */   
/*    */   public void setTime(String time) {
/* 54 */     this.time = time;
/*    */   }
/*    */   
/* 57 */   public float getTemperature() { return this.temperature; }
/*    */   
/*    */   public void setTemperature(float temperature) {
/* 60 */     this.temperature = temperature;
/*    */   }
/*    */   
/* 63 */   public float getBreath() { return this.breath; }
/*    */   
/*    */   public void setBreath(float breath) {
/* 66 */     this.breath = breath;
/*    */   }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\serialize\android\PConditionPhone.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */