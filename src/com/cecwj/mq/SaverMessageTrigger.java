/*    */ package com.cecwj.mq;
/*    */ 
/*    */ import com.cecwj.service.PatientManager;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class SaverMessageTrigger
/*    */ {
/*    */   private RequestMapListeners requestMapListeners;
/*    */   private PatientManager patientManager;
/*    */   
/*    */   public PatientManager getPatientManager()
/*    */   {
/* 19 */     return this.patientManager;
/*    */   }
/*    */   
/*    */   @Resource
/* 23 */   public void setPatientManager(PatientManager patientManager) 
			{ 
				this.patientManager = patientManager;
			}
/*    */   
/*    */   public RequestMapListeners getRequestMapListeners()
/*    */   {
/* 27 */     return this.requestMapListeners;
/*    */   }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\mq\SaverMessageTrigger.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */