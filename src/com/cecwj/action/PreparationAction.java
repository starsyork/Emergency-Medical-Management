package com.cecwj.action;

import com.cecwj.common.PinyinTool;
import com.cecwj.model.Apply;
import com.cecwj.model.Edetail;
import com.cecwj.model.Inspect;
import com.cecwj.model.Operation;
import com.cecwj.model.Preparation;
import com.cecwj.model.Section;
import com.cecwj.model.Transport;
import com.cecwj.model.serialize.JsonBase;
import com.cecwj.service.BedManager;
import com.cecwj.service.PreparationManager;
import com.cecwj.service.SectionManager;
import com.cecwj.service.UserManager;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PreparationAction extends BaseAction {
	private PreparationManager preparationManager;
	private int id;
	private Integer pid;
	private Integer applyId;
	private String content;
	private String drugname;
	private Integer amount;

	public PreparationManager getPreparationManager() {
		return preparationManager;
	}

	public void setPreparationManager(PreparationManager preparationManager) {
		this.preparationManager = preparationManager;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getApplyId() {
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getDrugname() {
		return drugname;
	}

	public void setDrugname(String drugname) {
		this.drugname = drugname;
	}

	public String AddPreparation() {
		this.result = new JsonBase();
		if (this.applyId == 0) {
			this.result.setMsg("增加失败，请选择申请项目！");
			this.result.setSuccess(false);
		} else if (this.drugname == null) {
			this.result.setMsg("增加失败，请输入项目名称！");
			this.result.setSuccess(false);
		} else if (this.amount == null) {
			this.result.setMsg("增加失败，请输入需求数量！");
			this.result.setSuccess(false);
		} else if (this.content == null) {
			this.result.setMsg("增加失败，请输入手术名称！");
			this.result.setSuccess(false);
		}
		Preparation preparation = new Preparation();
		preparation.setAmount(this.amount);
		preparation.setDrugname(this.drugname);
		preparation.setApplyId(this.applyId);
		preparation.setPid(this.id);
		preparation.setContent(this.content);
		// preparation.setPid(this.pid);
		try {
			this.preparationManager.add(preparation);

		} catch (Exception e) {
			System.out.print(e);
			this.result.setMsg("增加失败！");
			this.result.setSuccess(false);
		}

		return OK;
	}

	public String getPreparation() {
		this.result = new JsonBase();
	//	if (this.id == 0) {
		//	this.result.setMsg("获取失败，请选择申请项目！");
		//	this.result.setSuccess(true);
	//	}else{
		try {
			List<Preparation> preparation = this.preparationManager
					.getPreparation(this.id);
			this.result.setResults(preparation);
			this.result.setSuccess(true);
		} catch (Exception e) {
			System.out.print(e);
			this.result.setMsg("获取失败！");
			this.result.setSuccess(false);
		}
	//	}
		return OK;
	}

}
