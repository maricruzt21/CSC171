
public class Card {
	
	protected  int suit; 
	protected  int rank;
	
	//constructor
	public Card(int suit, int rank) {
		this.suit = suit; 
		this.rank = rank;
	}
	
	
	//modeling a Card
	public String toString() {
		
		//4 suits
		String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
		
		//13 ranks
		String[] rank = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };

		//System.out.println("Suit: " + this.suit);
		//System.out.println("Rank: " + this.rank);
		String cards = rank[this.rank] + " of " + suits[this.suit];
		return cards;
	}
}
