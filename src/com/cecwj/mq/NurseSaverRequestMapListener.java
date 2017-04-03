/*    */ package com.cecwj.mq;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NurseSaverRequestMapListener
/*    */ {
/* 14 */   private Map<Integer, Message> messageMap = new HashMap();
/*    */   
/*    */   public Map<Integer, Message> getMessageMap() {
/* 17 */     return this.messageMap;
/*    */   }
/*    */   
/*    */   public void setMessageMap(Map<Integer, Message> messageMap) {
/* 21 */     this.messageMap = messageMap;
/*    */   }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\mq\NurseSaverRequestMapListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */