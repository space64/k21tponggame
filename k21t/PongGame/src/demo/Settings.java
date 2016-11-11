package demo;

import java.awt.Color;

public class Settings {
	private String userName1, userName2;
	private Color backgroundColor, paddleColor, ballColor;
	
	public String getUserName2() {
		return userName2;
	}

	public void setUserName2(String userName2) {
		this.userName2 = userName2;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Color getPaddleColor() {
		return paddleColor;
	}

	public void setPaddleColor(Color paddleColor) {
		this.paddleColor = paddleColor;
	}

	public Color getBallColor() {
		return ballColor;
	}

	public void setBallColor(Color ballColor) {
		this.ballColor = ballColor;
	}

	public String getUserName1(){
		return userName1;
	}
	
	public void setUserName1(String uname){
		userName1 = uname;
	}

}
