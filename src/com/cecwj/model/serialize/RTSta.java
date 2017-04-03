	      package com.cecwj.model.serialize;

		  import java.sql.Timestamp;
import java.util.Date;

		 
		  public class RTSta {
			private long id;  

		private int LInjNum;
		  private int MInjNum;
		  private int SInjNum;		  
		  private String Date;
		  private int TotalDeIn;				
		   

		  public long getId() {
				return id;
			}

			public void setId(long i) {
				this.id = i;
			
		   }
		   public int getLInjNum() {
			return LInjNum;
		}

		public void setLInjNum(int lInjNum) {
			LInjNum = lInjNum;
		}

		public int getMInjNum() {
			return MInjNum;
		}

		public void setMInjNum(int mInjNum) {
			MInjNum = mInjNum;
		}

		public int getSInjNum() {
			return SInjNum;
		}

		public void setSInjNum(int sInjNum) {
			SInjNum = sInjNum;
		}

		public int getTotalDeIn() {
			return TotalDeIn;
		}

		public void setTotalDeIn(int totalDeIn) {
			TotalDeIn = totalDeIn;
		}

		public String getDate() {
			     return this.Date;
			}
			                  
		public void setDate(String date) { 
			this.Date = date; 
			}

	




		 }
