/* Name: Maricruz Tolosa Amaya
 * Net ID: mtolosaa
 * Project #3
 * Lab: MW 2:00-3:15
 * 
 * I did not collaborate with anyone on this assignment
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ChangeListener;

public class Firework extends JFrame implements ActionListener{
	
	protected JFrame frame;
	protected JTextField textAngle;
	protected JTextField textSpeed;
	protected JTextField textTime;
	protected JButton calculation;
	protected JSlider R;
	protected JSlider G; 
	protected JSlider B;
	protected JPanel sliderPanel;
	protected ChangeListener listener;
	
	protected double launchSpeed;
	protected double launchAngle;
	protected double launchTime;
	protected double launchX;
	protected double launchY;
	protected double X;
	protected double Y;
	protected double finalY;
	protected double g = -9.8;
	protected String Speed; 
	protected String Angle;
	
	
	protected double currX, currY;
	protected double lastX, lastY;
	protected Timer timer;
	
	
	public Firework() {
		setTitle("Fireworks, everything in constructor");
		//frame = new JFrame();
		/*
		//creating JPanel to add to JFrame
		R =new JSlider(0,100, 100);
		addSlider(R, "Red");
		
		sliderPanel = new JPanel();
		sliderPanel.setLayout(new GridLayout());
		*/
		//listener for the sliders 
		
		
		//angle
		textAngle =new JTextField("Enter a launch angle "); 
		textAngle.setBounds(0,0, 150,30); 
		
		//speed
		textSpeed =new JTextField("Enter a launch speed"); 
		textSpeed.setBounds(0,30, 150,30);  
		
		//time
		textTime = new JTextField("Enter a time for explosion");
		textTime.setBounds(0, 60, 150, 30);
		
		//Calculate button
		calculation = new JButton("Calculate");
		calculation.setBounds(150, 20, 150, 30);
		calculation.addActionListener(this);
		
		//adding Text Fields and Buttons 
		add(textAngle);
		add(textSpeed);
		add(textTime);
		
		add(calculation);
		
	
		setSize(600,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		
		/*
		//creating JSliders for color
		R =new JSlider(0,100, 100);
		addSlider(R, "Red");
		G = new JSlider(0, 100, 50);
		addSlider(G, "Green");
		B = new JSlider(0, 100, 0);
		addSlider(B, "Blue");
		*/
		
		
	}
	/*
	//add Jsliders
	public void addSlider(JSlider slider, String name) {
		slider.addChangeListener(listener);
		JPanel panel = new JPanel();
		panel.add(slider);
		panel.add(new JLabel(name));
		sliderPanel.add(panel);
		
	}
	*/
	
	//calculations for the projectile
	public double calculate() {
		
		
		X = launchSpeed * Math.cos(launchAngle) * launchTime;
		System.out.println("x in calculate: " + X);
		Y = (launchSpeed * Math.cos(launchAngle) * launchTime) - (0.5 * g * Math.pow(launchTime, 2));
		//System.out.println("y in calculate: " + Y);
		finalY = ((launchSpeed*Math.cos(launchAngle)*X)/(launchSpeed*Math.cos(launchAngle))) - (0.5*g*((X*X)/ Math.pow(launchSpeed*Math.cos(launchAngle), 2)));
		return finalY;
		
	}

		public void actionPerformed(ActionEvent e) {
			Object src = e.getSource();
			
			if (src == calculation) { //making sure the Calculation button was pressed 
				//textAngle.getText();
				//textSpeed.getText();
				
				//changing from String to double 
				launchAngle = Double.parseDouble(textAngle.getText());
				launchSpeed = Double.parseDouble(textSpeed.getText());
				launchTime = Double.parseDouble(textTime.getText());
				
				//System.out.println(launchAngle);
				//System.out.println(launchSpeed);
				
				//double answer = calculate(X, Y);
				double results = calculate();
				System.out.println("X is" + X);
				System.out.println("Y is " + Y);
				System.out.println("answer is " + results);
				System.out.println("y is " + Y);
				//System.out.println(answer);
			
			}else if (src == R || src == G || src == B) {
				if (src == R) {
					int Rvalue = R.getValue();
					System.out.println("R :" + Rvalue);
					
				}else if (src == G) {
					System.out.println("G");
				}else if(src == B) {
					System.out.println("B");
				}
		
			}
		
		}	
	
	
	
	//drawing method
	public void drawingProjectile(Graphics g) {
		//drawing arc 
		
	}
	
	public static void main(String[] args) {
		//JFrame frame = new JFrame();
		new Firework().setVisible(true);
		//frame.setSize(600,600);
		//setLayout(null);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
