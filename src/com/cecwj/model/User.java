package com.cecwj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cecwj.model.Section;

@Entity
@Table(name = "user")
public class User {
	public enum Role {
		ADMIN, DOCTOR, NURSE, EXAMINATION, OPERATION, Volunteer, TRANSPORT;
	}

	private int id;
	private String loginName;
	private String password;
	private User.Role role;
	private String roleStr;
	private String userName;
	private Section section;
	private String deviceId;
	private Boolean flag;

	public enum Status {

	}

	// private Volunteer volunteer;
	@Id
	@GeneratedValue
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "username")
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@ManyToOne
	@JoinColumn(name = "sid")
	public Section getSection() {
		return this.section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	@Column(name = "password")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "role")
	public User.Role getRole() {
		return this.role;
	}

	public void setRole(User.Role role) {
		this.role = role;
		if (role.equals(User.Role.ADMIN)) {
			this.roleStr = "管理员";
		} else if (role.equals(User.Role.DOCTOR)) {
			this.roleStr = "主治医生";
		} else if (role.equals(User.Role.NURSE)) {
			this.roleStr = "护士";
		} else if (role.equals(User.Role.Volunteer)) {
			this.roleStr = "志愿者";
		} else if (role.equals(User.Role.EXAMINATION)) {
			this.roleStr = "检查检验医生";
		} else if (role.equals(User.Role.OPERATION)) {
			this.roleStr = "手术医生";
		} else if (role.equals(User.Role.TRANSPORT)) {
			this.roleStr = "转运医生";
		}
	}

	@Transient
	public String getRoleStr() {
		if ((this.roleStr == null) && (this.role != null)) {
			if (this.role.equals(User.Role.ADMIN)) {
				this.roleStr = "管理员";
			} else if (this.role.equals(User.Role.DOCTOR)) {
				this.roleStr = "主治医生";
			} else if (this.role.equals(User.Role.NURSE)) {
				this.roleStr = "护士";
			} else if (this.role.equals(User.Role.Volunteer)) {
				this.roleStr = "志愿者";
			} else if (this.role.equals(User.Role.EXAMINATION)) {
				this.roleStr = "检查检验医生";
			} else if (this.role.equals(User.Role.OPERATION)) {
				this.roleStr = "手术医生";
			} else if (this.role.equals(User.Role.TRANSPORT)) {
				this.roleStr = "转运医生";
			}
		}
		return this.roleStr;
	}

	public void setRoleStr(String roleStr) {
		this.roleStr = roleStr;
		if (roleStr.equals("")) {
			return;
		}
		if (roleStr.equals("管理员")) {
			this.role = User.Role.ADMIN;
		} else if (roleStr.equals("主治医生")) {
			this.role = User.Role.DOCTOR;
		} else if (roleStr.equals("护士")) {
			this.role = User.Role.NURSE;
		} else if (roleStr.equals("志愿者")) {
			this.role = User.Role.Volunteer;
		} else if (roleStr.equals("检查检验医生")) {
			this.role = User.Role.EXAMINATION;
		} else if (roleStr.equals("手术医生")) {
			this.role = User.Role.OPERATION;
		} else if (roleStr.equals("转运医生")) {
			this.role = User.Role.TRANSPORT;
		}
	}

	@Column(name = "name")
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "deviceId")
	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
}
