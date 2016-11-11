package demo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class CanvasPanel extends JPanel{
	Rectangle rect;
	Color buttonColor = Color.BLUE;
	
	public CanvasPanel() {
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
}
