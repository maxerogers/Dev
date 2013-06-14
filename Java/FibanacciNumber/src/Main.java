
public class Main {
	public static void main(String[] args)
	{
		int x = 15;
		int y = recursiveSolution(x);
		System.out.println(y);
	}

	private static int recursiveSolution(int x) {
		if(x == 1)
			return 1;
		if(x == 2)
			return 1;
		else
			return recursiveSolution(x-1) + recursiveSolution(x-2);
	}
}
