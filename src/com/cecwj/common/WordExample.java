package com.cecwj.common;

import java.io.File;
import java.io.IOException;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

public class WordExample {
    private static ActiveXComponent MsWordApp = null;  
	public static void main(String[] args) throws IOException{
		
		Word msWordManager;					
		try {	msWordManager = new Word(true);
			msWordManager.createNewDocument();
			msWordManager.insertText("Hello word.");
			for(int i=0;i<2;i++){
				System.out.println("D:" + File.separator +"pic"+ File.separator +i+".jpg");
				msWordManager.insertImage("D:" + File.separator +"pic"+ File.separator +i+".jpg");			
			}
			msWordManager.createTable("table1",5,5);
			msWordManager.putTxtToCell(1,2,3,"dfg");
			msWordManager.moveDown(7);
			msWordManager.insertText("Hello word.");
			msWordManager.createTable("table2",5,5);
			msWordManager.putTxtToCell(2,2,2,"dfg");
			msWordManager.moveDown(7);
			msWordManager.insertText("Hello word.");
			msWordManager.save("D:/xiantong.doc");

			System.out.println("success");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//msWordManager.closeDocument();
		}	
			
		   	
		}
}
