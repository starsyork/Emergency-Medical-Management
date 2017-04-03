package com.cecwj.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import com.jacob.activeX.ActiveXComponent;
import javax.annotation.Resource;
import javax.servlet.ServletContext;

import com.cecwj.common.Word;
import com.cecwj.dao.ConditionDao;
import com.cecwj.model.Condition;
import com.cecwj.model.DisProcess;
import com.cecwj.model.Disposal;
import com.cecwj.model.DocAdvice;
import com.cecwj.model.Edetail;
import com.cecwj.model.Epidemic;
import com.cecwj.model.Inspect;
import com.cecwj.model.Measure;
import com.cecwj.model.Operation;
import com.cecwj.model.PCondition;
import com.cecwj.model.Patient;
import com.cecwj.model.serialize.JsonBase;
import com.cecwj.service.ApplyManager;
import com.cecwj.service.ConditionManager;
import com.cecwj.service.DisProcessManager;
import com.cecwj.service.DisposalManager;
import com.cecwj.service.DocAdviceManager;
import com.cecwj.service.EdetailManager;
import com.cecwj.service.EpidemicManager;
import com.cecwj.service.InspectManager;
import com.cecwj.service.MeasureManager;
import com.cecwj.service.OperationManager;
import com.cecwj.service.PConditionManager;
import com.cecwj.service.PatientManager;

