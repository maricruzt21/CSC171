import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class Fireworks extends JFrame{
	protected JFrame frame;
	protected JTextField textAngle;
	protected JTextField textSpeed;
	protected JTextField textTime;
	protected JButton calculation;
	protected JSlider redSlider;
	protected JSlider greenSlider; 
	protected JSlider blueSlider;
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
	
	
	
	public static int redValue;
	public static int greenValue;
	public static int blueValue;
	
	
	protected double currX, currY;
	protected double lastX, lastY;
	protected Timer timer;
	FireworksPanel firePanel;
	
	//constructor
	public Fireworks() {
		setTitle("Fireworks");
		setSize(800,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		timer = new Timer(5000, new TimerCallback());
	
		//JPanel 
		sliderPanel = new JPanel();
		//sliderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		listener = new ChangeListener(){
           @Override
			public void stateChanged(ChangeEvent event){
             
              JSlider source = (JSlider) event.getSource();
              if (source == redSlider) {
            	  redValue = redSlider.getValue();
            	  System.out.println("R");
           
              }else if (source == greenSlider) {
            	  greenValue = greenSlider.getValue();
            	  System.out.println("G");
            	  
              }else if (source == blueSlider) {
            	  blueValue = blueSlider.getValue();
            	  System.out.println("B");
              }
              
              firePanel.repaint();
           }
           
        };
        
        

        
        //creating and adding Color (Red, Green, Blue) sliders to panel
        redSlider = new JSlider();
        addSlider(redSlider, "Red");
        greenSlider = new JSlider();
        addSlider(greenSlider, "Green");
        blueSlider = new JSlider();
        addSlider(blueSlider, "Blue");
      
    
        add(sliderPanel); //adding color sliders panel to frame
        
        firePanel = new FireworksPanel();
        
        add(firePanel);
        
	}
	
	//slider method
	public void addSlider(JSlider slider, String name) {
		slider.addChangeListener(listener);
		JPanel panel1 = new JPanel();
		panel1.add(slider);
		panel1.add(new JLabel(name));
		sliderPanel.add(panel1);
		
	}
	
	
	protected class TimerCallback implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	}
	
	
	public static void main(String[] args) {
		//JFrame frame = new JFrame("fireworks");
		new Fireworks().setVisible(true);
		//Fireworks canvas = new Fireworks();
		//frame.add(canvas);
	}
}
