package com.cecwj.service;

import com.cecwj.dao.UserDao;
import com.cecwj.model.serialize.JsonBase;
import com.cecwj.model.serialize.UserAndSection;
import com.cecwj.model.User;
import com.cecwj.model.User.Role;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

@Component("userManager")
public class UserManager {
	private UserDao userDao;

	public UserDao getUserDao() {
		return this.userDao;
	}

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User getLogin(String username, String password, User.Role role) {
		User user = this.userDao.getUserByUsernameAndRole(username, role);
		if (user != null) {
			if (!user.getPassword().equals(password)) {
				return null;
			}
			return user;
		}

		return null;
	}

	public User getLoginAll(String username, String password) {
		return this.userDao.getUserByUsernameAndPwd(username, password);
	}

	public void getUserByRole(int page, JsonBase result, User.Role role) {
		List<User> users = this.userDao.getUserByRole(page, role);
		List<UserAndSection> us = new ArrayList();
		for (User user : users) {
			UserAndSection u = new UserAndSection(user);
			us.add(u);
		}
		int total = this.userDao.getUserTotalByRole(role);
		result.setResults(us);
		result.setTotal(total);
		result.setSuccess(true);
	}
	public void getUserByRoleA(int page, JsonBase result, User.Role role) {
		List<User> users = this.userDao.getUserByRoleA(page, role);
		List<UserAndSection> us = new ArrayList();
		for (User user : users) {
			UserAndSection u = new UserAndSection(user);
			us.add(u);
		}
		int total = this.userDao.getUserTotalByRole(role);
		result.setResults(us);
		result.setTotal(total);
		result.setSuccess(true);
	}

	public User getUserById(Integer id) {
		return this.userDao.getUserById(id.intValue());
	}

	public User getUserByDeviceId(String deviceId) {
		return this.userDao.getUserByDeviceId(deviceId);
	}

	public void add(User user) {
		this.userDao.add(user);
	}

	public void update(User user) {
		this.userDao.update(user);
	}

	public void delete(User user) {
		this.userDao.delete(user);
	}

	public int getUserTotalBySid(int sid) {
		return this.userDao.getUserTotalBySid(sid);
	}

	public List<User> getDoctorBySid(int sid) {
		return this.userDao.getDoctorBySid(sid);
	}

	public List<User> getAllDoctors() {
		return this.userDao.getAllDoctors();
	}

	public boolean getUserByName(String loginName) {
		return this.userDao.getUserByName(loginName);
	}

	public User getLoginFlag(String loginName) {
		
		return this.userDao.getLoginFlag(loginName);
		
	}
}

/*
 * Location:
 * D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\service
 * \UserManager.class Java compiler version: 5 (49.0) JD-Core Version: 0.7.1
 */