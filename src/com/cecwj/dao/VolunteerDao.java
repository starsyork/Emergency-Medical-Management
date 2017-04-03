package com.cecwj.dao;

import com.cecwj.model.Volunteer;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("volunteerDao")
public class VolunteerDao {
	private HibernateTemplate hibernateTemplate;
	Logger log = LoggerFactory.getLogger(VolunteerDao.class);

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void add(Volunteer volunteer) {
		this.hibernateTemplate.save(volunteer);
	}

	public void update(Volunteer volunteer) {
		this.hibernateTemplate.update(volunteer);
	}

	public void delete(Volunteer volunteer) {
		this.hibernateTemplate.delete(volunteer);
	}

	public Volunteer getvolunteerByVid(int vid) {
		Volunteer volunteer = null;
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Volunteer model where model.vid=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, vid);
		volunteer = (Volunteer) query.uniqueResult();
		if (volunteer != null)
			return volunteer;
		return null;

	}

	public Volunteer getvolunteerByIdA(int vid) {
		Volunteer volunteer = null;
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Volunteer model where model.user.id=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, vid);
		volunteer = (Volunteer) query.uniqueResult();
		if (volunteer != null)
			return volunteer;
		return null;
	}

	public List<Volunteer> getAllVolunteer() {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Volunteer model where model.vflag=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, 1);
		List<Volunteer> list = null;
		try {
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Volunteer> getunderVolunteer() {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Volunteer model where model.vflag=? ";
		Query query = session.createQuery(queryString);
		query.setParameter(0, 0);
		List<Volunteer> list = null;
		try {
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getAllVolunteercount() {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "select count(id) from Volunteer model ";
		Query query = session.createQuery(queryString);
		Long count = (Long) query.uniqueResult();
		int total = count.intValue();
		return total;
	}

	public Volunteer getvolunteerById(int id) {
		Volunteer volunteer = (Volunteer) this.hibernateTemplate.get(
				Volunteer.class, Integer.valueOf(id));
		return volunteer;

	}

}
