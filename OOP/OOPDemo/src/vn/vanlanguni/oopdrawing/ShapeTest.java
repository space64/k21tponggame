package vn.vanlanguni.oopdrawing;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class ShapeTest extends JFrame {
	ArrayList<Shape> list;
	Canvas pnl;
	public ShapeTest() {
		list = new ArrayList<>();
		pnl = new Canvas();
		pnl.setPreferredSize(new Dimension(600, 600));
		setContentPane(pnl);
		pack();
	}

	public static void main(String[] args) {
		ShapeTest f = new ShapeTest();
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setTitle("Demo");
		f.setLocationRelativeTo(null);
		f.setVisible(true);

	}

	private class Canvas extends JPanel implements MouseListener{

		public Canvas(){
			addMouseListener(this);
			setFocusable(true);
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			for (Iterator<Shape> iterator = list.iterator(); iterator.hasNext();) {
				Shape shape = (Shape) iterator.next();
				shape.draw(g);
			}
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			Shape s;
			//System.out.println(e.toString());
			if (SwingUtilities.isLeftMouseButton(e)) {
				s = new Square(50, e.getPoint());
				s.setColor(Color.BLUE);
			} else {
				s = new Circle(25, e.getPoint());
				s.setColor(Color.RED);
			}
			if (s != null) {
				list.add(s);
			}
			repaint();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	}

}
