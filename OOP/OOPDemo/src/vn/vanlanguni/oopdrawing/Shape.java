package vn.vanlanguni.oopdrawing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Shape implements Cloneable{
	private Color color;
	private String name;

	protected Point center;
	
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

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}
	
	@Override
	public String toString(){
		return String.format("%s[%d-%d]", name, center.x, center.y);
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
