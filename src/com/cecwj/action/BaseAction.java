	    package com.cecwj.action;
	    
	    import com.cecwj.model.serialize.JsonBase;
	    import com.opensymphony.xwork2.ActionSupport;
	    import java.util.Map;
	    import javax.servlet.http.HttpServletRequest;
	    import javax.servlet.http.HttpServletResponse;
	    import org.apache.log4j.Logger;
	    import org.apache.struts2.ServletActionContext;
	    import org.apache.struts2.interceptor.ServletRequestAware;
	    import org.apache.struts2.interceptor.ServletResponseAware;
	    import org.apache.struts2.interceptor.SessionAware;
	    
	    
	
		/* implements 是实现接口  
			在接口中定义了方法，这个方法要你自己去实现，
			一个标准，比如定义了一个动物的接口，它里面有吃（eat()）这个方法，
			这个方法implements，这个方法是自己写，可以是吃苹果，吃梨子，香蕉，或者其他的。
			ENTS就是具体实现这个接口。
		*/
	
	    public class BaseAction extends ActionSupport 
	    			implements SessionAware, ServletRequestAware, ServletResponseAware
	    {
	   protected JsonBase result = new JsonBase();
	      private static final String ContentType = "UTF-8";
	      
	   public JsonBase getResult() {
		return this.result; 
	}
	      
	      public void setResult(JsonBase result)
	      {
	     this.result = result;
	      }
	      
	    
	    
	    
	      private static final String ContentTypeXML = "text/xml";
	    
	   protected Logger log = Logger.getLogger(getClass());
	   protected final String OK = "OK";
	      
	    
	   private HttpServletRequest request;
	     
	      private  HttpServletResponse response;
	      
	      private Map<String, Object> session;
	    	/*
	 * 字符串非空
	 * */
	   public boolean notNull(String string) { return string != null; }
	      /*
	 * 字符串数组非空
	 * */
	      public boolean notNull(String... string) {
	     boolean r = true;
	     String[] arrayOfString; 
	  int j = (arrayOfString = string).length; 
	  for (int i = 0; i < j; i++) {
		  String s = arrayOfString[i];
	     		  if (s == null) {
	        		 r = false;
	         		 }
	        }
	     return r;
	      }
	      
	    
	      public boolean isEmpty(String string)
	      {
	     return (string == null) || (string.equals(""));
	      }
	      
	    
	      public boolean isEmpty(String... strings)
	      {
	     boolean r = false;
	     String[] arrayOfString; 
		int j = (arrayOfString = strings).length; 
		for (int i = 0; i < j; i++){ 
			String s = arrayOfString[i];
	       if ((s == null) || (s.equals(""))) {
	         r = true;
	          }
	        }
	     return r;
	      }
	      
	/*
	 * 从session中获取授权
	 * */
	
	   public boolean authorized(String key) { 
		if (getSession().get(key) == null) {
	       return false;
	        }
	     return true;
	      }
	      
	    	/*
	 * 从session中获取授权 与目标obj对比
	 * */
	      public boolean authorized(String key, String obj)
	      {
			     if (getSession().get(key) == null)
			       return false;
			     if (getSession().get(key).toString().equals(obj)) {
			       String a = getSession().get(key).toString();
			       return true;
			        }
			     return false;
	      }
	      
	    
	    
	    
	      public HttpServletRequest getRequest()
	      {
	     return this.request;
	      }
	      
	      public void setRequest(HttpServletRequest request)
	      {
	     this.request = request;
	      }
	      
	      public HttpServletResponse getResponse() {
	     return this.response;
	      }
	      
	      public void setResponse(HttpServletResponse response) {
	     this.response = response;
	      }
	      
	      public Map<String, Object> getSession() {
	     return this.session;
	      }
	      
	      public void setSession(Map<String, Object> session) {
	     this.session = session;
	      }
	      
	      public void setServletRequest(HttpServletRequest arg0) {
	     this.request = arg0;
	      }
	      
      public void setServletResponse(HttpServletResponse arg0) {
     this.response = arg0;
      }
      
    
    /*
     *在SSH各个层次获得项目根目录 
  *获取ServletAction上下文对象
  *getServletContext()获取Servlet上下文对象
  *getRealPath("/")获取upload的绝对路径
  * */
      public String getWebRootPath(){
     return ServletActionContext.getServletContext().getRealPath("/");
      }
    }


/* Location:              D:\apache-tomcat-7.0.37\webapps\WJ.war!\WEB-INF\classes\com\cecwj\action\BaseAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */