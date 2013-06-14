import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

/**
 * With this driver class, make sure that the .txt is in the bin folder otherwise
 * you will need to rewrite the pathing url
 * @author max
 *
 */
public class Main 
{
	public static void main(String[] args)
	{
		String input = "g-bt-10-9.txt"; 
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL url = classLoader.getResource(input); 
		File f = null;
		try{
			f = new File(url.getPath());
		}catch(NullPointerException e){ System.out.println("Invalid file path");}
		if(f.exists())
		{
			System.out.println("Valid file path");
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(f));
			} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int m = 0,n =0;
			int[] edges1 = null,edges2 = null;
			try{
				int count = 0;
				String line = null;
					do
					{
						line = br.readLine();
						if(count == 0)
						{
							//read number of Edges
							//System.out.println(line);
							n = Integer.parseInt(line);
							edges1 = new int[n];
							edges2 = new int[n];
						}else if(count == 1){
							//read number of Vertices
							m = Integer.parseInt(line);
						}else{
							//read in edges
							//System.out.println(line);
							try{
								String[] split = line.split("    ");
								edges1[count-2] = Integer.parseInt(split[0]);
								edges2[count-2] = Integer.parseInt(split[0]);
							}catch(NullPointerException e)
							{}
						}
						count++;
				}while(line != null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
			
	//		long startTime = System.nanoTime();
			CopyOfBandwidthProblem bp = new CopyOfBandwidthProblem(n,m,edges1,edges2);
	//		long endTime = System.nanoTime();
			System.out.println(bp.getSoltuion());
		//	System.out.println("Starting Unixtime: "+startTime);
		//	System.out.println("End UnixTime: "+endTime);
		//	System.out.println("Total Difference in NanoSeconds: "+(endTime - startTime));
		//	System.out.println("Total Difference in RegularSeconds: "+(endTime - startTime)/(Math.pow(10, 9)));

		}else{
			System.out.println("Invalid file path");
		}
	}
}
