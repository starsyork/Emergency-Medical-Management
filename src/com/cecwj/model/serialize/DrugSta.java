

/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\serialize\DoctorSta.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */
package com.cecwj.model.serialize;

import java.sql.Timestamp;

import javax.persistence.Column;

import com.cecwj.common.TimeFormat;
import com.cecwj.model.TermAll;


public class DrugSta {
	   	  private int id;
	   	  private String drugcode;
		  private String name;			 
		  private int amount;
		  private int need;
		 // private Timestamp entrytime;
		  private Timestamp  entrytime;

	    public DrugSta(TermAll termAll) {
		   this.id = termAll.getId();
		   this.drugcode=termAll.getDrugcode();
		   this.name = termAll.getName();
		   this.amount = termAll.getAmount();
		   this.need = termAll.getNeed()-termAll.getAmount();
		   this.entrytime = termAll.getEntrytime();		
		
		 }
		  




		public Timestamp getEntrytime() {
			return entrytime;
		}

		public void setEntrytime(Timestamp entrytime) {
			this.entrytime = entrytime;
		}





		 
 public int getId() {
			return id;
		}





		public void setId(int id) {
			this.id = id;
		}





		public String getDrugcode() {
			return drugcode;
		}





		public void setDrugcode(String drugcode) {
			this.drugcode = drugcode;
		}





public String getName() {
		    return this.name;
		  }
		  
		  public void setName(String name) { 
			  this.name = name; 
		  }
		  public int getAmount() {
		    return this.amount;
		  }
		  
		  public void setAmount(int amount) { 
			  this.amount = amount; 
		  }
		  public int getNeed() {
		    return this.need;
		  }
		  
		  public void setNeed(int need) { 
			  this.need = need; 
		  }


		  
}




