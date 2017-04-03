package com.cecwj.action;

import com.cecwj.common.TimeFormat;
import com.cecwj.model.Epidemic;
import com.cecwj.model.Inspect;
import com.cecwj.model.serialize.JsonBase;
import com.cecwj.service.EpidemicManager;

import java.sql.Timestamp;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class EpidemicAction extends BaseAction {
	private EpidemicManager epidemicManager;
	private int id;
	private String name;
	private int number;
	private String zone;
	private String time;
	private String degree;
	private String proc;

	static Log log = LogFactory.getLog(EpidemicAction.class);

	public EpidemicManager getEpidemicManager() {
		return this.epidemicManager;
	}

	@Resource
	public void setEpidemicManager(EpidemicManager epidemicManager) {
		this.epidemicManager = epidemicManager;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getZone() {
		return this.zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDegree() {
		return this.degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getProc() {
		return this.proc;
	}

	public void setProc(String proc) {
		this.proc = proc;
	}

	public String AddEpidemic() {
		this.result = new JsonBase();
		if ((!this.name.equals("")) && (this.name == null)) {
			this.result.setMsg("增加失败，请输入疫情名！");
			this.result.setSuccess(false);
		} else if ((this.zone.equals("")) && (this.zone == null)) {
			this.result.setMsg("增加失败，请输入疫情区域！");
			this.result.setSuccess(false);
		} else if (((Integer) this.number == null) && (this.number < 0)) {
			this.result.setMsg("增加失败，请输入疫情人数！");
			this.result.setSuccess(false);
		} else if ((this.zone.equals("")) && (this.degree == null)) {
			this.result.setMsg("增加失败，请输入疫情严重程度！");
			this.result.setSuccess(false);
		} else if ((this.proc.equals("")) && (this.proc == null)) {
			this.result.setMsg("增加失败，请输入疫情处治方法");
			this.result.setSuccess(false);
		} else {
			Epidemic epidemic = new Epidemic();
			epidemic.setName(this.name);
			epidemic.setNumber(this.number);
			epidemic.setZone(this.zone);
			epidemic.setProc(this.proc);
			epidemic.setDegree(this.degree);
			try {
				this.epidemicManager.add(epidemic);
			} catch (Exception e) {
				this.result.setMsg("修改信息失败");
				this.result.setSuccess(false);
				log.error("用户修改信息失败");
			}
		}
		this.result.setMsg("修改信息成功");
		this.result.setSuccess(true);
		return "OK";
	}

	public String getEpidemic() {
		this.result = new JsonBase();
		int page = -1;
		int limit = 25;
		try {
			String pa = getRequest().getParameter("page");
			String li = getRequest().getParameter("limit");
			if(pa!=null)
			page = Integer.parseInt(pa);
			if(li!=null)
			limit = Integer.parseInt(li);
		} catch (Exception e) {
			System.out.println(e);
			log.error(e);
		}
		try {
			List<Epidemic> epidemic = this.epidemicManager.getAllEpidemic();
			List<Epidemic> da3 = new ArrayList();
			if (page != -1) {					
					for (int i = (page - 1) * limit; (i < epidemic.size())
							&& (i < page * limit); i++) {
						da3.add((Epidemic) epidemic.get(i));
					}
					int total = epidemic.size();
					this.result.setResults(da3);
					this.result.setSuccess(true);
					this.result.setTotal(total);					
			} else {
				int total = epidemic.size();
				this.result.setResults(epidemic);
				this.result.setSuccess(true);
				this.result.setTotal(total);
			}
		} catch (Exception e) {
			System.out.println(e);
			this.result.setMsg("请检查网络链接是否正常！");
			this.result.setSuccess(false);
			log.error(e);
		}
		return "OK";
	}

	public String UpdataEpidemic() {
		this.result = new JsonBase();
		if (((Integer) this.id == null) || (this.id < 0)) {
			this.result.setMsg("请选择疫情事件！");
			this.result.setSuccess(false);
			log.error("疫情事件id不正确");
		} else {
			Epidemic epidemic = this.epidemicManager.getEpidemic(this.id);
			if ((this.name != null))
				epidemic.setName(this.name);
			if (((Integer) this.number != null) && (this.number > 0))
				epidemic.setNumber(this.number);
			if ((this.zone != null))
				epidemic.setZone(this.zone);
			if ((this.time != null))
				System.out.println(this.time);
				epidemic.setTime(TimeFormat.stringToTimestamp(this.time));
			if ((this.degree != null))
				epidemic.setDegree(this.degree);
			if ((this.proc != null))
				epidemic.setProc(this.proc);
			try {
				this.epidemicManager.update(epidemic);
				this.result.setMsg("修改信息成功");
				this.result.setSuccess(true);
			} catch (Exception e) {
				this.result.setMsg("修改信息失败");
				this.result.setSuccess(false);
				log.error("用户修改信息失败");
			}
		}
		return "OK";
	}

	public String deleteEpidemic() {
		this.result = new JsonBase();
		int id = -1;
		try {
			id = Integer.parseInt(getRequest().getParameter("id"));
		} catch (Exception e) {
			log.error(e);
		}
		if (id == -1) {
			this.result.setMsg("请选择要删除疫情");
			this.result.setSuccess(false);
			log.error("疫情id不正确");
		} else {
			int num = 0;
			try {
				num = this.epidemicManager.getEpidemicById(id);
			} catch (Exception e) {
				log.error(e);
				this.result.setMsg("删除失败，请检查网络");
				this.result.setSuccess(false);
				return "OK";
			}
			if (num > 0) {
				try {
					this.epidemicManager.deleteEpidemic(id);
				} catch (Exception e) {
					this.result.setMsg("删除失败，请检查网络连接");
					this.result.setSuccess(false);
					log.error(e);
				}
			}
		}
		return "OK";
	}
}
