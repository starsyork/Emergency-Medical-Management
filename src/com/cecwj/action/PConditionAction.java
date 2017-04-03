package com.cecwj.action;

import com.cecwj.model.PCondition;
import com.cecwj.model.Patient;
import com.cecwj.model.User;
import com.cecwj.model.serialize.JsonBase;
import com.cecwj.model.serialize.android.PConditionPhone;
import com.cecwj.model.serialize.android.PatientAndPContionPhone;
import com.cecwj.service.PConditionManager;
import com.cecwj.service.PatientManager;
import com.cecwj.service.UserManager;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PConditionAction extends BaseAction {
	private PConditionManager pConditionManager;
	private UserManager userMamager;
	private PatientManager patientManager;
	static Log log = LogFactory.getLog(PConditionAction.class);
	int id;
	int ptid;
	float pulse;
	float breath;
	float diastolic;
	float systolic;
	float temperature;
	String comment;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPtid() {
		return this.ptid;
	}

	public void setPtid(int ptid) {
		this.ptid = ptid;
	}

	public float getPulse() {
		return this.pulse;
	}

	public void setPulse(float pulse) {
		this.pulse = pulse;
	}

	public float getBreath() {
		return this.breath;
	}

	public void setBreath(float breath) {
		this.breath = breath;
	}

	public float getDiastolic() {
		return this.diastolic;
	}

	public void setDiastolic(float diastolic) {
		this.diastolic = diastolic;
	}

	public float getSystolic() {
		return this.systolic;
	}

	public void setSystolic(float systolic) {
		this.systolic = systolic;
	}

	public float getTemperature() {
		return this.temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public PConditionManager getpConditionManager() {
		return this.pConditionManager;
	}

	@Resource
	public void setpConditionManager(PConditionManager pConditionManager) {
		this.pConditionManager = pConditionManager;
	}

	public UserManager getUserMamager() {
		return this.userMamager;
	}

	@Resource
	public void setUserMamager(UserManager userMamager) {
		this.userMamager = userMamager;
	}

	public PatientManager getPatientManager() {
		return this.patientManager;
	}

	@Resource
	public void setPatientManager(PatientManager patientManager) {
		this.patientManager = patientManager;
	}

	public String addPCondition() {
		this.result = new JsonBase();

		if (!authorized("id")) {
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
		} else if (this.ptid < 1) {
			this.result.setMsg("请指定病人id");
			this.result.setSuccess(false);
		} else {
			Integer uid = (Integer) getSession().get("id");
			PCondition condition = new PCondition();
			condition.setPid(this.ptid);
			condition.setUid(uid.intValue());
			condition.setBreath(this.breath);
			condition.setComment(this.comment);
			condition.setDiastolic(this.diastolic);
			condition.setPulse(this.pulse);
			condition.setSystolic(this.systolic);
			condition.setTemperature(this.temperature);
			condition.setCondition_time(new Timestamp(System
					.currentTimeMillis()));
			try {
				this.pConditionManager.addCondition(condition);
			} catch (Exception e) {
				this.result.setMsg("添加失败");
				this.result.setSuccess(false);
				log.error(e);
			}
		}

		return "OK";
	}

	public String addPConditionPhone() {
		this.result = new JsonBase();

		if (!authorized("id")) {
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
		} else if (this.ptid < 1) {
			this.result.setMsg("请指定病人id");
			this.result.setSuccess(false);
		} else {
			Integer uid = (Integer) getSession().get("id");
			PCondition condition = new PCondition();
			condition.setPid(this.ptid);
			condition.setUid(uid.intValue());
			condition.setBreath(this.breath);
			if (this.comment != null) {
				try {
					this.comment = new String(
							this.comment.getBytes("ISO-8859-1"), "UTF-8");
				} catch (UnsupportedEncodingException e1) {
					this.comment = null;
				}
			} else {
				this.comment = null;
			}

			condition.setComment(this.comment);
			condition.setDiastolic(this.diastolic);
			condition.setPulse(this.pulse);
			condition.setSystolic(this.systolic);
			condition.setTemperature(this.temperature);
			condition.setCondition_time(new Timestamp(System
					.currentTimeMillis()));
			try {
				this.pConditionManager.addCondition(condition);
			} catch (Exception e) {
				this.result.setMsg("添加失败");
				this.result.setSuccess(false);
				log.error(e);
			}
		}

		return "OK";
	}

	public String updatePCondition() {
		PCondition condition = this.pConditionManager.getConditionById(this.id);
		condition.setBreath(this.breath);
		condition.setComment(this.comment);
		condition.setDiastolic(this.diastolic);
		condition.setPulse(this.pulse);
		condition.setSystolic(this.systolic);
		condition.setTemperature(this.temperature);
		try {
			this.pConditionManager.update(condition);
			this.result.setSuccess(true);
			this.result.setMsg("修改成功");
		} catch (Exception e) {
			this.result.setSuccess(false);
			this.result.setMsg("修改失败，请检查网络连接");
			log.error(e);
		}
		return "OK";
	}

	public String getPConditions() {
		this.result = new JsonBase();
		int page = 1;
		String str = getRequest().getParameter("page");
		if ((str != null) && (!str.equals(""))) {
			page = Integer.valueOf(str).intValue();
			try {
				List<PCondition> list = this.pConditionManager.getConditions(
						page, this.id);
				int num = this.pConditionManager.getTotalNum(this.id);
				this.result.setResults(list);
				this.result.setTotal(num);
				this.result.setSuccess(true);
			} catch (Exception e) {
				this.result.setMsg("查询病情失败，请检查网络");
				this.result.setSuccess(false);
				log.error(e);
			}
		}

		return "OK";
	}

	public String getPConditionsPhone() {
		this.result = new JsonBase();
		String pid = getRequest().getParameter("pid");
		if ((pid != null) && (!pid.equals(""))) {
			int ptid = Integer.valueOf(pid).intValue();
			try {
				List<PCondition> list = this.pConditionManager.getConditions(
						-1, ptid);
				List<PConditionPhone> listP = new ArrayList();
				for (PCondition p : list) {
					PConditionPhone pp = new PConditionPhone(p);
					listP.add(pp);
				}

				this.result.setResults(listP);
				this.result.setTotal(listP.size());
				this.result.setSuccess(true);
			} catch (Exception e) {
				this.result.setMsg("查询病情失败，请检查网络");
				this.result.setSuccess(false);
				log.error(e);
			}
		}

		return "OK";
	}

	public String getPConditionById() {
		this.result = new JsonBase();
		try {
			PCondition pcondition = this.pConditionManager
					.getConditionById(this.id);
			this.result.setResults(pcondition);
			this.result.setSuccess(true);
		} catch (Exception e) {
			this.result.setMsg("查询病情失败，请检查网络");
			this.result.setSuccess(false);
			log.error(e);
		}
		return "OK";
	}

	public String getPConditionByIdPhone() {
		this.result = new JsonBase();
		try {
			PCondition pcondition = this.pConditionManager
					.getConditionById(this.id);
			if (pcondition.getUid() != 0) {
				String docName = this.userMamager.getUserById(
						Integer.valueOf(pcondition.getUid())).getUserName();
				Patient patient = this.patientManager.getPatientById(pcondition
						.getPid());
				PatientAndPContionPhone pc = new PatientAndPContionPhone(
						patient, docName, pcondition);
				this.result.setResults(pc);
				this.result.setSuccess(true);
			} else {
				Patient patient = this.patientManager.getPatientById(pcondition
						.getPid());
				PatientAndPContionPhone pc = new PatientAndPContionPhone(
						patient, pcondition);
				this.result.setResults(pc);
				this.result.setSuccess(true);
			}
		} catch (Exception e) {
			this.result.setMsg("查询病情失败，请检查网络");
			this.result.setSuccess(false);
			log.error(e);
		}
     return "OK";
               }
             }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\action\PConditionAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */