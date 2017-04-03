/*    */ package com.cecwj.model;
/*    */ 
/*    */ import java.sql.Timestamp;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.OneToOne;
/*    */ 
/*    */ @Entity
/*    */ @javax.persistence.Table(name="handler")
/*    */ public class Handler
/*    */ {
/*    */   private int id;
/*    */   private int oid;
/*    */   private int cid;
/*    */   private User user;
/*    */   private Timestamp handle_time;
/*    */   private String ref;
/*    */   
/*    */   @Id
/*    */   @GeneratedValue
/*    */   public int getId()
/*    */   {
/* 26 */     return this.id;
/*    */   }
/*    */   
/* 29 */   public void setId(int id) { this.id = id; }
/*    */   
/*    */   @Column
/*    */   public int getOid() {
/* 33 */     return this.oid;
/*    */   }
/*    */   
/* 36 */   public void setOid(int oid) { this.oid = oid; }
/*    */   
/*    */   @Column
/*    */   public int getCid() {
/* 40 */     return this.cid;
/*    */   }
/*    */   
/* 43 */   public void setCid(int cid) { this.cid = cid; }
/*    */   
/*    */   @Column
/*    */   public Timestamp getHandle_time() {
/* 47 */     return this.handle_time;
/*    */   }
/*    */   
/* 50 */   public void setHandle_time(Timestamp handle_time) { this.handle_time = handle_time; }
/*    */   
/*    */   @Column
/*    */   public String getRef() {
/* 54 */     return this.ref;
/*    */   }
/*    */   
/* 57 */   public void setRef(String ref) { this.ref = ref; }
/*    */   
/*    */   @OneToOne
/*    */   @JoinColumn(name="uid")
/*    */   public User getUser() {
/* 62 */     return this.user;
/*    */   }
/*    */   
/* 65 */   public void setUser(User user) { this.user = user; }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\Handler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */