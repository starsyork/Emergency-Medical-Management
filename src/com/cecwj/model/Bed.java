package com.cecwj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "bed")
// 用来指定映射表的表名
public class Bed {

	public enum Status {
		EMPTY, ASSIGNED, INUSE;
	}

	private int id;
	private Patient patient;
	private Bed.Status status;
	private int sectionid;
	private int bedNum;
	private String statusStr;
	private int flag;

	@Id
	@GeneratedValue
	/* 34 */public int getId() {
		return this.id;
	}

	public void setId(int id) {
		/* 37 */this.id = id;
		/* 38 */if ((this.bedNum == 0) && (id > 0))
			/* 39 */this.bedNum = id;
	}

	// 一对一的关系
	@OneToOne
	@JoinColumn(name = "pid")
	/* 44 */public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		/* 47 */this.patient = patient;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column
	/* 52 */public Bed.Status getStatus() {
		return this.status;
	}

	public void setStatus(Bed.Status status) {
		/* 55 */this.status = status;
		/* 56 */if (status.equals(Bed.Status.ASSIGNED)) {
			/* 57 */this.statusStr = "等待";
			/* 58 */} else if (status.equals(Bed.Status.EMPTY)) {
			/* 59 */this.statusStr = "空";
			/* 60 */} else if (status.equals(Bed.Status.INUSE))
			/* 61 */this.statusStr = "已分配";
	}

	@Column(name = "section")
	public int getSectionid() {
		/* 66 */return this.sectionid;
	}

	/* 69 */public void setSectionid(int sectionid) {
		this.sectionid = sectionid;
	}

	@Transient
	public int getBedNum() {
		/* 73 */if ((this.bedNum == 0) && (this.id > 0))
			/* 74 */this.bedNum = this.id;
		/* 75 */return this.bedNum;
	}

	/* 78 */public void setBedNum(int bedNum) {
		this.bedNum = bedNum;
		/* 79 */if (bedNum > 0)
			/* 80 */this.id = bedNum;
	}

	@Transient
	/* 84 */public String getStatusStr() {
		/* 85 */if (((this.statusStr == null) || (this.statusStr.equals(""))) && (this.status != null)) {
			/* 86 */if (this.status.equals(Bed.Status.ASSIGNED)) {
				/* 87 */this.statusStr = "等待";
				/* 88 */} else if (this.status.equals(Bed.Status.EMPTY)) {
				/* 89 */this.statusStr = "空";
				/* 90 */} else if (this.status.equals(Bed.Status.INUSE)) {
				/* 91 */this.statusStr = "已分配";
			}
		}
		/* 94 */return this.statusStr;
	}

	/* 97 */public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
		/* 98 */if (statusStr.equals("空")) {
			/* 99 */this.status = Bed.Status.EMPTY;
			/* 100 */} else if (statusStr.equals("等待")) {
			/* 101 */this.status = Bed.Status.ASSIGNED;
			/* 102 */} else if (statusStr.equals("已分配"))
			/* 103 */this.status = Bed.Status.INUSE;
	}

	@Column
	/* 107 */public int getFlag() {
		return this.flag;
	}

	public void setFlag(int flag) {
		/* 110 */this.flag = flag;
	}
}

/*
 * Location:
 * D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model
 * \Bed.class Java compiler version: 5 (49.0) JD-Core Version: 0.7.1
 */