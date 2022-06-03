/* Name: Maricruz Tolosa Amaya
 * Net ID: mtolosaa
 * ID#: 29988518
 * Project #2
 * Lab: MW 2:00-3:15
 * 
 * I did not collaborate with anyone on this assignment
 */


This is my submission for Project 2. There are several files: Card.java, Deck.java, Hand.java, and Tester.java. 

General description of classes: 
	- Card: has method that models a card (ex: 2 of Spades) 
	- Deck: makes 52 Cards , can shuffle those cards and deal 5 cards to 
		two players and print the remaining deck
	- Hand: compares the Hand of the two players, gets classification and determines who wins. 
	- Tester: showing and printing a Card, a Deck, and Hand and converting inputs of user to 		determines who wins

PROBLEM 1: 
	For this problem, it asks to make a card, then make a deck of 52 cards. After that, the program 	should 	print the deck and then print a shuffled deck. For this problem, I created a Card class 	(Card.java) that has a toString method which allows the cards to print as "Rank of Suit" (ex: 		Ace of Clubs). The Deck class creates a deck. A deck has 52 cards. The deck is 	not shuffled 		initially, so it will  go through a suit(Club, Diamond, Heart, Spades) first then go through the  	ranks. It makes sure that every suit has every single rank. There is a shuffle method, that 		shuffles all 52 cards. It will randomly swap cards using a random integer. There is a draw 		method that simulates a person taking the top card of the deck. If a card gets drawn, then it 		goes to the next card in the deck and that will be the top card for now. There is a deal method. 	The deal method will deal a hand. Each hand will have 5 cards. It creates a new hand for every 5  	cards.In the tester class, if it is run, then it will print an example of a card. Then it will   	print a deck called 'dk', this will be the deck in order, sorted by suit and rank. After it will 	shuffle 'dk', the deck, and will print it. Then it will deal a hand for 2 players and print 		them. 

PROBLEM 2: 
	Similar to what I said in Problem 1, in the Deck class there is a deal method. The deal method 		will deal 5 cards to each player. When the hand is called, it sorts the cards according to their 	suit and rank. In the tester class, it deals two hands to two players. It will also print each 		player's hand when the tester is run. Also, there is printRemaining method, that prints all the 	cards from the top of the deck all the way to 52 to get the remaining cards in the deck. This is 	called in the tester and is printed after the hands for the two players are made.

PROBLEM 3: 
	Problem 3's code is in the Hand class. The project prompt put the classifications in order which 
	are: Straight Flush, Four of a Kind, Full House, Flush, Straight, Three of a Kind, Two Pair, One 	Pair, High Card. First, the cards need to be sorted. When sorting a card, it is comparing a card 	with the card after it. It will first check the suit with each other. If the suit higher then 		the card, then it will go to the top of the array. Higher suits are first in the array and lower 	suits are later in the array. After sorting the suits, for the ones that have the same suit, 		it will now sort the ranks. Higher ranks are placed for those card that have the same suit in 		that hand. Each hand will be  sorted when a hand is made. After the hand is sorted, it will 		begin to look for the classifications.The highest classification is Straight Flush and 	the 		lowest is High Card. The methods for classification begin with Straight Flush and goes to High 		Card. Each hand will go through each method until it finds one that the method returns 	true. 		When the method returns true that means that that hand is in that classification. By sorting the 	cards before, it makes the classification easier to do since everything is in order from highest 	to lowest. After finding the classification for the hand, there is a getClassification method 		where it just assigns the Classification to a number. Straight flush is the highest so it is 9 		and High Card is the lowest so it is 1. Also for each one, there will be a print statement so 		that it will show what classification each hand has. There is a compare method after. This will 	compare two hands to each other. I get the classification number of each hand. If one hand(ex: 		thisHand) has a higher classification number than the other hand(otherHand), then thisHand will 	win. If otherHand has a higher classification number than thisHand then otherHand will win. If 		both have the same classification number, then more things need to be done. Sorted 			Classifications that are similar to each other(ex: straight flush is similar to straight). Then 	it begins to compare ranks to each other. After it compares the ranks, it will then determine 		who wins the game. In the Tester class, this is shown with int result. Depending on what result 	is, it will print a statement that says who is the winner. It will also print the classification 	and its number of classification followed by its hand. Then it will print which player wins or 		if it was a tie.

PROBLEM 4: 
	This is done in the Tester class. Towards the end of the class, there is another method called 		buildHandFromString. This essentially changes the letters that user enters to all lowercase so 		it would be easier to compare. Depending on what the user inputs, it compares to the string 		array in the Card class. Then it converts those characters into its corresponding suit and rank, 	making a hand of 5 cards. In the main method, there is a scanner that gets the two 10-character 	string from user. Each hand then calls the buildHandFromString method to convert those 			characters into cards and then a hand.  To make sure the cards are sorted, the sort method from 	the Hand class is called. After the cards are sorted, both hands are printed. Then it will 		compare Hand 1 to Hand 2, find each of their classifications. After, it will determine and print 	the winner of the game. 



To run: 
	To run this program, simply run the Tester class (Tester.java). Once it runs, it will 			automatically print an example of a card, of what it should looking like: Rank of Suit (ex: 4 of 	Diamonds will print). Then it will print a new ordered deck, there are 52 cards in total. There 	will be 13 ranks for all 4 suits, in order. Using that deck, the shuffle method is called so the 	deck is not in order anymore. The newly shuffled deck will be printed. Then it will 			automatically deal 2 hands, each with 5 cards. One hand for player 1 and the other for player 2. 	After each hand is dealt, each hand will then be sorted, by suit and rank. Then both sorted 		hands print each of their classification type and number, along with its sorted hand. It will 		print the remaining deck, the remaining 42 cards. Then the program compares player 1's hand/		classification to player 2's hand/classification.  It will print which player won. 
	After the user is prompted to enter 2 10-character string. Those 10-character strings are then 		converted into cards which then created a hand. Those 2 hands are then sorted and compared to 		each other, like before, according to its classification. Then it will prints each of their 		classification, the sorted hand, and the winner.



Thank you!












