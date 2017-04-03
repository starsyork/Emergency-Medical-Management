package com.cecwj.model;

import java.sql.Timestamp;

import javax.persistence.*;

import com.cecwj.model.TaskAndVolunteer.TypeA;

@Entity
@Table(name = "transport")
// 用来指定映射表的表名
public class Transport {

	private int id;
	private int did;

	private int pid;//病人ID

	private int applyId;//申请编号
	private String content;//去往医院
	private Timestamp time;//转运时间
	private String illustration;//注意事项说明
	public enum TypeA{
		unexecuted, executing,finished;
	}
	private String status;
	private Transport.TypeA typeA;
	private int tid;
	
	
	
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
		@Enumerated(EnumType.ORDINAL)
	@Column(name="status")
	public Transport.TypeA getTypeA() {
		return this.typeA;
	}
	public void setTypeA(Transport.TypeA typeA) {
		this.typeA = typeA;
		if (typeA.equals(Transport.TypeA.unexecuted)) {
			this.status = "等待转运";
		} else if (typeA.equals(Transport.TypeA.executing)){
			this.status = "正在转运";
		}else if (typeA.equals(Transport.TypeA.finished)){
			this.status = "转运完成";
		}
	}
	@Transient	
	public String getStatus() {
		if ((this.status == null) && (this.typeA != null)) {
			if (this.typeA.equals(Transport.TypeA.unexecuted)) {
				this.status = "等待转运";
			} else if (this.typeA.equals(Transport.TypeA.executing)) {
				this.status = "正在转运";
			}else if (this.typeA.equals(Transport.TypeA.finished)) {
				this.status = "转运完成";
			}
		}
		return this.status;
	}
	
	public void setStatus(String status) {		
		this.status = status;
		if (status.equals("")) {
			return;
		}
		if (status.equals("等待转运")) {
			this.typeA = Transport.TypeA.unexecuted;
		} else if (status.equals("正在转运")) {
			this.typeA = Transport.TypeA.executing;
		} else if (status.equals("转运完成")) {
			this.typeA = Transport.TypeA.finished;
		}
	}

	@Column(name="tid")
	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	
}
