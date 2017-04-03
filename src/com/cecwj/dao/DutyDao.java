package com.cecwj.dao;

import java.util.List;

import com.cecwj.model.Duty;
import com.cecwj.model.Psychology;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("dutyDao")
public class DutyDao {
	private HibernateTemplate hibernateTemplate;
	Logger log = LoggerFactory.getLogger(DutyDao.class);

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public Duty get(int id) {
		Duty duty = (Duty) this.hibernateTemplate.get(Duty.class, id);
		return duty;
	}

	public void add(Duty duty) {
		this.hibernateTemplate.save(duty);
	}

	public void update(Duty duty) {
		this.hibernateTemplate.update(duty);
	}

	public void delete(Duty duty) {
		this.hibernateTemplate.delete(duty);
	}

	public List<Duty> getDutyBySection(int section) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Duty model where model.sectionId= ?  ";
		Query query = session.createQuery(queryString);
		query.setParameter(0, section);
		List<Duty> list = null;
		try {
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
