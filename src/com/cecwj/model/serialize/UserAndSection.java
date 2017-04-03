/*    */ package com.cecwj.model.serialize;
/*    */ 
/*    */ import com.cecwj.model.User;
/*    */ 
/*    */ public class UserAndSection
/*    */ {
/*    */   private int id;
/*    */   private String loginName;
/*    */   private String password;
/*    */   private String roleStr;
/*    */   private String userName;
/*    */   private String sector;
/*    */   private int secId;
/*    */   

			
/*    */   public UserAndSection(User user) {
/* 16 */     this.id = user.getId();
/* 17 */     this.loginName = user.getLoginName();
/* 18 */     this.password = user.getPassword();
/* 19 */     this.roleStr = user.getRoleStr();
/* 20 */     this.userName = user.getUserName();
/* 21 */     this.sector = user.getSection().getSecName();
/* 22 */     this.secId = user.getSection().getId();
		
/*    */   }
/*    */   
/*    */   public int getId() {
/* 26 */     return this.id;
/*    */   }
/*    */   
/* 29 */   public void setId(int id) { this.id = id; }
/*    */   
/*    */   public String getLoginName() {
/* 32 */     return this.loginName;
/*    */   }
/*    */   
/* 35 */   public void setLoginName(String loginName) { this.loginName = loginName; }
/*    */   
/*    */   public String getPassword() {
/* 38 */     return this.password;
/*    */   }
/*    */   
/* 41 */   public void setPassword(String password) { this.password = password; }
/*    */   
/*    */   public String getRoleStr()
/*    */   {
/* 45 */     return this.roleStr;
/*    */   }
/*    */   
/*    */   public void setRoleStr(String roleStr) {
/* 49 */     this.roleStr = roleStr;
/*    */   }
/*    */   
/*    */   public String getUserName() {
/* 53 */     return this.userName;
/*    */   }
/*    */   
/* 56 */   public void setUserName(String userName) { this.userName = userName; }
/*    */   
/*    */   public String getSector()
/*    */   {
/* 60 */     return this.sector;
/*    */   }
/*    */   
/*    */   public void setSector(String sector) {
/* 64 */     this.sector = sector;
/*    */   }
/*    */   
/*    */   public int getSecId() {
/* 68 */     return this.secId;
/*    */   }
/*    */   
/*    */   public void setSecId(int secId) {
/* 72 */     this.secId = secId;
/*    */   }
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\serialize\UserAndSection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */