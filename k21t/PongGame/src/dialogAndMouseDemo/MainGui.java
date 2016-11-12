package dialogAndMouseDemo;

import javax.swing.JFrame;

@SuppressWarnings("serial")
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
