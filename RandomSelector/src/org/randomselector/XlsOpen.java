package org.randomselector;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class XlsOpen {
	public void openFile(String filePath){
		System.out.println("testing... XlsOpenClass in openXlsMethod.. : " + filePath);
		
		FileInputStream fis = null;
		HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;
		HSSFRow row = null;
		HSSFCell cel = null;
		
		int rowIndex = 0;
		int columnIndex = 0;
		
		try {
			fis = new FileInputStream(filePath);
			workbook = new HSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0); // 시트수(첫번째만 존재하므로 0을 줌) 만약 각 시트를 읽기 위해서 for문을 한번 더 돌려줌.
			
			int rows = sheet.getPhysicalNumberOfRows(); //행의 수
			
			for(rowIndex = 1; rowIndex < rows;rowIndex++){
				row = sheet.getRow(rowIndex); // 행을 읽음
				if(row != null){
					int cells = row.getPhysicalNumberOfCells();//셀의 수
				}
			}
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
