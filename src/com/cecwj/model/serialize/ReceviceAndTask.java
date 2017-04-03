package com.cecwj.model.serialize;

import java.sql.Timestamp;

import com.cecwj.model.Task;
import com.cecwj.model.TaskAndVolunteer;
import com.cecwj.model.User;
import com.cecwj.model.User.Role;
import com.cecwj.model.Volunteer;

public class ReceviceAndTask {
	   

	private int id;
	private Timestamp time;
	private String details;	
	private String taskpeople;
	private String tasktype;
	private String status;
	private int number;
	private User.Role taskpeopletype;
  
	public ReceviceAndTask(TaskAndVolunteer taskandvolunteer) {
		this.id=taskandvolunteer.getTask().getId();
		this.time=taskandvolunteer.getTask().getTime();
		this.details=taskandvolunteer.getTask().getDetails();
		this.taskpeople=taskandvolunteer.getTask().getTaskpeople();
		this.taskpeopletype=taskandvolunteer.getTask().getTaskpeopletype();
		this.tasktype=taskandvolunteer.getTask().getTasktype();
		this.status=taskandvolunteer.getStatus();
		this.number=taskandvolunteer.getTask().getNumber();
	
		
	 }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getTaskpeople() {
		return taskpeople;
	}

	public void setTaskpeople(String taskpeople) {
		this.taskpeople = taskpeople;
	}

	public String getTasktype() {
		return tasktype;
	}

	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public User.Role getTaskpeopletype() {
		return taskpeopletype;
	}

	public void setTaskpeopletype(User.Role taskpeopletype) {
		this.taskpeopletype = taskpeopletype;
	}


	
		   	
}
