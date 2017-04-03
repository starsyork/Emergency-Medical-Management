package com.cecwj.dao;

import java.util.List;

import com.cecwj.model.Apply;
import com.cecwj.model.Preparation;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("preparationDao")
public class PreparationDao {
	private HibernateTemplate hibernateTemplate;
	Logger log = LoggerFactory.getLogger(PreparationDao.class);

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void add(Preparation preparation) {
		this.hibernateTemplate.save(preparation);
	}

	public void delete(int id) {
		Preparation preparation = (Preparation) this.hibernateTemplate.get(
				Preparation.class, Integer.valueOf(id));
		this.hibernateTemplate.delete(preparation);
	}

	public void update(Preparation preparation) {
		this.hibernateTemplate.update(preparation);
	}

	public List<Preparation> getPreparation(Integer applyId) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Preparation model where model.applyId=? ";
		Query query = session.createQuery(queryString);
		query.setParameter(0, applyId);
		List<Preparation> list = null;
		try {
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
