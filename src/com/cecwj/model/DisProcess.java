/*    */ package com.cecwj.model;
/*    */ 
/*    */ import com.cecwj.common.TimeFormat;
/*    */ import java.sql.Timestamp;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.Table;
/*    */ import javax.persistence.Transient;
/*    */ 
/*    */ 
/*    */ @Entity
/*    */ @Table(name="disProcess")
/*    */ public class DisProcess
/*    */ {
/*    */   private int id;
/*    */   private int uid;
/*    */   private int pid;
/*    */   private Timestamp add_date;
/*    */   private String addTime;
/*    */   private String description;
/*    */   private String doc;
/*    */   
/*    */   @Id
/*    */   @GeneratedValue
/*    */   public int getId()
/*    */   {
/* 29 */     return this.id;
/*    */   }
/*    */   
/* 32 */   public void setId(int id) { this.id = id; }
/*    */   
/*    */   @Column
/*    */   public int getUid() {
/* 36 */     return this.uid;
/*    */   }
/*    */   
/* 39 */   public void setUid(int uid) { this.uid = uid; }
/*    */   
/*    */   @Column
/*    */   public int getPid() {
/* 43 */     return this.pid;
/*    */   }
/*    */   
/* 46 */   public void setPid(int pid) { this.pid = pid; }
/*    */   
/*    */   @Column(name="add_date")
/*    */   public Timestamp getAdd_date() {
/* 50 */     return this.add_date;
/*    */   }
/*    */   
/* 53 */   public void setAdd_date(Timestamp add_date) { this.add_date = add_date; }
/*    */   
/*    */   @Column(name="comment")
/*    */   public String getDescription() {
/* 57 */     return this.description;
/*    */   }
/*    */   
/* 60 */   public void setDescription(String description) { this.description = description; }
/*    */   
/*    */   @Column(name="advice_path")
/*    */   public String getDoc() {
/* 64 */     return this.doc;
/*    */   }
/*    */   
/* 67 */   public void setDoc(String doc) { this.doc = doc; }
/*    */   
/*    */   @Transient
/*    */   public String getAddTime() {
/* 71 */     if (this.addTime == null) {
/* 72 */       this.addTime = (this.add_date != null ? TimeFormat.timeStampToString(this.add_date) : null);
/*    */     }
/* 74 */     return this.addTime;
/*    */   }
/*    */   
/* 77 */   public void setAddTime(String addTime) { this.addTime = addTime;
/* 78 */     if (addTime.equals("")) {
/* 79 */       return;
/*    */     }
/* 81 */     this.add_date = TimeFormat.stringToTimestamp(addTime);
/*    */   }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\DisProcess.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */