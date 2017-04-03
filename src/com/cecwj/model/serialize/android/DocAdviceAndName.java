             package com.cecwj.model.serialize.android;
             
             import com.cecwj.model.DocAdvice;
             
             public class DocAdviceAndName
             {
               private int id;
               private String content;
               private String spec;
               private String startTime;
               private String endTime;
               private String state;
               private String type;
               private String dose;
               private String usage;
               private String frequency;
               private String type2;
               private String docName;
               
               public DocAdviceAndName(String docName, DocAdvice docAdvice)
               {
/*  22 */     this.docName = docName;
/*  23 */     this.id = docAdvice.getId();
/*  24 */     this.content = docAdvice.getContent();
/*  25 */     this.spec = docAdvice.getSpec();
/*  26 */     this.startTime = docAdvice.getStartTime();
/*  27 */     this.endTime = docAdvice.getEndTime();
/*  28 */     this.state = docAdvice.getState();
/*  29 */     this.type = docAdvice.getType();
/*  30 */     this.dose = docAdvice.getDose();
/*  31 */     this.usage = docAdvice.getUsage();
/*  32 */     this.frequency = docAdvice.getFrequency();
/*  33 */     this.type2 = docAdvice.getType2();
               }
               
             
             
             
             
               public int getId()
               {
/*  42 */     return this.id;
               }
               
/*  45 */   public void setId(int id) { this.id = id; }
               
               public String getContent() {
/*  48 */     return this.content;
               }
               
/*  51 */   public void setContent(String content) { this.content = content; }
               
               public String getSpec() {
/*  54 */     return this.spec;
               }
               
/*  57 */   public void setSpec(String spec) { this.spec = spec; }
               
               public String getStartTime() {
/*  60 */     return this.startTime;
               }
               
/*  63 */   public void setStartTime(String startTime) { this.startTime = startTime; }
               
               public String getEndTime() {
/*  66 */     return this.endTime;
               }
               
/*  69 */   public void setEndTime(String endTime) { this.endTime = endTime; }
               
               public String getState() {
/*  72 */     return this.state;
               }
               
/*  75 */   public void setState(String state) { this.state = state; }
               
               public String getType() {
/*  78 */     return this.type;
               }
               
/*  81 */   public void setType(String type) { this.type = type; }
               
               public String getDose() {
/*  84 */     return this.dose;
               }
               
/*  87 */   public void setDose(String dose) { this.dose = dose; }
               
               public String getUsage() {
/*  90 */     return this.usage;
               }
               
/*  93 */   public void setUsage(String usage) { this.usage = usage; }
               
               public String getFrequency() {
/*  96 */     return this.frequency;
               }
               
/*  99 */   public void setFrequency(String frequency) { this.frequency = frequency; }
               
               public String getType2() {
/* 102 */     return this.type2;
               }
               
/* 105 */   public void setType2(String type2) { this.type2 = type2; }
               
               public String getDocName() {
/* 108 */     return this.docName;
               }
               
/* 111 */   public void setDocName(String docName) { this.docName = docName; }
             }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\serialize\android\DocAdviceAndName.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */