import java.awt.Graphics;
import java.awt.Point;

public class Square extends Shape {
	private int sideWidth;

	public Square(int sideWidth, Point center) {
		super("Square");
		this.center = center;
		this.sideWidth = sideWidth;
	}

	public int getSideWidth() {
		return sideWidth;
	}

	public void setSideWidth(int sideWidth) {
		this.sideWidth = sideWidth;
	}

	@Override
	public double getArea() {
		return sideWidth * sideWidth;
	}

	@Override
	public double getPerimeter() {
		return sideWidth * 4;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		int cornerX = center.x - (sideWidth / 2);
		int cornerY = center.y - (sideWidth / 2);
		g.drawRect(cornerX, cornerY, sideWidth, sideWidth);
	}

}
