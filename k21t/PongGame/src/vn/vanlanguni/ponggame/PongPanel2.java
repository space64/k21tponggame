/*
 * PONG GAME REQUIREMENTS
 * This simple "tennis like" game features two paddles and a ball, 
 * the goal is to defeat your opponent by being the first one to gain 3 point,
 *  a player gets a point once the opponent misses a ball. 
 *  The game can be played with two human players, one on the left and one on 
 *  the right. They use keyboard to start/restart game and control the paddles. 
 *  The ball and two paddles should be red and separating lines should be green. 
 *  Players score should be blue and background should be black.
 *  Keyboard requirements:
 *  + P key: start
 *  + Space key: restart
 *  + W/S key: move paddle up/down
 *  + Up/Down key: move paddle up/down
 *  
 *  Version: 0.5
 */
package vn.vanlanguni.ponggame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 
 * @author Invisible Man
 *
 */
public class PongPanel2 extends JPanel implements ActionListener, KeyListener {
	private static final long serialVersionUID = -1097341635155021546L;

	private boolean showTitleScreen = true;
	private boolean playing;
	private boolean gameOver;

	/** Background. */
	private Color backgroundColor = Color.BLACK;
	private ImageIcon backgroundImage;

	/** State on the control keys. */
	private boolean upPressed;
	private boolean downPressed;
	private boolean wPressed;
	private boolean sPressed;
	
	//Random +/-
	private int timeToDisplay;
	private boolean showRandom;
	private int xRanBall;
	private int yRanBall;

	/** The ball: position, diameter */
	private int ballX = 250;
	private int ballY = 250;
	private int diameter = 40;
	private int ballDeltaX = -2;
	private int ballDeltaY = 5;
	ImageIcon imgBall;

	/** Player 1's paddle: position and size */
	private int playerOneX = 25;
	private int playerOneY = 250;
	private int playerOneWidth = 10;
	private int playerOneHeight = 80;

	/** Player 2's paddle: position and size */
	private int playerTwoX = 465;
	private int playerTwoY = 250;
	private int playerTwoWidth = 10;
	private int playerTwoHeight = 80;

	/** Speed of the paddle - How fast the paddle move. */
	private int paddleSpeed = 5;

	/** Player score, show on upper left and right. */
	private int playerOneScore;
	private int playerTwoScore;

	/** Construct a PongPanel. */
	public PongPanel2() {
		setBackground(backgroundColor);
		//Ball
		imgBall = new ImageIcon("images/ball-blue.png");
		//Background
		backgroundImage = new ImageIcon("images/bg.jpg");
		// listen to key presses
		setFocusable(true);
		addKeyListener(this);
		
		timeToDisplay = ThreadLocalRandom.current().nextInt(5, 15 + 1)*1000;
		
		// call step() 60 fps
		Timer timer = new Timer(1000 / 60, this);
		timer.start();
	}

	/** Implement actionPerformed */
	public void actionPerformed(ActionEvent e) {
		step();
	}

	/** Repeated task */
	public void step() {

		if (playing) {

			/* Playing mode */

			// move player 1
			// Move up if after moving, paddle is not outside the screen
			if (wPressed && playerOneY - paddleSpeed > 0) {
				playerOneY -= paddleSpeed;
			}
			// Move down if after moving paddle is not outside the screen
			if (sPressed && playerOneY + playerOneHeight + paddleSpeed < getHeight()) {
				playerOneY += paddleSpeed;
			}

			// move player 2
			// Move up if after moving paddle is not outside the screen
			if (upPressed && playerTwoY - paddleSpeed > 0) {
				playerTwoY -= paddleSpeed;
			}
			// Move down if after moving paddle is not outside the screen
			if (downPressed && playerTwoY + playerTwoHeight + paddleSpeed < getHeight()) {
				playerTwoY += paddleSpeed;
			}

			/*
			 * where will the ball be after it moves? calculate 4 corners: Left,
			 * Right, Top, Bottom of the ball used to determine whether the ball
			 * was out yet
			 */
			int nextBallLeft = ballX + ballDeltaX;
			int nextBallRight = ballX + diameter + ballDeltaX;
			int nextBallTop = ballY + ballDeltaY;
			int nextBallBottom = ballY + diameter + ballDeltaY;

			// Player 1's paddle position
			int playerOneRight = playerOneX + playerOneWidth;
			int playerOneTop = playerOneY;
			int playerOneBottom = playerOneY + playerOneHeight;

			// Player 2's paddle position
			float playerTwoLeft = playerTwoX;
			float playerTwoTop = playerTwoY;
			float playerTwoBottom = playerTwoY + playerTwoHeight;

			// ball bounces off top and bottom of screen
			if (nextBallTop < 0 || nextBallBottom > getHeight()) {
				ballDeltaY *= -1;
				Sound.play("sound/sound.wav");
			}

			// will the ball go off the left side?
			if (nextBallLeft < playerOneRight) {
				// is it going to miss the paddle?
				if (nextBallTop > playerOneBottom || nextBallBottom < playerOneTop) {

					playerTwoScore++;

					// Player 2 Win, restart the game
					if (playerTwoScore == 30) {
						playing = false;
						gameOver = true;
					}
					ballX = 250;
					ballY = 250;
				} else {
					// If the ball hitting the paddle, it will bounce back
					ballDeltaX *= -1;
					Sound.play("sound/sound.wav");
				}
			}

			// will the ball go off the right side?
			if (nextBallRight > playerTwoLeft) {
				// is it going to miss the paddle?
				if (nextBallTop > playerTwoBottom || nextBallBottom < playerTwoTop) {

					playerOneScore++;

					// Player 1 Win, restart the game
					if (playerOneScore == 30) {
						playing = false;
						gameOver = true;
					}
					ballX = 250;
					ballY = 250;
				} else {

					// If the ball hitting the paddle, it will bounce back
					ballDeltaX *= -1;
					Sound.play("sound/sound.wav");
				}
			}

			// move the ball
			ballX += ballDeltaX;
			ballY += ballDeltaY;
			
			//Show random
			timeToDisplay -= 1000/60;
			System.out.println(timeToDisplay+" x: "+xRanBall+" y:"+yRanBall);
			if(timeToDisplay < 0){
				if(!showRandom){
					showRandom = true;
					xRanBall = ThreadLocalRandom.current().nextInt(50, 520 + 1);
					yRanBall = ThreadLocalRandom.current().nextInt(50, 520 + 1);
				}else{
					//Kiem tra giao nhau
					Point ballCenter = new Point(ballX+diameter/2, ballY+diameter/2);
					Point ranCenter = new Point(xRanBall+15, yRanBall+15);
					double distanceBetween2Center = getPointDistance(ballCenter, ranCenter);
					if(distanceBetween2Center < (diameter/2+15)){
						playerOneHeight -= 20;
						showRandom = false;
						timeToDisplay = ThreadLocalRandom.current().nextInt(5, 15 + 1)*1000;
					}
				}
				if(timeToDisplay < - 15000){
					showRandom = false;
					timeToDisplay = ThreadLocalRandom.current().nextInt(5, 15 + 1)*1000;
				}
			}
		}

		// stuff has moved, tell this JPanel to repaint itself
		repaint();
	}

