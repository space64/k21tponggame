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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

	public ArrayList<Rectangle> getRectangleList() {
		return rectangleList;
	}

	public void setRectangleList(ArrayList<Rectangle> rectangleList) {
		this.rectangleList = rectangleList;
		repaint();
	}

	public Color getDefaultColor() {
		return defaultColor;
	}

	public void setDefaultColor(Color defaultColor) {
		this.defaultColor = defaultColor;
	}

	public void reDrawRectangleList() {
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Draw rectangles in a list
		Graphics2D g2d = (Graphics2D) g.create();
		if (rectangleList != null) {
			BasicStroke stroke = new BasicStroke(2.5f);
			g2d.setStroke(stroke);
			for (Rectangle rect : rectangleList) {
				if (rect.getClass().equals(MyRectangle.class)) {
					Color c = ((MyRectangle) rect).getColor();
					g2d.setColor(c);
				} else {
					g2d.setColor(defaultColor);
				}
				g2d.drawRect(rect.x, rect.y, rect.width, rect.height);
			}
		}
		// Draw temporary rectangle while user draw using mouse
		if (dragged && tmpRect != null) {
			float dash1[] = { 10.0f };
			BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1,
					0.0f);
			g2d.setStroke(dashed);
			g2d.setColor(tmpRect.getColor());
			g2d.drawRect(tmpRect.x, tmpRect.y, tmpRect.width, tmpRect.height);
		}
		// Draw mouse coordinate
		if (showCoordinate) {
			g2d.setFont(new Font("Tahoma", Font.BOLD, 10));
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.drawString(String.format("x: %d", mouseX), mouseX + 10, mouseY + 25);
			g2d.drawString(String.format("y: %d", mouseY), mouseX + 10, mouseY + 35);
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		dragged = true;
		int x2 = e.getX();
		int y2 = e.getY();
		mouseX = e.getX();
		mouseY = e.getY();
		int recX = x1 < x2 ? x1 : x2;
		int recY = y1 < y2 ? y1 : y2;
		int width = Math.abs(x1 - x2);
		int height = Math.abs(y1 - y2);
		tmpRect = new MyRectangle(recX, recY, width, height, defaultColor);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		showCoordinate = true;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		showCoordinate = false;
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
		if (x1 != x2 && y1 != y2) {
			int recX = x1 < x2 ? x1 : x2;
			int recY = y1 < y2 ? y1 : y2;
			int width = Math.abs(x1 - x2);
			int height = Math.abs(y1 - y2);
			MyRectangle rect = new MyRectangle(recX, recY, width, height, defaultColor);
			rectangleList.add(rect);
			tmpRect = null;
		}
		dragged = false;
	}
}
