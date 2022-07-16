public class Deck{
	protected String deck[] = new String[52];
	protected int cardIDs[] = new int[52];
	private int numOfCards = 52;
	private int usedCards =0;
	public Deck(){
		//String a = "";
		
		for(int i=1; i<53; i++){ //load up an array with card IDs (1-52)
			cardIDs[i-1] = i;
		}
		
		/*for(int i=3; i<7; i++){
			for(int j = 1; j<14; j++){
				if (j<11){
					a = ""+j;
				} 
				if(j==1){
					a = "A";
				}
				if(j==11){
					a = "J";
				}
				if(j==12){
					a = "Q";
				}
				if(j==13){
					a = "K";
				}
				deck[j+13*(i-3)-1] = ""+a+(char)(i);
			}
		}*/
		for(int i = 0; i<52; i++){ //convert the card IDs into actual card's with suits and fill an array with those cards
			deck[i] = convertID(cardIDs[i]);
		}
	}

	public String toString(){ //print the deck
		String print = "";
		String used = "";
		
		for(int i = 0;i<52-usedCards;i++){ //(52-usedCards) = make sure the deck does not repeat the cards that have been used when printing
			print+= convertID(cardIDs[i])+" ";
		}
		return print;
	}
	
	public void shuffle(){ //shuffle the deck
		int random = 0;
		int temp=0;
		//go through each index of the card IDs array and swap it with a random index in the array
		for(int i=0; i<52;i++){
			random = (int)(Math.random()*52);
			temp = cardIDs[i];
			cardIDs[i]=cardIDs[random];						//from suit to card ID = (suit-3)*13+num
			cardIDs[random]=temp;
		}
		
	}
	public String unshuffled(){ //print the unshuffled version of the deck
		String out = "";
		String used = "";
		for(int i=0; i<usedCards; i++){ //figure out which cards have been used 
			used+= convertID(cardIDs[51-i])+" ";
		}
		for(int i=0; i<52; i++){
			if(used.contains(deck[i])) //makes sure that used cards do not get printed out
				out+="";
			else
				out+= deck[i]+" ";
		}
		return out;
	}
	
	public String convertID(int cardID){ //convert card ID into an actual card with suit's
		int num = cardID%13;
		int suit = cardID/13;
		String out = "";
		if (num<11)
			out = ""+num;
		if(num==1)
			out = "A";
		if(num==11)
			out = "J";
		if(num==12)
			out = "Q";
		if(num==0){
			out = "K";
			suit--;
		}
		return ""+out+(char)(suit+3);
	}
	
	public String getCard(int x){ //get x amount of cards from the deck 
		String out ="";
		for(int i=0; i<x; i++){
			out+=cardIDs[numOfCards-1-i]+" ";
		}
		numOfCards -= x;
		usedCards+=x;
		return out;
	}
	
	public int length(){ //return how many cards are left in the deck
		return numOfCards;
	}
	
	public void reset(){ //before making a new deck reset the number of cards left in the deck to 52
		this.numOfCards=52;
	}
	
}