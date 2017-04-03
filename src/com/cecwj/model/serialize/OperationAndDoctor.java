package com.cecwj.model.serialize;

import java.sql.Timestamp;

import com.cecwj.model.Apply;
import com.cecwj.model.Bed;
import com.cecwj.model.Doctor;
import com.cecwj.model.Operation;
import com.cecwj.model.Patient;
import com.cecwj.model.Section;
import com.cecwj.model.User;

public class OperationAndDoctor {

	private int id;
	private int pid;//病人ID
	private int applyId;//申请编号
	private String content;//手术项目
	private Timestamp operationtime;//检查时间
	private String illustration;//说明
	private String status;
	private String operationdoc;
	private String applydoc;
	private Timestamp applytime;//检查时间
	
	public OperationAndDoctor(User user,User user1,Operation operation,Apply apply){
		this.id = operation.getId();
		this.pid = operation.getPid();	
		this.operationtime = operation.getTime();
		this.applyId= operation.getApplyId();//申请编号
		this.content= operation.getContent();//手术项目		
		this.illustration= operation.getIllustration();//说明
		this.status= operation.getStatus();
		this.operationdoc= user1.getUserName();
		this.applydoc= user.getUserName();
		this.applytime = apply.getTime();
		
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





	public Timestamp getOperationtime() {
		return operationtime;
	}


	public void setOperationtime(Timestamp operationtime) {
		this.operationtime = operationtime;
	}


	public Timestamp getApplytime() {
		return applytime;
	}


	public void setApplytime(Timestamp applytime) {
		this.applytime = applytime;
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


	public String getOperationdoc() {
		return operationdoc;
	}


	public void setOperationdoc(String operationdoc) {
		this.operationdoc = operationdoc;
	}


	public String getApplydoc() {
		return applydoc;
	}


	public void setApplydoc(String applydoc) {
		this.applydoc = applydoc;
	}


	
	
}