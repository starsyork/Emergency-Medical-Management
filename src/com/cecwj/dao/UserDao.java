package com.cecwj.dao;

import com.cecwj.model.User;
import com.cecwj.model.User.Role;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("userDao")
public class UserDao {
	private HibernateTemplate hibernateTemplate;
	static final int defaultPageSize = 40;

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public User getUserById(int id) {
		User user = (User) this.hibernateTemplate.get(User.class,
				Integer.valueOf(id));
		return user;
	}

	public User getUserByDeviceId(String deviceId) {
		String queryString = "from User as model where model.deviceId=?";
		List<User> users = this.hibernateTemplate.find(queryString, deviceId);
		if (users.size() >= 1) {
			return (User) users.get(0);
		}
		return null;
	}

	public User getUserByUsernameAndRole(String username, User.Role role) {
		String queryString = "from User as model where model.loginName=? and model.role=?";
		List<User> users = this.hibernateTemplate.find(queryString,
				new Object[] { username, role });
		if (users.size() != 1) {
			return null;
		}
		return (User) users.get(0);
	}

	public void add(User user) {
		try {
			this.hibernateTemplate.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(User user) {
		this.hibernateTemplate.update(user);
	}

	public void delete(User user) {
		this.hibernateTemplate.delete(user);
	}

	public List<User> getUserByRoleA(int page, User.Role role) {
		String queryString = "from User model where model.role=? or model.role=? or model.role=? or model.role=?";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setFirstResult((page - 1) * 40);
		query.setMaxResults(40);
		query.setParameter(0, role);
		query.setParameter(1, User.Role.EXAMINATION);
		query.setParameter(2, User.Role.OPERATION);
		query.setParameter(3, User.Role.TRANSPORT);
		return query.list();
	}
	public List<User> getUserByRole(int page, User.Role role) {
		String queryString = "from User model where model.role=? ";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setFirstResult((page - 1) * 40);
		query.setMaxResults(40);
		query.setParameter(0, role);

		return query.list();
	}
	public int getUserTotalByRole(User.Role role) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "select count(id) from User model where model.role=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, role);
		Long count = (Long) query.uniqueResult();
		int total = count.intValue();
		return total;
	}

	public int getUserTotalBySid(int sid) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "select count(id) from User model where model.section.id=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(sid));
		Long num = (Long) query.uniqueResult();
		return num.intValue();
	}

	public List<User> getDoctorBySid(int sid) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from User model where model.section.id=? and model.role=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(sid));
		query.setParameter(1, User.Role.DOCTOR);
		return query.list();
	}

	public List<User> getAllDoctors() {
		String queryString = "from User model where model.role=?";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, User.Role.DOCTOR);
		return query.list();
	}

	public boolean getUserByName(String loginName) {
		String queryString = "from User model where model.loginName=?";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, loginName);
		if (query.uniqueResult() != null)
			return true;
		return false;
	}

	public User getUserByUsernameAndPwd(String username, String password) {
		String queryString = "from User as model where model.loginName=? and model.password=?";
		List<User> users = this.hibernateTemplate.find(queryString,
				new Object[] { username, password });
		if (users.size() != 1) {
			return null;
		}
		return (User) users.get(0);
	}

	public User getLoginFlag(String loginName) {
		String queryString = "from User as model where model.loginName=? ";
		List<User> users = this.hibernateTemplate.find(queryString,
				new Object[] { loginName });
		if (users.size() != 1) {
			return null;
		}
		return (User) users.get(0);

	}
}
