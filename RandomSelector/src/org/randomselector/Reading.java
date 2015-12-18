package org.randomselector;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
  
public class Reading
{
    private JTextArea ta;
    private JProgressBar progressBar;
    private int value;
    private ArrayList<String> testList = null;
  
    public Reading()
    {
        final JFileChooser fc = new JFileChooser(".");
        fc.setMultiSelectionEnabled(false);
        ta = new JTextArea();
		ta.setLineWrap(true);
		ta.setEditable(false);
        progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
        progressBar.setStringPainted(true);
        final JButton
            open = new JButton("open"),
            read = new JButton("read");
        testList = new ArrayList<String>();
        ActionListener l = new ActionListener()
        {
            File selectedFile;
  
            public void actionPerformed(ActionEvent e)
            {
                JButton button = (JButton)e.getSource();
                if(button == open)
                {
                    if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                    {
                        selectedFile = fc.getSelectedFile();
                        readFile(selectedFile);
                    }
                }
                if(button == read)
                    readFile(selectedFile);
            }
        };
        open.addActionListener(l);
        read.addActionListener(l);
        JPanel north = new JPanel();
        north.add(open);
        north.add(read);
        JPanel south = new JPanel();
        south.add(progressBar);
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(north, "North");
        f.getContentPane().add(new JScrollPane(ta));
        f.getContentPane().add(south, "South");
        f.setSize(600,400);
        f.setLocation(200,200);
        f.setVisible(true);
    }
  
    private void readFile(final File f)
    {
        ta.setText("");
        // read in a separate thread to allow gui to be responsive to user
        new Thread(new Runnable()
        {
            public void run()
            {
                BufferedReader br = null;
                
                try
                {
                    br = new BufferedReader(
                         new InputStreamReader(
                         new FileInputStream(f)));
                    long size = f.length(), len = 0;
                    String line = null;
                    int i=0;
                    while((line = br.readLine()) != null)
                    {
                    	ta.append(line + "\n");
                        len += line.length() + 2;       // "\n" length = 2
                        value = (int)((100 * len)/size);
                        progressBar.setValue(value);
                        // slow down the action to allow
                        // demonstration using small files
                        testList.add(line);
                        System.out.println(testList.get(i));
                        i++;
                    }
                    br.close();
                }catch(IOException ioe)
                {
                    System.err.println("read: " + ioe.getMessage());
                }
            }
        }).start();
    }
  
    public static void main(String[] args)
    {
        new Reading();
    }
}