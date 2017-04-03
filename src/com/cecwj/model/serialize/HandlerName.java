/*    */ package com.cecwj.model.serialize;
/*    */ 
/*    */ import com.cecwj.common.TimeFormat;
/*    */ import com.cecwj.model.Handler;
/*    */ import com.cecwj.model.User;
/*    */ 
/*    */ public class HandlerName
/*    */ {
/*    */   private int id;
/*    */   private String exEr;
/*    */   private String exTime;
/*    */   private String exNote;
/*    */   
/*    */   public HandlerName(Handler handler)
/*    */   {
/* 16 */     this.id = handler.getId();
/* 17 */     this.exEr = handler.getUser().getUserName();
/* 18 */     this.exTime = TimeFormat.timeStampToString(handler.getHandle_time());
/* 19 */     this.exNote = handler.getRef();
/*    */   }
/*    */   
/*    */   public int getId() {
/* 23 */     return this.id;
/*    */   }
/*    */   
/* 26 */   public void setId(int id) { this.id = id; }
/*    */   
/*    */   public String getExEr() {
/* 29 */     return this.exEr;
/*    */   }
/*    */   
/* 32 */   public void setExEr(String exEr) { this.exEr = exEr; }
/*    */   
/*    */   public String getExTime() {
/* 35 */     return this.exTime;
/*    */   }
/*    */   
/* 38 */   public void setExTime(String exTime) { this.exTime = exTime; }
/*    */   
/*    */   public String getExNote() {
/* 41 */     return this.exNote;
/*    */   }
/*    */   
/* 44 */   public void setExNote(String exNote) { this.exNote = exNote; }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\serialize\HandlerName.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */