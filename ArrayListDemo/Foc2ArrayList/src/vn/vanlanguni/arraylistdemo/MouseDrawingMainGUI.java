package vn.vanlanguni.arraylistdemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Huyen Pham
 *
 */
@SuppressWarnings("serial")
public class MouseDrawingMainGUI extends JFrame implements ActionListener {
	MouseDrawingPanel pnlMain;
	JPanel pnlTop;
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtW;
	private JTextField txtH;
	private JButton btnColor;
	private JButton btnAdd;
	private JButton btnClear;
	private Color drawColor = Color.BLACK;
	KeyAdapter key;
	FocusAdapter focus;

	public MouseDrawingMainGUI() {
		setTitle("Simple Drawing Using Mouse - Space64");
		pnlTop = new JPanel();
		pnlTop.setPreferredSize(new Dimension(500, 70));
		pnlTop.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(pnlTop, BorderLayout.NORTH);
		pnlTop.setLayout(null);

		// Label
		JLabel lblX = new JLabel("X");
		lblX.setBounds(68, 11, 15, 22);
		pnlTop.add(lblX);

		JLabel lblY = new JLabel("Y");
		lblY.setBounds(68, 33, 15, 22);
		pnlTop.add(lblY);

		JLabel lblWidth = new JLabel("Width");
		lblWidth.setBounds(160, 11, 39, 22);
		pnlTop.add(lblWidth);

		JLabel lblHeight = new JLabel("Height");
		lblHeight.setBounds(160, 33, 39, 22);
		pnlTop.add(lblHeight);

		// Textfield
		txtX = new JTextField();
		txtX.setBounds(78, 12, 61, 20);
		pnlTop.add(txtX);
		txtX.setColumns(10);

		txtY = new JTextField();
		txtY.setColumns(10);
		txtY.setBounds(78, 34, 61, 20);
		pnlTop.add(txtY);

		txtW = new JTextField();
		txtW.setColumns(10);
		txtW.setBounds(198, 11, 61, 20);
		pnlTop.add(txtW);

		txtH = new JTextField();
		txtH.setColumns(10);
		txtH.setBounds(198, 33, 61, 20);
		pnlTop.add(txtH);

		// Button Add
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(this);
		btnAdd.setBounds(283, 11, 72, 44);
		pnlTop.add(btnAdd);

		// Button Clear
		btnClear = new JButton("Clear");
		btnClear.addActionListener(this);
		btnClear.setBounds(365, 11, 74, 44);
		pnlTop.add(btnClear);

		// Button Color
		btnColor = new JButton("");
		btnColor.setBackground(drawColor);
		btnColor.addActionListener(this);
		btnColor.setBounds(9, 12, 48, 43);
		pnlTop.add(btnColor);

		// Create listener
		key = new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() < '0' || e.getKeyChar() > '9') {
					e.consume();
				}
			}
		};

		focus = new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				super.focusGained(e);
				if (e.getSource().getClass().equals(JTextField.class)) {
					((JTextField) e.getSource()).selectAll();
				}
			}
		};

		// Add listener
		txtX.addKeyListener(key);
		txtY.addKeyListener(key);
		txtW.addKeyListener(key);
		txtH.addKeyListener(key);
		txtX.addFocusListener(focus);
		txtY.addFocusListener(focus);
		txtW.addFocusListener(focus);
		txtH.addFocusListener(focus);

		// Main panel
		pnlMain = new MouseDrawingPanel();
		getContentPane().add(pnlMain, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			addRectangle();
		} else if (e.getSource() == btnClear) {
			clear();
		} else if (e.getSource() == btnColor) {
			selectColor();
		}
	}

	private void clear() {
		txtX.setText("");
		txtY.setText("");
		txtW.setText("");
		txtH.setText("");
		drawColor = Color.BLACK;
		btnColor.setBackground(drawColor);
		pnlMain.getRectangleList().clear();
		pnlMain.reDrawRectangleList();
	}

	private void selectColor() {
		drawColor = JColorChooser.showDialog(MouseDrawingMainGUI.this, "Select a draw color", drawColor);
		btnColor.setBackground(drawColor);
		pnlMain.setDefaultColor(drawColor);
	}

	private void addRectangle() {
		if (!txtX.getText().trim().equals("") && !txtY.getText().trim().equals("") && !txtW.getText().trim().equals("")
				&& !txtH.getText().trim().equals("")) {
			int x = Integer.parseInt(txtX.getText());
			int y = Integer.parseInt(txtY.getText());
			int w = Integer.parseInt(txtW.getText());
			int h = Integer.parseInt(txtH.getText());
			MyRectangle rect = new MyRectangle(x, y, w, h, drawColor);
			pnlMain.getRectangleList().add(rect);
			pnlMain.reDrawRectangleList();
			txtX.requestFocus();
		}else{
			JOptionPane.showMessageDialog(this, "Please enter x, y, width, height");
		}
	}

	public static void main(String[] args) {
		MouseDrawingMainGUI f = new MouseDrawingMainGUI();
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
