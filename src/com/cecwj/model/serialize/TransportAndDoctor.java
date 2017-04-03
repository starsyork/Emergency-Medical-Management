package com.cecwj.model.serialize;

import java.sql.Timestamp;

import com.cecwj.model.Apply;
import com.cecwj.model.Bed;
import com.cecwj.model.Doctor;
import com.cecwj.model.Operation;
import com.cecwj.model.Patient;
import com.cecwj.model.Section;
import com.cecwj.model.Transport;
import com.cecwj.model.User;

public class TransportAndDoctor {

	private int id;
	private int did;
	private int pid;//病人ID
	private int tid;
	private String dname;
	private String pname;//病人ID
	private String tname;
	private int applyId;//申请编号
	private String content;//去往医院
	private Timestamp time;//转运时间
	private String illustration;//注意事项说明
	private String status;
	
	public TransportAndDoctor(User user,User user1,Transport transport,Apply apply){
		this.id = transport.getId();
		this.pid = transport.getPid();			
		this.applyId= transport.getApplyId();//申请编号
		this.content= transport.getContent();//手术项目		
		this.illustration= transport.getIllustration();//说明
		this.status= transport.getStatus();
		this.time = transport.getTime();
		this.dname= user.getUserName();
		if(user1!=null)
			this.tname= user1.getUserName();
		else
			this.tname=null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
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

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
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