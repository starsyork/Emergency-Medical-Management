             package com.cecwj.dao;
             
             import com.cecwj.model.Bed;
             import java.util.List;
             import javax.annotation.Resource;
             import org.hibernate.Query;
             import org.hibernate.Session;
             import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
             
             @org.springframework.stereotype.Component("bedDao")
             public class BedDao
             {
               private HibernateTemplate hibernateTemplate;
               public HibernateTemplate getHibernateTemplate(){
/*  18 */     return this.hibernateTemplate;
               }
               
               @Resource
/*  22 */   public void setHibernateTemplate(HibernateTemplate hibernateTemplate) { 
            			this.hibernateTemplate = hibernateTemplate; 
             		}
               
               public List<Bed> getBeds(int sid)
               {
/*  26 */     Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
/*  27 */     String queryString = "from Bed model where model.sectionid=? and model.flag=? order by status";
/*  28 */     Query query = session.createQuery(queryString);
/*  29 */     query.setParameter(0, Integer.valueOf(sid));
/*  30 */     query.setParameter(1, Integer.valueOf(1));
/*  31 */     return query.list();
               }
               
               public List<Bed> getBedsWeb(int sid) {
/*  35 */     Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
/*  36 */     String queryString = "from Bed model where model.sectionid=? and model.flag=? order by status desc";
/*  37 */     Query query = session.createQuery(queryString);
/*  38 */     query.setParameter(0, Integer.valueOf(sid));
/*  39 */     query.setParameter(1, Integer.valueOf(1));
/*  40 */     return query.list();
               }
               
/*  43 */   public List<Bed> getBedsAdmin(int sid) {
			  Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
/*  44 */    String queryString = "from Bed model where model.sectionid=? and model.flag=? order by id";
			//   String queryString = "from Bed model where model.sectionid=?";
/*  45 */     Query query = session.createQuery(queryString);
/*  46 */     query.setParameter(0, Integer.valueOf(sid));
/*  47 */     query.setParameter(1, Integer.valueOf(1));
		//	System.out.println(query.list());
/*  48 */     return query.list();
               }
               //获取未分配床位
               public List<Bed> getUnAllocBed(int sid)
               {
/*  53 */     Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
/*  54 */     String queryString = "from Bed model where model.sectionid=? and model.flag=? and model.status=?";
/*  55 */     Query query = session.createQuery(queryString);
/*  56 */     query.setParameter(0, Integer.valueOf(sid));
/*  57 */     query.setParameter(1, Integer.valueOf(1));
/*  58 */     query.setParameter(2, com.cecwj.model.Bed.Status.EMPTY);
/*  59 */     return query.list();
               }
               
               public Bed getBedById(int id) {
/*  63 */     return (Bed)this.hibernateTemplate.get(Bed.class, Integer.valueOf(id));
               }
               
             
/*  67 */   public void updateBed(Bed bed) { 
				this.hibernateTemplate.update(bed); 
				}
               
               public Bed getBedByPid(int pid) {
/*  70 */     Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
/*  71 */     String queryString = "from Bed model where model.patient.id=?";
/*  72 */     Query query = session.createQuery(queryString);
/*  73 */     query.setParameter(0, Integer.valueOf(pid));
/*  74 */     return (Bed)query.uniqueResult();
               }
               
             
/*  78 */   public void add(Bed bed) { 
				this.hibernateTemplate.save(bed); 
			}
               
               public Bed getDeletedBed() {
/*  81 */     Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
/*  82 */     String queryString = "select min(id) from Bed model where model.flag=0";
/*  83 */     Query query = session.createQuery(queryString);
/*  84 */     Integer num = (Integer)query.uniqueResult();
/*  85 */     if (num != null) {
/*  86 */       return (Bed)this.hibernateTemplate.get(Bed.class, num);
                 }
/*  88 */     return null;
               }
               
               public void delete(Bed bed) {
/*  92 */     this.hibernateTemplate.delete(bed);
               }
               
               public int getBedsNum(int sid) {
/*  96 */     Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
/*  97 */     String queryString = "select count(id) from Bed model where model.sectionid=? and model.flag=1";
/*  98 */     Query query = session.createQuery(queryString);
/*  99 */     query.setParameter(0, Integer.valueOf(sid));
/* 100 */     Long count = (Long)query.uniqueResult();
/* 101 */     return count.intValue();
               }
             }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\dao\BedDao.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */