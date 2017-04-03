package com.cecwj.model;

import java.sql.Timestamp;

import javax.persistence.*;

import com.cecwj.model.User.Role;


@Entity
@Table(name="task")//用来指定映射表的表名
public class Task {
	public enum TypeA{
		unexecuted, executing,finished;
	}
	private int id;
	private String taskpeople;
	private Timestamp time;
	private String status;
	private String details;
	private String tasktype;
	private int number;
	private Task.TypeA typeA;
	private User.Role taskpeopletype;

	@Id
    @GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(name="date")
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	@Column(name="taskpeople")
	public String getTaskpeople() {
		return taskpeople;
	}

	public void setTaskpeople(String taskpeople) {
		this.taskpeople = taskpeople;
	}
	@Column(name="taskdetails")

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	@Column(name="number")

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name="status")
	public Task.TypeA getTypeA() {
		return this.typeA;
	}
	public void setTypeA(Task.TypeA typeA) {
		this.typeA = typeA;
		if (typeA.equals(Task.TypeA.unexecuted)) {
			this.status = "未领取";
		} else if (typeA.equals(Task.TypeA.executing)){
			this.status = "已领取";
		}else if (typeA.equals(Task.TypeA.finished)){
			this.status = "已完成";
		}
	}
	@Transient	
	public String getStatus() {
		if ((this.status == null) && (this.typeA != null)) {
			if (this.typeA.equals(Task.TypeA.unexecuted)) {
				this.status = "未领取";
			} else if (this.typeA.equals(Task.TypeA.executing)) {
				this.status = "已领取";
			}else if (this.typeA.equals(Task.TypeA.finished)) {
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
			this.typeA = Task.TypeA.unexecuted;
		} else if (status.equals("已领取")) {
			this.typeA = Task.TypeA.executing;
		} else if (status.equals("已完成")) {
			this.typeA = Task.TypeA.finished;
		}
	}

	

	@Column(name="tasktype")
	public String getTasktype() {
		return tasktype;
	}

	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}

	@Column(name="taskpeopletype")
	public User.Role getTaskpeopletype() {
		return taskpeopletype;
	}

	public void setTaskpeopletype(User.Role role) {
		this.taskpeopletype = role;
	}


}
