package com.cecwj.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@javax.persistence.Entity
@javax.persistence.Table(name = "condition")
public class Condition {
	private int id;
	private int pid;
	private int uid;
	private int pulse;
	private int breath;
	private int diastolic_blood_pressure;
	private int systolic_blood_pressure;
	private int temperature;
	private String comment;
	private Timestamp condition_time;

	@Id
	@GeneratedValue
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column
	public int getPid() {
		return this.pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	@Column
	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	@Column
	public int getPulse() {
		return this.pulse;
	}

	public void setPulse(int pulse) {
		this.pulse = pulse;
	}

	@Column
	public int getBreath() {
		return this.breath;
	}

	public void setBreath(int breath) {
		this.breath = breath;
	}

	@Column
	public int getDiastolic_blood_pressure() {
		return this.diastolic_blood_pressure;
	}

	public void setDiastolic_blood_pressure(int diastolic_blood_pressure) {
		this.diastolic_blood_pressure = diastolic_blood_pressure;
	}

	@Column
	public int getSystolic_blood_pressure() {
		return this.systolic_blood_pressure;
	}

	public void setSystolic_blood_pressure(int systolic_blood_pressure) {
		this.systolic_blood_pressure = systolic_blood_pressure;
	}

	@Column
	public int getTemperature() {
		return this.temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	@Column
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column
	public Timestamp getCondition_time() {
		return this.condition_time;
	}

	public void setCondition_time(Timestamp condition_time) {
		this.condition_time = condition_time;
	}
 }


