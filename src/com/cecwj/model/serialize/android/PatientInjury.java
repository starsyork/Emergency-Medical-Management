/*    */ package com.cecwj.model.serialize.android;
/*    */ 
/*    */ import com.cecwj.model.Patient;
/*    */ 
/*    */ public class PatientInjury
/*    */ {
/*    */   private int id;
/*    */   private String woundType;
/*    */   private String woundTime;
/*    */   private String name;
/*    */   private int type;
/*    */   
/*    */   public PatientInjury(Patient p, com.cecwj.model.Injury injury) {
/* 14 */     if (p != null) {
/* 15 */       this.id = p.getId();
/* 16 */       this.woundType = p.getWoundType();
/* 17 */       this.woundTime = p.getWoundTime();
/* 18 */       this.name = p.getName();
/*    */     }
/* 20 */     if (injury != null) {
/* 21 */       this.type = injury.getType();
/*    */     }
/*    */   }
/*    */   
/*    */   public int getId() {
/* 26 */     return this.id;
/*    */   }
/*    */   
/* 29 */   public void setId(int id) { this.id = id; }
/*    */   
/*    */   public String getWoundType() {
/* 32 */     return this.woundType;
/*    */   }
/*    */   
/* 35 */   public void setWoundType(String woundType) { this.woundType = woundType; }
/*    */   
/*    */   public String getWoundTime() {
/* 38 */     return this.woundTime;
/*    */   }
/*    */   
/* 41 */   public void setWoundTime(String woundTime) { this.woundTime = woundTime; }
/*    */   
/*    */   public String getName() {
/* 44 */     return this.name;
/*    */   }
/*    */   
/* 47 */   public void setName(String name) { this.name = name; }
/*    */   
/*    */   public int getType() {
/* 50 */     return this.type;
/*    */   }
/*    */   
/* 53 */   public void setType(int type) { this.type = type; }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\serialize\android\PatientInjury.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */