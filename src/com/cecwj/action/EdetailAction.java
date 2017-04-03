package com.cecwj.action;

import com.cecwj.model.Apply;
import com.cecwj.model.Edetail;
import com.cecwj.model.serialize.JsonBase;
import com.cecwj.service.ApplyManager;
import com.cecwj.service.EdetailManager;
import java.util.List;
import javax.annotation.Resource;

public class EdetailAction extends BaseAction {
	private EdetailManager edetailManager;
	private ApplyManager applyManager;
	private int id;
	private int resultNum;
	private String flag;
	private String scope;
	private String unit;
	private float min;
	private float max;
	private int applyId;

	public EdetailManager getEdetailManager() {
		return this.edetailManager;
	}

	@Resource
	public void setEdetailManager(EdetailManager edetailManager) {
		this.edetailManager = edetailManager;
	}

	public ApplyManager getApplyManager() {
		return applyManager;
	}

	public void setApplyManager(ApplyManager applyManager) {
		this.applyManager = applyManager;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getResultNum() {
		return resultNum;
	}

	public void setResultNum(int resultNum) {
		this.resultNum = resultNum;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public float getMin() {
		return min;
	}

	public void setMin(float min) {
		this.min = min;
	}

	public float getMax() {
		return max;
	}

	public void setMax(float max) {
		this.max = max;
	}

	public int getApplyId() {
		return applyId;
	}

	public void setApplyId(int applyId) {
		this.applyId = applyId;
	}

	public String getEdetail() {
		this.result = new JsonBase();
		int page = 1;
		try {
			page = Integer.parseInt(getRequest().getParameter("page"));
		} catch (Exception e) {
			this.log.error(e);
		}
		int mid = -1;
		try {
			String s = getRequest().getParameter("id");
			if (s != null) {
				mid = Integer.parseInt(s);
			} else {
				mid = -1;
			}
		} catch (Exception e) {
			this.log.error(e);
		}
		if (mid == -1) {
			this.result.setMsg("病人id不正确！");
			this.result.setSuccess(true);
			this.result.setTotal(0);
		} else {
			try {
				List<com.cecwj.model.Edetail> exs = this.edetailManager
						.getEdetail(page, mid);
				int total = exs.size();
				this.result.setResults(exs);
				this.result.setSuccess(true);
				this.result.setTotal(total);
			} catch (Exception e) {
				this.result.setSuccess(false);
				this.result.setMsg("获取检查检验结果失败");
				e.printStackTrace();
			}
		}

		return "OK";
	}

	public String UpdataEdetail() {
		this.result = new JsonBase();
		if ((this.id == 0) || (this.id < 0)) {
			this.result.setMsg("请选择要录入的检查项目！");
			this.result.setSuccess(false);
			log.error("检查项目id不正确");
		} else {
			Edetail edetail = this.edetailManager.getEdetailById(this.id);

			if (this.max == 0 || this.max < 0) {
				this.result.setMsg("增加失败，请输入参考值！");
				this.result.setSuccess(false);
			} else 
				edetail.setMax(this.max);
			if (this.min == 0 || this.min < 0) {
				this.result.setMsg("增加失败，请输入参考值！");
				this.result.setSuccess(false);
			} else
				edetail.setMin(this.min);
				if (this.resultNum == 0 || this.resultNum < 0) {
				this.result.setMsg("增加失败，请输入检查结果值！");
				this.result.setSuccess(false);
			}else
				edetail.setResultNum(this.resultNum);
				
			if (this.scope != null)
				edetail.setScope(this.scope);
			if (this.unit != null)
				edetail.setUnit(this.unit);
			try {
				edetail.setStatus("已完成");
				this.edetailManager.update(edetail);
				this.result.setResults(edetail);
				this.result.setMsg("录入检查结果成功");
				
				List<Apply> applyExam = this.applyManager.getAllApplyByPid(edetail.getMid(),Apply.TypeA.EXAMINE);
				for(Apply apply :applyExam){
					List<Edetail>  edetails = this.edetailManager.getEdetailBypid(apply.getPid(),apply.getId());										
					if(edetails.size()!=0){
					
					}else{
						apply.setStatus("已完成");
						this.applyManager.update(apply);
					}
				}
				
			} catch (Exception e) {
				this.result.setMsg("录入检查结果失败");
				this.result.setSuccess(false);
				log.error(e);
			}
		}
		return OK;

	}

}
