public class Fibonacci
{
	public static int operations = 0;
	public static int[] memo = new int[100];
	public static void main(String[] args)
	{
		System.out.println(fibonacci(9));
		System.out.println(operations);
		operations = 0;
		System.out.println(betterFibo(9));
		System.out.println(operations);
	}
	public static int fibonacci(int x)
	{
		operations++;
		if(x <= 2)
		{
			if(x == 0)
				return 0;
			else
				return 1;
		}else{
			return fibonacci(x-1) + fibonacci(x-2);
		}
	}
	public static int betterFibo(int x)
	{
		operations++;
		if(memo[x] == 0)
		{
			if(x == 0)
			{
				memo[x] = 0;
			}else if(x <= 2)
			{
				memo[x] = 1;
			}else{
				memo[x] = betterFibo(x-1) + betterFibo(x-2);
			}
		}
		return memo[x];
	}
}