package com.cecwj.model.serialize;

import java.sql.Timestamp;

import com.cecwj.model.Apply;
import com.cecwj.model.Doctor;
import com.cecwj.model.Patient;

public class ApplyContent {
	private int id;
	private int pid;
	private int did;
	private String PatientName;
	private String DoctorName;
	private Timestamp time;
	public ApplyContent(Apply apply,Patient patient,Doctor doctor){
		this.id = apply.getId();
		this.pid = apply.getPid();
		this.did = apply.getDid();
		this.PatientName = patient.getName();
		this.DoctorName = doctor.getName();
		this.time = apply.getTime();		
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
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getPatientName() {
		return PatientName;
	}
	public void setPatientName(String patientName) {
		PatientName = patientName;
	}
	public String getDoctorName() {
		return DoctorName;
	}
	public void setDoctorName(String doctorName) {
		DoctorName = doctorName;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}

}
