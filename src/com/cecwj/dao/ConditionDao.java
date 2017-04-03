/*    */ package com.cecwj.dao;
/*    */ 
/*    */ import com.cecwj.model.Condition;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.SessionFactory;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ 
/*    */ @org.springframework.stereotype.Component("conditionDao")
/*    */ public class ConditionDao
/*    */ {
/*    */   private HibernateTemplate hibernateTemplate;
/*    */   
/*    */   public HibernateTemplate getHibernateTemplate()
/*    */   {
/* 18 */     return this.hibernateTemplate;
/*    */   }
/*    */   
/*    */   @Resource
/* 22 */   public void setHibernateTemplate(HibernateTemplate hibernateTemplate) 
			{ 
				this.hibernateTemplate = hibernateTemplate; 
			}
/*    */   
/*    */   public void add(Condition condition)
/*    */   {
/* 26 */     this.hibernateTemplate.save(condition);
/*    */   }
/*    */   
/*    */   public void delete(int id) {
/* 30 */     ConditionDao condition = (ConditionDao)this.hibernateTemplate.get(Condition.class, Integer.valueOf(id));
/* 31 */     this.hibernateTemplate.delete(condition);
/*    */   }
/*    */   
/* 34 */   public int getTotalNum(int pid) {
			 Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
/* 35 */     String queryString = "select count(id) from Condition model where model.pid=?";
/* 36 */     Query query = session.createQuery(queryString);
/* 37 */     query.setParameter(0, Integer.valueOf(pid));
/* 38 */     Long count = (Long)query.uniqueResult();
/* 39 */     int num = count.intValue();
/* 40 */     session.close();
/* 41 */     return num;
/*    */   }
/*    */   
/* 44 */   public int getTotalPage(int pid) { int num = getTotalNum(pid);
/* 45 */     if (num % 40 == 0)
/* 46 */       return num / 40;
/* 47 */     return num / 40 + 1;
/*    */   }
/*    */   
/* 50 */   public List<Condition> getConditions(int page, int pid) {
			 Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
/* 51 */     String queryString = "from Condition model where model.pid=?";
/* 52 */     Query query = session.createQuery(queryString);
/* 53 */     query.setParameter(0, Integer.valueOf(pid));
/* 54 */     query.setFirstResult((page - 1) * 40);
/* 55 */     query.setMaxResults(40);
/* 56 */     session.close();
/* 57 */     return query.list();
/*    */   }
/*    */   
/* 60 */   public Condition getConditionById(int id) 
			{ 
			  return (Condition)this.hibernateTemplate.get(Condition.class, Integer.valueOf(id)); 
			}
/*    */ }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\dao\ConditionDao.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */