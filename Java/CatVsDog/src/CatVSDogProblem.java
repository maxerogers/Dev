import java.util.Scanner;
/**
 * This my solution for the cat dog problem
 * 
 * The goal of this is to create two arrays one to store the votes for a particular cat/dog to stay
 * and another to store their votes to go
 * 
 * Then at the end we iterate through to find the cat/dog who will most votes for or against it
 * 
 * Test Cases:
 * C1 D1
 * D1 C1
 * 
 * it recognizes that the max votes for one particular animal regardless of for/against is 1
 * so it returns 1
 * 
 * Test Case2:
 * C1 D1
 * C1 D1
 * C1 D2
 * D2 C1
 * 
 * it recognizes that C1 has the most votes to stay so it says there are 3 viewers we can make happy
 * by keeping C1 on board
 * 
 * Originally I thought this problem was about finding the pair with the most votes for and against it
 * however your output doesn't suppor that
 * 
 * Then I thought the problem was about finding the animal to kick off that would satisfy the most viewers 
 * but TestCase 2 doesn't support that since C1 is the only element that recurs 3 times
 * @author max
 *
 */
public class CatVSDogProblem 
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		String temp = scan.nextLine();
		int T = Integer.parseInt(temp);
		for(int i=0;i<T;i++)//Will loop the number of test cases
		{
			temp = scan.nextLine();
			int[] vars = new int[3]; //this will store the number of Cats/Dogs/Voters for each case
			String[] temp2 = temp.split(" "); //splits them up based upon white space
			for(int j=0;j<3;j++)
			{
				vars[j] = Integer.parseInt(temp2[j]);
				//System.out.println(vars[j]);
			}
			int[] votesFor = new int[2];
			//int[] votesAgainst = new int[length];
			for(int k=0;k<vars[2];k++)
			{
				temp = scan.nextLine();
				if(temp.charAt(0) == 'C')
					votesFor[0]++;
				else
					votesFor[1]++;
			}
			
			System.out.println(Math.max(votesFor[0],votesFor[1]));
		}
	}
	
	private static int findMax(int[] votesFor, int cursor) {
		int catVotes = 0;
		int dogVotes = 0;
		for(int i=0;i<votesFor.length;i++)
		{
			if(i>=cursor)
				dogVotes++;
			else
				catVotes++;
		}
		return Math.max(catVotes, dogVotes);
	}
	//Cursor is the index that seperates the cats from the dogs
	private static int findIndex(String input, int cursor)
	{
		int result = 0;
		if(input.charAt(0) == 'D')
		{
			result = Character.getNumericValue(input.charAt(1))+cursor-1;
		}else{
			result =  Character.getNumericValue(input.charAt(1))-1;
		}
		return result;
	}
}
