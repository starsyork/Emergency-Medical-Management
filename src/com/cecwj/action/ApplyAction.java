package com.cecwj.action;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cecwj.common.PinyinTool;
import com.cecwj.model.Apply;
import com.cecwj.model.Bed;
import com.cecwj.model.DocAdvice;
import com.cecwj.model.Doctor;
import com.cecwj.model.Edetail;
import com.cecwj.model.Hospital;
import com.cecwj.model.Inspect;
import com.cecwj.model.Operation;
import com.cecwj.model.Patient;
import com.cecwj.model.TermAll;
import com.cecwj.model.Transport;
import com.cecwj.model.User;
import com.cecwj.model.serialize.ApplyAndPatient;
import com.cecwj.model.serialize.InspectAndDoctor;
import com.cecwj.model.serialize.JsonBase;
import com.cecwj.model.serialize.OperationAndDoctor;
import com.cecwj.model.serialize.PatientAndBed;
import com.cecwj.model.serialize.TestNewAdvice;
import com.cecwj.model.serialize.TestNewPt;
import com.cecwj.model.serialize.TestUndealt;
import com.cecwj.model.serialize.TransportAndDoctor;
import com.cecwj.model.serialize.android.ApplyAndPatientAndContent;
import com.cecwj.service.ApplyManager;
import com.cecwj.service.BedManager;
import com.cecwj.service.DictManager;
import com.cecwj.service.DoctorManager;
import com.cecwj.service.EdetailManager;
import com.cecwj.service.HospitalManager;
import com.cecwj.service.InspectManager;
import com.cecwj.service.OperationManager;
import com.cecwj.service.PatientManager;
import com.cecwj.service.TransportManager;
import com.cecwj.service.UserManager;

