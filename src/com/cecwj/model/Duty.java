package com.cecwj.model;

import java.sql.Timestamp;

import javax.persistence.*;


@Entity
@Table(name="duty")//用来指定映射表的表名
public class Duty {

	private int id;
	private Timestamp workupdowndate;
	private String workoldcon ;
	private String worknewcon ;
	private String  workupper;
	private String workdownloader ;
	private String workupstuff ;
	private String workdownstuff ;
	private int sectionId;
	@Id
    @GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(name="workupdowndate")
	public Timestamp getWorkupdowndate() {
		return workupdowndate;
	}
	public void setWorkupdowndate(Timestamp workupdowndate) {
		this.workupdowndate = workupdowndate;
	}

	@Column(name="workoldcon")
	public String getWorkoldcon() {
		return workoldcon;
	}

	public void setWorkoldcon(String workoldcon) {
		this.workoldcon = workoldcon;
	}
	@Column(name="worknewcon")

	public String getWorknewcon() {
		return worknewcon;
	}

	public void setWorknewcon(String worknewcon) {
		this.worknewcon = worknewcon;
	}
	@Column(name="workupper")

	public String getWorkupper() {
		return workupper;
	}

	public void setWorkupper(String workupper) {
		this.workupper = workupper;
	}
	@Column(name="workdownloader")

	public String getWorkdownloader() {
		return workdownloader;
	}

	public void setWorkdownloader(String workdownloader) {
		this.workdownloader = workdownloader;
	}
	@Column(name="workupstuff")

	public String getWorkupstuff() {
		return workupstuff;
	}

	public void setWorkupstuff(String workupstuff) {
		this.workupstuff = workupstuff;
	}
	@Column(name="workdownstuff")

	public String getWorkdownstuff() {
		return workdownstuff;
	}

	public void setWorkdownstuff(String workdownstuff) {
		this.workdownstuff = workdownstuff;
	}
	@Column(name="sectionId")
	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}	

}
