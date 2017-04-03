             package com.cecwj.model.serialize;
             
             import com.cecwj.model.Bed;
import com.cecwj.model.Patient;
             
             
             
             public class PatientAndBed
             {
               private int id;
               private String name;
               private int sex;
               private String rfid;
/*  14 */      private Integer age = null;
               
               private int bedNum;
               private String statusStr;
               private String woundType;
               private String woundTime;
               private String woundAddr;
               private int docId;
               private String docName;
               
               public String getDocName()
               {
/*  26 */     return this.docName;
               }
               
               public void setDocName(String docName) {
/*  30 */     this.docName = docName;
               }
               
             
             
               public PatientAndBed(Bed bed, String docName)
               {
/*  37 */     this.bedNum = bed.getBedNum();
/*  38 */     this.statusStr = bed.getStatusStr();
/*  39 */     if (bed.getPatient() != null) {
/*  40 */       this.id = bed.getPatient().getId();
/*  41 */       this.name = bed.getPatient().getName();
/*  42 */       this.sex = bed.getPatient().getSex();
/*  43 */       this.rfid = bed.getPatient().getRfid();
/*  44 */       this.age = Integer.valueOf(bed.getPatient().getAge());
/*  45 */       this.woundType = bed.getPatient().getWoundType();
/*  46 */       this.woundTime = bed.getPatient().getWoundTime();
/*  47 */       this.woundAddr = bed.getPatient().getWound_address();
/*  48 */       this.docId = bed.getPatient().getUid();
/*  49 */       this.docName = docName;
                 }
               }
               
               public PatientAndBed(Bed bed)
               {
/*  55 */     this.bedNum = bed.getBedNum();
/*  56 */     this.statusStr = bed.getStatusStr();
/*  57 */     if (bed.getPatient() != null) {
/*  58 */       this.id = bed.getPatient().getId();
/*  59 */       this.name = bed.getPatient().getName();
/*  60 */       this.sex = bed.getPatient().getSex();
/*  61 */       this.rfid = bed.getPatient().getRfid();
/*  62 */       this.age = Integer.valueOf(bed.getPatient().getAge());
/*  63 */       this.woundType = bed.getPatient().getWoundType();
/*  64 */       this.woundTime = bed.getPatient().getWoundTime();
/*  65 */       this.woundAddr = bed.getPatient().getWound_address();
/*  66 */       this.docId = bed.getPatient().getUid();
                 }
               }
               
               public String getStatusStr() {
/*  71 */     return this.statusStr;
               }
               
               public void setStatusStr(String statusStr) {
/*  75 */     this.statusStr = statusStr;
               }
               
               public int getId() {
/*  79 */     return this.id;
               }
               
               public void setId(int id) {
/*  83 */     this.id = id;
               }
               
             
               public String getName()
               {
/*  89 */     return this.name;
               }
               
               public void setName(String name) {
/*  93 */     this.name = name;
               }
               
               public int getSex() {
/*  97 */     return this.sex;
               }
               
               public void setSex(int sex) {
/* 101 */     this.sex = sex;
               }
               
               public String getRfid() {
/* 105 */     return this.rfid;
               }
               
               public void setRfid(String rfid) {
/* 109 */     this.rfid = rfid;
               }
               
               public Integer getAge() {
/* 113 */     return this.age;
               }
               
               public void setAge(Integer age) {
/* 117 */     this.age = age;
               }
               
               public int getBedNum() {
/* 121 */     return this.bedNum;
               }
               
               public void setBedNum(int bedNum) {
/* 125 */     this.bedNum = bedNum;
               }
               
               public String getWoundType() {
/* 129 */     return this.woundType;
               }
               
               public void setWoundType(String woundType) {
/* 133 */     this.woundType = woundType;
               }
               
               public String getWoundTime() {
/* 137 */     return this.woundTime;
               }
               
               public void setWoundTime(String woundTime) {
/* 141 */     this.woundTime = woundTime;
               }
               
               public String getWoundAddr() {
/* 145 */     return this.woundAddr;
               }
               
               public void setWoundAddr(String woundAddr) {
/* 149 */     this.woundAddr = woundAddr;
               }
               
               public int getDocId() {
/* 153 */     return this.docId;
               }
               
               public void setDocId(int docId) {
/* 157 */     this.docId = docId;
               }
             }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\serialize\PatientAndBed.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */