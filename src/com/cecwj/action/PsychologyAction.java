package com.cecwj.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cecwj.common.PinyinTool;
import com.cecwj.common.TimeFormat;
import com.cecwj.model.Apply;
import com.cecwj.model.Doctor;
import com.cecwj.model.Edetail;
import com.cecwj.model.Inspect;
import com.cecwj.model.Operation;
import com.cecwj.model.Patient;
import com.cecwj.model.Psychology;
import com.cecwj.model.Transport;
import com.cecwj.model.User;
import com.cecwj.model.serialize.ApplyAndPatient;
import com.cecwj.model.serialize.JsonBase;
import com.cecwj.service.ApplyManager;
import com.cecwj.service.DictManager;
import com.cecwj.service.DoctorManager;
import com.cecwj.service.EdetailManager;
import com.cecwj.service.InspectManager;
import com.cecwj.service.OperationManager;
import com.cecwj.service.PatientManager;
import com.cecwj.service.PsychologyManager;
import com.cecwj.service.TransportManager;
import com.cecwj.service.UserManager;

public class PsychologyAction extends BaseAction {
	static Log log = LogFactory.getLog(PsychologyAction.class);
	private int id;
	private int nid;
	private int pid;
	private String time;
	private String ptname;
	private String nursename;
	private String psychologyStatus;
	private PsychologyManager psychologyManager;
	private PatientManager patientManager;
	private UserManager userManger;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPtname() {
		return ptname;
	}
	public void setPtname(String ptname) {
		this.ptname = ptname;
	}
	public String getNursename() {
		return nursename;
	}
	public void setNursename(String nursename) {
		this.nursename = nursename;
	}
	public String getPsychologyStatus() {
		return psychologyStatus;
	}
	public void setPsychologyStatus(String psychologyStatus) {
		this.psychologyStatus = psychologyStatus;
	}
	public PsychologyManager getPsychologyManager() {
		return psychologyManager;
	}
	@Resource	
	public void setPsychologyManager(PsychologyManager psychologyManager) {
		this.psychologyManager = psychologyManager;
	}
	
	
	public PatientManager getPatientManager() {
		return patientManager;
	}
	@Resource	
	public void setPatientManger(PatientManager patientManager) {
		this.patientManager = patientManager;
	}
	public UserManager getUserManger() {
		return userManger;
	}
	@Resource	
	public void setUserManger(UserManager userManger) {
		this.userManger = userManger;
	}
	public String addPsychology(){
		this.result= new JsonBase();
		if(this.pid<=0){
			this.result.setMsg("请选择病人");
			this.result.setSuccess(false);
		}else if (this.psychologyStatus==null||this.psychologyStatus.equals("")){
			this.result.setMsg("获取心理评估内容失败");
			this.result.setSuccess(false);
		}else {
						
				try{
					Psychology psychology= new Psychology();
					Patient patient = this.patientManager.getPatientById(this.pid);
					if(patient!=null){
						psychology.setPid(this.pid);
						psychology.setPtname(patient.getName());									
					//psychology.setTime(TimeFormat.stringToTimestamp(this.time));
					psychology.setPsychologyStatus(this.psychologyStatus);
					this.psychologyManager.addPsychology(psychology);
					this.result.setMsg("添加成功");
					this.result.setSuccess(true);
					}else{						
						this.result.setMsg("病人不存在");
						this.result.setSuccess(false);
					}
			}catch (Exception e){
				System.out.println(e);
				this.result.setMsg("添加失败");
				this.result.setSuccess(false);
			}
		}							
		return "OK";
	}
	
	public String getPsychology(){
		this.result= new JsonBase();
		if(this.id<=0){
			//this.result.setMsg("获取病人失败");
		//	this.result.setSuccess(false);
		}else{
				try{
					List<Psychology> psychology = this.psychologyManager.getPsychologyByPid(this.id);
					this.result.setResults(psychology);
					this.result.setSuccess(true);					
				}catch (Exception e){
					System.out.println(e);
					this.result.setMsg("获取心理评估失败");
					this.result.setSuccess(false);
				}
			}		
		
		return "OK";
	}
	
	public String deletePsychology(){
		this.result= new JsonBase();
		if(this.id<=0){
			this.result.setMsg("获取失败");
			this.result.setSuccess(false);
		}else{
				try{
					Psychology psychology = this.psychologyManager.getPsychologyById(this.id);
					if(psychology!=null){
						this.psychologyManager.deletePsychology(psychology);
						this.result.setMsg("删除成功");
						this.result.setSuccess(true);
					}					
				}catch (Exception e){
					System.out.println(e);
					this.result.setMsg("获取心理评估失败");
					this.result.setSuccess(false);
				}
			}		
		
		return "OK";
	}
	public String updatePsychology(){
		this.result= new JsonBase();
		if(this.id<=0){
			this.result.setMsg("获取病人失败");
			this.result.setSuccess(false);
		}else{
			if(this.psychologyStatus!=null){
					Psychology psychology = this.psychologyManager.getPsychologyById(this.id);
					psychology.setPsychologyStatus(this.psychologyStatus);
				try{
					this.psychologyManager.update(psychology);
					this.result.setResults(psychology);
					this.result.setSuccess(true);
					
				}catch (Exception e){
					System.out.println(e);
					this.result.setMsg("获取心理评估失败");
					this.result.setSuccess(false);
				}
			}		
		}
		return "OK";
	}

	
}
