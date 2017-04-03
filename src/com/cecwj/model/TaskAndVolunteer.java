package com.cecwj.model;

import java.sql.Timestamp;

import javax.persistence.*;

import com.cecwj.model.TaskAndVolunteer.TypeA;


@Entity
@Table(name="taskandvolunteer")//用来指定映射表的表名
public class TaskAndVolunteer {

	private int id;
	private int taskid;
	private String  volunteername;
	private int  volunteerid;
	private Task task;
	public enum TypeA{
		unexecuted, executing,finished;
	}
	private String status;
	private TypeA typeA;
	@Id
    @GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="taskid")
	public Task getTask() {
	return task;
	}	
	public void setTask(Task task) {
	this.task = task;
	}

	@Column(name="volunteername")
	public String getVolunteername() {
		return volunteername;
	}
	public void setVolunteername(String volunteername) {
		this.volunteername = volunteername;
	}
	@Column(name="volunteerid")
	public int getVolunteerid() {
		return volunteerid;
	}

	public void setVolunteerid(int volunteerid) {
		this.volunteerid = volunteerid;
	}
	@Enumerated(EnumType.ORDINAL)
	@Column(name="status")
	public TaskAndVolunteer.TypeA getTypeA() {
		return this.typeA;
	}
	public void setTypeA(TaskAndVolunteer.TypeA typeA) {
		this.typeA = typeA;
		if (typeA.equals(TaskAndVolunteer.TypeA.unexecuted)) {
			this.status = "未领取";
		} else if (typeA.equals(TaskAndVolunteer.TypeA.executing)){
			this.status = "已领取";
		}else if (typeA.equals(TaskAndVolunteer.TypeA.finished)){
			this.status = "已完成";
		}
	}
	@Transient	
	public String getStatus() {
		if ((this.status == null) && (this.typeA != null)) {
			if (this.typeA.equals(TaskAndVolunteer.TypeA.unexecuted)) {
				this.status = "未领取";
			} else if (this.typeA.equals(TaskAndVolunteer.TypeA.executing)) {
				this.status = "已领取";
			}else if (this.typeA.equals(TaskAndVolunteer.TypeA.finished)) {
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
			this.typeA = TaskAndVolunteer.TypeA.unexecuted;
		} else if (status.equals("已领取")) {
			this.typeA = TaskAndVolunteer.TypeA.executing;
		} else if (status.equals("已完成")) {
			this.typeA = TaskAndVolunteer.TypeA.finished;
		}
	}

	

}
