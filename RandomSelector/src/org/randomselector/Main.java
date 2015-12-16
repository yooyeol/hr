package org.randomselector;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {
	public static void main(String[] args) {
		mainFrame tf = new mainFrame();
	}
}

class mainFrame extends JFrame implements ActionListener {
	
	private JFileChooser fileChooser = null;
	private JButton openButton = null;
	private JButton saveButton = null;
	private JButton exitButton = null;
	private JLabel rowNum = null;
	private JPanel northPanel = null;
	private JPanel westPanel = null;
	private JPanel eastPanel = null;
	private JPanel southPanel = null;
	private JPanel centerPanel = null;
	
	private int rows = 0;

	public mainFrame() {
		super("�K�K�K");
		this.init();
		this.start();
		this.setSize(800, 700);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}

	public void init() {
		fileChooser = new JFileChooser();
		openButton = new JButton("open");
		saveButton = new JButton("save");
		exitButton = new JButton("exit");
		rowNum = new JLabel("���� ����");
		getContentPane().setLayout(new BorderLayout());
		
		northPanel = new JPanel(new FlowLayout());
		northPanel.add(openButton);
		northPanel.add(saveButton);
		northPanel.add(exitButton);
		add(northPanel, BorderLayout.NORTH);
		
		westPanel = new JPanel(new FlowLayout());
		add(westPanel, BorderLayout.WEST);
		
		eastPanel = new JPanel(new FlowLayout());
		add(eastPanel, BorderLayout.EAST);
		
		southPanel = new JPanel(new FlowLayout());
		add(southPanel, BorderLayout.SOUTH);
		
		centerPanel = new JPanel(new FlowLayout());
		centerPanel.add(rowNum);
		add(centerPanel, BorderLayout.CENTER);	
	}

	public void start() {
		openButton.addActionListener(this);
		saveButton.addActionListener(this);
		exitButton.addActionListener(this);

		fileChooser.setFileFilter(new FileNameExtensionFilter("���� 2007-2013 ���չ���(*.xlsx)", "xlsx"));
		fileChooser.setFileFilter(new FileNameExtensionFilter("���� 2003-2007 ���չ���(*.xls)", "xls"));
		// ���� ����
		fileChooser.setMultiSelectionEnabled(false);// ���� ���� �Ұ�
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		XlsOpen openXls = new XlsOpen();
		XlsxOpen openXlsx = new XlsxOpen();
		
		if (e.getSource() == openButton) {
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				// showopendialog ���� â�� ���� Ȯ�� ��ư�� �������� Ȯ��
				String openFilePath = fileChooser.getSelectedFile().toString();
				if(fileMatch(openFilePath).equals("xls")){
					openXls.openFile(openFilePath);
				}else if(fileMatch(openFilePath).equals("xlsx")){
					openXlsx.openFile(openFilePath);
				}
			}
		} else if (e.getSource() == saveButton) {
			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				// showSaveDialog ���� â�� ���� Ȯ�� ��ư�� �������� Ȯ��
				String saveFilePath = fileChooser.getSelectedFile().toString() + "."
						+ fileChooser.getFileFilter().getDescription();
				System.out.println(saveFilePath);
			}
		} else if(e.getSource() == exitButton){
			System.exit(0);
		}
	}
	
	public String fileMatch(String openFilePath){
		String result = null;
		
		int index = openFilePath.lastIndexOf(".");
		result = openFilePath.substring(index+1);		
		return result;
	}
}
