/*    */ package com.cecwj.model.serialize;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class JsonBase implements Serializable
/*    */ {
/*    */   boolean success;
/*    */   Object results;
/*    */   String msg;
/*    */   int total;
/*    */   
/*    */   public int getTotal() {
/* 13 */     return this.total;
/*    */   }
/*    */   
/* 16 */   public void setTotal(int total2) { this.total = total2; }
/*    */   
/*    */   public Object getResults() {
/* 19 */     return this.results;
/*    */   }
/*    */   
/* 22 */   public void setResults(Object results) { this.results = results; }
/*    */   
/*    */   public boolean isSuccess() {
/* 25 */     return this.success;
/*    */   }
/*    */   
/* 28 */   public void setSuccess(boolean success) { this.success = success; }
/*    */   
/*    */   public String getMsg() {
/* 31 */     return this.msg;
/*    */   }
/*    */   
/* 34 */   public void setMsg(String msg) { this.msg = msg; }
/*    */   
/*    */ 
/*    */ 
/*    */   public JsonBase()
/*    */   {
/* 40 */     this.results = null;
/* 41 */     this.success = true;
/* 42 */     this.msg = null;
/*    */   }
/*    */   
/* 45 */   public JsonBase(String msg, Object results) { this.success = true;
/* 46 */     this.results = results;
/* 47 */     this.msg = msg;
/*    */   }
/*    */   
/* 50 */   public JsonBase(String msg, boolean success) { this.success = success;
/* 51 */     this.results = null;
/* 52 */     this.msg = msg;
/*    */   }
/*    */   
/* 55 */   public JsonBase(Object results) { this.msg = null;
/* 56 */     this.results = results;
/* 57 */     if (results != null) {
/* 58 */       this.success = true;
/*    */     } else {
/* 60 */       this.success = false;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\serialize\JsonBase.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */