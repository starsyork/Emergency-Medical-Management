package com.cecwj.dao;

import com.cecwj.model.Dict;
import com.cecwj.model.Patient;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component
public class DictDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		/* 21 */return this.hibernateTemplate;
	}

	@Resource
	/* 25 */public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public List<Dict> getDicts(String type2) {
		
		/* 32 */List<Dict> ds = new ArrayList();
		/* 33 */if (type2.equals("检验")) {
			/* 34 */String queryString = "from Dict model where  model.clas=?";
			/* 35 */Session session = this.hibernateTemplate
					.getSessionFactory().getCurrentSession();
			/* 36 */Query query = session.createQuery(queryString);

			/* 39 */query.setParameter(0, "C");
			/* 40 */ds = query.list();
			/* 41 */} else if (type2.equals("检查")) {
			/* 42 */String queryString = "from Dict model where model.clas=?";
			/* 43 */Session session = this.hibernateTemplate
					.getSessionFactory().getCurrentSession();
			/* 44 */Query query = session.createQuery(queryString);

			/* 47 */query.setParameter(0, "D");
			/* 48 */ds = query.list();
			/* 49 */} else if (type2.equals("处置")) {
			/* 50 */String queryString = "from Dict model where  model.clas=?";
			/* 51 */Session session = this.hibernateTemplate
					.getSessionFactory().getCurrentSession();
			/* 52 */Query query = session.createQuery(queryString);

			/* 55 */query.setParameter(0, "E");
			/* 56 */ds = query.list();
			/* 57 */} else if (type2.equals("手术")) {
			/* 58 */String queryString = "from Dict model where  model.clas=?";
			/* 59 */Session session = this.hibernateTemplate
					.getSessionFactory().getCurrentSession();
			/* 60 */Query query = session.createQuery(queryString);

			/* 63 */query.setParameter(0, "F");
			/* 64 */ds = query.list();
			/* 65 */} else if (type2.equals("麻醉")) {
			/* 66 */String queryString = "from Dict model where  model.clas=?";
			/* 67 */Session session = this.hibernateTemplate
					.getSessionFactory().getCurrentSession();
			/* 68 */Query query = session.createQuery(queryString);

			/* 71 */query.setParameter(0, "G");
			/* 72 */ds = query.list();
			/* 73 */} else if (type2.equals("膳食")) {
			/* 74 */String queryString = "from Dict model where  model.clas=?";
			/* 75 */Session session = this.hibernateTemplate
					.getSessionFactory().getCurrentSession();
			/* 76 */Query query = session.createQuery(queryString);

			/* 79 */query.setParameter(0, "I");
			/* 80 */ds = query.list();
			/* 81 */} else if (type2.equals("护理")) {
			/* 82 */String queryString = "from Dict model where  model.clas=?";
			/* 83 */Session session = this.hibernateTemplate
					.getSessionFactory().getCurrentSession();
			/* 84 */Query query = session.createQuery(queryString);

			/* 87 */query.setParameter(0, "H");
			/* 88 */ds = query.list();
		}
		/* 90 */return ds;
	}
	public List<Dict> getDicts(String term, String type2) {
		/* 31 */String tp = term + "%";
		/* 32 */List<Dict> ds = new ArrayList();
		/* 33 */if (type2.equals("检验")) {
			/* 34 */String queryString = "from Dict model where (model.name like ? or model.code like ?) and model.clas=?";
			/* 35 */Session session = this.hibernateTemplate
					.getSessionFactory().getCurrentSession();
			/* 36 */Query query = session.createQuery(queryString);
			/* 37 */query.setParameter(0, tp);
			/* 38 */query.setParameter(1, tp);
			/* 39 */query.setParameter(2, "C");
			/* 40 */ds = query.list();
			/* 41 */} else if (type2.equals("检查")) {
			/* 42 */String queryString = "from Dict model where (model.name like ? or model.code like ?) and model.clas=?";
			/* 43 */Session session = this.hibernateTemplate
					.getSessionFactory().getCurrentSession();
			/* 44 */Query query = session.createQuery(queryString);
			/* 45 */query.setParameter(0, tp);
			/* 46 */query.setParameter(1, tp);
			/* 47 */query.setParameter(2, "D");
			/* 48 */ds = query.list();
			/* 49 */} else if (type2.equals("处置")) {
			/* 50 */String queryString = "from Dict model where (model.name like ? or model.code like ?) and model.clas=?";
			/* 51 */Session session = this.hibernateTemplate
					.getSessionFactory().getCurrentSession();
			/* 52 */Query query = session.createQuery(queryString);
			/* 53 */query.setParameter(0, tp);
			/* 54 */query.setParameter(1, tp);
			/* 55 */query.setParameter(2, "E");
			/* 56 */ds = query.list();
			/* 57 */} else if (type2.equals("手术")) {
			/* 58 */String queryString = "from Dict model where (model.name like ? or model.code like ?) and model.clas=?";
			/* 59 */Session session = this.hibernateTemplate
					.getSessionFactory().getCurrentSession();
			/* 60 */Query query = session.createQuery(queryString);
			/* 61 */query.setParameter(0, tp);
			/* 62 */query.setParameter(1, tp);
			/* 63 */query.setParameter(2, "F");
			/* 64 */ds = query.list();
			/* 65 */} else if (type2.equals("麻醉")) {
			/* 66 */String queryString = "from Dict model where (model.name like ? or model.code like ?) and model.clas=?";
			/* 67 */Session session = this.hibernateTemplate
					.getSessionFactory().getCurrentSession();
			/* 68 */Query query = session.createQuery(queryString);
			/* 69 */query.setParameter(0, tp);
			/* 70 */query.setParameter(1, tp);
			/* 71 */query.setParameter(2, "G");
			/* 72 */ds = query.list();
			/* 73 */} else if (type2.equals("膳食")) {
			/* 74 */String queryString = "from Dict model where (model.name like ? or model.code like ?) and model.clas=?";
			/* 75 */Session session = this.hibernateTemplate
					.getSessionFactory().getCurrentSession();
			/* 76 */Query query = session.createQuery(queryString);
			/* 77 */query.setParameter(0, tp);
			/* 78 */query.setParameter(1, tp);
			/* 79 */query.setParameter(2, "I");
			/* 80 */ds = query.list();
			/* 81 */} else if (type2.equals("护理")) {
			/* 82 */String queryString = "from Dict model where (model.name like ? or model.code like ?) and model.clas=?";
			/* 83 */Session session = this.hibernateTemplate
					.getSessionFactory().getCurrentSession();
			/* 84 */Query query = session.createQuery(queryString);
			/* 85 */query.setParameter(0, tp);
			/* 86 */query.setParameter(1, tp);
			/* 87 */query.setParameter(2, "H");
			/* 88 */ds = query.list();
		}
		/* 90 */return ds;
	}
	public Dict getDictsByContent(String content) {
		/* 95 */String queryString = "from Dict as model where model.name=?";
		/* 96 */List<Dict> ts = this.hibernateTemplate.find(queryString,
				content);
		/* 97 */if (ts.size() != 1) {
			/* 98 */return null;
		}
		/* 100 */return (Dict) ts.get(0);
	}

	public Dict getDictsByContentA(String term) {
		String tp = term + "%";
		String queryString = "from Dict model where (model.name like ? or model.code like ?) and model.clas=?";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, tp);
		query.setParameter(1, tp);
		query.setParameter(2, "C");
		query.setFirstResult(0);
		query.setMaxResults(1);
		return (Dict) query.uniqueResult();
	}
}
/*
 * Location:
 * D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\dao
 * \DictDao.class Java compiler version: 5 (49.0) JD-Core Version: 0.7.1
 */