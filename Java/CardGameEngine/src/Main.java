
public class Main {
	public static void main(String[] args)
	{
		Deck myDeck = new Deck(50);
		System.out.println(myDeck.printDeck());
		myDeck.shuffleDeck();
		System.out.println(myDeck.printDeck());
		myDeck.sortDeck();
		System.out.println(myDeck.printDeck());
	}
}
