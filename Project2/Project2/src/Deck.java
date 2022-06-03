
public class Deck {
	
	//array of cards
	Card[] cards;
	
	//top card
	int topCard;
	
	//52 cards in a deck
	private int totalCards = 52;
	
	//constructor
	public Deck() {
		this.cards = new Card[52]; //makes 52 cards
		topCard = 0; //going to keep track of the card on top
		int number = 0; 
		for (int suit = 0; suit <= 3; suit++) { //4 types of suits
            for (int rank = 0; rank <= 12; rank++) { //13 types of ranks
                cards[number] = new Card(suit, rank); 
     
                number++; 
            }
            
        }
	}
	
	//Deck 
	public String toString() {
		String d = " ";
		for (int i = 0; i < totalCards; i++) { //printing all 52 cards
			d += this.cards[i] + "\n"; //prints each card on a new line
		}
		return d;
	}
	
	//shuffling the deck
	public void shuffle() {
		for (int i = 0; i < totalCards; i++) { //all 52 cards
			int r = (int)(Math.random() * totalCards); //randomly starting 
			
			//swapping cards
			Card temp = this.cards[r];
			this.cards[r] = this.cards[i];
			this.cards[i] = temp;
					
		}
	}
	
	//simiulating drawing a card (getting the card that is on top of the deck)
	public Card draw() {
		//getting the card thats on top
		Card top = cards[topCard];
		topCard++; //will "place" the next on top
		return top;
	}
	
	//dealing  hands
	public Hand deal() {
		//each hand has 5 cards
		Card[] hand = new Card[5];
		
		//drawing 5 cards
		for (int i = 0; i < hand.length; i++) {
			hand[i] = draw();
		}
		//makes a hand from the cards that were drawn(taken from the top)
		Hand hd = new Hand(hand);
		System.out.println(hd.getClassification());
		return hd;
	}
	
	//printing the remaining cards in the deck
	public String printRemaining() {
		String remaining = " ";
		for (int i = topCard; i < 52; i++) { //starting from the top card until the 52 card in deck
			remaining += this.cards[i] + "\n"; //adding it to string
		}
		return remaining; 
	}
}
