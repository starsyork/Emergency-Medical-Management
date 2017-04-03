package com.cecwj.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cecwj.model.Task;
import com.cecwj.model.TaskAndVolunteer;
import com.cecwj.model.User;
import com.cecwj.model.serialize.ApplyAndPatient;
import com.cecwj.model.serialize.JsonBase;
import com.cecwj.model.serialize.ReceviceAndTask;
import com.cecwj.service.TaskAndVolunteerManager;
import com.cecwj.service.TaskManager;
import com.cecwj.service.UserManager;
import com.cecwj.service.VolunteerManager;

public class TaskAction extends BaseAction {
	static Log log = LogFactory.getLog(TaskAction.class);
	private int id;
	private String taskpeople;
	private String time;
	private String status;
	private String details;
	private int peopletype;

	private String type;
	private int number;
	private int taskpeopletype;

	
	private TaskManager taskmanager;
	private VolunteerManager volunteerManager;
	private TaskAndVolunteerManager taskandvolunteerManager;
	private UserManager usermanager;
	public TaskManager getTaskmanager() {
		return taskmanager;
	}
	@Resource
	public void setTaskmanager(TaskManager taskmanager) {
		this.taskmanager = taskmanager;
	}

	
	
	
	public VolunteerManager getVolunteerManager() {
		return volunteerManager;
	}
	@Resource
	public void setVolunteerManager(VolunteerManager volunteerManager) {
		this.volunteerManager = volunteerManager;
	}
	public TaskAndVolunteerManager getTaskandvolunteerManager() {
		return taskandvolunteerManager;
	}
	@Resource
	public void setTaskandvolunteerManager(
			TaskAndVolunteerManager taskandvolunteerManager) {
		this.taskandvolunteerManager = taskandvolunteerManager;
	}
	

