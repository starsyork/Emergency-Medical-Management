			package com.cecwj.model;
			
			import javax.persistence.Column;
			import javax.persistence.Entity;
			import javax.persistence.GeneratedValue;
			import javax.persistence.Id;
			import javax.persistence.Table;
			
			@Entity
			@Table(name="preparation")
			public class Preparation
			{
			  private int id;
			  private Integer applyId;
			  private String content;
			  private String drugname;
			  private Integer pid;
			  private Integer amount;
			
				  @Id
				  @GeneratedValue
				  public int getId()
				  {
				    return this.id;
				  }
				  
				  public void setId(int id) { 
					  this.id = id; 
				  }
				  @Column(name="applyId")

				public Integer getApplyId() {
					return applyId;
				}
				  @Column(name="content")
				public String getContent() {
					return content;
				}

				public void setContent(String content) {
					this.content = content;
				}

				public void setApplyId(Integer applyId) {
					this.applyId = applyId;
				}

				@Column(name="pid")

				public Integer getPid() {
					return pid;
				}

				public void setPid(Integer pid) {
					this.pid = pid;
				}
				@Column(name="amount")

				public Integer getAmount() {
					return amount;
				}

				public void setAmount(Integer amount) {
					this.amount = amount;
				}

				@Column(name="drugname")
				public String getDrugname() {
					return drugname;
				}

				public void setDrugname(String drugname) {
					this.drugname = drugname;
				}
				  

				  
				  
			}
			

/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\model\Section.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */