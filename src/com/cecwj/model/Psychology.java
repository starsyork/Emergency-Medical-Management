package com.cecwj.model;

import java.sql.Timestamp;

import javax.persistence.*;


@Entity
@Table(name="psychology")//用来指定映射表的表名
public class Psychology {
	private int id;
	private int pid;
	private Timestamp time;
	private String ptname;
	private String psychologyStatus;


	@Id
    @GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(name="pid")
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}
	@Column(name="time")
	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
	@Column(name="ptname")
	public String getPtname() {
		return ptname;
	}

	public void setPtname(String ptname) {
		this.ptname = ptname;
	}

	@Column(name="psychologyStatus")
	public String getPsychologyStatus() {
		return psychologyStatus;
	}

	public void setPsychologyStatus(String psychologyStatus) {
		this.psychologyStatus = psychologyStatus;
	}




}
