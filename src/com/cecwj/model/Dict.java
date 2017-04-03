/*    */ package com.cecwj.model;
/*    */ 
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.Table;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="dict")
/*    */ public class Dict
/*    */ {
/*    */   private String name;
/*    */   private String clas;
/*    */   private String itemCode;
/*    */   private String flag;
/*    */   private String code;
/*    */   
/*    */   @Id
/*    */   @GeneratedValue
/*    */   @Column(name="ITEM_NAME")
/*    */   public String getName()
/*    */   {
/* 24 */     return this.name;
/*    */   }
/*    */   
/* 27 */   public void setName(String name) { this.name = name; }
/*    */   
/*    */   @Column(name="ITEM_CLASS")
/*    */   public String getClas() {
/* 31 */     return this.clas;
/*    */   }
/*    */   
/* 34 */   public void setClas(String clas) { this.clas = clas; }
/*    */   
/*    */   @Column(name="ITEM_CODE")
/*    */   public String getItemCode() {
/* 38 */     return this.itemCode;
/*    */   }
/*    */   
/* 41 */   public void setItemCode(String itemCode) { this.itemCode = itemCode; }
/*    */   
/*    */   @Column(name="STD_INDICATOR")
/*    */   public String getFlag() {
/* 45 */     return this.flag;
/*    */   }
/*    */   
/* 48 */   public void setFlag(String flag) { this.flag = flag; }
/*    */   
/*    */   @Column(name="INPUT_CODE")
/*    */   public String getCode() {
/* 52 */     return this.code;
/*    */   }
/*    */   
/* 55 */   public void setCode(String code) { this.code = code; }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\Dict.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */