package com.cecwj.action;

import com.cecwj.common.PinyinTool;
import com.cecwj.common.TimeFormat;
import com.cecwj.model.Dict;
import com.cecwj.model.DocAdvice;
import com.cecwj.model.Epidemic;
import com.cecwj.model.TermAll;
import com.cecwj.model.serialize.DrugAndUnit;
import com.cecwj.model.serialize.JsonBase;
import com.cecwj.model.Volunteer;
import com.cecwj.service.DictManager;
import com.cecwj.service.TermAllManager;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TermAction extends BaseAction {
	private TermAllManager termAllManager;
	private DictManager dictManger;
	private String name;
	private String term;
	private String content;
	private String type2;
	private int id;
	private int amount;
	private int need;
	private Timestamp entrytime;
	private String spec;
	private String drugcode;

	private String units;
	private String doseType;
	private String property;
	private float mixUnit;
	private String doseUnit;
	private String code;
	private float indicator;

	public String getDrugcode() {
		return drugcode;
	}

	public void setDrugcode(String drugcode) {
		this.drugcode = drugcode;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getDoseType() {
		return doseType;
	}

	public void setDoseType(String doseType) {
		this.doseType = doseType;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public float getMixUnit() {
		return mixUnit;
	}

	public void setMixUnit(float mixUnit) {
		this.mixUnit = mixUnit;
	}

	public String getDoseUnit() {
		return doseUnit;
	}

	public void setDoseUnit(String doseUnit) {
		this.doseUnit = doseUnit;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public float getIndicator() {
		return indicator;
	}

	public void setIndicator(float indicator) {
		this.indicator = indicator;
	}

	static Log log = LogFactory.getLog(TermAction.class);

	public TermAllManager getTermAllManager() {
		return this.termAllManager;
	}

	@Resource
	public void setTermAllManager(TermAllManager termAllManager) {
		this.termAllManager = termAllManager;
	}

	public DictManager getDictManger() {
		return this.dictManger;
	}

	@Resource
	public void setDictManger(DictManager dictManger) {
		this.dictManger = dictManger;
	}

	public String getTerm() {
		return this.term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getType2() {
		return this.type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getEntrytime() {
		return entrytime;
	}

	public void setEntrytime(Timestamp entrytime) {
		this.entrytime = entrytime;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getNeed() {
		return this.need;
	}

	public void setNeed(int need) {
		this.need = need;
	}

	public String getTerms() {
		this.result = new JsonBase();
		if (this.term != null) {
			try {
				this.term = new String(this.term.getBytes("ISO-8859-1"),
						"utf-8");
			} catch (UnsupportedEncodingException e1) {
				this.term = null;
			}
		}

		if (this.type2 != null) {
			try {
				this.type2 = new String(this.type2.getBytes("ISO-8859-1"),
						"utf-8");
			} catch (UnsupportedEncodingException e1) {
				this.type2 = null;
			}
		}
		if (this.type2 != null) {
			if (this.type2.equals("药疗")) {
				if(this.term==null&&this.term.equals("")){
					List<TermAll> ts = this.termAllManager.getAllDrugA();
					List<DrugAndUnit> du = new ArrayList();
					for (TermAll termAll : ts) {
						DrugAndUnit drug = new DrugAndUnit(termAll);
						du.add(drug);
					}
					this.result.setResults(du);
					this.result.setSuccess(true);
					this.result.setTotal(du.size());
					}else{
				List<TermAll> ts = this.termAllManager.getTerms(this.term);
				List<DrugAndUnit> du = new ArrayList();
				for (TermAll termAll : ts) {
					DrugAndUnit drug = new DrugAndUnit(termAll);
					du.add(drug);
				}
				this.result.setResults(du);
				this.result.setSuccess(true);
				this.result.setTotal(du.size());
				}
			
			} else {
				if(this.term==null&&this.term.equals("")){
					List<Dict> ts = this.dictManger.getDicts(this.type2);
					List<DrugAndUnit> du = new ArrayList();
					for (Dict dict : ts) {
						DrugAndUnit drug = new DrugAndUnit(dict);
						du.add(drug);
					}
					this.result.setResults(du);
					this.result.setSuccess(true);
					this.result.setTotal(du.size());
					}else{
				List<Dict> ds = this.dictManger.getDicts(this.term, this.type2);
				List<DrugAndUnit> du = new ArrayList();
				for (Dict dict : ds) {
					DrugAndUnit drug = new DrugAndUnit(dict);
					du.add(drug);
				}
				this.result.setResults(du);
				this.result.setSuccess(true);
				this.result.setTotal(du.size());
			}
		}
		}
		return "OK";
	}

	public String getUnit() {
		this.result = new JsonBase();
		if (this.content != null) {
			try {
				this.content = new String(this.content.getBytes("ISO-8859-1"),
						"utf-8");
			} catch (UnsupportedEncodingException e1) {
				this.content = null;
			}
		}
		if (this.type2 != null) {
			try {
				this.type2 = new String(this.type2.getBytes("ISO-8859-1"),
						"utf-8");
			} catch (UnsupportedEncodingException e1) {
				this.type2 = null;
			}
		}
		if ((this.content != null) && (!this.content.equals(""))) {
			if ((this.type2 != null) && (this.type2.equals("药疗"))) {
				TermAll ta = this.termAllManager
						.getTermsByContent(this.content);
				if (ta != null) {
					 DrugAndUnit du = new DrugAndUnit(ta);
					// this.result.setResults(du);
					this.result.setSuccess(true);
				}
			} else {
				this.result.setSuccess(true);
			}
		}
		return "OK";
	}

	public String getAllDrug() {
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
				List<TermAll> da1 = this.termAllManager.getAllDrugA();
				List<TermAll> da3 = new ArrayList();
				if (page != -1) {					
						for (int i = (page - 1) * limit; (i < da1.size())
								&& (i < page * limit); i++) {
							da3.add((TermAll) da1.get(i));
						}
						int total = da1.size();
						this.result.setResults(da3);
						this.result.setSuccess(true);
						this.result.setTotal(total);					
				} else {
					int total = da1.size();
					this.result.setResults(da1);
					this.result.setSuccess(true);
					this.result.setTotal(total);
				}
			} catch (Exception e) {
				System.out.println(e);
				this.result.setMsg("请检查网络链接是否正常！");
				this.result.setSuccess(false);
				log.error(e);
			}		
		return "OK";
	}

	public String getAllDrugA() {
		this.result = new JsonBase();
		this.termAllManager.getAllDrug(this.result);
		return OK;

	}

	public String UpdataDrug() {
		this.result = new JsonBase();
		if ((this.id <= 0)) {
			this.result.setMsg("请选择药品！");
			this.result.setSuccess(false);
			log.error("药品id不正确");
		} else {
			TermAll termall = this.termAllManager.getDrugById(this.id);
			if (this.name != null)
				termall.setName(this.name);
			if (!(this.amount == 0))
				termall.setAmount(this.amount);
			if (!(this.need == 0))
				termall.setNeed(this.need);
			try {
				this.termAllManager.update(termall);
			} catch (Exception e) {
				System.out.print(e);
				this.result.setMsg("修改信息失败");
				this.result.setSuccess(false);
				log.error("用户修改信息失败");
			}
		}
		return "OK";
	}

	public String UpdataNeed() {
		this.result = new JsonBase();
		if ((this.id == 0)) {
			this.result.setMsg("请选择yaopin！");
			this.result.setSuccess(false);
			log.error("志愿者id不正确");
		} else {
			TermAll termall = this.termAllManager.getDrugById(this.id);
			if (!(this.need == 0)) {
				termall.setAmount(this.need);
				try {
					this.termAllManager.update(termall);
				} catch (Exception e) {
					this.result.setMsg("修改信息失败");
					this.result.setSuccess(false);
					log.error("用户修改信息失败");
				}
			}
		}
		return "OK";
	}

	public String DeleteDrug() {
		this.result = new JsonBase();

		if (this.id <= 0) {
			this.result.setMsg("请选择要删除药品");
			this.result.setSuccess(false);
			log.error("药品id不正确");
		} else {
			try {
				TermAll termall = new TermAll();
				termall = this.termAllManager.getDrugById(id);
				this.termAllManager.delete(termall);
			} catch (Exception e) {
				log.error(e);
				this.result.setMsg("删除失败，请检查网络");
				this.result.setSuccess(false);
				return "OK";
			}

		}
		return "OK";
	}

	public String AddDrug() {
		this.result = new JsonBase();
		if ((this.drugcode == null)) {
			this.result.setMsg("增加失败，请输入疫情名！");
			this.result.setSuccess(false);
		} else if ((this.name == null)) {
			this.result.setMsg("增加失败，请输入疫情名！");
			this.result.setSuccess(false);
		} else if ((this.spec == null)) {
			this.result.setMsg("增加失败，请输入疫情严重程度！");
			this.result.setSuccess(false);
		} else if ((this.units == null)) {
			this.result.setMsg("增加失败，请输入疫情处治方法");
			this.result.setSuccess(false);
		} else if ((this.doseType == null)) {
			this.result.setMsg("增加失败，请输入疫情处治方法");
			this.result.setSuccess(false);
		} else if ((this.property == null)) {
			this.result.setMsg("增加失败，请输入疫情处治方法");
			this.result.setSuccess(false);
		} else if ((this.doseUnit == null)) {
			this.result.setMsg("增加失败，请输入疫情处治方法");
			this.result.setSuccess(false);
		} else if ((this.mixUnit <= 0)) {
			this.result.setMsg("增加失败，请输入疫情处治方法");
			this.result.setSuccess(false);
		} else if ((this.indicator <= 0)) {
			this.result.setMsg("增加失败，请输入疫情处治方法");
			this.result.setSuccess(false);
		} else if ((this.amount <= 0)) {
			this.result.setMsg("增加失败，请输入疫情处治方法");
			this.result.setSuccess(false);
		} else {
			TermAll termall1 = new TermAll();
			termall1 = this.termAllManager.getDrugByIdA(this.drugcode);
			if (term != null) {
				termall1.setAmount(termall1.getAmount() + this.amount);
				this.termAllManager.update(termall1);
				this.result.setMsg("药品已存在，已更新数量");
				this.result.setSuccess(true);
			} else {
				TermAll termall = new TermAll();
				termall.setAmount(this.amount);
				termall.setDoseType(this.doseType);
				termall.setDoseUnit(this.doseUnit);
				termall.setDrugcode(this.drugcode);
				termall.setIndicator(this.indicator);
				termall.setMixUnit(this.mixUnit);
				termall.setName(this.name);
				PinyinTool tool = new PinyinTool();
				try {
					code = tool.toPinYin(this.name, "",
							PinyinTool.Type.FIRSTUPPER);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				termall.setCode(code);
				termall.setProperty(this.property);
				termall.setSpec(this.spec);
				termall.setUnits(this.units);
				termall.setNeed(300);

				this.termAllManager.add(termall);
				this.result.setMsg("新增药品成功");

			}

		}

		return "OK";
	}

}
