package org.randomselector;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ProgressMonitorInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test {
	public static void main(String[] args) {
		// create a test frame with a "press me" button
		final JFrame f = new JFrame("Sample");
		f.getContentPane().setLayout(new FlowLayout());
		JButton b = new JButton("Press me");
		f.getContentPane().add(b);
		f.pack();

		// set up the file read action
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// when button is pressed, start a new thread
				// to read the file. A new thread is needed because we
				// need to free the GUI update thread to paint the
				// progress monitor
				new Thread() {
					public void run() {
						try {
							// open the file, wrapping it in a
							// ProgressMonitorInputStream
							File f = new File("C:\\Users\\kyy\\Documents\\테스트파일(100000).xlsx");
							System.out.println(f.exists());
							InputStream in = new FileInputStream(f);
							ProgressMonitorInputStream pm = new ProgressMonitorInputStream(new JFrame(),
									"Reading the big file", in);
							XSSFWorkbook wb = null;
							
							int rowindex = 0;
							int colindex = 0;
							try{
								wb = new XSSFWorkbook(pm);

								XSSFSheet sheet = wb.getSheetAt(0);

								int rows = sheet.getPhysicalNumberOfRows();
								for(rowindex=0;rowindex<rows;rowindex++){
									XSSFRow row = sheet.getRow(rowindex);
									if(row != null){
										int cells = row.getPhysicalNumberOfCells();
										for(colindex=0;colindex<cells;colindex++){
											XSSFCell cell = row.getCell(colindex);
											String value="";
											if(cell == null){
												continue;
											}else{
												switch (cell.getCellType()){
								                case XSSFCell.CELL_TYPE_FORMULA:
								                    value=cell.getCellFormula();
								                    break;
								                case XSSFCell.CELL_TYPE_NUMERIC:
								                    value=cell.getNumericCellValue()+"";
								                    break;
								                case XSSFCell.CELL_TYPE_STRING:
								                    value=cell.getStringCellValue()+"";
								                    break;
								                case XSSFCell.CELL_TYPE_BLANK:
								                    value=cell.getBooleanCellValue()+"";
								                    break;
								                case XSSFCell.CELL_TYPE_ERROR:
								                    value=cell.getErrorCellValue()+"";
								                    break;
								                }
											}
											System.out.println("각 셀 내용 :"+value);
										}
									}
								}
								
								
							}catch(FileNotFoundException e){
								e.printStackTrace();
							}catch(IOException e){
								e.printStackTrace();
							}
							// read the file. If it's taking too long, the
							// progress
							// monitor will appear. The amount of time is
							// roughly
							// 1/100th of the estimated read time (based on how
							// long
							// it took to read the first 1/100th of the file.)
							// Note that by default, the dialog won't appear
							// unless
							// the overall estimate is over 2 seconds.
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}.start();
			}
		});

		// display the frame
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);
	}
}