package com.cecwj.service;

import java.util.List;

import com.cecwj.dao.PreparationDao;
import com.cecwj.model.Apply;
import com.cecwj.model.Preparation;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

@Component("preparationManager")
public class PreparationManager {
	private PreparationDao preparationDao;

	public PreparationDao getPreparationDao() {
		return this.preparationDao;
	}

	@Resource
	public void setPreparationDao(PreparationDao preparationDao) {
		this.preparationDao = preparationDao;
	}

	public void addPreparation(Preparation preparation) {
		this.preparationDao.add(preparation);
	}

	public void update(Preparation preparation) {
		this.preparationDao.update(preparation);
	}

	public void add(Preparation preparation) {
		this.preparationDao.add(preparation);
	}

	public void deletePreparation(int id) {
		this.preparationDao.delete(id);

	}

	public List<Preparation> getPreparation(Integer applyId) {
		List<Preparation> preparation = this.preparationDao.getPreparation(applyId);
		return preparation;
	}
}
