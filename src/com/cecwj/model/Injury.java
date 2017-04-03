/*    */ package com.cecwj.model;
/*    */ 
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ 
/*    */ @javax.persistence.Entity
/*    */ @javax.persistence.Table(name="injury")
/*    */ public class Injury
/*    */ {
/*    */   private int id;
/*    */   private int pid;
/*    */   private int type;
/*    */   
/*    */   @Id
/*    */   @GeneratedValue
/*    */   public int getId()
/*    */   {
/* 19 */     return this.id;
/*    */   }
/*    */   
/* 22 */   public void setId(int id) { this.id = id; }
/*    */   
/*    */   @Column
/*    */   public int getPid() {
/* 26 */     return this.pid;
/*    */   }
/*    */   
/* 29 */   public void setPid(int pid) { this.pid = pid; }
/*    */   
/*    */   @Column
/*    */   public int getType() {
/* 33 */     return this.type;
/*    */   }
/*    */   
/* 36 */   public void setType(int type) { this.type = type; }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\Injury.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */