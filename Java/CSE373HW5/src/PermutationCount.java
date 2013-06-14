
public class PermutationCount 
{
	public static void main(String[] args)
	{
		int n = 7;
		int r = 3;
		int x = permutation(n,r);
		int y = count(n,r);
		System.out.println(x);
		System.out.println(y);
	}
	public static int permutation(int n,int r)
	{
		return (factorial(n)/(factorial(n-r)));
	}
	public static int count(int n, int r)
	{
		return (factorial(n)/(factorial(r)*factorial(n-r)));
	}
	 public static int factorial(int n) 
	 {
        int fact = 1; // this  will be the result
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
}
