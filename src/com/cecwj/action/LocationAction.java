package com.cecwj.action;

import com.cecwj.model.User;
import com.cecwj.model.serialize.JsonBase;
import com.cecwj.model.serialize.android.UserLocation;
import com.cecwj.service.UserManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

public class LocationAction extends BaseAction {
	static List<UserLocation> locations = new ArrayList();
	private double longitude;
	private double latitude;
	private String deviceId;
	private UserManager userManager;

	public UserManager getUserManager() {
		return this.userManager;
	}

	@Resource
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String addLocation() {
		this.result = new JsonBase();
		try {
			if (locations.size() > 0) {
				int flag = 0;
				for (UserLocation userLocation : locations) {
					if (this.deviceId.equals(userLocation.getDeviceId())) {
						flag = 1;
					}
				}
				if (flag == 0) {
					UserLocation us = new UserLocation();
					us.setDeviceId(this.deviceId);
					us.setLatitude(this.latitude);
					us.setLongitude(this.longitude);
					us.setDate(new Date());
					locations.add(us);
				} else {
					for (UserLocation userLocation : locations) {
						if (this.deviceId.equals(userLocation.getDeviceId())) {
							userLocation.setLatitude(this.latitude);
							userLocation.setLongitude(this.longitude);
							userLocation.setDate(new Date());
						}
					}
				}

				this.result.setSuccess(true);
			} else {
				UserLocation us = new UserLocation();
				us.setDeviceId(this.deviceId);
				us.setLatitude(this.latitude);
				us.setLongitude(this.longitude);
				us.setDate(new Date());
				locations.add(us);
				this.result.setSuccess(true);
			}
		} catch (Exception e) {
			this.result.setMsg("添加位置信息失败");
			this.result.setSuccess(false);
		}
		return "OK";
	}

	public String getNewLocation() {
		this.result = new JsonBase();
		try {
			Date datenow = new Date();
			for (UserLocation userLocation : locations) {
				if (datenow.getTime() - userLocation.getDate().getTime() > 1000000L) {
					locations.remove(userLocation);
				}
			}
			this.result.setResults(locations);
			this.result.setSuccess(true);
		} catch (Exception e) {
			this.result.setMsg("获取位置信息失败");
			this.result.setSuccess(false);
		}
		return "OK";
	}

	public String getUserByDeviceId() {
		this.result = new JsonBase();
		User u = this.userManager.getUserByDeviceId(this.deviceId);
		this.result.setResults(u);
		this.result.setSuccess(true);
		return "OK";
	}
}
