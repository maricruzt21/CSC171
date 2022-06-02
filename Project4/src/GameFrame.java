/*
	 * Name: Maricruz
	 * Project 04
	 * I did not collaborate with anyone on this assignment 
	 */

import javax.swing.JFrame;


public class GameFrame extends JFrame {
	//creating frame of the game and the size 
		public GameFrame() {
			Game panel = new Game();
			add(panel);
			setTitle("Drone Pilot"); //name of game
			setSize(500, 500);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		public static void main(String[] args) {
			new GameFrame().setVisible(true);
		}
	}

