package vn.vanlanguni.arraylistdemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SimpleGUI extends JFrame {
	MouseDrawingPanel pnlMain;
	
	public SimpleGUI() {
		setTitle("Simple Drawing Using Mouse - Space64");
		pnlMain = new MouseDrawingPanel();
		getContentPane().add(pnlMain, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(null);
	}
	public static void main(String[] args) {
		SimpleGUI f = new SimpleGUI();
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setVisible(true);

	}

}
