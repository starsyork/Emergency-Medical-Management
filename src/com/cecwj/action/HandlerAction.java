package com.cecwj.action;

import com.cecwj.model.DocAdvice;
import com.cecwj.model.DocAdvice.AType;
import com.cecwj.model.Handler;
import com.cecwj.model.User;
import com.cecwj.model.serialize.JsonBase;
import com.cecwj.service.DocAdviceManager;
import com.cecwj.service.HandlerManager;
import com.cecwj.service.UserManager;
import java.sql.Timestamp;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HandlerAction extends BaseAction {
	private HandlerManager handlerManager;
	private DocAdviceManager docAdviceManager;
	private UserManager userManager;
	static Log log = LogFactory.getLog(HandlerAction.class);
	int cid;
	int uid;
	String note;

	public HandlerManager getHandlerManager() {
		return this.handlerManager;
	}

	@Resource
	public void setHandlerManager(HandlerManager handlerManager) {
		this.handlerManager = handlerManager;
	}

	public UserManager getUserManager() {
		return this.userManager;
	}

	@Resource
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public DocAdviceManager getDocAdviceManager() {
		return this.docAdviceManager;
	}

	@Resource
	public void setDocAdviceManager(DocAdviceManager docAdviceManager) {
		this.docAdviceManager = docAdviceManager;
	}

	public int getCid() {
		return this.cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String ref) {
		this.note = ref;
	}

	public String addHandler() {
		this.result = new JsonBase();

		if (!authorized("id")) {
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
		} else {
			Integer uid = (Integer) getSession().get("id");
			String str = getRequest().getParameter("id");
			if ((str == null) || (str.equals(""))) {
				this.result.setMsg("医嘱id错误");
				this.result.setSuccess(false);
			} else {
				int id = Integer.valueOf(str).intValue();
				DocAdvice docAdvice = this.docAdviceManager
						.getDocAdviceById(id);
				Handler handler = new Handler();
				handler.setCid(this.cid);
				User user = this.userManager.getUserById(uid);
				handler.setUser(user);
				handler.setOid(id);
				handler.setRef(this.note);
				handler.setHandle_time(new Timestamp(System.currentTimeMillis()));
				try {
					this.handlerManager.addHandler(handler);
					if (docAdvice.getAType().equals(DocAdvice.AType.TEMPORARY)) {
						docAdvice
								.setStatus(com.cecwj.model.DocAdvice.Status.EXECUTE);
						this.docAdviceManager.update(docAdvice);
					}
				} catch (Exception e) {
					this.result.setMsg("添加失败,请检查网络");
					this.result.setSuccess(false);
					log.error(e);
				}
			}
		}
		return "OK";
	}

	public String getHandlers() {
		this.result = new JsonBase();

		String num = getRequest().getParameter("page");
		Integer page;
		if ((num != null) && (!num.equals(""))) {
			page = Integer.valueOf(num);
		} else {
			page = Integer.valueOf(1);
		}
		String str = getRequest().getParameter("id");
		if ((str == null) || (str.equals(""))
				|| (Integer.valueOf(str).intValue() < 1)) {
			this.result.setMsg("医嘱id指定错误");
			this.result.setSuccess(false);
		} else {
			int id = Integer.valueOf(str).intValue();
			try {
				this.handlerManager.getHandlers(id, page.intValue(),
						this.result);
			} catch (Exception e) {
				this.result.setMsg("获取handler失败");
				this.result.setSuccess(false);
				log.error(e);
			}
		}
		return "OK";
	}
}

