package com.cecwj.dao;

import com.cecwj.model.PCondition;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

@org.springframework.stereotype.Component("pConditionDao")
public class PConditionDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void add(PCondition condition) {
		this.hibernateTemplate.save(condition);
	}

	public void update(PCondition condition) {
		this.hibernateTemplate.update(condition);
	}

	public void delete(int id) {
		PConditionDao condition = (PConditionDao) this.hibernateTemplate.get(
				PCondition.class, Integer.valueOf(id));
		this.hibernateTemplate.delete(condition);
	}

	public int getTotalNum(int pid) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "select count(id) from PCondition model where model.pid=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(pid));
		Long count = (Long) query.uniqueResult();
		int num = count.intValue();
		return num;
	}

	public int getTotalPage(int pid) {
		int num = getTotalNum(pid);
		if (num % 40 == 0)
			return num / 40;
		return num / 40 + 1;
	}

	public List<PCondition> getPConditions(int page, int pid) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from PCondition model where model.pid=? order by condition_time desc";
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(pid));
		if (page != -1) {
			query.setFirstResult((page - 1) * 40);
			query.setMaxResults(40);
		}
		return query.list();
	}

	public List<PCondition> getPConditions(int pid) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from PCondition model where model.pid=? order by condition_time desc";
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(pid));

		return query.list();
	}

	public PCondition getPConditionById(int id) {
		return (PCondition) this.hibernateTemplate.get(PCondition.class,
				Integer.valueOf(id));
	}
	
}




