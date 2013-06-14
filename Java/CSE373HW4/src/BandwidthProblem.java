import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.sound.sampled.Line;


public class BandwidthProblem 
{
	public static final int MAXVERTICES = 256;
	char *edge1,*edge2;   // Edges we store in here
	char *edges;   // NxN matrix :)

	char vtex[] = new char[MAXVERTICES];  // vtex[i] = vertices available
	char posn[] = new char[MAXVERTICES];  // posn[i] = where is vertex i?
	char best[] = new char[MAXVERTICES];  // the best permutation so far
	char used[] = new char[MAXVERTICES];  // tracks vertices used in the solution
	char maxl[] = new char[MAXVERTICES];  // tracks max len of edge during backtracking
	char rmap[] = new char[MAXVERTICES];  // used for dynamic randomizing
	char larg[] = new char[MAXVERTICES];  // used to keep track of the first largest edge
	
	BandwidthProblem(boolean d)
	{
		numEdges = 0;
		numVertices = 0;
		directed = d;
	}
	void addEdge(String str)
	{
		int x = -1;int y = -1;
		boolean flag = true;
		try{
			String[] split = str.split(" ");
			x = Integer.parseInt(split[0]);
			y = Integer.parseInt(split[4]);
		}catch(NullPointerException e){
			flag = false;
		}
		if(flag)
		{
			matrix[x][y] = 1;
			if(this.directed == false)
				matrix[y][x] = 1;
		}
	}
	
	void readInput(File f)
	{
		//go through the file and add up the lines
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
					this.numVertices = Integer.parseInt(line);
					this.matrix = new int[numVertices+1][numVertices+1];
					for(int i=0;i<numVertices;i++)
					{
						for(int j=0;j<numVertices;j++)
						{
							matrix[i][j] = 0;
						}
					}
				}else if(count == 1){
					//read number of Vertices
					this.numEdges = Integer.parseInt(line);
				}else{
					//read in edges
					this.addEdge(line);
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
	}
	public String printGrpah()
	{
		String result = "******\nPrinting Graph:\n\t";
		for(int i=1;i<this.matrix.length;i++)
			result += i +"| ";
		result += "\n";
		for(int i=1;i<this.matrix.length;i++)
		{
			result += i+":\t";
			for(int j=1;j<this.matrix[i].length;j++)
			{
				result += matrix[i][j]+", ";
			}
			result += "\n";
		}
		result += "******\n";
		return result;
	}

	public String producePermuation()
	{
		findNextLayer();
		return this.permutation;
	}
}
