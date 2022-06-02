/*
	 * Name: Maricruz Tolosa Amaya  ID: 29988518
	 * Lab: TR 11:05 - 12:20
	 * Project 02
	 * I did not collaborate with anyone on this assignment 
	 */

import java.util.Scanner;
import java.util.Random;



public class Project2 {
	public static Random random;
	
	public static void main(String [] args) {
		Scanner scan = new Scanner(System.in);
		random = new Random();
		
		//intro to the game
		System.out.println("Welcome to CS Golf Course! Which golf course do you play golf at?" );
		System.out.println("Enter 1 if you want to play golf at Genesee Valley Park North Course. Enter"
				+ " 2 to play at The Old Course at St. Andrews.");
		
		boolean play = true; //status of game
		
		//created arrays to store data
		String [] puttPower = new String [] {"10,40,5", "9,30,5", "8,25,4", "8,25,4","7,20,4","6,16,3","5,12,3","4,8,2","3,4,2","2,2,1", "1,1,1"};
		
		String [] golfClubs = new String [] {"1,Driver,230,30", "2,3-wood,215,20", "3,3-iron,180,20", "4,4-iron,170,17", "5,5-iron,155,15", "6,6-iron,145,15", 
				 "7,7-iron,135,15", "8,8-iron,125,15", "9,9-iron,110,10,", "10,Wedge,50,10"};
		
		String [] geneseeCourse = new String [] {"1,530,5", "2,305,4", "3,331,4", "4,201,3", "5,500,5", "6,226,3", "7,409,4", "8,410,4", 
				"9,229,3", "10,433,4", "11,363,4", "12,174,3", "13,545,5", "14,419,4", "15,512,5", "16,410,4", "17,320,4", "18,170,3"};
		
		String [] andrewsCourse = new String [] {"1,376,4", "2,453,4", "3,397,4", "4,480,4", "5,568,5", 
				"6,412,4", "7,371,4", "8,175,3", "9,352,4", "10,386,4", "11,174,3", 
				"12,348,4", "13,465,4", "14,618,5", "15,455,4", "16,423,4", 
				"17,495,4", "18,457,4"};
		
		String [] golfCourses = new String [] {"Genesee Valley Park North Course", "The Old Course at St. Andrews"};
		
		
		
		//while the player wants to play the game
		while(play) {
			int score = 0; //initialized player's score and hit 
			int hit = 0;
			int location = scan.nextInt(); //user inputs which course to play at
			//System.out.println("Please select which golf course you want to play at. Options are: " + golfCourses);
			if (location == 1 || location == 2){ //only runs when player inputs either of the two numbers 
				System.out.println("Your golf course selection: " +  golfCourses[location - 1]);
				System.out.println();
				
				
				String [] Courses = new String[1];
				
				if (location == 1) { //course is equal to which ever course they want to play at and gets that course data
					Courses = geneseeCourse;
				}else if(location == 2) {
					Courses = andrewsCourse;
				}
				System.out.println();
				System.out.println("Options for golf clubs are(Golf Club Number, Name, Mean in yards, Std Dev in yards) : " );
				for (int i = 0; i< golfClubs.length; i++) { //prints out all of the golf club data
					System.out.println(golfClubs[i]);
				}	
				System.out.println();
				for(int i = 0; i < Courses.length; i++) {
					String [] ParsData = Courses[i].split(","); //gets the integer from the string array 
					//System.out.println(ParsData[0] + " ," + ParsData[1] + "," + ParsData[2]);
					int Yards = Integer.parseInt(ParsData[1]);
					Yards = Math.abs(Yards); //not negative number for yards
					
					int Par =  Integer.parseInt(ParsData[2]); //prints out the interger value from the array 
					int HoleNumber = Integer.parseInt(ParsData[0]);
					
					System.out.println();
					System.out.println("You are at tee " + HoleNumber); //informs player where they are 
					System.out.println("The hole is " + Math.abs(Yards) + " away. The par for this hole is " + Par ); //informs how much they have left
					System.out.println();
					
					while(Yards != 0 ) { //while the ball is still not in the hole 
						Yards = Math.abs(Yards); //makes sure holes isnt a negative number
						if (Yards > 20 ) {
							System.out.print("Enter golf club number[1 - 10]: " ); //inputs golf club number
							int clubSelection = scan.nextInt();
							System.out.println("Golf club selection: " + golfClubs[clubSelection -1]);
							System.out.println();
							
							System.out.print("Enter power [1-10]: "); //power of the club
							int Power = scan.nextInt();
							System.out.println("Power [1-10]: " + Power);
							System.out.println();
							
							String [] ClubData = golfClubs[clubSelection - 1].split(","); //makes an interger from the array of the clubs information 
							int StdDev = Integer.parseInt(ClubData[3]);
							int Mean = Integer.parseInt(ClubData[2]);
						
							int HitDistance = nextDistance(Mean,StdDev,Power); //uses the code given in the project info 
							System.out.println("You hit the ball " + HitDistance + " yards away");
							
							Yards = Yards - HitDistance;
							System.out.println("You are " + Math.abs(Yards) + " yards away from the hole");
							System.out.println();
							hit++; //this increases everytime they olayer hits the ball
							//System.out.println(hit);
							
						}else { //if yards is less then  or equal to 20 
							System.out.println();
							System.out.print("You are 20 yards or less away from the hole.On the green! Enter the putt power[1 - 10]: "); //player now uses the putt
							int Power = scan.nextInt();
							System.out.println("Power of the putt: " + Power);
							System.out.println();
							
							String [] PuttData = puttPower[10 - Power].split(","); //converts string from integer
							int puttMean = Integer.parseInt(PuttData[1])/3;
							int puttStdDev = Integer.parseInt(PuttData[2])/3; //converts it from ft to yards
							
							int PuttDistance = nextDistance(puttMean, puttStdDev, Power);
							
							System.out.println("You hit the ball " + PuttDistance + " yards away");
							
							Yards = Yards - PuttDistance;
							System.out.println("You are " + Math.abs(Yards) + " yards away from the hole");
							System.out.println();
							hit++;
							//System.out.println(hit);
							
							}
							
						}
					
					
					if (Par == hit) { //scoring
						score = score + 0;
					}else if (Par > hit) {
						int difference = Par - hit;
						score = score + difference; 
					}else {
						int diff = hit - Par;
						score = score - diff;
						
					}
					System.out.println("Score is: " + score);
				}
				System.out.println("Game over!");
				System.out.println("Final Score: " + score);
				System.out.println("Do you want to continue? Enter 1 to play another game or 2 to quit: ");
				int continuation = scan.nextInt(); //lets the player play again 
				if (continuation == 1) {
					play = true;
					System.out.println("Enter 1 if you want to play golf at Genesee Valley Park North Course. Enter"
							+ " 2 to play at The Old Course at St. Andrews.");
					continue;
				}else {
					play = false;
					break;
				}
			}else { //doesn't take any other number besides 1 and 2
				System.out.println();
				System.out.println("Please choose one of the two options listed above.");
				continue; //keeps on repeating until the user chooses one of the two options
			
			}
			
		
		}
		scan.close();
	}
	
	public static int nextDistance(int mean, int stddev, int power) {
		double mean_adj = mean * power / 10.0;
		double stddev_adj = stddev * power / 10.0;
		double val = Math.abs(random.nextGaussian() * stddev_adj + mean_adj);
		return (int)val;
	}
	
}
