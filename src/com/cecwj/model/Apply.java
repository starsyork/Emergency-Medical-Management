package com.cecwj.model;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "application")
// 用来指定映射表的表名
public class Apply {
	public enum TypeA {
		 EXAMINE,INSPECTION, OPS, TRANSPORT;//检查检验手术转运
	}

	public enum TypeB {
		unexecuted, executing, finished;
	}

	private int id;
	private int did;
	private Timestamp time;
	private String type;
	private String status;
	private int pid;
	private Apply.TypeA typeA;
	private Apply.TypeB typeB;

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "time")
	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	@Column(name = "did")
	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	@Column(name = "pid")
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "class")
	public Apply.TypeA getTypeA() {
		return this.typeA;
	}

	public void setTypeA(Apply.TypeA typeA) {
		this.typeA = typeA;
		if (typeA.equals(Apply.TypeA.EXAMINE)) {
			this.type = "检查";
		} else if (typeA.equals(Apply.TypeA.INSPECTION)) {
			this.type = "检验";
		} else if (typeA.equals(Apply.TypeA.OPS)) {
			this.type = "手术";
		} else if (typeA.equals(Apply.TypeA.TRANSPORT))
			this.type = "转运";
	}

	@Transient
	public String getType() {
		if ((this.type == null) && (this.typeA != null)) {
			if (this.typeA.equals(Apply.TypeA.EXAMINE)) {
				this.type = "检验";
			} else if (this.typeA.equals(Apply.TypeA.INSPECTION)) {
				this.type = "检查";
			} else if (this.typeA.equals(Apply.TypeA.OPS)) {
				this.type = "手术";
			} else if (this.typeA.equals(Apply.TypeA.TRANSPORT)) {
				this.type = "转运";
			}
		}
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
		if (type.equals("")) {
			return;
		}
		if (type.equals("检验")) {
			this.typeA = Apply.TypeA.EXAMINE;
		} else if (type.equals("检查")) {
			this.typeA = Apply.TypeA.INSPECTION;
		} else if (type.equals("手术")) {
			this.typeA = Apply.TypeA.OPS;
		} else if (type.equals("转运")) {
			this.typeA = Apply.TypeA.TRANSPORT;
		}
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	public Apply.TypeB getTypeB() {
		return this.typeB;
	}

	public void setTypeB(Apply.TypeB typeB) {
		this.typeB = typeB;
		if (typeB.equals(Apply.TypeB.unexecuted)) {
			this.status = "未领取";
		} else if (typeB.equals(Apply.TypeB.executing)) {
			this.status = "已领取";
		} else if (typeB.equals(Apply.TypeB.finished)) {
			this.status = "已完成";
		}
	}

	@Transient
	public String getStatus() {
		if ((this.status == null) && (this.typeB != null)) {
			if (this.typeB.equals(Apply.TypeB.unexecuted)) {
				this.status = "未领取";
			} else if (this.typeB.equals(Apply.TypeB.executing)) {
				this.status = "已领取";
			} else if (this.typeB.equals(Apply.TypeB.finished)) {
				this.status = "已完成";
			}
		}
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
		if (status.equals("")) {
			return;
		}
		if (status.equals("未领取")) {
			this.typeB = Apply.TypeB.unexecuted;
		} else if (status.equals("已领取")) {
			this.typeB = Apply.TypeB.executing;
		} else if (status.equals("已完成")) {
			this.typeB = Apply.TypeB.finished;
		}
	}

}
