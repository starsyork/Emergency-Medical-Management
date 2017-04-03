package com.cecwj.dao;

import com.cecwj.model.Measure;
import javax.annotation.Resource;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component
public class MeasureDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public Measure getMeasure(int id) {
		return (Measure) this.hibernateTemplate.get(Measure.class,
				Integer.valueOf(id));
	}
}
