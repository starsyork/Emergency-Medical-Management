package com.cecwj.dao;

import com.cecwj.model.Section;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("sectionDao")
public class SectionDao {
	private HibernateTemplate hibernateTemplate;
	Logger log = LoggerFactory.getLogger(SectionDao.class);

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public Section getSectionById(int sid) {
		Section section = (Section) this.hibernateTemplate.get(Section.class,
				Integer.valueOf(sid));
		return section;
	}

	public Section getSectionByName(String secName) {
		Section section = null;
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Section model where model.secName=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, secName);
		section = (Section) query.uniqueResult();
		if (section != null)
			return section;
		return null;
	}

	public void add(Section section) {
		this.hibernateTemplate.save(section);
	}

	public void delete(int id) {
		Section s = (Section) this.hibernateTemplate.get(Section.class,
				Integer.valueOf(id));
		this.hibernateTemplate.delete(s);
	}

	public List<Section> getAllSection() {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Section";
		List<Section> list = null;
		Query query = session.createQuery(queryString);
		try {
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public int getTotalSection() {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "select count(id) from Section";
		Query query = session.createQuery(queryString);
		Long count = (Long) query.uniqueResult();
		int total = count.intValue();
		return total;
	}

	public void update(Section section) {
		this.hibernateTemplate.update(section);
	}
}
