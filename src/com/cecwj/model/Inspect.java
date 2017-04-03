package com.cecwj.model;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "inspect")
// 用来指定映射表的表名
public class Inspect {

	private int id;
	private int did;
	private int pid;//病人ID
	private int applyId;//申请编号
	private String content;//检查项目
	private Timestamp time;//检查时间
	private String illustration;//说明
	private String url;//图像数据存储位置
	private String status;
	public enum TypeB {
		unexecuted, executing, finished;
	}
	private Inspect.TypeB typeB;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Column(name = "time")
	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
	@Column(name="ApplyId")
	public int getApplyId() {
		return applyId;
	}

	public void setApplyId(int applyId) {
		this.applyId = applyId;
	}
	@Column(name="content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@Column(name="illustration")
	public String getIllustration() {
		return illustration;
	}

	public void setIllustration(String illustration) {
		this.illustration = illustration;
	}
	@Column(name="url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	public Inspect.TypeB getTypeB() {
		return this.typeB;
	}

	public void setTypeB(Inspect.TypeB typeB) {
		this.typeB = typeB;
		if (typeB.equals(Inspect.TypeB.unexecuted)) {
			this.status = "未领取";
		} else if (typeB.equals(Inspect.TypeB.executing)) {
			this.status = "已领取";
		} else if (typeB.equals(Inspect.TypeB.finished)) {
			this.status = "已完成";
		}
	}

	@Transient
	public String getStatus() {
		if ((this.status == null) && (this.typeB != null)) {
			if (this.typeB.equals(Inspect.TypeB.unexecuted)) {
				this.status = "未领取";
			} else if (this.typeB.equals(Inspect.TypeB.executing)) {
				this.status = "已领取";
			} else if (this.typeB.equals(Inspect.TypeB.finished)) {
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
			this.typeB = Inspect.TypeB.unexecuted;
		} else if (status.equals("已领取")) {
			this.typeB = Inspect.TypeB.executing;
		} else if (status.equals("已完成")) {
			this.typeB = Inspect.TypeB.finished;
		}
	}

}
