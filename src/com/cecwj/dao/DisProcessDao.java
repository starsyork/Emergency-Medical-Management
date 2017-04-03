package com.cecwj.dao;

import com.cecwj.model.DisProcess;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

@org.springframework.stereotype.Component("disProcessDao")
public class DisProcessDao {
	private HibernateTemplate hibernateTemplate;
	static final int defaultPageSize = 40;

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void add(DisProcess disProcess) {
		this.hibernateTemplate.save(disProcess);
	}

	public void delete(DisProcess disProcess) {
		this.hibernateTemplate.delete(disProcess);
	}

	public void update(DisProcess disProcess) {
		this.hibernateTemplate.update(disProcess);
	}

	public List<DisProcess> getDisProcessByPid(int page, int pid) {
		String queryString = "from DisProcess model where model.pid=? order by add_date desc ";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(pid));
		if (page != -1) {
			query.setFirstResult((page - 1) * 40);
			query.setMaxResults(40);
		}
		return query.list();
	}
	public List<DisProcess> getDisProcessByPid( int pid) {
		String queryString = "from DisProcess model where model.pid=? order by add_date desc ";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(pid));
		
		return query.list();
	}
	public int getDisProcessTotalByPid(int pid) {
		String queryString = "select count(id) from DisProcess model where model.pid=?";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(pid));
		Long count = (Long) query.uniqueResult();
		int total = count.intValue();
		return total;
	}

	public DisProcess getDisProcessById(int id) {
		DisProcess disProcess = (DisProcess) this.hibernateTemplate.get(
				DisProcess.class, Integer.valueOf(id));
		return disProcess;
	}
 }


