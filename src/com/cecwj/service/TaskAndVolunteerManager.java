package com.cecwj.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cecwj.dao.TaskAndVolunteerDao;
import com.cecwj.dao.TaskDao;
import com.cecwj.model.Task;
import com.cecwj.model.Task.TypeA;
import com.cecwj.model.TaskAndVolunteer;

import javax.annotation.Resource;

@Component("taskandvolunteerManager")
public class TaskAndVolunteerManager {
	private TaskAndVolunteerDao taskandvolunteerDao;


	public TaskAndVolunteerDao getTaskAndVolunteerDao() {
		return taskandvolunteerDao;
	}
	@Resource
	public void setTaskAndVolunteerDao(TaskAndVolunteerDao taskandvolunteerDao) {
		this.taskandvolunteerDao = taskandvolunteerDao;
	}

	
	public  TaskAndVolunteer getTaskAndVolunteer(int id){
		TaskAndVolunteer task = this.taskandvolunteerDao.getTaskAndVolunteer(id);
		return task;
	}

	public void update(TaskAndVolunteer task) {
		 this.taskandvolunteerDao.update(task);			
	}
	public void add(TaskAndVolunteer task) {
		this.taskandvolunteerDao.add(task);
		
	}
	public void delete(TaskAndVolunteer task) {
		this.taskandvolunteerDao.delete(task);
		
	}

	public TaskAndVolunteer getTaskAndVolunteerByVid(int ts) {
		TaskAndVolunteer taskandvolunteer = this.taskandvolunteerDao.getTaskAndVolunteerByVid(ts);
		return taskandvolunteer;
		
	}
	public TaskAndVolunteer getTaskByVolunteer(int id, int ts) {
		TaskAndVolunteer taskandvolunteer = this.taskandvolunteerDao.getTaskByVolunteer(id,ts);
		return taskandvolunteer;
	}
	public List<TaskAndVolunteer> getTaskByTaskId(int id) {
		// TODO Auto-generated method stub
		List<TaskAndVolunteer> Task = this.taskandvolunteerDao.getTaskByTaskId(id);	
		return Task;
	}
	public List<TaskAndVolunteer> getTaskByStatus(int id, TaskAndVolunteer.TypeA finished) {
		List<TaskAndVolunteer> Task = this.taskandvolunteerDao.getTaskByStatus(id,finished);	
		return Task;
	}	
	public List<TaskAndVolunteer> getTaskByVid(int id) {
		List<TaskAndVolunteer> Task = this.taskandvolunteerDao.getTaskByVid(id);	
		return Task;
	}
	public TaskAndVolunteer getTaskByTaskid(int ts, int id) {
		// TODO Auto-generated method stub
		return null;
	}	
}
