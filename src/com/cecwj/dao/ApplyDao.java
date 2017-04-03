package com.cecwj.dao;

import com.cecwj.model.Apply;
import com.cecwj.model.Apply.TypeA;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("applyDao")
public class ApplyDao {
	private HibernateTemplate hibernateTemplate;
	Logger log = LoggerFactory.getLogger(ApplyDao.class);

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void add(Apply apply) {
		this.hibernateTemplate.save(apply);
	}

	public void update(Apply apply) {
		this.hibernateTemplate.update(apply);
	}

	public void delete(Apply apply) {
		this.hibernateTemplate.delete(apply);
	}

	public Apply getApply(int id) {
		Apply apply = (Apply) this.hibernateTemplate.get(Apply.class,
				Integer.valueOf(id));
		return apply;

	}

	public Apply getApplyA(int pid) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Apply model where model.pid=? order by model.time desc ";
		Query query = session.createQuery(queryString);
		query.setParameter(0, pid);
		query.setFirstResult(0);
		query.setMaxResults(1);
		return (Apply) query.uniqueResult();

	}

	public List<Apply> getApplyByPid(int pid, TypeA type) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Apply model where model.pid= ? and model.typeA = ? and model.typeB<>? order by model.time desc ";
		Query query = session.createQuery(queryString);
		query.setParameter(0, pid);
		query.setParameter(1, type);
		query.setParameter(2, Apply.TypeB.finished);
		List<Apply> list = null;
		try {
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Apply> getAllApplyByPid(int pid, TypeA type) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Apply model where model.pid= ? and model.typeA =? order by model.time desc ";
		Query query = session.createQuery(queryString);
		query.setParameter(0, pid);
		query.setParameter(1, type);
		List<Apply> list = null;
		try {
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	
	
	public List<Apply> getApplyPid(Apply.TypeB typeb, TypeA inspection,
			TypeA examine) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Apply model where  ( model.typeA=? or model.typeA=? ) and model.typeB=? ";
		Query query = session.createQuery(queryString);		
		query.setParameter(0, inspection);
		query.setParameter(1, examine);
		query.setParameter(2, typeb);
		List<Apply> list = null;
		try {
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Apply> getSurgyApply(Apply.TypeB typeb, TypeA ops) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Apply model where model.typeB=? and model.typeA=? ";
		Query query = session.createQuery(queryString);
		query.setParameter(0, typeb);
		query.setParameter(1, ops);
		List<Apply> list = null;
		try {
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Apply> getSurgyApplyA(Apply.TypeB typeb, Apply.TypeA type) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Apply model where model.typeB=? and model.typeA=? ";
		Query query = session.createQuery(queryString);
		query.setParameter(0, typeb);
		query.setParameter(1, type);
		List<Apply> list = null;
		try {
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Apply> getUndealt(TypeA type) {
		String queryString = "from Apply model where model.typeB=? and model.typeA=?";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, Apply.TypeB.unexecuted);
		query.setParameter(1, type);
		List<Apply> list = null;
		try {
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
