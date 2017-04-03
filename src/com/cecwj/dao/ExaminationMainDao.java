package com.cecwj.dao;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component
public class ExaminationMainDao {
	private HibernateTemplate hibernateTemplate;
	static final int defaultPageSize = 40;

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public List<com.cecwj.model.ExaminationMain> getExaminationMain(int page,
			int pid) {
		String queryString = "from ExaminationMain model where model.pid=?";
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
}
