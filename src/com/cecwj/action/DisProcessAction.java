package com.cecwj.action;

import com.cecwj.model.DisProcess;
import com.cecwj.model.Patient;
import com.cecwj.model.User;
import com.cecwj.model.serialize.JsonBase;
import com.cecwj.model.serialize.android.DisProcessAndPatientPhone;
import com.cecwj.model.serialize.android.DisProcessPhone;
import com.cecwj.service.DisProcessManager;
import com.cecwj.service.PatientManager;
import com.cecwj.service.UserManager;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class DisProcessAction extends BaseAction {
	private DisProcessManager disProcessManager;
	private UserManager userManager;
	private PatientManager patientManager;
	private Integer id;
	private String addTime;
	private String description;
	private String doc;
	private String filename;
	private String fileContentType;
	private ServletContext servletContext;
	private File uploadDoc;
	private String uploadDocFileName;

	public DisProcessManager getDisProcessManager() {
		return this.disProcessManager;
	}

	@Resource
	public void setDisProcessManager(DisProcessManager disProcessManager) {
		this.disProcessManager = disProcessManager;
	}

	public UserManager getUserManager() {
		return this.userManager;
	}

	@Resource
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public PatientManager getPatientManager() {
		return this.patientManager;
	}

	@Resource
	public void setPatientManager(PatientManager patientManager) {
		this.patientManager = patientManager;
	}

	public String getDoc() {
		return this.doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public ServletContext getServletContext() {
		return this.servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public String getSavePath() {
		return getRequest().getRealPath("/DisProessDoc");
	}

	public String getUploadDocFileName() {
		return this.uploadDocFileName;
	}

	public void setUploadDocFileName(String uploadDocFileName) {
		this.uploadDocFileName = uploadDocFileName;
	}

	public File getUploadDoc() {
		return this.uploadDoc;
	}

	public void setUploadDoc(File uploadDoc) {
		this.uploadDoc = uploadDoc;
	}

	public String getFileContentType() {
		return this.fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String editDisProcess() {
		this.result = new JsonBase();
		if ((this.id == null) || (this.id.intValue() < 0)) {
			this.result.setMsg("请选择要更新的病程！");
			this.result.setSuccess(false);
			this.log.error("病程id不正确");
		} else {
			String a = getUploadDocFileName();
			DisProcess disProcess = this.disProcessManager
					.getDisProcessById(this.id.intValue());
			String oldpath = disProcess.getDoc();
			try {
				if ((this.description != null)
						&& (!this.description.equals("")))
					disProcess.setDescription(this.description);
				String filePath = upload(this.uploadDoc, this.id.intValue(),
						disProcess.getUid(), disProcess.getPid());
				if (filePath == null) {
					filePath = oldpath;
				}
				disProcess.setDoc(filePath);
				this.disProcessManager.update(disProcess);
				this.result.setSuccess(true);
				this.result.setMsg("编辑病程成功");
				this.log.info("编辑病程成功，病程id=" + this.id);
			} catch (Exception e) {
				this.result.setMsg("修改病人病程信息失败");
				this.result.setSuccess(false);
				this.log.error("修改病程失败");
				e.printStackTrace();
			}
			getResponse().setContentType("text/doc");
			getResponse().setCharacterEncoding("UTF-8");
		}
		return "OK";
	}

	public String editDisProcessPhone() {
		this.result = new JsonBase();
		if ((this.id == null) || (this.id.intValue() < 0)) {
			this.result.setMsg("请选择要更新的病程！");
			this.result.setSuccess(false);
			this.log.error("病程id不正确");
		} else {
			DisProcess disProcess = this.disProcessManager
					.getDisProcessById(this.id.intValue());
			try {
				if ((this.description != null)
						&& (!this.description.equals(""))) {
					try {
						this.description = new String(
								this.description.getBytes("ISO-8859-1"),
								"utf-8");
					} catch (UnsupportedEncodingException e1) {
						this.description = null;
					}
					disProcess.setDescription(this.description);
				}

				this.disProcessManager.update(disProcess);
				this.result.setSuccess(true);
				this.result.setMsg("编辑病程成功");
				this.log.info("编辑病程成功，病程id=" + this.id);
			} catch (Exception e) {
				this.result.setMsg("修改病人病程信息失败");
				this.result.setSuccess(false);
				this.log.error("修改病程失败");
			}
		}
		return "OK";
	}

	public String deleteDisProcess() {
		this.result = new JsonBase();
		if ((this.id == null) || (this.id.intValue() < 0)) {
			this.result.setMsg("请选择要删除的病程！");
			this.result.setSuccess(false);
			this.log.error("病程id不正确");
		} else {
			try {
				DisProcess disProcess = this.disProcessManager
						.getDisProcessById(this.id.intValue());
				this.disProcessManager.delete(disProcess);
				this.result.setMsg("删除病程成功！");
				this.result.setSuccess(true);
				this.log.info("删除病程" + this.id + "成功");
			} catch (Exception e) {
				this.log.error(e);
				this.log.info("删除失败，exception");
				this.result.setMsg("删除失败，请检查网络连接是否正常！");
				this.result.setSuccess(false);
			}
		}
		return "OK";
	}

	public String addDisProcess() {
		if (!authorized("id")) {
			this.result = new JsonBase();
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
			this.log.info("获取当前用户失败");
			return "OK";
		}
		this.result = new JsonBase();
		if ((this.id == null) || (this.id.intValue() < 0)) {
			this.result.setMsg("请选择病人！");
			this.result.setSuccess(false);
			this.log.error("病人id不正确");
		} else {
			if ((this.description == null) || (this.description.equals(""))) {
				this.result.setMsg("添加失败，请输入病程描述！");
				this.result.setSuccess(false);
			}
			try {
				DisProcess disProcess = new DisProcess();
				disProcess.setPid(this.id.intValue());
				int did = ((Integer) getSession().get("id")).intValue();
				disProcess.setUid(did);
				disProcess.setAddTime(this.addTime + ":00");
				disProcess.setDescription(this.description);
				this.disProcessManager.add(disProcess);
				String filePath = upload(this.uploadDoc, disProcess.getId(),
						did, disProcess.getPid());
				DisProcess disProcess1 = this.disProcessManager
						.getDisProcessById(disProcess.getId());
				disProcess1.setDoc(filePath);
				this.disProcessManager.update(disProcess1);
				this.result.setSuccess(true);
				this.result.setMsg("新增病程成功");
				this.log.info("新增病程成功，disProcesspid=" + disProcess.getId());
			} catch (Exception e) {
				this.result.setMsg("添加病人病程信息失败");
				this.result.setSuccess(false);
				this.log.error("添加信息失败");
			}
			getResponse().setContentType("text/doc");
			getResponse().setCharacterEncoding("UTF-8");
		}
		return "OK";
	}

	public String getDisProcessByPid() {
		/* 269 */this.result = new JsonBase();
		/* 270 */int page = 1;
		try {
			/* 272 */page = Integer.parseInt(getRequest().getParameter("page"));
		} catch (Exception e) {
			/* 274 */this.log.error(e);
		}

		/* 277 */int pid = -1;
		try {
			/* 279 */String s = getRequest().getParameter("id");
			/* 280 */if (s != null) {
				/* 282 */pid = Integer.parseInt(s);
			} else {
				/* 284 */pid = -1;
			}
		} catch (Exception e) {
			/* 287 */this.log.error(e);
		}
		/* 289 */if (pid == -1) {
			/* 290 */this.result.setMsg("病人id不正确！");
			/* 291 */this.result.setSuccess(false);
		} else {
			try {
				/* 296 */List<DisProcess> disPs = this.disProcessManager
						.getDisProcessByPid(page, pid);
				/* 297 */int total = this.disProcessManager
						.getDisProcessTotalByPid(pid);
				/* 298 */this.result.setResults(disPs);
				/* 299 */this.result.setSuccess(true);
				/* 300 */this.result.setTotal(total);
			} catch (Exception e) {
				/* 302 */this.result.setMsg("请检查网络链接是否正常！");
				/* 303 */this.result.setSuccess(false);
				/* 304 */this.log.error(e);
			}
		}
		/* 307 */return "OK";
	}

	public String upload(File f, int num, int did, int pid) {
		if (f != null) {
			int n1 = this.uploadDocFileName.lastIndexOf(".");
			String geshi1 = this.uploadDocFileName.substring(n1);
			String fileName1 = num + "D" + did + "P" + pid + geshi1;
			String str = getSavePath() + "\\" + fileName1;
			File file1 = new File(str);
			if (file1.exists()) {
				file1.delete();
			}
		}
		File dir = new File(getSavePath());
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File file = getUploadDoc();
		if (file != null) {
			int n = this.uploadDocFileName.lastIndexOf(".");
			String geshi = this.uploadDocFileName.substring(n);
			String fileName = num + "D" + did + "P" + pid + geshi;
			file.renameTo(new File(getSavePath() + "/" + fileName));
			String filePath = "../DisProessDoc/" + fileName;
			return filePath;
		}
		return null;
	}

	public String getDisProcessByPidPhone() {
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
			this.result.setSuccess(false);
		} else {
			try {
				List<DisProcess> disPs = this.disProcessManager
						.getDisProcessByPid(page, pid);
				List<DisProcessPhone> dps = new ArrayList();
				for (DisProcess disProcess : disPs) {
					DisProcessPhone d = new DisProcessPhone(disProcess);
					dps.add(d);
				}
				int total = disPs.size();
				this.result.setResults(dps);
				this.result.setSuccess(true);
				this.result.setTotal(total);
			} catch (Exception e) {
				this.result.setMsg("请检查网络链接是否正常！");
				this.result.setSuccess(false);
				this.log.error(e);
			}
		}
		return "OK";
	}

	public String getDisProcessByIdPhone() {
		this.result = new JsonBase();
		try {
			DisProcess disProcess = this.disProcessManager
					.getDisProcessById(this.id.intValue());
			String docName = this.userManager.getUserById(
					Integer.valueOf(disProcess.getUid())).getUserName();
			Patient patient = this.patientManager.getPatientById(disProcess
					.getPid());
			DisProcessAndPatientPhone dd = new DisProcessAndPatientPhone(
					patient, docName, disProcess);
			this.result.setResults(dd);
			this.result.setSuccess(true);
		} catch (Exception e) {
			this.result.setMsg("获取病程失败，请检查网络");
			this.result.setSuccess(false);
			this.log.error(e);
		}

		return "OK";
	}

	public String addDisProcessPhone() {
		if (!authorized("id")) {
			this.result = new JsonBase();
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
			return "OK";
		}
		this.result = new JsonBase();
		if ((this.id == null) || (this.id.intValue() < 0)) {
			this.result.setMsg("请选择病人！");
			this.result.setSuccess(false);
			this.log.error("病人id不正确");
		} else {
			if ((this.description == null) || (this.description.equals(""))) {
				this.result.setMsg("添加失败，请输入病程描述！");
				this.result.setSuccess(false);
			}
			try {
				this.description = new String(
						this.description.getBytes("ISO-8859-1"), "utf-8");
				this.addTime = new String(this.addTime.getBytes("ISO-8859-1"),
						"utf-8");
				try {
					DisProcess disProcess = new DisProcess();
					disProcess.setPid(this.id.intValue());
					int did = ((Integer) getSession().get("id")).intValue();
					disProcess.setUid(did);
					disProcess.setAddTime(this.addTime + ":00");
					disProcess.setDescription(this.description);
					this.disProcessManager.add(disProcess);
					this.result.setSuccess(true);
					this.result.setMsg("新增病程成功");
				} catch (Exception e) {
					this.result.setMsg("添加病人病程信息失败");
					this.result.setSuccess(false);
					this.log.error("添加信息失败");
				}

				return "OK";
			} catch (UnsupportedEncodingException e1) {
				this.result.setMsg("添加病人病程信息失败");
				this.result.setSuccess(false);
				this.log.error("添加信息失败");
			}
		}

		return "OK";
	}
}
