             package com.cecwj.model.serialize.android;
             
             import com.cecwj.model.ExaminationMain;
             
             
             public class EmainPhone
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
               private int pid;
               private int num;
               
               public EmainPhone(int num, ExaminationMain emain)
               {
/*  29 */     this.id = emain.getId();
/*  30 */     this.age = emain.getAge();
/*  31 */     this.applyContent = emain.getApplyContent();
/*  32 */     this.applyDoctor = emain.getApplyDoctor();
/*  33 */     this.name = emain.getName();
/*  34 */     this.sex = emain.getSex();
/*  35 */     this.kb = emain.getKb();
/*  36 */     this.bed = emain.getBed();
/*  37 */     this.diag = emain.getDiag();
/*  38 */     this.sampleType = emain.getSampleType();
/*  39 */     this.inspectKb = emain.getInspectKb();
/*  40 */     this.reporter = emain.getReporter();
/*  41 */     this.collater = emain.getCollater();
/*  42 */     this.note = emain.getNote();
/*  43 */     this.date = emain.getDate();
/*  44 */     this.inspectNumber = emain.getInspectNumber();
/*  45 */     this.pid = emain.getPid();
/*  46 */     this.num = num;
               }
               
             
               public int getId()
               {
/*  52 */     return this.id;
               }
               
/*  55 */   public void setId(int id) { this.id = id; }
               
               public String getName() {
/*  58 */     return this.name;
               }
               
/*  61 */   public void setName(String name) { this.name = name; }
               
               public int getSex() {
/*  64 */     return this.sex;
               }
               
/*  67 */   public void setSex(int sex) { this.sex = sex; }
               
               public int getAge() {
/*  70 */     return this.age;
               }
               
/*  73 */   public void setAge(int age) { this.age = age; }
               
               public String getKb() {
/*  76 */     return this.kb;
               }
               
/*  79 */   public void setKb(String kb) { this.kb = kb; }
               
               public String getBed() {
/*  82 */     return this.bed;
               }
               
/*  85 */   public void setBed(String bed) { this.bed = bed; }
               
               public String getDiag() {
/*  88 */     return this.diag;
               }
               
/*  91 */   public void setDiag(String diag) { this.diag = diag; }
               
               public String getSampleType() {
/*  94 */     return this.sampleType;
               }
               
/*  97 */   public void setSampleType(String sampleType) { this.sampleType = sampleType; }
               
               public String getApplyDoctor() {
/* 100 */     return this.applyDoctor;
               }
               
/* 103 */   public void setApplyDoctor(String applyDoctor) { this.applyDoctor = applyDoctor; }
               
               public String getApplyContent() {
/* 106 */     return this.applyContent;
               }
               
/* 109 */   public void setApplyContent(String applyContent) { this.applyContent = applyContent; }
               
               public String getInspectKb() {
/* 112 */     return this.inspectKb;
               }
               
/* 115 */   public void setInspectKb(String inspectKb) { this.inspectKb = inspectKb; }
               
               public String getReporter() {
/* 118 */     return this.reporter;
               }
               
/* 121 */   public void setReporter(String reporter) { this.reporter = reporter; }
               
               public String getCollater() {
/* 124 */     return this.collater;
               }
               
/* 127 */   public void setCollater(String collater) { this.collater = collater; }
               
               public String getNote() {
/* 130 */     return this.note;
               }
               
/* 133 */   public void setNote(String note) { this.note = note; }
               
               public String getInspectNumber() {
/* 136 */     return this.inspectNumber;
               }
               
/* 139 */   public void setInspectNumber(String inspectNumber) { this.inspectNumber = inspectNumber; }
               
               public String getDate() {
/* 142 */     return this.date;
               }
               
/* 145 */   public void setDate(String date) { this.date = date; }
               
               public int getPid() {
/* 148 */     return this.pid;
               }
               
/* 151 */   public void setPid(int pid) { this.pid = pid; }
               
               public int getNum() {
/* 154 */     return this.num;
               }
               
/* 157 */   public void setNum(int num) { this.num = num; }
             }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\serialize\android\EmainPhone.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */