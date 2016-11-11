package demo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class CanvasPanel extends JPanel implements MouseMotionListener, MouseListener{
	Color buttonColor = Color.BLUE;
	Rectangle rect;
	
	public CanvasPanel() {
		addMouseMotionListener(this);
		addMouseListener(this);
		setFocusable(true);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(buttonColor);
		
		int x = getWidth()/2-50;
		int y = getHeight()/2-15;
		int w = 100;
		int h = 30;
		rect = new Rectangle(x, y, w, h);
		g.fillRect(x, y, w, h);
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		//System.out.println(String.format("x=%d - y=%d", e.getX(), e.getY()));
		if(rect.contains(e.getX(),e.getY())){
			buttonColor = Color.RED;
		}else{
			buttonColor = Color.BLUE;
		}
		repaint();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(rect.contains(e.getPoint())){
			SecondWindow w = new SecondWindow();
			w.setVisible(true);
			Settings s = w.getSetings();
			
			System.out.println(s.getUserName1());
			System.out.println(s.getUserName2());
			w.dispose();
		}
		
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
