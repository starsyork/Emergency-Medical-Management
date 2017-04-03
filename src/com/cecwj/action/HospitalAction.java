             package com.cecwj.action;

import com.cecwj.model.Hospital;
import com.cecwj.model.serialize.JsonBase;
import com.cecwj.service.BedManager;
import com.cecwj.service.HospitalManager;
import com.cecwj.service.TransportManager;
import com.cecwj.service.UserManager;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HospitalAction extends BaseAction {
	private HospitalManager hospitalManager;
	private TransportManager transportManager;
	static Log log = LogFactory.getLog(HospitalAction.class);
	private int id;
	private String hospName;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getHospName() {
		return hospName;
	}

	public void setHospName(String hospName) {
		this.hospName = hospName;
	}

	public HospitalManager getHospitalManager() {
		return this.hospitalManager;
	}

	@Resource
	public void setHospitalManager(HospitalManager hospitalManager) {
		this.hospitalManager = hospitalManager;
	}


	public TransportManager getTransportManager() {
		return transportManager;
	}
	@Resource
	public void setTransportManager(TransportManager transportManager) {
		this.transportManager = transportManager;
	}

	public JsonBase getResult() {
		return this.result;
	}

	public void setResult(JsonBase result) {
		this.result = result;
	}

	public String getAllHospital() {
		this.result = new JsonBase();
		List<Hospital> list = null;
		try {
			list = this.hospitalManager.getAllHospital();
			this.result.setResults(list);
			this.result.setSuccess(true);
			this.result.setTotal(list.size());
		} catch (Exception e) {
			this.result.setMsg("请检查网络链接是否正常！");
			this.result.setSuccess(false);
			log.error(e);
		}
		return "OK";
	}

	public String addHospital() {
		this.result = new JsonBase();
		Hospital hospital = new Hospital();
		try {
			this.hospName = new String(this.hospName.getBytes("ISO-8859-1"),
					"utf-8");
		} catch (UnsupportedEncodingException e1) {
			this.hospName = null;
		}
		if ((this.hospName == null) || (this.hospName.equals(""))) {
			this.result.setMsg("请输入分区名称");
			this.result.setSuccess(false);
			log.error("请输入分区名称");
		} else if (this.hospitalManager.getHospitalByName(this.hospName) != null) {
			this.result.setMsg("该分区已存在，请重新输入分区名称");
			this.result.setSuccess(false);
			log.error("分区名称重复");
		} else {
			hospital.setHospName(this.hospName);
			try {
				this.hospitalManager.addHospital(hospital);
			} catch (Exception e) {
				this.result = new JsonBase("添加分区失败，请稍后重试", false);
				log.error(e);
			}
		}

		return "OK";
	}

	public String deleteHospital() {
		this.result = new JsonBase();
		int hid = -1;
		try {
			hid = Integer.parseInt(getRequest().getParameter("hid"));
		} catch (Exception e) {
			log.error(e);
		}
		if (id == -1) {
			this.result.setMsg("请选择要删除的医院");
			this.result.setSuccess(false);
			log.error("分区id不正确");
		} else {
		try{
				this.hospitalManager.deleteHospital(id);
				} catch (Exception e) {
					this.result.setMsg("删除失败，请检查网络连接");
					this.result.setSuccess(false);
					log.error(e);
				}
		}
		return "OK";
	}

	public String modifyHosptail() {
		this.result = new JsonBase();
		int sid = 0;
		String HospName = null;
		try {
			sid = Integer.valueOf(getRequest().getParameter("sid")).intValue();
		} catch (Exception e) {
			this.result.setMsg("请选择病区");
			this.result.setSuccess(false);
			return "OK";
		}
		HospName = getRequest().getParameter("HospName");
		if ((HospName == null) || (HospName.equals(""))) {
			this.result.setMsg("分区名不能为空");
			this.result.setSuccess(false);
			return "OK";
		}
		try {
			HospName = new String(HospName.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e1) {
			this.result.setMsg("分区名错误");
			this.result.setSuccess(false);
			return "OK";
		}
		if (this.hospitalManager.getHospitalByName(HospName) != null) {
			this.result.setMsg("该分区已存在，请重新输入分区名称");
			this.result.setSuccess(false);
			return "OK";
		}
		try {
			Hospital hospital = this.hospitalManager.getHospitalById(sid);
			hospital.setHospName(HospName);
			this.hospitalManager.update(hospital);
			this.result.setMsg("修改成功");
			this.result.setSuccess(true);
		} catch (Exception e) {
			this.result.setMsg("修改失败");
			this.result.setSuccess(false);
			e.printStackTrace();
		}
     return "OK";
               }
             }

