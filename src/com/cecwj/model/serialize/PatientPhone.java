/*    */ package com.cecwj.model.serialize;
/*    */ 
/*    */ import com.cecwj.model.Bed;
/*    */ 
/*    */ public class PatientPhone
/*    */ {
/*    */   private int id;
/*    */   private int bedNum;
/*    */   private String woundType;
/*    */   private String woundTime;
/*    */   private String name;
/*    */   private String docName;
/*    */   private String rfid;
/*    */   public PatientPhone(Bed bed, String docName) {
/* 15 */     this.docName = docName;
/* 16 */     this.bedNum = bed.getBedNum();
/* 17 */     if (bed.getPatient() != null) {
/* 18 */       this.id = bed.getPatient().getId();
/* 19 */       this.woundType = bed.getPatient().getWoundType();
/* 20 */       this.woundTime = bed.getPatient().getWoundTime();
/* 21 */       this.name = bed.getPatient().getName();
/*    */     }
/*    */   }
/*    */   
/* 25 */   public PatientPhone(com.cecwj.model.Patient patient) { this.id = patient.getId();
/* 26 */     this.woundTime = patient.getWoundTime();
/* 27 */     this.woundType = patient.getWoundType();
/* 28 */     this.name = patient.getName();
			this.rfid= patient.getRfid();
/*    */   }
/*    */   
/*    */   public PatientPhone(Bed bed)
/*    */   {
/* 33 */     this.bedNum = bed.getBedNum();
/* 34 */     if (bed.getPatient() != null) {
/* 35 */       this.id = bed.getPatient().getId();
/* 36 */       this.woundType = bed.getPatient().getWoundType();
/* 37 */       this.woundTime = bed.getPatient().getWoundTime();
/* 38 */       this.name = bed.getPatient().getName();
			   this.rfid= bed.getPatient().getRfid();
/*    */     }
/*    */   }
/*    */   
/*    */   public int getId() {
/* 43 */     return this.id;
/*    */   }
/*    */   
/* 46 */   public void setId(int id) { this.id = id; }
/*    */   
/*    */   public int getBedNum() {
/* 49 */     return this.bedNum;
/*    */   }
/*    */   
/* 52 */   public void setBedNum(int bedNum) { this.bedNum = bedNum; }
/*    */   
/*    */   public String getWoundType() {
/* 55 */     return this.woundType;
/*    */   }
/*    */   
/* 58 */   public void setWoundType(String woundType) { this.woundType = woundType; }
/*    */   
/*    */   public String getWoundTime() {
/* 61 */     return this.woundTime;
/*    */   }
/*    */   
/* 64 */   public void setWoundTime(String woundTime) { this.woundTime = woundTime; }
/*    */   
/*    */   public String getName()
/*    */   {
/* 68 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 72 */     this.name = name;
/*    */   }
/*    */   
/* 75 */   public String getDocName() { return this.docName; }
/*    */   
/*    */   public void setDocName(String docName) {
/* 78 */     this.docName = docName;
/*    */   }
/*    */
			public String getRfid() {
				return rfid;
			}
			public void setRfid(String rfid) {
				this.rfid = rfid;
			} 
						

			}


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\serialize\PatientPhone.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */