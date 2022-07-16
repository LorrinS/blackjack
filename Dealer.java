public class Dealer extends Player{
	private String hand;
	private String handID;
	private int handSum;
	private String name;
	private int age;
	private int money;
	
	public Dealer(){ // automatically set dealers profile
		super("Dealer X", 21);
		this.name = "Dealer X";
		this.age = 21;
		this.money = 1000000;
	}
	public void setVal(int x){ //set the value of the Dealer's hand
		this.handSum = x;
	}
	public boolean hit(){ // check whether dealer wants to hit or stand based on the dealers current hand's value
		return (handSum<17);
	}
	public String showHand(){ //show the hand of the dealer 
		String out = "";
		String temp = convertHand();
		int spc = 0;
		boolean temp2 = false; //temp2 once one card is revealed make the rest ?
		//reveal one card and replace the rest with '?'
		for(int i=0; i<temp.length();i++){
			if(temp.charAt(i)!=' ' && temp2==false) 
				out+=String.valueOf(temp.charAt(i));
			if(temp.charAt(i)==' '){
				spc++;
				temp2=true;
			}
		}
		for(int i=0; i<(spc-1); i++){
			out+=" ?";
		}
		return out;
	}

	public String getName(){ //return the name of the dealer
		return name;
	}
	public int getCoveredVal(){ // get the value of the one card that the dealer revealed
		int out = 0;
		
		String a = ((showHand()).substring(0,3)).trim(); //get the card in question
		//get rid of the suits
		a = a.replaceAll(String.valueOf((char)(3)), "");
		a = a.replaceAll(String.valueOf((char)(4)), "");
		a = a.replaceAll(String.valueOf((char)(5)), "");
		a = a.replaceAll(String.valueOf((char)(6)), "");
		//check for cards that are not numbers
		if(a.equals("A"))
			out=11;
		else if(a.equals("J") || a.equals("Q") || a.equals("K"))
			out=10;
		else //if the card is a number return that card as is
			out = Integer.parseInt(a);
		return out;
			
	}
}