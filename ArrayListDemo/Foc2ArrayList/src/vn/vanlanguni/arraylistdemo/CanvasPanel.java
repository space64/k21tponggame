package vn.vanlanguni.arraylistdemo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CanvasPanel extends JPanel{
	ArrayList<Rectangle> rectangleList;
	Color defaultColor = Color.BLACK;

	public CanvasPanel() {
		setPreferredSize(new Dimension(500, 400));
		setFocusable(true);
		rectangleList = new ArrayList<>(10);
	}

	public ArrayList<Rectangle> getRectangleList() {
		return rectangleList;
	}

	public void setRectangleList(ArrayList<Rectangle> rectangleList) {
		this.rectangleList = rectangleList;
		repaint();
	}
	public void reDrawRectangleList(){
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g.create();
		if (rectangleList != null) {
			BasicStroke stroke = new BasicStroke(3);
			g2d.setStroke(stroke);
			for (Rectangle rect : rectangleList) {
				if(rect.getClass().equals(MyRectangle.class)){
					Color c = ((MyRectangle)rect).getColor();
					g2d.setColor(c);
				}else{
					g2d.setColor(defaultColor);
				}
				g2d.drawRect(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}
}