	public double getPointDistance(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
	}
	/** Paint the game screen. */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.setColor(Color.WHITE);

		if (showTitleScreen) {

			/* Show welcome screen */

			// Draw game title and start message
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
			g.drawString("Pong Game", 130, 100);

			g.setFont(new Font(Font.DIALOG, Font.BOLD, 18));

			g.drawString("Press 'P' to play.", 175, 400);
		} else if (playing) {

			/* Game is playing */
			
			g.drawImage(backgroundImage.getImage(), 0, 0, 500, 500, null);
			// set the coordinate limit
			int playerOneRight = playerOneX + playerOneWidth;
			int playerTwoLeft = playerTwoX;

			// draw dashed line down center
			for (int lineY = 0; lineY < getHeight(); lineY += 50) {
				g.drawLine(250, lineY, 250, lineY + 25);
			}

			// draw "goal lines" on each side
			g.setColor(Color.GREEN);
			g.drawLine(playerOneRight, 0, playerOneRight, getHeight());
			g.drawLine(playerTwoLeft, 0, playerTwoLeft, getHeight());

			// draw the scores
			g.setColor(Color.BLUE);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
			g.drawString(String.valueOf(playerOneScore), 100, 100); // Player 1
																	// score
			g.drawString(String.valueOf(playerTwoScore), 400, 100); // Player 2
																	// score

			// draw the ball
			g.setColor(Color.RED);
			//g.fillOval(ballX, ballY, diameter, diameter);
			g.drawImage(imgBall.getImage(), ballX, ballY, diameter, diameter, null);
			// draw the paddles
			g.fillRect(playerOneX, playerOneY, playerOneWidth, playerOneHeight);
			g.fillRect(playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight);
			
			// Show random
			if(showRandom){
				g.fillOval(xRanBall, yRanBall, 30, 30);
			}
		} else if (gameOver) {

			/* Show End game screen with winner name and score */

			// Draw scores
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
			g.drawString(String.valueOf(playerOneScore), 100, 100);
			g.drawString(String.valueOf(playerTwoScore), 400, 100);

			// Draw the winner name
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
			if (playerOneScore > playerTwoScore) {
				g.drawString("Player 1 Wins!", 165, 200);
			} else {
				g.drawString("Player 2 Wins!", 165, 200);
			}

			// Draw Restart message
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
			g.drawString("Press space to restart.", 150, 400);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if (showTitleScreen) {
			if (e.getKeyCode() == KeyEvent.VK_P) {
				showTitleScreen = false;
				playing = true;
			}
		} else if (playing) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				upPressed = true;
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				downPressed = true;
			} else if (e.getKeyCode() == KeyEvent.VK_W) {
				wPressed = true;
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				sPressed = true;
			}
		} else if (gameOver && e.getKeyCode() == KeyEvent.VK_SPACE) {
			gameOver = false;
			showTitleScreen = true;
			playerOneY = 250;
			playerTwoY = 250;
			ballX = 250;
			ballY = 250;
			playerOneScore = 0;
			playerTwoScore = 0;
		}
	}

	public void keyReleased(KeyEvent e) {
		if (playing) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				upPressed = false;
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				downPressed = false;
			} else if (e.getKeyCode() == KeyEvent.VK_W) {
				wPressed = false;
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				sPressed = false;
			}
		}
	}

}
