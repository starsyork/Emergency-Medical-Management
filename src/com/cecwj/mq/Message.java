/*    */ package com.cecwj.mq;
/*    */ 
/*    */ import java.sql.Timestamp;
/*    */ 
/*    */ public class Message
/*    */ {
/*    */   String message;
/*    */   int repeat;
/*    */   Timestamp requestTime;
/*    */   
/*    */   public int getRepeat() {
/* 12 */     return this.repeat;
/*    */   }
/*    */   
/* 15 */   public void setRepeat(int repeat) { this.repeat = repeat; }
/*    */   
/*    */   public String getMessage() {
/* 18 */     return this.message;
/*    */   }
/*    */   
/* 21 */   public void setMessage(String message) { this.message = message; }
/*    */   
/*    */   public Timestamp getRequestTime() {
/* 24 */     return this.requestTime;
/*    */   }
/*    */   
/* 27 */   public void setRequestTime(Timestamp requestTime) { this.requestTime = requestTime; }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\mq\Message.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */