import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {
	private Color color;
	private String name;
	
	public Shape(String sName){
		name = sName;
		color = Color.BLACK;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public abstract double getArea();
	public abstract double getPerimeter();
	public abstract void draw(Graphics g);
}
