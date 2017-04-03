			package com.cecwj.model;
			
			import java.sql.Timestamp;

import javax.persistence.Column;
			import javax.persistence.Entity;
			import javax.persistence.GeneratedValue;
			import javax.persistence.Id;
import javax.persistence.Table;
			
			@Entity
			@Table(name="epidemic")
			public class Epidemic
			{
			  private int id;
			  private String name;
			  private Integer number;
			  private String zone;
			  private Timestamp time;
			  private String degree;
			  private String proc;
			
				  @Id
				  @GeneratedValue
				  public int getId()
				  {
				    return this.id;
				  }
				  
				  public void setId(int id) { 
					  this.id = id; 
				  }
				  
				  @Column(name="name")
				  public String getName() {
				    return this.name;
				  }
				  
				  public void setName(String name) { 
					  this.name = name; 
				  }
				  
				  @Column(name="number")
				  public Integer getNumber() {
				    return this.number;
				  }
				  
				  public void setNumber(Integer number) { 
					  this.number = number; 
				  }
				  
				  @Column(name="zone")
				  public String getZone() {
				    return this.zone;
				  }
				  
				  public void setZone(String zone) { 
					  this.zone = zone; 
				  }
			

				  @Column(name="degree")
				  public String getDegree() {
				    return this.degree;
				  }
				  public void setDegree(String degree) { 
					  this.degree = degree; 
				  }  
				  @Column(name="time")
				  public Timestamp getTime() {
					return time;
				}

				public void setTime(Timestamp time) {
					this.time = time;
				}


				  @Column(name="Proc")
				  public String getProc() {
				    return this.proc;
				  }
				  
				  public void setProc(String proc) { 
					  this.proc = proc; 
				  }
				
				  
				  
				  
			}
			

/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\Section.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */