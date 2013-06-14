
public class CoinProblem 
{
	private int[] set;
	private int[] memo;
	CoinProblem(int[] s)
	{	set = s;memo = new int[100];
		for(int m : memo){
			m = 0;
		}
	}
	public int C(int n)
	{
		for(int i=1;i<=n;i++)
		{	
			memo[i] = CHelper(i, set.length-1);
		}
		return memo[n];
	}
	public int CHelper(int n, int k)
	{
		if(n == 0)
			return 1;
		else if(memo[n] != 0)
			return memo[n];
		else if(n < 0 || k<0)
			return 0;
		else return CHelper(n, k-1) + CHelper(n-set[k], k);
	}
}
