package com.cecwj.dao;

import com.cecwj.model.Hospital;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("hospitalDao")
public class HospitalDao {
	private HibernateTemplate hibernateTemplate;
	Logger log = LoggerFactory.getLogger(HospitalDao.class);

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public Hospital getHospitalById(int sid) {
		Hospital hospital = (Hospital) this.hibernateTemplate.get(Hospital.class,
				Integer.valueOf(sid));
		return hospital;
	}

	public Hospital getHospitalByName(String hospName) {
		Hospital hospital = null;
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Hospital model where model.hospName=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, hospName);
		hospital = (Hospital) query.uniqueResult();
		if (hospital != null)
			return hospital;
		return null;
	}

	public void add(Hospital hospital) {
		this.hibernateTemplate.save(hospital);
	}

	public void delete(int id) {
		Hospital s = (Hospital) this.hibernateTemplate.get(Hospital.class,
				Integer.valueOf(id));
		this.hibernateTemplate.delete(s);
	}

	public List<Hospital> getAllHospital() {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Hospital";
		List<Hospital> list = null;
		Query query = session.createQuery(queryString);
		try {
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public int getTotalHospital() {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "select count(id) from Hospital";
		Query query = session.createQuery(queryString);
		Long count = (Long) query.uniqueResult();
		int total = count.intValue();
		return total;
	}

	public void update(Hospital hospital) {
		this.hibernateTemplate.update(hospital);
	}
}
