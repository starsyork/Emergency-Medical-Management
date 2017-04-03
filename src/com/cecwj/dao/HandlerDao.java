package com.cecwj.dao;

import com.cecwj.model.Handler;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("handlerDao")
public class HandlerDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void addHandler(Handler handler) {
		this.hibernateTemplate.save(handler);
	}

	public void deleteHandler(int id) {
		Handler handler = (Handler) this.hibernateTemplate.get(Handler.class,
				Integer.valueOf(id));
		this.hibernateTemplate.delete(handler);
	}

	public void deleteHandlers(int id) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "delete from Handler model where model.oid=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(id));
		query.executeUpdate();
	}

	public List<Handler> getHandlers(int page, int oid) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Handler model where model.oid=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(oid));
		query.setFirstResult((page - 1) * 40);
		query.setMaxResults(40);
		return query.list();
	}

	public int getTotalNum(int oid) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "select count(id) from Handler model where model.oid=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(oid));
		Long count = (Long) query.uniqueResult();
		int num = count.intValue();
		return num;
	}

	public int getTotalPage(int oid) {
		int num = getTotalNum(oid);
		if (num % 40 == 0)
			return num / 40;
		return num / 40 + 1;
	}
}
