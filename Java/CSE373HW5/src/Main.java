
public class Main 
{
	public static void main(String[] args)
	{
	//	int n = 20;
	//	int[] set = {1,6,10};
	//	CoinProblem cp = new CoinProblem(set);
	//	System.out.println(cp.C(20));
		System.out.println(distance("monkey","skeletonkey"));
	}
	/**
	 * This is the edit distance problem. It returns the minimal moves it takes to transform string s1 into s2. This is useful for detecting spelling mistakes
	 * The logic behind it:
	 * Subproblem: M[i][j] = Min(M[i-1][j]+1,M[i][j-1]+1,X)
	 * 			   if s1[i-1] == s2[j-1] then X is M[i-1][j-1]
	 * 			   el X is M[i-1][j-1]+1
	 * Base Case: M[0][j] = j and M[i][0] = i
	 * Top Level: calculate the entire matrix n*m
	 * Solution: at M[s1.len][s2.len]
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int distance(String s1, String s2){
	     int edits[][]=new int[s1.length()+1][s2.length()+1];
	     for(int i=0;i<=s1.length();i++)
	         edits[i][0]=i;
	     for(int j=1;j<=s2.length();j++)
	         edits[0][j]=j;
	     for(int i=1;i<=s1.length();i++){
	         for(int j=1;j<=s2.length();j++){
	             int u=(s1.charAt(i-1)==s2.charAt(j-1)?0:1);
	             edits[i][j]=Math.min(
	                             edits[i-1][j]+1,
	                             Math.min(
	                                edits[i][j-1]+1,
	                                edits[i-1][j-1]+u
	                             )
	                         );
	         }
	     }
	     for(int i =0;i<s1.length();i++)
	     {
	    	 System.out.println();
	    	 for(int j=0;j<s2.length();j++)
	    	 {
	    		 System.out.print(edits[i][j]+", ");
	    	 }
	     }
	     System.out.println();
	     return edits[s1.length()][s2.length()];
	}
}
