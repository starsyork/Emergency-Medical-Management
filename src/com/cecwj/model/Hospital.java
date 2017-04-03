/*    */ package com.cecwj.model;
/*    */ 
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.Table;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="hospital")
/*    */ public class Hospital
/*    */ {
/*    */   private int id;
/*    */   private String hospName;
/*    */   
/*    */   @Id
/*    */   @GeneratedValue
/*    */   public int getId()
/*    */   {
/* 20 */     return this.id;
/*    */   }
/*    */   
/* 23 */   public void setId(int id) { this.id = id; }
/*    */ @Column(name="name")
			public String getHospName() {
				return hospName;
			}
			public void setHospName(String hospName) {
				this.hospName = hospName;
			}   

/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\Section.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */