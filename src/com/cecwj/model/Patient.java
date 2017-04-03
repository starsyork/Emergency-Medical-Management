package com.cecwj.model;

import com.cecwj.common.TimeFormat;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "patient")
public class Patient {

	public enum Status {
		NOASSGNED, ASSIGNEDNOENTER, ENTER, OUT;
	}

	public enum Wound_type {
		MACHINE, PRESSASPHYXIA, DROWNING, BURN, COLDINJURY, POISONING, denger;
	}

	private int id;
	private String rfid;
	private Timestamp requestTime;
	private Patient.Status status;
	private int sid;
	private String name;
	private int sex;
	private int age;
	private String wound_address;
	private String woundAddr;
	private Patient.Wound_type wound_type;
	private String woundType;
	private Timestamp wound_time;
	private String woundTime;
	private String photo;
	private int uid;
	private int pflag;

	@Id
	@GeneratedValue
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "rfid")
	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	@Column(name = "req_time")
	public Timestamp getRequestTime() {
		return this.requestTime;
	}

	public void setRequestTime(Timestamp requestTime) {
		this.requestTime = requestTime;
	}

	@Column
	public Patient.Status getStatus() {
		return this.status;
	}

	public void setStatus(Patient.Status status) {
		this.status = status;
	}

	@Column
	public int getSid() {
		return this.sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	@Column
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column
	public int getSex() {
		return this.sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	@Column
	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Column
	public String getWound_address() {
		return this.wound_address;
	}

	public void setWound_address(String wound_address) {
		this.wound_address = wound_address;
	}

	@Transient
	public String getWoundAddr() {
		if ((this.woundAddr == null) && (this.wound_address != null))
			this.woundAddr = this.wound_address;
		return this.woundAddr;
	}

	public void setWoundAddr(String woundAddr) {
		this.woundAddr = woundAddr;
		if ((woundAddr != null) && (!woundAddr.equals("")))
			this.wound_address = woundAddr;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column
	public Patient.Wound_type getWound_type() {
		return this.wound_type;
	}

	public void setWound_type(Patient.Wound_type wound_type) {
		this.wound_type = wound_type;
		if (wound_type.equals(Patient.Wound_type.MACHINE)) {
			this.woundType = "机械性外伤";
		} else if (wound_type.equals(Patient.Wound_type.PRESSASPHYXIA)) {
			this.woundType = "埋压窒息伤";
		} else if (wound_type.equals(Patient.Wound_type.DROWNING)) {
			this.woundType = "淹溺";
		} else if (wound_type.equals(Patient.Wound_type.BURN)) {
			this.woundType = "烧伤";
		} else if (wound_type.equals(Patient.Wound_type.COLDINJURY)) {
			this.woundType = "冻伤";
		} else if (wound_type.equals(Patient.Wound_type.POISONING))
			this.woundType = "中毒";
	}

	@Transient
	public String getWoundType() {
		if ((this.woundType == null) && (this.wound_type != null)) {
			if (this.wound_type.equals(Patient.Wound_type.MACHINE)) {
				this.woundType = "机械性外伤";
			} else if (this.wound_type.equals(Patient.Wound_type.PRESSASPHYXIA)) {
				this.woundType = "埋压窒息伤";
			} else if (this.wound_type.equals(Patient.Wound_type.DROWNING)) {
				this.woundType = "淹溺";
			} else if (this.wound_type.equals(Patient.Wound_type.BURN)) {
				this.woundType = "烧伤";
			} else if (this.wound_type.equals(Patient.Wound_type.COLDINJURY)) {
				this.woundType = "冻伤";
			} else if (this.wound_type.equals(Patient.Wound_type.POISONING))
				this.woundType = "中毒";
		}
		return this.woundType;
	}

	public void setWoundType(String woundType) {
		this.woundType = woundType;
		if (woundType.equals("")) {
			return;
		}
		if (woundType.equals("机械性外伤")) {
			this.wound_type = Patient.Wound_type.MACHINE;
		} else if (woundType.equals("埋压窒息伤")) {
			this.wound_type = Patient.Wound_type.PRESSASPHYXIA;
		} else if (woundType.equals("淹溺")) {
			this.wound_type = Patient.Wound_type.DROWNING;
		} else if (woundType.equals("烧伤")) {
			this.wound_type = Patient.Wound_type.BURN;
		} else if (woundType.equals("烧伤")) {
			this.wound_type = Patient.Wound_type.COLDINJURY;
		} else if (woundType.equals("中毒"))
			this.wound_type = Patient.Wound_type.POISONING;
	}

	@Column
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Column
	public Timestamp getWound_time() {
		return this.wound_time;
	}

	public void setWound_time(Timestamp wound_time) {
		this.wound_time = wound_time;
		if (wound_time != null)
			this.woundTime = TimeFormat.timeStampToString(wound_time);
	}

	@Transient
	public String getWoundTime() {
		if ((this.woundTime == null) && (this.wound_time != null)) {
			this.woundTime = TimeFormat.timeStampToString(this.wound_time);
		}
		return this.woundTime;
	}

	public void setWoundTime(String woundTime) {
		this.woundTime = woundTime;
		if (woundTime.equals(""))
			return;
		this.wound_time = TimeFormat.stringToTimestamp(woundTime);
	}

	@Column
	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getPflag() {
		return this.pflag;
	}

	@Column
	public void setPflag(int pflag) {
		this.pflag = pflag;
	}
}
