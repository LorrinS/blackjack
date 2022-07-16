import java.util.*;
import java.io.*;
public class Joke{
	private static ArrayList<String> jokes = new ArrayList<String>();
	private static String joke;
	static Scanner scFile = null;
	
	public static void loadJokes() throws FileNotFoundException{  //fill up the jokes arraylist
	//access joke file and load all the jokes into the arraylist
		scFile = new Scanner(new File("Jokes.txt"));
		while(scFile.hasNext()){
			jokes.add(scFile.nextLine());
		}
		//shuffle the arraylist of jokes
		Collections.shuffle(jokes);
	}
	public static String nextJoke() throws FileNotFoundException{ //get the next joke in the list
		if(jokes.size()==0) //if the jokes list is empty load it up
			loadJokes();
		//take a joke from the jokes list and then remove it from the list
		joke = jokes.get(0);
		jokes.remove(0);
		return joke;
	} 
}