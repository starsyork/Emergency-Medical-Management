/*    */ package com.cecwj.model;
/*    */ 
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.Table;
/*    */ import javax.persistence.Transient;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="disposal")
/*    */ public class Disposal
/*    */ {
/*    */   private int id;
/*    */   private int mid;
/*    */   private int pid;
/*    */   private String time;
/*    */   private String type;
/*    */   private String measure;
/*    */   
/*    */   @Id
/*    */   @GeneratedValue
/*    */   public int getId()
/*    */   {
/* 25 */     return this.id;
/*    */   }
/*    */   
/* 28 */   public void setId(int id) { this.id = id; }
/*    */   
/*    */   @Column
/*    */   public int getMid() {
/* 32 */     return this.mid;
/*    */   }
/*    */   
/* 35 */   public void setMid(int mid) { this.mid = mid; }
/*    */   
/*    */   @Column
/*    */   public int getPid() {
/* 39 */     return this.pid;
/*    */   }
/*    */   
/* 42 */   public void setPid(int pid) { this.pid = pid; }
/*    */   
/*    */   @Column
/*    */   public String getTime() {
/* 46 */     return this.time;
/*    */   }
/*    */   
/* 49 */   public void setTime(String time) { this.time = time; }
/*    */   
/*    */   @Column
/*    */   public String getType() {
/* 53 */     return this.type;
/*    */   }
/*    */   
/* 56 */   public void setType(String type) { this.type = type; }
/*    */   
/*    */   @Transient
/*    */   public String getMeasure() {
/* 60 */     return this.measure;
/*    */   }
/*    */   
/* 63 */   public void setMeasure(String measure) { this.measure = measure; }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\Disposal.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */