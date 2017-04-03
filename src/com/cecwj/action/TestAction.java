package com.cecwj.action;

import com.cecwj.model.serialize.JsonBase;

public class TestAction extends BaseAction {
	public String test() {
		this.result = new JsonBase();
		this.result.setMsg("asdfghjkl");
		return "OK";
	}
}
