/*    */package com.cecwj.action;

import com.cecwj.mq.SaverMessageTrigger;
import javax.annotation.Resource;

public class RequestAction extends BaseAction {
	private String rfid;
	private int section = -1;

	private String initHandle;

	private SaverMessageTrigger saverMessageTrigger;

	public SaverMessageTrigger getSaverMessageTrigger() {
		return this.saverMessageTrigger;
	}

	@Resource
	public void setSaverMessageTrigger(SaverMessageTrigger saverMessageTrigger) {
		this.saverMessageTrigger = saverMessageTrigger;
	}

	public String getRfid() {
		return this.rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public int getSection() {
		return this.section;
	}

	public void setSection(int section) {
		this.section = section;
	}

	public String getInitHandle() {
		return this.initHandle;
	}

	public void setInitHandle(String initHandle) {
		this.initHandle = initHandle;
	}
}
