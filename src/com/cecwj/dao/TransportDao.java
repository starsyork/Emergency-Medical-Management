package com.cecwj.dao;

import java.util.List;

import com.cecwj.model.Apply;
import com.cecwj.model.Transport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("transportDao")
public class TransportDao {
	private HibernateTemplate hibernateTemplate;
	Logger log = LoggerFactory.getLogger(TransportDao.class);

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void add(Transport transport) {
		this.hibernateTemplate.save(transport);
	}

	public void delete(Transport transport) {
		this.hibernateTemplate.delete(transport);
	}

	public void update(Transport transport) {
		this.hibernateTemplate.update(transport);
	}

	public Transport gettransportById(int id) {
		Transport transport = (Transport) this.hibernateTemplate.get(
				Transport.class, Integer.valueOf(id));
		return transport;
	}

	public List<Transport> getTransportBypid(int pid, int id) {
		String queryString = "from Transport model where model.applyId=? and model.pid=? and model.typeA<>?";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(id));
		query.setParameter(1, Integer.valueOf(pid));
		query.setParameter(2, Transport.TypeA.finished);
		return query.list();
	}

	public List<Transport> getAllTransportBypid(int pid, int id) {
		String queryString = "from Transport model where model.applyId=? and model.pid=?";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(id));
		query.setParameter(1, Integer.valueOf(pid));

		return query.list();
	}
	public Transport getTransportByApply(int id) {
		String queryString = "from Transport model where model.applyId=? ";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(id));
		return (Transport) query.uniqueResult();
	}
	public List<Transport> getTransportByApplyA(int id) {
		String queryString = "from Transport model where model.applyId=? ";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(id));
		return query.list();
	}
	public List<Transport> getTransportByTid(Integer tid) {
		String queryString = "from Transport model where  model.tid=? and  model.typeA<>?";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(tid));
		query.setParameter(1, Transport.TypeA.finished);
		return query.list();
	}


}
