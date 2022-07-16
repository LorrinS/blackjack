public class Player{
	private String name;
	private int age;
	private int money;
	private String hand;
	private String handID;
	private int handSum;
	private int bet;
	
	public Player(String name, int age, int money){ //player constructor
		this.name = name;
		this.age = age;
		this.money = money;
	}
	public Player(String name, int age){ //player constructor for dealer(without a money parameter)
		this.name=name;
		this.age=age;
	}
	public void setHand(String cards){ //set the id of the Player's cards ex: "19 20 "
		this.handID = cards;
	}
	public void addHand(String cards){ //add a cards id to the Player's cards ex: "19 20 "+"21 "
		this.handID+=cards;
	}
	public String getHand(){ //get the all the Player's card IDs
		return handID;
	}
	public int getMoney(){ //return how much money the Player has
		return money;
	}
	public void setBet(int x){ //set the value for how much money the Player has bet
		this.bet = x;
	}
	public void addBet(){ //apply the bet to the user's money at the end of the round (+ or -)
		money += bet;
	}
	public int getValue(){ //calculate the sum of the Player's cards
		String temp ="";
		handSum =0;
		int add = 0;
		for(int i = 0;i<handID.length();i++){ 
		
			//seperate the numbers(individual card IDs) from the spaces
			if(handID.charAt(i)!=' ')
				temp+=String.valueOf(handID.charAt(i));
			
			if(handID.charAt(i)==' '){ //once the number(card Id) has been seperated
				
				add=(Integer.parseInt(temp))%13; //find what card it is (A/1, 2, 3, ... 10, J, Q, K)
				
				//if add = 1, the card ID corresponded to an Ace, if the sum of the hand is less than 11(adding Ace as value of 11 goes over 21), 
				//ace's value goes from 1 to 11
				if(add==1 && handSum<11){ 
					add=11;
				}
				
				else if(add==11 || add==12 || add==0) // if the card is a J, Q, or K their values become 10
					add=10;
				
				temp="";
				//System.out.println("handsum: "+ handSum+"\t add: "+add);
				handSum+=add; //add the cards value to the sum of the Player's hand
			}
		}
		return handSum;
	}
	public String convertID(int cardID){ //convert the card ID to an actual card with the suits
		//take the card id and figure out what card it corresponds to
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
	public String convertHand(){  //convert a String of card ID's into actual cards with suits
		String out = "";
		String temp ="";
		//seperate the user's hand into individual card ID's then pass it through the convertID method
		for(int i = 0; i<handID.length();i++){
			if(handID.charAt(i)!=' ')
				temp+=String.valueOf(handID.charAt(i));
			if(handID.charAt(i)==' '){
				out+= convertID(Integer.parseInt(temp))+" ";
				temp ="";
			}
		}
		this.hand = out;
		return out;
	}
	public void reset(){ //after the round is over reset the hand of the user and the value of that hand
		hand="";
		handID="";
		handSum=0;
	}
	public String toString(){ //print out the Player's info
		return "Player: "+name+"\tAge: "+age+"\tMoney: "+money;
	}
}
