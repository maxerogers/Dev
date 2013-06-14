import java.io.File;
import java.net.URL;
import java.util.Scanner;


public class Main 
{
	public static void main(String[] args)
	{
		String input = "g-p-10-9.txt";
		Scanner scan = new Scanner(System.in);
		while(!input.equalsIgnoreCase("Q"))
		{
			System.out.println("Insert the path of the Graph Txt File(Type Q to quit)");
			input = scan.nextLine();
			//System.out.println(input);
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			input =  "g-p-10-9.txt";
			URL url = classLoader.getResource(input);
			File f = null;
			try{
				f = new File(url.getPath());
			}catch(NullPointerException e){
				System.out.println("Invalid file path");
				continue;}
			if(f.exists())
			{
				System.out.println("Valid file path");
				long startTime = System.nanoTime();
				System.out.println("Starting Unixtime: "+startTime);
				Graph g = new Graph(false);
				g.readInput(f);
				long endTime = System.nanoTime();
				System.out.println("End UnixTime: "+endTime);
				System.out.println("Total Difference in NanoSeconds: "+(endTime - startTime));
				System.out.println("Total Difference in RegularSeconds: "+(endTime - startTime)/(Math.pow(10, 9)));
				System.out.println(g.printGrpah());
				g.producePermuation();
				System.out.println(g.printGrpah());
			}else if(input.equalsIgnoreCase("q"))
			{
				System.out.println("Good Bye");
			}else{
				System.out.println("Invalid file path");
			}
		}
	}
}
