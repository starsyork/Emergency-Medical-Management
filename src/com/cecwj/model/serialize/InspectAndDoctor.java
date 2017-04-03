package com.cecwj.model.serialize;

import java.sql.Timestamp;

import com.cecwj.model.Apply;
import com.cecwj.model.Bed;
import com.cecwj.model.Doctor;
import com.cecwj.model.Inspect;
import com.cecwj.model.Operation;
import com.cecwj.model.Patient;
import com.cecwj.model.Section;
import com.cecwj.model.User;

public class InspectAndDoctor {

	private int id;
	private int did;
	private int pid;//病人ID
	private int applyId;//申请编号
	private String content;//检查项目
	private Timestamp time;//检查时间
	private String illustration;//说明
	private String url;//图像数据存储位置
	private String status;
	private String applydoc;


	public InspectAndDoctor(User user,User user1 ,Inspect inspect) {
		this.id = inspect.getId();
		this.pid = inspect.getPid();	
		
		this.applyId= inspect.getApplyId();//申请编号
		this.content= inspect.getContent();//手术项目		
		this.illustration= inspect.getIllustration();//说明
		this.status= inspect.getStatus();
		this.url = inspect.getUrl();
		this.applydoc=user.getUserName();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getPid() {
		return pid;
	}


	public void setPid(int pid) {
		this.pid = pid;
	}


	public int getApplyId() {
		return applyId;
	}


	public void setApplyId(int applyId) {
		this.applyId = applyId;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}





	public String getApplydoc() {
		return applydoc;
	}


	public void setApplydoc(String applydoc) {
		this.applydoc = applydoc;
	}


	public int getDid() {
		return did;
	}


	public void setDid(int did) {
		this.did = did;
	}


	public Timestamp getTime() {
		return time;
	}


	public void setTime(Timestamp time) {
		this.time = time;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getIllustration() {
		return illustration;
	}


	public void setIllustration(String illustration) {
		this.illustration = illustration;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}



}