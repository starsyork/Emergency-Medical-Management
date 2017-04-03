package com.cecwj.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cecwj.dao.TaskDao;
import com.cecwj.model.Task;

import javax.annotation.Resource;

@Component("taskManager")
public class TaskManager {
	private TaskDao taskDao;


	public TaskDao getTaskDao() {
		return taskDao;
	}
	@Resource
	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

	
	public  Task getTask(int id){
		Task task = this.taskDao.getTask(id);
		return task;
	}

	public void update(Task task) {
		 this.taskDao.update(task);			
	}
	public void add(Task task) {
		this.taskDao.add(task);
		
	}
	public void delete(Task task) {
		this.taskDao.delete(task);		
	}
	public List<Task> getTask() {		
		List<Task> Task = this.taskDao.getTask();	
		return Task;
	}	
	public List<Task> getAllTask() {		
		List<Task> Task = this.taskDao.getAllTask();	
		return Task;
	}
}
