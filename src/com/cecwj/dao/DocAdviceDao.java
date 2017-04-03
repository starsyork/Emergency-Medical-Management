package com.cecwj.dao;

import com.cecwj.model.DocAdvice;
import com.cecwj.model.DocAdvice.Status;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("docAdviceDao")
public class DocAdviceDao {
	private HibernateTemplate hibernateTemplate;
	static final int defaultPageSize = 40;

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void add(DocAdvice docAdvice) {
		this.hibernateTemplate.save(docAdvice);
	}

	public void delete(DocAdvice docAdvice) {
		this.hibernateTemplate.delete(docAdvice);
	}

	public void update(DocAdvice docAdvice) {
		this.hibernateTemplate.update(docAdvice);
	}

	public List<DocAdvice> getDocAdviceByPid(int page, int pid) {
		String queryString = "from DocAdvice model where model.pid=? order by start_time desc";
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
	public List<DocAdvice> getDocAdviceByPid( int pid) {
		String queryString = "from DocAdvice model where model.pid=? order by start_time desc";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);

		query.setParameter(0, Integer.valueOf(pid));

		return query.list();
	}
	public int getDocAdviceTotalByPid(int pid) {
		String queryString = "select count(id) from DocAdvice model where model.pid=?";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(pid));
		Long count = (Long) query.uniqueResult();
		int total = count.intValue();
		return total;
	}

	public List<DocAdvice> getDocAdviceByPidAndNoStop(int pid) {
		String queryString = "from DocAdvice model where model.pid=? and (model.status=? or model.status=?) order by start_time desc";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(pid));
		query.setParameter(1, DocAdvice.Status.EFFECT);
		query.setParameter(2, DocAdvice.Status.NOEXECUTE);
		return query.list();
	}

	public List<DocAdvice> getDocAdviceByPidAndStop(int pid) {
		String queryString = "from DocAdvice model where model.pid=? and (model.status=? or model.status=?) order by start_time desc";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(pid));
		query.setParameter(1, DocAdvice.Status.EXECUTE);
		query.setParameter(2, DocAdvice.Status.STOP);
		return query.list();
	}

	public DocAdvice getDocAdviceById(int id) {
		DocAdvice docAdvice = (DocAdvice) this.hibernateTemplate.get(
				DocAdvice.class, Integer.valueOf(id));
		return docAdvice;
	}

	public List<DocAdvice> getDocAdviceByFlag(int pid, int page) {
		String queryString = "from DocAdvice model where model.flag=? and model.pid=?";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		if (page != -1) {
			query.setFirstResult((page - 1) * 40);
			query.setMaxResults(40);
		}
		query.setParameter(0, Integer.valueOf(0));
		query.setParameter(1, Integer.valueOf(pid));
		return query.list();
	}
}