public class ApplyAction extends BaseAction {
	static Log log = LogFactory.getLog(ApplyAction.class);
	private int id;
	private int pid;
	private int did;
	private String time;
	private String type2;
	private String status;
	private String content;
	private String illustration;
	private String dname;
	private String ptname;
	private int  tid;
	private int  hid;
	private String tname;
	private boolean all;
	private BedManager bedManager;
	private UserManager userManager;
	private ApplyManager applyManager;
	private PatientManager patientManager;
	private DoctorManager doctorManager;
	private DictManager dictManager;
	private EdetailManager edetailManager;
	private InspectManager inspectManager;
	private TransportManager transportManager;
	private OperationManager operationManager;
	private HospitalManager hospitalManager;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}


	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getPtname() {
		return ptname;
	}

	public void setPtname(String ptname) {
		this.ptname = ptname;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIllustration() {
		return illustration;
	}

	public void setIllustration(String illustration) {
		this.illustration = illustration;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	
	
	public boolean isAll() {
		return all;
	}

	public void setAll(boolean all) {
		this.all = all;
	}

	public BedManager getBedManager() {
		return bedManager;
	}

	public void setBedManager(BedManager bedManager) {
		this.bedManager = bedManager;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public ApplyManager getApplyManager() {
		return applyManager;
	}

	
	public void setApplyManager(ApplyManager applyManager) {
		this.applyManager = applyManager;
	}

	public PatientManager getPatientManager() {
		return patientManager;
	}

	public void setPatientManager(PatientManager patientManager) {
		this.patientManager = patientManager;
	}

	public DoctorManager getDoctorManager() {
		return doctorManager;
	}

	public void setDoctorManager(DoctorManager doctorManager) {
		this.doctorManager = doctorManager;
	}

	public DictManager getDictManager() {
		return dictManager;
	}

	public void setDictManager(DictManager dictManager) {
		this.dictManager = dictManager;
	}

	public EdetailManager getEdetailManager() {
		return edetailManager;
	}

	public void setEdetailManager(EdetailManager edetailManager) {
		this.edetailManager = edetailManager;
	}

	public InspectManager getInspectManager() {
		return inspectManager;
	}

	public void setInspectManager(InspectManager inspectManager) {
		this.inspectManager = inspectManager;
	}

	public TransportManager getTransportManager() {
		return transportManager;
	}

	public void setTransportManager(TransportManager transportManager) {
		this.transportManager = transportManager;
	}

	public OperationManager getOperationManager() {
		return operationManager;
	}

	public void setOperationManager(OperationManager operationManager) {
		this.operationManager = operationManager;
	}



	
	
	
						
public HospitalManager getHospitalManager() {
		return hospitalManager;
	}

	public void setHospitalManager(HospitalManager hospitalManager) {
		this.hospitalManager = hospitalManager;
	}

	//	添加申请
	public String AddApply() {
		this.result = new JsonBase();
		int Did = (Integer) getSession().get("id");
		if (this.pid == 0) {
			this.result.setMsg("增加失败，请选择病人！");
			this.result.setSuccess(false);
		} else if (this.content == null) {
			this.result.setMsg("增加失败，请输入项目名称！");
			this.result.setSuccess(false);
		} else {
			try {
				String code = "";
				if(this.type2.equals("转运")){
				List<Apply>	transport1 = this.applyManager.getApplyByPid(this.pid, Apply.TypeA.TRANSPORT);
				if(transport1.size()>0){
					this.result.setMsg("转运信息已存在，请直接修改");
					this.result.setSuccess(false);
				}else{	
					   Apply applyTran = new Apply();
					   applyTran.setDid(Did);
					   applyTran.setPid(this.pid);
						Date time2 = new Date();
						Timestamp nousedate = new Timestamp(time2.getTime());
						applyTran.setTime(nousedate);
						applyTran.setStatus("未领取");
						applyTran.setType(this.type2);
						this.applyManager.add(applyTran);
						
					
						//	Hospital hospital = this.hospitalManager.getHospitalById(this.hid);
							Transport transport = new Transport();
							transport.setPid(this.pid);				
							transport.setDid(Did);
							transport.setContent(this.content);
							transport.setApplyId(applyTran.getId());
							transport.setStatus("等待转运");
							if (this.illustration != null&& !this.illustration.equals(""))
								transport.setIllustration(this.illustration);
							this.transportManager.add(transport);
							this.result.setResults(applyTran);
							this.result.setMsg("增加成功！");
							this.result.setSuccess(true);
						}
						}
				else{
				Apply applyold = this.applyManager.getApplyA(this.pid);

//				if (!applyold.getClass().equals("转运")) {
					if (applyold != null&&applyold.getStatus().equals(this.type2)) {
						applyold.setStatus("未领取");
						Date time2 = new Date();
						Timestamp nousedate = new Timestamp(time2.getTime());
						// System.out.println(time2);
						applyold.setTime(nousedate);
						Date time1 = new Date(applyold.getTime().getTime());
						// System.out.println(time1);
						long l = time2.getTime() - time1.getTime();
						long day = l / (24 * 60 * 60 * 1000);
						if (day > 1 || !this.type2.equals(applyold.getType())) {
							applyold.setType(this.type2);
							this.applyManager.add(applyold);
						} else {
							this.applyManager.update(applyold);
						}
					} else {
						Apply applynew = new Apply();
						applynew.setDid(Did);
						applynew.setPid(this.pid);
						Date time2 = new Date();
						Timestamp nousedate = new Timestamp(time2.getTime());
						applynew.setTime(nousedate);
						applynew.setStatus("未领取");
						applynew.setType(this.type2);
						this.applyManager.add(applynew);
					}
					Apply apply = this.applyManager.getApplyA(this.pid);
					if (this.type2.equals("检验")) {
						Edetail edetail = new Edetail();
						PinyinTool tool = new PinyinTool();
						try {
							code = tool.toPinYin(this.content, "",
									PinyinTool.Type.FIRSTUPPER);
						} catch (BadHanyuPinyinOutputFormatCombination e) {
							e.printStackTrace();
						}
						edetail.setItem(code);
						edetail.setItemStr(this.content);
						edetail.setDid(Did);
						edetail.setMid(this.pid);
						edetail.setApplyId(apply.getId());
						edetail.setStatus("未领取");
						this.edetailManager.add(edetail);
						this.result.setResults(apply);
						this.result.setSuccess(true);
					} else if (this.type2.equals("检查")) {
						Inspect inspect = new Inspect();
						inspect.setPid(this.pid);
						inspect.setDid(Did);
						inspect.setContent(this.content);
						inspect.setApplyId(apply.getId());
						inspect.setStatus("未领取");
						if (this.illustration != null
								&& !this.illustration.equals(""))
							inspect.setIllustration(this.illustration);
						this.inspectManager.add(inspect);
						this.result.setResults(apply);
						this.result.setSuccess(true);
					} else if (this.type2.equals("手术")) {
						Operation operation = new Operation();
						operation.setPid(this.pid);
						operation.setDid(Did);
						operation.setContent(this.content);
						operation.setApplyId(apply.getId());
						operation.setStatus("未领取");
						if (this.illustration != null
								&& this.illustration.equals(""))
							operation.setIllustration(this.illustration);
						this.operationManager.add(operation);
						this.result.setResults(apply);
						this.result.setSuccess(true);
					} 
				}	
				
/*				} else {
					this.result.setMsg("转运信息已存在！");
					this.result.setSuccess(true);
				}*/
			} catch (Exception e) {
				System.out.println(e);
				this.result.setMsg("增加失败！");
				this.result.setSuccess(false);
			}
		}
		return OK;
	}


	public String AddApplyPhone() {
		this.result = new JsonBase();
		int Did = (Integer) getSession().get("id");
		if (this.pid == 0) {
			this.result.setMsg("增加失败，请选择病人！");
			this.result.setSuccess(false);
		} else if (this.content == null) {
			this.result.setMsg("增加失败，请输入项目名称！");
			this.result.setSuccess(false);
		} else {
			if ((this.type2 != null) && (!this.type2.equals(""))) {
				try {
					this.type2 = new String(
							this.type2.getBytes("ISO-8859-1"), "utf-8");
				} catch (UnsupportedEncodingException e1) {
					this.type2 = null;
				}
			
			}
			
			if ((this.content != null) && (!this.content.equals(""))) {
				try {
					this.content = new String(
							this.content.getBytes("ISO-8859-1"), "utf-8");
				} catch (UnsupportedEncodingException e1) {
					this.content = null;
				}
			}
			if ((this.illustration != null) && (!this.illustration.equals(""))) {
				try {
					this.illustration = new String(
							this.illustration.getBytes("ISO-8859-1"), "utf-8");
				} catch (UnsupportedEncodingException e1) {
					this.illustration = null;
				}
			}
			
			try {
				String code = "";
				if(this.type2.equals("转运")){
				List<Apply>	transport1 = this.applyManager.getApplyByPid(this.pid, Apply.TypeA.TRANSPORT);
				if(transport1.size()>0){
					this.result.setMsg("转运信息已存在，请直接修改");
					this.result.setSuccess(false);
				}else{	
					   Apply applyTran = new Apply();
					   applyTran.setDid(Did);
					   applyTran.setPid(this.pid);
						Date time2 = new Date();
						Timestamp nousedate = new Timestamp(time2.getTime());
						applyTran.setTime(nousedate);
						applyTran.setStatus("未领取");
						applyTran.setType(this.type2);
						this.applyManager.add(applyTran);
						
					
						//	Hospital hospital = this.hospitalManager.getHospitalById(this.hid);
							Transport transport = new Transport();
							transport.setPid(this.pid);				
							transport.setDid(Did);
							transport.setContent(this.content);
							transport.setApplyId(applyTran.getId());
							transport.setStatus("等待转运");
							if (this.illustration != null&& !this.illustration.equals(""))
								transport.setIllustration(this.illustration);
							this.transportManager.add(transport);
							this.result.setResults(applyTran);
							this.result.setMsg("增加成功！");
							this.result.setSuccess(true);
						}
						}
				else{
				Apply applyold = this.applyManager.getApplyA(this.pid);

//				if (!applyold.getClass().equals("转运")) {
					if (applyold != null&&applyold.getStatus().equals(this.type2)) {
						applyold.setStatus("未领取");
						Date time2 = new Date();
						Timestamp nousedate = new Timestamp(time2.getTime());
						// System.out.println(time2);
						applyold.setTime(nousedate);
						Date time1 = new Date(applyold.getTime().getTime());
						// System.out.println(time1);
						long l = time2.getTime() - time1.getTime();
						long day = l / (24 * 60 * 60 * 1000);
						if (day > 1 || !this.type2.equals(applyold.getType())) {
							applyold.setType(this.type2);
							this.applyManager.add(applyold);
						} else {
							this.applyManager.update(applyold);
						}
					} else {
						Apply applynew = new Apply();
						applynew.setDid(Did);
						applynew.setPid(this.pid);
						Date time2 = new Date();
						Timestamp nousedate = new Timestamp(time2.getTime());
						applynew.setTime(nousedate);
						applynew.setStatus("未领取");
						applynew.setType(this.type2);
						this.applyManager.add(applynew);
					}
					Apply apply = this.applyManager.getApplyA(this.pid);
					if (this.type2.equals("检验")) {
						Edetail edetail = new Edetail();
						PinyinTool tool = new PinyinTool();
						try {
							code = tool.toPinYin(this.content, "",
									PinyinTool.Type.FIRSTUPPER);
						} catch (BadHanyuPinyinOutputFormatCombination e) {
							e.printStackTrace();
						}
						edetail.setItem(code);
						edetail.setItemStr(this.content);
						edetail.setDid(Did);
						edetail.setMid(this.pid);
						edetail.setApplyId(apply.getId());
						edetail.setStatus("未领取");
						this.edetailManager.add(edetail);
						this.result.setResults(apply);
						this.result.setSuccess(true);
					} else if (this.type2.equals("检查")) {
						Inspect inspect = new Inspect();
						inspect.setPid(this.pid);
						inspect.setDid(Did);
						inspect.setContent(this.content);
						inspect.setApplyId(apply.getId());
						inspect.setStatus("未领取");
						if (this.illustration != null
								&& !this.illustration.equals(""))
							inspect.setIllustration(this.illustration);
						this.inspectManager.add(inspect);
						this.result.setResults(apply);
						this.result.setSuccess(true);
					} else if (this.type2.equals("手术")) {
						Operation operation = new Operation();
						operation.setPid(this.pid);
						operation.setDid(Did);
						operation.setContent(this.content);
						operation.setApplyId(apply.getId());
						operation.setStatus("未领取");
						if (this.illustration != null
								&& this.illustration.equals(""))
							operation.setIllustration(this.illustration);
						this.operationManager.add(operation);
						this.result.setResults(apply);
						this.result.setSuccess(true);
					} 
				}	
				
/*				} else {
					this.result.setMsg("转运信息已存在！");
					this.result.setSuccess(true);
				}*/
			} catch (Exception e) {
				System.out.println(e);
				this.result.setMsg("增加失败！");
				this.result.setSuccess(false);
			}
		}
			
		return OK;
	}
	
	// 获取申请的病人列表
	public String getApplyByStatus() {
		this.result = new JsonBase();
		if(!this.all){						
				try {
					List<Apply> list = this.applyManager.getApplyByStatus(Apply.TypeB.executing,
					Apply.TypeA.INSPECTION, Apply.TypeA.EXAMINE);
				List<ApplyAndPatient> applyAndPatient = new ArrayList();
				for (Apply apply : list) {
					if (apply.getPid() != 0) {
						Patient patient = this.patientManager.getPatientById(apply
								.getPid());
						User doctor = this.userManager.getUserById(apply
								.getDid());
						Bed bed = this.bedManager.getBedByPid(apply.getPid());
						ApplyAndPatient a = new ApplyAndPatient(apply, patient,
								doctor,bed);
						applyAndPatient.add(a);
					}
				}
				this.result.setTotal(applyAndPatient.size());
				this.result.setResults(applyAndPatient);
				this.result.setSuccess(true);
				}catch (Exception e) {
					System.out.println(e);
					this.result.setMsg("请检查网络链接是否正常！");
					this.result.setSuccess(false);
				}
		}	else{
			try {
				List<Apply> list = this.applyManager.getApplyByStatus(Apply.TypeB.executing,
				Apply.TypeA.INSPECTION, Apply.TypeA.EXAMINE);
				List<Apply> list2 = this.applyManager.getApplyByStatus(Apply.TypeB.finished,
				Apply.TypeA.INSPECTION, Apply.TypeA.EXAMINE);
			List<ApplyAndPatient> applyAndPatient = new ArrayList();		
			if(list.size()>0){
			for (Apply apply : list) {
				if (apply.getPid() != 0) {
					Patient patient = this.patientManager.getPatientById(apply
							.getPid());
					User doctor = this.userManager.getUserById(apply
							.getDid());
					Bed bed = this.bedManager.getBedByPid(apply.getPid());
					ApplyAndPatient a = new ApplyAndPatient(apply, patient,
							doctor,bed);
					applyAndPatient.add(a);
				}
			}
			}
			if(list2.size()>0){
				for (Apply apply : list2) {
					if (apply.getPid() != 0) {
						Patient patient = this.patientManager.getPatientById(apply
								.getPid());
						User doctor = this.userManager.getUserById(apply
								.getDid());
						Bed bed = this.bedManager.getBedByPid(apply.getPid());
						ApplyAndPatient a = new ApplyAndPatient(apply, patient,
								doctor,bed);
						applyAndPatient.add(a);
					}
				}
			}
			this.result.setTotal(applyAndPatient.size());
			this.result.setResults(applyAndPatient);
			this.result.setSuccess(true);
			}catch (Exception e) {
				System.out.println(e);
				this.result.setMsg("请检查网络链接是否正常！");
				this.result.setSuccess(false);
			}
		}
	
			
			
		
		
		return OK;
	}
	// 获取未化验申请内容
	public String getExamApply() {
	if(!this.all){	
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
			List<Apply> list = this.applyManager.getApplyByPid(this.id,Apply.TypeA.EXAMINE);
			List<Edetail> list1 = new ArrayList();
			for (Apply apply : list) {
				List<Edetail> edetails = this.edetailManager.getEdetailBypid(
						apply.getPid(), apply.getId());
				for (Edetail edetail : edetails) {
					list1.add(edetail);
				}
				
			}
			List<Edetail> da3 = new ArrayList();
			if (page != -1) {					
					for (int i = (page - 1) * limit; (i < list1.size())
							&& (i < page * limit); i++) {
						da3.add((Edetail) list1.get(i));
					}
					int total = list1.size();
					this.result.setResults(da3);
					this.result.setSuccess(true);
					this.result.setTotal(total);					
			} else {
				int total = list1.size();
				this.result.setResults(list1);
				this.result.setSuccess(true);
				this.result.setTotal(total);
			}
		} catch (Exception e) {
			System.out.println(e);
			this.result.setMsg("请检查网络链接是否正常！");
			this.result.setSuccess(false);
		}	
		}else{
			getAllExamApply();
		}	
	return OK;
	}
	// 获取所有化验申请内容
	public String getAllExamApply() {
		this.result = new JsonBase();
		int page = -1;
		int limit = 40;
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
			List<Apply> list = this.applyManager.getAllApplyByPid(this.id,
					Apply.TypeA.EXAMINE);
			List<Edetail> list1 = new ArrayList();
			for (Apply apply : list) {
				List<Edetail> edetails = this.edetailManager.getAllEdetailBypid(
						apply.getPid(), apply.getId());
				System.out.println(apply.getTime());
				for (Edetail edetail : edetails) {
					list1.add(edetail);
				}
			}
			List<Edetail> da3 = new ArrayList();
			if (page != -1) {					
					for (int i = (page - 1) * limit; (i < list1.size())
							&& (i < page * limit); i++) {
						da3.add((Edetail) list1.get(i));
					}
					int total = list1.size();
					this.result.setResults(da3);
					this.result.setSuccess(true);
					this.result.setTotal(total);					
			} else {
				int total = list1.size();
				this.result.setResults(list1);
				this.result.setSuccess(true);
				this.result.setTotal(total);
			}
		} catch (Exception e) {
			System.out.println(e);
			this.result.setMsg("请检查网络链接是否正常！");
			this.result.setSuccess(false);
		}
		return OK;
	}
	
	// 获取新化验病人列表
	public String ExamNewPt(){
		this.result = new JsonBase();
		try {	
			List<Apply> applys = this.applyManager.getApplyByStatus(Apply.TypeB.unexecuted,
				Apply.TypeA.INSPECTION, Apply.TypeA.EXAMINE);
		List<PatientAndBed> bedAndPatient = new ArrayList();
		List<Patient> patients = new ArrayList();
		for(Apply apply :applys){
			Patient patient = this.patientManager.getPatientById(apply.getPid());	
			patients.add(patient);
		}
		for (Patient patient :patients){
			Bed bed = this.bedManager.getBedByPid(patient.getId());
		//	String docName = this.userManager.getUserById(uid)
		//			.getUserName();
			PatientAndBed b = new PatientAndBed(bed);
			bedAndPatient.add(b);
		}
		int total = patients.size();
		this.result.setResults(bedAndPatient);
		this.result.setTotal(total);
		this.result.setSuccess(true);
		}catch (Exception e) {
			this.result.setMsg("获取病人信息失败");
			this.result.setSuccess(false);
			log.error(e);
		}
		return "OK";
	}
	
	// 确认化验检查病人
	public String ConfirmExamNewPt(){
		
		this.result = new JsonBase();
		if (!authorized("id")) {
			this.result = new JsonBase();
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
			this.log.info("获取当前用户失败");
			return "OK";
		}else{
		try{
		Integer DocId=(Integer)getSession().get("id");
		List<Apply> applys = this.applyManager.testUndealt(Apply.TypeA.EXAMINE);
		List<Apply> applys1 = this.applyManager.testUndealt(Apply.TypeA.INSPECTION);
	
		for(Apply apply :applys){
		//	Patient patient = this.patientManager.getPatientById(apply.getPid());
			List<Edetail> edetails = this.edetailManager.getAllEdetailBypid(apply.getPid(),apply.getId());
			for(Edetail edetail:edetails){
				edetail.setDid(DocId);
				edetail.setStatus("已领取");
				this.edetailManager.update(edetail);
			}
			apply.setStatus("已领取");
			this.applyManager.update(apply);
			}
		for(Apply apply :applys1){
			List<Inspect> inspects = this.inspectManager.getAllInspectBypid(apply.getPid(),apply.getId());
			for(Inspect inspect:inspects){
				inspect.setDid(DocId);
				inspect.setStatus("已领取");
				this.inspectManager.update(inspect);
			}
			apply.setStatus("已领取");
			this.applyManager.update(apply);
			//patients.add(patient);
		}		
		this.result.setSuccess(true);
	}catch (Exception e) {
		System.out.println(e);
		this.result.setMsg("获取病人信息失败");
		this.result.setSuccess(false);
		log.error(e);
	}
		}
		return "OK";
	}
	
	
	// 获取未检查申请内容
	public String getInspectApply() {
		if(!this.all){
		this.result = new JsonBase();
		int page = -1;
		int limit = 40;
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
			List<Apply> list = this.applyManager.getApplyByPid(this.id,
					Apply.TypeA.INSPECTION);
			List<InspectAndDoctor> list1 = new ArrayList();
			for (Apply apply : list) {
				List<Inspect> inspects = this.inspectManager.getInspectBypid(
						apply.getPid(), apply.getId());
				for (Inspect inspect : inspects) {
					User user = this.userManager.getUserById(apply.getDid());
					User user1 = this.userManager.getUserById(inspect.getDid());
					InspectAndDoctor a = new InspectAndDoctor(user,user1,inspect);					
							list1.add(a);
					
				}
			}
			List<InspectAndDoctor> da3 = new ArrayList();
			if (page != -1) {					
					for (int i = (page - 1) * limit; (i < list1.size())
							&& (i < page * limit); i++) {
						da3.add((InspectAndDoctor) list1.get(i));
					}
					int total = list1.size();
					this.result.setResults(da3);
					this.result.setSuccess(true);
					this.result.setTotal(total);					
			} else {
				int total = list1.size();
				this.result.setResults(list1);
				this.result.setSuccess(true);
				this.result.setTotal(total);
			}
		} catch (Exception e) {
			System.out.println(e);
			this.result.setMsg("请检查网络链接是否正常！");
			this.result.setSuccess(false);
		}
		}else{
			getAllInspectApply();
		}
		return OK;
	}
	// 获取所有检查申请内容
	public String getAllInspectApply() {
		this.result = new JsonBase();
		int page = -1;
		int limit = 40;
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
			List<Apply> list = this.applyManager.getAllApplyByPid(this.id,
					Apply.TypeA.INSPECTION);
			List<InspectAndDoctor> list1 = new ArrayList();
			for (Apply apply : list) {
				List<Inspect> inspects = this.inspectManager.getAllInspectBypid(
						apply.getPid(), apply.getId());
				for (Inspect inspect : inspects) {
					User user = this.userManager.getUserById(apply.getDid());
					User user1 = this.userManager.getUserById(inspect.getDid());
					InspectAndDoctor a = new InspectAndDoctor(user,user1,inspect);					
							list1.add(a);
					
				}
			}
			List<InspectAndDoctor> da3 = new ArrayList();
			if (page != -1) {					
					for (int i = (page - 1) * limit; (i < list1.size())
							&& (i < page * limit); i++) {
						da3.add((InspectAndDoctor) list1.get(i));
					}
					int total = list1.size();
					this.result.setResults(da3);
					this.result.setSuccess(true);
					this.result.setTotal(total);					
			} else {
				int total = list1.size();
				this.result.setResults(list1);
				this.result.setSuccess(true);
				this.result.setTotal(total);
			}
		} catch (Exception e) {
			System.out.println("getAllInspectApply Exception"+e);
			this.result.setMsg("请检查网络链接是否正常！");
			this.result.setSuccess(false);
		}
		return OK;
	}
	// 获取新检查病人列表
	public String getNewInspectionPt() {
		this.result = new JsonBase();
		/*
		 * if (!authorized("sectionid")) { this.result.setMsg("statusfailed");
		 * this.result.setSuccess(false); } else {
		 */
		try {
			List<Apply> a = this.applyManager.testUndealt(Apply.TypeA.EXAMINE);
			List<Apply> b = this.applyManager.testUndealt(Apply.TypeA.INSPECTION);
			TestNewPt tna = new TestNewPt();
			//List<TestNewAdvice> ts = new ArrayList();
			//ts.add(tna);
			int num = b.size()+a.size();
			if (num > 0) {
				tna.setIsNewPt(true);
			} else {
				tna.setIsNewPt(false);
			}
			this.result.setResults(tna);
			this.result.setTotal(num);
			this.result.setSuccess(true);
		} catch (Exception e) {
			this.result.setSuccess(false);
			this.result.setMsg("查询病人信息失败，请检查网络");
			log.error(e);
		}

		return "OK";
	}

	
	
	//获取手术病人
	public String getSurgyApply() {		
		this.result = new JsonBase();
		if(!this.all){							
			try {
				List<Apply> list = this.applyManager.getApplyByStatusA(Apply.TypeB.executing,
						Apply.TypeA.OPS);
				List<ApplyAndPatient> applyAndPatient = new ArrayList();
				for (Apply apply : list) {
					if (apply.getPid() != 0) {
						Patient patient = this.patientManager.getPatientById(apply
								.getPid());
						User doctor = this.userManager.getUserById(apply
								.getDid());
						Bed bed = this.bedManager.getBedByPid(apply.getPid());
						ApplyAndPatient a = new ApplyAndPatient(apply, patient,
								doctor,bed);
						applyAndPatient.add(a);
					}
				}
				this.result.setTotal(applyAndPatient.size());
				this.result.setResults(applyAndPatient);
				this.result.setSuccess(true);
			} catch (Exception e) {
				System.out.println(e);
				this.result.setMsg("请检查网络链接是否正常！");
				this.result.setSuccess(false);
			}
		}else{
		try {
			List<Apply> list = this.applyManager.getApplyByStatusA(Apply.TypeB.executing,
					Apply.TypeA.OPS);
			List<Apply> list2 = this.applyManager.getApplyByStatusA(Apply.TypeB.finished,
					Apply.TypeA.OPS);
			List<ApplyAndPatient> applyAndPatient = new ArrayList();
			for (Apply apply : list) {
				if (apply.getPid() != 0) {
					Patient patient = this.patientManager.getPatientById(apply
							.getPid());
					User doctor = this.userManager.getUserById(apply
							.getDid());
					Bed bed = this.bedManager.getBedByPid(apply.getPid());
					ApplyAndPatient a = new ApplyAndPatient(apply, patient,
							doctor,bed);
					applyAndPatient.add(a);
				}
			}
			for (Apply apply : list2) {
				if (apply.getPid() != 0) {
					Patient patient = this.patientManager.getPatientById(apply
							.getPid());
					User doctor = this.userManager.getUserById(apply
							.getDid());
					Bed bed = this.bedManager.getBedByPid(apply.getPid());
					ApplyAndPatient a = new ApplyAndPatient(apply, patient,
							doctor,bed);
					applyAndPatient.add(a);
				}
			}
			
			this.result.setTotal(applyAndPatient.size());
			this.result.setResults(applyAndPatient);
			this.result.setSuccess(true);
		} catch (Exception e) {
			System.out.println(e);
			this.result.setMsg("请检查网络链接是否正常！");
			this.result.setSuccess(false);
		}
		}
		
		return OK;
	}
	//手术新病人提醒
	public String getNewSurgyPt() {
		this.result = new JsonBase();
		/*
		 * if (!authorized("sectionid")) { this.result.setMsg("statusfailed");
		 * this.result.setSuccess(false); } else {
		 */
		try {
			List<Apply> b = this.applyManager.testUndealt(Apply.TypeA.OPS);
			TestNewPt tna = new TestNewPt();
	//		List<TestNewAdvice> ts = new ArrayList();
		//	ts.add(tna);
			int num = b.size();
			if (num > 0) {
				tna.setIsNewPt(true);
			} else {
				tna.setIsNewPt(false);
			}
			this.result.setResults(tna);
			this.result.setTotal(num);
			this.result.setSuccess(true);
		} catch (Exception e) {
			this.result.setSuccess(false);
			this.result.setMsg("查询病人信息失败，请检查网络");
			log.error(e);
		}

		return "OK";
	}

	// 获取手术申请内容	
	public String getOperationApply() {
		this.result = new JsonBase();
	
		if(!this.all){
			
			int page = -1;
			int limit = 40;
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
		if(this.id== 0){			
		}else{
		try {
			List<Apply> list = this.applyManager.getApplyByPid(this.id,
					Apply.TypeA.OPS);
			List<OperationAndDoctor> list1 = new ArrayList();
			for (Apply apply : list) {
				List<Operation> operations = this.operationManager.getOperationBypid(apply.getPid(), apply.getId());		
				for (Operation operation : operations) {	
					User user = this.userManager.getUserById(apply.getDid());
					User user1 = this.userManager.getUserById(operation.getDid());
					OperationAndDoctor a = new OperationAndDoctor(user,user1,operation,apply);					
							list1.add(a);
					
				}
			}			
			List<OperationAndDoctor> da3 = new ArrayList();
			if (page != -1) {					
					for (int i = (page - 1) * limit; (i < list1.size())
							&& (i < page * limit); i++) {
						da3.add((OperationAndDoctor) list1.get(i));
					}
					int total = list1.size();
					this.result.setResults(da3);
					this.result.setSuccess(true);
					this.result.setTotal(total);					
			} else {
				int total = list1.size();
				this.result.setResults(list1);
				this.result.setSuccess(true);
				this.result.setTotal(total);
			}
		} catch (Exception e) {
			System.out.println(e);
			this.result.setMsg("请检查网络链接是否正常！");
			this.result.setSuccess(false);
		}}
		
		}else{
			getAllOperationApply();
		}
		return OK;
	}
	// 获取所有手术申请内容
	public String getAllOperationApply() {
		this.result = new JsonBase();
		int page = -1;
		int limit = 40;
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
		if(this.id== 0){
			
		}else{
		try {
			List<Apply> list = this.applyManager.getAllApplyByPid(this.id,
					Apply.TypeA.OPS);
			List<OperationAndDoctor> list1 = new ArrayList();
			if(list!=null){
			for (Apply apply : list) {
				List<Operation> operations = this.operationManager
						.getAllOperationBypid(apply.getPid(), apply.getId());
				for (Operation operation : operations) {
					User user = this.userManager.getUserById(apply.getDid());
					User user1 = this.userManager.getUserById(operation.getDid());
					OperationAndDoctor a = new OperationAndDoctor(user,user1,operation,apply);					
							list1.add(a);
				}
			}
			
			List<OperationAndDoctor> da3 = new ArrayList();
			if (page != -1) {					
					for (int i = (page - 1) * limit; (i < list1.size())
							&& (i < page * limit); i++) {
						da3.add((OperationAndDoctor) list1.get(i));
					}
			}
					int total = list1.size();
					this.result.setResults(da3);
					this.result.setSuccess(true);
					this.result.setTotal(total);					
			} else {
				int total = list1.size();
				this.result.setResults(list1);
				this.result.setSuccess(true);
				this.result.setTotal(total);
			}
		} catch (Exception e) {
			System.out.println(e);
			this.result.setMsg("请检查网络链接是否正常！");
			this.result.setSuccess(false);
		}}
		return OK;
	}		
	
	//获取手术新病人
	public String SurgyNewPt(){
		this.result = new JsonBase();
		try {
		List<Apply> applys = this.applyManager.testUndealt(Apply.TypeA.OPS);
		List<PatientAndBed> bedAndPatient = new ArrayList();
		List<Patient> patients = new ArrayList();
		for(Apply apply :applys){
			Patient patient = this.patientManager.getPatientById(apply.getPid());
		//	List<Operation> operations = this.operationManager.getOperationBypid(apply.getPid(),apply.getDid());
			patients.add(patient);
		}
		for (Patient patient :patients){
			Bed bed = this.bedManager.getBedByPid(patient.getId());
		//	String docName = this.userManager.getUserById(uid)
		//			.getUserName();
			PatientAndBed b = new PatientAndBed(bed);
			bedAndPatient.add(b);
		}
		int total = patients.size();
		this.result.setResults(bedAndPatient);
		this.result.setTotal(total);
		this.result.setSuccess(true);}catch (Exception e) {
			this.result.setMsg("获取病人信息失败");
			this.result.setSuccess(false);
			log.error(e);
		}
		return "OK";
	}
	
	//确认手术新病人
	public String ConfirmSurgyNewPt(){
		this.result = new JsonBase();
		if (!authorized("id")) {
			this.result = new JsonBase();
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
			this.log.info("获取当前用户失败");
			return "OK";
		}else{
		try {
			Integer DocId=(Integer)getSession().get("id");
		List<Apply> applys = this.applyManager.testUndealt(Apply.TypeA.OPS);
		List<PatientAndBed> bedAndPatient = new ArrayList();
		List<Patient> patients = new ArrayList();
		for(Apply apply :applys){
		//	Patient patient = this.patientManager.getPatientById(apply.getPid());
			List<Operation> operations = this.operationManager.getOperationBypid(apply.getPid(),apply.getId());
			for(Operation operation:operations){
				operation.setStatus("已领取");
				operation.setDid(DocId);
				this.operationManager.update(operation);
			}
			apply.setStatus("已领取");
			this.applyManager.update(apply);
			//patients.add(patient);
		}		
		this.result.setSuccess(true);
		}catch (Exception e) {
			this.result.setMsg("获取病人信息失败");
			this.result.setSuccess(false);
			log.error(e);
		}
		}
		return "OK";
	}
							
	
	
	
	
	
	// 获取转运申请内容		
	public String getTransApply() {
		this.result = new JsonBase();
		int page = -1;
		int limit = 40;
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
			List<Apply> list = this.applyManager.getApplyByPid(this.id,
					Apply.TypeA.TRANSPORT);
			List<TransportAndDoctor> list1 = new ArrayList();
			for (Apply apply : list) {
				List<Transport> transports = this.transportManager
						.getTransportBypid(apply.getPid(), apply.getId());
				for (Transport transport : transports) {
					User user = this.userManager.getUserById(apply.getDid());
					User user1=null;
					if((Integer)transport.getTid()!=null&&transport.getTid()>0){
						 user1 = this.userManager.getUserById(transport.getTid());
					}
					
					TransportAndDoctor a = new TransportAndDoctor(user,user1,transport,apply);					
							list1.add(a);
				}
			}
			List<TransportAndDoctor> da3 = new ArrayList();
			if (page != -1) {					
					for (int i = (page - 1) * limit; (i < list1.size())
							&& (i < page * limit); i++) {
						da3.add((TransportAndDoctor) list1.get(i));
					}
					int total = list1.size();
					this.result.setResults(da3);
					this.result.setSuccess(true);
					this.result.setTotal(total);					
			} else {
				int total = list1.size();
				this.result.setResults(list1);
				this.result.setSuccess(true);
				this.result.setTotal(total);
			}
		} catch (Exception e) {
			System.out.println(e);
			this.result.setMsg("请检查网络链接是否正常！");
			this.result.setSuccess(false);
		}
		return OK;
	}
	
	public String getAllTransApply() {
		this.result = new JsonBase();
		int page = -1;
		int limit = 40;
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
			List<Apply> list = this.applyManager.getAllApplyByPid(this.id,
					Apply.TypeA.TRANSPORT);
			List<TransportAndDoctor> list1 = new ArrayList();
			for (Apply apply : list) {
				List<Transport> transports = this.transportManager
						.getTransportBypid(apply.getPid(), apply.getId());
				for (Transport transport : transports) {
					User user = this.userManager.getUserById(apply.getDid());
					User user1=null;
					if((Integer)transport.getTid()!=null&&transport.getTid()>0){
						 user1 = this.userManager.getUserById(transport.getTid());
					}
					
					TransportAndDoctor a = new TransportAndDoctor(user,user1,transport,apply);					
							list1.add(a);
				}
			}
			List<TransportAndDoctor> da3 = new ArrayList();
			if (page != -1) {					
					for (int i = (page - 1) * limit; (i < list1.size())
							&& (i < page * limit); i++) {
						da3.add((TransportAndDoctor) list1.get(i));
					}
					int total = list1.size();
					this.result.setResults(da3);
					this.result.setSuccess(true);
					this.result.setTotal(total);					
			} else {
				int total = list1.size();
				this.result.setResults(list1);
				this.result.setSuccess(true);
				this.result.setTotal(total);
			}
		} catch (Exception e) {
			System.out.println(e);
			this.result.setMsg("请检查网络链接是否正常！");
			this.result.setSuccess(false);
		}
		return OK;
	}
	
	//获取转运病人
	public String getTransportApply() {
			this.result = new JsonBase();
			try {	
				List<Apply> list = this.applyManager.getApplyByStatusA(Apply.TypeB.unexecuted,
						Apply.TypeA.TRANSPORT);
				List<ApplyAndPatientAndContent> ApplyAndPatientAndContent = new ArrayList();
				for (Apply apply : list) {
					if (apply.getPid() != 0) {	
						Transport transport = this.transportManager
								.getTransportByApply(apply.getId());					
						if(!transport.getStatus().equals("转运完成")){
						Patient patient = this.patientManager.getPatientById(apply
								.getPid());
						User doctor = this.userManager.getUserById(apply
								.getDid());									
						ApplyAndPatientAndContent a = new ApplyAndPatientAndContent(
								apply, patient, doctor, transport);					
						ApplyAndPatientAndContent.add(a);
						
						}
					}
				}
				this.result.setTotal(ApplyAndPatientAndContent.size());
				this.result.setResults(ApplyAndPatientAndContent);
				this.result.setSuccess(true);
			} catch (Exception e) {
				System.out.println(e);
				this.result.setMsg("请检查网络链接是否正常！");
				this.result.setSuccess(false);
			}
			return OK;
		}
	//根据医生信息获取获取转运病人			
	public String getMyTransportApply() {
			this.result = new JsonBase();	
				if (!authorized("id")) {
					this.result = new JsonBase();
					this.result.setMsg("statusfailed");
					this.result.setSuccess(false);
					this.log.info("获取当前用户失败");
					return "OK";
				}else{
				try {
				List<Transport> list = this.transportManager.getTransportByTid((Integer)getSession().get("id"));
				List<ApplyAndPatientAndContent> ApplyAndPatientAndContent = new ArrayList();
				for (Transport transport : list) {
					if (transport.getPid() != 0) {
						if(!transport.getStatus().equals("转运完成")){
						Apply apply = this.applyManager.getApply(transport.getApplyId());
						Patient patient = this.patientManager.getPatientById(transport
								.getPid());
						User doctor = this.userManager.getUserById(apply
								.getDid());	
						ApplyAndPatientAndContent a = new ApplyAndPatientAndContent(
								apply, patient, doctor, transport);
						ApplyAndPatientAndContent.add(a);
						}
					}
				}
				this.result.setTotal(ApplyAndPatientAndContent.size());
				this.result.setResults(ApplyAndPatientAndContent);
				this.result.setSuccess(true);
			} catch (Exception e) {
				System.out.println(e);
				this.result.setMsg("请检查网络链接是否正常！");
				this.result.setSuccess(false);
			}
			return OK;
			}
		}
			
	//转运新病人提醒
	public String getNewTransportPt() {
			this.result = new JsonBase();
			/*
			 * if (!authorized("sectionid")) { this.result.setMsg("statusfailed");
			 * this.result.setSuccess(false); } else {
			 */
			try {
				List<Apply> b = this.applyManager.testUndealt(Apply.TypeA.TRANSPORT);
				TestNewAdvice tna = new TestNewAdvice();
				//List<TestNewAdvice> ts = new ArrayList();
				//ts.add(tna);
				int num = b.size();
				if (num > 0) {
					tna.setIsNewAdvice(true);
				} else {
					tna.setIsNewAdvice(false);
				}
				this.result.setResults(tna);
				this.result.setTotal(num);
				this.result.setSuccess(true);
			} catch (Exception e) {
				this.result.setSuccess(false);
				this.result.setMsg("查询病人信息失败，请检查网络");
				log.error(e);
			}

			return "OK";
		}
		
	
	public String editTransport() {
		this.result = new JsonBase();
		if ( this.id < 0) {
			this.result.setMsg("请选择要更新的转运信息！");
			this.result.setSuccess(false);
			this.log.error("医嘱id不正确");
		} else {
			try {
				Transport transports = this.transportManager.gettransportById(this.id);
				if ((this.content != null) && (!this.content.equals("")))
					transports.setContent(this.content);
				if ((this.illustration != null) && (!this.illustration.equals("")))
					transports.setContent(this.illustration);
				
				this.transportManager.update(transports);
				this.result.setSuccess(true);
				this.result.setMsg("转运信息修改成功");				
			} catch (Exception e) {
				System.out.println("editDocAdvice"+e);
				this.result.setMsg("转运信息修改失败");
				this.result.setSuccess(false);
				this.log.error("修改信息失败");
			}
		}

		return "OK";
	}
		
	
	
	
	
	public String ReceviceInspection() {
		if (!authorized("id")) {
			this.result = new JsonBase();
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
			this.log.info("获取当前用户失败");
			return "OK";
		}else{
		this.result = new JsonBase();
		if (this.id < 0) {
			this.result.setMsg("请选择要领取的转运信息！");
			this.result.setSuccess(false);
			this.log.error("转运id不正确");
		} else {
			try {
			//	Transport transport = this..gettransportById(this.id);
			//	transport.setStatus("已领取");
			//	this.transportManager.update(transport);
				this.result.setSuccess(true);
				this.result.setMsg("修改转运信息成功");
				this.log.info("修改转运信息成功");
			} catch (Exception e) {
				this.result.setMsg("修改转运信息失败");
				this.result.setSuccess(false);
				this.log.error("修改转运信息失败");
			}
		}
		return OK;
		}
	}

	public String SubmitInspection() {
		if (!authorized("id")) {
			this.result = new JsonBase();
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
			this.log.info("获取当前用户失败");
			return "OK";
		}else{
		this.result = new JsonBase();
		if (this.id < 0) {
			this.result.setMsg("请选择要提交的转运信息！");
			this.result.setSuccess(false);
			this.log.error("转运id不正确");
		} else {
			try {
			//	Transport transport = this.transportManage.gettransportById(this.id);
			//	transport.setStatus("已完成");
			//	Apply apply = this.applyManager.getApply(transport.getApplyId());
			//	apply.setStatus("已完成");
			//	this.applyManager.update(apply);
			//	this.transportManager.update(transport);
				this.result.setSuccess(true);
				this.result.setMsg("提交转运任务成功");
				this.log.info("提交转运任务成功");
			} catch (Exception e) {
				this.result.setMsg("提交转运任务失败");
				this.result.setSuccess(false);
				this.log.error("提交转运任务失败");
			}
		}

		return OK;
		}
	}

	public String ReceviceTransport() {
		if (!authorized("id")) {
			this.result = new JsonBase();
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
			this.log.info("获取当前用户失败");
			return "OK";
		}else{
		this.result = new JsonBase();
		if (this.id < 0) {
			this.result.setMsg("请选择要领取的转运信息！");
			this.result.setSuccess(false);
			this.log.error("转运id不正确");
		} else {
			try {
				tid=(Integer)getSession().get("id");
				Transport transport = this.transportManager.getTransportByApply(this.id);		
				if(transport==null){
					this.result.setMsg("转运id不正确");
					this.result.setSuccess(false);
				}else{
				transport.setStatus("正在转运");
				transport.setTid(tid);		
				Apply apply = this.applyManager.getApply(transport.getApplyId());
				apply.setStatus("已领取");
				this.transportManager.update(transport);
				this.applyManager.update(apply);
				this.result.setSuccess(true);
				this.result.setMsg("修改转运信息成功");
				this.log.info("修改转运信息成功");
				}
			} catch (Exception e) {
				System.out.println(e);
				this.result.setMsg("修改转运信息失败");
				this.result.setSuccess(false);
				this.log.error("修改转运信息失败");
			}
		}

		return OK;
		}
	}

	public String SubmitTransport() {
		if (!authorized("id")) {
			this.result = new JsonBase();
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
			this.log.info("获取当前用户失败");
			return "OK";
		}else{
		this.result = new JsonBase();
		if (this.id < 0) {
			this.result.setMsg("请选择要提交的转运信息！");
			this.result.setSuccess(false);
			this.log.error("转运id不正确");
		} else {
			try {
				Transport transport = this.transportManager.getTransportByApply(this.id);
				if(transport==null){
					this.result.setMsg("转运id不正确");
					this.result.setSuccess(false);
				}else{
				transport.setStatus("转运完成");
				Apply apply = this.applyManager.getApply(transport.getApplyId());
				Patient patient = this.patientManager.getPatientById(apply.getPid());
				apply.setStatus("已完成");
				patient.setStatus(Patient.Status.OUT);
				this.applyManager.update(apply);
				this.transportManager.update(transport);
				this.patientManager.updatePatient(patient);
				this.result.setSuccess(true);
				this.result.setMsg("提交转运任务成功");
				this.log.info("提交转运任务成功");
				}
			} catch (Exception e) {
				System.out.println(e);
				this.result.setMsg("提交转运任务失败");
				this.result.setSuccess(false);
				this.log.error("提交转运任务失败");
			}
		}

		return OK;
		}
	}
	
	public String ReceviceOperation() {
		if (!authorized("id")) {
			this.result = new JsonBase();
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
			this.log.info("获取当前用户失败");
			return "OK";
		}else{
		this.result = new JsonBase();
		if (this.id < 0) {
			this.result.setMsg("请选择要领取的手术信息！");
			this.result.setSuccess(false);
			this.log.error("手术id不正确");
		} else {
			try {
				Operation operation = this.operationManager.getoperationById(this.id);
					
				operation.setStatus("已领取");
				this.operationManager.update(operation);
				this.result.setSuccess(true);
				this.result.setMsg("领取手术成功");
				this.log.info("领取手术信息成功");
			} catch (Exception e) {
				this.result.setMsg("领取手术信息失败");
				this.result.setSuccess(false);
				this.log.error("领取手术信息失败");
			}
		}

		return OK;
		}
	}

	public String SubmitOperation() {
		if (!authorized("id")) {
			this.result = new JsonBase();
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
			this.log.info("获取当前用户失败");
			return "OK";
		}else{
		this.result = new JsonBase();
		if (this.id < 0) {
			this.result.setMsg("请选择要提交的手术信息！");
			this.result.setSuccess(false);
			this.log.error("手术id不正确");
		} else {
			try {
				Operation operation = this.operationManager.getoperationById(this.id);
				operation.setStatus("已完成");
				Apply apply = this.applyManager
						.getApply(operation.getApplyId());
				apply.setStatus("已完成");
				this.applyManager.update(apply);
				this.operationManager.update(operation);
				this.result.setSuccess(true);
				this.result.setMsg("提交手术任务成功");
				this.log.info("提交手术任务成功");
			} catch (Exception e) {
				this.result.setMsg("提交手术任务失败");
				this.result.setSuccess(false);
				this.log.error("提交手术任务失败");
			}
		}

		return OK;
		}
	}


	

	
	
}
