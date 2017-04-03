package com.cecwj.action;

import com.cecwj.model.Section;
import com.cecwj.model.User;
import com.cecwj.model.User.Role;
import com.cecwj.model.serialize.JsonBase;
import com.cecwj.model.serialize.UserAndSection;
import com.cecwj.service.UserManager;
import java.util.Map;
import javax.annotation.Resource;

public class LoginAction extends BaseAction {
	private int userType;
	private String loginName;
	private String password;
	private int sectionid;
	private int id;
	private int role;
	private UserManager userManager;
	private String deviceId;

	public UserManager getUserManager() {
		return this.userManager;
	}

	@Resource
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public int getUserType() {
		return this.userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String login() {
		User login = null;
		try {
			switch (this.userType) {
			case 0:
				login = this.userManager.getLogin(this.loginName,
						this.password, User.Role.ADMIN);
				break;
			case 1:
				login = this.userManager.getLogin(this.loginName,
						this.password, User.Role.DOCTOR);
				break;
			case 2:
				login = this.userManager.getLogin(this.loginName,
						this.password, User.Role.NURSE);
				break;
			
			case 3:
				login = this.userManager.getLogin(this.loginName,
						this.password, User.Role.EXAMINATION);
				break;
			case 4:
				login = this.userManager.getLogin(this.loginName,
						this.password, User.Role.OPERATION);
				break;
			case 5:
				login = this.userManager.getLogin(this.loginName,
						this.password, User.Role.Volunteer);
				break;
			default:
				login = null;
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			this.result = new JsonBase("网络错误", false);
			return "OK";
		}
		if (login != null) {
			getSession().put("sectionid",Integer.valueOf(login.getSection().getId()));
			getSession().put("id", Integer.valueOf(login.getId()));
			getSession().put("loginName", login.getLoginName());
			getSession().put("Chinesename", login.getUserName());
			getSession().put("role", login.getRole());
			getSession().put("roleStr", login.getRoleStr());
			UserAndSection u = new UserAndSection(login);
			this.result = new JsonBase(u);
		} else {
			this.result = new JsonBase("用户名或密码错误", false);
		}

		return "OK";
	}

	public String loginPhone() {
		User login = null;

		try {
			switch (this.userType) {
			case 0:
				login = this.userManager.getLogin(this.loginName,
						this.password, User.Role.ADMIN);
				break;
			case 1:
				login = this.userManager.getLogin(this.loginName,
						this.password, User.Role.DOCTOR);
				break;
			case 2:
				login = this.userManager.getLogin(this.loginName,
						this.password, User.Role.NURSE);
				break;
			case 3:
				login = this.userManager.getLogin(this.loginName,
						this.password, User.Role.EXAMINATION);
				break;
			case 4:
				login = this.userManager.getLogin(this.loginName,
						this.password, User.Role.OPERATION);
				break;
			case 5:
				login = this.userManager.getLogin(this.loginName,
						this.password, User.Role.Volunteer);
				break;
			case 6:
				login = this.userManager.getLogin(this.loginName,
						this.password, User.Role.TRANSPORT);
				break;
			default:
				login = null;
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			this.result = new JsonBase("网络错误", false);
			return "OK";
		}
		if (login != null) {
			login.setDeviceId(this.deviceId);
			this.userManager.update(login);
			getSession().put("sectionid",
					Integer.valueOf(login.getSection().getId()));
			getSession().put("id", Integer.valueOf(login.getId()));
			getSession().put("loginName", login.getLoginName());
			getSession().put("Chinesename", login.getUserName());
			getSession().put("role", login.getRole());
			getSession().put("roleStr", login.getRoleStr());
			UserAndSection u = new UserAndSection(login);
			this.result = new JsonBase(u);
		} else {
			this.result = new JsonBase("用户名或密码错误", false);
		}
		return "OK";
	}

	public String loginPhoneAll() {
		User login = null;
		try {
			login = this.userManager.getLoginAll(this.loginName, this.password);
		} catch (RuntimeException e) {
			this.result = new JsonBase("网络错误", false);
			return "OK";
		}
		if (login != null) {
			login.setDeviceId(this.deviceId);
			this.userManager.update(login);
			getSession().put("sectionid",
					Integer.valueOf(login.getSection().getId()));
			getSession().put("id", Integer.valueOf(login.getId()));
			getSession().put("loginName", login.getLoginName());
			getSession().put("Chinesename", login.getUserName());
			getSession().put("role", login.getRole());
			getSession().put("roleStr", login.getRoleStr());
			UserAndSection u = new UserAndSection(login);
			this.result = new JsonBase(u);
		} else {
			this.result = new JsonBase("用户名或密码错误", false);
		}

		return "OK";
	}

   public String go() { 
	   return "OK"; 
	   }
}
