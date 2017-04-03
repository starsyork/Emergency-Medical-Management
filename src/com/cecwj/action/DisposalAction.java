package com.cecwj.action;

import com.cecwj.model.Disposal;
import com.cecwj.model.Injury;
import com.cecwj.model.Measure;
import com.cecwj.model.serialize.JsonBase;
import com.cecwj.service.DisposalManager;
import com.cecwj.service.InjuryManager;
import com.cecwj.service.MeasureManager;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DisposalAction extends BaseAction {
	private DisposalManager disposalManager;
	private MeasureManager measureManager;
	private InjuryManager injuryManager;
	static Log log = LogFactory.getLog(DisposalAction.class);

	public DisposalManager getDisposalManager() {
		return this.disposalManager;
	}

	@Resource
	public void setDisposalManager(DisposalManager disposalManager) {
		this.disposalManager = disposalManager;
	}

	public MeasureManager getMeasureManager() {
		return this.measureManager;
	}

	@Resource
	public void setMeasureManager(MeasureManager measureManager) {
		this.measureManager = measureManager;
	}

	public InjuryManager getInjuryManager() {
		return this.injuryManager;
	}

	@Resource
	public void setInjuryManager(InjuryManager injuryManager) {
		this.injuryManager = injuryManager;
	}

	public String getDisposals() {
		this.result = new JsonBase();
		int pid = 0;
		int page = 1;
		try {
			page = Integer.valueOf(getRequest().getParameter("page")).intValue();
		} catch (Exception e) {
			log.error(e);
		}
		try {
			pid = Integer.valueOf(getRequest().getParameter("id")).intValue();
		} catch (Exception e) {
			pid = 0;
		}
		if (pid == 0) {
			this.result.setSuccess(true);
			log.error("请选择要查看的病人");
		} else {
			List<Disposal> list = null;
			try {
				list = this.disposalManager.getDisposals(pid, page);
				int total = this.disposalManager.getTotalNum(pid);
				for (int i = 0; i < list.size(); i++) {
					Disposal disposal = (Disposal) list.get(i);
					Measure measure = this.measureManager.getMeasureById(disposal.getMid());
					disposal.setMeasure(measure.getContent());
					list.set(i, disposal);
				}
				this.result.setSuccess(true);
				this.result.setResults(list);
				this.result.setTotal(total);
			} catch (Exception e) {
				log.error(e);
				System.out.println(e);
				this.result.setSuccess(false);
				this.result.setMsg("查询失败，请检查网络");
			}
		}
		return "OK";
	}

	public String getDisposalsPhone() {
		this.result = new JsonBase();
		int pid = 0;
		try {
			pid = Integer.valueOf(getRequest().getParameter("id")).intValue();
		} catch (Exception e) {
			pid = 0;
		}
		if (pid == 0) {
			this.result.setMsg("请选择要查看的病人");
			this.result.setSuccess(false);
			log.error("查看的病人id不正确");
		} else {
			List<Disposal> list = null;
			try {
				list = this.disposalManager.getDisposals(pid, -1);
				for (int i = 0; i < list.size(); i++) {
					Disposal disposal = (Disposal) list.get(i);
					Measure measure = this.measureManager.getMeasureById(disposal.getMid());
					disposal.setMeasure(measure.getContent());
					list.set(i, disposal);
				}
				this.result.setSuccess(true);
				this.result.setResults(list);
				this.result.setTotal(list.size());
			} catch (Exception e) {
				log.error(e);
				System.out.println(e);
				this.result.setSuccess(false);
				this.result.setMsg("查询失败，请检查网络");
			}
		}

		return "OK";
	}

	public String getInjury() {
		this.result = new JsonBase();
		int pid = 0;
		try {
			pid = Integer.valueOf(getRequest().getParameter("pid")).intValue();
		} catch (Exception e) {
			log.error(e);
		}
		Injury injury = this.injuryManager.getInjury(pid);
		this.result.setResults(injury);
		this.result.setSuccess(true);
		return "OK";
	}

	public String addDisposal() {
		this.result = new JsonBase();
		int pid = 0;
		String disposal = null;
		try {
			pid = Integer.valueOf(getRequest().getParameter("pid")).intValue();
			disposal = getRequest().getParameter("disposal");
		} catch (Exception e) {
			log.error(e);
		}
		if ((pid < 1) || (disposal == null) || (disposal.equals(""))) {
			this.result.setMsg("请选择处置途径");
			this.result.setSuccess(false);
		} else {
			String[] list = disposal.split(";");
			String[] arrayOfString1;
			int j = (arrayOfString1 = list).length;
			for (int i = 0; i < j; i++) {
				String s = arrayOfString1[i];
				Disposal dis = new Disposal();
				int pos = s.indexOf(",");
				int mid = Integer.valueOf(s.substring(0, pos)).intValue();
				String time = s.substring(pos + 1);
				dis.setMid(mid);
				dis.setTime(time);
				dis.setPid(pid);
				dis.setType("转运监护");
				this.disposalManager.addDisposal(dis);
			}
			this.result.setSuccess(true);
			this.result.setMsg("添加成功");
		}
		return "OK";
	}
}
