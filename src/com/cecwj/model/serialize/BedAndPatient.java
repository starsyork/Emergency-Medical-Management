             package com.cecwj.model.serialize;
             
             import com.cecwj.model.Bed;
             
             public class BedAndPatient
             {
               private int id;
               private int ptid;
               private String name;
               private int sex;
               private String rfid;
/*  12 */   private Integer age = null;
               private int bedNum;
               private String statusStr;
               private String woundType;
               private String woundTime;
               private String woundAddr;
               private int docId;
               private String docName;
               
               public BedAndPatient(Bed bed, com.cecwj.model.User user) {
/*  22 */     this.id = bed.getId();
/*  23 */     this.bedNum = bed.getBedNum();
/*  24 */     this.statusStr = bed.getStatusStr();
/*  25 */     if (bed.getPatient() != null) {
/*  26 */       this.ptid = bed.getPatient().getId();//获取病人ID
/*  27 */       this.name = bed.getPatient().getName();//获取病人姓名
/*  28 */       this.sex = bed.getPatient().getSex();//获取病人性别
/*  29 */       this.rfid = bed.getPatient().getRfid();//获取病人RFID号
/*  30 */       this.age = Integer.valueOf(bed.getPatient().getAge());//获取病人年龄
/*  31 */       this.woundType = bed.getPatient().getWoundType();//获取病人受伤类型
/*  32 */       this.woundTime = bed.getPatient().getWoundTime();//获取病人受伤时间
/*  33 */       this.woundAddr = bed.getPatient().getWound_address();//获取病人受伤地点
/*  34 */       if (user != null) {
/*  35 */         this.docId = user.getId();//获取医生ID
/*  36 */         this.docName = user.getUserName();//获取医生名
                   } else {
/*  38 */         this.docId = 0;
/*  39 */         this.docName = null;
                   }
                 }
               }
               
               public String getStatusStr() {
/*  45 */     return this.statusStr;
               }
               
               public void setStatusStr(String statusStr) {
/*  49 */     this.statusStr = statusStr;
               }
               
               public int getId() {
/*  53 */     return this.id;
               }
               
               public void setId(int id) {
/*  57 */     this.id = id;
               }
               
               public int getPtid() {
/*  61 */     return this.ptid;
               }
               
               public void setPtid(int ptid) {
/*  65 */     this.ptid = ptid;
               }
               
               public String getName() {
/*  69 */     return this.name;
               }
               
               public void setName(String name) {
/*  73 */     this.name = name;
               }
               
               public int getSex() {
/*  77 */     return this.sex;
               }
               
               public void setSex(int sex) {
/*  81 */     this.sex = sex;
               }
               
               public String getRfid() {
/*  85 */     return this.rfid;
               }
               
               public void setRfid(String rfid) {
/*  89 */     this.rfid = rfid;
               }
               
               public Integer getAge() {
/*  93 */     return this.age;
               }
               
               public void setAge(Integer age) {
/*  97 */     this.age = age;
               }
               
               public int getBedNum() {
/* 101 */     return this.bedNum;
               }
               
               public void setBedNum(int bedNum) {
/* 105 */     this.bedNum = bedNum;
               }
               
               public String getWoundType() {
/* 109 */     return this.woundType;
               }
               
               public void setWoundType(String woundType) {
/* 113 */     this.woundType = woundType;
               }
               
               public String getWoundTime() {
/* 117 */     return this.woundTime;
               }
               
               public void setWoundTime(String woundTime) {
/* 121 */     this.woundTime = woundTime;
               }
               
               public String getWoundAddr() {
/* 125 */     return this.woundAddr;
               }
               
               public void setWoundAddr(String woundAddr) {
/* 129 */     this.woundAddr = woundAddr;
               }
               
               public int getDocId() {
/* 133 */     return this.docId;
               }
               
               public void setDocId(int docId) {
/* 137 */     this.docId = docId;
               }
               
               public String getDocName() {
/* 141 */     return this.docName;
               }
               
               public void setDocName(String docName) {
/* 145 */     this.docName = docName;
               }
             }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\serialize\BedAndPatient.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */