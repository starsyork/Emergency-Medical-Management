package com.cecwj.dao;

import java.util.List;

import com.cecwj.model.Apply;
import com.cecwj.model.Psychology;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("psychologyDao")
public class PsychologyDao {
	private HibernateTemplate hibernateTemplate;
	Logger log = LoggerFactory.getLogger(PsychologyDao.class);

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public Psychology get(int id) {
		Psychology psychology = (Psychology) this.hibernateTemplate.get(
				Psychology.class, id);
		return psychology;
	}

	public void add(Psychology psychology) {
		this.hibernateTemplate.save(psychology);
	}

	public void update(Psychology psychology) {
		this.hibernateTemplate.update(psychology);
	}

	public void delete(Psychology psychology) {
		this.hibernateTemplate.delete(psychology);
	}

	public List<Psychology> getPsychologyByPid(int pid) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Psychology model where model.pid= ?  ";
		Query query = session.createQuery(queryString);
		query.setParameter(0, pid);
		List<Psychology> list = null;
		try {
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
