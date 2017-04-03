/*    */ package com.cecwj.model.serialize.android;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UserLocation
/*    */ {
/*    */   private double longitude;
/*    */   private double latitude;
/*    */   private String deviceId;
/*    */   private Date date;
/*    */   
/*    */   public double getLongitude()
/*    */   {
/* 18 */     return this.longitude;
/*    */   }
/*    */   
/* 21 */   public void setLongitude(double longitude) { this.longitude = longitude; }
/*    */   
/*    */   public double getLatitude() {
/* 24 */     return this.latitude;
/*    */   }
/*    */   
/* 27 */   public void setLatitude(double latitude) { this.latitude = latitude; }
/*    */   
/*    */   public String getDeviceId()
/*    */   {
/* 31 */     return this.deviceId;
/*    */   }
/*    */   
/* 34 */   public void setDeviceId(String deviceId) { this.deviceId = deviceId; }
/*    */   
/*    */   public Date getDate() {
/* 37 */     return this.date;
/*    */   }
/*    */   
/* 40 */   public void setDate(Date date) { this.date = date; }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\serialize\android\UserLocation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */