/*    */ package com.cecwj.model;
/*    */ 
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.Table;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="section")
/*    */ public class Section
/*    */ {
/*    */   private int id;
/*    */   private String secName;
/*    */   
/*    */   @Id
/*    */   @GeneratedValue
/*    */   public int getId()
/*    */   {
/* 20 */     return this.id;
/*    */   }
/*    */   
/* 23 */   public void setId(int id) { this.id = id; }
/*    */   
/*    */   @Column(name="name")
/*    */   public String getSecName() {
/* 27 */     return this.secName;
/*    */   }
/*    */   
/* 30 */   public void setSecName(String secName) { this.secName = secName; }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\Section.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */