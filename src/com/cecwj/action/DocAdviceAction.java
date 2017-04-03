package com.cecwj.action;

import com.cecwj.model.DocAdvice;
import com.cecwj.model.Patient;
import com.cecwj.model.serialize.JsonBase;
import com.cecwj.model.serialize.TestNewAdvice;
import com.cecwj.service.DocAdviceManager;
import com.cecwj.service.HandlerManager;
import com.cecwj.service.PatientManager;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;

public class DocAdviceAction extends BaseAction {
	DocAdviceManager docAdviceManager;
	private PatientManager patientManager;
	private HandlerManager handlerManager;
	private com.cecwj.service.BedManager bedManager;
	private com.cecwj.service.UserManager userManager;
	private Integer id;
	private String content;
	private String type;
	private String state;
	private String startTime;
	private String endTime;
	private String dose;
	private String usage;
	private String frequency;
	private String spec;
	private String type2;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDose() {
		return this.dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public String getUsage() {
		return this.usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public String getFrequency() {
		return this.frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getSpec() {
		return this.spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getType2() {
		return this.type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public DocAdviceManager getDocAdviceManager() {
		return this.docAdviceManager;
	}

	@Resource
	public void setDocAdviceManager(DocAdviceManager docAdviceManager) {
		this.docAdviceManager = docAdviceManager;
	}

	public PatientManager getPatientManager() {
		return this.patientManager;
	}

	@Resource
	public void setPatientManager(PatientManager patientManager) {
		this.patientManager = patientManager;
	}

	public com.cecwj.service.BedManager getBedManager() {
		return this.bedManager;
	}

	@Resource
	public void setBedManager(com.cecwj.service.BedManager bedManager) {
		this.bedManager = bedManager;
	}

	public com.cecwj.service.UserManager getUserManager() {
		return this.userManager;
	}

	@Resource
	public void setUserManager(com.cecwj.service.UserManager userManager) {
		this.userManager = userManager;
	}

	public HandlerManager getHandlerManager() {
		return this.handlerManager;
	}

	@Resource
	public void setHandlerManager(HandlerManager handlerManager) {
		this.handlerManager = handlerManager;
	}

	public String editDocAdvice() {
		this.result = new JsonBase();
		if ((this.id == null) || (this.id.intValue() < 0)) {
			this.result.setMsg("请选择要更新的医嘱！");
			this.result.setSuccess(false);
			this.log.error("医嘱id不正确");
		} else {
			try {
				DocAdvice docAdvice = this.docAdviceManager
						.getDocAdviceById(this.id.intValue());
				if ((this.type2 != null) && (!this.type2.equals("")))
					docAdvice.setContent(this.type2);
				if ((this.content != null) && (!this.content.equals("")))
					docAdvice.setContent(this.content);
				if ((this.startTime != null) && (!this.startTime.equals("")))
					docAdvice.setStartTime(this.startTime  );
				if ((this.endTime != null) && (!this.endTime.equals("")))
					docAdvice.setEndTime(this.endTime );
				if ((this.dose != null) && (!this.dose.equals("")))
					docAdvice.setDose(this.dose);
				if ((this.usage != null) && (!this.usage.equals("")))
					docAdvice.setUsage(this.usage);
				if ((this.frequency != null) && (!this.frequency.equals("")))
					docAdvice.setFrequency(this.frequency);
				if ((this.spec != null) && (!this.spec.equals("")))
					docAdvice.setSpec(this.spec);
				this.docAdviceManager.update(docAdvice);
				this.result.setSuccess(true);
				this.result.setMsg("编辑保存成功");
				this.log.info("编辑医嘱成功，医嘱id=" + this.id);
			} catch (Exception e) {
				System.out.println("editDocAdvice"+e);
				this.result.setMsg("修改病人医嘱信息失败");
				this.result.setSuccess(false);
				this.log.error("修改信息失败");
			}
		}

		return "OK";
	}

	public String editDocAdvicePhone() {
		this.result = new JsonBase();
		if ((this.id == null) || (this.id.intValue() < 0)) {
			this.result.setMsg("请选择要更新的医嘱！");
			this.result.setSuccess(false);
			this.log.error("医嘱id不正确");
		} else {
			try {
				DocAdvice docAdvice = this.docAdviceManager
						.getDocAdviceById(this.id.intValue());
				if ((this.type2 != null) && (!this.type2.equals(""))) {
					try {
						this.type2 = new String(
								this.type2.getBytes("ISO-8859-1"), "utf-8");
					} catch (UnsupportedEncodingException e1) {
						this.type2 = null;
					}
					docAdvice.setType2(this.type2);
				}
				
				if ((this.content != null) && (!this.content.equals(""))) {
					try {
						this.content = new String(
								this.content.getBytes("ISO-8859-1"), "utf-8");
					} catch (UnsupportedEncodingException e1) {
						this.content = null;
					}

					docAdvice.setContent(this.content);
				}
				if ((this.startTime != null) && (!this.startTime.equals("")))
					docAdvice.setStartTime(this.startTime );
				if ((this.endTime != null) && (!this.endTime.equals("")))
					docAdvice.setEndTime(this.endTime );
				if ((this.dose != null) && (!this.dose.equals(""))) {
					try {
						this.dose = new String(
								this.dose.getBytes("ISO-8859-1"), "utf-8");
					} catch (UnsupportedEncodingException e1) {
						this.dose = null;
					}
					docAdvice.setDose(this.dose);
				}
				if ((this.usage != null) && (!this.usage.equals(""))) {
					try {
						this.usage = new String(
								this.usage.getBytes("ISO-8859-1"), "utf-8");
					} catch (UnsupportedEncodingException e1) {
						this.usage = null;
					}
					docAdvice.setUsage(this.usage);
				}
				if ((this.frequency != null) && (!this.frequency.equals(""))) {
					try {
						this.frequency = new String(
								this.frequency.getBytes("ISO-8859-1"), "utf-8");
					} catch (UnsupportedEncodingException e1) {
						this.frequency = null;
					}
					docAdvice.setFrequency(this.frequency);
				}
				if ((this.spec != null) && (!this.spec.equals(""))) {
					try {
						this.spec = new String(
								this.spec.getBytes("ISO-8859-1"), "utf-8");
					} catch (UnsupportedEncodingException e1) {
						this.spec = null;
					}
					docAdvice.setSpec(this.spec);
				}
				this.docAdviceManager.update(docAdvice);
				this.result.setSuccess(true);
				this.result.setMsg("编辑保存成功");
			} catch (Exception e) {
				this.result.setMsg("修改病人医嘱信息失败");
				this.result.setSuccess(false);
				this.log.error("修改信息失败");
			}
		}

		return "OK";
	}

	public String stopDocAdvice() {
		this.result = new JsonBase();
		if ((this.id == null) || (this.id.intValue() < 0)) {
			this.result.setMsg("请选择要停止的医嘱！");
			this.result.setSuccess(false);
			this.log.error("医嘱id不正确");
		} else {
			DocAdvice docAdvice = this.docAdviceManager
					.getDocAdviceById(this.id.intValue());
			try {
				docAdvice.setState(this.state);
				this.docAdviceManager.update(docAdvice);
				this.result.setSuccess(true);
				this.result.setMsg("停止成功");
				this.log.info("停止医嘱成功，医嘱id=" + this.id);
			} catch (Exception e) {
				this.result.setMsg("停止医嘱失败");
				this.result.setSuccess(false);
				this.log.error("停止医嘱失败");
			}
		}
		return "OK";
	}

	public String stopDocAdvicePhone() {
		this.result = new JsonBase();
		if ((this.id == null) || (this.id.intValue() < 0)) {
			this.result.setMsg("请选择要停止的医嘱！");
			this.result.setSuccess(false);
			this.log.error("医嘱id不正确");
		} else {
			DocAdvice docAdvice = this.docAdviceManager
					.getDocAdviceById(this.id.intValue());
			try {
				docAdvice.setState("已停止");
				this.docAdviceManager.update(docAdvice);
				this.result.setSuccess(true);
				this.result.setMsg("停止成功");
			} catch (Exception e) {
				this.result.setMsg("停止医嘱失败");
				this.result.setSuccess(false);
				this.log.error("停止医嘱失败");
			}
		}

		return "OK";
	}

	public String deleteDocAdvice() {
		this.result = new JsonBase();
		if ((this.id == null) || (this.id.intValue() < 0)) {
			this.result.setMsg("请选择要删除的医嘱！");
			this.result.setSuccess(false);
			this.log.error("医嘱id不正确");
		} else {
			try {
				DocAdvice docAdvice = this.docAdviceManager
						.getDocAdviceById(this.id.intValue());
				this.docAdviceManager.delete(docAdvice);
				this.handlerManager.deleteHandlers(this.id.intValue());
				this.result.setMsg("删除医嘱成功！");
				this.result.setSuccess(true);
				this.log.info("删除医嘱" + this.id + "成功");
			} catch (Exception e) {
				this.log.error(e);
				this.log.info("删除失败，exception");
				this.result.setMsg("删除失败，请检查网络连接是否正常！");
				this.result.setSuccess(false);
			}
		}
		return "OK";
	}

	public String addDocAdvice() {
		if (!authorized("id")) {
			this.result = new JsonBase();
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
			this.log.info("获取当前用户失败");
			return "OK";
		}
		this.result = new JsonBase();
		if ((this.content == null) || (this.content.equals(""))) {
			this.result.setMsg("添加失败，请输入医嘱内容！");
			this.result.setSuccess(false);
		} else if ((this.type == null) || (this.type.equals(""))) {
			this.result.setMsg("添加失败，请输入医嘱实效！");
			this.result.setSuccess(false);

		} else if ((this.startTime == null) || (this.startTime.equals(""))) {

			this.result.setMsg("添加失败，请输入医嘱开始时间！");
			this.result.setSuccess(false);
		} else if ((this.type2 == null) || (this.type2.equals(""))) {
			this.result.setMsg("添加失败，请输入医嘱类型！");
			this.result.setSuccess(false);
		}
		if ((this.type != null) && (this.type.equals("长期医嘱"))) {
			if ((this.state == null) || (this.state.equals(""))) {
				this.result.setMsg("添加失败，请输入医嘱结束时间！");
				this.result.setSuccess(false);
			} else if ((this.endTime == null) || (this.endTime.equals(""))) {
				this.result.setMsg("添加失败，请输入医嘱结束时间！");
				this.result.setSuccess(false);
			} else if ((this.dose == null) || (this.dose.equals(""))) {
				this.result.setMsg("添加失败，请输入剂量！");
				this.result.setSuccess(false);
			} else if ((this.usage == null) || (this.usage.equals(""))) {
				this.result.setMsg("添加失败，请输入用药途径！");
				this.result.setSuccess(false);
			}

			if ((this.frequency == null) || (this.frequency.equals(""))) {
				this.result.setMsg("添加失败，请输入用药次数！");
				this.result.setSuccess(false);
			}
			if ((this.spec == null) || (this.spec.equals(""))) {
				this.result.setMsg("添加失败，请输入用药次数！");
				this.result.setSuccess(false);
			}
			try {
				int pid = Integer.parseInt(getRequest().getParameter("id"));
				int uid = ((Integer) getSession().get("id")).intValue();
				DocAdvice docAdvice = new DocAdvice();
				docAdvice.setPid(pid);
				docAdvice.setUid(uid);
				docAdvice.setContent(this.content);
				docAdvice.setType(this.type);
				docAdvice.setType2(this.type2);
				docAdvice.setState(this.state);
				docAdvice.setStartTime(this.startTime + ":00");
				docAdvice.setEndTime(this.endTime + ":00");
				docAdvice.setDose(this.dose);
				docAdvice.setUsage(this.usage);
				docAdvice.setFrequency(this.frequency);
				docAdvice.setSpec(this.spec);
				this.docAdviceManager.add(docAdvice);
				this.result.setSuccess(true);
				this.result.setMsg("新增医嘱成功");
				this.log.info("新增医嘱成功，docAdvicepid=" + this.id);
			} catch (Exception e) {
				this.result.setMsg("添加病人医嘱信息失败");
				this.result.setSuccess(false);
				this.log.error("添加信息失败");
			}
		} else if ((this.type != null) && (this.type.equals("临时医嘱"))) {
			if ((this.state == null) || (this.state.equals(""))) {
				this.result.setMsg("添加失败，请输入医嘱结束时间！");
				this.result.setSuccess(false);
			} else if ((this.dose == null) || (this.dose.equals(""))) {
				this.result.setMsg("添加失败，请输入剂量！");
				this.result.setSuccess(false);
			} else if ((this.usage == null) || (this.usage.equals(""))) {
				this.result.setMsg("添加失败，请输入用药途径！");
				this.result.setSuccess(false);
			}

			if ((this.frequency == null) || (this.frequency.equals(""))) {
				this.result.setMsg("添加失败，请输入用药次数！");
				this.result.setSuccess(false);
			}
			if ((this.spec == null) || (this.spec.equals(""))) {
				this.result.setMsg("添加失败，请输入用药次数！");
				this.result.setSuccess(false);
			}
			try {
				int pid = Integer.parseInt(getRequest().getParameter("id"));
				int uid = ((Integer) getSession().get("id")).intValue();
				DocAdvice docAdvice = new DocAdvice();
				docAdvice.setPid(pid);
				docAdvice.setUid(uid);
				docAdvice.setContent(this.content);
				docAdvice.setType(this.type);
				docAdvice.setType2(this.type2);
				docAdvice.setState(this.state);
				docAdvice.setStartTime(this.startTime);
				docAdvice.setDose(this.dose);
				docAdvice.setUsage(this.usage);
				docAdvice.setFrequency(this.frequency);
				docAdvice.setSpec(this.spec);
				this.docAdviceManager.add(docAdvice);
				this.result.setMsg("新增医嘱成功");
				this.result.setSuccess(true);
			} catch (Exception e) {
				this.result.setMsg("添加病人医嘱信息失败");
				this.result.setSuccess(false);
				this.log.error("添加信息失败");
			}
		} else {
			this.result.setMsg("添加病人医嘱信息失败");
			this.result.setSuccess(false);
			this.log.error("添加信息失败");
		}

		return "OK";
	}

	public String addDocAdvicePhone() {
		if (!authorized("id")) {
			this.result = new JsonBase();
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
			this.log.info("获取当前用户失败");
			return "OK";
		}
		this.result = new JsonBase();
		if ((this.content == null) || (this.content.equals(""))) {
			this.result.setMsg("添加失败，请输入医嘱内容！");
			this.result.setSuccess(false);
			return "OK";
		}
		if ((this.type == null) || (this.type.equals(""))) {
			this.result.setMsg("添加失败，请输入医嘱实效！");
			this.result.setSuccess(false);

		} else if ((this.startTime == null) || (this.startTime.equals(""))) {

			this.result.setMsg("添加失败，请输入医嘱开始时间！");
			this.result.setSuccess(false);
		} else if ((this.type2 == null) || (this.type2.equals(""))) {
			this.result.setMsg("添加失败，请输入医嘱类型！");
			this.result.setSuccess(false);
		}
		if (this.type2 != null) {
			try {
				this.type2 = new String(this.type2.getBytes("ISO-8859-1"),
						"utf-8");
			} catch (UnsupportedEncodingException e1) {
				this.type2 = null;
			}
		}
		if (this.type != null) {
			try {
				this.type = new String(this.type.getBytes("ISO-8859-1"),
						"utf-8");
			} catch (UnsupportedEncodingException e1) {
				this.type = null;
			}
		}
		if ((this.type != null) && (this.type.equals("长期医嘱"))) {
			if ((this.endTime == null) || (this.endTime.equals(""))) {
				this.result.setMsg("添加失败，请输入医嘱结束时间！");
				this.result.setSuccess(false);
			} else if ((this.dose == null) || (this.dose.equals(""))) {
				this.result.setMsg("添加失败，请输入剂量！");
				this.result.setSuccess(false);
			} else if ((this.usage == null) || (this.usage.equals(""))) {
				this.result.setMsg("添加失败，请输入用药途径！");
				this.result.setSuccess(false);
			}

			if ((this.frequency == null) || (this.frequency.equals(""))) {
				this.result.setMsg("添加失败，请输入用药次数！");
				this.result.setSuccess(false);
			}
			if ((this.spec == null) || (this.spec.equals(""))) {
				this.result.setMsg("添加失败，请输入用药次数！");
				this.result.setSuccess(false);
			}

			try {
				int pid = Integer.parseInt(getRequest().getParameter("id"));
				int uid = ((Integer) getSession().get("id")).intValue();
				DocAdvice docAdvice = new DocAdvice();
				docAdvice.setPid(pid);
				docAdvice.setUid(uid);
				if ((this.content != null) && (!this.content.equals(""))) {
					try {
						this.content = new String(
								this.content.getBytes("ISO-8859-1"), "utf-8");
						docAdvice.setContent(this.content);
					} catch (UnsupportedEncodingException e1) {
						this.content = null;
					}
				}
				if ((this.dose != null) && (!this.dose.equals(""))) {
					try {
						this.dose = new String(
								this.dose.getBytes("ISO-8859-1"), "utf-8");
						docAdvice.setDose(this.dose);
					} catch (UnsupportedEncodingException e1) {
						this.dose = null;
					}
				}
				if ((this.usage != null) && (!this.usage.equals(""))) {
					try {
						this.usage = new String(
								this.usage.getBytes("ISO-8859-1"), "utf-8");
						docAdvice.setUsage(this.usage);
					} catch (UnsupportedEncodingException e1) {
						this.usage = null;
					}
				}
				if ((this.frequency != null) && (!this.usage.equals(""))) {
					try {
						this.frequency = new String(
								this.frequency.getBytes("ISO-8859-1"), "utf-8");
						docAdvice.setFrequency(this.frequency);
					} catch (UnsupportedEncodingException e1) {
						this.frequency = null;
					}
				}
				if ((this.spec != null) && (!this.spec.equals(""))) {
					try {
						this.spec = new String(
								this.spec.getBytes("ISO-8859-1"), "utf-8");
						docAdvice.setSpec(this.spec);
					} catch (UnsupportedEncodingException e1) {
						this.spec = null;
					}
				}
				docAdvice.setType(this.type);
				docAdvice.setType2(this.type2);
				docAdvice.setState("有效");
				docAdvice.setStartTime(this.startTime + ":00");
				docAdvice.setEndTime(this.endTime + ":00");
				this.docAdviceManager.add(docAdvice);
				this.result.setSuccess(true);
				this.result.setMsg("新增医嘱成功");
				this.log.info("新增医嘱成功，docAdvicepid=" + this.id);
			} catch (Exception e) {
				this.result.setMsg("添加病人医嘱信息失败");
				this.result.setSuccess(false);
				this.log.error("添加信息失败");
				e.printStackTrace();

			}

		} else if ((this.type != null) && (this.type.equals("临时医嘱"))) {
			if ((this.dose == null) || (this.dose.equals(""))) {
				this.result.setMsg("添加失败，请输入剂量！");
				this.result.setSuccess(false);
			} else if ((this.usage == null) || (this.usage.equals(""))) {
				this.result.setMsg("添加失败，请输入用药途径！");
				this.result.setSuccess(false);
			}

			if ((this.frequency == null) || (this.frequency.equals(""))) {
				this.result.setMsg("添加失败，请输入用药次数！");
				this.result.setSuccess(false);
			}
			if ((this.spec == null) || (this.spec.equals(""))) {
				this.result.setMsg("添加失败，请输入用药次数！");
				this.result.setSuccess(false);
			}
			try {
				int pid = Integer.parseInt(getRequest().getParameter("id"));
				int uid = ((Integer) getSession().get("id")).intValue();
				DocAdvice docAdvice = new DocAdvice();
				docAdvice.setPid(pid);
				docAdvice.setUid(uid);
				if ((this.content != null) && (!this.content.equals(""))) {
					try {
						this.content = new String(
								this.content.getBytes("ISO-8859-1"), "utf-8");
						docAdvice.setContent(this.content);
					} catch (UnsupportedEncodingException e1) {
						this.content = null;
					}
				}
				if ((this.dose != null) && (!this.dose.equals(""))) {
					try {
						this.dose = new String(
								this.dose.getBytes("ISO-8859-1"), "utf-8");
						docAdvice.setDose(this.dose);
					} catch (UnsupportedEncodingException e1) {
						this.dose = null;
					}
				}
				if ((this.usage != null) && (!this.usage.equals(""))) {
					try {
						this.usage = new String(
								this.usage.getBytes("ISO-8859-1"), "utf-8");
						docAdvice.setUsage(this.usage);
					} catch (UnsupportedEncodingException e1) {
						this.usage = null;
					}
				}
				if ((this.frequency != null) && (!this.usage.equals(""))) {
					try {
						this.frequency = new String(
								this.frequency.getBytes("ISO-8859-1"), "utf-8");
						docAdvice.setFrequency(this.frequency);
					} catch (UnsupportedEncodingException e1) {
						this.frequency = null;
					}
				}
				if ((this.spec != null) && (!this.spec.equals(""))) {
					try {
						this.spec = new String(
								this.spec.getBytes("ISO-8859-1"), "utf-8");
						docAdvice.setSpec(this.spec);
					} catch (UnsupportedEncodingException e1) {
						this.spec = null;
					}
				}

				docAdvice.setType(this.type);
				docAdvice.setType2(this.type2);
				docAdvice.setState("未执行");
				docAdvice.setStartTime(this.startTime + ":00");
				this.docAdviceManager.add(docAdvice);
				this.result.setMsg("新增医嘱成功");
				this.result.setSuccess(true);
			} catch (Exception e) {
				this.result.setMsg("添加病人医嘱信息失败");
				this.result.setSuccess(false);
				this.log.error("添加信息失败");
				e.printStackTrace();
			}
		} else {
			this.result.setMsg("添加病人医嘱信息失败");
			this.result.setSuccess(false);
			this.log.error("添加信息失败");
		}

		return "OK";
	}

	public String getDocAdviceByPid() {
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
				List<DocAdvice> da1 = this.docAdviceManager
						.getDocAdviceByPidAndNoStop(pid);
				List<DocAdvice> da2 = this.docAdviceManager
						.getDocAdviceByPidAndStop(pid);
				List<DocAdvice> da3 = new ArrayList();
				da1.addAll(da2);
				if (page != -1) {
					for (int i = (page - 1) * 40; (i < da1.size())
							&& (i < page * 40); i++) {
						da3.add((DocAdvice) da1.get(i));
					}
					int total = da1.size();
					this.result.setResults(da3);
					this.result.setSuccess(true);
					this.result.setTotal(total);
				} else {
					int total = da1.size();
					this.result.setResults(da1);
					this.result.setSuccess(true);
					this.result.setTotal(total);
				}
			} catch (Exception e) {
				this.result.setMsg("获取病人医嘱信息失败!");
				this.result.setSuccess(false);
			}
		}
		return "OK";
	}

	public String getDocAdviceByPidPhone() {
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
				List<DocAdvice> doc1 = this.docAdviceManager.getDocAdviceByPid(
						page, pid);
				List<com.cecwj.model.serialize.android.DocAdvicePhone> doc2 = new ArrayList();
				for (DocAdvice docAdvice : doc1) {
					com.cecwj.model.serialize.android.DocAdvicePhone dp = new com.cecwj.model.serialize.android.DocAdvicePhone(
							docAdvice);
					doc2.add(dp);
				}
				int total = doc2.size();
				this.result.setResults(doc2);
				this.result.setSuccess(true);
				this.result.setTotal(total);
			} catch (Exception e) {
				this.result.setMsg("获取病人医嘱信息失败!");
				this.result.setSuccess(false);
			}
		}
		return "OK";
	}

	public String getDocAdviceById() {
		this.result = new JsonBase();
		try {
			DocAdvice docAdvice = this.docAdviceManager
					.getDocAdviceById(this.id.intValue());
			this.result.setResults(docAdvice);
			this.result.setSuccess(true);
		} catch (Exception e) {
			this.result.setMsg("获取医嘱信息失败");
			this.result.setSuccess(false);
			this.log.error(e);
		}
		return "OK";
	}

	public String getDocAdviceByIdPhone() {
		this.result = new JsonBase();
		try {
			DocAdvice docAdvice = this.docAdviceManager
					.getDocAdviceById(this.id.intValue());
			int uid = docAdvice.getUid();
			String docName = this.userManager.getUserById(Integer.valueOf(uid))
					.getUserName();
			com.cecwj.model.serialize.android.DocAdviceAndName doc = new com.cecwj.model.serialize.android.DocAdviceAndName(
					docName, docAdvice);
			this.result.setResults(doc);
			this.result.setSuccess(true);
		} catch (Exception e) {
			this.result.setMsg("获取医嘱信息失败");
			this.result.setSuccess(false);
		}
		return "OK";
	}
	/*
	public String testNewAdvice() {
		this.result = new JsonBase();
		try {
			int sid = ((Integer) getSession().get("sectionid")).intValue();
			List<Patient> ps = this.patientManager.getPatientBySid(-1, sid);
			List<DocAdvice> dos = new ArrayList();
			Iterator localIterator2;
			for (Iterator localIterator1 = ps.iterator(); localIterator1
					.hasNext(); localIterator2.hasNext()) {
				Patient patient = (Patient) localIterator1.next();
				int pid = patient.getId();
				List<DocAdvice> dos1 = this.docAdviceManager.testNewAdvice(pid,
						-1);
				localIterator2 = dos1.iterator();
				// continue;
				DocAdvice docAdvice = (DocAdvice) localIterator2.next();
				dos.add(docAdvice);
			}

			int num = dos.size();
			TestNewAdvice tna = new TestNewAdvice();
			List<TestNewAdvice> ts = new ArrayList();
			ts.add(tna);
			if (num > 0) {
				tna.setIsNewAdvice(true);
			} else {
				tna.setIsNewAdvice(false);
			}
			this.result.setResults(ts);
			this.result.setSuccess(true);
		} catch (Exception e) {
			System.out.println(e);
			this.result.setMsg("获取分区病人信息失败！");
			this.result.setSuccess(false);
			this.log.error(e);
		}

		return "OK";
	}
*/
	
	public String testNewAdvice() {
		this.result = new JsonBase();
		try {
			int sid = ((Integer) getSession().get("sectionid")).intValue();
			List<Patient> ps = this.patientManager.getPatientBySid(-1, sid);
			List<DocAdvice> dos = new ArrayList();
		
			for (Patient patient:ps ) {
			
				int pid = patient.getId();
				List<DocAdvice> dos1 = this.docAdviceManager.testNewAdvice(pid,
						-1);
				for (DocAdvice docadvice:dos1 ) {
					dos.add(docadvice);
					}
			}
			int num = dos.size();
			TestNewAdvice tna = new TestNewAdvice();
			List<TestNewAdvice> ts = new ArrayList();
			ts.add(tna);
			if (num > 0) {
				tna.setIsNewAdvice(true);
			} else {
				tna.setIsNewAdvice(false);
			}
			this.result.setResults(ts);
			this.result.setSuccess(true);
		} catch (Exception e) {
			System.out.println(e);
			this.result.setMsg("获取分区病人信息失败！");
			this.result.setSuccess(false);
			this.log.error(e);
		}

		return "OK";
	}
	public String testNewAdvicePhone() {
		this.result = new JsonBase();
		try {
			int sid =  Integer.valueOf(getRequest().getParameter("sid"));
			List<Patient> ps = this.patientManager.getPatientBySid(-1, sid);
			List<DocAdvice> dos = new ArrayList();
		
			for (Patient patient:ps ) {
			
				int pid = patient.getId();
				List<DocAdvice> dos1 = this.docAdviceManager.testNewAdvice(pid,
						-1);
				for (DocAdvice docadvice:dos1 ) {
					dos.add(docadvice);
					}
			}
			int num = dos.size();
			TestNewAdvice tna = new TestNewAdvice();
			List<TestNewAdvice> ts = new ArrayList();
			ts.add(tna);
			if (num > 0) {
				tna.setIsNewAdvice(true);
			} else {
				tna.setIsNewAdvice(false);
			}
			this.result.setResults(ts);
			this.result.setTotal(num);
			this.result.setSuccess(false);
		} catch (Exception e) {
			System.out.println(e);
			this.result.setMsg("获取分区病人信息失败！");
			this.result.setSuccess(false);
			this.log.error(e);
		}

		return "OK";
	}
	public String getNewDocAdvice() {
		this.result = new JsonBase();
		int page = 1;
		try {
			page = Integer.parseInt(getRequest().getParameter("page"));
		} catch (Exception e) {
			this.log.error(e);
		}
		try {
			int sid = ((Integer) getSession().get("sectionid")).intValue();
			List<Patient> ps = this.patientManager.getPatientBySid(-1, sid);
			List<com.cecwj.model.serialize.DocAndPat> dos = new ArrayList();
		
			for (Patient patient:ps) {				
				int pid = patient.getId();
				String name = patient.getName();
				int bedNum = this.bedManager.getBedByPid(pid).getId();
				List<DocAdvice> dos1 = this.docAdviceManager.testNewAdvice(pid,
						page);
			for (DocAdvice docadvice:dos1 ) {
				com.cecwj.model.serialize.DocAndPat dos2 = new com.cecwj.model.serialize.DocAndPat(
						name, bedNum, docadvice);
				dos.add(dos2);
			}
										
			}

			int total = dos.size();
			this.result.setResults(dos);
			this.result.setSuccess(true);
			this.result.setTotal(total);
		} catch (Exception e) {
			this.result.setMsg("获取病人最新医嘱信息失败！");
			this.result.setSuccess(false);
			this.log.error(e);
		}

		return "OK";
	}

	public String testConDocAdvice() {
		this.result = new JsonBase();
		try {
			int sid = ((Integer) getSession().get("sectionid")).intValue();
			List<Patient> ps = this.patientManager.getPatientBySid(-1, sid);
			List<DocAdvice> dos = new ArrayList();
			Iterator localIterator2;
			for (Patient patient : ps) {
				
				int pid = patient.getId();
				List<DocAdvice> dos1 = this.docAdviceManager.testNewAdvice(pid,
						-1);
					for (DocAdvice docAdvice : dos1) {
					docAdvice.setFlag(1);
					this.docAdviceManager.update(docAdvice);
				}
			}
			this.result.setMsg("确认成功！");
			this.result.setSuccess(true);
		} catch (Exception e) {
			this.result.setMsg("获取分区病人信息失败！");
			this.result.setSuccess(false);
			this.log.error(e);
		}
		return "OK";
	}

	public String testConDocAdvicePhone() {
		this.result = new JsonBase();
		try {
			int sid =  Integer.valueOf(getRequest().getParameter("sid"));
			List<Patient> ps = this.patientManager.getPatientBySid(-1, sid);
			List<DocAdvice> dos = new ArrayList();		
			for (Patient patient : ps) {				
				int pid = patient.getId();
				List<DocAdvice> dos1 = this.docAdviceManager.testNewAdvice(pid,-1);
					for (DocAdvice docAdvice : dos1) {
					docAdvice.setFlag(1);
					this.docAdviceManager.update(docAdvice);
				}
			}
			this.result.setMsg("确认成功！");
			this.result.setSuccess(true);
		} catch (Exception e) {
			this.result.setMsg("获取分区病人信息失败！");
			this.result.setSuccess(false);
			this.log.error(e);
		}
		return "OK";
	}



}
