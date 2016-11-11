package demo;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class SecondWindow extends JDialog{
	JTextField txtInput1;
	JTextField txtInput2;
	
	public SecondWindow() {
		setPreferredSize(new Dimension(300, 300));
		setTitle("Second Window");
		setLayout(null);
		setModal(true);
		txtInput1 = new JTextField(10);
		txtInput2 = new JTextField(10);
		add(txtInput1);
		add(txtInput2);
		txtInput1.setBounds(10, 10, 100, 30);
		txtInput2.setBounds(10, 50, 100, 30);
		//setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
	}
	
	public Settings getSetings(){
		Settings st = new Settings();
		st.setUserName1(txtInput1.getText());
		st.setUserName2(txtInput2.getText());
		return st;
	}

}
