
public class Hand {
	
	//array of cards
	private Card[] h;
	
	//index
	int threeof;
	int fourof;
	int pairA;
	int pairB;
	
	
	
	//constructor
	public Hand(Card[] h) {
		this.h = h;
		this.Sort(); //sorting cards
	}
	
	//sorting Cards 
	public void Sort() {
		for (int i = 0; i < h.length; i++) { //going through the array of cards
			
			 //assuming smallest card
			int minIndex = i;
			
			//checking if it's smaller
			
			//checking the minCard against other cards
			for (int j = i + 1; j < h.length; j++) {
				Card temp = h[j]; //other card test
				Card minCard = h[i]; 
				
				//if test suit is less than minCard then test suit is the smallest
				if (temp.suit < minCard.suit) {
					minIndex = j; //changing the smallest value
					
				//both cards are the same suit
				}else if (temp.suit == minCard.suit) {
					
					//sorting card according to rank
					if(temp.rank < minCard.rank) {
						minIndex = j;
					}
				}
			}
			
			if (minIndex == i) {
				continue;
			}
			
			//swapping cards 
			Card minCard = h[minIndex];
			h[minIndex] = h[i];
			h[i] = minCard;
		}
	}
	
	
	
	//straight flush 
	//cards in a sequence, and same suit (shape)
	public boolean isStraightFlush( ) {
		
		boolean straightFlush = isStraight() && isFlush();

		return straightFlush; //calling other two methods and both have to be true for a Straight flush
	
	}
	
	//four of a kind
	//four cards of the same rank
	public boolean isAFourOfAKind() {
		
		//going through the cards
		for (int i = 0; i < h.length; i++ ) {
			int count = 0; //will keep track of cards that are the same
			
			for (int j = i + 1; j < h.length; j++) {
				if (h[i].rank == h[j].rank) { //checking if ranks is the same to another card 
					count++;
				}
			}
			if (count == 4) {  //4 cards are the same
				fourof = i; //storing it to use it later
				return true;
			}
		}
		
		
		return false;
	}
	
	//full house
	//three of a kind with a pair
	public boolean isFullHouse() {
		
		
		return isThreeofAKind() && isOnePair(); //calling other two methods 
	}
	
	//flush
	//all have same suit (shape) 
	public boolean isFlush() {
		
		return (h[0].suit == h[4].suit); //checking to see if the highest card is equal to the lowest one
	}
	
	
	//straight
	//cards in a sequence but not the same suit
	public boolean isStraight() {
		
		for(int i = 1; i < h.length; i++) { //going through the cards
			
			//not a Straight
			if (h[i].rank - h[i -1].rank != 1 ) { //comparing the card to card before it
				return false; 
			}
		}
		return true; //otherwise if the diference is 1, then its in sequence
	}
	
	
	//three of a kind
	//three cards of the same rank (letter or number)
	public boolean isThreeofAKind() {
		
		
		for (int i = 0; i < h.length; i++ ) { //going through the card array
			int count = 0;
			
			for (int j = i + 1; j < h.length; j++) { //comparing it to a card after it
				if (h[i].rank == h[j].rank) { //checking if ranks is the same to another card 
					count++; //if they are the same rank, then count goes up
				}
			}
			if (count == 3) { //three cards are the same
				threeof = i;
				return true; //it is a three of a kind
			}
		}
		
		
		return false; //if not, then it returns false
	}
	
	//two pair
	public boolean isTwoPair() {
		int pairs = 0; //keeping track of pairs
		
		for (int i = 0; i < h.length; i++ ) { //going through the cards
			for (int j = i + 1; j < h.length; j++) { //going through the cards after it
				if (h[i].rank == h[j].rank) { //checking if ranks is the same to another card 
					pairs++; //if they are equal, then pairs goes up
					if (pairs == 1) {
						pairA = i; //saves pairA at the index i
					}else if(pairs == 2) {
						pairB = i; //saves pairB at the other index
					}
					break; //ends loops after 2 pairs
				}
			}
		}
		if (pairs != 2) { //returns false if there arent 2 pairs
			return false;
		}
		
		return true; 
	}
	
	//one pair
	//two cards with same rank
	public boolean isOnePair() {
		
		for (int i = 0; i < h.length; i++ ) {
			for (int j = i + 1; j < h.length; j++) {
				if (h[i].rank == h[j].rank) { //checking if ranks is the same to another card 
					pairA = i; //saves pairA at the index in which they are the same
					return true; //there is one pair
				}
			}
		}
	
		//System.out.println(h + "One Pair");
		return false; //otherwise, false
	}
	
	//high card
	//take the highest level
	public boolean isHighCard() {
		return true; //last resort, will return true
	}
	
	public int getClassification() { //checking see which one it is for the hand
		if( isStraightFlush() ) {
			System.out.println("A Straight Flush");
			return 9; //highest classification
		}else if(isAFourOfAKind()) { //four of the same rank
			System.out.println("Four of a Kind");
			return 8;
		}else if ( isFullHouse() ) { //3 of a kind + pair
			System.out.println("Full House");
			return 7;
		}else if(isFlush()) { //same suit
			System.out.println("A Flush");
			return 6;
		}else if ( isStraight() ) { //sequence
			System.out.println("A Straight");
			return 5; 
		}else if(isThreeofAKind()) { //3 same rank
			System.out.println("Three of a Kind");
			return 4;
		}else if(isTwoPair()) { //two pairs of same rank
			System.out.println("Two Pair");
			return 3;
		}else if(isOnePair()) { //two cards of same rank
			System.out.println("One Pair");
			return 2;
		}else  { //highest card
			System.out.println("Highest Card");
			return 1; //lowest classification
		}
	} 
	
	
	//using an int so that we know who wins 
	//0 = win, 1 = lose, 2 = draw
	public int compare(Hand other) { //going to compare hands to each other
		//this.Sort();
		//other.Sort();
		
		//getting both of their hands
		int otherClass = other.getClassification();
		int thisClass = this.getClassification();
		int[] result = new int[2]; 
	
		
		if (thisClass > otherClass) {
			//that hand is the  winner
			result[0] = 0; 
			result[1] = thisClass;
			return 0; //winner
		}else if (thisClass < otherClass) { //other hand is the winner
			//other winner
			return 1; //this hand loses
		}else { //both hands have the same classification
			result[1] = thisClass; 
			
			//going to compare ranks 
			
			//both hands have either a Straight Flush or Stright hand
			if(thisClass == 9 || thisClass == 5) { //Stright Flush + Straight
				//comparing ranks to each other
				if(this.h[0].rank > other.h[0].rank ) {
					return 0; //this hand has a higher rank than the other hand so this hand wins
				}else if (this.h[0].rank == other.h[0].rank) { //this hand rank is equal to other hand rank
					return 2; //a tie/draw
				}else { //this hand rank is lower than the other hand rank
					return 1; //this hand loses 
				}
			//both hands are Four of a Kind
			}else if (thisClass == 8) { //Four of 
				//comparing their ranks
				if(this.h[this.fourof].rank > other.h[other.fourof].rank ) { //getting the rank from its method
					return 0; //this hand won 
				}else if(this.h[this.fourof].rank == other.h[other.fourof].rank ) { //ranks equal to each other
					return 2; //tie
				}else {//this hand rank is lower than the other hand rank
					return 1; //lose
				}
				
			//cboth are either a Full House or a Three of a Kind
			}else if(thisClass == 7 || thisClass ==4) { //Full House and Three of
				//getting the rank of the hands and comparing it to each other
				if(this.h[this.threeof].rank > other.h[other.threeof].rank ) { //this hand at the index threeof rank is higher than the other hand rank
					return 0; //this hand wins
				}else if(this.h[threeof].rank == other.h[other.threeof].rank ) { //equal to each other
					return 2; //draw
				}else { //this hand rank is smaller than other hand
					return 1; //this hand  loses
				}
				
			//hands are either both a Flush or both are Highest Card	
			}else if (thisClass == 6 || thisClass == 1) { //Flush and Highest Card
				if(this.h[4].rank > other.h[4].rank ) {  //comparing the last card and its rank
					return 0; //win
				}else if(this.h[4].rank == other.h[4].rank ) {
					return 2; //draw or tie
				}else {
					return 1; //lose
				}
			}else if (thisClass == 3) { //Two Pair
				int thisMaxRank = Math.max(this.h[pairA].rank, this.h[pairB].rank); //getting the max rank between the two for this hand 
				int otherMaxRank = Math.max(other.h[other.pairA].rank, other.h[other.pairB].rank); //getting the max rank for the other hand 
				
				if(thisMaxRank > otherMaxRank ) { //this hand rank is higher than other hand rank
					return 0; //this hand wins 
				}else if(thisMaxRank == otherMaxRank ) { //equal to each other
					return 2; //a tie
				}else { //this hand rank is lower than other hand rank
					return 1;
				}
				
				//Classification is One PAir
			}else if (thisClass == 2) { //One pair
				if(this.h[this.pairA].rank > other.h[other.pairA].rank ) { //checks the ranks
					return 0;//this hand wins
				}else if(this.h[pairA].rank ==  other.h[other.pairA].rank ) { //equal to each other
					return 2; //a tie
				}else { //this hand rank is lower than the other
					return 1; //this hand loses
				}
			}
		}
		return 1;
		
	}
	
	 //printing the hand
	public String toString() {
		String h2 = " ";
		for (int i = 0; i < 5; i++) {
			h2 += h[i] + "\n"; 
		}
		return h2;
	}
}
