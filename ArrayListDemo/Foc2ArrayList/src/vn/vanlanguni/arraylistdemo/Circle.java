package vn.vanlanguni.arraylistdemo;

import java.awt.Color;
import java.awt.Point;

public class Circle {
	private Point center;
	private double radius;
	private Color lineColor;
	
	public Circle(Point center, double radius, Color lineColor) {
		super();
		this.center = center;
		this.radius = radius;
		this.lineColor = lineColor;
	}
	
	
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public Color getLineColor() {
		return lineColor;
	}
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	private double x,y;
	
	private Point getCenter() {
		return center;
	}
	private void setCenter(Point center) {
		this.center = center;
	}
	
	
}
