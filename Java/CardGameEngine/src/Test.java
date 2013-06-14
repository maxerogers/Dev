import static org.junit.Assert.*;


public class Test {

	@org.junit.Test
	public void test() {
		Deck myDeck = new Deck(51);
		System.out.println(myDeck.printDeck());
		myDeck.shuffleDeck();
		System.out.println(myDeck.printDeck());
		myDeck.sortDeck();
		System.out.println(myDeck.printDeck());
	}

}