public class WordAction extends BaseAction{
	private EpidemicManager epidemicManager;
	private PatientManager patientManager;
	private OperationManager operationManager;
	private PConditionManager pconditionManager;
	private DisProcessManager disProcessManager;
	private DocAdviceManager docAdviceManager;
	private DisposalManager disposalManager;
    private EdetailManager edetailManager;
    private InspectManager inspectManager;
    private MeasureManager measureManager;

public InspectManager getInspectManager() {
		return inspectManager;
	}
public EdetailManager getEdetailManager() {
		return edetailManager;
	}


public DisposalManager getDisposalManager() {
		return disposalManager;
	}


public DocAdviceManager getDocAdviceManager() {
		return docAdviceManager;
	}

public DisProcessManager getDisProcessManager() {
		return disProcessManager;
	}

public PConditionManager getPconditionManager() {
		return pconditionManager;
	}

public OperationManager getOperationManager() {
		return operationManager;
	}

public EpidemicManager getEpidemicManager() {
		return epidemicManager;
	}

public PatientManager getPatientById(){
	return patientManager;
}

@Resource
public void setInspectManager(InspectManager inspectManager) {
	this.inspectManager = inspectManager;
}
@Resource
public void setEdetailManager(EdetailManager edetailManager) {
	this.edetailManager = edetailManager;
}
@Resource
public void setDisposalManager(DisposalManager disposalManager) {
	this.disposalManager = disposalManager;
}
@Resource
public void setDocAdviceManager(DocAdviceManager docAdviceManager) {
	this.docAdviceManager = docAdviceManager;
}
@Resource
public void setDisProcessManager(DisProcessManager disProcessManager) {
	this.disProcessManager = disProcessManager;
}
@Resource
public void setPconditionManager(PConditionManager pconditionManager) {
	this.pconditionManager = pconditionManager;
}
@Resource
	public void setOperationManager(OperationManager operationManager) {
		this.operationManager = operationManager;
	}
@Resource
	public void setEpidemicManager(EpidemicManager epidemicManager) {
		this.epidemicManager = epidemicManager;
	}
@Resource
public void setPatientManager(PatientManager patientManager){
	this.patientManager = patientManager;
}

public MeasureManager getMeasureManager() {
	return measureManager;
}
@Resource
public void setMeasureManager(MeasureManager measureManager) {
	this.measureManager = measureManager;
}
public void sdfk()  {	
	
	
	
		Word msWordManager;	
		this.result = new JsonBase();		
		try {	
			msWordManager = new com.cecwj.common.Word(false);
			msWordManager.createNewDocument();
		msWordManager.insertFormatStr("武警总医院", "黑体", 20, "0,0,0,0", true, false, 1);
			msWordManager.insertFormatStr("\r\n", "黑体", 20, "0,0,0,0", false, false, 1);
			msWordManager.insertFormatStr("转院报告单", "黑体", 15, "0,0,0,0", false, false, 1);
			msWordManager.insertFormatStr("\r\n", "黑体", 60, "0,0,0,0", false, false, 1);
//			for(int i=0;i<2;i++){
//				System.out.println("E:" + File.separator +"pic"+ File.separator +i+".jpg");
//				msWordManager.insertImage("E:" + File.separator +"pic"+ File.separator +i+".jpg");			
//			}
		
			//患者id
			int id=199;
			
			
// * **********************表1 基本信息*************************
			msWordManager.insertFormatStr("基本信息", "黑体", 13, "0,0,0,0", true, false, 3);			
			msWordManager.moveDown(1);
			
			
		   msWordManager.setFont(false, false, false, "10", "宋体");
		    msWordManager.createTable("table1",7,3);	
		   
			Patient patient = this.patientManager.getPatientById(id);		
			msWordManager.putTxtToCell(1,1,1,"姓名");
			msWordManager.putTxtToCell(1,1,2,"性别");
			msWordManager.putTxtToCell(1,1,3,"年龄");
			msWordManager.putTxtToCell(1,1,4,"RFID");
			msWordManager.putTxtToCell(1,1,5,"受伤类型");
			msWordManager.putTxtToCell(1,1,6,"受伤时间");
			msWordManager.putTxtToCell(1,1,7,"受伤地点");
			msWordManager.putTxtToCell(1,2,1,patient.getName());
			if(patient.getSex()==1){
				msWordManager.putTxtToCell(1,2,2,"男");
			}else{
				msWordManager.putTxtToCell(1,2,2,"女");
			}
			msWordManager.putTxtToCell(1,2,3,String.valueOf(patient.getAge()));
			msWordManager.putTxtToCell(1,2,4,patient.getRfid());
			msWordManager.putTxtToCell(1,2,5,patient.getWoundType());
			msWordManager.putTxtToCell(1,2,6,patient.getWoundTime());
			msWordManager.putTxtToCell(1,2,7,patient.getWoundAddr());
			msWordManager.putTxtToCell(1,3,7,null);
			msWordManager.moveEnd();
			

 //* ******************************表2 手术记录*************************
			
			msWordManager.insertFormatStr("手术记录", "黑体", 13, "0,0,0,0", true, false, 3);
			msWordManager.moveDown(1);
			List<Operation> operation =  this.operationManager.getAllOperationBypid(id);
			
			msWordManager.setFont(false, false, false, "10", "宋体");
			msWordManager.createTable("table2",6,operation.size()+2);
			msWordManager.putTxtToCell(2,1,1,"手术编号");
			msWordManager.putTxtToCell(2,1,2,"手术医生");
			msWordManager.putTxtToCell(2,1,3,"手术时间");
			msWordManager.putTxtToCell(2,1,4,"手术内容");
			msWordManager.putTxtToCell(2,1,5,"补充说明");
			msWordManager.putTxtToCell(2,1,6,"手术状态");
			int operationcount=2;
			for(Operation o:operation){			
				msWordManager.putTxtToCell(2,operationcount,1,String.valueOf(o.getId()));
				msWordManager.putTxtToCell(2,operationcount,2,String.valueOf(o.getDid()));
				msWordManager.putTxtToCell(2,operationcount,3,String.valueOf(o.getTime()));
				msWordManager.putTxtToCell(2,operationcount,4,o.getContent());
				msWordManager.putTxtToCell(2,operationcount,5,o.getIllustration());
				msWordManager.putTxtToCell(2,operationcount,6,o.getStatus());
				operationcount++;
			}
			
			msWordManager.putTxtToCell(2,operation.size()+2,6,null);
			msWordManager.moveEnd();
			
			
// * ******************************表3 病情记录************Dao中设计了最多现实10条记录************* 		
			
			
			msWordManager.insertFormatStr("病情记录", "黑体", 13, "0,0,0,0", true, false, 3);
			msWordManager.moveDown(1);
			List<PCondition> condition =  this.pconditionManager.getConditions(id);
			msWordManager.setFont(false, false, false, "10", "宋体");
			msWordManager.createTable("table3",8,condition.size()+2);
			msWordManager.putTxtToCell(3,1,1,"病情编号");
			msWordManager.putTxtToCell(3,1,2,"脉搏");
			msWordManager.putTxtToCell(3,1,3,"呼吸频率");
			msWordManager.putTxtToCell(3,1,4,"舒张压");
			msWordManager.putTxtToCell(3,1,5,"收缩压");
			msWordManager.putTxtToCell(3,1,6,"体温");
			msWordManager.putTxtToCell(3,1,7,"病情描述");
			msWordManager.putTxtToCell(3,1,8,"时间");
			
			int conditioncount=2;
			for(PCondition c:condition){			
				msWordManager.putTxtToCell(3,conditioncount,1,String.valueOf(c.getId()));
				msWordManager.putTxtToCell(3,conditioncount,2,String.valueOf(c.getPulse()));
				msWordManager.putTxtToCell(3,conditioncount,3,String.valueOf(c.getBreath()));
				msWordManager.putTxtToCell(3,conditioncount,4,String.valueOf(c.getDiastolic()));
				msWordManager.putTxtToCell(3,conditioncount,5,String.valueOf(c.getSystolic()));
				msWordManager.putTxtToCell(3,conditioncount,6,String.valueOf(c.getTemperature()));
				msWordManager.putTxtToCell(3,conditioncount,7,c.getComment());
				msWordManager.putTxtToCell(3,conditioncount,8,String.valueOf(c.getCondition_time()));
				conditioncount++;
			}
			
			//msWordManager.putTxtToCell(2,condition.size()+2,6,null);
			msWordManager.moveEnd();
			
//* * ******************************表4 病程记录************Dao中设计了最多现实10条记录*************			
			
			msWordManager.insertFormatStr("病程记录", "黑体", 13, "0,0,0,0", true, false, 3);
			msWordManager.moveDown(1);
			List<DisProcess> disProcess =  this.disProcessManager.getDisProcessByPid(id);
			msWordManager.setFont(false, false, false, "10", "宋体");
			msWordManager.createTable("table4",3,disProcess.size()+2);
			msWordManager.putTxtToCell(4,1,1,"病程编号");
			msWordManager.putTxtToCell(4,1,2,"编写日期");
			msWordManager.putTxtToCell(4,1,3,"病程描述");
			int disprocesscount=2;
			for(DisProcess d:disProcess){
				msWordManager.putTxtToCell(4,disprocesscount,1,String.valueOf(d.getId()));
				msWordManager.putTxtToCell(4,disprocesscount,2,d.getAddTime());
				msWordManager.putTxtToCell(4,disprocesscount,3,d.getDescription());
				disprocesscount++;
			}
			//msWordManager.putTxtToCell(2,condition.size()+2,6,null);
			msWordManager.moveEnd();
			
//* * ******************************表5 医嘱************Dao中设计了最多现实10条记录************
					
			msWordManager.insertFormatStr("医嘱", "黑体", 13, "0,0,0,0", true, false, 3);
			msWordManager.moveDown(1);
			List<DocAdvice> docAdvice =  this.docAdviceManager.getDocAdviceByPid(id);
			msWordManager.setFont(false, false, false, "10", "宋体");
			msWordManager.createTable("table5",11,disProcess.size()+2);
			msWordManager.putTxtToCell(5,1,1,"编号");
			msWordManager.putTxtToCell(5,1,2,"医嘱内容");
			msWordManager.putTxtToCell(5,1,3,"医嘱时效");
			msWordManager.putTxtToCell(5,1,4,"医嘱类型");
			msWordManager.putTxtToCell(5,1,5,"医嘱状态");
			msWordManager.putTxtToCell(5,1,6,"开始时间");
			msWordManager.putTxtToCell(5,1,7,"结束时间");
			msWordManager.putTxtToCell(5,1,8,"剂量");
			msWordManager.putTxtToCell(5,1,9,"途径");
			msWordManager.putTxtToCell(5,1,10,"频次");
			msWordManager.putTxtToCell(5,1,11,"医生说明");
			int docadvicecount = 2;
			for(DocAdvice d : docAdvice){
				msWordManager.putTxtToCell(5,docadvicecount,1,String.valueOf(d.getId()));
				msWordManager.putTxtToCell(5,docadvicecount,2,d.getContent());
				msWordManager.putTxtToCell(5,docadvicecount,3,d.getType());
				msWordManager.putTxtToCell(5,docadvicecount,4,d.getType2());
				msWordManager.putTxtToCell(5,docadvicecount,5,d.getState());
				msWordManager.putTxtToCell(5,docadvicecount,6,d.getStartTime());
				msWordManager.putTxtToCell(5,docadvicecount,7,d.getEndTime());
				msWordManager.putTxtToCell(5,docadvicecount,8,d.getDose());
				msWordManager.putTxtToCell(5,docadvicecount,9,d.getUsage());
				msWordManager.putTxtToCell(5,docadvicecount,10,d.getFrequency());
				msWordManager.putTxtToCell(5,docadvicecount,11,d.getSpec());
				docadvicecount++;
			}
			msWordManager.moveEnd();
			
			
//* * ******************************表6 处置报告************Dao中设计了最多现实10条记录************			
				
			msWordManager.insertFormatStr("处置报告", "黑体", 13, "0,0,0,0", true, false, 3);
			msWordManager.moveDown(1);
			List<Disposal> disposal =  this.disposalManager.getDisposalsA(id);
			
			msWordManager.setFont(false, false, false, "10", "宋体");
			msWordManager.createTable("table6",4,disposal.size()+2);
			msWordManager.putTxtToCell(6,1,1,"编号");
			msWordManager.putTxtToCell(6,1,2,"处置内容");
			msWordManager.putTxtToCell(6,1,3,"类型");
			msWordManager.putTxtToCell(6,1,4,"时间");
			int disposalcount=2;
			for(Disposal d:disposal){
				Measure measure = this.measureManager.getMeasureById(d.getMid());
				msWordManager.putTxtToCell(6,disposalcount,1,String.valueOf(d.getId()));
				msWordManager.putTxtToCell(6,disposalcount,2,measure.getContent());
				msWordManager.putTxtToCell(6,disposalcount,3,d.getType());
				msWordManager.putTxtToCell(6,disposalcount,4,d.getTime());
				disposalcount++;
			}
			msWordManager.moveEnd();
			
			
//* * ******************************表7 化验结果************Dao中设计了最多现实10条记录************					
			msWordManager.insertFormatStr("检查化验", "黑体", 13, "0,0,0,0", true, false, 3);
			msWordManager.moveDown(1);
			List<Edetail> edetail =  this.edetailManager.getEdetail(id);
			msWordManager.setFont(false, false, false, "10", "宋体");
			msWordManager.createTable("table7",11,edetail.size()+2);
			msWordManager.putTxtToCell(7,1,1,"编号");
			msWordManager.putTxtToCell(7,1,2,"项目");
			msWordManager.putTxtToCell(7,1,3,"中文名称");
			msWordManager.putTxtToCell(7,1,4,"结果");
			msWordManager.putTxtToCell(7,1,5,"标志");
			msWordManager.putTxtToCell(7,1,6,"参考范围");
			msWordManager.putTxtToCell(7,1,7,"单位");
			msWordManager.putTxtToCell(7,1,8,"最小值");
			msWordManager.putTxtToCell(7,1,9,"最大值");
			msWordManager.putTxtToCell(7,1,10,"状态");
			msWordManager.putTxtToCell(7,1,11,"时间");
			int edetailcount=2;
			for(Edetail e : edetail){
				msWordManager.putTxtToCell(7,edetailcount,1,String.valueOf(e.getId()));
				msWordManager.putTxtToCell(7,edetailcount,2,e.getItem());
				msWordManager.putTxtToCell(7,edetailcount,3,e.getItemStr());
				msWordManager.putTxtToCell(7,edetailcount,4,String.valueOf(e.getResultNum()));
				msWordManager.putTxtToCell(7,edetailcount,5,e.getFlag());
				msWordManager.putTxtToCell(7,edetailcount,6,e.getScope());
				msWordManager.putTxtToCell(7,edetailcount,7,e.getUnit());
				msWordManager.putTxtToCell(7,edetailcount,8,String.valueOf(e.getMin()));
				msWordManager.putTxtToCell(7,edetailcount,9,String.valueOf(e.getMax()));
				msWordManager.putTxtToCell(7,edetailcount,10,e.getStatus());
				msWordManager.putTxtToCell(7,edetailcount,11,String.valueOf(e.getTime()));
				edetailcount++;
			}
			msWordManager.moveEnd();
	
//* * ******************************表8影像查看************************		
		
			//获取webapps路径
			String loc= getRequest().getSession().getServletContext().getRealPath("");
						
			msWordManager.insertFormatStr("影像查看", "黑体", 13, "0,0,0,0", true, false, 3);
			msWordManager.moveDown(1);
			List<Inspect> inspect =  this.inspectManager.getAllInspectBypid(id);
			msWordManager.setFont(false, false, false, "10", "宋体");
			msWordManager.createTable("table7",4,inspect.size()+2);
			msWordManager.putTxtToCell(8,1,1,"编号");	
			msWordManager.putTxtToCell(8,1,2,"申请医生");	
			msWordManager.putTxtToCell(8,1,3,"申请内容");	
			msWordManager.putTxtToCell(8,1,4,"图像");	
			int inspectcount=2;
			for(Inspect i:inspect){
				msWordManager.putTxtToCell(8,inspectcount,1,String.valueOf(i.getId()));
				msWordManager.putTxtToCell(8,inspectcount,2,String.valueOf(i.getDid()));
				
				//切割调整字符串
				msWordManager.putTxtToCell(8,inspectcount,3,i.getContent());
				String a = i.getUrl();	
				if(a.length()<=2){
					msWordManager.putTxtToCell(8,inspectcount,4,"无图像数据");
				}
				else{
				String b =a.substring(2);								
				String path = b.replaceAll("/","\\\\");
				//msWordManager.putTxtToCell(8,inspectcount,4,loc+path);
				msWordManager.moveRight(1);
				//msWordManager.insertImage(loc+path);
				}
				inspectcount++;
				
			}
			msWordManager.moveEnd();
			
	
			
			msWordManager.insertFormatStr("END", "黑体", 20, "0,0,0,0", true, false, 3);
			msWordManager.moveDown(1);
			
//******************************** 结束 ****************************************				
			
			
			msWordManager.save("D:/武警医院转院报告单.doc");
		//msWordManager.openDocument("E:/xiantong.doc");
			this.result.setSuccess(true);
			System.out.println("success");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
			//msWordManager.closeDocument();
		}	
			
		   	
		}
}
