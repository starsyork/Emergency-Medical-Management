package com.cecwj.dao;

import com.cecwj.model.Epidemic;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("epidemicDao")
public class EpidemicDao {
	private HibernateTemplate hibernateTemplate;
	Logger log = LoggerFactory.getLogger(EpidemicDao.class);

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void add(Epidemic epidemic) {
		this.hibernateTemplate.save(epidemic);
	}

	public void delete(int id) {
		Epidemic s = (Epidemic) this.hibernateTemplate.get(Epidemic.class,
				Integer.valueOf(id));
		this.hibernateTemplate.delete(s);
	}

	public List<Epidemic> getAllEpidemic() {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Epidemic";
		List<Epidemic> list = null;
		Query query = session.createQuery(queryString);
		try {
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public void update(Epidemic epidemic) {
		this.hibernateTemplate.update(epidemic);
	}

	public Epidemic getepidemic(int id) {
		Epidemic epidemic = (Epidemic) this.hibernateTemplate.get(
				Epidemic.class, id);
		return epidemic;

	}

	public int getepidemicById(int id) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "select count(id) from Epidemic model where model.id=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(id));
		Long num = (Long) query.uniqueResult();
		return num.intValue();

	}
}

/*
 * Location:
 * D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\dao
 * \SectionDao.class Java compiler version: 5 (49.0) JD-Core Version: 0.7.1
 */