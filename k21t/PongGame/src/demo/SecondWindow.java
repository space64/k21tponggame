package demo;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class SecondWindow extends JFrame {
	
	public SecondWindow() {
		setPreferredSize(new Dimension(300, 300));
		setTitle("Second Window");
		setLayout(null);
		pack();
	}
	
	public String getSettings(){
		return "bgcolor:Black;paddleColor:Blue";
	}
}
