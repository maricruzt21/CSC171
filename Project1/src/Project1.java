/*
	 * Name: Maricruz
	 * Project 01
	 * I did not collaborate with anyone on this assignment 
	 */

import java.util.Scanner;
import java.util.Random;

public class Project1 {
	public static void main(String[] args) {
		int score = 0; //initial score is 0 
		int guess = 0;
		
		//Created max's and min's for the Wall Height and the Distance from the wall
		double MinimumWallHeight  = 0;
		double MaximumWallHeight  = 500 ;
		double MinimumWallDistance = 0;	
		double MaximumWallDistance = 1000;
		//writing the prompt/instructions for the players
		System.out.println("In this game, you have a catapult that can launch projectiles. In every round, the height and distance of the wall will change. The height will range from 0 meters to "
			+ " 500 meters. The distance will be between 0 meters to 1000 meters. In this game, you have the ability to enter values for the projectile's launch angle and the speed at which it launches."
			+ "Based on those numbers, you will be told if you managed to get the projectile over the wall or not.");
	
	
		System.out.println("To play the game, please enter '0'.After every round there will be an option to end the game by entering any other number besides 0 when asked. Enjoy!");
		System.out.print("Enter 0 to play the game: ");
		Scanner scan = new Scanner(System.in ) ; //takes in what the user inputs
		int play = scan.nextInt();
		
		while (play == 0) { //if what the user enters is true or play, then the games begin
		
			//created random values for the wall's height and distance in between the ranges listed above
			double randomWallHeight = MinimumWallHeight + new Random().nextDouble() * (MaximumWallHeight - MinimumWallHeight); 
			double randomWallDistance = MinimumWallDistance + new Random().nextDouble() * (MaximumWallDistance - MinimumWallDistance);
		
			//ask users for their input 
			System.out.println("Please enter two different values. The first number will be the launch angle and the second number will be "
					+ "the launch speed.");
			System.out.print("Enter a number for the launch angle: ");
			double Angle = scan.nextDouble();
			System.out.print("Enter a number for the launch speed: ");
			double Speed = scan.nextDouble();
			System.out.println("Launch Angle: " + Angle + " Launch Speed: " + Speed); //reminds the users of what they entered
			
			//the equation of the projectile
			double denominator = 2*(Speed*Math.cos(Angle));
			double y = randomWallDistance * Math.tan(Angle) - ((9.8*randomWallDistance)/Math.pow(denominator, 2));
			
			
			//created if statements to see how far the projectile goes over the wall or to check how far it still needed to go over the wall
				if (randomWallHeight - y <= 3) { //
					
					System.out.println("Sorry, you are not quite over! You are less than 3 meters from the top of the wall");
					score = score - 1;
				}
				else if (y - randomWallHeight < 3) {
					
					System.out.println("You made it! You made it over the wall by less than 3 meters!!");
					score = score + 5; 
				
				} else if (y - randomWallHeight >= 3) {
					
					System.out.println("There's plenty of room! You made it over the wall by more than 3 meters!!"  );
					score = score + 7;
					
				} else {
					System.out.println("Sorry! You are not even close to getting over the wall. ");
					score = score - 3;
				}
				System.out.println("Score: " + score);
			
			//this is to ask after every round
			System.out.print("To play a new round, enter 0. To quit, enter another number besides 0. Enter number: ");
			play = scan.nextInt(); //if zero is entered, then it will bring the player to the beginning of the while loop. 
		
		
			

		}
		//printed once they don't want to play anymore 
		System.out.println("Game over!");
		System.out.println("Final Score: " + score);
	}	
}
