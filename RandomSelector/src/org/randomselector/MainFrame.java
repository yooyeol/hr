package org.randomselector;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new MainFrame();
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
	private JLabel totalCount = null; // 총 인원 수
	private JLabel subCount = null; // 제거된 인원 수
	private JLabel remainingCnt = null; // 남은 인원 수
	private JLabel originCheckEncoding = null; // 인코딩 확인
	private JLabel confirmCheckEncoding = null;
	private JLabel title1 = null; // 타이틀1
	private JLabel title2 = null; // 타이틀2
	private JLabel title3 = null; // 타이틀3
	private JLabel information = null;
	
	private JProgressBar progressBar = null;
	int progressValue;
	private JLabel originFileName = null;
	private JLabel originFilePath = null;
	private JLabel confirmFileName = null;
	private JLabel confirmFilePath = null;
	private JLabel confirmPeopleNum = null;
	private ArrayList<String> firstList = null;
	private ArrayList<String> confirmList = null;
	
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
		progressBar = new JProgressBar(JProgressBar.HORIZONTAL,0,100);
		progressBar.setStringPainted(true);
		northPanel.add(progressBar);
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
		
		originFileName = new JLabel("원본 파일 이름"); // 파일 이름
		originFilePath = new JLabel("원본 파일 위치"); // 파일 사이즈
		totalCount = new JLabel("총 인원 수"); // 총 인원 수
		confirmFileName = new JLabel("이전 당첨자 파일 이름");
		confirmFilePath = new JLabel("이전 당첨자 파일 위치");
		confirmPeopleNum = new JLabel("이전 당첨자 인원 수");
		subCount = new JLabel("제거된 인원 수"); // 제거된 인원 수
		remainingCnt = new JLabel("남은 인원 수"); // 남은 인원 수
		originCheckEncoding = new JLabel("파일 인코딩 형식"); // 파일 인코딩 형식
		confirmCheckEncoding = new JLabel("파일 인코딩 형식");
		centerPanel.add(information);
		centerPanel.add(originFileName);
		centerPanel.add(originFilePath);
		centerPanel.add(originCheckEncoding);
		centerPanel.add(totalCount);
		centerPanel.add(confirmFileName);
		centerPanel.add(confirmFilePath);
		centerPanel.add(confirmCheckEncoding);
		centerPanel.add(confirmPeopleNum);
		centerPanel.add(subCount);
		centerPanel.add(remainingCnt);
		add(centerPanel, BorderLayout.CENTER);	
	}

	public void start() {
		firstList = new ArrayList<String>();
		confirmList = new ArrayList<String>();
		openButton.addActionListener(this);
		saveButton.addActionListener(this);
		overlapOpenButton.addActionListener(this);
		startButton.addActionListener(this);

//		fileChooser.setFileFilter(new FileNameExtensionFilter("엑셀 2007-2013 통합문서(*.xlsx)", "xlsx"));
//		fileChooser.setFileFilter(new FileNameExtensionFilter("엑셀 2003-2007 통합문서(*.xls)", "xls"));
		fileChooser.setFileFilter(new FileNameExtensionFilter("CSV (쉼표로 분리)(*.csv)", "csv"));
		// 파일 필터
		fileChooser.setMultiSelectionEnabled(false);// 다중 선택 불가
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		FileMatch fileMatch = new FileMatch();
		
		if (e.getSource() == openButton) {
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				// showopendialog 열기 창을 열고 확인 버튼을 눌렀는지 확인
				File openFilePath = fileChooser.getSelectedFile();
				/*if(fileMatch.fileMatch(openFilePath.toString()).equals("xls")){
					//xls파일
				}else if(fileMatch.fileMatch(openFilePath.toString()).equals("xlsx")){
					//xlsx파일
				}*/
				if(fileMatch.fileMatch(openFilePath.toString()).equals("csv")){
					firstReadFile(openFilePath);
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
				File openFilePath = fileChooser.getSelectedFile();
				if(fileMatch.fileMatch(openFilePath.toString()).equals("csv")){
					lastReadFile(openFilePath);
				}
			}
		}
	}
	
	public void addLog(String log){
		console.append(log+"\n");
	}

	private void firstReadFile(final File f){
		new Thread(new Runnable(){
			public void run(){
				BufferedReader br = null;
				
				originFileName.setText("원본 파일 이름 : " + f.getName());
				originFilePath.setText("원본 파일 경로 : " + f.getAbsolutePath());
				originCheckEncoding.setText("파일 인코딩 형식 : " + new FindEncoding().confirmEncoding(f.getAbsolutePath()));
				try{
					br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
					long size = f.length();
					int len = 0;
					int lineNum = 0;
					String strLine = null;
					while((strLine = br.readLine()) != null){
						firstList.add(strLine);
						lineNum++;
						len += strLine.length() + 2;
						progressValue = (int)((100*len)/size);
						progressBar.setValue(progressValue);
						totalCount.setText("총 인원 수 : " + lineNum);
						if(progressValue == 100){
							addLog("** 정렬을 시작합니다. **\n** 정렬이 완료 될 때 까지 잠시만 기다려 주세요. **");
							MergeSort m = new MergeSort(firstList);
							m.sort();
							addLog("** 정렬이 완료되었습니다. **");
						}
					}
					br.close();
				}catch(IOException e){
					addLog(e.getMessage());
				}
			}
		}).start();
	}
	
	private void lastReadFile(final File f) {
		new Thread(new Runnable(){
			public void run(){
				BufferedReader br = null;
				
				confirmFileName.setText("이전 당첨자 파일 이름 : " + f.getName());
				confirmFilePath.setText("이전 당첨자 파일 위치 : " + f.getAbsolutePath());
				confirmCheckEncoding.setText("파일 인코딩 형식 : " + new FindEncoding().confirmEncoding(f.getAbsolutePath()));
				try{
					br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
					long size = f.length();
					int len = 0;
					int lineNum = 0;
					String strLine = null;
					while((strLine = br.readLine()) != null){
						confirmList.add(strLine);
						lineNum++;
						len += strLine.length() + 2;
						progressValue = (int)((100*len)/size);
						progressBar.setValue(progressValue);
						confirmPeopleNum.setText("이전 당첨자 인원 수 : " + lineNum);
						if(progressValue == 100){
							addLog("** 정렬을 시작합니다. **\n** 잠시만 기다려 주세요. **");
							MergeSort m = new MergeSort(confirmList);
							m.sort();
							addLog("** 정렬이 완료되었습니다. **");
							addLog("** 중복 제거를 시작합니다. 잠시만 기다려 주세요. **");
							deleteDuplication(firstList, confirmList);
							addLog("** 중복 제거가 완료되었습니다. **");
						}
					}
					br.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public void deleteDuplication(ArrayList<String> firstList, ArrayList<String>confirmList){
		int duplicateIndex = 0;
		System.out.println("firstList.size : " + firstList.size());
		System.out.println("confirmList.size : " + confirmList.size());
		
		for(int i=0;i<firstList.size();i++){
			for(int j=0;j<confirmList.size();j++){
				try{
					if(firstList.get(i).equals(confirmList.get(j))){
						duplicateIndex++;
						firstList.remove(i);
						subCount.setText("제거된 인원 수 : " + String.valueOf(duplicateIndex));
						remainingCnt.setText("남은 인원 수 : " + String.valueOf(firstList.size()));
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		confirmList.clear();
	}
	
	public void randomSelect(ArrayList<String> firstList){
		
	}
}