package com.cecwj.dao;

import java.util.List;

import com.cecwj.model.Operation;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("operationDao")
public class OperationDao {
	private HibernateTemplate hibernateTemplate;
	Logger log = LoggerFactory.getLogger(OperationDao.class);

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void add(Operation operation) {
		this.hibernateTemplate.save(operation);
	}

	public void delete(Operation operation) {
		this.hibernateTemplate.delete(operation);
	}

	public void update(Operation operation) {
		this.hibernateTemplate.update(operation);
	}

	public Operation getoperationById(int id) {
		Operation operation = (Operation) this.hibernateTemplate.get(
				Operation.class, Integer.valueOf(id));
		return operation;
	}

	public List<Operation> getOperationBypid(int pid, int id) {
		String queryString = "from Operation model where model.applyId=? and model.pid=? and model.typeA<>?";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(id));
		query.setParameter(1, Integer.valueOf(pid));
		query.setParameter(2, Operation.TypeA.finished);
		return query.list();
	}
	public List<Operation> getAllOperationBypid(int pid, int id) {
		String queryString = "from Operation model where model.applyId=? and model.pid=?";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(id));
		query.setParameter(1, Integer.valueOf(pid));

		return query.list();
	}
	
	public List<Operation> getAllOperationBypid(int pid) {
		String queryString = "from Operation model where  model.pid=?";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		
		query.setParameter(0, Integer.valueOf(pid));

		return query.list();
	}
}
