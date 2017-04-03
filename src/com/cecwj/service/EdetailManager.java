 package com.cecwj.service;
 
import com.cecwj.dao.EdetailDao;
 import com.cecwj.model.Edetail;
 import java.util.List;
 import javax.annotation.Resource;
import org.springframework.stereotype.Component;
 
 
 
@Component("edetailManager")
 public class EdetailManager
 {
   private EdetailDao edetailDao;
   
   public EdetailDao getEdetailDao()
   {
     return this.edetailDao;
   }
   
   @Resource
   public void setEdetailDao(EdetailDao edetailDao) { this.edetailDao = edetailDao; }
   
   public List<Edetail> getEdetail(int page, int mid)
   {
     return this.edetailDao.getEdetail(page, mid);
   }
   public List<Edetail> getEdetail( int mid)
   {
     return this.edetailDao.getEdetail( mid);
   }
   public int getEdetailTotal(int mid) {
     return this.edetailDao.getEdetailTotal(mid);
   }

			public void add(Edetail edetail) {
				
				this.edetailDao.add(edetail);
				// TODO Auto-generated method stub
				
			}
			public List<Edetail> getEdetailBypid(int pid, int id) {
				
				 return this.edetailDao.getEdetailBypid(pid,id );
			}
			public List<Edetail> getAllEdetailBypid(int pid, int id) {
				
				 return this.edetailDao.getAllEdetailBypid(id, pid);
			}
			public Edetail getEdetailById(int id) {
				Edetail edetail = this.edetailDao.get(id);
				 return edetail;
			}

			public void update(Edetail edetail) {
				this.edetailDao.update(edetail);				
			}


}





