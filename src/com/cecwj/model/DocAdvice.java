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
@Table(name = "docAdvice")

public class DocAdvice {
	public enum BType {
		MEDICINE, EX, NURSE, FOOD, INSPECTION, EXAMINE, OPS, NARCOSIS;
	}

	public enum AType {
		TEMPORARY, LONGTERM;
	}

	public enum Status {
		NOEXECUTE, EXECUTE, EFFECT, STOP;
	}

	private int id;
	private int pid;
	private int uid;
	private String content;
	private String spec;
	private Timestamp start_time;
	private String startTime;
	private Timestamp stop_time;
	private String endTime;
	private DocAdvice.Status status;
	private String state;
	private DocAdvice.AType aType;
	private String type;
	private String dose;
	private String usage;
	private String frequency;
	private DocAdvice.BType bType;
	private String type2;
	private int flag;

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
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "comment")
	public String getSpec() {
		return this.spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	@Column(name = "start_time")
	public Timestamp getStart_time() {
		return this.start_time;
	}

	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}

	@Column(name = "stop_time")
	public Timestamp getStop_time() {
		return this.stop_time;
	}

	public void setStop_time(Timestamp stop_time) {
		this.stop_time = stop_time;
	}
	@Column
	public String getDose() {
		return this.dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	@Column(name = "useway")
	public String getUsage() {
		return this.usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	@Column
	public String getFrequency() {
		return this.frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
/*****************************************************************/	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	public DocAdvice.Status getStatus() {
		return this.status;
	}

	public void setStatus(DocAdvice.Status status) {
		this.status = status;
		if (status.equals(DocAdvice.Status.NOEXECUTE)) {
			this.state = "未执行";
		} else if (status.equals(DocAdvice.Status.EXECUTE)) {
			this.state = "已执行";
		} else if (status.equals(DocAdvice.Status.EFFECT)) {
			this.state = "有效";
		} else if (status.equals(DocAdvice.Status.STOP))
			this.state = "已停止";
	}

	@Transient
	public String getState() {
		if ((this.state == null) && (this.status != null)) {
			if (this.status.equals(DocAdvice.Status.NOEXECUTE)) {
				this.state = "未执行";
			} else if (this.status.equals(DocAdvice.Status.EXECUTE)) {
				this.state = "已执行";
			} else if (this.status.equals(DocAdvice.Status.EFFECT)) {
				this.state = "有效";
			} else if (this.status.equals(DocAdvice.Status.STOP)) {
				this.state = "已执行";
			}
		}

		return this.state;
	}

	public void setState(String state) {
		this.state = state;
		if (state.equals("")) {
			return;
		}
		if (state.equals("未执行")) {
			this.status = DocAdvice.Status.NOEXECUTE;
		} else if (state.equals("已执行")) {
			this.status = DocAdvice.Status.EXECUTE;
		} else if (state.equals("有效")) {
			this.status = DocAdvice.Status.EFFECT;
		} else if (state.equals("已停止")) {
			this.status = DocAdvice.Status.STOP;
		}
	}	
	
	
	
	
	
/*****************************************************************/	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "dtype")
	public DocAdvice.AType getAType() {
		return this.aType;
	}
		public void setAType(DocAdvice.AType aType) {
		this.aType = aType;
		if (aType.equals(DocAdvice.AType.TEMPORARY)) {
			this.type = "临时医嘱";
		} else if (aType.equals(DocAdvice.AType.LONGTERM))
			this.type = "长期医嘱";
	}
	@Transient
	public String getType() {
		if ((this.type == null) && (this.aType != null)) {
			if (this.aType.equals(DocAdvice.AType.TEMPORARY)) {
				this.type = "临时医嘱";
			} else if (this.aType.equals(DocAdvice.AType.LONGTERM)) {
				this.type = "长期医嘱";
			}
		}

		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
		if (type.equals("")) {
			return;
		}
		if (type.equals("临时医嘱")) {
			this.aType = DocAdvice.AType.TEMPORARY;
		} else if (type.equals("长期医嘱")) {
			this.aType = DocAdvice.AType.LONGTERM;
		}
	}
/*****************************************************************/	
	
	@Transient
	public String getStartTime() {
		if (this.startTime == null) {
			if (this.start_time != null) {
				this.startTime = TimeFormat.timeStampToString(this.start_time);
			} else {
				this.startTime = null;
			}
		}
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
		if (startTime.equals("")) {
			return;
		}

		this.start_time = TimeFormat.stringToTimestamp(startTime);
	}

	@Transient
	public String getEndTime() {
		if (this.endTime == null) {
			if (this.stop_time != null) {
				this.endTime = TimeFormat.timeStampToString(this.stop_time);
			} else {
				this.endTime = null;
			}
		}
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;

		if (endTime.equals("")) {
			return;
		}

		this.stop_time = TimeFormat.stringToTimestamp(endTime);
	}
/*****************************************************************/	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "btype")
	public DocAdvice.BType getbType() {
		return this.bType;
	}

	public void setbType(DocAdvice.BType bType) {
		this.bType = bType;
		if (bType.equals(DocAdvice.BType.MEDICINE)) {
			this.type2 = "药疗";
		} else if (bType.equals(DocAdvice.BType.EX)) {
			this.type2 = "处置";
		} else if (bType.equals(DocAdvice.BType.NURSE)) {
			this.type2 = "护理";
		} else if (bType.equals(DocAdvice.BType.FOOD)) {
			this.type2 = "膳食";
		} else if (bType.equals(DocAdvice.BType.INSPECTION)) {
			this.type2 = "检查";
		} else if (bType.equals(DocAdvice.BType.EXAMINE)) {
			this.type2 = "检验";
		} else if (bType.equals(DocAdvice.BType.OPS)) {
			this.type2 = "手术";
		} else if (bType.equals(DocAdvice.BType.NARCOSIS)) {
			this.type2 = "麻醉";
		}
	}

	@Transient
	public String getType2() {
		if ((this.type2 == null) && (this.bType != null)) {
			if (this.bType.equals(DocAdvice.BType.MEDICINE)) {
				this.type2 = "药疗";
			} else if (this.bType.equals(DocAdvice.BType.EX)) {
				this.type2 = "处置";
			} else if (this.bType.equals(DocAdvice.BType.FOOD)) {
				this.type2 = "膳食";
			} else if (this.bType.equals(DocAdvice.BType.INSPECTION)) {
				this.type2 = "检查";
			} else if (this.bType.equals(DocAdvice.BType.EXAMINE)) {
				this.type2 = "检验";
			} else if (this.bType.equals(DocAdvice.BType.OPS)) {
				this.type2 = "手术";
			} else if (this.bType.equals(DocAdvice.BType.NARCOSIS)) {
				this.type2 = "麻醉";
			}
		}

		return this.type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
		if (type2.equals("")) {
			return;
		}
		if (type2.equals("药疗")) {
			this.bType = DocAdvice.BType.MEDICINE;
		} else if (type2.equals("处置")) {
			this.bType = DocAdvice.BType.EX;
		} else if (type2.equals("膳食")) {
			this.bType = DocAdvice.BType.FOOD;
		} else if (type2.equals("护理")) {
			this.bType = DocAdvice.BType.NURSE;
		} else if (type2.equals("检查")) {
			this.bType = DocAdvice.BType.INSPECTION;
		} else if (type2.equals("检验")) {
			this.bType = DocAdvice.BType.EXAMINE;
		} else if (type2.equals("手术")) {
			this.bType = DocAdvice.BType.OPS;
		} else if (type2.equals("麻醉")) {
			this.bType = DocAdvice.BType.NARCOSIS;
		}
	}
/*****************************************************************/	
	public int getFlag() {
		return this.flag;
	}

	@Column
	public void setFlag(int flag) {
		this.flag = flag;
	}
}
