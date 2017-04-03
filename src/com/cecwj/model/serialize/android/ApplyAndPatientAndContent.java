package com.cecwj.model.serialize.android;

import java.sql.Timestamp;

import com.cecwj.model.Apply;
import com.cecwj.model.Doctor;
import com.cecwj.model.Patient;
import com.cecwj.model.Section;
import com.cecwj.model.Transport;
import com.cecwj.model.User;

public class ApplyAndPatientAndContent {
	private int id;
	private int pid;
	private int docId;
	private String name;
	private String docName;
	private Timestamp time;
	private String examptsec;
	private String sex;
	private int age;
	private String woundType;
	private String woundAddr;
	private String woundTime;
	private String content;	
	private String status;
	private String rfid;
	private String illustration;
	public ApplyAndPatientAndContent(Apply apply,Patient patient,User doctor,Transport transport){
		this.id = apply.getId();
		this.pid = apply.getPid();
		this.docId = apply.getDid();
		this.name = patient.getName();
		this.docName = doctor.getUserName();
		this.time = apply.getTime();
		this.examptsec=doctor.getSection().getSecName();		
			if(patient.getSex()==1)
				this.sex="女";	
			else
				this.sex="男";		
		this.age=patient.getAge();
		this.woundAddr=patient.getWoundAddr();
		this.woundTime=patient.getWoundTime();
		this.woundType=patient.getWoundType();
		this.rfid=patient.getRfid();
		this.content=transport.getContent();
		this.status=transport.getStatus();
		this.illustration=transport.getIllustration();
	}
	
	
	public String getRfid() {
		return rfid;
	}
	public void setRfid(String rfid) {
		this.rfid = rfid;
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

	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getWoundType() {
		return woundType;
	}
	public void setWoundType(String woundType) {
		this.woundType = woundType;
	}
	public String getWoundAddr() {
		return woundAddr;
	}
	public void setWoundAddr(String woundAddr) {
		this.woundAddr = woundAddr;
	}
	public String getWoundTime() {
		return woundTime;
	}
	public void setWoundTime(String woundTime) {
		this.woundTime = woundTime;
	}
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getExamptsec() {
		return examptsec;
	}
	public void setExamptsec(String examptsec) {
		this.examptsec = examptsec;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIllustration() {
		return illustration;
	}
	public void setIllustration(String illustration) {
		this.illustration = illustration;
	}


}
