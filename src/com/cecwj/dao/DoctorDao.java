package com.cecwj.dao;

import com.cecwj.model.Doctor;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("doctorDao")
public class DoctorDao {
	private HibernateTemplate hibernateTemplate;
	Logger log = LoggerFactory.getLogger(DoctorDao.class);

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void add(Doctor doctor) {
		this.hibernateTemplate.save(doctor);
	}

	public void update(Doctor doctor) {
		this.hibernateTemplate.update(doctor);
	}

	public void delete(Doctor doctor) {
		this.hibernateTemplate.delete(doctor);
	}

	public Doctor getdoctorByName(String name) {
		Doctor doctor = null;
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Doctor model where model.vid=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, name);
		doctor = (Doctor) query.uniqueResult();
		if (doctor != null)
			return doctor;
		return null;

	}

	public Doctor getdoctorByIdA(int vid) {
		Doctor doctor = null;
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Doctor model where model.user.id=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, vid);
		doctor = (Doctor) query.uniqueResult();
		if (doctor != null)
			return doctor;
		return null;
	}

	public List<Doctor> getAllDoctor() {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Doctor";
		List<Doctor> list = null;
		Query query = session.createQuery(queryString);
		try {
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getAllDoctorcount() {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "select count(id) from Doctor model ";
		Query query = session.createQuery(queryString);
		Long count = (Long) query.uniqueResult();
		int total = count.intValue();
		return total;
	}

	public Doctor getdoctorById(int id) {
		Doctor doctor = (Doctor) this.hibernateTemplate.get(Doctor.class,
				Integer.valueOf(id));
		return doctor;

	}

}
