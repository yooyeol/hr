package org.randomselector;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class TestPOI {

	/*public static void main(String[] args) throws IOException, InvalidFormatException {
		File file = new File("‪C:\\Users\\kyy\\Documents\\통합문서1.xlsx");
		long start = System.currentTimeMillis();
		System.out.println(file.exists());
		OPCPackage opcPackage = OPCPackage.open(file);
		XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
		opcPackage.close();
		System.out.println((System.currentTimeMillis() - start) / 1000 + "초");
		XSSFSheet sheet = workbook.getSheetAt(0);
		for (Row row : sheet) {
			for (Cell cell : row) {
				System.out.println(cell);
			}
		}
	}*/

	public static void main(String[] args)
			throws FileNotFoundException, IOException, EncryptedDocumentException, InvalidFormatException {
		File file = new File("src/main/resources/통합문서1.xlsx");
		long start = System.currentTimeMillis();
		Workbook workbook = WorkbookFactory.create(file);
		System.out.println((System.currentTimeMillis() - start) / 1000 + "초");
		Iterator<Sheet> itr = workbook.iterator();
		Sheet sheet = itr.next();
		Iterator<Row> rows = sheet.iterator();
		Row row = null;
		int i = 1;
		while ((row = rows.next()) != null) {
			Cell cell = row.getCell(0);
			System.out.println(cell.getStringCellValue());
			i++;
			if (i == 1000)
				break;
		}
	}

}