package com.cecwj.dao;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.cecwj.model.Edetail;

@Component("edetailDao")
public class EdetailDao {
	private HibernateTemplate hibernateTemplate;
	static final int defaultPageSize = 40;

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public List<Edetail> getEdetail(int page, int mid) {
		String queryString = "from Edetail model where model.mid=?";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(mid));
		if (page != -1) {
			query.setFirstResult((page - 1) * 40);
			query.setMaxResults(40);
		}
		return query.list();
	}
	public List<Edetail> getEdetail( int mid) {
		String queryString = "from Edetail model where model.mid=?";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(mid));

		return query.list();
	}
	public int getEdetailTotal(int mid) {
		String queryString = "select count(id) from Edetail model where model.mid=?";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(mid));
		Long count = (Long) query.uniqueResult();
		int total = count.intValue();
		return total;
	}

	public void add(Edetail edetail) {
		this.hibernateTemplate.save(edetail);

	}

	public List<Edetail> getEdetailBypid(int pid, int id) {

		String queryString = "from Edetail model where model.applyId=? and model.mid=? and model.typeB <>?";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(id));
		query.setParameter(1, Integer.valueOf(pid));
		query.setParameter(2, Edetail.TypeB.finished);
		return query.list();
	}

	public Edetail get(int id) {
		Edetail edetail = (Edetail) this.hibernateTemplate.get(Edetail.class,
				id);
		return edetail;
	}

	public void update(Edetail edetail) {
		this.hibernateTemplate.update(edetail);
	}

	public List<Edetail> getAllEdetailBypid(int pid, int id) {
		
		String queryString = "from Edetail model where model.applyId=? and model.mid=?";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(pid));
		query.setParameter(1, Integer.valueOf(id));

		return query.list();
	}
}
