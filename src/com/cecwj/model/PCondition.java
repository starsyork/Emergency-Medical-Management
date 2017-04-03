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
 @Table(name="pcondition")
 public class PCondition
 {
   private int id;
   private int pid;
   private int uid;
   private float pulse;
   private float breath;
   private float diastolic;
   private float systolic;
   private float temperature;
   private String comment;
   private Timestamp condition_time;
   private String time;
   
   @Id
   @GeneratedValue
   public int getId()
   {
  return this.id;
   }
   
public void setId(int id) { this.id = id; }
   
   @Column
   public int getPid() {
  return this.pid;
   }
   
public void setPid(int pid) { this.pid = pid; }
   
   @Column
   public int getUid() {
  return this.uid;
   }
   
public void setUid(int uid) { this.uid = uid; }
   
   @Column
   public float getPulse() {
  return this.pulse;
   }
   
public void setPulse(float pulse) { this.pulse = pulse; }
   
   @Column
   public float getBreath() {
  return this.breath;
   }
   
public void setBreath(float breath) { this.breath = breath; }
   
   @Column(name="diastolic_blood_pressure")
   public float getDiastolic() {
  return this.diastolic;
   }
   
public void setDiastolic(float diastolic_blood_pressure) { this.diastolic = diastolic_blood_pressure; }
   
   @Column(name="systolic_blood_pressure")
   public float getSystolic() {
  return this.systolic;
   }
   
public void setSystolic(float systolic_blood_pressure) { this.systolic = systolic_blood_pressure; }
   
   @Column
   public float getTemperature() {
  return this.temperature;
   }
   
public void setTemperature(float temperature) { this.temperature = temperature; }
   
   @Column
   public String getComment() {
  return this.comment;
   }
   
public void setComment(String comment) { this.comment = comment; }
   
 
   @Column
public Timestamp getCondition_time() { return this.condition_time; }
   
   public void setCondition_time(Timestamp condition_time) {
  this.condition_time = condition_time;
  if (condition_time != null)
    this.time = TimeFormat.timeStampToString(condition_time);
   }
   
   @Transient
public String getTime() { if ((this.time == null) && (this.condition_time != null))
    this.time = TimeFormat.timeStampToString(this.condition_time);
  return this.time;
   }
   
public void setTime(String time) { this.time = time;
  if ((time != null) && (!time.equals(""))) {
    this.condition_time = TimeFormat.stringToTimestamp(time);
     }
   }
 }





