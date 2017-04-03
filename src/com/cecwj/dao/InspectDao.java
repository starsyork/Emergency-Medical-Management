package com.cecwj.dao;

import java.util.List;

import com.cecwj.model.DisProcess;
import com.cecwj.model.Inspect;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("inspectDao")
public class InspectDao {
	private HibernateTemplate hibernateTemplate;
	Logger log = LoggerFactory.getLogger(InspectDao.class);

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void add(Inspect inspect) {
		this.hibernateTemplate.save(inspect);
	}

	public void delete(Inspect inspect) {
		this.hibernateTemplate.delete(inspect);
	}

	public void update(Inspect inspect) {
		this.hibernateTemplate.update(inspect);
	}

	public Inspect getinspectById(int id) {
		Inspect inspect = (Inspect) this.hibernateTemplate.get(Inspect.class,
				Integer.valueOf(id));
		return inspect;
	}

	public List<Inspect> getInspectBypid(int pid, int id) {
		String queryString = "from Inspect model where model.applyId=? and model.pid=? and model.typeB<>?";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(id));
		query.setParameter(1, Integer.valueOf(pid));
		query.setParameter(2, Inspect.TypeB.finished);
		return query.list();
	}
	public List<Inspect> getAllInspectBypid(int pid, int id) {
		String queryString = "from Inspect model where model.applyId=? and model.pid=?";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(id));
		query.setParameter(1, Integer.valueOf(pid));

		return query.list();
	}
	public List<Inspect> getAllInspectBypid(int pid) {
		String queryString = "from Inspect model where  model.pid=?";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		
		query.setParameter(0, Integer.valueOf(pid));

		return query.list();
	}
}
