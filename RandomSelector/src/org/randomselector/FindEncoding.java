package org.randomselector;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FindEncoding {
	public String confirmEncoding(String openFilePath){
		String encoding = "";
		
		try {			
			FileInputStream fis = new FileInputStream(openFilePath);
			
			byte[] BOM = new byte[4];
			fis.read(BOM,0,4);
			
			if( (BOM[0] & 0xFF) == 0xEF && (BOM[1] & 0xFF) == 0xBB && (BOM[2] & 0xFF) == 0xBF ){
				encoding = "UTF-8";
				System.out.println("UTF-8");
			}else if( (BOM[0] & 0xFF) == 0xFE && (BOM[1] & 0xFF) == 0xFF ){
			    encoding = "UTF-16BE";
				System.out.println("UTF-16BE");
			}else if( (BOM[0] & 0xFF) == 0xFF && (BOM[1] & 0xFF) == 0xFE ){
				encoding = "UTF-16LE";
			    System.out.println("UTF-16LE");
			}else if( (BOM[0] & 0xFF) == 0x00 && (BOM[1] & 0xFF) == 0x00 && 
			         (BOM[0] & 0xFF) == 0xFE && (BOM[1] & 0xFF) == 0xFF ){
				encoding = "UTF-32BE";
			    System.out.println("UTF-32BE");
			}else if( (BOM[0] & 0xFF) == 0xFF && (BOM[1] & 0xFF) == 0xFE && 
			         (BOM[0] & 0xFF) == 0x00 && (BOM[1] & 0xFF) == 0x00 ){
				encoding = "UTF-32LE";
			    System.out.println("UTF-32LE");
			}else{
				encoding = "EUC-KR";
			    System.out.println("EUC-KR");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return encoding;
	}
}
