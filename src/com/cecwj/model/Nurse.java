package com.cecwj.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class Nurse {
	   private int id;
	   private String name;
	   private int Sex;
	   private int IDcard;
	   private String Type;
	   private int nid;
	   private int Docflag;
	   private User user;
	   
	      @Id
	      @GeneratedValue
	      public int getId()
	      {
	    	  return this.id;
	      }
	      
	   	  public void setId(int id) { 
	   		  this.id = id; 
	   	  }
	   	  
	      @Column(name="Name")
	      public String getName() {
	   	     return this.name;
	      }
	      public void setName(String name) {
		   	  this.name=name;
		  }
	      @Column(name="IDcard")
	      public int getIDcard() {
	   	     return this.IDcard;
	      }
	      public void setIDcard(int idcard) {
		   	  this.IDcard=idcard;
		  }
	      @Column(name="Sex")
	      public int getSex()
	      {
	    	  return this.Sex;
	      }
	      
	   	  public void setSex(int Sex) { 
	   		  this.Sex = Sex; 
	   	}
	   	@Column(name="Type")
	   	  public String getType()
	      {
	    	  return this.Type;
	      }
	      
	   	  public void setType(String Type) { 
	   		  this.Type = Type; 
	   	}	
	   	
	   	  @OneToOne 
	   	  @JoinColumn(name = "nid") 
	   	  public User getUser() {
	       return this.user;
	   	   }
	   	  
	   	  public void setUser(User user) { 
	   		  this.user = user; 
	   	  }
	   	  
	   	  public int getDocflag() {
		       return this.Docflag;
		  }
		   	  
		 public void setDocflag(int Docflag) { 
		   	 this.Docflag = Docflag; 
		 }

}
