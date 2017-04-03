             package com.cecwj.util;
             
             import java.io.BufferedReader;
             import java.io.BufferedWriter;
             import java.io.FileInputStream;
             import java.io.FileNotFoundException;
             import java.io.FileOutputStream;
             import java.io.IOException;
             import java.io.InputStream;
             import java.io.InputStreamReader;
             import java.io.OutputStream;
             import java.io.OutputStreamWriter;
             import java.io.PrintStream;
             import java.util.Date;
             import java.util.Properties;
             import org.springframework.stereotype.Component;
             
             
             @Component
             public class DBUtil
             {
               private String dbName;
               private String username;
               private String password;
               private String backup;
               
               public String getDbName()
               {
/*  29 */     return this.dbName;
               }
               
               public void setDbName(String dbName) {
/*  33 */     this.dbName = dbName;
               }
               
               public String getUsername() {
/*  37 */     return this.username;
               }
               
               public void setUsername(String username) {
/*  41 */     this.username = username;
               }
               
               public String getPassword() {
/*  45 */     return this.password;
               }
               
               public void setPassword(String password) {
/*  49 */     this.password = password;
               }
               
               public String getBackup() {
/*  53 */     return this.backup;
               }
               
               public void setBackup(String backup) {
/*  57 */     this.backup = backup;
               }
               
               public DBUtil() {
/*  61 */     System.out.print("/" + new StringBuilder().append(getClass().getResource("/")).append("jdbc.properties").toString().replace("file:/", ""));
/*  62 */     InputStream inputStream = null;
                 try {
/*  64 */       inputStream = new FileInputStream("/" + new StringBuilder().append(getClass().getResource("/")).append("jdbc.properties").toString().replace("file:/", "").replace("%20", " "));
                 }
                 catch (FileNotFoundException e) {
/*  67 */       e.printStackTrace();
                 }
/*  69 */     Properties properties = new Properties();
                 try {
/*  71 */       properties.load(inputStream);
                 } catch (IOException e1) {
/*  73 */       e1.printStackTrace();
                 }
/*  75 */     this.dbName = properties.getProperty("dbname");
/*  76 */     this.username = properties.getProperty("jdbc.username");
/*  77 */     this.password = properties.getProperty("jdbc.password");
/*  78 */     this.backup = properties.getProperty("backup");
               }
               
               public boolean sqlDump() {
/*  82 */     boolean flag = false;
                 try {
/*  84 */       Runtime run = Runtime.getRuntime();
                   
             
/*  87 */       Process p = run.exec(this.dbName + this.username + 
/*  88 */         " -p" + this.password + " gxg_new");
/*  89 */       InputStream is = p.getInputStream();
/*  90 */       InputStreamReader isr = new InputStreamReader(is, "UTF-8");
/*  91 */       BufferedReader br = new BufferedReader(isr);
                   
/*  93 */       FileOutputStream fos = new FileOutputStream(this.backup + "/gxg_new" + new Date().getTime() + ".sql");
/*  94 */       BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos, 
/*  95 */         "UTF-8"));
/*  96 */       String temp = null;
/*  97 */       while ((temp = br.readLine()) != null) {
/*  98 */         bw.write(temp);
/*  99 */         bw.newLine();
                   }
/* 101 */       bw.flush();
/* 102 */       bw.close();
/* 103 */       br.close();
/* 104 */       flag = true;
/* 105 */       System.out.println("/* Dump  SQL File " + this.backup + "/gxg_new.sql" + " OK! */");
                 }
                 catch (IOException e) {
/* 108 */       e.printStackTrace();
                 }
/* 110 */     return flag;
               }
               
             
             
             
               public void sqlLoad()
               {
                 try
                 {
/* 120 */       Runtime rt = Runtime.getRuntime();
                   
             
/* 123 */       Process child = rt.exec(this.dbName + "/bin/mysql.exe -u" + this.username + 
/* 124 */         " -p" + this.password + " gxg_new");
/* 125 */       OutputStream out = child.getOutputStream();
                   
/* 127 */       BufferedReader br = new BufferedReader(new InputStreamReader(
/* 128 */         new FileInputStream(this.backup + "/gxg_new.sql"), "utf8"));
                   
/* 130 */       OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
                   String inStr;
/* 132 */       while ((inStr = br.readLine()) != null) {
/* 133 */         writer.write(inStr);
/* 134 */         writer.write("\r\n");
                   }
/* 136 */       writer.flush();
                   
/* 138 */       out.close();
/* 139 */       br.close();
/* 140 */       writer.close();
/* 141 */       System.out.println("/* Load  SQL File " + this.backup + "/gxg_new.sql" + " OK! */");
                 } catch (Exception e) {
/* 143 */       e.printStackTrace();
                 }
               }
             }


