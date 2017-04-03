package com.cecwj.dao;

import com.cecwj.model.Disposal;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

@org.springframework.stereotype.Component
public class DisposalDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void addDisposal(Disposal disposal) {
		this.hibernateTemplate.save(disposal);
	}

	public Disposal getDisposal(int id) {
		return (Disposal) this.hibernateTemplate.get(Disposal.class,
				Integer.valueOf(id));
	}

	public int getTotalNum(int pid) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "select count(id) from Disposal model where model.pid=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(pid));
		Long total = (Long) query.uniqueResult();
		return total.intValue();
	}

	public List<Disposal> getDisposals(int pid, int page) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Disposal model where model.pid=?";
		Query query = session.createQuery(queryString);
		if (page != -1) {
			query.setParameter(0, Integer.valueOf(pid));
			query.setFirstResult((page - 1) * 40);
			query.setMaxResults(40);
		} else {
			query.setParameter(0, Integer.valueOf(pid));
		}
		return query.list();
	}
	
	public List<Disposal> getDisposalsA(int pid) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Disposal model where model.pid=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(pid));
		return query.list();
	}
}

