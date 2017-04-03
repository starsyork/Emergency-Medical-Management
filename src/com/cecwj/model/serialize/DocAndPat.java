/*    */ package com.cecwj.model.serialize;
/*    */ 
/*    */ import com.cecwj.model.DocAdvice;
/*    */ 
/*    */ public class DocAndPat
/*    */ {
/*    */   private int id;
/*    */   private String name;
/*    */   private int bedNum;
/*    */   private String type;
/*    */   private String content;
/*    */   private String startTime;
/*    */   
/*    */   public DocAndPat(String name, int bedNum, DocAdvice docAdvice) {
/* 15 */     this.name = name;
/* 16 */     this.bedNum = bedNum;
/* 17 */     this.id = docAdvice.getId();
/* 18 */     this.type = docAdvice.getType();
/* 19 */     this.content = docAdvice.getContent();
/* 20 */     this.startTime = docAdvice.getStartTime();
/*    */   }
/*    */   
/*    */ 
/*    */   public int getId()
/*    */   {
/* 26 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id)
/*    */   {
/* 31 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getName()
/*    */   {
/* 36 */     return this.name;
/*    */   }
/*    */   
/* 39 */   public void setName(String name) { this.name = name; }
/*    */   
/*    */   public int getBedNum() {
/* 42 */     return this.bedNum;
/*    */   }
/*    */   
/* 45 */   public void setBedNum(int bedNum) { this.bedNum = bedNum; }
/*    */   
/*    */   public String getType() {
/* 48 */     return this.type;
/*    */   }
/*    */   
/* 51 */   public void setType(String type) { this.type = type; }
/*    */   
/*    */   public String getContent() {
/* 54 */     return this.content;
/*    */   }
/*    */   
/* 57 */   public void setContent(String content) { this.content = content; }
/*    */   
/*    */   public String getStartTime() {
/* 60 */     return this.startTime;
/*    */   }
/*    */   
/* 63 */   public void setStartTime(String startTime) { this.startTime = startTime; }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\serialize\DocAndPat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */