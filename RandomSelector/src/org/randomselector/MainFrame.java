package org.randomselector;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MainFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		MainFrame m = new MainFrame();
	}
	
	private JFileChooser fileChooser = null; // ���� ���� â
	private JButton openButton = null; // ó�� ���� ���� ��ư
	private JButton saveButton = null; // ���� ��ư
	private JButton overlapOpenButton = null; // �ߺ� ���� ���� ��ư
	private JButton startButton = null; // ���� �̱� ���� ��ư
	
	private JPanel northPanel = null;
	private JPanel westPanel = null;
	private JPanel eastPanel = null;
	private JPanel firEastPanel = null;
	private JPanel firEastPanel1 = null;
	private JPanel firEastPanel2 = null;
	private JPanel secEastPanel = null;
	private JPanel secEastPanel1 = null;
	private JPanel secEastPanel2 = null;
	private JPanel tirEastPanel = null;
	private JPanel tirEastPanel1 = null;
	private JPanel tirEastPanel2 = null;
	private JPanel tirEastPanel3= null;
	private JPanel southPanel = null;
	private JPanel centerPanel = null;
	
	private JScrollPane consoleScrollPane = null; // �ܼ�â ��ũ��
	private JTextArea console = null; // �ܼ�
	private JTextField insertNum = null;// ���� �ο��� üũ
	//���� ���� ���� ����
	private JLabel fileName = null; // ���� �̸�
	private JLabel fileSize = null; // ���� ������
	private JLabel totalCount = null; // �� �ο� ��
	private JLabel sheetName = null; // sheet�̸�
	private JLabel subCount = null; // ���ŵ� �ο� ��
	private JLabel title1 = null; // Ÿ��Ʋ1
	private JLabel title2 = null; // Ÿ��Ʋ2
	private JLabel title3 = null; // Ÿ��Ʋ3
	private JLabel information = null;
	
	@SuppressWarnings("static-access")
	public MainFrame() {
		super("�K�K�K");
		this.init();
		this.start();
		this.setSize(600, 550);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}

	public void init() {
		fileChooser = new JFileChooser();
		overlapOpenButton = new JButton("�̹� ��÷�� ���");
		startButton = new JButton("�����̱� ����");
		
		console = new JTextArea();
		console.setLineWrap(true); //������ �ʹ� ��� �ڵ����� �������� ����
		console.setColumns(50); //���� ũ��(����ũ��)
		console.setRows(10); //���� ũ��(����ũ��)
		console.setEditable(false);
		
		consoleScrollPane = new JScrollPane(console);
		getContentPane().setLayout(new BorderLayout());
		
		//����
		northPanel = new JPanel(new FlowLayout());
		add(northPanel, BorderLayout.NORTH);
		
		//����
		westPanel = new JPanel(new FlowLayout());
		add(westPanel, BorderLayout.WEST);
		
		//������
		eastPanel = new JPanel(new GridLayout(3,1));
		firEastPanel = new JPanel(new GridLayout(2,1));
		firEastPanel1 = new JPanel(new FlowLayout());
		firEastPanel2 = new JPanel(new FlowLayout());
		openButton = new JButton("����");
		saveButton = new JButton("����");
		title1 = new JLabel("1. ���� �ҷ����� & ����");
		firEastPanel1.add(title1);
		firEastPanel2.add(openButton);
		firEastPanel2.add(saveButton);
		firEastPanel.add(firEastPanel1);
		firEastPanel.add(firEastPanel2);
		eastPanel.add(firEastPanel);

		secEastPanel = new JPanel(new GridLayout(2,1));
		secEastPanel1 = new JPanel(new FlowLayout());
		secEastPanel2 = new JPanel(new FlowLayout());
		title2 = new JLabel("2. ���� ��÷�� �ҷ�����");
		overlapOpenButton = new JButton("����");
		secEastPanel1.add(title2);
		secEastPanel2.add(overlapOpenButton);
		secEastPanel.add(secEastPanel1);
		secEastPanel.add(secEastPanel2);
		eastPanel.add(secEastPanel);
		
		tirEastPanel = new JPanel(new GridLayout(3,1));
		tirEastPanel1 = new JPanel(new FlowLayout());
		tirEastPanel2 = new JPanel(new FlowLayout());
		tirEastPanel3 = new JPanel(new FlowLayout());
		title3 = new JLabel("3. ������ �̱� ����");
		insertNum = new JTextField(10);
		insertNum.setUI(new JTextFieldHintUI("�ο� �� �Է�", Color.BLACK));
		startButton = new JButton("����");
		tirEastPanel1.add(title3);
		tirEastPanel2.add(insertNum);
		tirEastPanel3.add(startButton);
		tirEastPanel.add(tirEastPanel1);
		tirEastPanel.add(tirEastPanel2);
		tirEastPanel.add(tirEastPanel3);
		eastPanel.add(tirEastPanel);
		
		add(eastPanel, BorderLayout.EAST);
		
		//�Ʒ���
		southPanel = new JPanel(new FlowLayout());
		southPanel.add(consoleScrollPane);
		add(southPanel, BorderLayout.SOUTH);
		
		//���
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		information = new JLabel("<html>��������<br/></html>");
		information.setFont(new Font("consol", Font.BOLD, 25));
		centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
		
		fileName = new JLabel("���� �̸�"); // ���� �̸�
		fileSize = new JLabel("���� ������"); // ���� ������
		totalCount = new JLabel("�� �ο� ��"); // �� �ο� ��
		sheetName = new JLabel("Sheet �̸�"); // sheet�̸�
		subCount = new JLabel("���ŵ� �ο� ��"); // ���ŵ� �ο� ��
		centerPanel.add(information);
		centerPanel.add(fileName);
		centerPanel.add(fileSize);
		centerPanel.add(totalCount);
		centerPanel.add(sheetName);
		centerPanel.add(subCount);
		add(centerPanel, BorderLayout.CENTER);	
	}

	public void start() {
		openButton.addActionListener(this);
		saveButton.addActionListener(this);
		overlapOpenButton.addActionListener(this);

		fileChooser.setFileFilter(new FileNameExtensionFilter("���� 2007-2013 ���չ���(*.xlsx)", "xlsx"));
		fileChooser.setFileFilter(new FileNameExtensionFilter("���� 2003-2007 ���չ���(*.xls)", "xls"));
		// ���� ����
		fileChooser.setMultiSelectionEnabled(false);// ���� ���� �Ұ�
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == openButton) {
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				// showopendialog ���� â�� ���� Ȯ�� ��ư�� �������� Ȯ��
				String openFilePath = fileChooser.getSelectedFile().toString();
				if(fileMatch(openFilePath).equals("xls")){
					//xls����
				}else if(fileMatch(openFilePath).equals("xlsx")){
					//xlsx����
					openFile(openFilePath);
				}
			}
		} else if (e.getSource() == saveButton) {
			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				// showSaveDialog ���� â�� ���� Ȯ�� ��ư�� �������� Ȯ��
				String saveFilePath = fileChooser.getSelectedFile().toString() + "."
						+ fileChooser.getFileFilter().getDescription();
				System.out.println(saveFilePath);
			}
		} else if(e.getSource() == overlapOpenButton){
			if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
				//�ߺ� ���� ��ư Ŭ��
			}
		}
	}
	
	public String fileMatch(String openFilePath){
		String result = null;
		
		int index = openFilePath.lastIndexOf(".");
		result = openFilePath.substring(index+1);		
		return result;
	}
	
	public void addLog(String log){
		console.append(log+"\n");
		console.setCaretPosition(console.getDocument().getLength());
	}
	
	public void openFile(String filePath) {
		System.out.println("testing... XlsxOpenClass in openXlsxMethod.." + filePath);
		long start = System.currentTimeMillis();
		File f = new File(filePath);
		
		int rowIndex = 0;
		int colIndex = 0;
		try {
			FileInputStream fis = new FileInputStream(f);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			
			System.out.println("sheet�� : " + wb.getNumberOfSheets());
			System.out.println("sheet�̸� : " + wb.getSheetName(0));
			System.out.println("getLastRowNum : "+sheet.getLastRowNum());
			System.out.println("rows : " + rows);
			System.out.println("�ɸ� �ð� : " + (System.currentTimeMillis() - start / 1000.0));
			
			for(rowIndex = 0;rowIndex < rows;rowIndex++){
				XSSFRow row = sheet.getRow(rowIndex);
				if(row != null){
					int cells = row.getPhysicalNumberOfCells();
					for(colIndex = 0;colIndex < cells;colIndex++){
						XSSFCell cell = row.getCell(colIndex);
						System.out.println("cell value : " + cell);
						String value = "";
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
						addLog("�� �� ���� : "+value);
					}
				}
			}
		} catch (FileNotFoundException e) {
			addLog("������ ã�� �� �����ϴ�.");
			e.printStackTrace();
		} catch(IOException e){
			addLog("����� ����");
			e.printStackTrace();
		}
	}	
}