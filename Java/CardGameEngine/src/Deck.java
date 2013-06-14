import java.util.ArrayList;
import java.util.Random;


public class Deck 
{
	public ArrayList<Card> deck = new ArrayList<Card>();
	Deck(){}
	Deck(int n)
	{
		for(int i=0;i<n;i++)
		{
			deck.add(new Card(i));
		}
	}
	public Card drawCard(){
		Card temp = deck.get(deck.size());
		deck.remove(deck.size());
		return temp;
	}
	public void insertCard(Card c)
	{
		deck.add(c);
	}

	public void mergeDeck(Deck d)
	{
		for(int i=0;i<d.deck.size();i++)
		{
			this.deck.add(d.deck.get(i));
		}
	}
	public void shuffleDeck()
	{
		Random rand = new Random();
		for(int i=0;i<deck.size();i++)
		{
			int temp = deck.get(i).id;
			int x = randomIndex(0,deck.size()-1);
			int temp2 = deck.get(x).id;
			int y = temp;
			temp = temp2;
			temp2 = y;
			deck.get(i).id = temp;
			deck.get(x).id = temp2;
			
		}
	}
	
	public int randomIndex(int min, int max)
	{
		return min+(int)(Math.random() * max);
	}
	
	/**
	 * This sorts the deck.Using 
	 */
	public void sortDeck()
	{
		if(deck.size() == 52)
		{
			for(int i=0;i<52;i++)
			{
				deck.set(i, new Card(i));
			}
		}else{
			int[] array = new int[deck.size()];
			Card[] cards = deck.toArray(new Card[0]);
			for(int i=0;i<array.length;i++)
			{
				array[i] = cards[i].id;
			}
			quickSort(array,0, (array.length-1));
			for(int i=0;i<array.length;i++)
			{
				deck.set(i, new Card(array[i]));
			}
		}
	}
	
	int partition(int arr[], int left, int right)
	{
	      int i = left, j = right;
	      int tmp;
	      int pivot = arr[(left + right) / 2];
	     
	      while (i <= j) {
	            while (arr[i] < pivot)
	                  i++;
	            while (arr[j] > pivot)
	                  j--;
	            if (i <= j) {
	                  tmp = arr[i];
	                  arr[i] = arr[j];
	                  arr[j] = tmp;
	                  i++;
	                  j--;
	            }
	      };
	     
	      return i;
	}
	 
	void quickSort(int arr[], int left, int right) {
	      int index = partition(arr, left, right);
	      if (left < index - 1)
	            quickSort(arr, left, index - 1);
	      if (index < right)
	            quickSort(arr, index, right);
	}

	
	public String printDeck()
	{
		String result = "";
		for(int i=0;i<deck.size();i++)
		{
			result += deck.get(i).id + ", ";
		}
		return result;
	}
}
