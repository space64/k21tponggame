package vn.vanlanguni.arraylistdemo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 
 * @author Huyen Pham
 *
 */
@SuppressWarnings("serial")
public class MouseDrawingPanel extends JPanel implements MouseListener, MouseMotionListener {
	ArrayList<Rectangle> rectangleList;

	Color defaultColor = Color.BLACK;
	boolean dragged;
	boolean showCoordinate;
	MyRectangle tmpRect;
	int x1, y1;
	int mouseX, mouseY;

	public MouseDrawingPanel() {
		setPreferredSize(new Dimension(500, 400));
		setFocusable(true);
		setBackground(Color.WHITE);
		rectangleList = new ArrayList<>(10);
		addMouseListener(this);
		addMouseMotionListener(this);
		Timer t = new Timer(1000 / 60, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				repaint();
			}
		});
		t.start();
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g.create();
		float dash1[] = { 10.0f};
		BasicStroke stroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, 
				BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
		g2d.setStroke(stroke);
		if(tmpRect != null){
			g2d.drawRect(tmpRect.x, tmpRect.y, tmpRect.width, tmpRect.height);
		}
		
		g2d.setStroke(new BasicStroke(3.0f));
		for(Rectangle tmp: rectangleList){
			g2d.drawRect(tmp.x, tmp.y, tmp.width, tmp.height);
		}
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		int x2 = e.getX();
		int y2 = e.getY();
		int w = Math.abs(x2 - x1);
		int h = Math.abs(y2 - y1);
		int x = (x1 < x2) ? x1 : x2;
		int y = (y1 < y2) ? y1 : y2;
		tmpRect = new MyRectangle(x, y, w, h);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int x2 = e.getX();
		int y2 = e.getY();
		int w = Math.abs(x2 - x1);
		int h = Math.abs(y2 - y1);
		int x = (x1 < x2) ? x1 : x2;
		int y = (y1 < y2) ? y1 : y2;
		tmpRect = new MyRectangle(x, y, w, h);
		rectangleList.add(tmpRect);

	}
}
