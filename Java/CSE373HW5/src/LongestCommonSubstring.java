
public class LongestCommonSubstring 
{
	public static void main(String[] args)
	{
		String A = "photograph";
		String B = "tomography ";
		int[][] matrix = new int[A.length()][B.length()];
		// initialize
		for(int i =0;i<A.length();i++)
		{
			matrix[i][0] = 0;
		}
		for(int i =0;i<B.length();i++)
		{
			matrix[0][i] = 0;
		}
		for(int i = 1; i<A.length();i++)
		{
			for(int j = 1; j<B.length();j++)
			{
				if(A.charAt(i) == B.charAt(j))
				{
					matrix[i][j] = matrix[i-1][j-1] + 1;
				}else{
					matrix[i][j] = 0;
				}
			}
		}
		
		String result = "***************\n\t";
		for(int i=0;i<A.length();i++)
			result += A.charAt(i)+" | ";
		for(int i=0;i<B.length();i++)
		{
			result += "\n"+ B.charAt(i) + "| \t";
			for(int j=0;j<A.length();j++)
			{
				result += matrix[j][i] + " , "; 
			}
		}
		result += "\n***************\n";
		System.out.println(result);
		
	}
}
