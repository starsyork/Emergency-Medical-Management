package com.cecwj.action;

import com.cecwj.common.TimeFormat;
import com.cecwj.model.serialize.JsonBase;
import com.cecwj.model.serialize.PatientSta;
import com.cecwj.model.serialize.UserAndSection;
import com.cecwj.model.serialize.VolunteerInfo;
import com.cecwj.model.Doctor;
import com.cecwj.model.Edetail;
import com.cecwj.model.Inspect;
import com.cecwj.model.Patient;
import com.cecwj.model.Section;
import com.cecwj.model.User;
import com.cecwj.model.Volunteer;
//          import com.cecwj.service.PatientManager;
import com.cecwj.service.DoctorManager;
import com.cecwj.service.PatientManager;
import com.cecwj.service.SectionManager;
import com.cecwj.service.UserManager;
import com.cecwj.service.VolunteerManager;
import com.cecwj.model.serialize.RTSta;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserAction extends BaseAction {
	static Log log = LogFactory.getLog(UserAction.class);
	private String loginName;
	private String password;
	private String userName;
	private String sector;
	private Integer id;
	private String oldPassword;
	private User.Role role;
	private String roleStr;
	private int vid;
	private String name;
	private int Sex;
	private long IDcard;
	private String Specialty;
	private String address;
	private long phone;
	private int vflag;
	private int age;

	private SectionManager sectionManager;
	// private PatientManager patientManager;
	private UserManager userManager;
	private VolunteerManager volunteerManager;

	private PatientManager patientManager;
	private DoctorManager doctorManager;

	public UserManager getUserManager() {
		return this.userManager;
	}

	@Resource
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public VolunteerManager getVolunteerManager() {
		return this.volunteerManager;
	}

	@Resource
	public void setVolunteerManager(VolunteerManager volunteerManager) {
		this.volunteerManager = volunteerManager;
	}

	public DoctorManager getDoctorManager() {
		return this.doctorManager;
	}

	@Resource
	public void setDoctorManagerr(DoctorManager doctorManager) {
		this.doctorManager = doctorManager;
	}

	/*
	 * 
	 * /* public PatientManager getPatientManager() { return
	 * this.patientManager; }
	 * 
	 * @Resource public void setPatientManager(PatientManager patientManager) {
	 * this.patientManager = patientManager; }
	 */
	public SectionManager getSectionManager() {
		return this.sectionManager;
	}

	@Resource
	public void setSectionManager(SectionManager sectionManager) {
		this.sectionManager = sectionManager;
	}

	public PatientManager getPatientManager() {
		return this.patientManager;
	}

	@Resource
	public void setPatientManagerr(PatientManager patientManager) {
		this.patientManager = patientManager;
	}

	public String getOldPassword() {
		return this.oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public User.Role getRole() {
		return this.role;
	}

	public void setRole(User.Role role) {
		this.role = role;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSector() {
		return this.sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public int getVid() {
		return this.vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getIDcard() {
		return IDcard;
	}

	public void setIDcard(long iDcard) {
		IDcard = iDcard;
	}

	public int getSex() {
		return this.Sex;
	}

	public void setSex(int Sex) {
		this.Sex = Sex;
	}

	public String getSpecialty() {
		return this.Specialty;
	}

	public void setSpecialty(String Specialty) {
		this.Specialty = Specialty;
	}

	
	
	
	public String getRoleStr() {
		return roleStr;
	}

	public void setRoleStr(String roleStr) {
		this.roleStr = roleStr;
	}

	public int getVflag() {
		return vflag;
	}

	public void setVflag(int vflag) {
		this.vflag = vflag;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getUser() {
		if (!authorized("id")) {
			this.result = new JsonBase();
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
			log.info("获取当前用户失败");
			return "OK";
		}
		this.result = new JsonBase();
		int uid = ((Integer) getSession().get("id")).intValue();
		try {
			User user = this.userManager.getUserById(Integer.valueOf(uid));
			this.result.setResults(user);
			this.result.setSuccess(true);
		} catch (Exception e) {
			this.result.setMsg("请检查网络链接是否正常！");
			this.result.setSuccess(false);
			log.error(e);
		}
		return "OK";
	}

	public String getDoctor() {
		this.result = new JsonBase();
		int page = 1;
		try {
			page = Integer.parseInt(getRequest().getParameter("page"));
		} catch (Exception e) {
			log.error(e);
		}
		try {
			this.userManager.getUserByRoleA(page, this.result, User.Role.DOCTOR);
		} catch (Exception e) {
			this.result.setMsg("请检查网络链接是否正常！");
			this.result.setSuccess(false);
			log.error(e);
		}
		return "OK";
	}

	public String getNurse() {
		this.result = new JsonBase();
		int page = 1;
		try {
			page = Integer.parseInt(getRequest().getParameter("page"));
		} catch (Exception e) {
			log.error(e);
		}
		try {
			this.userManager.getUserByRole(page, this.result, User.Role.NURSE);
		} catch (Exception e) {
			this.result.setMsg("获取护士信息失败");
			this.result.setSuccess(false);
			log.error(e);
		}
		return "OK";
	}

	public String getVolunteerById() {
		this.result = new JsonBase();
		int id = 1;
		try {
			Volunteer volunteer = this.volunteerManager.getvolunteerById(id);
			this.result.setResults(volunteer);
			this.result.setSuccess(true);
		} catch (Exception e) {
			System.out.println(e);
			this.result.setMsg("请检查网络链接是否正常！");
			this.result.setSuccess(false);
		}
		return "OK";
	}

	// 获取所有志愿者所有信息

	public String getAllVolunteer() {
		this.result = new JsonBase();
		try {
			List<Volunteer> volunteers = this.volunteerManager.getAllVolunteer();
			this.result.setResults(volunteers);
			this.result.setSuccess(true);
		} catch (Exception e) {
			System.out.println(e);
			this.result.setMsg("请检查网络链接是否正常！");
			this.result.setSuccess(false);
			log.error(e);
		}
		return "OK";
	}

	// 获取所有志愿者部分信息
	public String getAllVolunteerA() {
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
			List<Volunteer> us = this.volunteerManager.getAllVolunteerA(this.result);
			List<Volunteer> da3 = new ArrayList();
			if (page != -1) {					
				for (int i = (page - 1) * limit; (i < us.size())
						&& (i < page * limit); i++) {
					da3.add((Volunteer) us.get(i));
				}
				int total = us.size();
				this.result.setResults(da3);
				this.result.setSuccess(true);
				this.result.setTotal(total);					
		} else {
			int total = us.size();
			this.result.setResults(us);
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

	public String getundVolunteer() {
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
			List<Volunteer> us = this.volunteerManager.getundeVolunteer();
			List<Volunteer> da3 = new ArrayList();
			if (page != -1) {					
				for (int i = (page - 1) * limit; (i < us.size())
						&& (i < page * limit); i++) {
					da3.add((Volunteer) us.get(i));
				}
				int total = us.size();
				this.result.setResults(da3);
				this.result.setSuccess(true);
				this.result.setTotal(total);					
		} else {
			int total = us.size();
			this.result.setResults(us);
			this.result.setSuccess(true);
			this.result.setTotal(total);
		}		
		} catch (Exception e) {
			System.out.println(e);
			this.result.setMsg("请检查网络链接是否正常！");
			this.result.setSuccess(false);
			log.error(e);
		}

		this.result.setSuccess(true);
		return "OK";
	}

	public String getFirstPatientTime() {
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
			Patient patient = this.patientManager.getFirstPatientTime();
			if(patient!=null){
			Timestamp timestamp = patient.getRequestTime(); // 第一天
																								// 2013-07-01
																								// 09:41:36.0
			timestamp = TimeFormat.TimestampToTimestamp(timestamp);// 第一天
																	// 2013-07-01													// 00:00:00.0

			Timestamp timestampadd = TimeFormat.TimestampAddOneDay(timestamp); // 第二天
																				// 2013-07-02
																				// 00:00:00.0

			Timestamp timestampnow = this.patientManager.getLastPatientTime().getRequestTime(); // 最后一天
		
			timestampnow = TimeFormat.TimestampToTimestamp(timestampnow);

			long time = TimeFormat.Timestampsub(timestamp, timestampnow);

			List<RTSta> list = new ArrayList();
			List<Section> ls = this.sectionManager.getAllSection();
			int stotal[] = new int[30];
			int flag = 0;
			int j = 0;
			try {
				for (long i = 0; i < time+1; i++) {
					RTSta t = new RTSta();
					for (Section s : ls) {
						long total = this.patientManager.getRTSta(s.getId(), timestamp, timestampadd);
	
						if (total != 0) {
							stotal[s.getId()] = (int) total;
							flag = 1;
						}
					}
					if (flag == 1) {
						Integer a = new Integer(stotal[3]);
						Integer b = new Integer(stotal[2]);
						Integer c = new Integer(stotal[1]);
						if (a != null)
							t.setLInjNum(stotal[3]);
						else
							t.setLInjNum(0);

						if (b != null)
							t.setMInjNum(stotal[2]);
						else
							t.setMInjNum(0);
						if (c != null)
							t.setSInjNum(stotal[1]);
						else
							t.setSInjNum(0);

						t.setTotalDeIn(stotal[1] + stotal[2] + stotal[3]);
						t.setId(++j);
						t.setDate(TimeFormat.timeStampToStringA(timestamp));

						list.add(t);
						flag = 0;
					}
					timestamp = TimeFormat.TimestampAddOneDay(timestamp);
					timestampadd = TimeFormat.TimestampAddOneDay(timestampadd);	
					List<RTSta> da3 = new ArrayList();
					if (page != -1) {					
							for (int i1 = (page - 1) * limit; (i1 < list.size())
									&& (i1 < page * limit); i1++) {
								da3.add((RTSta) list.get(i1));
							}
							int total = list.size();
							this.result.setResults(da3);
							this.result.setSuccess(true);
							this.result.setTotal(total);
				}else{
					 
							int total = list.size();
							this.result.setResults(list);
							this.result.setSuccess(true);
							this.result.setTotal(total);				
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				this.result.setMsg("获取统计信息失败");

				this.result.setSuccess(false);
				log.error(e);
			}
			this.result.setSuccess(true);
			this.result.setTotal(list.size());
			}else{
				this.result.setResults(patient);
				this.result.setSuccess(true);
			}
			} catch (Exception e) {
			System.out.println(e);
			//this.result.setMsg("请检查网络链接是否正常！");
			this.result.setSuccess(false);
			log.error(e);
		}
		return "OK";
	}

	public String getRTSta() {
		this.result = new JsonBase();
		try {
			Timestamp timestamp = this.patientManager.getFirstPatientTime().getRequestTime(); // 第一天
																								// 2013-07-01
																								// 09:41:36.0
			timestamp = TimeFormat.TimestampToTimestamp(timestamp);// 第一天
																	// 2013-07-01
																	// 00:00:00.0

			Timestamp timestampadd = TimeFormat.TimestampAddOneDay(timestamp); // 第二天
																				// 2013-07-02
																				// 00:00:00.0

			Timestamp timestampnow = this.patientManager.getLastPatientTime().getRequestTime(); // 最后一天
			timestampnow = TimeFormat.TimestampToTimestamp(timestampnow);

			long time = TimeFormat.Timestampsub(timestamp, timestampnow);

			List<RTSta> list = new ArrayList();
			List<Section> ls = this.sectionManager.getAllSection();
			int stotal[] = new int[30];
			int flag = 0;
			int j = 0;
			try {
				for (long i = 0; i < time+1; i++) {
					RTSta t = new RTSta();
					for (Section s : ls) {
						long total = this.patientManager.getRTSta(s.getId(), timestamp, timestampadd);
						if (total != 0) {
							stotal[s.getId()] = (int) total;
							flag = 1;
						}
					}
					if (flag == 1) {
						Integer a = new Integer(stotal[3]);
						Integer b = new Integer(stotal[2]);
						Integer c = new Integer(stotal[1]);
						if (a != null)
							t.setLInjNum(stotal[3]);
						else
							t.setLInjNum(0);

						if (b != null)
							t.setMInjNum(stotal[2]);
						else
							t.setMInjNum(0);
						if (c != null)
							t.setSInjNum(stotal[1]);
						else
							t.setSInjNum(0);

						t.setTotalDeIn(stotal[1] + stotal[2] + stotal[3]);
						t.setId(++j);
						t.setDate(TimeFormat.timeStampToStringB(timestamp));

						list.add(t);
						flag = 0;
					}
					timestamp = TimeFormat.TimestampAddOneDay(timestamp);
					timestampadd = TimeFormat.TimestampAddOneDay(timestampadd);
					this.result.setResults(list);
				}

			} catch (Exception e) {
				e.printStackTrace();
				this.result.setMsg("获取统计信息失败");

				this.result.setSuccess(false);
				log.error(e);
			}
			this.result.setSuccess(true);
			this.result.setTotal(list.size());
		} catch (Exception e) {
			System.out.println(e);
			this.result.setMsg("请检查网络链接是否正常！");
			this.result.setSuccess(false);
			log.error(e);
		}
		return "OK";
	}

	public String addDoctor() {
		this.result = new JsonBase();
		if ((this.loginName == null) || (this.loginName.equals(""))) {
			this.result.setMsg("增加失败，请输入登陆名！");
			this.result.setSuccess(false);
		} else if ((this.password == null) || (this.password.equals(""))) {
			this.result.setMsg("增加失败，请输入密码！");
			this.result.setSuccess(false);
		} else if ((this.userName == null) || (this.userName.equals(""))) {
			this.result.setMsg("增加失败，请输入用户姓名！");
			this.result.setSuccess(false);
		} else if ((this.sector == null) || (this.sector.equals(""))) {
			this.result.setMsg("增加失败，请输入分区号！");
			this.result.setSuccess(false);
		} else if (this.userManager.getUserByName(this.loginName)) {
			this.result.setMsg("新增用户失败，用户loginName=" + this.loginName + "已经存在");
			this.result.setSuccess(false);
		} else {
			User user = new User();
			user.setLoginName(this.loginName);
			user.setPassword(this.password);
			user.setUserName(this.userName);
			user.setSection(this.sectionManager.getSectionByName(this.sector));
			user.setRoleStr(this.roleStr);
			user.setFlag(true);

			try {
				this.userManager.add(user);
				this.result.setSuccess(true);
				this.result.setMsg("新增用户成功");
				log.info("新增用户成功，loginName=" + this.loginName);
			} catch (Exception e) {
				log.error(e);
				this.result.setMsg("新增失败，请检查网络连接是否正常！");
				this.result.setSuccess(false);
				log.info("数据库插入失败,loginName=" + this.loginName);
			}
		}
		return "OK";
	}

	public String addDoctorA() {
		this.result = new JsonBase();
		if ((this.loginName == null) || (this.loginName.equals(""))) {
			this.result.setMsg("增加失败，请输入登陆名！");
			this.result.setSuccess(false);
		} else if ((this.password == null) || (this.password.equals(""))) {
			this.result.setMsg("增加失败，请输入密码！");
			this.result.setSuccess(false);
		} else if ((this.userName == null) || (this.userName.equals(""))) {
			this.result.setMsg("增加失败，请输入用户姓名！");
			this.result.setSuccess(false);
		} else if ((this.sector == null) || (this.sector.equals(""))) {
			this.result.setMsg("增加失败，请输入分区号！");
			this.result.setSuccess(false);
		} else if (this.userManager.getUserByName(this.loginName)) {
			this.result.setMsg("新增用户失败，用户loginName=" + this.loginName + "已经存在");
			this.result.setSuccess(false);
		} else {
			User user = new User();
			user.setLoginName(this.loginName);
			user.setPassword(this.password);
			user.setUserName(this.userName);
			user.setSection(this.sectionManager.getSectionByName(this.sector));
			user.setRole(User.Role.DOCTOR);

			Doctor doctor = new Doctor();
			doctor.setIDcard(this.IDcard);
			doctor.setName(this.name);
			doctor.setSex(this.Sex);
			doctor.setUser(user);
			try {
				this.userManager.add(user);
				this.doctorManager.add(doctor);
				this.result.setSuccess(true);
				this.result.setMsg("新增用户成功");
				log.info("新增用户成功，loginName=" + this.loginName);
			} catch (Exception e) {
				log.error(e);
				this.result.setMsg("新增失败，请检查网络连接是否正常！");
				this.result.setSuccess(false);
				log.info("数据库插入失败,loginName=" + this.loginName);
			}
		}
		return "OK";
	}

	public String addDoctorB() {
		this.result = new JsonBase();
		String loginName = "sdg";
		String password = "sfghy";
		String userName = "srtzb";
		int IDcard = 4254;
		// String name="dfhngdhn";
		String sector = "中度监护区";
		int Sex = 1;
		User user = new User();
		user.setLoginName(loginName);
		user.setPassword(password);
		user.setUserName(userName);
		user.setSection(this.sectionManager.getSectionByName(sector));
		user.setRole(User.Role.DOCTOR);

		Doctor doctor = new Doctor();
		doctor.setIDcard(IDcard);
		doctor.setName(name);
		doctor.setSex(Sex);
		doctor.setUser(user);
		try {
			this.userManager.add(user);
			this.doctorManager.add(doctor);
			this.result.setSuccess(true);
			this.result.setMsg("新增用户成功");
			log.info("新增用户成功，loginName=" + this.loginName);
		} catch (Exception e) {
			log.error(e);
			this.result.setMsg("新增失败，请检查网络连接是否正常！");
			this.result.setSuccess(false);
			log.info("数据库插入失败,loginName=" + this.loginName);
		}

		return "OK";
	}

	public String addNurse() {
		this.result = new JsonBase();
		if ((this.loginName == null) || (this.loginName.equals(""))) {
			this.result.setMsg("增加失败，请输入登陆名！");
			this.result.setSuccess(false);
		} else if ((this.password == null) || (this.password.equals(""))) {
			this.result.setMsg("增加失败，请输入密码！");
			this.result.setSuccess(false);
		} else if ((this.userName == null) || (this.userName.equals(""))) {
			this.result.setMsg("增加失败，请输入用户姓名！");
			this.result.setSuccess(false);
		} else if ((this.sector == null) || (this.sector.equals(""))) {
			this.result.setMsg("增加失败，请输入分区！");
			this.result.setSuccess(false);
		} else if (this.userManager.getUserByName(this.loginName)) {
			this.result.setMsg("新增用户失败，用户loginName=" + this.loginName + "已经存在");
			this.result.setSuccess(false);
		} else {
			User user = new User();
			user.setLoginName(this.loginName);
			user.setPassword(this.password);
			user.setUserName(this.userName);
			user.setSection(this.sectionManager.getSectionByName(this.sector));
			user.setRole(User.Role.NURSE);
			// UserAndSection u = new UserAndSection(user);

			try {
				this.userManager.add(user);
				this.result.setSuccess(true);
				this.result.setMsg("新增用户成功");
				log.info("新增用户成功，loginName=" + this.loginName);
			} catch (Exception e) {
				log.error(e);
				e.printStackTrace();
				this.result.setMsg("新增失败，请检查网络连接是否正常！");
				this.result.setSuccess(false);
				log.info("数据库插入失败,loginName=" + this.loginName);
			}
		}

		return "OK";
	}

	public String addVolunteer() {
		this.result = new JsonBase();
		if ((this.loginName == null) || (this.loginName.equals(""))) {
			this.result.setMsg("增加失败，请输入登陆名！");
			this.result.setSuccess(false);
		} else if ((this.password == null) || (this.password.equals(""))) {
			this.result.setMsg("增加失败，请输入密码！");
			this.result.setSuccess(false);
		} else if ((this.userName == null) || (this.userName.equals(""))) {
			this.result.setMsg("增加失败，请输入用户姓名！");
			this.result.setSuccess(false);
		} else if (this.IDcard == 0) {
			this.result.setMsg("增加失败，请输入正确的身份证号！");
			this.result.setSuccess(false);
		} else if ((this.Specialty == null) || (this.Specialty.equals(""))) {
			this.result.setMsg("增加失败，请输入技能！");
			this.result.setSuccess(false);
		} else if ((this.address == null) || (this.address.equals(""))) {
			this.result.setMsg("增加失败，请输入家庭住址  ！");
			this.result.setSuccess(false);
		} else if ((this.phone <= 0)) {
			this.result.setMsg("增加失败，请输入联系方式 ！");
			this.result.setSuccess(false);
		} else if (this.Sex != 1 && this.Sex != 2) {
			this.result.setMsg("增加失败，请输入性别！");
			this.result.setSuccess(false);
		} else if (this.age <= 0 || this.Sex >= 100) {
			this.result.setMsg("增加失败，请输入年龄！");
			this.result.setSuccess(false);
		} else if (this.userManager.getUserByName(this.loginName)) {
			this.result.setMsg("新增用户失败，用户loginName=" + this.loginName + "已经存在");
			this.result.setSuccess(false);
		} else {
			try {
				User user = new User();
				user.setLoginName(new String(this.loginName.getBytes("ISO-8859-1"), "UTF-8"));
				user.setPassword(this.password);
				user.setRole(User.Role.Volunteer);
				user.setSection(sectionManager.getSectionByName("其他"));
				user.setUserName(new String(this.userName.getBytes("ISO-8859-1"), "UTF-8"));
				user.setFlag(false);
				this.userManager.add(user);
				Volunteer volunteer = new Volunteer();
				volunteer.setIDcard(this.IDcard);
				volunteer.setName(new String(this.userName.getBytes("ISO-8859-1"), "UTF-8"));
				volunteer.setSex(this.Sex);
				volunteer.setSpecialty(new String(this.Specialty.getBytes("ISO-8859-1"), "UTF-8"));
				volunteer.setAddress(new String(this.address.getBytes("ISO-8859-1"), "UTF-8"));
				volunteer.setPhone(this.phone);
				volunteer.setAge(this.age);
				volunteer.setVflag(0);
				volunteer.setUser(user);

				this.volunteerManager.add(volunteer);
				this.result.setSuccess(true);
				this.result.setMsg("新增用户成功");
				log.info("新增用户成功，loginName=" + this.loginName);
			} catch (Exception e) {
				log.error(e);
				System.out.println(e);
				this.result.setMsg("新增失败，请检查网络连接是否正常！");
				this.result.setSuccess(false);
				log.info("数据库插入失败,loginName=" + this.loginName);
			}
		}
		return "OK";
	}

	public String addVolunteerA() {
		this.result = new JsonBase();

		String loginName = "sdgsdcg";
		String password = "sfgdfghy";
		String userName = "srtzhcb";
		int IDcard = 4254;
		// String name="dfhngdhn";
		String sector = "中度监护区";
		int Sex = 1;
		String Specialty = "tydgjfg";

		User user = new User();
		user.setLoginName(loginName);
		user.setPassword(password);
		user.setRole(User.Role.Volunteer);
		user.setSection(sectionManager.getSectionByName(sector));
		user.setUserName(userName);

		Volunteer volunteer = new Volunteer();
		volunteer.setIDcard(IDcard);
		volunteer.setName(userName);
		volunteer.setSex(Sex);
		volunteer.setSpecialty(Specialty);
		volunteer.setUser(user);
		volunteer.setVflag(0);
		try {
			this.userManager.add(user);
			this.volunteerManager.add(volunteer);
			try {
				// Volunteer volunteer2 =
				// this.volunteerManager.getvolunteerByIdA(user.getId());
				// if(volunteer2 != null) {

				this.result.setSuccess(true);
				this.result.setMsg("新增用户成功");
				log.info("新增用户成功，loginName=" + this.loginName);
				// }else{
				// this.userManager.delete(user);
				// this.result.setMsg("新增失败，请检查网络连接是否正常！");
				// this.result.setSuccess(false);
				// }
			} catch (Exception e) {
				this.result.setMsg("新增失败，请检查网络连接是否正常！");
				this.result.setSuccess(false);
			}

		} catch (Exception e) {
			log.error(e);
			this.result.setMsg("新增失败，请检查网络连接是否正常！");
			this.result.setSuccess(false);
			log.info("数据库插入失败,loginName=" + this.loginName);
		}
		return "OK";
	}

	public String deleteDoctor() {
		this.result = new JsonBase();
		if ((this.id == null) || (this.id.intValue() < 0)) {
			this.result.setMsg("请选择要删除的医生！");
			this.result.setSuccess(false);
			log.error("医生id不正确");
		} else {
			int total = this.patientManager.getPatientTotalByDid(this.id.intValue());
			User user = this.userManager.getUserById(this.id);
			if ((user.getRole() == User.Role.DOCTOR) && (total <= 0)) {
				try {
					this.userManager.delete(user);
				} catch (Exception e) {
					log.error(e);
					log.info("删除失败，exception");
					this.result.setMsg("删除失败，请检查网络连接是否正常！");
					this.result.setSuccess(false);
				}
			} else {
				this.result.setMsg("不能删除医生，有未出院的病人！");
				this.result.setSuccess(false);
				log.error("医生id不正确");
			}
		}
		return "OK";
	}

	public String deleteNurse() {
		this.result = new JsonBase();
		if ((this.id == null) || (this.id.intValue() < 0)) {
			this.result.setMsg("请选择要删除的护士！");
			this.result.setSuccess(false);
			log.error("护士id不正确");
		} else {
			User user = this.userManager.getUserById(this.id);
			if (user.getRole() == User.Role.NURSE) {
				try {
					this.userManager.delete(user);
				} catch (Exception e) {
					log.error(e);
					log.info("删除失败，exception");
					this.result.setMsg("删除失败，请检查网络连接是否正常！");
					this.result.setSuccess(false);
				}
			} else {
				this.result.setMsg("请选择要删除的护士！");
				this.result.setSuccess(false);
				log.error("护士id不正确");
			}
		}
		return "OK";
	}

	public String deleteVolunteer() {
		this.result = new JsonBase();
		if ((this.id == null) || (this.id.intValue() < 0)) {
			this.result.setMsg("请选择要删除的志愿者！");
			this.result.setSuccess(false);
			log.error("志愿者id不正确");
		} else {
			try {				
				Volunteer volunteer = this.volunteerManager.getvolunteerById(this.id);
				User user = this.userManager.getUserById(volunteer.getUser().getId());
				if ((volunteer.getUser().getRole() == User.Role.Volunteer)) {
					try {
						this.userManager.delete(user);
						this.volunteerManager.delete(volunteer);
						this.result.setMsg("删除成功");
						this.result.setSuccess(true);
					} catch (Exception e) {
						log.error(e);
						log.info("删除失败，exception");
						this.result.setMsg("删除失败，请检查网络连接是否正常！");
						this.result.setSuccess(false);
					}
				} else {
					this.result.setMsg("不能删除志愿者，有未完成的任务！");
					this.result.setSuccess(false);
					log.error("志愿者id不正确");
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			// User user = this.userManager.getUserById(this.id);
			// if ((user.getRole() == User.Role.Volunteer) && (total <= 0 )) {

			// }
		}
		return "OK";
	}

	public String modifyUser() {
		this.result = new JsonBase();
		if ((this.id == null) || (this.id.intValue() < 0)) {
			this.result.setMsg("请选择要修改的用户！");
			this.result.setSuccess(false);
			log.error("用户id不正确");
		} else {
			User user = this.userManager.getUserById(this.id);
			if ((this.loginName != null) && (!this.loginName.equals("")))
				user.setLoginName(this.loginName);
			if ((this.password != null) && (!this.password.equals(""))) {
				user.setPassword(this.password);
			}
			if ((this.sector != null) && (!this.sector.equals("")))
				user.setSection(this.sectionManager.getSectionByName(this.sector));
			if ((this.userName != null) && (!this.userName.equals(""))) {
				user.setUserName(this.userName);
			}
			try {
				this.userManager.update(user);
			} catch (Exception e) {
				this.result.setMsg("修改信息失败");
				this.result.setSuccess(false);
				log.error(e);
			}
		}
		return "OK";
	}

	public String modifyVolunteer() {
		this.result = new JsonBase();
		if ((this.id == null) || (this.id.intValue() < 0)) {
			this.result.setMsg("请选择要修改的用户！");
			this.result.setSuccess(false);
			log.error("用户id不正确");
		} else {
			Volunteer volunteer = this.volunteerManager.getvolunteerByIdA(this.id);
			if (this.IDcard != 0)
				volunteer.setIDcard(this.IDcard);
			if ((this.Specialty != null) || (!this.Specialty.equals("")))
				volunteer.setSpecialty(this.Specialty);
			if ((this.address != null) || (!this.address.equals("")))
				volunteer.setAddress(this.address);
			if ((this.phone >= 0))
				volunteer.setPhone(this.phone);
			if (this.Sex == 0 || this.Sex == 1)
				volunteer.setSex(this.Sex);
			if (this.vflag == 0 || this.vflag == 1)
				volunteer.setVflag(this.vflag);
			if (this.age >= 0 && this.age <= 100)
				volunteer.setAge(this.age);
			try {
				this.volunteerManager.update(volunteer);
			} catch (Exception e) {
				this.result.setMsg("修改信息失败");
				this.result.setSuccess(false);
				log.error(e);
			}
		}
		return "OK";
	}

	public String modifyVolunteerA() {
		this.result = new JsonBase();

		if ((this.id == null) || (this.id.intValue() < 0)) {
			this.result.setMsg("请选择要修改的用户！");
			this.result.setSuccess(false);
			log.error("用户id不正确");
		} else {
			Volunteer volunteer = this.volunteerManager.getvolunteerById(this.id);
			volunteer.setVflag(1);
			try {
				this.volunteerManager.update(volunteer);
			} catch (Exception e) {
				this.result.setMsg("修改信息失败");
				this.result.setSuccess(false);
				log.error(e);
			}
		}
		return "OK";
	}

	public String modify() {
		this.result = new JsonBase();
		if (!authorized("id")) {
			this.result.setMsg("statusfailed");
			this.result.setSuccess(false);
			log.info("获取当前用户失败");
		} 
		else {
			this.id = ((Integer) getSession().get("id"));
			if ((this.id == null) || (this.id.intValue() < 0)) {
				this.result.setMsg("用户不存在，请重新登录！");
				this.result.setSuccess(false);
				log.error("用户id不正确");
			} 
			else {
				User user = this.userManager.getUserById(this.id);
				if ((!this.loginName.equals("")) && (this.loginName != null))
					user.setLoginName(this.loginName);
				if ((!this.userName.equals("")) && (this.userName != null)) {
					user.setUserName(this.userName);
				}
				if ((!this.oldPassword.equals("")) && (this.oldPassword != null)) {
					if (!this.oldPassword.equals(user.getPassword())) {
						this.result.setMsg("旧密码输入错误！");
						this.result.setSuccess(false);
						log.error("旧密码输入错误");
					} else {
						if ((!this.password.equals("")) && (this.password != null)) {
							user.setPassword(this.password);
						}
						try {
							this.userManager.update(user);
							this.result.setMsg("修改信息成功");
							this.result.setSuccess(true);
						} catch (Exception e) {
							this.result.setMsg("修改信息失败");
							this.result.setSuccess(false);
							log.error("用户修改信息失败");
						}
					}
				} else {
					try {
						this.userManager.update(user);
					} catch (Exception e) {
						this.result.setMsg("修改信息失败");
						this.result.setSuccess(false);
						log.error("用户修改信息失败");
					}
				}
			}
		}
		return "OK";
	}

	public String getDoctorBySid() {
		this.result = new JsonBase();
		if (!authorized("sectionid")) {
		///	this.result.setMsg("statusfailed");
		//	this.result.setSuccess(false);
			log.info("获取当前用户失败");
		} else {
			try {
				Integer sid = (Integer) getSession().get("sectionid");
				List<User> list = this.userManager.getDoctorBySid(sid.intValue());
				this.result.setSuccess(true);
				this.result.setResults(list);
			} catch (Exception e) {
				this.result.setSuccess(false);
				this.result.setMsg("查询失败，请检查网络");
				log.error(e);
			}
		}
		return "OK";
	}

}
