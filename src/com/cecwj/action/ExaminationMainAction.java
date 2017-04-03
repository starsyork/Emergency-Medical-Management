package com.cecwj.action;

import com.cecwj.model.ExaminationMain;
import com.cecwj.model.serialize.JsonBase;
import com.cecwj.service.EdetailManager;
import com.cecwj.service.ExaminationMainManager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class ExaminationMainAction extends BaseAction {
	private int id;
	private ExaminationMainManager examinationMainManager;
	private EdetailManager edetailManager;

	public ExaminationMainManager getExaminationMainManager() {
		return this.examinationMainManager;
	}

	@javax.annotation.Resource
	public void setExaminationMainManager(
			ExaminationMainManager examinationMainManager) {
		this.examinationMainManager = examinationMainManager;
	}

	public EdetailManager getEdetailManager() {
		return this.edetailManager;
	}

	public void setEdetailManager(EdetailManager edetailManager) {
		this.edetailManager = edetailManager;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExaminationMain() {
		this.result = new JsonBase();
		int page = 1;
		try {
			page = Integer.parseInt(getRequest().getParameter("page"));
		} catch (Exception e) {
			this.log.error(e);
		}
		int pid = -1;
		try {
			String s = getRequest().getParameter("id");
			if (s != null) {
				pid = Integer.parseInt(s);
			} else {
				pid = -1;
			}
		} catch (Exception e) {
			this.log.error(e);
		}
		if (pid == -1) {
			this.result.setMsg("病人id不正确！");
			this.result.setSuccess(true);
			this.result.setTotal(0);
		} else {
			try {
				List<ExaminationMain> exMains = this.examinationMainManager
						.getExaminationMain(page, pid);
				int total = exMains.size();
				this.result.setResults(exMains);
				this.result.setSuccess(true);
				this.result.setTotal(total);
			} catch (Exception e) {
				this.result.setSuccess(false);
				this.result.setMsg("获取检查检验结果失败");
				e.printStackTrace();
			}
		}

		return "OK";
	}

	public String getExaminationMainPhone() {
		this.result = new JsonBase();
		int page = 1;
		try {
			page = Integer.parseInt(getRequest().getParameter("page"));
		} catch (Exception e) {
			this.log.error(e);
		}
		int pid = -1;
		try {
			String s = getRequest().getParameter("id");
			if (s != null) {
				pid = Integer.parseInt(s);
			} else {
				pid = -1;
			}
		} catch (Exception e) {
			this.log.error(e);
		}
		if (pid == -1) {
			this.result.setMsg("病人id不正确！");
			this.result.setSuccess(true);
			this.result.setTotal(0);
		} else {
			try {
				List<ExaminationMain> exMains = this.examinationMainManager
						.getExaminationMain(page, pid);
				List<com.cecwj.model.serialize.android.EmainPhone> eps = new java.util.ArrayList();
				for (ExaminationMain emain : exMains) {
					int num = this.edetailManager
							.getEdetailTotal(emain.getId());
					com.cecwj.model.serialize.android.EmainPhone ep = new com.cecwj.model.serialize.android.EmainPhone(
							num, emain);
					eps.add(ep);
				}
				int total = exMains.size();
				this.result.setResults(eps);
				this.result.setSuccess(true);
				this.result.setTotal(total);
			} catch (Exception e) {
				this.result.setSuccess(false);
				this.result.setMsg("获取检查检验结果失败");
				e.printStackTrace();
			}
		}

		return "OK";
	}
}
