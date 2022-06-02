/*
	 * Name: Maricruz Tolosa Amaya  ID: 29988518
	 * Lab: TR 11:05 - 12:20
	 * Project 04
	 * I did not collaborate with anyone on this assignment 
	 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
//import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Game extends JPanel implements ActionListener, KeyListener {
	protected int baseX = 0; // starting x point for rocket
	protected int baseY = 200;// starting y point for rocket
	protected int angle = 0;
	protected Timer timer;
	protected Image rocket; // rocket image
	protected Image rocks; //rock image

	protected JLabel winning; // appears when the game is won
	protected JLabel lives; // number of lives left
	protected JLabel score; // score
	protected JLabel fuelElapsed; // how much fuel has elapsed
	protected JLabel gameOver;
	protected int fuel = 50; // starting fuel
	protected int userScore = 0; // starting score
	protected int livesRemaining = 3; // starting number of lives

	//height of the top part of the wall
	protected int topA; // level 1
	protected int topB;
	protected int topC;
	protected int topD;
	protected int topE;

	//height of the top part of the wall
	protected int bottomA; // level 1
	protected int bottomB;
	protected int bottomC;
	protected int bottomD;
	protected int bottomE;

	protected int topA2; // level 2
	protected int topB2;
	protected int topC2;
	protected int topD2;
	protected int topE2;

	protected int bottomA2; // level2
	protected int bottomB2;
	protected int bottomC2;
	protected int bottomD2;
	protected int bottomE2;

	protected int speed = 2; //how fast it moves
	protected int gravity = 1;

	protected int level = 0; // starting level

	Random random = new Random();
	
	public Game() {
		
		// level 1
		topA = random.nextInt(100) + 70; // randomly generated numbers for top part of wall
		topB = random.nextInt(100) + 70; 
		topC = random.nextInt(100) + 70;
		topD = random.nextInt(100) + 70;
		topE = random.nextInt(100) + 70;
		bottomA = random.nextInt(100) + 60;// randomly generated numbers for bottom part
		bottomB = random.nextInt(100) + 70;
		bottomC = random.nextInt(100) + 70;
		bottomD = random.nextInt(100) + 70;
		bottomE = random.nextInt(100) + 70;

		//level 2 wall
		topA2 = random.nextInt(51) + 150; // randomly generated numbers for top part of wall in level 2									
		topB2 = random.nextInt(51) + 150; 
		topC2 = random.nextInt(51) + 150;
		topD2 = random.nextInt(51) + 150;
		topE2 = random.nextInt(51) + 150;
		bottomA2 = random.nextInt(51) + 150; // randomly generated numbers for bottom part
		bottomB2 = random.nextInt(51) + 150; 
		bottomC2 = random.nextInt(51) + 150;
		bottomD2 = random.nextInt(51) + 150;
		bottomE2 = random.nextInt(51) + 150;

		this.addKeyListener(this); //listens to arrows
		this.setFocusable(true);
		this.requestFocusInWindow();
		timer = new Timer(50, new TimerCallback()); // how fast it goes
		timer.start(); // starting the animation

		try { //image of the rocket
			rocket = ImageIO.read(new File("src/download.png"));
			rocket = rocket.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			
		}catch (IOException ex) {
			System.out.println("IO Exception.");
		}

		try { //rocks 
			rocks = ImageIO.read(new File("src/rock.jpg"));
			rocks = rocks.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
		} catch (IOException ex) {
			System.out.println("IO Exception.");
		}
		
		
		//will appear on top of game screen
		winning = new JLabel("");
		add(winning);

		lives = new JLabel("");
		add(lives);

		score = new JLabel("");
		add(score);

		fuelElapsed = new JLabel("");
		add(fuelElapsed);

		gameOver = new JLabel("");
		add(gameOver);

	}
	
	public void restartGame() { // restarts the game if user runs out of fuel or lives
		System.out.println("Game over");
		
		//resets everything
		topA = random.nextInt(100) + 60;
		topB = random.nextInt(100) + 60;
		topC = random.nextInt(100) + 60;
		topD = random.nextInt(100) + 60;
		topE = random.nextInt(100) + 60;
		
		bottomA = random.nextInt(100) + 60;
		bottomB = random.nextInt(100) + 60;
		bottomC = random.nextInt(100) + 60;
		bottomD = random.nextInt(100) + 60;
		bottomE = random.nextInt(100) + 60;
		
		baseX = 0;
		baseY = 0;
		userScore = 0;
		livesRemaining = 4;
		fuel = 100;
		score.setText("score: " + String.valueOf(userScore));
		fuelElapsed.setText("fuel remaining: " + String.valueOf(fuel));
		lives.setText("lives remaining: " + String.valueOf(livesRemaining));
		
		}

	public void fuel() { //runs out of fuel 
		if (fuel <= 0) {
			restartGame(); //restarts game if there is no fuel left
		}
	}

	public void lives() { //lives
		if (livesRemaining <= 0) {
			restartGame(); //restarts game if there is no more lives
		}
	}

	
	public void bottomWall0() { // level 1
		if (level == 0) {
			//checks to see if rocket hits any part of the bottom wall. will deduct point and life.
			if (baseX <= 100 && baseX >= 0 && baseY + rocket.getHeight(null) >= 500 - bottomA) {
				baseX = 0;
				baseY = 250;
				userScore -= 15;
				livesRemaining -= 1;
				System.out.println(userScore);
				System.out.println(" b hit rectangle1");
				lives.setText("lives remaining: " + String.valueOf(livesRemaining));
			} else if (baseX <= 200 && baseX >= 100 && baseY + rocket.getHeight(null) >= 500 - bottomB) {
				baseX = 0;
				baseY = 250;
				userScore -= 15;
				livesRemaining -= 1;
				System.out.println(userScore);
				System.out.println("b hit rectangle2");
				lives.setText("lives remaining: " + String.valueOf(livesRemaining));
			} else if (baseX <= 300 && baseX >= 200 && baseY + rocket.getHeight(null)>= 500 - bottomC) {
				baseX = 0;
				baseY = 250;
				userScore -= 15;
				livesRemaining -= 1;
				System.out.println(userScore);
				System.out.println("b hit rectangle3");
				lives.setText("lives remaining: " + String.valueOf(livesRemaining));
			} else if (baseX <= 400 && baseX >= 300 && baseY + rocket.getHeight(null) >= 500 - bottomD) {
				baseX = 0;
				baseY = 250;
				userScore -= 15;
				livesRemaining -= 1;
				System.out.println(userScore);
				System.out.println("b hit rectangle4");
				lives.setText("lives remaining: " + String.valueOf(livesRemaining));
			}
		}
	}

	public void topWall0() { // level1
		if (level == 0) {
			//checks to see if rocket hits any part of the top wall. will deduct point and life.
			if (baseX <= 100 && baseY <= topA && baseY >= 0) {
				baseX = 0;
				baseY = 250;
				userScore -= 15;
				livesRemaining -= 1;
				System.out.println(userScore);
				System.out.println("t hit rectangle1");
				lives.setText("lives remaining: " + String.valueOf(livesRemaining));
			} else if (baseX <= 200 && baseX >= 100 && baseY <= topB && baseY >= 0) {
				baseX = 0;
				baseY = 250;
				userScore -= 15;
				livesRemaining -= 1;
				System.out.println(userScore);
				System.out.println("t hit rectangle2");
				lives.setText("lives remaining: " + String.valueOf(livesRemaining));
			} else if (baseX <= 300 && baseX >= 200 && baseY <= topC && baseY >= 0) {
				baseX = 0;
				baseY = 250;
				userScore -= 15;
				livesRemaining -= 1;
				System.out.println(userScore);
				System.out.println("t hit rectangle3");
				lives.setText("lives remaining: " + String.valueOf(livesRemaining));
			} else if (baseX <= 400 && baseX >= 300 && baseY <= topD && baseY >= 0) {
				baseX = 0;
				baseY = 250;
				userScore -= 15;
				livesRemaining -= 1;
				System.out.println(userScore);
				System.out.println("t hit rectangle4");
				lives.setText("lives remaining: " + String.valueOf(livesRemaining));
			} else if (baseX <= 500 && baseX >= 400 && baseY <= topE && baseY >= 0) {
				baseX = 0;
				baseY = 250;
				userScore -= 15;
				livesRemaining -= 1;
				System.out.println(userScore);
				System.out.println("t hit rectangle5");
				lives.setText("lives remaining: " + String.valueOf(livesRemaining));
			}
		}
	}
	public void topWall1() { // level 2
		if (level == 1) {
			//checks to see if rocket hits any part of the top wall. will deduct point and life.
			if (baseX <= 100 && baseY <= topA2 && baseY >= 0) {
				baseX = 0;
				baseY = 250;
				userScore -= 15;
				livesRemaining -= 1;
				System.out.println(userScore);
				System.out.println("hit rectangle 1");
				lives.setText("lives remaining: " + String.valueOf(livesRemaining));
			}else if (baseX <= 200 && baseX >= 100 && baseY <= topB2 && baseY >= 0) {
				baseX = 0;
				baseY = 250;
				userScore -= 15;
				livesRemaining -= 1;
				System.out.println(userScore);
				System.out.println("t hit rectangle2");
				lives.setText("lives remaining: " + String.valueOf(livesRemaining));
			} else if (baseX <= 300 && baseX <= 200 && baseY <= topC2 && baseY >= 0) {
				baseX = 0;
				baseY = 250;
				userScore -= 15;
				livesRemaining -= 1;
				System.out.println(userScore);
				System.out.println("t hit rectangle3");
				lives.setText("lives remaining: " + String.valueOf(livesRemaining));
			} else if (baseX <= 400 && baseX >= 300 && baseY <= topD2 && baseY >= 0) {
				baseX = 0;
				baseY = 250;
				userScore -= 15;
				livesRemaining -= 1;
				System.out.println(userScore);
				System.out.println("t hit rectangle4");
				lives.setText("lives remaining: " + String.valueOf(livesRemaining));
			} else if (baseX <= 500 && baseX >= 400 && baseY <= topE2 && baseY >= 0) {
				baseX = 0;
				baseY = 250;
				userScore -= 15;
				livesRemaining -= 1;
				System.out.println(userScore);
				System.out.println("t hit rectangle5");
				lives.setText("lives remaining: " + String.valueOf(livesRemaining));
			}
		}
	}
	public void bottomWall1() { // level 2
		if (level == 1) {
			//checks to see if rocket hits any part of the bottom wall. will deduct point and life.
			if (baseX <= 100 && baseX >= 100 && baseY >= 500 - bottomA2) {
				baseX = 0;
				baseY = 250;
				userScore -= 15;
				livesRemaining -= 1;
				System.out.println(userScore);
				System.out.println(" b hit rectangle1");
				lives.setText("lives remaining: " + String.valueOf(livesRemaining));
			} else if (baseX <= 200 && baseX >= 100 && baseY >= 500 - bottomB2) {
				baseX = 0;
				baseY = 250;
				userScore -= 15;
				livesRemaining -= 1;
				System.out.println(userScore);
				System.out.println("b hit rectangle2");
				lives.setText("lives remaining: " + String.valueOf(livesRemaining));
			} else if (baseX <= 300 && baseX <= 200 && baseY >= 500 - bottomC2) {
				baseX = 0;
				baseY = 250;
				userScore -= 15;
				livesRemaining -= 1;
				System.out.println(userScore);
				System.out.println("b hit rectangle3");
				lives.setText("lives remaining: " + String.valueOf(livesRemaining));
			} else if (baseX <= 400 && baseX >= 300 && baseY >= 500 - bottomD2) {
				baseX = 0;
				baseY = 250;
				userScore -= 15;
				livesRemaining -= 1;
				System.out.println(userScore);
				System.out.println("b hit rectangle4");
				lives.setText("lives remaining: " + String.valueOf(livesRemaining));
			}
		}
	}

	
	//if it makes it to the last rectangle 
	public void winner1() {
		if (level == 0) {
			if (baseX <= 500 && baseX >= 400 && baseY + rocket.getHeight(null) >= 500 - bottomE + 10) {
			System.out.println("Passed Level 1!");
			level = 1;
			baseX = 0;
			baseY = 200;
			fuel = 50;
			userScore += 100;
			}
		} else if (level == 1) {
			if (baseX <= 500 && baseX >= 400 && baseY + rocket.getHeight(null) >= 500 - bottomE2 + 10) {
				System.out.println("Winner!! ");
				winning.setText("YOU WON");
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(rocks, 0, 0, null);
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform af = g2.getTransform();
		g2.rotate(Math.toRadians(angle), 0, 0);
		g2.drawImage(rocket, baseX, baseY, null);
		g2.setTransform(af);
		
		if (level == 0) {
			g.setColor(Color.RED);
			g.drawString("LEVEL 1", 20, 300); 
			
			g.setColor(Color.DARK_GRAY);
			// top border
			g.fillRect(0, 0, 100, topA);
			g.fillRect(100, 0, 100, topB);
			g.fillRect(200, 0, 100, topC);
			g.fillRect(300, 0, 100, topD);
			g.fillRect(400, 0, 100, topE);
			
			// bottom border
			g.fillRect(0, 500 - bottomA, 100, bottomA);
			g.fillRect(100, 500 - bottomB, 100, bottomB);
			g.fillRect(200, 500 - bottomC, 100, bottomC);
			g.fillRect(300, 500 - bottomD, 100, bottomD);
			g.fillRect(400, 500 - bottomE, 100, bottomE);
			
			g.setColor(Color.RED);
			g.fillRect(400, 500 - bottomE, 100, 10); //finish platform
			g.drawImage(rocks, 200, 300, null);
			g.drawImage(rocks, 100, 200, null);
			
			
			
		}
		if (level == 1) {
			g.setColor(Color.RED);
			g.drawString("LEVEL 2", 50, 300);
			g.setColor(Color.gray);
			// top border
			g.fillRect(0, 0, 100, topA2);
			g.fillRect(100, 0, 100, topB2);
			g.fillRect(200, 0, 100, topC2);
			g.fillRect(300, 0, 100, topD2);
			g.fillRect(400, 0, 100, topE2);
			
			// bottom border
			g.fillRect(0, 500 - bottomA2, 100, bottomA2);
			g.fillRect(100, 500 - bottomB2, 100, bottomB2);
			g.fillRect(200, 500 - bottomC2, 100, bottomC2);
			g.fillRect(300, 500 - bottomD2, 100, bottomD2);
			g.fillRect(400, 500 - bottomE2, 100, bottomE2);
			
			g.drawImage(rocks, 200, 300, null);
			g.drawImage(rocks, 100, 200, null);
			
			g.setColor(Color.RED); //ending platform
			g.fillRect(400, 500 - bottomE2, 100, 10);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		//changes position depending on which key is pressed 
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			baseY -= 15*(Math.sin(angle));
			baseX += 15*(Math.cos(angle));
			System.out.println("up");
			
			userScore += 5;
			System.out.println(userScore);
			score.setText("score: " + String.valueOf(userScore));
			
			fuel -= 1;
			System.out.println(fuel);
			fuelElapsed.setText("fuel remaining: " + String.valueOf(fuel));
			
			repaint();
	
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			angle -= 7;
			
			System.out.println("left");
			
			userScore += 5;
			System.out.println(userScore);
			score.setText("score: " + String.valueOf(userScore));
			
			fuel -= 1;
			System.out.println(fuel);
			fuelElapsed.setText("fuel remaining: " + String.valueOf(fuel));
			
			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			angle += 7;
			
			System.out.println("right");
			
			userScore += 5;
			System.out.println(userScore);
			score.setText("score: " + String.valueOf(userScore));
			
			fuel -= 1;
			System.out.println(fuel);
			fuelElapsed.setText("fuel remaining: " + String.valueOf(fuel));
			
			repaint();
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		speed = 2;
		baseY = baseY + speed;
		
		repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	
	}
	
	protected class TimerCallback implements ActionListener {
	
		@Override
		public void actionPerformed(ActionEvent e) {
			topWall1();
			bottomWall1();
			topWall0();
			bottomWall0();
			lives();
			fuel();
			repaint();
			winner1();
			
			baseY = baseY + gravity; //gravity brings it down
			
		}
	}
}
