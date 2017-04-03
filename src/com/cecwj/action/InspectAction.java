package com.cecwj.action;

import com.cecwj.model.Apply;
import com.cecwj.model.DisProcess;
import com.cecwj.model.Edetail;
import com.cecwj.model.Inspect;
import com.cecwj.model.Operation;
import com.cecwj.model.Preparation;
import com.cecwj.model.Section;
import com.cecwj.model.Transport;
import com.cecwj.model.serialize.JsonBase;
import com.cecwj.service.ApplyManager;
import com.cecwj.service.BedManager;
import com.cecwj.service.EdetailManager;
import com.cecwj.service.EpidemicManager;
import com.cecwj.service.InspectManager;
import com.cecwj.service.OperationManager;
import com.cecwj.service.PreparationManager;
import com.cecwj.service.SectionManager;
import com.cecwj.service.TransportManager;
import com.cecwj.service.UserManager;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InspectAction extends BaseAction {
	private InspectManager inspectManager;	
	private TransportManager transportManager;
	private OperationManager operationManager;
	private PreparationManager preparationManager;
	private ApplyManager applyManager; 
	private EdetailManager edetailManager;
	private Integer id;
	private int did;
	private Integer pid;//病人ID
	
	private Integer applyId;//申请编号
	private String content;//检查项目
	private Timestamp time;//检查时间
	private String illustration;//说明
	private String url;//图像数据存储位置
	private String delurl;//图像数据存储位置
	private Integer amount;
	private String filename;
	private String fileContentType;
	private ServletContext servletContext;
	private File uploadDoc;
	private String uploadDocFileName;
	static Log log = LogFactory.getLog(InspectAction.class);

	
	
	
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
	
	public PreparationManager getPreparationManager() {
		return preparationManager;
	}

	public void setPreparationManager(PreparationManager preparationManager) {
		this.preparationManager = preparationManager;
	}

	
	




	public EdetailManager getEdetailManager() {
		return edetailManager;
	}

	public void setEdetailManager(EdetailManager edetailManager) {
		this.edetailManager = edetailManager;
	}

	public ApplyManager getApplyManager() {
		return applyManager;
	}

	public void setApplyManager(ApplyManager applyManager) {
		this.applyManager = applyManager;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public int getApplyId() {
		return applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getIllustration() {
		return illustration;
	}

	public void setIllustration(String illustration) {
		this.illustration = illustration;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}



	public String getDelurl() {
		return delurl;
	}

	public void setDelurl(String delurl) {
		this.delurl = delurl;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	public String getSavePath() {
		return getRequest().getRealPath("/Inspect");
	}
	public File getUploadDoc() {
		return uploadDoc;
	}

	public void setUploadDoc(File uploadDoc) {
		this.uploadDoc = uploadDoc;
	}

	public String getUploadDocFileName() {
		return uploadDocFileName;
	}

	public void setUploadDocFileName(String uploadDocFileName) {
		this.uploadDocFileName = uploadDocFileName;
	}



	public String editInspect() {
		this.result = new JsonBase();
		int flag= 0;
		if ((this.id == null) || (this.id.intValue() < 0)) {
			this.result.setMsg("请选择要更新的检查内容！");
			this.result.setSuccess(false);
			this.log.error("病程id不正确");
		} else {
			String a = getUploadDocFileName();
			try {
				
				Inspect inspect = this.inspectManager.getinspectById(this.id.intValue());
				String oldpath = inspect.getUrl();			
				if ((this.content != null)&& (!this.content.equals("")))
					inspect.setContent(this.content);
				if ((this.illustration != null)&& (!this.illustration.equals(""))){
					inspect.setIllustration(this.illustration);
				
				}
				Date date= new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-ddHH-mm-ss"); 
				String str=sdf.format(date); 
				String filePath = upload(this.uploadDoc, this.id.intValue(), inspect.getDid(), inspect.getPid(),str);
				if (filePath == null) {
					filePath = oldpath;
				}else if (oldpath != null&&!oldpath.equals("")){
					filePath=filePath+";"+oldpath;					
				}else{
					filePath=filePath;					
				}
				System.out.println(filePath);
				inspect.setUrl(filePath);
				inspect.setStatus("已完成");
				this.inspectManager.update(inspect);
				List<Apply> applyIns = this.applyManager.getAllApplyByPid(inspect.getPid(),Apply.TypeA.INSPECTION);
				for(Apply apply :applyIns){					
					List<Inspect>  inspects = this.inspectManager.getInspectBypid(apply.getPid(),apply.getId());										
					if(inspects.size()!=0){
						flag = 1;
					}else{
						apply.setStatus("已完成");
						this.applyManager.update(apply);
					}
				}
				
				this.result.setResults(inspect);				
				this.result.setSuccess(true);
				this.result.setMsg(inspect.getUrl());
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

	public String upload(File f, int num, int did, int pid,String asd) {
		if (f != null) {
			int n1 = this.uploadDocFileName.lastIndexOf(".");
			String geshi1 = this.uploadDocFileName.substring(n1);
			
			String fileName1 = num + "D" + did + "P" + pid +asd+ geshi1;
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
			String fileName = num + "D" + did + "P" + pid +asd+ geshi;
			file.renameTo(new File(getSavePath() + "/" + fileName));
			
			String filePath = "../Inspect/" + fileName;
			return filePath;
		}
		return null;
	}
	
	/*	
	public String addDisProcess() {
		if (!authorized("id")) {
			this.result = new JsonBase();
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
			this.log.info("获取当前用户失败");
			return "OK";
		}
		this.result = new JsonBase();
		if ((this.pid == null) || (this.pid.intValue() < 0)) {
			this.result.setMsg("请选择病人！");
			this.result.setSuccess(false);
			this.log.error("病人id不正确");
		} else {
			if ((this.content == null) || (this.content.equals(""))) {
				this.result.setMsg("添加失败，请输入检查内容！");
				this.result.setSuccess(false);
			}
			try {
				Inspect inspect = new Inspect();
				inspect.setPid(this.id.intValue());
				int did = ((Integer) getSession().get("id")).intValue();				
				inspect.setDid(did);				
				inspect.setContent(this.content);
				this.inspectManager.add(inspect);
				
				this.result.setSuccess(true);
				this.result.setMsg("新增病程成功");
				this.log.info("新增病程成功，disProcesspid=" + inspect.getId());
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
	
*/	
	
	public String editTransport() {		
		if (!authorized("id")) {
			this.result = new JsonBase();
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
			this.log.info("获取当前用户失败");
			return "OK";
		}
		this.result = new JsonBase();		
		if ((this.id == null) || (this.id.intValue() < 0)) {
			this.result.setMsg("请选择要更新的转运信息！");
			this.result.setSuccess(false);
			this.log.error("转运id不正确");
		}else {
			Transport transport = this.transportManager.gettransportById(this.id.intValue());
			if ((this.content != null) || (!this.content.equals(""))) 
				transport.setContent(this.content);
			if ((this.illustration != null) || (!this.illustration.equals(""))) 
				transport.setContent(this.illustration);
			Date  time2 = new Date();
			Timestamp now = new Timestamp(time2.getTime());
				transport.setTime(now);
				try {	
				this.transportManager.update(transport);					
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
	
	public String deleteImg(){
		
//		if (!authorized("id")) {
//			this.result = new JsonBase();
//			this.result.setMsg("statusfailed");
//			this.result.setSuccess(false);
//			this.log.info("获取当前用户失败");
//			return "OK";
//		}
		this.result = new JsonBase();		
		if ((this.id == null) || (this.id.equals(""))) {
			this.result.setMsg("请选择申请编号");
			this.result.setSuccess(false);			
		}else if ((this.delurl == null) || (this.delurl.equals(""))) {
			this.result.setMsg("请选择要删除的图片");
			this.result.setSuccess(false);			
		}else{			
			try{
			Inspect inspect = this.inspectManager.getinspectById(this.id.intValue());
			String OldUrl = inspect.getUrl();
			String[] sourceStrArray = OldUrl.split(";");
	        for (int i = 0; i < sourceStrArray.length; i++) {
	            System.out.println(sourceStrArray[i]);
	            if(this.delurl .equals(sourceStrArray[i])){
	            	sourceStrArray[i]="";
	            }	            
	        }
	        String  Url = "";
	        for (int i = 0; i < sourceStrArray.length; i++) {
	            
	           if(sourceStrArray[i]!=null&&!sourceStrArray[i].equals("")){
	        	   System.out.println(sourceStrArray[i]);
		            if (Url == null||Url.equals("")){
		            	Url = sourceStrArray[i];
					}else {
						Url =Url+";"+sourceStrArray[i];				
					}  
	            }           
	        } 
	        System.out.println(Url);    	
	        inspect.setUrl(Url);
	        this.inspectManager.update(inspect);
	        this.result.setMsg("删除成功");
	        this.result.setResults(inspect);
	        this.result.setSuccess(true);
	        }catch(Exception e){
	        	System.out.println(e);
	        	this.result.setMsg("删除失败");
	        	this.result.setSuccess(false);
	        }
		}
	
	
		
		return "OK";
	}
	
	
	
	
	
	
	
	
	
	public String editOperation() {		
		if (!authorized("id")) {
			this.result = new JsonBase();
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
			this.log.info("获取当前用户失败");
			return "OK";
		}
		this.result = new JsonBase();		
		if ((this.id == null) || (this.id.intValue() < 0)) {
			this.result.setMsg("请选择要更新的转运信息！");
			this.result.setSuccess(false);
			this.log.error("转运id不正确");
		}else {
			Operation operation = this.operationManager.getoperationById(this.id.intValue());
			if ((this.content != null) || (!this.content.equals(""))) 
				operation.setContent(this.content);
			if ((this.illustration != null) || (!this.illustration.equals(""))) 
				operation.setContent(this.illustration);
			Date  time2 = new Date();
			Timestamp now = new Timestamp(time2.getTime());
			operation.setTime(now);
				try {	
				this.operationManager.update(operation);					
				this.result.setSuccess(true);
				this.result.setMsg("修改手术信息成功");
				this.log.info("修改手术信息成功");
			} catch (Exception e) {
				this.result.setMsg("修改手术信息失败");
				this.result.setSuccess(false);
				this.log.error("修改手术信息失败");
			}
		}
		
		
		return OK;
	}

	
	public String addPreparation(){
		this.result = new JsonBase();
		if(this.pid == null&&this.pid.equals(""))
		{
			this.result.setMsg("病人信息获取失败，术前准本添加失败");
			this.result.setSuccess(false);
		}else if (this.applyId==null&&this.applyId.equals("")){
			this.result.setMsg("手术信息获取失败，术前准本添加失败");
			this.result.setSuccess(false);
		}else if (this.content == null && this.content.equals("")){
			this.result.setMsg("准备内容信息获取失败，术前准本添加失败");
			this.result.setSuccess(false);
		}else if(this.amount ==null&&this.amount.equals("")){
			this.result.setMsg("准备数量获取失败，术前准本添加失败");
			this.result.setSuccess(false);		
		}else{
			Preparation preparation = new Preparation();
			preparation.setPid(this.pid);
			preparation.setApplyId(this.applyId);					
			//preparation.setContent(this.content);
			preparation.setAmount(this.amount);
			try{
				this.preparationManager.add(preparation);
				this.result.setMsg("添加成功");
				this.result.setSuccess(true);	
			}catch (Exception e){
				this.result.setMsg("术前准本添加失败");
				this.result.setSuccess(false);	
			}
		}			
		return OK;
	}

}
