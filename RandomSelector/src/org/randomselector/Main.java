package org.randomselector;

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

	public mainFrame() {
		super("핳핳핳");
		this.init();
		this.start();
		this.setSize(400, 200);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}

	public void init() {
		fileChooser = new JFileChooser();
		openButton = new JButton("open");
		saveButton = new JButton("save");
		exitButton = new JButton("exit");
		getContentPane().setLayout(new FlowLayout());
		add(openButton);
		add(saveButton);
		add(exitButton);
	}

	public void start() {
		openButton.addActionListener(this);
		saveButton.addActionListener(this);
		exitButton.addActionListener(this);

		fileChooser.setFileFilter(new FileNameExtensionFilter("엑셀 2007-2013 통합문서(*.xlsx)", "xlsx"));
		fileChooser.setFileFilter(new FileNameExtensionFilter("엑셀 2003-2007 통합문서(*.xls)", "xls"));
		// 파일 필터
		fileChooser.setMultiSelectionEnabled(false);// 다중 선택 불가
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		XlsOpen openXls = new XlsOpen();
		XlsxOpen openXlsx = new XlsxOpen();
		
		if (e.getSource() == openButton) {
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				// showopendialog 열기 창을 열고 확인 버튼을 눌렀는지 확인
				String openFilePath = fileChooser.getSelectedFile().toString();
				if(fileMatch(openFilePath).equals("xls")){
					openXls.openFile(openFilePath);
				}else if(fileMatch(openFilePath).equals("xlsx")){
					openXlsx.openFile(openFilePath);
				}
			}
		} else if (e.getSource() == saveButton) {
			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				// showSaveDialog 저장 창을 열고 확인 버튼을 눌렀는지 확인
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
		
		int index = openFilePath.indexOf(".");
		result = openFilePath.substring(index+1);		
		return result;
	}
}