	public UserManager getUsermanager() {
		return usermanager;
	}
	@Resource
	public void setUsermanager(UserManager usermanager) {
		this.usermanager = usermanager;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskpeople() {
		return taskpeople;
	}

	public void setTaskpeople(String taskpeople) {
		this.taskpeople = taskpeople;
	}



	public int getPeopletype() {
		return peopletype;
	}
	public void setPeopletype(int peopletype) {
		this.peopletype = peopletype;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}


	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
			
	public int getTaskpeopletype() {
		return taskpeopletype;
	}
	public void setTaskpeopletype(int taskpeopletype) {
		this.taskpeopletype = taskpeopletype;
	}
	
	
	
	
	public String getTask() {
		this.result = new JsonBase();
		try {
		List<Task> list = this.taskmanager.getAllTask();
		this.result.setResults(list);
		this.result.setSuccess(true);
		}catch (Exception e) {
			System.out.println(e);
			this.result.setMsg("请检查网络链接是否正常！");
			this.result.setSuccess(false);
		}
		return "OK";
	}
		
	public String addTask(){
		this.result = new JsonBase();	
		if(!authorized("id")){
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
		}else{		
			
			Integer tst=(Integer) getSession().get("id");
			int ts = tst.intValue();
			User user = this.usermanager.getUserById(ts);
		if (user == null){
			this.result.setMsg("获取发布人，失败");
			this.result.setSuccess(false);
		}else if (this.type==null||this.type.equals("")){
			this.result.setMsg("请填写任务类型");
			this.result.setSuccess(false);
		}else if (this.time==null||this.time.equals("")){
			this.result.setMsg("请选择开始时间");
			this.result.setSuccess(false);
		}else if (this.number<=0){
			this.result.setMsg("请选择所需人数");
			this.result.setSuccess(false);
		}else if (this.details==null||this.details.equals("")){
			this.result.setMsg("请输入任务具体信息");
			this.result.setSuccess(false);
		}else{
			Task task = new Task();
			task.setDetails(this.details);
			task.setNumber(this.number);
			task.setTaskpeople(user.getUserName());
		//	task.setTime(this.time);
			task.setTaskpeopletype(user.getRole());
			task.setTasktype(this.type);
			task.setStatus("未领取");			
			try{
				this.taskmanager.add(task);				
				this.result.setMsg("添加成功");
				this.result.setSuccess(true);
				}catch (Exception e ){
				this.result.setMsg("添加失败，请检查网络连接");
				this.result.setSuccess(false);
				}		
			}				
		}
		return "OK";	
	}
	
	public String addTaskPhone(){
		this.result = new JsonBase();	
		if(!authorized("id")){
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
		}else{		
			
			Integer tst=(Integer) getSession().get("id");
			int ts = tst.intValue();
			User user = this.usermanager.getUserById(ts);
		if (user == null){
			this.result.setMsg("获取发布人，失败");
			this.result.setSuccess(false);
		}else if (this.type==null||this.type.equals("")){
			this.result.setMsg("请填写任务类型");
			this.result.setSuccess(false);
		}else if (this.time==null||this.time.equals("")){
			this.result.setMsg("请选择开始时间");
			this.result.setSuccess(false);
		}else if (this.number<=0){
			this.result.setMsg("请选择所需人数");
			this.result.setSuccess(false);
		}else if (this.details==null||this.details.equals("")){
			this.result.setMsg("请输入任务具体信息");
			this.result.setSuccess(false);
		}else{
			try{
			Task task = new Task();
			details=new String (this.details.getBytes("ISO-8859-1"), "utf-8");
			task.setDetails(details);
			task.setNumber(this.number);
			task.setTaskpeople(user.getUserName());
		//	task.setTime(this.time);
			task.setTaskpeopletype(user.getRole());
			type=new String (this.type.getBytes("ISO-8859-1"), "utf-8");
			task.setTasktype(type);
			task.setStatus("未领取");			
			
				this.taskmanager.add(task);				
				this.result.setMsg("添加成功");
				this.result.setSuccess(true);
				}catch (Exception e ){
				this.result.setMsg("添加失败，请检查网络连接");
				this.result.setSuccess(false);
				}		
			}				
		}
		return "OK";	
	}
	public String deleteTask(){
		this.result= new JsonBase();
		if(this.id<=0){
			this.result.setMsg("请选择要删除的任务");
			this.result.setSuccess(false);
		}else{
				try{
						Task task = this.taskmanager.getTask(this.id);
						if (task!=null){
							this.taskmanager.delete(task);
							this.result.setMsg("删除成功");
							this.result.setSuccess(true);
						}else{
							this.result.setMsg("该任务不存在");
							this.result.setSuccess(false);
						}
					}catch (Exception e ){
					this.result.setMsg("添加失败，请检查网络连接");
					this.result.setSuccess(false);
				}
			}			
		return "OK";
	}
	
	public String edtailTask(){
		this.result= new JsonBase();
		if(this.id<=0){
			this.result.setMsg("请选择要更新的任务");
			this.result.setSuccess(false);
		}else{
			try{
				Task task= this.taskmanager.getTask(id);
				if(task!=null){
				if (this.taskpeople != null&&!this.taskpeople.equals(""))
					task.setTaskpeople(this.taskpeople);
				if (this.type!=null&&!this.type.equals(""))
					task.setTasktype(this.type);
			//	if (this.time!=null&&!this.time.equals(""))
			//		task.setTime(this.time);
				if (this.number>0)
					task.setNumber(this.number);
				if (this.details!=null&&!this.details.equals(""))
					task.setDetails(this.details);
				this.taskmanager.update(task);
				this.result.setMsg("更新任务成功");
				this.result.setSuccess(true);
				}
			}catch (Exception e){
				this.result.setMsg("更新任务失败");
				this.result.setSuccess(false);
			}
		}				
			return "OK";
	}
	
	public String ReceivedTask(){
		this.result = new JsonBase();
		if(!authorized("id")){
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
		}else{
		
			Integer tst=(Integer) getSession().get("id");
			int ts = tst.intValue();
		
		//int ts=75;
			if(this.id<=0){
				this.result.setMsg("任务获取失败");
				this.result.setSuccess(false);
			}else{
				
				try{
					Task task = this.taskmanager.getTask(this.id);
					TaskAndVolunteer taskandvolunteer1=taskandvolunteerManager.getTaskByVolunteer(this.id,ts);				
					if(taskandvolunteer1 !=null){
						this.result.setMsg("已领取，请前往我的任务查看");
						this.result.setSuccess(false);
					}else{
					
					if(task.getNumber()>0){
						task.setNumber(task.getNumber()-1);
						if(task.getNumber()==0)
							task.setStatus("已领取");
						
						TaskAndVolunteer taskandvolunteer = new TaskAndVolunteer();		
						taskandvolunteer.setTask(task);
						taskandvolunteer.setVolunteerid(ts);
						taskandvolunteer.setVolunteername(this.volunteerManager.getvolunteerByIdA(ts).getName());
						taskandvolunteer.setStatus("已领取");
						this.taskmanager.update(task);
						this.taskandvolunteerManager.add(taskandvolunteer);
						this.result.setMsg("领取成功");
						this.result.setSuccess(true);
					}else{
						this.result.setMsg("人数已满");
						this.result.setSuccess(false);
					}
					}
				}catch(Exception e){
					System.out.println(e);
					this.result.setMsg("领取失败");
					this.result.setSuccess(false);
				}
			}
			
		}
				
		return "OK";	
	}
	
	
	public String SubmitTask(){
		this.result = new JsonBase();
		if(!authorized("id")){
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
		}else{
		
			Integer tst=(Integer) getSession().get("id");
			int ts = tst.intValue();
			
		if(this.id<=0){
			this.result.setMsg("任务获取失败");
			this.result.setSuccess(false);
		}else{
			try{
				TaskAndVolunteer taskandvolunteer = this.taskandvolunteerManager.getTaskByVolunteer(this.id,ts);
				if(taskandvolunteer!=null){
					taskandvolunteer.setStatus("已完成");
					this.taskandvolunteerManager.update(taskandvolunteer);
					List<TaskAndVolunteer> taskandvolunteers = this.taskandvolunteerManager.getTaskByStatus(this.id,TaskAndVolunteer.TypeA.finished);
					if(taskandvolunteers.size()==0){
						Task task = this.taskmanager.getTask(this.id);
						if(task.getNumber()==0){
							task.setStatus("已完成");
							this.taskmanager.update(task);
						}
					}
					//this.result.setResults(taskandvolunteers);
					this.result.setMsg("恭喜您，任务已完成");
					this.result.setSuccess(true);
				}else{
					this.result.setMsg("任务获取失败");
					this.result.setSuccess(false);
				}
			}catch(Exception e){
				System.out.println(e);
				this.result.setMsg("任务获取失败");
				this.result.setSuccess(false);
				
			}
			
			
		}
		}
		
		
		return "OK";
	}
	
	public String getTaskByVid() {
		this.result = new JsonBase();
		if(!authorized("id")){
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
		}else{
		
			Integer tst=(Integer) getSession().get("id");
			int ts = tst.intValue();
			
			try{
				List<TaskAndVolunteer> taskandvolunteers = this.taskandvolunteerManager.getTaskByVid(ts);
				List<ReceviceAndTask> Rt = new ArrayList(); 
				for(TaskAndVolunteer taskandvolunteer:taskandvolunteers){
					ReceviceAndTask a = new ReceviceAndTask(taskandvolunteer);	
					Rt.add(a);
				}				
				this.result.setResults(Rt);
				this.result.setSuccess(true);
			}catch(Exception e){
				this.result.setMsg("获取任务列表失败，请检查网络");
				this.result.setSuccess(false);
			}
			
	}	
		return "OK";
	}
	
}
