import java.util.Random;


public class Main {
	/**
	3-11. [5] Suppose that we are given as sequence of n values x1,....xn and seek to
			quickly answer repeated queries of the form: given i and j, find the smallest value in xi,...,xj.
			(a) Design a data structure that uses O(n2) space and answers queries in O(1) time.
			(b) Design a data structure that uses O(n) space and answers queries in O(log n) time. For partial credit, your data structure can use O(n log n) space and have O(log n) query time.
	
	*/
	public static void main(String[] args)
	{
		//EZBinaryTree ezbt = new EZBinaryTree(initSequenceArray(100));
		
		double [] array = makeArray(10);
		/*
		double count = 0;
		for(int i=0;i<array.length;i++)
		{
			System.out.print(array[i]+",");
			count += array[i];
		}
		double[] output = worstFit(array);
		System.out.println("\n"+count+"*****************");
		count = 0;
		for(int i=0;i<output.length;i++)
		{
			System.out.print(output[i]+",");
			count += output[i];
		}
		System.out.println("\n"+count+"*****************");
		*/
		System.out.println("\n*****************");
		double[][] result = makeBitArray(array, 4);
		for(int i=0;i<result.length;i++)
		{
			System.out.print(result[i][0]+":"+result[i][1]+", ");
		}
	}
	private static double[][] makeBitArray(double[] array, int m) {
		double[][] output = new double[array.length][2];
		double min = array[array.length-1];
		double max = -1;
		for(int i=array.length-1;i>=0;i--)
		{
			output[i][0] = array[i];
			if(i == array.length || i%m == m-1 || min > output[i][0])
			{
				min = output[i][0];
			}
			output[i][1] = min;
			if(max < output[i][0])
				max = output[i][0];
		}
		return output;
	}
	public static double[] bestFit(double[] input)
	{
		double[] output = new double[1];
		int cursor = 0;
		for(int i=0;i<input.length;i++)
		{
			for(int j=cursor;j<output.length;j++)
			{
				if(output[j]+input[i] <= 1.0)
				{
					output[j] += input[i];
					for(int k=cursor;k<output.length;k++)
					{
						if(output[k] > output[j])
						{
							double temp = output[k];
							output[k] = output[j];
							output[j] = temp;
							if(output[k] == 1)
								cursor++;
							break;
						}
					}
					break;
				}
				if(j==output.length-1)
				{
					double[] result = new double[output.length+1];
					for(int k=0;k<output.length;k++)
					{
						result[k] = output[k];
					}
					result[output.length] = 0;
					output = result;
				}
			}
		}
		return output;
	}
	
	public static double[] worstFit(double[] input)
	{
		double[] output = new double[1];
		int cursor = 0;
		for(int i=0;i<input.length;i++)
		{
			for(int j=cursor;j<output.length;j++)
			{
				if(output[j]+input[i] <= 1.0)
				{
					output[j] += input[i];
					for(int k=output.length-1; k > cursor;k--)
					{
						if(output[k] < output[j])
						{
							double temp = output[k];
							output[k] = output[j];
							output[j] = temp;
							if(output[j] == 1)
								cursor++;
							break;
						}
					}
					break;
				}
				if(j==output.length-1)
				{
					double[] result = new double[output.length+1];
					for(int k=0;k<output.length;k++)
					{
						result[k] = output[k];
					}
					result[output.length] = 0;
					output = result;
				}
			}
		}
		return output;
	}
	
	public static double[] makeArray(int size)
	{
		double[] result = new double[size];
		for(int i=0;i<size;i++)
			result[i] = Math.floor(1000*Math.random())/1000;
		return result;
	}
}
