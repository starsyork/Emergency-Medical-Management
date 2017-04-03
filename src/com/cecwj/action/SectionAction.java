             package com.cecwj.action;

import com.cecwj.model.Section;
import com.cecwj.model.serialize.JsonBase;
import com.cecwj.service.BedManager;
import com.cecwj.service.SectionManager;
import com.cecwj.service.UserManager;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SectionAction extends BaseAction {
	private SectionManager sectionManager;
	private UserManager userManager;
	private BedManager bedManager;
	static Log log = LogFactory.getLog(SectionAction.class);
	private int id;
	private String secName;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSecName() {
		return this.secName;
	}

	public void setSecName(String secName) {
		this.secName = secName;
	}

	public SectionManager getSectionManager() {
		return this.sectionManager;
	}

	@Resource
	public void setSectionManager(SectionManager sectionManager) {
		this.sectionManager = sectionManager;
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

	public JsonBase getResult() {
		return this.result;
	}

	public void setResult(JsonBase result) {
		this.result = result;
	}

	public String getAllSection() {
		this.result = new JsonBase();
		List<Section> list = null;
		try {
			list = this.sectionManager.getAllSection();
			this.result.setResults(list);
			this.result.setSuccess(true);
			this.result.setTotal(list.size());
		} catch (Exception e) {
			this.result.setMsg("请检查网络链接是否正常！");
			this.result.setSuccess(false);
			log.error(e);
		}
		return "OK";
	}

	public String addSection() {
		this.result = new JsonBase();
		Section section = new Section();
		try {
			this.secName = new String(this.secName.getBytes("ISO-8859-1"),
					"utf-8");
		} catch (UnsupportedEncodingException e1) {
			this.secName = null;
		}
		if ((this.secName == null) || (this.secName.equals(""))) {
			this.result.setMsg("请输入分区名称");
			this.result.setSuccess(false);
			log.error("请输入分区名称");
		} else if (this.sectionManager.getSectionByName(this.secName) != null) {
			this.result.setMsg("该分区已存在，请重新输入分区名称");
			this.result.setSuccess(false);
			log.error("分区名称重复");
		} else {
			section.setSecName(this.secName);
			try {
				this.sectionManager.addSection(section);
			} catch (Exception e) {
				this.result = new JsonBase("添加分区失败，请稍后重试", false);
				log.error(e);
			}
		}

		return "OK";
	}

	public String deleteSection() {
		this.result = new JsonBase();
		int id = -1;
		try {
			id = Integer.parseInt(getRequest().getParameter("id"));
		} catch (Exception e) {
			log.error(e);
		}
		if (id == -1) {
			this.result.setMsg("请选择要删除的分区");
			this.result.setSuccess(false);
			log.error("分区id不正确");
		} else {
			int num = 0;
			int count = 0;
			try {
				num = this.userManager.getUserTotalBySid(id);
			} catch (Exception e) {
				log.error(e);
				this.result.setMsg("删除失败，请检查网络");
				this.result.setSuccess(false);
				return "OK";
			}
			try {
				count = this.bedManager.getBedsNum(id);
			} catch (Exception e) {
				log.error(e);
				this.result.setMsg("删除失败，请检查网络");
				this.result.setSuccess(false);
				return "OK";
			}
			if (num > 0) {
				this.result.setMsg("该分区还存在用户，不能删除");
				this.result.setSuccess(false);
				log.info("分区存在用户，无法删除");
			} else if (count > 0) {
				this.result.setMsg("该分区还存在床位，不能删除");
				this.result.setSuccess(false);
				log.info("该分区还存在床位，不能删除");
			} else {
				try {
					this.sectionManager.deleteSection(id);
				} catch (Exception e) {
					this.result.setMsg("删除失败，请检查网络连接");
					this.result.setSuccess(false);
					log.error(e);
				}
			}
		}
		return "OK";
	}

	public String modifySector() {
		this.result = new JsonBase();
		int sid = 0;
		String secName = null;
		try {
			sid = Integer.valueOf(getRequest().getParameter("sid")).intValue();
		} catch (Exception e) {
			this.result.setMsg("请选择病区");
			this.result.setSuccess(false);
			return "OK";
		}
		secName = getRequest().getParameter("secName");
		if ((secName == null) || (secName.equals(""))) {
			this.result.setMsg("分区名不能为空");
			this.result.setSuccess(false);
			return "OK";
		}
		try {
			secName = new String(secName.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e1) {
			this.result.setMsg("分区名错误");
			this.result.setSuccess(false);
			return "OK";
		}
		if (this.sectionManager.getSectionByName(secName) != null) {
			this.result.setMsg("该分区已存在，请重新输入分区名称");
			this.result.setSuccess(false);
			return "OK";
		}
		try {
			Section section = this.sectionManager.getSectionById(sid);
			section.setSecName(secName);
			this.sectionManager.update(section);
			this.result.setMsg("修改成功");
			this.result.setSuccess(true);
		} catch (Exception e) {
			this.result.setMsg("修改失败");
			this.result.setSuccess(false);
			e.printStackTrace();
		}
     return "OK";
               }
             }

