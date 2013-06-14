
public class EditDistance 
{
	String start; String target;
	int m,n;
	int matrix[][];
	EditDistance(String s, String t)
	{
		start = s; target = t;
		m = s.length()+1;
		n = t.length()+1;
		matrix = new int[m][n];
		for(int i=0;i<m;i++)
			matrix[i][0] = 0;
		for(int i=0;i<n;i++)
			matrix[0][i] = 0;
		matrixFill();
	}
	private void matrixFill()
	{
		for(int i=1;i<m;i++)
		{
			for(int j=1;i<n;j++)
			{
				int result = 0;
				result = 
				matrix[i][j] = result;
			}
		}
	}
}
