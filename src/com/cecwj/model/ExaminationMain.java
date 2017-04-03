             package com.cecwj.model;
             
             import com.cecwj.common.TimeFormat;
             import java.sql.Timestamp;
             import javax.persistence.Column;
             import javax.persistence.Entity;
             import javax.persistence.GeneratedValue;
             import javax.persistence.Id;
             import javax.persistence.Table;
             import javax.persistence.Transient;
             
             @Entity
             @Table(name="main")
             public class ExaminationMain
             {
               private int id;
               private String name;
               private int sex;
               private int age;
               private String kb;
               private String bed;
               private String diag;
               private String sampleType;
               private String applyDoctor;
               private String applyContent;
               private String inspectKb;
               private String reporter;
               private String collater;
               private String note;
               private String inspectNumber;
               private String date;
               private Timestamp time;
               private int pid;
               private String applyNum;
               private Timestamp rcvdate;
               private String rcvTime;
               private String mkNum;
               private String sampleNum;
               private String exec;
               private Timestamp smpDate;
               private Timestamp rptDate;
               private String smpTime;
               private String rptTime;
               
               @Id
               @GeneratedValue
               public int getId()
               {
/*  49 */     return this.id;
               }
               
/*  52 */   public void setId(int id) { this.id = id; }
               
               @Column
               public String getName() {
/*  56 */     return this.name;
               }
               
/*  59 */   public void setName(String name) { this.name = name; }
               
               @Column
               public int getSex() {
/*  63 */     return this.sex;
               }
               
/*  66 */   public void setSex(int sex) { this.sex = sex; }
               
               @Column
               public int getAge() {
/*  70 */     return this.age;
               }
               
/*  73 */   public void setAge(int age) { this.age = age; }
               
               @Column
               public String getKb() {
/*  77 */     return this.kb;
               }
               
/*  80 */   public void setKb(String kb) { this.kb = kb; }
               
               @Column
               public String getBed() {
/*  84 */     return this.bed;
               }
               
/*  87 */   public void setBed(String bed) { this.bed = bed; }
               
               @Column
               public String getDiag() {
/*  91 */     return this.diag;
               }
               
/*  94 */   public void setDiag(String diag) { this.diag = diag; }
               
               @Column
               public String getSampleType() {
/*  98 */     return this.sampleType;
               }
               
/* 101 */   public void setSampleType(String sampleType) { this.sampleType = sampleType; }
               
               @Column
               public String getApplyDoctor() {
/* 105 */     return this.applyDoctor;
               }
               
/* 108 */   public void setApplyDoctor(String applyDoctor) { this.applyDoctor = applyDoctor; }
               
               @Column
               public String getApplyContent() {
/* 112 */     return this.applyContent;
               }
               
/* 115 */   public void setApplyContent(String applyContent) { this.applyContent = applyContent; }
               
               @Column
               public String getInspectKb() {
/* 119 */     return this.inspectKb;
               }
               
/* 122 */   public void setInspectKb(String inspectKb) { this.inspectKb = inspectKb; }
               
               @Column
               public String getReporter() {
/* 126 */     return this.reporter;
               }
               
/* 129 */   public void setReporter(String reporter) { this.reporter = reporter; }
               
               @Column
               public String getCollater() {
/* 133 */     return this.collater;
               }
               
/* 136 */   public void setCollater(String collater) { this.collater = collater; }
               
               @Column
               public String getNote() {
/* 140 */     return this.note;
               }
               
/* 143 */   public void setNote(String note) { this.note = note; }
               
               @Column
               public String getInspectNumber() {
/* 147 */     return this.inspectNumber;
               }
               
/* 150 */   public void setInspectNumber(String inspectNumber) { this.inspectNumber = inspectNumber; }
               
               @Transient
               public String getDate() {
/* 154 */     if (this.date == null) {
/* 155 */       if (this.time != null)
                   {
/* 157 */         this.date = TimeFormat.timeStampToString(this.time);
                   }
                   else
                   {
/* 161 */         this.date = null;
                   }
                 }
/* 164 */     return this.date;
               }
               
/* 167 */   public void setDate(String date) { this.date = date;
/* 168 */     if (date.equals("")) {
/* 169 */       return;
                 }
/* 171 */     this.time = TimeFormat.stringToTimestamp(date);
               }
               
               @Column(name="gddate")
/* 175 */   public Timestamp getTime() { return this.time; }
               
               public void setTime(Timestamp time) {
/* 178 */     this.time = time;
               }
               
               @Column
/* 182 */   public int getPid() { return this.pid; }
               
               public void setPid(int pid) {
/* 185 */     this.pid = pid;
               }
               
               @Column
/* 189 */   public String getApplyNum() { return this.applyNum; }
               
               public void setApplyNum(String applyNum) {
/* 192 */     this.applyNum = applyNum;
               }
               
               @Column(name="rcvTime")
/* 196 */   public Timestamp getRcvdate() { return this.rcvdate; }
               
               public void setRcvdate(Timestamp rcvdate) {
/* 199 */     this.rcvdate = rcvdate;
               }
               
               @Transient
               public String getRcvTime() {
/* 204 */     if (this.rcvTime == null) {
/* 205 */       if (this.rcvdate != null)
                   {
/* 207 */         this.rcvTime = TimeFormat.timeStampToString(this.rcvdate);
                   }
                   else
                   {
/* 211 */         this.rcvTime = null;
                   }
                 }
/* 214 */     return this.rcvTime;
               }
               
/* 217 */   public void setRcvTime(String rcvTime) { this.rcvTime = rcvTime;
/* 218 */     if (rcvTime.equals(""))
/* 219 */       return;
/* 220 */     this.rcvdate = TimeFormat.stringToTimestamp(rcvTime);
               }
               
               @Column
/* 224 */   public String getMkNum() { return this.mkNum; }
               
               public void setMkNum(String mkNum) {
/* 227 */     this.mkNum = mkNum;
               }
               
               @Column
/* 231 */   public String getSampleNum() { return this.sampleNum; }
               
               public void setSampleNum(String sampleNum) {
/* 234 */     this.sampleNum = sampleNum;
               }
               
               @Column
/* 238 */   public String getExec() { return this.exec; }
               
               public void setExec(String exec) {
/* 241 */     this.exec = exec;
               }
               
               @Column(name="smpTime")
/* 245 */   public Timestamp getSmpDate() { return this.smpDate; }
               
               public void setSmpDate(Timestamp smpDate) {
/* 248 */     this.smpDate = smpDate;
               }
               
               @Column(name="rptTime")
/* 252 */   public Timestamp getRptDate() { return this.rptDate; }
               
             
/* 255 */   public void setRptDate(Timestamp rptDate) { this.rptDate = rptDate; }
               
               @Transient
               public String getSmpTime() {
/* 259 */     if (this.smpTime == null) {
/* 260 */       if (this.smpDate != null) {
/* 261 */         this.smpTime = TimeFormat.timeStampToString(this.smpDate);
                   }
                   else {
/* 264 */         this.smpTime = null;
                   }
                 }
/* 267 */     return this.smpTime;
               }
               
/* 270 */   public void setSmpTime(String smpTime) { this.smpTime = smpTime;
/* 271 */     if (smpTime.equals(""))
/* 272 */       return;
/* 273 */     this.smpDate = TimeFormat.stringToTimestamp(smpTime);
               }
               
               @Transient
/* 277 */   public String getRptTime() { if (this.rptTime == null) {
/* 278 */       if (this.rptDate != null) {
/* 279 */         this.rptTime = TimeFormat.timeStampToString(this.rptDate);
                   }
                   else {
/* 282 */         this.rptTime = null;
                   }
                 }
/* 285 */     return this.rptTime;
               }
               
/* 288 */   public void setRptTime(String rptTime) { this.rptTime = rptTime;
/* 289 */     if (rptTime.equals(""))
/* 290 */       return;
/* 291 */     this.rptDate = TimeFormat.stringToTimestamp(rptTime);
               }
             }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\ExaminationMain.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */