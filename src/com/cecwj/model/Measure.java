/*    */ package com.cecwj.model;
/*    */ 
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ 
/*    */ @javax.persistence.Entity
/*    */ @javax.persistence.Table(name="measure")
/*    */ public class Measure
/*    */ {
/*    */   private int id;
/*    */   private String content;
/*    */   
/*    */   @Id
/*    */   @GeneratedValue
/*    */   public int getId()
/*    */   {
/* 17 */     return this.id;
/*    */   }
/*    */   
/* 20 */   public void setId(int id) { this.id = id; }
/*    */   
/*    */   @javax.persistence.Column
/*    */   public String getContent() {
/* 24 */     return this.content;
/*    */   }
/*    */   
/* 27 */   public void setContent(String content) { this.content = content; }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\Measure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */