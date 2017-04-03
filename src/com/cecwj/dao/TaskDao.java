package com.cecwj.dao;

import com.cecwj.model.Task;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("taskDao")
public class TaskDao {
	private HibernateTemplate hibernateTemplate;
	Logger log = LoggerFactory.getLogger(TaskDao.class);

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void add(Task task) {
		this.hibernateTemplate.save(task);
	}

	public void update(Task task) {
		this.hibernateTemplate.update(task);
	}

	public void delete(Task task) {
		this.hibernateTemplate.delete(task);
	}

	public Task getTask(int id) {
		Task apply = (Task) this.hibernateTemplate.get(Task.class,
				Integer.valueOf(id));
		return apply;

	}

	public List<Task> getTask() {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Task model where model.typeA<>? and model.number<>?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, Task.TypeA.finished);
		query.setParameter(1, 0);
		List<Task> list = null;
		try {
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}
	public List<Task> getAllTask() {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Task model ";
		Query query = session.createQuery(queryString);

		List<Task> list = null;
		try {
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}
}
