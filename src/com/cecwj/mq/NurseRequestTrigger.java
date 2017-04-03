/*    */ package com.cecwj.mq;
/*    */ 
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ public class NurseRequestTrigger
/*    */ {
/*    */   private RequestMapListeners queueListeners;
/*    */   
/*    */   public RequestMapListeners getQueueListeners()
/*    */   {
/* 12 */     return this.queueListeners;
/*    */   }
/*    */   
/*    */   @javax.annotation.Resource
/* 16 */   public void setQueueListeners(RequestMapListeners queueListeners) { 
				this.queueListeners = queueListeners; 
			}
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\mq\NurseRequestTrigger.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */