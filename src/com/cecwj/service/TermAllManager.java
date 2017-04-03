			package com.cecwj.service;
			
			import com.cecwj.dao.TermAllDao;
			import com.cecwj.model.TermAll;
			import com.cecwj.model.serialize.DrugSta;
			import com.cecwj.model.serialize.JsonBase;
			import java.util.ArrayList;
			import java.util.List;
			import javax.annotation.Resource;
			import org.springframework.stereotype.Component;
			
			
			@Component("termAllManager")
			
			public class TermAllManager
			{
			  private TermAllDao termAllDao;
			  
			  public TermAllDao getTermAllDao()
			  {
			    return this.termAllDao;
			  }
			  
			  @Resource
			  public void setTermAllDao(TermAllDao termAllDao) { 
				  this.termAllDao = termAllDao; 
			  }
			  
			  public List<TermAll> getTerms(String term)
			  {
			    return this.termAllDao.getTerms(term);
			  }

			  public TermAll getTermsByContent(String content)
			  {
			    return this.termAllDao.getTermsByContent(content);
			  }

				public List<TermAll> getAllDrugA( ) {
					 List<TermAll> drug = this.termAllDao.getAlldrug();
					return drug;
				}
				public void getAllDrug(JsonBase result) {	
				   List<TermAll> drugs = this.termAllDao.getAlldrug();
				 //  List<Volunteer> volunteer = this.volunteerDao.getId();
			       List<DrugSta> us = new ArrayList();
				      for (TermAll drug : drugs) {
				    	  if(drug.getAmount()<(drug.getNeed()/10))
				    	  {
					    	  DrugSta u = new DrugSta(drug);
					    	  us.add(u);
				    	  }
				       }
				       int total = us.size();
				       result.setResults(us);
				       result.setTotal(total);
				       result.setSuccess(true);					
					}

				public TermAll getDrugByIdA(String id) {
					return this.termAllDao.getDrugByIdA(id);
				}
				public TermAll getDrugById(int id) {
					return this.termAllDao.getDrugById(id);
				}
				public void update(TermAll termall) {
					this.termAllDao.update(termall);					
				}
				public void delete(TermAll termall) {
					this.termAllDao.delete(termall);					
				}
				public void add(TermAll termall) {
					this.termAllDao.add(termall);					
				}
			
			}





