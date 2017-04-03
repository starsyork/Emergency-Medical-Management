             package com.cecwj.util;
             
             import java.awt.Color;
             import java.awt.Font;
             import java.awt.Graphics;
             import java.awt.image.BufferedImage;
             import java.io.PrintStream;
             import java.util.Map;
             import java.util.Random;
             import javax.imageio.ImageIO;
             import javax.servlet.ServletOutputStream;
             import org.springframework.stereotype.Component;
             
             
             @Component
             public class VcodeNew
             {
               public static final String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY";
               
               public VcodeNew()
               {
/*  22 */     System.out.print("start");
               }
               
/*  25 */   private Random random = new Random();
/*  26 */   private String randString = "123456789ABCDEFGHIJKLMNPQRSTUVWXYZ";
               
/*  28 */   private int width = 80;
/*  29 */   private int height = 26;
/*  30 */   private int lineSize = 40;
/*  31 */   private int stringNum = 4;
               
             
               private Font getFont()
               {
/*  36 */     return new Font("Fixedsys", 1, 18);
               }
               
             
               private Color getRandColor(int fc, int bc)
               {
/*  42 */     if (fc > 255)
/*  43 */       fc = 255;
/*  44 */     if (bc > 255)
/*  45 */       bc = 255;
/*  46 */     int r = fc + this.random.nextInt(bc - fc - 16);
/*  47 */     int g = fc + this.random.nextInt(bc - fc - 14);
/*  48 */     int b = fc + this.random.nextInt(bc - fc - 18);
/*  49 */     return new Color(r, g, b);
               }
               
             
             
             
               public void getRandcode(Map<String, Object> session, ServletOutputStream response)
               {
/*  57 */     BufferedImage image = new BufferedImage(this.width, this.height, 4);
/*  58 */     Graphics g = image.getGraphics();
/*  59 */     g.fillRect(0, 0, this.width, this.height);
/*  60 */     g.setFont(new Font("Times New Roman", 0, 18));
/*  61 */     g.setColor(getRandColor(110, 133));
                 
/*  63 */     for (int i = 0; i <= this.lineSize; i++) {
/*  64 */       drawLine(g);
                 }
                 
/*  67 */     String randomString = "";
/*  68 */     for (int i = 1; i <= this.stringNum; i++) {
/*  69 */       randomString = drawString(g, randomString, i);
                 }
/*  71 */     session.put("vcode", randomString.toLowerCase());
                 
/*  73 */     g.dispose();
                 try {
/*  75 */       ImageIO.write(image, "JPEG", response);
                 } catch (Exception e) {
/*  77 */       e.printStackTrace();
                 }
               }
               
             
               private String drawString(Graphics g, String randomString, int i)
               {
/*  84 */     g.setFont(getFont());
/*  85 */     g.setColor(new Color(this.random.nextInt(101), this.random.nextInt(111), this.random.nextInt(121)));
/*  86 */     String rand = String.valueOf(getRandomString(this.random.nextInt(this.randString.length())));
/*  87 */     randomString = randomString + rand;
/*  88 */     g.translate(this.random.nextInt(3), this.random.nextInt(3));
/*  89 */     g.drawString(rand, 13 * i, 16);
/*  90 */     return randomString;
               }
               
             
               private void drawLine(Graphics g)
               {
/*  96 */     int x = this.random.nextInt(this.width);
/*  97 */     int y = this.random.nextInt(this.height);
/*  98 */     int xl = this.random.nextInt(13);
/*  99 */     int yl = this.random.nextInt(15);
/* 100 */     g.drawLine(x, y, x + xl, y + yl);
               }
               
             
               public String getRandomString(int num)
               {
/* 106 */     return String.valueOf(this.randString.charAt(num));
               }
             }


