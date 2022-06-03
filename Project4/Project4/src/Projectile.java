/* Name: Maricruz Tolosa Amaya
 * Net ID: mtolosaa
 * Project #4
 * Lab: MW 2:00-3:15
 * 
 * I did not collaborate with anyone on this assignment
 */
import java.awt.Color;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;





public class Projectile extends JFrame{
	protected JLabel scoreText;
	protected int score = 0;
	protected JPanel anglePanel;
	protected JPanel velocityPanel;
	protected JPanel buttonPanel;
	protected JPanel scorePanel;
	protected JSlider launchAngle;
	protected JSlider initialVelocity;
	protected JLabel angleLabel;
	protected JLabel velocityLabel;
	protected JButton newGame;
	protected JButton launch;
	protected JPanel launchPanel;
	protected JButton newRound;
	protected JLabel roundLabel;
	protected JPanel roundPanel;
	protected JPanel triesPanel;
	protected JLabel triesLabel;
	protected JLabel resultLabel;
	protected JPanel resultPanel;
	protected JLabel gameOver;
	protected JPanel overPanel;
	
	
	protected double distance;
	protected double angle; 
	protected double velocity;
	protected double gravity;
	
	protected int round = 1;
	protected int remainingRounds;
	protected int launchNum = 0;
	protected Random random;
	protected int remainingTries = 3;
	
	protected int heightAtWall;
	protected int screenX;
	protected int screenY; 
	protected int RandomWallX;
	protected int RandomWallY;
	protected int RandomWallHeight;
	protected int RandomWallWidth;
	protected Color RandomWallcolor;
	
	 //created string arrays depending on the outcome
	protected String[] SuccessStrings = {"You did it!", "Yay! Congrats!", "Success", "Good job!", "Awesome" };
	protected String[] FailStrings = {"Good luck next time!", "Sorry!", "Try again", "Aww, better luck next time"};
	protected JLabel  successLabel;
	protected JLabel failLabel;
	protected JPanel successPanel;
	protected JPanel failPanel;
	
	protected boolean triesLeft = true;
	protected boolean isLaunching = false;
	protected Timer timer;
	
	private static final double g = -9.8; //positive because Y axis is positive going down
    private int animationSpeed = 5; //millis. The smaller the faster
    private static int  ballDiameter = 10;
    private int ballX, ballY;
    private double time; 
    private double deltaTime = 1 ; //in seconds
    protected int wallSpeed;
    private List<Integer> XcurvePoints= new ArrayList<>();
    private List<Integer> YcurvePoints= new ArrayList<>();
   
    protected ButtonListener buttonListener = new ButtonListener();
	
	//constructor
	public Projectile() {
		
		
		
		//create buttons
		newGame = new JButton("Start Game");
		newGame.addActionListener(buttonListener);
		launch = new JButton("Launch");
		launch.addActionListener(buttonListener);
		
		//creating score, round, result random success/fail string, and tries remaining label
		scoreText = new JLabel("Score: " + score);
		roundLabel = new JLabel("Round: " + round);
		resultLabel = new JLabel(" Result: ");
		successLabel = new JLabel(" ");
		failLabel = new JLabel(" ");
		triesLabel = new JLabel("Remaining Tries: " + remainingTries);
		gameOver  = new JLabel(" ");
		roundLabel = new JLabel("Round: " + round);
		
		
		//creating labels for sliders
		angleLabel  = new JLabel("Launch Angle");
		velocityLabel = new JLabel("Velocity");	
		
		
		//create the sliders 
		launchAngle = new JSlider(0, 90, 25);
		launchAngle.addChangeListener(new SliderListener());
		initialVelocity = new JSlider(0, 200, 50);
		initialVelocity.addChangeListener(new SliderListener());
		
		//creating panels 
		anglePanel = new JPanel();
		velocityPanel = new JPanel();
		buttonPanel = new JPanel();
		scorePanel = new JPanel();
		launchPanel = new JPanel();
		resultPanel = new JPanel();
		successPanel = new JPanel();
		failPanel = new JPanel();
		triesPanel = new JPanel();
		overPanel = new JPanel();
		roundPanel = new JPanel();
		
		
		//adding sliders + button + labels to panels
		anglePanel.add(launchAngle);
		anglePanel.add(angleLabel);
		velocityPanel.add(initialVelocity);
		velocityPanel.add(velocityLabel);
		buttonPanel.add(newGame);
		scorePanel.add(scoreText);
		launchPanel.add(launch);
		resultPanel.add(resultLabel);
		successPanel.add(successLabel);
		failPanel.add(failLabel);
		triesPanel.add(triesLabel);
		overPanel.add(gameOver);
		roundPanel.add(roundLabel);
		
		//layout 
		setLayout(new FlowLayout());
		
		
		//adding panels to frame
		add(roundPanel);
		add(anglePanel);
		add(velocityPanel);
		add(launchPanel);
		add(scorePanel);
		add(triesPanel);
		add(buttonPanel);
		add(resultPanel);
		add(failPanel);
		add(successPanel);
		add(overPanel);
		
	
		ballX = 0;
	    ballY  = getHeight();
		random = new Random();
		

        triesLeft = true;
       
        
        
        generateWall();
        repaint();
        
		setVisible(true);
		setTitle("Project 4");
		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
		
	
	}
	
	
	
