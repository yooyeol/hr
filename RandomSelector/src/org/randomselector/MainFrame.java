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
	
	private JFileChooser fileChooser = null; // 파일 선택 창
	private JButton openButton = null; // 처음 파일 오픈 버튼
	private JButton saveButton = null; // 저장 버튼
	private JButton overlapOpenButton = null; // 중복 파일 오픈 버튼
	private JButton startButton = null; // 랜덤 뽑기 시작 버튼
	
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
	
	private JScrollPane consoleScrollPane = null; // 콘솔창 스크롤
	private JTextArea console = null; // 콘솔
	private JTextField insertNum = null;// 뽑을 인원수 체크
	//파일 정보 관련 선언
	private JLabel fileName = null; // 파일 이름
	private JLabel fileSize = null; // 파일 사이즈
	private JLabel totalCount = null; // 총 인원 수
	private JLabel sheetName = null; // sheet이름
	private JLabel subCount = null; // 제거된 인원 수
	private JLabel title1 = null; // 타이틀1
	private JLabel title2 = null; // 타이틀2
	private JLabel title3 = null; // 타이틀3
	private JLabel information = null;
	
	@SuppressWarnings("static-access")
	public MainFrame() {
		super("핳핳핳");
		this.init();
		this.start();
		this.setSize(600, 550);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}

	public void init() {
		fileChooser = new JFileChooser();
		overlapOpenButton = new JButton("이미 당첨된 사람");
		startButton = new JButton("랜덤뽑기 시작");
		
		console = new JTextArea();
		console.setLineWrap(true); //한줄이 너무 길면 자동으로 개행할지 설정
		console.setColumns(50); //열의 크기(가로크기)
		console.setRows(10); //행의 크기(세로크기)
		console.setEditable(false);
		
		consoleScrollPane = new JScrollPane(console);
		getContentPane().setLayout(new BorderLayout());
		
		//위쪽
		northPanel = new JPanel(new FlowLayout());
		add(northPanel, BorderLayout.NORTH);
		
		//왼쪽
		westPanel = new JPanel(new FlowLayout());
		add(westPanel, BorderLayout.WEST);
		
		//오른쪽
		eastPanel = new JPanel(new GridLayout(3,1));
		firEastPanel = new JPanel(new GridLayout(2,1));
		firEastPanel1 = new JPanel(new FlowLayout());
		firEastPanel2 = new JPanel(new FlowLayout());
		openButton = new JButton("열기");
		saveButton = new JButton("저장");
		title1 = new JLabel("1. 파일 불러오기 & 저장");
		firEastPanel1.add(title1);
		firEastPanel2.add(openButton);
		firEastPanel2.add(saveButton);
		firEastPanel.add(firEastPanel1);
		firEastPanel.add(firEastPanel2);
		eastPanel.add(firEastPanel);

		secEastPanel = new JPanel(new GridLayout(2,1));
		secEastPanel1 = new JPanel(new FlowLayout());
		secEastPanel2 = new JPanel(new FlowLayout());
		title2 = new JLabel("2. 이전 당첨자 불러오기");
		overlapOpenButton = new JButton("열기");
		secEastPanel1.add(title2);
		secEastPanel2.add(overlapOpenButton);
		secEastPanel.add(secEastPanel1);
		secEastPanel.add(secEastPanel2);
		eastPanel.add(secEastPanel);
		
		tirEastPanel = new JPanel(new GridLayout(3,1));
		tirEastPanel1 = new JPanel(new FlowLayout());
		tirEastPanel2 = new JPanel(new FlowLayout());
		tirEastPanel3 = new JPanel(new FlowLayout());
		title3 = new JLabel("3. 무작위 뽑기 실행");
		insertNum = new JTextField(10);
		insertNum.setUI(new JTextFieldHintUI("인원 수 입력", Color.BLACK));
		startButton = new JButton("실행");
		tirEastPanel1.add(title3);
		tirEastPanel2.add(insertNum);
		tirEastPanel3.add(startButton);
		tirEastPanel.add(tirEastPanel1);
		tirEastPanel.add(tirEastPanel2);
		tirEastPanel.add(tirEastPanel3);
		eastPanel.add(tirEastPanel);
		
		add(eastPanel, BorderLayout.EAST);
		
		//아래쪽
		southPanel = new JPanel(new FlowLayout());
		southPanel.add(consoleScrollPane);
		add(southPanel, BorderLayout.SOUTH);
		
		//가운데
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		information = new JLabel("<html>상태정보<br/></html>");
		information.setFont(new Font("consol", Font.BOLD, 25));
		centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
		
		fileName = new JLabel("파일 이름"); // 파일 이름
		fileSize = new JLabel("파일 사이즈"); // 파일 사이즈
		totalCount = new JLabel("총 인원 수"); // 총 인원 수
		sheetName = new JLabel("Sheet 이름"); // sheet이름
		subCount = new JLabel("제거된 인원 수"); // 제거된 인원 수
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

		fileChooser.setFileFilter(new FileNameExtensionFilter("엑셀 2007-2013 통합문서(*.xlsx)", "xlsx"));
		fileChooser.setFileFilter(new FileNameExtensionFilter("엑셀 2003-2007 통합문서(*.xls)", "xls"));
		// 파일 필터
		fileChooser.setMultiSelectionEnabled(false);// 다중 선택 불가
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == openButton) {
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				// showopendialog 열기 창을 열고 확인 버튼을 눌렀는지 확인
				String openFilePath = fileChooser.getSelectedFile().toString();
				if(fileMatch(openFilePath).equals("xls")){
					//xls파일
				}else if(fileMatch(openFilePath).equals("xlsx")){
					//xlsx파일
					openFile(openFilePath);
				}
			}
		} else if (e.getSource() == saveButton) {
			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				// showSaveDialog 저장 창을 열고 확인 버튼을 눌렀는지 확인
				String saveFilePath = fileChooser.getSelectedFile().toString() + "."
						+ fileChooser.getFileFilter().getDescription();
				System.out.println(saveFilePath);
			}
		} else if(e.getSource() == overlapOpenButton){
			if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
				//중복 제거 버튼 클릭
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
			
			System.out.println("sheet수 : " + wb.getNumberOfSheets());
			System.out.println("sheet이름 : " + wb.getSheetName(0));
			System.out.println("getLastRowNum : "+sheet.getLastRowNum());
			System.out.println("rows : " + rows);
			System.out.println("걸린 시간 : " + (System.currentTimeMillis() - start / 1000.0));
			
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
						addLog("각 셀 내용 : "+value);
					}
				}
			}
		} catch (FileNotFoundException e) {
			addLog("파일을 찾을 수 없습니다.");
			e.printStackTrace();
		} catch(IOException e){
			addLog("입출력 오류");
			e.printStackTrace();
		}
	}	
}