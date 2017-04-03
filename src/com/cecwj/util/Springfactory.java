/*    */ package com.cecwj.util;
/*    */ 
/*    */ import org.springframework.beans.factory.BeanFactory;
/*    */ import org.springframework.beans.factory.BeanFactoryAware;
/*    */ 
/*    */ @org.springframework.stereotype.Component
/*    */ public class Springfactory implements BeanFactoryAware
/*    */ {
/*    */   private static BeanFactory beanFactory;
/*    */   
/*    */   public void setBeanFactory(BeanFactory factory) throws org.springframework.beans.BeansException
/*    */   {
/* 13 */     beanFactory = factory;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public static <T> T getBean(String beanName)
/*    */   {
/* 21 */     if (beanFactory != null)
/*    */     {
/* 23 */       return (T)beanFactory.getBean(beanName);
/*    */     }
/*    */     
/*    */ 
/* 27 */     return null;
/*    */   }
/*    */ }