	//handles change events when slider is moved
	protected class SliderListener implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent e) {
			Object src = e.getSource();
			
			//gets updated value of sliders
			if(src == launchAngle) {
				//launch angle slider range 0 - 100
				angle =launchAngle.getValue() ; //gets value of the angle
				
				
			}else {
				//velocity slider range 0 - 200 
				velocity = initialVelocity.getValue() ; //makes it go faster 
			}
			
		}
	
	}
	
	public class BallUpdate implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			//calculating position
			ballX = (int) (velocity * time * Math.cos(Math.toRadians(angle)));
			ballY = (int) (velocity * time * Math.sin(Math.toRadians(angle)) - (0.5 * 9.8 * time *time )); 
			ballY = getHeight() - ballY;
			time += deltaTime;
			wallSpeed = 10;
			RandomWallX = (int) Math.min(getWidth()-20, RandomWallX - wallSpeed*deltaTime); // wall can only move in between frame size
			
			
			//adding points to the List 
			XcurvePoints.add(ballX);
			YcurvePoints.add(ballY);
			
			boolean done = false;
			repaint();
			
			//ball is over frame size
			if (ballX > getWidth() || ballY > getHeight()) {
				done = true;
			
			}else if(ballX > RandomWallX && ballX < RandomWallX + 20 && ballY > RandomWallHeight && ballY < getHeight()) { //ball needs to be in frame
				done = true;
			}
			//System.out.println("time: " + time);
			//System.out.println("x: " + ballX);
			//System.out.println("y: " + ballY);
			
			if (done) {
				checkLanding();
				timer.stop();
				isLaunching = false;
			}
			
			
		}
	}
	
	//handles when button is pressed 
	protected class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object src = e.getSource();
			//System.out.println(src);
			
			
			if(!isLaunching) { //only press button while not launching
				XcurvePoints.clear();
				YcurvePoints.clear();
				
				if (src == newGame) { //the New Game button was pressed
					
					//resetting everything
					triesLeft = true;
					round = 1;
					score = 0; //resets score when the button is pressed
					scoreText.setText("Score: " + score);
					launchNum = 0;
					remainingTries = 3;
					triesLabel.setText("Remaining Tries: " + remainingTries);
					roundLabel.setText("Round: " + round);
					resultLabel.setText(" ");
					successLabel.setText(" ");
					failLabel.setText(" ");
					gameOver.setText(" ");
					newGame.setText("New Game");
					generateWall();
					repaint();
					
				}else { //Launch button pressed
					resultLabel.setText(" ");
					successLabel.setText(" ");
					failLabel.setText(" ");
					gameOver.setText(" ");
					System.out.println("angle: " + angle);
					System.out.println("velocity: " + velocity);
					if(triesLeft && round<=3) {
						//erases what was previously there
						resultLabel.setText(" ");
						successLabel.setText(" ");
						
						//everytime the launch button is pressed, launch goes up by 1
						launchNum++;
						ballX = 0; 
						ballY = getHeight();
						timer = new Timer(500, new BallUpdate());
						time = 0;
						timer.start();
						isLaunching = true; //disables the button pressage
						
					} 
				
				}
			}
		}
	}
	
	public void checkLanding() {
		calculation();
		if ( heightAtWall > RandomWallHeight) { //makes sure that the projectile goes over the wall height and lands further from the wall
			int projDistance = (int) (heightAtWall - RandomWallHeight);
			resultLabel.setText(" ");
			successLabel.setText(" ");
			round++;
			roundLabel.setText("Round :" + round);
			
			if (heightAtWall - RandomWallHeight <=3) { //made it over the wall by 3m or less
            	
				successLabel.setText( SuccessStrings[random.nextInt(SuccessStrings.length)]); //randomly prints out a statement from array
             
                resultLabel.setText("Projectile made it by " + projDistance+ "meters");
            	//System.out.println("Projectile made it by 3m or less"); //prints result
                
            	//score has a net of 3
                score = score + 3 - launchNum;
                scoreText.setText("Score: " + score);
                //repaint();
            
            }else  { //made it over the wall by over 3 m
            	successLabel.setText( SuccessStrings[random.nextInt(SuccessStrings.length)]);
            	//System.out.println("Projectile made it by over 3 meters!"); //prints result
            	resultLabel.setText("Projectile made it by " + projDistance + "meters");
                
            	//score has a net of +5
            	score = score + 5 - launchNum;
	            scoreText.setText("Score: " + score);
	            //repaint();
            
            }
        }else { //projectile does not make it over the wall
        	
        	resultLabel.setText(" ");
			successLabel.setText(" ");
        	remainingTries--; //only 3 chances
        	triesLabel.setText("Remaining Tries: " + remainingTries);
            
        	int wallDistance = (int) (RandomWallHeight - distance); 
            
        	if (distance - RandomWallX >= -3){ //didnt make it by 3m
            	failLabel.setText(FailStrings[random.nextInt(FailStrings.length)]);
            	//System.out.println("Not quite over. Projectile did not make it over by 3m or less");
            	score = score - 1 - launchNum;
	            scoreText.setText("Score: " + score);
	           resultLabel.setText("Projectile did not make it by " + wallDistance + "meters");
                //score =  score - 1 - launch; //net score of -1
               
            }else { //did not make it over 3m
            	failLabel.setText(FailStrings[random.nextInt(FailStrings.length)]);
            	//System.out.println(FailStrings[random.nextInt(FailStrings.length)]);
            	
            	score = score - 2 - launchNum; //net score of -2
	            scoreText.setText("Score: " + score);
                

	            resultLabel.setText("Projectile did not make it by " + wallDistance+ "meters");
            }
        }
	}
	
	//calculation 
	public void calculation() {
		//getting values from the sliders
		angle = launchAngle.getValue();
		velocity= initialVelocity.getValue();
		
		//equation from the Project4  assignment
		double numerator = (Math.pow(velocity, 2) * Math.sin(Math.toRadians(angle)*2));
		distance = numerator / (9.8);
		
		
		//calculating the height (from Project 1)
		double yA = RandomWallX * Math.tan(Math.toRadians(angle));
		double yB = 9.8 * Math.pow(RandomWallX, 2);
		double yC = 2 * Math.pow(velocity * Math.cos(Math.toRadians(angle)), 2);
		heightAtWall = (int) (yA - (yB / yC));
		
		
	
	
	}
	//creates a wall(size and color)
	public void generateWall() {
		
		screenY = getHeight();
		screenX = getWidth();
		
		
		//creating a random color everytime
		int red = (int) ( 255* Math.random());
		int green = (int) ( 255* Math.random());
		int blue = (int) ( 255* Math.random());
		RandomWallcolor = new Color(red, green, blue);
		
		int width = 20; //fixed width 
		
		//Random height (range from frame Height/2 to frame height)
		RandomWallHeight = (int) (screenY /2  * Math.random());
		
		//Random Wall Distance range
		int RandomWallDistance = (int) (screenX  * Math.random() - width);
		
		//Random X position
		RandomWallX = (int) (screenX - RandomWallDistance - width);
		
		
		//Y has to be max, so the size of the frame
		RandomWallY =  screenY  - RandomWallHeight;
		
	}
	
	//painting method
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		
	//made a random color
		
		g.setColor(RandomWallcolor);
		
		//(X, Y, width, height)
		//g.drawRect(RandomWallX, RandomWallY, width, RandomWallHeight);
		g.fillRect(RandomWallX, RandomWallY, 20, RandomWallHeight);
		
		
		
		
		g.fillOval(ballX, ballY, ballDiameter, ballDiameter); //creating the ball
		g.setColor(RandomWallcolor);
		//creating the line for the trajectory
		for (int i = 1; i < XcurvePoints.size(); i++ ) {
			int X1 = XcurvePoints.get(i -1);
			int Y1 = YcurvePoints.get(i -1);
			int X2 = XcurvePoints.get(i);
			int Y2 = YcurvePoints.get(i);
			g.drawLine(X1, Y1, X2, Y2);
		}
		//System.out.println("paint");
		
	}
		
	
	//main method
	public static void main(String[] args) {
		new Projectile().setVisible(true);
	}
	
	
}
