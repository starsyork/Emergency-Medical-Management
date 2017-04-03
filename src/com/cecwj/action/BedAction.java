package com.cecwj.action;

import com.cecwj.model.Bed;
import com.cecwj.model.Bed.Status;
import com.cecwj.model.Patient;
//import com.cecwj.model.Patient.Status;
import com.cecwj.model.User;
import com.cecwj.model.serialize.BedAndPatient;
import com.cecwj.model.serialize.JsonBase;
import com.cecwj.model.serialize.android.BedPhone;
import com.cecwj.service.BedManager;
import com.cecwj.service.PatientManager;
import com.cecwj.service.UserManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BedAction extends BaseAction {
	private BedManager bedManager;
	private UserManager userManager;
	private PatientManager patientManager;

	static Log log = LogFactory.getLog(BedAction.class);// 日志操作,BedAction类的日志可以记录下来
	Integer id;
	int bedNum;
	int status;

	// 构造函数
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getBedNum() {
		return this.bedNum;
	}

	public void setBedNum(int bedNum) {
		this.bedNum = bedNum;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public BedManager getBedManager() {
		return this.bedManager;
	}

	@Resource
	public void setBedManager(BedManager bedManager) {
		this.bedManager = bedManager;
	}

	public PatientManager getPatientManager() {
		return this.patientManager;
	}

	@Resource
	public void setPatientManager(PatientManager patientManager) {
		this.patientManager = patientManager;
	}

	public UserManager getUserManager() {
		return this.userManager;
	}

	@Resource
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	/*
	 * 获取床位信息
	 */
	public String getBeds() {
		this.result = new JsonBase();
		if (!authorized("sectionid")) {// session中是否包含sectionid
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
			log.error("重新登录");
		} else {
			Integer sid = (Integer) getSession().get("sectionid");// 从session中获取sectionid值
			try {
				List<Bed> list = this.bedManager.getBeds(sid.intValue());// 从数据库中根据sid查询出床位信息
				List<BedAndPatient> bedAndPatient = new ArrayList();
				for (Bed bed : list) {
					User user = null;
					if (bed.getPatient() != null)// 床位有病人
						user = this.userManager.getUserById(Integer.valueOf(bed
								.getPatient().getUid()));// 获取yishenID
					BedAndPatient b = new BedAndPatient(bed, user);// 获取床位和病人医生信息
					bedAndPatient.add(b);// 添加到里 bedAndPatient list中
				}
				this.result.setTotal(bedAndPatient.size());
				this.result.setResults(bedAndPatient);
			} catch (Exception e) {
				System.out.println(e);
				this.result.setMsg("获取床位信息失败");
				this.result.setSuccess(false);
				log.error(e);
			}
		}
		return "OK";
	}

	public String getBedsWeb() {
		this.result = new JsonBase();
		if (!authorized("sectionid")) {
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
			log.error("重新登录");
		} else {
			Integer sid = (Integer) getSession().get("sectionid");
			try {
				List<Bed> list = this.bedManager.getBedsWeb(sid.intValue());
				List<BedAndPatient> bedAndPatient = new ArrayList();
				for (Bed bed : list) {
					User user = null;
					if (bed.getPatient() != null)
						user = this.userManager.getUserById(Integer.valueOf(bed
								.getPatient().getUid()));
					BedAndPatient b = new BedAndPatient(bed, user);
					bedAndPatient.add(b);
				}
				this.result.setTotal(bedAndPatient.size());
				this.result.setResults(bedAndPatient);
			} catch (Exception e) {
				System.out.println(e);
				this.result.setMsg("获取床位信息失败");
				this.result.setSuccess(false);
				log.error(e);
			}
		}
		return "OK";
	}

	public String getBedsBySid() {
		this.result = new JsonBase();
		int sid = 0;
		try {
			sid = Integer.valueOf(getRequest().getParameter("id")).intValue();// 获取请求的ID
		} catch (Exception e) {
			this.result.setMsg("请选择分区");
			this.result.setSuccess(false);
			log.error(e);
			return "OK";
		}
		try {
			List<Bed> list = this.bedManager.getBedsWeb(sid);
			List<BedAndPatient> bedAndPatient = new ArrayList();
			for (Bed bed : list) {
				User user = null;
				if (bed.getPatient() != null)
					user = this.userManager.getUserById(Integer.valueOf(bed
							.getPatient().getUid()));
				BedAndPatient b = new BedAndPatient(bed, user);
				bedAndPatient.add(b);
			}
			this.result.setTotal(bedAndPatient.size());
			this.result.setResults(bedAndPatient);
		} catch (Exception e) {
			System.out.println(e);
			this.result.setMsg("获取床位信息失败");
			this.result.setSuccess(false);
			log.error(e);
		}
		return "OK";
	}

	/*
	 * 添加床位
	 */
	public String addBed() {
		this.result = new JsonBase();
		int sid = 0;
		try {
			sid = Integer.valueOf(getRequest().getParameter("id")).intValue();
		} catch (Exception e) {
			this.result.setMsg("请选择分区");
			this.result.setSuccess(false);
			log.error(e);
			return "OK";
		}
		Bed bed = new Bed();
		bed.setSectionid(sid);
		bed.setPatient(null);
		bed.setStatus(Bed.Status.EMPTY);
		bed.setFlag(1);
		try {
			this.bedManager.add(bed);
		} catch (Exception e) {
			this.result.setMsg("床位添加失败");
			this.result.setSuccess(false);
			log.error(e);
		}
		return "OK";
	}

	/*
	 * 删除床位
	 */
	public String deleteBed() {
		this.result = new JsonBase();
		int id = 0;
		try {
			id = Integer.valueOf(getRequest().getParameter("id")).intValue();
		} catch (Exception e) {
			this.result.setMsg("请选择床位");
			this.result.setSuccess(false);
			log.error(e);
			return "OK";
		}
		try {
			Bed bed = this.bedManager.getBedById(id);
			if (bed.getStatus().equals(Bed.Status.INUSE)) {
				this.result.setMsg("床位已经分配，禁止删除");
				this.result.setSuccess(false);
			} else {
				this.bedManager.delete(bed);
				this.result.setMsg("删除成功");
				this.result.setSuccess(true);
			}
		} catch (Exception e) {
			this.result.setMsg("床位删除失败");
			this.result.setSuccess(false);
		}
		return "OK";
	}

	public String getBedsPhone() {
		this.result = new JsonBase();
		if (!authorized("sectionid")) {
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
			log.error("重新登录");
		} else {
			Integer sid = (Integer) getSession().get("sectionid");
			try {
				List<Bed> list = this.bedManager.getBeds(sid.intValue());
				List<BedPhone> listB = new ArrayList();
				for (Bed bed : list) {
					if(bed.getPatient()!=null){
						BedPhone bp = new BedPhone(bed);
						listB.add(bp);
					}
				}
				this.result.setResults(listB);
				this.result.setSuccess(true);
			} catch (Exception e) {
				System.out.println(e);
				this.result.setMsg("获取床位信息失败");
				this.result.setSuccess(false);
				log.error(e);
			}
		}
		return "OK";
	}

	public String getUnAllocBed() {
		this.result = new JsonBase();

		if (!authorized("sectionid")) {
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
			log.error("重新登录");
		} else {
			Integer sid = (Integer) getSession().get("sectionid");
			try {
				List<Bed> list = this.bedManager.getUnAllocBed(sid.intValue());
				this.result.setResults(list);
				this.result.setTotal(list.size());
				this.result.setSuccess(true);
			} catch (Exception e) {
				this.result.setMsg("获取未被分配床位信息失败");
				this.result.setSuccess(false);
				e.printStackTrace();
				log.error(e);
			}
		}
		return "OK";
	}

	public String allocBed() {
		this.result = new JsonBase();
		Integer docId = Integer.valueOf(0);
		try {
			docId = Integer.valueOf(Integer.parseInt(getRequest().getParameter(
					"docId")));
		} catch (Exception e) {
			log.error(e);
		}
		if ((this.id.intValue() < 1) || (this.bedNum < 1)
				|| (docId.intValue() < 1)) {
			this.result.setMsg("床位或病人信息或 医生信息获取失败");
			this.result.setSuccess(false);
		} else {
			try {
				Bed bed = this.bedManager.getBedById(this.bedNum);
				Patient patient = this.patientManager.getPatientById(this.id
						.intValue());
				bed.setStatus(Bed.Status.INUSE);
				bed.setPatient(patient);
				patient.setStatus(Patient.Status.ENTER);
				patient.setUid(docId.intValue());
				this.bedManager.updateBed(bed);
				this.patientManager.updatePatient(patient);
				this.result.setMsg("床位分配成功");
				this.result.setSuccess(true);
			} catch (Exception e) {
				this.result.setMsg("床位分配失败");
				this.result.setSuccess(false);
				log.error(e);
				e.printStackTrace();
			}
		}
		return "OK";
	}

	public String allocBedA() {
		this.result = new JsonBase();
		if (!authorized("sectionid")) {
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
			log.error("重新登录");
		} else {
			Integer docId = Integer.valueOf(0);
			try {
				docId = Integer.valueOf(Integer.parseInt(getRequest()
						.getParameter("docId")));
			} catch (Exception e) {
				log.error(e);
			}
			if ((this.id.intValue() < 1) || (this.bedNum < 1)
					|| (docId.intValue() < 1)) {
				this.result.setMsg("床位或病人信息或 医生信息获取失败");
				this.result.setSuccess(false);
			} else {
				Integer sid = (Integer) getSession().get("sectionid");
				try {
					Bed bed = this.bedManager.getBedById(this.bedNum);
					Patient patient = this.patientManager
							.getPatientById(this.id.intValue());
					bed.setStatus(Bed.Status.INUSE);
					bed.setPatient(patient);
					patient.setStatus(Patient.Status.ENTER);
					patient.setUid(docId.intValue());
					patient.setSid(sid.intValue());
					this.bedManager.updateBed(bed);
					this.patientManager.updatePatient(patient);
					this.result.setMsg("床位分配成功");
					this.result.setSuccess(true);
				} catch (Exception e) {
					this.result.setMsg("床位分配失败，请检查网络");
					this.result.setSuccess(false);
					log.error(e);
					e.printStackTrace();
				}
			}
		}

		return "OK";
	}

	public String modifyStatus() {
		this.result = new JsonBase();
		if ((this.status < 0) || (this.status > 2) || (this.id.intValue() < 1)) {
			this.result.setMsg("参数错误，请重新选择");
			this.result.setSuccess(false);
		} else {
			Bed bed = this.bedManager.getBedById(this.id.intValue());
			Patient patient = bed.getPatient();
			if (this.status == 0) {
				if (bed.getStatus().equals(Bed.Status.EMPTY)) {
					this.result.setMsg("无法修改空床位");
					this.result.setSuccess(false);
					return "OK";
				}
				bed.setStatus(Bed.Status.EMPTY);
				bed.setPatient(null);
				this.bedManager.updateBed(bed);
				patient.setStatus(Patient.Status.NOASSGNED);
				patient.setPflag(0);
				this.patientManager.updatePatient(patient);
				this.result.setMsg("修改成功");
				this.result.setSuccess(true);
			} else {
				if (bed.getStatus().equals(Bed.Status.INUSE)) {
					this.result.setMsg("未对状态做修改");
					this.result.setSuccess(false);
					return "OK";
				}
				this.result.setMsg("无法修改空床位");
				this.result.setSuccess(false);
			}
		}
		return "OK";
	}

	public String modifyBed() {
		this.result = new JsonBase();
		int oldbed = 0;
		try {
			 oldbed = Integer.valueOf(getRequest().getParameter("oldbed"))
					.intValue();
		} catch (Exception e) {
			
			this.result.setMsg("请选择旧床位");
			this.result.setSuccess(false);
			return "OK";
		}
		int newbed = 0;
		try {
			newbed = Integer.valueOf(getRequest().getParameter("newbed"))
					.intValue();
		} catch (Exception e) {
			this.result.setMsg("请选择新床位");
			this.result.setSuccess(false);
			return "OK";
		}
		Bed obed = this.bedManager.getBedById(oldbed);
		Bed nbed = this.bedManager.getBedById(newbed);
		Patient patient = obed.getPatient();
		if (patient == null) {
			this.result.setMsg("旧床位为空，请重新选择");
			this.result.setSuccess(false);
			return "OK";
		}
		try {
			nbed.setPatient(patient);
			obed.setPatient(null);
			obed.setStatus(Bed.Status.EMPTY);
			this.bedManager.updateBed(obed);
			nbed.setStatus(Bed.Status.INUSE);
			this.bedManager.updateBed(nbed);
			this.result.setMsg("换床成功");
			this.result.setSuccess(true);
		} catch (Exception e) {
			this.result.setMsg("换床失败");
			this.result.setSuccess(false);
		}

		return "OK";
	}

}

