/*    */ package com.cecwj.model.serialize.android;
/*    */ 
/*    */ import com.cecwj.model.DocAdvice;
/*    */ 
/*    */ public class DocAdvicePhone {
/*    */   private int id;
/*    */   private String content;
/*    */   private String startTime;
/*    */   private String type2;
/*    */   private String dose;
/*    */   
/* 12 */   public DocAdvicePhone(DocAdvice docAdvice) { this.id = docAdvice.getId();
/* 13 */     this.content = docAdvice.getContent();
/* 14 */     this.startTime = docAdvice.getStartTime();
/* 15 */     this.type2 = docAdvice.getType2();
/* 16 */     this.dose = docAdvice.getDose();
/*    */   }
/*    */   
/*    */   public int getId() {
/* 20 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 24 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getContent() {
/* 28 */     return this.content;
/*    */   }
/*    */   
/* 31 */   public void setContent(String content) { this.content = content; }
/*    */   
/*    */   public String getStartTime() {
/* 34 */     return this.startTime;
/*    */   }
/*    */   
/* 37 */   public void setStartTime(String startTime) { this.startTime = startTime; }
/*    */   
/*    */   public String getType2() {
/* 40 */     return this.type2;
/*    */   }
/*    */   
/* 43 */   public void setType2(String type2) { this.type2 = type2; }
/*    */   
/*    */   public String getDose() {
/* 46 */     return this.dose;
/*    */   }
/*    */   
/* 49 */   public void setDose(String dose) { this.dose = dose; }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\serialize\android\DocAdvicePhone.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */