package com.cecwj.action;

import com.cecwj.common.TimeFormat;
import com.cecwj.model.Bed;
import com.cecwj.model.Bed.Status;
import com.cecwj.model.Disposal;
import com.cecwj.model.Injury;
import com.cecwj.model.PCondition;
import com.cecwj.model.Patient;
import com.cecwj.model.Psychology;

import com.cecwj.model.Section;
import com.cecwj.model.User;
import com.cecwj.model.serialize.DoctorSta;
import com.cecwj.model.serialize.JsonBase;
import com.cecwj.model.serialize.PatientAndBed;
import com.cecwj.model.serialize.PatientPhone;
import com.cecwj.model.serialize.PatientSta;
import com.cecwj.model.serialize.TestNewPt;
import com.cecwj.model.serialize.TestUndealt;
import com.cecwj.model.serialize.android.PatientInjury;
import com.cecwj.service.BedManager;
import com.cecwj.service.DisposalManager;
import com.cecwj.service.InjuryManager;
import com.cecwj.service.PConditionManager;
import com.cecwj.service.PatientManager;
import com.cecwj.service.PsychologyManager;
import com.cecwj.service.SectionManager;
import com.cecwj.service.UserManager;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PatientAction extends BaseAction {
	private PatientManager patientManager;
	private UserManager userManager;
	private BedManager bedManager;
	private InjuryManager injuryManager;
	private PConditionManager pConditionManager;
	private DisposalManager disposalManager;
	private SectionManager sectionManager;
	private PsychologyManager psychologyManager;
	private File file;
	private String fileFileName;
	private String fileContentType;
	private boolean all;
	private Integer sid;
	private Integer wound_type;
	static Log log = LogFactory.getLog(PatientAction.class);
	Integer id;

	public boolean isAll() {
		return this.all;
	}

	public void setAll(boolean all) {
		this.all = all;
	}

	public File getFile() {
		return this.file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return this.fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
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

	public PatientManager getPatientManager() {
		return this.patientManager;
	}

	@Resource
	public void setPatientManager(PatientManager patientManager) {
		this.patientManager = patientManager;
	}

	public PConditionManager getpConditionManager() {
		return this.pConditionManager;
	}

	@Resource
	public void setpConditionManager(PConditionManager pConditionManager) {
		this.pConditionManager = pConditionManager;
	}

	public DisposalManager getDisposalManager() {
		return this.disposalManager;
	}

	@Resource
	public void setDisposalManager(DisposalManager disposalManager) {
		this.disposalManager = disposalManager;
	}

	public UserManager getUserManager() {
		return this.userManager;
	}

	@Resource
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public BedManager getBedManager() {
		return this.bedManager;
	}

	@Resource
	public void setBedManager(BedManager bedManager) {
		this.bedManager = bedManager;
	}

	public InjuryManager getInjuryManager() {
		return this.injuryManager;
	}

	@Resource
	public void setInjuryManager(InjuryManager injuryManager) {
		this.injuryManager = injuryManager;
	}

	public SectionManager getSectionManager() {
		return this.sectionManager;
	}

	@Resource
	public void setSectionManager(SectionManager sectionManager) {
		this.sectionManager = sectionManager;
	}

	public PsychologyManager getPsychologyManager() {
		return psychologyManager;
	}

	@Resource
	public void setPsychologyManager(PsychologyManager psychologyManager) {
		this.psychologyManager = psychologyManager;
	}

	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getWound_type() {
		return this.wound_type;
	}

	public void setWound_type(Integer woundType) {
		this.wound_type = woundType;
	}

	public String uploadPhoto() {
		File dir = new File(getSavePath());
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File file = getFile();
		file.renameTo(new File(getSavePath() + "/" + this.fileFileName));
		return "OK";
	}

	public String getDoctorPatient() {
		if ((!authorized("id")) || (!authorized("sectionid"))) {
			this.result = new JsonBase();
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
			log.info("获取当前用户失败");
			return "OK";
		}
		this.result = new JsonBase();
		int page = -1;
		try {
			page = Integer.parseInt(getRequest().getParameter("page"));
		} catch (Exception e) {
			log.error(e);
		}
		if (this.all) {
			try {
				Integer sid = (Integer) getSession().get("sectionid");
				List<Patient> patients = this.patientManager.getPatientBySid(
						page, sid.intValue());
				int total = patients.size();
				List<PatientAndBed> bedAndPatient = new ArrayList();
				for (Patient patient : patients) {
					Bed bed = this.bedManager.getBedByPid(patient.getId());
					Integer uid = Integer.valueOf(patient.getUid());
					String docName = this.userManager.getUserById(uid)
							.getUserName();
					PatientAndBed b = new PatientAndBed(bed, docName);
					bedAndPatient.add(b);
				}
				this.result.setResults(bedAndPatient);
				this.result.setTotal(total);
				this.result.setSuccess(true);
			} catch (Exception e) {
				this.result.setMsg("获取病人信息失败");
				this.result.setSuccess(false);
				log.error(e);
				e.printStackTrace();
			}

			return "OK";
		}else{

		try {
			Integer uid = (Integer) getSession().get("id");
			List<Patient> patients = this.patientManager.getPatientByDid(page,
					uid.intValue());
			int total = patients.size();
			List<PatientAndBed> bedAndPatient = new ArrayList();
			for (Patient patient : patients) {
				Bed bed = this.bedManager.getBedByPid(patient.getId());
				String docName = this.userManager.getUserById(uid)
						.getUserName();
				PatientAndBed b = new PatientAndBed(bed, docName);
				bedAndPatient.add(b);
			}
			this.result.setResults(bedAndPatient);
			this.result.setTotal(total);
			this.result.setSuccess(true);
		} catch (Exception e) {
			this.result.setMsg("获取病人信息失败");
			this.result.setSuccess(false);
			log.error(e);
		}
		return "OK";
		}
	}

	public String getDoctorPatientPhone() {
		if ((!authorized("id")) || (!authorized("sectionid"))) {
			this.result = new JsonBase();
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);	
			log.info("获取当前用户失败");
			return "OK";
		}
		this.result = new JsonBase();
		int page = -1;
		try {
			page = Integer.parseInt(getRequest().getParameter("page"));
		} catch (Exception e) {
			log.error(e);
		}
/*	
		if (this.all) {
			try {
				Integer sid = (Integer) getSession().get("sectionid");
				List<Patient> patients = this.patientManager.getPatientBySid(
						page, sid.intValue());
				int total = patients.size();
				List<PatientAndBed> bedAndPatient = new ArrayList();
				for (Patient patient : patients) {
					Bed bed = this.bedManager.getBedByPid(patient.getId());
					Integer uid = Integer.valueOf(patient.getUid());
					String docName = this.userManager.getUserById(uid)
							.getUserName();
					PatientAndBed b = new PatientAndBed(bed, docName);
					bedAndPatient.add(b);
				}
				this.result.setResults(bedAndPatient);
				this.result.setTotal(total);
				this.result.setSuccess(true);
			} catch (Exception e) {
				this.result.setMsg("获取病人信息失败");
				this.result.setSuccess(false);
				log.error(e);
				e.printStackTrace();
			}

			return "OK";
		}
*/	
		try {
			Integer uid = (Integer) getSession().get("id");
			List<Patient> patients = this.patientManager.getPatientByDid(page,
					uid.intValue());
			int total = patients.size();
			List<PatientAndBed> bedAndPatient = new ArrayList();
			for (Patient patient : patients) {
				Bed bed = this.bedManager.getBedByPid(patient.getId());
				String docName = this.userManager.getUserById(uid)
						.getUserName();
				PatientAndBed b = new PatientAndBed(bed, docName);
				bedAndPatient.add(b);
			}
			this.result.setResults(bedAndPatient);
			this.result.setTotal(total);
			this.result.setSuccess(true);
		} catch (Exception e) {
			this.result.setMsg("获取病人信息失败");
			this.result.setSuccess(false);
			log.error(e);
		}

		return "OK";
	}

	/*
	 * public String getDoctorPatientPhone() { if (!authorized("id")) {
	 * this.result = new JsonBase(); this.result.setMsg("statusfailed");
	 * this.result.setSuccess(false); log.info("获取当前用户失败"); return "OK"; }
	 * this.result = new JsonBase(); int page = -1; String roleStr =
	 * (String)getSession().get("roleStr"); if (roleStr.equals("护士")) { try {
	 * Integer sid = (Integer)getSession().get("sectionid"); List<Patient>
	 * patients = this.patientManager.getPatientBySid(page, sid.intValue()); int
	 * total = patients.size(); List<PatientAndBed> bedAndPatient = new
	 * ArrayList(); for (Patient patient : patients) { Bed bed =
	 * this.bedManager.getBedByPid(patient.getId()); PatientAndBed b = new
	 * PatientAndBed(bed); bedAndPatient.add(b); }
	 * this.result.setResults(bedAndPatient); this.result.setTotal(total);
	 * this.result.setSuccess(true); } catch (Exception e) {
	 * this.result.setMsg("获取病人信息失败"); this.result.setSuccess(false);
	 * log.error(e); e.printStackTrace(); }
	 * 
	 * } else { try { Integer uid = (Integer)getSession().get("id");
	 * List<Patient> patients = this.patientManager.getPatientByDid(page,
	 * uid.intValue()); int total = patients.size(); List<PatientPhone>
	 * patientPhone = new ArrayList(); for (Patient patient : patients) { String
	 * docName =
	 * this.userManager.getUserById(Integer.valueOf(patient.getUid())).
	 * getUserName(); Bed bed = this.bedManager.getBedByPid(patient.getId());
	 * PatientPhone b = new PatientPhone(bed, docName); patientPhone.add(b); }
	 * this.result.setResults(patientPhone); this.result.setTotal(total);
	 * this.result.setSuccess(true); } catch (Exception e) {
	 * this.result.setMsg("获取病人信息失败"); this.result.setSuccess(false);
	 * e.printStackTrace(); } } return "OK"; }
	 */

	public String getNursePatient() {
		this.result = new JsonBase();
		int page = 1;
		try {
			page = Integer.parseInt(getRequest().getParameter("page"));
		} catch (Exception e) {
			log.error(e);
		}
		try {
			Integer sid = (Integer) getSession().get("sectionid");
			List<Patient> patients = this.patientManager.getPatientBySid(
					page, sid.intValue());
			int total = patients.size();
			List<PatientAndBed> bedAndPatient = new ArrayList();
			for (Patient patient : patients) {
				Bed bed = this.bedManager.getBedByPid(patient.getId());
				Integer uid = Integer.valueOf(patient.getUid());
				String docName = this.userManager.getUserById(uid)
						.getUserName();
				PatientAndBed b = new PatientAndBed(bed, docName);
				bedAndPatient.add(b);
			}
			this.result.setResults(bedAndPatient);
			this.result.setTotal(total);
			this.result.setSuccess(true);
		} catch (Exception e) {
			this.result.setMsg("获取病人信息失败");
			this.result.setSuccess(false);
			log.error(e);
			e.printStackTrace();
		}

		return "OK";
	}

	public String getSavePath() {
		return getRequest().getRealPath("/photos");
	}

	public String getPatientUndealt() {
		this.result = new JsonBase();

		if (!authorized("sectionid")) {
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
		} else {
			try {
				Integer sid = (Integer) getSession().get("sectionid");
				List<Patient> list = this.patientManager.getPatientUndealt(sid
						.intValue());
				this.result.setResults(list);
				this.result.setTotal(list.size());
				this.result.setSuccess(true);
			} catch (Exception e) {
				this.result.setMsg("获取未分配床位的病人信息失败");
				this.result.setSuccess(false);
				log.error(e);
			}
		}
		return "OK";
	}

	public String getPatientUndealtPhone() {
		this.result = new JsonBase();
		if (!authorized("sectionid")) {
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
		} else {
			try {
				Integer sid = (Integer) getSession().get("sectionid");
				List<Patient> list = this.patientManager.getPatientUndealt(sid
						.intValue());
				List<PatientPhone> listP = new ArrayList();
				for (Patient p : list) {
					PatientPhone pp = new PatientPhone(p);
					listP.add(pp);
				}
				this.result.setResults(listP);
				this.result.setTotal(listP.size());
				this.result.setSuccess(true);
			} catch (Exception e) {
				this.result.setMsg("获取未分配床位的病人信息失败");
				this.result.setSuccess(false);
				log.error(e);
			}
		}
		return "OK";
	}

	public String getPatientUndealtA() {
		this.result = new JsonBase();

		if (!authorized("sectionid")) {
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
		} else {
			try {
				Integer sid = (Integer) getSession().get("sectionid");
				this.patientManager.getPatientUndealtA(sid.intValue(),
						this.result);
			} catch (Exception e) {
				this.result.setMsg("获取信息失败，请检查网络");
				this.result.setSuccess(false);
				log.error(e);
			}
		}
		return "OK";
	}

	public String removePatient() {
		this.result = new JsonBase();
		if ((this.id == null) || (this.id.intValue() < 1)) {
			this.result.setMsg("请选择病人");
			this.result.setSuccess(false);
		} else {
			Patient patient = this.patientManager.getPatientById(this.id
					.intValue());
			if (patient == null) {
				this.result.setMsg("请选择病人");
				this.result.setSuccess(false);
			} else {
				try {
					Bed bed = this.bedManager.getBedByPid(this.id.intValue());
					bed.setStatus(Bed.Status.EMPTY);
					bed.setPatient(null);
					this.bedManager.updateBed(bed);
					patient.setStatus(Patient.Status.OUT);
					patient.setSid(-1);
					this.patientManager.updatePatient(patient);
					this.result.setMsg("操作成功");
					this.result.setSuccess(true);
				} catch (Exception e) {
					this.result.setSuccess(false);
					this.result.setMsg("操作失败，请检查网络");
					log.error(e);
				}
			}
		}
		return "OK";
	}

	public String testUndealt() {
		this.result = new JsonBase();
		if (!authorized("sectionid")) {
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
		} else {
			try {
				Integer sectionid = (Integer) getSession().get("sectionid");
				int b = this.patientManager.testUndealt(sectionid.intValue());
				TestUndealt undealt = new TestUndealt();
				List<TestUndealt> test = new ArrayList();
				undealt.setIsUndealt(b);
				test.add(undealt);
				this.result.setResults(test);
				this.result.setSuccess(true);
			} catch (Exception e) {
				this.result.setSuccess(false);
				this.result.setMsg("查询病人信息失败，请检查网络");
				log.error(e);
			}
		}
		return "OK";
	}
	public String testUndealtPhone() {
		this.result = new JsonBase();	
			try {
				Integer sectionid = Integer.valueOf(getRequest().getParameter("id"));;
				List<Patient> patient = this.patientManager.testUndealtPhone(sectionid.intValue());			
				this.result.setTotal(patient.size());
				this.result.setSuccess(false);
			} catch (Exception e) {
				this.result.setSuccess(false);
				this.result.setMsg("查询病人信息失败，请检查网络");
				log.error(e);
			}
		
		return "OK";
	}

	public String getPatientByIdPhone() {
		this.result = new JsonBase();
		if(this.id<0){
			this.result.setMsg("获取病人ID错误");
			this.result.setSuccess(false);
		}else{
		try {
			Patient patient = this.patientManager.getPatientById(this.id
					.intValue());
			String docName = this.userManager.getUserById(
					Integer.valueOf(patient.getUid())).getUserName();
			Bed bed = this.bedManager.getBedByPid(patient.getId());
			PatientAndBed patientAndBed = new PatientAndBed(bed, docName);
			this.result.setResults(patientAndBed);
			this.result.setSuccess(true);
		} catch (Exception localException) {
			this.result.setMsg("获取病人失败，请检查网络连接");
			this.result.setSuccess(false);
		}
		}
		return "OK";
	}

	public String getPatientPhone() {
		this.result = new JsonBase();
		if (!authorized("sectionid")) {
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
		} else {
			Integer sid = (Integer) getSession().get("sectionid");
			List<Patient> list = this.patientManager.getPatientPhoneBySid(sid
					.intValue());
			List<PatientPhone> listP = new ArrayList();
			int total = 0;
			for (Patient p : list) {
				PatientPhone pp = null;
				if (p.getStatus().equals(Patient.Status.ENTER)) {
					Bed bed = this.bedManager.getBedByPid(p.getId());
					pp = new PatientPhone(bed);
				} else {
					pp = new PatientPhone(p);
					total++;
				}
				listP.add(pp);
			}
			this.result.setResults(listP);
			this.result.setTotal(total);
			this.result.setSuccess(true);
		}
		return "OK";
	}

	public String getPatientDetail() {
		this.result = new JsonBase();
		String ptid = getRequest().getParameter("id");
		if (ptid == null) {
			this.result.setMsg("请指定病人id");
			this.result.setSuccess(false);
		} else {
			this.id = Integer.valueOf(ptid);
			Patient patient = null;
			try {
				patient = this.patientManager
						.getPatientById(this.id.intValue());
			} catch (Exception e) {
				log.error(e);
			}
			this.result.setResults(patient);
			this.result.setSuccess(true);
		}
		return "OK";
	}

	public String getAllUndealt() {
		this.result = new JsonBase();
		try {
			List<Patient> list = this.patientManager.getAllUndealt();
			List<PatientInjury> listP = new ArrayList();
			for (Patient p : list) {
				Injury injury = this.injuryManager.getInjury(p.getId());
				PatientInjury pp = new PatientInjury(p, injury);
				listP.add(pp);
			}
			this.result.setResults(listP);
			this.result.setSuccess(true);
			this.result.setTotal(listP.size());
		} catch (Exception e) {
			this.result.setMsg("获取病人信息失败，请检查网络");
			this.result.setSuccess(false);
			log.error(e);
		}
		return "OK";
	}

	public String addTriage() {
		this.result = new JsonBase();

		String wound_address = null;
		String wound_time = null;
		String name = null;

		String woundadd = getRequest().getParameter("wound_address");
		try {
			wound_address = new String(woundadd.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e1) {
			wound_address = null;
		}
		String name2 = getRequest().getParameter("name");
		try {
			name = new String(name2.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e1) {
			name = null;
		}
		wound_time = getRequest().getParameter("wound_time");
		if ((wound_time == null) || (wound_time.equals(""))
				|| (this.sid == null) || (this.wound_type == null)) {
			this.result.setMsg("参数传递错误");
			this.result.setSuccess(false);
			return "OK";
		}
		Timestamp req_time = new Timestamp(System.currentTimeMillis());
	//	int status = 0;		
		int age;
		try {
			age = Integer.valueOf(getRequest().getParameter("age")).intValue();
		} catch (Exception e) { // int age;
			age = 0;
		}		
		int sex;
		try {
			sex = Integer.valueOf(getRequest().getParameter("sex")).intValue();
		} catch (Exception e) { // int sex;
			sex = 1;
		}
		String rfid;
		try {
			 rfid=getRequest().getParameter("rfid");
		System.out.println(rfid);
			 
		} catch (Exception e) { // int sex;
			rfid =" ";
		}
		Patient patient = new Patient();
		patient.setAge(age);
		patient.setName(name);
		patient.setRequestTime(req_time);
		patient.setSex(sex);
		patient.setSid(this.sid.intValue());
		patient.setRfid(rfid);
		patient.setStatus(Patient.Status.NOASSGNED);
		patient.setWound_address(wound_address);
		Timestamp woundTime = TimeFormat.stringToTimestamp(wound_time);
		patient.setWound_time(woundTime);
		patient.setWound_type(com.cecwj.model.Patient.Wound_type.values()[this.wound_type.intValue()]);
		if (this.file != null) {
			File dir = new File(getSavePath());
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File file = getFile();
			file.renameTo(new File(getSavePath() + "/" + this.fileFileName));
			patient.setPhoto(file.getAbsolutePath());
		}
		try {
			this.patientManager.addPatient(patient);
		} catch (Exception e) {
			log.error(e);
			System.out.println(e);
			this.result.setMsg("病人信息添加失败");
			this.result.setSuccess(false);
			return "OK";
		}

		PCondition pc = new PCondition();
		try {
			pc.setDiastolic(Float.valueOf(
					getRequest().getParameter("diastolic")).floatValue());
		} catch (Exception e) {
			log.info("no diastolic");
		}
		try {
			pc.setSystolic(Float.valueOf(getRequest().getParameter("systolic"))
					.floatValue());
		} catch (Exception e) {
			log.info("no systolic");
		}
		try {
			pc.setPulse(Float.valueOf(getRequest().getParameter("pulse"))
					.floatValue());
		} catch (Exception e) {
			log.info("no pulse");
		}
		try {
			pc.setTemperature(Float.valueOf(
					getRequest().getParameter("temperature")).floatValue());
		} catch (Exception e) {
			log.info("no temperature");
		}
		try {
			pc.setBreath(Float.valueOf(getRequest().getParameter("breath"))
					.floatValue());
		} catch (Exception e) {
			log.info("no breath");
		}

		pc.setPid(patient.getId());
		pc.setComment("检伤分类");
		try {
			this.pConditionManager.addCondition(pc);
		} catch (Exception e) {
			log.error(e);
			this.result.setMsg("病情信息添加失败");
			this.result.setSuccess(false);
			return "OK";
		}

		String disposal = getRequest().getParameter("disposal");
		if ((disposal != null) && (!disposal.equals(""))) {
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
				dis.setPid(patient.getId());
				dis.setType("检伤分类");
				try {
					this.disposalManager.addDisposal(dis);
				} catch (Exception e) {
					log.error(e);
					this.result.setMsg("处置信息添加失败");
					this.result.setSuccess(false);
					return "OK";
				}
			}
		}

		String psy = getRequest().getParameter("psy");
		if ((psy != null) && (!psy.equals(""))) {
			try {
				Psychology psychology = new Psychology();
				// Patient patient2 =
				// this.patientManager.getPatientById(this.pid);
				psychology.setPid(patient.getId());
				psychology.setPtname(patient.getName());
				String type = new String(psy.getBytes("ISO-8859-1"), "utf-8");
				psychology.setPsychologyStatus(type);

				this.psychologyManager.addPsychology(psychology);
			} catch (Exception e) {
				log.error(e);
				this.result.setMsg("心理状态添加失败");
				this.result.setSuccess(false);
				return "OK";
			}
		}
		int type = 0;
		try {
			type = Integer.valueOf(getRequest().getParameter("type"))
					.intValue();
		} catch (Exception e) {
			log.error(e);
		}
		Injury in = new Injury();
		in.setPid(patient.getId());
		in.setType(type);
		try {
			this.injuryManager.addInjury(in);
		} catch (Exception e) {
			log.error(e);
			this.result.setMsg("伤情判断信息添加失败");
			this.result.setSuccess(false);
			return "OK";
		}

		this.result.setSuccess(true);
		this.result.setMsg("信息添加成功");
		this.result.setResults(patient);
		return "OK";

	}

	public String getPatientStatis() {
		this.result = new JsonBase();
		List<PatientSta> list = new ArrayList();
		List<Section> ls = this.sectionManager.getAllSection();
		try {
			for (Section s : ls) {
				PatientSta t = this.patientManager.getPatientStatis(s.getId());
				t.setSecName(s.getSecName());
				list.add(t);
			}
			this.result.setResults(list);
			this.result.setTotal(list.size());
			this.result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			this.result.setMsg("获取统计信息失败");
			this.result.setSuccess(false);
			log.error(e);
		}
		return "OK";
	}

	public String getDocterStatis() {
		this.result = new JsonBase();
		List<DoctorSta> list = new ArrayList();
		List<User> userList = this.userManager.getAllDoctors();
		try {
			for (User u : userList) {
				DoctorSta doctorSta = this.patientManager.getDoctorStatis(u
						.getId());
				doctorSta.setName(u.getUserName());
				doctorSta.setSecName(u.getSection().getSecName());
				list.add(doctorSta);
			}
			this.result.setResults(list);
			this.result.setSuccess(true);
			this.result.setTotal(list.size());
		} catch (Exception e) {
			this.result.setMsg("获取医生统计信息失败");
			this.result.setSuccess(false);
			log.error(e);
		}
		return "OK";
	}

	public String testDoctorNewPatient() {
		if ((!authorized("id")) || (!authorized("sectionid"))) {
			this.result = new JsonBase();
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
			log.info("获取当前用户失败");
			return "OK";
		}
		this.result = new JsonBase();
		try {
			Integer uid = (Integer) getSession().get("id");
			List<Patient> patients = this.patientManager
					.getPatientByDidAndFlag(uid.intValue());
			int total = patients.size();
			List<TestNewPt> t = new ArrayList();
			TestNewPt tn = new TestNewPt();
			if (total > 0) {
				tn.setIsNewPt(true);
			} else {
				tn.setIsNewPt(false);
			}
			t.add(tn);
			this.result.setResults(t);
			this.result.setTotal(total);
			this.result.setSuccess(true);
		} catch (Exception e) {
			this.result.setMsg("获取病人信息失败");
			this.result.setSuccess(false);
			log.error(e);
		}

		return "OK";
	}

	public String getDoctorNewPatient() {
		if ((!authorized("id")) || (!authorized("sectionid"))) {
			this.result = new JsonBase();
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
			log.info("获取当前用户失败");
			return "OK";
		}
		this.result = new JsonBase();
		try {
			Integer uid = (Integer) getSession().get("id");
			List<Patient> patients = this.patientManager
					.getPatientByDidAndFlag(uid.intValue());
			int total = patients.size();
			List<PatientAndBed> bedAndPatient = new ArrayList();
			for (Patient patient : patients) {
				Bed bed = this.bedManager.getBedByPid(patient.getId());
				String docName = this.userManager.getUserById(uid)
						.getUserName();
				PatientAndBed b = new PatientAndBed(bed, docName);
				bedAndPatient.add(b);
			}
			this.result.setMsg("NewPatient");
			this.result.setResults(bedAndPatient);
			this.result.setTotal(total);
			this.result.setSuccess(true);
		} catch (Exception e) {
			this.result.setMsg("获取病人信息失败");
			this.result.setSuccess(false);
			log.error(e);
		}

		return "OK";
	}

	
	public String getDoctorNewPatientPhone() {
/*		if ((!authorized("id")) || (!authorized("sectionid"))) {
			Integer uid = (Integer) getSession().get("id");
			Integer sectionid = (Integer) getSession().get("sectionid");
			System.out.println(uid);
			System.out.println(sectionid);
			this.result = new JsonBase();
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
			log.info("获取当前用户失败");
			return "OK";
		}*/
		this.result = new JsonBase();
		try {
			
			//Integer uid = (Integer) getSession().get("id");
			Integer uid = Integer.valueOf(getRequest().getParameter("id"));
			List<Patient> patients = this.patientManager
					.getPatientByDidAndFlag(uid.intValue());
			int total = patients.size();
/*			
			List<PatientAndBed> bedAndPatient = new ArrayList();
			for (Patient patient : patients) {
				Bed bed = this.bedManager.getBedByPid(patient.getId());
				String docName = this.userManager.getUserById(uid)
						.getUserName();
				PatientAndBed b = new PatientAndBed(bed, docName);
				bedAndPatient.add(b);
			}
			*/
		//  this.result.setMsg("NewPatient");
		//  this.result.setResults(bedAndPatient);
			this.result.setTotal(total);
			this.result.setSuccess(false);
		} catch (Exception e) {
			this.result.setMsg("获取病人信息失败");
			this.result.setSuccess(false);
			log.error(e);
		}

		return "OK";
	}
	
	public String testConDoctorNewPatient() {
		if ((!authorized("id")) || (!authorized("sectionid"))) {
			this.result = new JsonBase();
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
			log.info("获取当前用户失败");
			return "OK";
		}
		this.result = new JsonBase();
		try {
			Integer uid = (Integer) getSession().get("id");
			List<Patient> patients = this.patientManager
					.getPatientByDidAndFlag(uid.intValue());
			for (Patient patient : patients) {
				patient.setPflag(1);
				this.patientManager.updatePatient(patient);
			}
			this.result.setSuccess(true);
		} catch (Exception e) {
			this.result.setMsg("获取病人信息失败");
			this.result.setSuccess(false);
			log.error(e);
		}

		return "OK";
	}
	
	public String ConDoctorNewPt() {
		
		this.result = new JsonBase();
		try {
			Integer uid = Integer.valueOf(getRequest().getParameter("id"));
			List<Patient> patients = this.patientManager
					.getPatientByDidAndFlag(uid.intValue());
			for (Patient patient : patients) {
				patient.setPflag(1);
				this.patientManager.updatePatient(patient);
			}
			this.result.setMsg("确认成功");
			this.result.setSuccess(true);
		} catch (Exception e) {
			this.result.setMsg("获取病人信息失败");
			this.result.setSuccess(false);
			log.error(e);
		}

		return "OK";
	}
}

