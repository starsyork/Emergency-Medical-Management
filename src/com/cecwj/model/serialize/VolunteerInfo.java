package com.cecwj.model.serialize;

import com.cecwj.model.Volunteer;

public class VolunteerInfo {
	/*    */   private int id;
	/*    */   private String loginName;
	/*    */   private String password;
	/*    */   private String roleStr;
	/*    */   private String userName;
	/*    */   private String sector;
	/*    */   private int secId;
			   private String Specialty;
			   private int Sex;
			   private long IDcard;			   
			   
	public VolunteerInfo(Volunteer Volunteer) {
		    this.id = Volunteer.getId();
		    this.Specialty = Volunteer.getSpecialty();
		    this.Sex = Volunteer.getSex();
		    this.IDcard = Volunteer.getIDcard();
		 
		    this.loginName = Volunteer.getUser().getLoginName();
		    this.password = Volunteer.getUser().getPassword();
		    this.roleStr = Volunteer.getUser().getRoleStr();
		    this.userName = Volunteer.getUser().getUserName();
		 //   this.sector = Volunteer.getUser().getSection().getSecName();
		//    this.secId = Volunteer.getUser().getSection().getId();
		// this.secId = Volunteer.getUser().getSection().getId();	
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
	     
		      public String getSpecialty() {
			return Specialty;
		}

		public void setSpecialty(String specialty) {
			Specialty = specialty;
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
