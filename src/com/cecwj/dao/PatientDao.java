package com.cecwj.dao;

import com.cecwj.model.Patient;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

@org.springframework.stereotype.Component
public class PatientDao {
	private HibernateTemplate hibernateTemplate;
	static final int defaultPageSize = 40;

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@javax.annotation.Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void add(Patient patient) {
		this.hibernateTemplate.save(patient);
	}

	public Patient findByRFID(String rfid) {
		List r = this.hibernateTemplate.find("from Patient p where p.rfid = '"
				+ rfid + "'");
		if (r.size() <= 0) {
			return null;
		}
		return (Patient) r.get(0);
	}

	public void update(Patient patient) {
		this.hibernateTemplate.update(patient);
	}

	public List<Patient> getPatientByDid(int page, int uid) {
		String queryString = "from Patient model where model.uid=? and model.status =?";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		//if (page == -1) {
			Query query = session.createQuery(queryString);
			query.setParameter(0, Integer.valueOf(uid));
			query.setParameter(1, Patient.Status.ENTER);
			return query.list();
	/*	}else{
			Query query = session.createQuery(queryString);
			query.setFirstResult((page - 1) * 40);
			query.setMaxResults(40);
			
			query.setParameter(0, Integer.valueOf(uid));
			query.setParameter(1, Patient.Status.ENTER);
			return query.list();
		}*/
	}
	
	public int getPatientTotalByDid(int uid) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "select count(id) from Patient model where model.uid=? and model.status =?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(uid));
		query.setParameter(1, Patient.Status.ENTER);
		Long count = (Long) query.uniqueResult();
		int total = count.intValue();
		return total;
	}

	public List<Patient> getPatientBySid(int page, int sid) {
		String queryString = "from Patient model where model.sid=? and model.status =?";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(sid));
		query.setParameter(1, Patient.Status.ENTER);	
		return query.list();
	}

	public List<Patient> getPatientPhoneBySid(int sid) {
		String queryString = "from Patient model where model.sid=? order by status";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(sid));
		return query.list();
	}

	public int getPatientTotalBySid(int sid) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "select count(id) from Patient model where model.sid=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(sid));
		Long count = (Long) query.uniqueResult();
		int total = count.intValue();
		return total;
	}

	public List<Patient> getPatientUndealt(int sid) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Patient model where model.sid=? and model.status = ?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(sid));
		query.setParameter(1, Patient.Status.NOASSGNED);
		return query.list();
	}

	public List<Patient> getPatientUndealtA(int sid) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "from Patient model where model.sid<>? and model.status = ?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(sid));
		query.setParameter(1, Patient.Status.NOASSGNED);
		return query.list();
	}

	public List<Patient> getAllUndealt() {
		String queryString = "from Patient model where model.status = ?";
		// String queryString =
		// "from Patient model where model.status = ? order by req_time desc";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();

		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(0));
		return query.list();
	}

	public Patient getPatientById(int id) {
		return (Patient) this.hibernateTemplate.get(Patient.class,
				Integer.valueOf(id));
	}

	public Long getPatientStatis(int sid, Patient.Wound_type wt) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "select count(*) from Patient model where model.sid=? and model.wound_type=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(sid));
		query.setParameter(1, wt);
		return (Long) query.uniqueResult();
	}

	public Long getPatientStatis(int uid, Patient.Status status) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "select count(*) from Patient model where model.uid=? and model.status=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(uid));
		query.setParameter(1, status);
		return (Long) query.uniqueResult();
	}

	public List<Patient> getPatientByDidAndFlag(int uid) {
		String queryString = "from Patient model where model.uid=? and model.status =? and model.pflag =?";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(uid));
		query.setParameter(1, Patient.Status.ENTER);
		query.setParameter(2, Integer.valueOf(0));
		return query.list();
	}

	public Long getPatientStatistime(int sid, Timestamp time) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "select count(*) from Patient model where model.sid=? and model.requestTime=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, Integer.valueOf(sid));
		query.setParameter(1, time);
		return (Long) query.uniqueResult();
	}

	public Patient getFirstPatientTime() {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = " from Patient model order by model.requestTime asc ";
		Query query = session.createQuery(queryString);
		query.setFirstResult(0);
		query.setMaxResults(1);
		return (Patient) query.uniqueResult();
	}

	public Patient getLastPatientTime() {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = " from Patient model order by model.requestTime desc ";
		Query query = session.createQuery(queryString);
		query.setFirstResult(0);
		query.setMaxResults(1);
		return (Patient) query.uniqueResult();
	}

	public Long getRTSta(int sid, Timestamp timestamp1, Timestamp timestamp2) {
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		String queryString = "select count(*) from Patient model where (model.wound_time between  ? and ?) and model.sid=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, timestamp1);
		query.setParameter(1, timestamp2);
		query.setParameter(2, Integer.valueOf(sid));
		return (Long) query.uniqueResult();
	}

	public Patient getPatientByTime() {
		// TODO Auto-generated method stub
		return null;
	}
}
