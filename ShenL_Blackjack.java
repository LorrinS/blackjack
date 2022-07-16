/*
Shen Lorrin
May 19 2020
Assignment: Blackjack
ICS3U7-01 Ms.Strelkovska
*/
import java.util.*;
import java.io.*;
public class ShenL_Blackjack{
	public static void main(String[] args) throws FileNotFoundException{
	Scanner sc = new Scanner(System.in);

	String cardsUserID ="";
	String cardsDealerID ="";
	String hand = "";
	String dHand ="";
	int userVal = 0;
	int dealerVal =0;
	
	String move = "";
	String round = "";
	
	int roundNum=1;
	int bet = 0;
	
	boolean newGame= false;
	boolean userStand=false;
	boolean dealerStand=false;
	boolean newRound=true;
	boolean bust = false;
	boolean userW = false;
	boolean tie= false;
	
	//take in input for players information
	System.out.print("name: ");
	String name = sc.nextLine();
	System.out.print("age: ");
	int age = sc.nextInt();
	System.out.print("money: ");
	int money = sc.nextInt();
	sc.nextLine();
	
	Player user = new Player(name, age, money);
	Dealer dealer = new Dealer();
	Deck d = new Deck();
	
	d.shuffle();
	
	while(newRound == true){ //while user wants a new round, automatically set to true for very first round
	
		line(50);
		
		System.out.println(d.unshuffled() +"\n\n"); //print unshuffled deck
		
		if(d.length()<10){ //make "new" deck if deck has less than 10 cards left and shuffle 
				d.reset();
				d.shuffle();
			}
			
		System.out.println(d); //print shuffled deck

		//get user's bet
		money = user.getMoney();
		System.out.print("\n"+name+"'s wager [$"+money+"]: "); 
		bet = sc.nextInt();
		sc.nextLine();
		user.setBet(bet);
		
		//get users cards
		cardsUserID = d.getCard(2);
		user.setHand(cardsUserID);
		hand = user.convertHand();
		userVal = user.getValue();
		
		//get dealers cards
		cardsDealerID =d.getCard(2);
		dealer.setHand(cardsDealerID);
		dHand=dealer.convertHand();
		dealerVal = dealer.getValue();
		dealer.setVal(dealerVal);
		
		while((userStand==false || dealerStand==false)&& bust==false){ //while either dealer or user has not stood yet, or the user has not busted yet 
			
			line(50);
			
			//show 1 of dealers cards and the value of that card and show the user their cards and their value
			System.out.println(dealer.getName()+"'s hand: "+dealer.showHand()+"\t[sum: >"+dealer.getCoveredVal()+"]");
			//System.out.println("full dealer hand: "+dHand);
			System.out.println(name+"'s hand: "+hand+"\t[sum: "+userVal+"]");
			
			if(userStand==false || userVal>=21){ //if the user has not busted or stood yet ask if they want to hit or stand
				System.out.print("[hit / stand] : ");
				move = sc.nextLine();
			
				if(move.equals("hit")){ //if the user hits add a card to their hand and update variables
					cardsUserID+=d.getCard(1);
					user.setHand(cardsUserID);
					hand = user.convertHand();
					userVal = user.getValue();
				}
				
				if(move.equals("stand")){ //if they stand record that they did
					userStand=true;
				}
				
				//tell the user if they got a blackjack or a 21
				if(userVal==21) 
					System.out.println(name+" has BlackJack!");
				if(userVal>21){
					System.out.println(name+" busted!");
					bust=true;
				}
				
			}
		
			if(dealer.hit() && bust==false){ //check if dealer wants to hit, also if user busted do not continue
			//if dealer wants to hit tell user that dealer did, and update dealers hand and stats
				System.out.println("Dealer Hits!");
				cardsDealerID+=d.getCard(1);
				dealer.setHand(cardsDealerID);
				dHand= dealer.convertHand();
				dealerVal = dealer.getValue();
				dealer.setVal(dealerVal);
				//System.out.println("dVal: "+dealerVal);
			}
			if(dealer.hit()==false && dealerStand!=true && bust==false){ //if dealer wants to stand and has not stood yet 
				System.out.println("Dealer Stands.");
				dealerStand=true;
			}
		}
		//once dealer and user have stood, or user busted: 
		
		if(dealerVal==21) //tell user if dealer got blackjack or if dealer busted
			System.out.println(dealer.getName()+" has Blackjack!");
		if(dealerVal>21)
			System.out.println(dealer.getName()+" busted!");
		
		 //if user did not bust and had a higher value than dealer while none of them busted, or if dealer busted and user did not 
		if(bust==false && ((userVal>dealerVal && dealerVal<21)|| dealerVal>21)){
			userW=true;
		}
		
		if(userVal==dealerVal && bust==false){ //if user did not bust and both dealer and user had the same cards value
			userW=false;
			tie=true;
		}
		
		line(50);
		
		//end of round results and users profits/losses
		System.out.println("RESULTS: ");
		if(userW){
			System.out.println(name+" Wins!\t +$"+bet);
		}
		else if(tie){
			System.out.println("Tie.");
			bet=0;
		}
		else{
			System.out.println(dealer.getName()+" Wins!\t -$"+bet);
			bet=bet*(-1);
		}
		
		//show what both players hands were
		System.out.println("Dealer's hand: "+dHand+"\t[sum: "+dealerVal+"]");
		System.out.println(name+"'s hand: "+hand+"\t[sum: "+userVal+"]");
		
		//update users money through the amount they bet, and show the user how much money they have left
		user.setBet(bet);
		user.addBet();
		System.out.println(name+"'s money: $"+user.getMoney());
		
		//print a joke
		line(50);
		System.out.println(Joke.nextJoke());
		line(50);
		
		//ask if the user wants to play again
		System.out.print("Do you want to play another round? [Y/N] ");
		round = sc.nextLine();
		round = round.toLowerCase();
		
		if(round.equals("n")){ //if the user does not want to play a new round set newRound to false so that the while true loop at the top gets broken
			newRound=false;
		}
		if(round.equals("y")){  // if the user wants to play a new round, reset all the variables
			roundNum+=1;
			cardsUserID ="";
			cardsDealerID ="";
			hand = "";
			dHand ="";
			userVal = 0;
			dealerVal =0;
			dealer.reset();
			user.reset();
			newGame= false;
			userStand=false;
			dealerStand=false;
			newRound=true;
			bust = false;
			tie=false;
		}
		}
		//once game loop is broken exit 
		System.exit(0);
			
	
	}
	
	public static void line(int times){ //print a line of x amount of *'s to seperate text
		String out = "";
		for(int i = 0; i<times ;i++){
			out+="*";
		}
		System.out.println("\n"+out+"\n");
	}
	
}