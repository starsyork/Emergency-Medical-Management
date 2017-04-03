package com.cecwj.dao;

import com.cecwj.model.Task;
import com.cecwj.model.Task.TypeA;
import com.cecwj.model.TaskAndVolunteer;
import com.cecwj.model.Volunteer;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("taskandvolunteerDao")
public class TaskAndVolunteerDao {
	private HibernateTemplate hibernateTemplate;
	Logger log = LoggerFactory.getLogger(TaskAndVolunteerDao.class);

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void add(TaskAndVolunteer taskandvolunteer) {
		this.hibernateTemplate.save(taskandvolunteer);
	}

	public void update(TaskAndVolunteer taskandvolunteer) {
		this.hibernateTemplate.update(taskandvolunteer);
	}

	public void delete(TaskAndVolunteer taskandvolunteer) {
		this.hibernateTemplate.delete(taskandvolunteer);
	}

	public TaskAndVolunteer getTaskAndVolunteer(int id) {
		TaskAndVolunteer taskandvolunteer = (TaskAndVolunteer) this.hibernateTemplate
				.get(TaskAndVolunteer.class, Integer.valueOf(id));
		return taskandvolunteer;

	}

	public List<TaskAndVolunteer> getTaskByTaskId(int id) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from TaskAndVolunteer model where model.task.id= ? ";
		Query query = session.createQuery(queryString);
		query.setParameter(0, id);
		List<TaskAndVolunteer> list = null;
		try {
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	public TaskAndVolunteer getTaskAndVolunteerByVid(int ts) {
		TaskAndVolunteer taskandvolunteer = null;
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from TaskAndVolunteer model where model.volunteerid= ? ";
		Query query = session.createQuery(queryString);
		query.setParameter(0, ts);
		taskandvolunteer = (TaskAndVolunteer) query.uniqueResult();
		if (taskandvolunteer != null)
			return taskandvolunteer;
		return null;

	}

	public TaskAndVolunteer getTaskByVolunteer(int id, int ts) {
		TaskAndVolunteer taskandvolunteer = null;
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from TaskAndVolunteer model where model.task.id=? and model.volunteerid=? ";
		Query query = session.createQuery(queryString);
		query.setParameter(0, id);
		query.setParameter(1, ts);
		taskandvolunteer = (TaskAndVolunteer) query.uniqueResult();
		if (taskandvolunteer != null)
			return taskandvolunteer;
		return null;
	}

	public List<TaskAndVolunteer> getTaskByStatus(int id,
			TaskAndVolunteer.TypeA finished) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from TaskAndVolunteer model where model.task.id=? and model.typeA<>?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, id);
		query.setParameter(1, finished);
		List<TaskAndVolunteer> list = null;
		try {
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<TaskAndVolunteer> getTaskByVid(int id) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from TaskAndVolunteer model where model.volunteerid=? and model.typeA<>?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, id);
		query.setParameter(1, TaskAndVolunteer.TypeA.finished);
		List<TaskAndVolunteer> list = null;
		try {
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
