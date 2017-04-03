package com.cecwj.model.serialize;

import javax.persistence.Column;

import com.cecwj.model.User;
import com.cecwj.model.Doctor;

public class DoctorInfo {
	/*    */   private int id;
	/*    */   private String loginName;
	/*    */   private String password;
	/*    */   private String roleStr;
	/*    */   private String userName;
	/*    */   private String sector;
	/*    */   private int secId;
			   private String name;
			   private int Sex;
			   private long IDcard;			   
			   
	public DoctorInfo(Doctor doctor) {
		    this.id = doctor.getId();
		 this.name = doctor.getName();
		 this.Sex = doctor.getSex();
		 this.IDcard = doctor.getIDcard();
		 
		    this.loginName = doctor.getUser().getLoginName();
		    this.password = doctor.getUser().getPassword();
		    this.roleStr = doctor.getUser().getRoleStr();
		    this.userName = doctor.getUser().getUserName();
		    this.sector = doctor.getUser().getSection().getSecName();
		    this.secId = doctor.getUser().getSection().getId();
		// this.secId = doctor.getUser().getSection().getId();	
		  }
		  
		  public int getId() {
		    return this.id;
		  }
		  
		  public void setId(int id) { this.id = id; }
		  
		  public String getLoginName() {
		    return this.loginName;
		  }
		  
		  public void setLoginName(String loginName) { this.loginName = loginName; }
		  
		  public String getPassword() {
		    return this.password;
		  }
		  
		  public void setPassword(String password) { this.password = password; }
		  
		  public String getRoleStr()
		  {
		    return this.roleStr;
		  }
		  
		  public void setRoleStr(String roleStr) {
		    this.roleStr = roleStr;
		  }
		  
		  public String getUserName() {
		    return this.userName;
		  }
		  
		  public void setUserName(String userName) { this.userName = userName; }
		  
		  public String getSector()
		  {
		    return this.sector;
		  }
		  
		  public void setSector(String sector) {
		    this.sector = sector;
		  }
		  
		  public int getSecId() {
		    return this.secId;
		  }
		  
		  public void setSecId(int secId) {
		/* 72 */     this.secId = secId;
		/*    */   }
		  public String getName() {
		   	     return this.name;
		      }
		      public void setName(String name) {
			   	  this.name=name;
			  }
		     
		      public long getIDcard() {
		   	     return this.IDcard;
		      }
		      public void setIDcard(long idcard) {
			   	  this.IDcard=idcard;
			  }
		     
		      public int getSex()
		      {
		    	  return this.Sex;
		      }
		      
		   	  public void setSex(int Sex) { 
		   		  this.Sex = Sex; 
		   	}
		   	
		   	
}
