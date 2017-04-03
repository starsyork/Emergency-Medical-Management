package com.cecwj.dao;

import com.cecwj.model.TermAll;
import com.cecwj.model.Volunteer;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component
public class TermAllDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public List<TermAll> getTerms(String term) {
		String tp = term + "%";
		String queryString = "from TermAll model where model.name like ? or model.code like ?";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, tp);
		query.setParameter(1, tp);
		return query.list();
	}

	public TermAll getTermsByContent(String content) {
		String queryString = "from TermAll as model where model.name=?";
		List<TermAll> ts = this.hibernateTemplate.find(queryString, content);
		if (ts.size() != 1) {
			return null;
		}
		return (TermAll) ts.get(0);
	}

	public List<TermAll> getAlldrug() {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from TermAll";
		Query query = session.createQuery(queryString);
		return query.list();
	}

	public TermAll getDrugById(int id) {
		TermAll termall = (TermAll) this.hibernateTemplate.get(TermAll.class,
				id);
		return termall;
	}

	public TermAll getDrugByIdA(String id) {
		String queryString = "from TermAll as model where model.drugcode=?";
		List<TermAll> ts = this.hibernateTemplate.find(queryString, id);
		if (ts.size() != 1) {
			return null;
		}
		return (TermAll) ts.get(0);

	}

	public void update(TermAll termall) {
		this.hibernateTemplate.update(termall);
	}

	public void delete(TermAll termall) {
		this.hibernateTemplate.delete(termall);
	}

	public void add(TermAll termall) {
		this.hibernateTemplate.save(termall);
	}

}
