import java.awt.Graphics;
import java.awt.Point;

public class Circle extends Shape {
	private int radius;
	private Point center;

	public Circle(int radius, Point center) {
		super("Circle");
		this.radius = radius;
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	@Override
	public double getArea() {
		return radius * radius * Math.PI;
	}

	@Override
	public double getPerimeter() {
		return radius * 2 * Math.PI;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		int cornerX = center.x - radius;
		int cornerY = center.y - radius;
		g.drawOval(cornerX, cornerY, radius * 2, radius * 2);

	}

}
