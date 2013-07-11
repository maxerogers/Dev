public class CardCounting
{
	public static int operations = 0;
	public static void main(String[] args)
	{
		System.out.println(factorial(5));
		System.out.println(operations);
	}
	public static int factorial(int x)
	{
		if(x <= 1)
		{
			operations++;
			return 1;
		}else{
			operations++;
			return factorial(x-1) * x;
		}
	}
}