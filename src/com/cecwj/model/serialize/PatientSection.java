/*    */ package com.cecwj.model.serialize;
/*    */ 
/*    */ import com.cecwj.model.Patient;
/*    */ 
/*    */ public class PatientSection
/*    */ {
/*    */   private int id;
/*    */   private String name;
/*    */   private int sex;
/*    */   private String rfid;
/*    */   private int age;
/*    */   private String woundType;
/*    */   private String woundTime;
/*    */   private String woundAddr;
/*    */   private String sName;
/*    */   
/*    */   public PatientSection(Patient p, com.cecwj.model.Section s) {
/* 18 */     this.id = p.getId();
/* 19 */     this.name = p.getName();
/* 20 */     this.sex = p.getSex();
/* 21 */     this.rfid = p.getRfid();
/* 22 */     this.age = p.getAge();
/* 23 */     this.woundType = p.getWoundType();
/* 24 */     this.woundTime = p.getWoundTime();
/* 25 */     this.woundAddr = p.getWoundAddr();
/* 26 */     if (s != null)
/* 27 */       this.sName = s.getSecName();
/*    */   }
/*    */   
/* 30 */   public int getId() { return this.id; }
/*    */   
/*    */   public void setId(int id) {
/* 33 */     this.id = id;
/*    */   }
/*    */   
/* 36 */   public String getName() { return this.name; }
/*    */   
/*    */   public void setName(String name) {
/* 39 */     this.name = name;
/*    */   }
/*    */   
/* 42 */   public int getSex() { return this.sex; }
/*    */   
/*    */   public void setSex(int sex) {
/* 45 */     this.sex = sex;
/*    */   }
/*    */   
/* 48 */   public String getRfid() { return this.rfid; }
/*    */   
/*    */   public void setRfid(String rfid) {
/* 51 */     this.rfid = rfid;
/*    */   }
/*    */   
/* 54 */   public int getAge() { return this.age; }
/*    */   
/*    */   public void setAge(int age) {
/* 57 */     this.age = age;
/*    */   }
/*    */   
/* 60 */   public String getWoundType() { return this.woundType; }
/*    */   
/*    */   public void setWoundType(String woundType) {
/* 63 */     this.woundType = woundType;
/*    */   }
/*    */   
/* 66 */   public String getWoundTime() { return this.woundTime; }
/*    */   
/*    */   public void setWoundTime(String woundTime) {
/* 69 */     this.woundTime = woundTime;
/*    */   }
/*    */   
/* 72 */   public String getWoundAddr() { return this.woundAddr; }
/*    */   
/*    */   public void setWoundAddr(String woundAddr) {
/* 75 */     this.woundAddr = woundAddr;
/*    */   }
/*    */   
/* 78 */   public String getsName() { return this.sName; }
/*    */   
/*    */   public void setsName(String sName) {
/* 81 */     this.sName = sName;
/*    */   }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\serialize\PatientSection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */