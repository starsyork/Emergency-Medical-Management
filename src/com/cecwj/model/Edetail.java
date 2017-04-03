package com.cecwj.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@javax.persistence.Entity
@javax.persistence.Table(name = "detail")
public class Edetail {
	private int id;
	private int mid; //病人Id
	private String item;
	private String itemStr;
	private int resultNum;
	private String flag;
	private String scope;
	private String unit;
	private float min;
	private float max;
	private int applyId;
	private int did;
	private Timestamp time;
	private String status;
	public enum TypeB {
		unexecuted, executing, finished;
	}
	private Edetail.TypeB typeB;
	
	@Id
	@GeneratedValue
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="mid")
	public int getMid() {
		return this.mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	@Column(name="item")
	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	@Column(name="itemStr")
	public String getItemStr() {
		return this.itemStr;
	}

	public void setItemStr(String itemStr) {
		this.itemStr = itemStr;
	}

	@Column(name="result")
	public int getResultNum() {
		return resultNum;
	}	
	public void setResultNum(int resultNum) {
		this.resultNum = resultNum;
	}
	@Column(name="flag")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	public Edetail.TypeB getTypeB() {
		return this.typeB;
	}

	public void setTypeB(Edetail.TypeB typeB) {
		this.typeB = typeB;
		if (typeB.equals(Edetail.TypeB.unexecuted)) {
			this.status = "未领取";
		} else if (typeB.equals(Edetail.TypeB.executing)) {
			this.status = "已领取";
		} else if (typeB.equals(Edetail.TypeB.finished)) {
			this.status = "已完成";
		}
	}

	@Transient
	public String getStatus() {
		if ((this.status == null) && (this.typeB != null)) {
			if (this.typeB.equals(Edetail.TypeB.unexecuted)) {
				this.status = "未领取";
			} else if (this.typeB.equals(Edetail.TypeB.executing)) {
				this.status = "已领取";
			} else if (this.typeB.equals(Edetail.TypeB.finished)) {
				this.status = "已完成";
			}
		}
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
		if (status.equals("")) {
			return;
		}
		if (status.equals("未领取")) {
			this.typeB = Edetail.TypeB.unexecuted;
		} else if (status.equals("已领取")) {
			this.typeB = Edetail.TypeB.executing;
		} else if (status.equals("已完成")) {
			this.typeB = Edetail.TypeB.finished;
		}
	}

	@Column(name = "scope")
	public String getScope() {
		return this.scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	@Column(name = "unit")
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "min")
	public float getMin() {
		return this.min;
	}

	public void setMin(float min) {
		this.min = min;
	}

	@Column(name = "max")
	public float getMax() {
		return this.max;
	}

	public void setMax(float max) {
		this.max = max;
	}

	@Column(name="applyId")
	public int getApplyId() {
		return applyId;
	}

	public void setApplyId(int applyId) {
		this.applyId = applyId;
	}

	@Column(name="time")
	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	
	@Column(name="did")
	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	
	
}

/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\Edetail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */