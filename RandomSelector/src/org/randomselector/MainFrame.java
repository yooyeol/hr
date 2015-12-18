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
	private JLabel totalCount = null; // �� �ο� ��
	private JLabel subCount = null; // ���ŵ� �ο� ��
	private JLabel remainingCnt = null; // ���� �ο� ��
	private JLabel originCheckEncoding = null; // ���ڵ� Ȯ��
	private JLabel confirmCheckEncoding = null;
	private JLabel title1 = null; // Ÿ��Ʋ1
	private JLabel title2 = null; // Ÿ��Ʋ2
	private JLabel title3 = null; // Ÿ��Ʋ3
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
		progressBar = new JProgressBar(JProgressBar.HORIZONTAL,0,100);
		progressBar.setStringPainted(true);
		northPanel.add(progressBar);
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
		
		originFileName = new JLabel("���� ���� �̸�"); // ���� �̸�
		originFilePath = new JLabel("���� ���� ��ġ"); // ���� ������
		totalCount = new JLabel("�� �ο� ��"); // �� �ο� ��
		confirmFileName = new JLabel("���� ��÷�� ���� �̸�");
		confirmFilePath = new JLabel("���� ��÷�� ���� ��ġ");
		confirmPeopleNum = new JLabel("���� ��÷�� �ο� ��");
		subCount = new JLabel("���ŵ� �ο� ��"); // ���ŵ� �ο� ��
		remainingCnt = new JLabel("���� �ο� ��"); // ���� �ο� ��
		originCheckEncoding = new JLabel("���� ���ڵ� ����"); // ���� ���ڵ� ����
		confirmCheckEncoding = new JLabel("���� ���ڵ� ����");
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

//		fileChooser.setFileFilter(new FileNameExtensionFilter("���� 2007-2013 ���չ���(*.xlsx)", "xlsx"));
//		fileChooser.setFileFilter(new FileNameExtensionFilter("���� 2003-2007 ���չ���(*.xls)", "xls"));
		fileChooser.setFileFilter(new FileNameExtensionFilter("CSV (��ǥ�� �и�)(*.csv)", "csv"));
		// ���� ����
		fileChooser.setMultiSelectionEnabled(false);// ���� ���� �Ұ�
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		FileMatch fileMatch = new FileMatch();
		
		if (e.getSource() == openButton) {
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				// showopendialog ���� â�� ���� Ȯ�� ��ư�� �������� Ȯ��
				File openFilePath = fileChooser.getSelectedFile();
				/*if(fileMatch.fileMatch(openFilePath.toString()).equals("xls")){
					//xls����
				}else if(fileMatch.fileMatch(openFilePath.toString()).equals("xlsx")){
					//xlsx����
				}*/
				if(fileMatch.fileMatch(openFilePath.toString()).equals("csv")){
					firstReadFile(openFilePath);
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
				
				originFileName.setText("���� ���� �̸� : " + f.getName());
				originFilePath.setText("���� ���� ��� : " + f.getAbsolutePath());
				originCheckEncoding.setText("���� ���ڵ� ���� : " + new FindEncoding().confirmEncoding(f.getAbsolutePath()));
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
						totalCount.setText("�� �ο� �� : " + lineNum);
						if(progressValue == 100){
							addLog("** ������ �����մϴ�. **\n** ������ �Ϸ� �� �� ���� ��ø� ��ٷ� �ּ���. **");
							MergeSort m = new MergeSort(firstList);
							m.sort();
							addLog("** ������ �Ϸ�Ǿ����ϴ�. **");
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
				
				confirmFileName.setText("���� ��÷�� ���� �̸� : " + f.getName());
				confirmFilePath.setText("���� ��÷�� ���� ��ġ : " + f.getAbsolutePath());
				confirmCheckEncoding.setText("���� ���ڵ� ���� : " + new FindEncoding().confirmEncoding(f.getAbsolutePath()));
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
						confirmPeopleNum.setText("���� ��÷�� �ο� �� : " + lineNum);
						if(progressValue == 100){
							addLog("** ������ �����մϴ�. **\n** ��ø� ��ٷ� �ּ���. **");
							MergeSort m = new MergeSort(confirmList);
							m.sort();
							addLog("** ������ �Ϸ�Ǿ����ϴ�. **");
							addLog("** �ߺ� ���Ÿ� �����մϴ�. ��ø� ��ٷ� �ּ���. **");
							deleteDuplication(firstList, confirmList);
							addLog("** �ߺ� ���Ű� �Ϸ�Ǿ����ϴ�. **");
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
						subCount.setText("���ŵ� �ο� �� : " + String.valueOf(duplicateIndex));
						remainingCnt.setText("���� �ο� �� : " + String.valueOf(firstList.size()));
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