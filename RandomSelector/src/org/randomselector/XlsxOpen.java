package org.randomselector;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class XlsxOpen {
	public void openFile(String filePath) {
		System.out.println("testing... XlsxOpenClass in openXlsxMethod.." + filePath);
		File f = new File(filePath);
		long start = System.currentTimeMillis();
		int i = 1;
		System.out.println(f.exists());

		Workbook workbook;
		try {
			workbook = WorkbookFactory.create(f);
			System.out.println((System.currentTimeMillis() - start) / 1000 + "√ ");
			Iterator<Sheet> itr = workbook.iterator();
			Sheet sheet = itr.next();
			Iterator<Row> rows = sheet.iterator();
			Row row = null;
			while ((row = rows.next()) != null) {
				Cell cell = row.getCell(0);
				System.out.println(cell.getStringCellValue());
				i++;
				if (i == 1000)
					break;
			}
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	private void readFile(final File f) {
		new Thread(new Runnable() {
			public void run() {
				try{
					
					
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}).start();
	}
}
