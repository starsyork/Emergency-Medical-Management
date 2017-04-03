package com.cecwj.dao;

import com.cecwj.model.Injury;
import com.cecwj.model.Measure;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component
public class InjuryDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@javax.annotation.Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public Measure getMeasure(int id) {
		return (Measure) this.hibernateTemplate.get(Measure.class,
				Integer.valueOf(id));
	}

	public void addInjury(Injury injury) {
		this.hibernateTemplate.save(injury);
	}

	public Injury getInjury(int pid) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Injury model where model.pid=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(pid));
		return (Injury) query.uniqueResult();
	}
}
