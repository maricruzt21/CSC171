/* Name: Maricruz Tolosa Amaya
 * Net ID: mtolosaa
 * ID#: 29988518
 * Project #2
 * Lab: MW 2:00-3:15
 * 
 * I did not collaborate with anyone on this assignment
 */

import java.util.Scanner;

public class Tester {
	public static void main(String[] args) {
		
	
		//Problem 1
		//example of Card class
		System.out.print("Printing a card: ");
		Card card = new Card(1, 2);
		System.out.println(card);
		
		
		System.out.println();
		
		//making a deck of cards
		Deck dk = new Deck();
		System.out.println("New Deck");
		System.out.println(dk.toString()); // printing the ordered deck
		
		
		System.out.println();
		
		
		//shuffling the deck and printing the new deck
		System.out.println("Shuffled Deck:");
		dk.shuffle();
		System.out.println(dk.toString()); //printing shuffled deck
		
		
		System.out.println();
		
		
		
		
		//Problem 2
		//deals 5 cards for 2 different people and prints the 42 remaining cards in deck
		System.out.println();
		
		System.out.println("Dealing");
		System.out.println("Player 1: " );
		Hand player1 = dk.deal(); //dealing 5 cards for one person
		player1.Sort();
		System.out.println("Hand for Player 1: "+  player1); //printing cards
		
		
		System.out.println("Player 2: ");
		Hand player2 = dk.deal(); //dealing 5 cards for another person
		
		player2.Sort();
		System.out.println("Hand for Player 2: " + player2); //printing cards
		
		System.out.println("Remaining Deck: " + dk.printRemaining());
		dk.printRemaining(); //printing remaining cards
		System.out.println();
		
	
		
		
		
		
		//Problem 3
		//checking hand
		System.out.println("Checking hands to see who wins");
		
		int result = player1.compare(player2); //getting 0, 1, or 2 to see who wins
		
		//printing statements depending on who wins or if it was a tie
		if (result == 0) {
			System.out.println("player 1 wins!");
		}else if(result == 1) {
			System.out.println("Player 2 wins!");
		}else {
			System.out.println("It's a tie!");
		}
		
		//System.out.println("Result: " + result);
		 


		
		//problem 4
		//using a scanner
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter 10 characters that mimics this: RSRSRSRSRS. R is for Rank and S is for Suit.");
		String hand1 = scan.nextLine(); //getting the 10 characters
		System.out.println("Please enter another 10 characters that mimics this: RSRSRSRSRS. ");
		String hand2 = scan.nextLine(); //getting another 10 characters
		
		Hand userHand1 = buildHandFromString(hand1); //calling the buildingHandFromString method
		userHand1.Sort(); //making sure cards are sorted
		Hand userHand2 = buildHandFromString(hand2);
		userHand2.Sort();
		
		
		System.out.println(userHand1); //printed the sorted hand
		System.out.println(userHand2); 
		int winner = userHand1.compare(userHand2); //comparing them
		
		//will print who wins or if it was a tie
		if (winner == 0) {
			System.out.println("Player/hand 1 wins!");
		}else if(winner == 1) {
			System.out.println("Player/hand 2 wins!");
		}else {
			System.out.println("It's a tie!");
		}
		
		
	
		
	}
	//problem 4
	//created a new method to build a hand from the scanner string
	public static Hand buildHandFromString(String handString) {
		String lower = handString.toLowerCase(); //changing everything to lower case
		String ranks = "23456789tjqka"; //rank from low to high
		String suits = "cdhs"; //suits
		
		//creating card array for hand
		Card[] cards = new Card[5];
		
		//going through the array
		for(int i =0; i < 5; i++) {
			
			int startIndex = i*2; 
			int endIndex = startIndex + 2; //going to everyother index
			String cardString = lower.substring(startIndex, endIndex); //getting only part of the string
			int rank = ranks.indexOf(cardString.charAt(0));
			int suit = suits.indexOf(cardString.charAt(1));
			cards[i] = new Card(suit, rank); //made it into a card
		}
		return new Hand(cards); //putting all the cards together to make a hand
		
	}
}
