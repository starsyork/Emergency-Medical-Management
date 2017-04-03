package com.cecwj.model;

import javax.persistence.*;

@Entity
@Table(name="doctor")
public class Doctor {
	   private int id;
	   private String name;
	   private int Sex;
	   private long IDcard;
	   private String Type;
	   private int uid;
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

	      public long getIDcard() {
			return IDcard;
		}

		public void setIDcard(long iDcard) {
			IDcard = iDcard;
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
	   	
	   	  @ManyToOne 
	   	  @JoinColumn(name = "uid") 
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
