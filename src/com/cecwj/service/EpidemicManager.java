		package com.cecwj.service;
		
		import com.cecwj.dao.EpidemicDao;
		import com.cecwj.model.Epidemic;
import com.cecwj.model.TermAll;
import com.cecwj.model.Volunteer;

		import java.util.List;
		import javax.annotation.Resource;
import org.springframework.stereotype.Component;
		
		
		
		
		@Component("epidemicManager")
		public class EpidemicManager
		{
		  private EpidemicDao epidemicDao;
		  
		  public EpidemicDao getEpidemicDao()
		  {
		    return this.epidemicDao;
		  }
		  
		  @Resource
		  public void setEpidemicDao(EpidemicDao epidemicDao) { 
			  this.epidemicDao = epidemicDao; 
		  }
		  
		  public void addEpidemic(Epidemic epidemic)
		  {
		    this.epidemicDao.add(epidemic);
		  }		  		 
		  
		  public List<Epidemic> getAllEpidemic() {
		    List<Epidemic> epidemics = this.epidemicDao.getAllEpidemic();
		    return epidemics;
		  }
		  
		  public void update(Epidemic epidemic) {
			  this.epidemicDao.update(epidemic);
		  	}

			public Epidemic getEpidemic(int id) {
				
				return this.epidemicDao.getepidemic(id);
				 			
			}
			public int getEpidemicById(int id) {
				
				return this.epidemicDao.getepidemicById(id);
				 			
			}
			public void add(Epidemic epidemic) {				
				this.epidemicDao.add(epidemic);
			}

			public void deleteEpidemic(int id) {
				this.epidemicDao.delete(id);
				
			}
}


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\service\SectionManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */