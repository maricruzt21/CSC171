import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class FireworksPanel extends JPanel {
	
	public FireworksPanel() {
		super(false);
	}

	public void paintComponent(Graphics2D g) {
		Color c = new Color(Fireworks.redValue, Fireworks.greenValue, Fireworks.blueValue);
		//g.setColor(c);
		//g.drawLine(200, 100, 500, 500);
		System.out.println(c);
		
	
	}
}
