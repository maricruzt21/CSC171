
/* Name: Maricruz 
 * ID#: 29988518
 * Project #1
 * 
 * I did not collaborate with anyone on this assignment
 */


import java.util.Scanner;
import java.time.Duration;
import java.time.Instant;
//import java.time.LocalTime;
import java.util.Random;

//import java.util.Math;

public class Project_1 {

    public static void main(String[] args) {


        int score = 0;
        int previousScore = 0;
        int launch = 0;
        int round = 0;
        boolean win = false;
        
        //created string arrays depending on the outcome
        String[] SuccessStrings = {"You did it!", "Yay! Congrats!", "Success", "Good job!", "Awesome" };
        String[] FailStrings = {"Good luck next time!", "Sorry!", "Try again", "Aww, better luck next time"};
        Random random = new Random();

        Scanner scanner = new Scanner(System.in); //used scanner

        //explaining the game, rules for the game
        System.out.println("Welcome to the Projectile Game! ");
        System.out.println("In this game, there is a catapult that can launch projectiles.");
        System.out.println("There will be a wall randomly placed that its distance will be anywhere from 0m to 1000m away.");
        System.out.println("The wall's height will randomly be from 0m to 500m.");
        System.out.println("In every round, the wall's height and distance will change. ");
        System.out.println("You will have the ability to enter the projectile's launch speed and launch angle in order to get the projectile over the wall.");
        System.out.println("After the launch, you will be informed where the projectile lands.");
        System.out.println("Good luck!");

        //created a range for the wall height and the distance to the wall
        //Wall height range: 0 - 500 meters
        double MaxWallHeight = 500.0;
        

        //Wall distance range: 0 - 1000 m
        double MaxWallDistance = 1000.0;
       
        //created a wall speed of 5m/s
        double wallSpeed = 5.0;
        

        System.out.println();


        //prompt the user to start the game.
        System.out.println("To begin the game, please enter 'Start' ");
        String starting = scanner.next();
        boolean start = true;


        while(start) { //player is playing game
            if (starting.equalsIgnoreCase("start")) { //player entered continue

                //round 1 of the game
                round = 1;

                //System.out.println();
                //System.out.println("New Round");
                //System.out.println();

                //reminding player of the wall height and distance
                System.out.println("Reminder: The wall height can be from 0m to 500m tall. The wall's distance can be 0m to 1000m.");
                System.out.println("The wall is moving at " + wallSpeed + " m/s. The longer you take, the closer the wall gets towards you!");
                System.out.println("Be careful and be quick!");
                
                //generates a random wall height and random wall distance
                double RandomWallHeight = MaxWallHeight * Math.random();
                double RandomWallDistance =  MaxWallDistance * Math.random();

                //get current time in milliseconds
                Instant startTime = Instant.now();
                
                //System.out.println("height " + RandomWallHeight);
                //System.out.println("distance " + RandomWallDistance);

                System.out.println();
                score = previousScore;

                while (win == false) { //while the player has not won
                	System.out.println();
                    System.out.println("New Round");
                    System.out.println();

               
                    //getting the launch angle + speed from user
                    System.out.print("Enter a launch angle: ");
                    double angle = scanner.nextDouble();
                    System.out.print("Enter a launch speed: ");
                    double speed = scanner.nextDouble();
                   
                    //prints the launch angle + speed that the user entered
                    System.out.println("Launch angle is: " + angle);
                    System.out.println("Launch speed is: " + speed);
                    launch++; //launch increases by 1

                    System.out.println();
                    
                    
                    Instant curTime = Instant.now(); //gets the current time
                    double elapsed = Duration.between(startTime, curTime).toMillis() / 1000.0; //turns milliseconds into seconds
                    //System.out.println(elapsed);  //how much time pased

                   //distance = speed * time
                    RandomWallDistance -= wallSpeed * elapsed; //wall distances gets closer as time goes by
                    
                    if(RandomWallDistance <= 0) { //checks to see if the person got hit by wall
                    	System.out.println("Oops! You got hit by the wall!");
                    	System.out.println("Resetting the wall position now.");
                    	
                    	startTime = Instant.now(); //starting time over again
                    	RandomWallDistance =  MaxWallDistance * Math.random(); //getting a new wall distance
                    	continue;  //continuing the game
                    }

                    //equation for a projectile
                    double g = 9.8; //gravity
                    double denominator = 2 * Math.pow(speed * Math.cos(angle), 2); //denominator of the fraction
                    double y = (RandomWallDistance * Math.tan(angle)) - ( (g * Math.pow(RandomWallDistance, 2)) / denominator ); //calculating for y
                    //System.out.println("y = " + y);


                    //System.out.println("New Round");
                    System.out.println();
                    //previousScore = score;
                    //checking to see where the projectile lands
                    if ( y > RandomWallHeight) { //makes sure that the projectile goes over the wall height and lands further from the wall


                        if (y - RandomWallHeight <=3) { //made it over the wall by 3m or less
                        	
                        	System.out.println(SuccessStrings[random.nextInt(SuccessStrings.length)]); //randomly prints out a statement from array
                            System.out.println("Projectile made it by 3m or less"); //prints result
                            score =  score + 3 - launch; //score has a net of +3
                            System.out.println("Score: " + score);  //prints score

                        }else  { //made it over the wall by over 3 m
                        	System.out.println(SuccessStrings[random.nextInt(SuccessStrings.length)]);
                        	System.out.println("Projectile made it by over 3 meters!"); //prints result

                            score =  score +  5 - launch; //score has a net of +5
                            System.out.println("Score: " + score); //prints score
                        
                        }
                    }else { //projectile does not make it over the wall

                        //System.out.println("else loop");
                        if (y - RandomWallHeight >= -3){ //didnt make it by 3m
                        	System.out.println(FailStrings[random.nextInt(FailStrings.length)]);
                        	System.out.println("Not quite over. Projectile did not make it over by 3m or less");


                            score =  score - 1 - launch; //net score of -1
                            System.out.println("Score: " + score);
                        }else { //did not make it over 3m
                        	System.out.println(FailStrings[random.nextInt(FailStrings.length)]);
                        	System.out.println("Not even close. The wall was more then 3m away from the projectile");

                            score = score - 2 - launch; //net score of -2

                            System.out.println("Score: " + score);
                        }

                    }


                    System.out.println("Do you want to continue playing? Enter yes or no"); //asks the user if they want to continue playing
                    String cont = scanner.next();

                    if (cont.equalsIgnoreCase("yes")) { //checks to see if they entered yes
                        start  = true;
                        round = 2; //goes onto round 2 if it was a yes, that they want to continue playing

                    }else { //if they enter no, or something else
                        System.out.println("Thank you for playing!");
                        System.out.println("Your final score: " + score); //prints out overall result
                        
                        //stops program
                        start = false;
                        win = true;
                        break;

                    }

                }
            }else { //player did not enter continue, so they did not want to play
                System.out.println("Game did not start");
                start = false; //never starts game
            }


        }
        scanner.close();
    }

}
