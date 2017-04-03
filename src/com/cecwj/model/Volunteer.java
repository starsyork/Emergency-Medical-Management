package com.cecwj.model;


 import javax.persistence.*;

@Entity
@Table(name="volunteer")
public class Volunteer {
	   private int id;
	   private String name;
	   private int Sex;
	   private long IDcard;
	   private String Specialty;
	   private String address;
	   private long phone;
	   private int age;

	private int vid;
	   private int vflag;
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
	   	  public long getIDcard() {
				return IDcard;
			}

			public void setIDcard(long IDcard) {
				this.IDcard = IDcard;
			}
	      @Column(name="Sex")
	      public int getSex()
	      {
	    	  return this.Sex;
	      }
	      
	

		public void setSex(int Sex) { 
	   		  this.Sex = Sex; 
	   	}
	   	@Column(name="Specialty")
	   	  public String getSpecialty()
	      {
	    	  return this.Specialty;
	      }
	      
	   	  public void setSpecialty(String Specialty) { 
	   		  this.Specialty = Specialty; 
	   	}	
	   	
	   	  @ManyToOne 
	      @JoinColumn(name = "vid") 
	   	  public User getUser() {
	       return this.user;
	   	   }	   	  
	   	  public void setUser(User user) { 
	   		  this.user = user; 
	   	  }
	   	  
	   	  @Column(name="vflag") 
	   	  public int getVflag() {
		       return this.vflag;
		  }
		  
	   	  public void setVflag(int vflag) { 
		   	 this.vflag = vflag; 
		 }

		
	  	
	    	@Column(name="address") 
	  	public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}
	  	@Column(name="phone") 

		public long getPhone() {
		return phone;
	}

	  		public void setPhone(long phone) {
		this.phone = phone;
	}
		@Column(name="age") 
		   public int getAge() {
				return age;
			}

			public void setAge(int age) {
				this.age = age;
			}


	  	
	  	
}
