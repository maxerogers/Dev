import java.util.ArrayList;


public class Main {
	public static void main(String[] args)
	{
		int[] input = {1,2,3,4,6,5,9,8,7};
		printArray(input);
		ArrayList<Integer> result = recognizeSequences(input);
		printArray(result);
	}
	
	public static ArrayList<Integer> recognizeSequences(int[] input)
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i=0;i<input.length-2;i++)
		{
			int k = input[i+1]-input[i];
			if(k == 1 || k == -1)
			{
				int j = input[i+2] - input[i+1];
				if( k == j)
				{
					result.add(i);
				}
			}
		}
		return result;
	}
	
	public static ArrayList<Integer> recognizeSequences2(int[] input)
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i=0;i<input.length-2;i++)
		{
			int k = input[i+1]-input[i];
			if(k == 1 || k == -1)
			{
				int j = input[i+2] - input[i+1];
				if( k == j)
				{
					result.add(i);
				}
			}
		}
		return result;
	}
	
	public static String printArray(ArrayList<Integer> input)
	{
		String result = "";
		for(int i=0;i<input.size();i++)
		{
			result += input.get(i) + ", "; 
		}
		System.out.println(result);
		return result;
	}
	public static String printArray(int[] input)
	{
		String result = "";
		
		for(int i=0;i<input.length;i++)
		{
			result += input[i] + ", ";
		}
		System.out.println(result);
		return result;
	}
}
