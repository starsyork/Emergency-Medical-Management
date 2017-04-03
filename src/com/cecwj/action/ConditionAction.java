package com.cecwj.action;

import com.cecwj.model.Condition;
import com.cecwj.model.serialize.JsonBase;
import com.cecwj.service.ConditionManager;
import java.sql.Timestamp;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConditionAction extends BaseAction {
	private ConditionManager conditionManager;
	static Log log = LogFactory.getLog(ConditionAction.class);
	int pid;
	int pulse;
	int breath;
	int diastolic_blood_pressure;
	int systolic_blood_pressure;
	int temperature;
	String comment;

	public int getPid() {
		return this.pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getPulse() {
		return this.pulse;
	}

	public void setPulse(int pulse) {
		this.pulse = pulse;
	}

	public int getBreath() {
		return this.breath;
	}

	public void setBreath(int breath) {
		this.breath = breath;
	}

	public int getDiastolic_blood_pressure() {
		return this.diastolic_blood_pressure;
	}

	public void setDiastolic_blood_pressure(int diastolic_blood_pressure) {
		this.diastolic_blood_pressure = diastolic_blood_pressure;
	}

	public int getSystolic_blood_pressure() {
		return this.systolic_blood_pressure;
	}

	public void setSystolic_blood_pressure(int systolic_blood_pressure) {
		this.systolic_blood_pressure = systolic_blood_pressure;
	}

	public int getTemperature() {
		return this.temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public ConditionManager getConditionManager() {
		return this.conditionManager;
	}

	@Resource
	public void setConditionManager(ConditionManager conditionManager) {
		this.conditionManager = conditionManager;
	}

	public String addCondition() {
		this.result = new JsonBase();
		Integer id = (Integer) getSession().get("id");
		if ((id == null) || (id.intValue() < 0)) {
			this.result.setMsg("用户不存在，请重新登录");
			this.result.setSuccess(false);
			log.error("请重新登录");
		} else {
			Condition condition = new Condition();
			condition.setPid(this.pid);
			condition.setUid(id.intValue());
			condition.setBreath(this.breath);
			condition.setComment(this.comment);
			condition
					.setDiastolic_blood_pressure(this.diastolic_blood_pressure);
			condition.setPulse(this.pulse);
			condition.setSystolic_blood_pressure(this.systolic_blood_pressure);
			condition.setTemperature(this.temperature);
			condition.setCondition_time(new Timestamp(System
					.currentTimeMillis()));
			try {
				this.conditionManager.addCondition(condition);
			} catch (Exception e) {
				this.result.setMsg("添加失败");
				this.result.setSuccess(false);
				log.error(e);
			}
		}
		return "OK";
	}

	public String getConditions() {
		this.result = new JsonBase();
		int page = 1;
		page = Integer.valueOf(getRequest().getParameter("page")).intValue();
		if (page < 1) {
			this.result.setMsg("请指定页数");
			this.result.setSuccess(false);
			log.error("未指定页数");
		} else {
			try {
				this.conditionManager
						.getConditions(page, this.pid, this.result);
			} catch (Exception e) {
				this.result.setMsg("读取信息失败，请检查网络原因");
				this.result.setSuccess(false);
				log.error(e);
			}
		}
		return "OK";
	}
}

