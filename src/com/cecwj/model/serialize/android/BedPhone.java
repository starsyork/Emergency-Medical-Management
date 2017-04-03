/*    */ package com.cecwj.model.serialize.android;
/*    */ 
/*    */ import com.cecwj.model.Bed;
/*    */ 
/*    */ public class BedPhone {
/*    */   private int bedNum;
/*    */   private int id;
/*    */   private String name;
/*    */   private String statusStr;
/*    */   private String woundType;
/*    */   private String rfid;
/*    */   public BedPhone(Bed bed) {
/* 13 */     this.bedNum = bed.getBedNum();
/* 14 */     this.statusStr = bed.getStatusStr();
/* 15 */     if (bed.getPatient() != null) {
/* 16 */       this.name = bed.getPatient().getName();
/* 17 */       this.woundType = bed.getPatient().getWoundType();
/* 18 */       this.id = bed.getPatient().getId();
			   this.rfid=bed.getPatient().getRfid();
/*    */     } else {
/* 20 */       this.name = null;
/* 21 */       this.woundType = null;
				this.rfid=null;
/*    */     }
/*    */   }
/*    */   
/* 25 */   public int getBedNum() { 
				return this.bedNum; 
			}
/*    */   
/*    */   public void setBedNum(int bedNum) {
/* 28 */     this.bedNum = bedNum;
/*    */   }
/*    */   
/* 31 */   public String getName() { return this.name; }
/*    */   
/*    */   public void setName(String name) {
/* 34 */     this.name = name;
/*    */   }
/*    */   
/* 37 */   public String getStatusStr() { return this.statusStr; }
/*    */   
/*    */   public void setStatusStr(String statusStr) {
/* 40 */     this.statusStr = statusStr;
/*    */   }
/*    */   
/* 43 */   public String getWoundType() { return this.woundType; }
/*    */   
/*    */   public void setWoundType(String woundType) {
/* 46 */     this.woundType = woundType;
/*    */   }
/*    */   
/* 49 */   public int getId() { return this.id; }
/*    */   
/*    */   public void setId(int id) {
/* 52 */     this.id = id;
/*    */   }
/*    */
			public String getRfid() {
				return rfid;
			}
			public void setRfid(String rfid) {
				this.rfid = rfid;
			} 
}


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\serialize\android\BedPhone.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */