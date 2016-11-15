package vn.vanlanguni.arraylistdemo;
import java.awt.Color;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class MyRectangle extends Rectangle {
	private Color color;

	public MyRectangle(int x, int y, int w, int h) {
		super(x, y, w, h);
		color = Color.BLACK;
	}
	
	public MyRectangle(int x, int y, int w, int h, Color drawColor) {
		super(x, y, w, h);
		color = drawColor;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
