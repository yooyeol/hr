package org.randomselector;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ProgressMonitorInputStream;

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
	        //   to read the file. A new thread is needed because we
	        //   need to free the GUI update thread to paint the
	        //   progress monitor
	        new Thread() {
	          public void run() {
	            try {
	              // open the file, wrapping it in a ProgressMonitorInputStream
	              InputStream in = new FileInputStream("C:\\Users\\kyy\\Documents\\123.txt");
	              ProgressMonitorInputStream pm = 
	                  new ProgressMonitorInputStream(new JFrame(),"Reading the big file",in);
	              StringBuffer buffer = new StringBuffer();
	              byte[] b = new byte[4096];
	              // read the file. If it's taking too long, the progress
	              //   monitor will appear. The amount of time is roughly
	              //   1/100th of the estimated read time (based on how long
	              //   it took to read the first 1/100th of the file.)
	              //   Note that by default, the dialog won't appear unless
	              //   the overall estimate is over 2 seconds.
	              int c;
	              while((c=pm.read(b)) != -1) {
	            	  buffer.append(new String(b, 0, c));
	            	  System.out.println(c);
	              }
	              System.out.println(buffer.toString());
	              pm.close(); // needs better error handling, of course...
	            }catch(Exception ex) {
	              ex.printStackTrace();
	            }
	          }
	        }.start();
	      }});  
	  
	    // display the frame
	    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    f.setVisible(true);
	  }
	}