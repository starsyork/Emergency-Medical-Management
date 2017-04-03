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
import com.cecwj.model.Duty;
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
import com.cecwj.service.DutyManager;
import com.cecwj.service.EdetailManager;
import com.cecwj.service.InspectManager;
import com.cecwj.service.OperationManager;
import com.cecwj.service.PatientManager;
import com.cecwj.service.PsychologyManager;
import com.cecwj.service.TransportManager;
import com.cecwj.service.UserManager;

public class DutyAction extends BaseAction {
	static Log log = LogFactory.getLog(DutyAction.class);
	private int id;
	private String workupdowndate;
	private String workoldcon ;
	private String worknewcon ;
	private String  workupper;
	private String workdownloader ;
	private String workupstuff ;
	private String workdownstuff ;
	private DutyManager dutyManger;
	private PatientManager patientManger;
	private UserManager userManger;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getWorkupdowndate() {
		return workupdowndate;
	}
	public void setWorkupdowndate(String workupdowndate) {
		this.workupdowndate = workupdowndate;
	}
	public String getWorkoldcon() {
		return workoldcon;
	}
	public void setWorkoldcon(String workoldcon) {
		this.workoldcon = workoldcon;
	}
	public String getWorknewcon() {
		return worknewcon;
	}
	public void setWorknewcon(String worknewcon) {
		this.worknewcon = worknewcon;
	}
	public String getWorkupper() {
		return workupper;
	}
	public void setWorkupper(String workupper) {
		this.workupper = workupper;
	}
	public String getWorkdownloader() {
		return workdownloader;
	}
	public void setWorkdownloader(String workdownloader) {
		this.workdownloader = workdownloader;
	}
	public String getWorkupstuff() {
		return workupstuff;
	}
	public void setWorkupstuff(String workupstuff) {
		this.workupstuff = workupstuff;
	}
	public String getWorkdownstuff() {
		return workdownstuff;
	}
	public void setWorkdownstuff(String workdownstuff) {
		this.workdownstuff = workdownstuff;
	}
	public DutyManager getDutyManger() {
		return dutyManger;
	}
	@Resource
	public void setDutyManger(DutyManager dutyManger) {
		this.dutyManger = dutyManger;
	}
	public PatientManager getPatientManger() {
		return patientManger;
	}
	@Resource	
	public void setPatientManger(PatientManager patientManger) {
		this.patientManger = patientManger;
	}
	public UserManager getUserManger() {
		return userManger;
	}
	@Resource	
	public void setUserManger(UserManager userManger) {
		this.userManger = userManger;
	}
	
	public String addDuty(){
		this.result = new JsonBase();
		try{
			int  section = (Integer)getSession().get("sectionid");				
		if(this.workoldcon == null||this.workoldcon.equals("")){
			this.result.setMsg("");
			this.result.setSuccess(false);				
		}else if(this.worknewcon== null||this.worknewcon.equals("")){
			this.result.setMsg("");
			this.result.setSuccess(false);				
		}else if(this.workdownloader== null||this.workdownloader.equals("")){
			this.result.setMsg("");
			this.result.setSuccess(false);				
		}else if(this.workupper== null||this.workupper.equals("")){
			this.result.setMsg("");
			this.result.setSuccess(false);				
		}else if(this.workupstuff == null||this.workupstuff.equals("")){
			this.result.setMsg("");
			this.result.setSuccess(false);				
		}else if(this.workdownstuff == null||this.workdownstuff.equals("")){
			this.result.setMsg("");
			this.result.setSuccess(false);				
		}else if(this.workupdowndate== null||this.workupdowndate.equals("")){
			this.result.setMsg("");
			this.result.setSuccess(false);				
		}else{
			Duty duty = new Duty();
			duty.setWorkoldcon(this.workoldcon);
			duty.setWorknewcon(this.worknewcon);
			duty.setWorkupper(this.workupper);
			duty.setWorkdownloader(this.workdownloader);
			duty.setWorkdownstuff(this.workdownstuff);
			duty.setWorkupstuff(this.workupstuff);
			duty.setWorkupdowndate(TimeFormat.stringToTimestamp(this.workupdowndate));
			duty.setSectionId(section);
			try{
				this.dutyManger.addDuty(duty);
			}catch(Exception e){
				System.out.println(e);
				this.result.setMsg("添加失败");
				this.result.setSuccess(false);
			}
		}
		}catch(Exception e){
			System.out.println(e);
			this.result.setMsg("登录过期");
			this.result.setSuccess(false);
		}
		return "OK";
	}
	public String getDuty(){
		this.result = new JsonBase();
		if(!authorized("id")){
		//	this.result.setMsg("statusfailed");
		//	this.result.setSuccess(false);
		}else{
			try{
				int  section = (Integer)getSession().get("sectionid");
				List<Duty> duty = this.dutyManger.getDutyBySection(section);
				this.result.setResults(duty);
				this.result.setSuccess(true);
			}catch (Exception e){
				System.out.println(e);
				this.result.setMsg("获取失败");
				this.result.setSuccess(false);
			}
	
		}			
		return "OK";
	}
	public String deleteDuty(){
		this.result = new JsonBase();		
		if(this.id<0){
			this.result.setMsg("获取失败");
			this.result.setSuccess(false);
		}else{
			Duty duty = this.dutyManger.getDutyById(this.id);
			try{
				this.dutyManger.deleteDuty(duty);
				this.result.setMsg("删除成功");
				this.result.setSuccess(true);
			}catch(Exception e){
				System.out.println(e);
				this.result.setMsg("删除失败");
				this.result.setSuccess(false);
			}
		}
		return "OK";
	}
	public String updateDuty(){
		this.result = new JsonBase();
		if(this.id<0){
			this.result.setMsg("获取失败");
			this.result.setSuccess(false);
		}else{
					Duty duty = this.dutyManger.getDutyById(this.id);
					if(duty!=null){
					if(this.workoldcon != null&&!this.workoldcon.equals(""))
						duty.setWorkoldcon(this.worknewcon);		
					if(this.workdownloader!= null&&!this.workdownloader.equals(""))
						duty.setWorkdownloader(this.workdownloader);
					if(this.workupper!= null&&!this.workupper.equals(""))
						duty.setWorkupper(this.workupper);
					if(this.workupstuff != null&&!this.workupstuff.equals(""))
						duty.setWorkupstuff(this.workupstuff);
					if(this.workdownstuff != null&&!this.workdownstuff.equals(""))
						duty.setWorkdownstuff(this.workdownstuff);
					if(this.workupdowndate!= null&&!this.workupdowndate.equals(""))
						duty.setWorkupdowndate(TimeFormat.stringToTimestamp(this.workupdowndate));
						try{
							this.dutyManger.updateDuty(duty);
							this.result.setMsg("更新成功");
							this.result.setSuccess(true);
						}catch (Exception e){
							System.out.println(e);
							this.result.setMsg("更新失败");
							this.result.setSuccess(false);
						}
					}else{
						this.result.setMsg("记录不存在");
						this.result.setSuccess(false);
					}
				}
		return "OK";
	}
}
