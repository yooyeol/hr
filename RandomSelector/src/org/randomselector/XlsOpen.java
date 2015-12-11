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
			sheet = workbook.getSheetAt(0); // ��Ʈ��(ù��°�� �����ϹǷ� 0�� ��) ���� �� ��Ʈ�� �б� ���ؼ� for���� �ѹ� �� ������.
			
			int rows = sheet.getPhysicalNumberOfRows(); //���� ��
			
			for(rowIndex = 1; rowIndex < rows;rowIndex++){
				row = sheet.getRow(rowIndex); // ���� ����
				if(row != null){
					int cells = row.getPhysicalNumberOfCells();//���� ��
				}
			}
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
