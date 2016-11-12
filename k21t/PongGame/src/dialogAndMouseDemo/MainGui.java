package dialogAndMouseDemo;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MainGui extends JFrame {
	CanvasPanel pnl;
	
	public MainGui() {
		setTitle("Demo");
		pnl = new CanvasPanel();
		setContentPane(pnl);
		pack();
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		MainGui f = new MainGui();
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setVisible(true);

	}

}
