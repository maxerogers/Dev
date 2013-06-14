import java.util.Random;

/**
 * This is my version of the BandwidthProblem. My solution is highly inspired by 
 * Dario Vlah's solution. The goal of this solution is to provide a self contained solving blackbox.
 * This way I could use it to with JUnit for testing and future Java Program.
 * 
 * Algorithm Breakdown
 * Base:
 * Edges is a set of Edge(A,B) elements. It is the input value
 * 
 * Best is the set that stores the best paritial solution thus far
 * Position is paritial solution
 * Used is the set that will store the vertices that have already been used in the paritial solution
 * RandomMap is a randomized parition
 * 
 * Top Level:
 * BP(i):
 * if(i == n):
 * 		maxEdge() < oldmin
 * 		oldmin = maxEdge()
 *  	return BestSet
 * else:
 * 	for all vertices:
 * 		A,B elements of Edge(A,B) 
 * 		if(A or B are already placed in the paritial solution below the threshold):
 * 			skip them
 * 		else:
 * 			BPH(Vertex)
 * 
 * SubProblem:
 * BPH(Vertex v):
 * 		for all of v's randomly ordered edges, attempt to fit one into
 * 		the parial solution.
 * 		if random edge hasn't been used:
 * 			store what level it was used
 * 			and then try to find a solution at the next level
 * 			BP(i+1)
 * 
 * Solution():			
 * start BP(0) and let it run until it finds a solution stored in Best
 * 
 * @author max rogers
 * ID: 107979405
 * CSE 373 Spring 2013
 *
 */
public class BandwidthProblem 
{
	public static final int MAXV = 256;
	int n,m; //number of Vertices and Edges
	int min; //smallest edge
	int count = 0;
	int decoy = 0; //used to help randomize the max edge process
	//edge(A,B)
	int[] edges1; //a list to hold all the vertex As
	int[] edges2; //a list to hold all the vertex Bs
	int[] vertices = new int[MAXV]; //states if the vertex is avaiable
	int[] position = new int[MAXV]; //stores the location of a vertex
	int[] best = new int[MAXV]; //stores the best solution so far
	int[] used = new int[MAXV]; //tracks which vertices have been used in the solution
	int[] random = new int[MAXV]; //used for randomization
	
	Random randGene = new Random();
	BandwidthProblem(int newN,int newM, int[] l1, int[] l2)
	{
		this.n = newN;
		this.m = newM;
		edges1 = l1;
		edges2 = l2;
		setup();
		randomize();
		fillPosition();
		min = maxEdge();
		buildBest();
		findSolution(0);
		System.out.println("Solution Found");
	}
	
	public String getSoltuion()
	{
		String result = "Solution: ";
		for(int i=0;i<n;i++)
		{
			result += best[i]+1;
		}
		result += "\n Bandwidth: "+min;
		return result;
	}
	
	private void buildBest()
	{
		for(int i=0;i<n;i++)
		{
			best[i] = vertices[i];
		}
	}
	private void setup()
	{
		//setup up extra edge arraries
		for(int i=0;i<n;i++)
		{
			vertices[i] = i;
			used[i] = 0;
			position[i] = i;
			random[i] = i;
		}
	}
	private void randomize()
	{
		//build random map
		for(int i=0;i<1000;i++)
		{
			int v1 = randGene.nextInt(n);
			int v2 = randGene.nextInt(n);
			int temp = random[v1];
			random[v1] = random[v2];
			random[v2] = temp;
		}
	}
	private void fillPosition()
	{
		for(int i=0;i<1000;i++)
		{
			int v2 = randGene.nextInt(n);
			int oldmax = maxEdge();
			int v1 = position[decoy];
			count = 0;
			do
			{
				int temp = vertices[v1];
				vertices[v1] = vertices[v2];
				vertices[v2] = temp;
				if(maxEdge() > oldmax)
				{
					temp = vertices[v1];
					vertices[v1] = vertices[v2];
					vertices[v2] = temp;
					position[vertices[v1]] = v1;
					position[vertices[v2]] = v2;
				}
				count++;
				v2 =  randGene.nextInt(n);
			}while(maxEdge() >= oldmax && count < 50);
		}
	}
	private int maxEdge()
	{
		int v=0, max=0, v1, v2;
		for(int i=0;i<m;i++)
		{
			v1 = edges1[i];
			v2 = edges2[i];
			v = Math.abs(position[v2] - position[v1]);
			if(v > max)
			{
				max = v;
				decoy = v1;
				if(randGene.nextInt() % 2 == 1)
					decoy = v2;
			}
		}
		return max;
	}
	
	private void findSolution(int level)
	{
		if(level == n) //
		{
			int max = maxEdge();
			if(max < min)
			{
				min = max;
				for(int i=0;i<n;i++)
					best[i] = vertices[i];
				System.out.println("New Min: "+min);
			}
		} else{
			for(int i=0;i<m;i++)
			{
				if((used[edges1[i]] == 1 )&& (used[edges2[i]] != 1))
					if(position[edges1[i]] <= level-min)
						return;
				if((used[edges1[i]] != 1 )&& (used[edges2[i]] == 1))
					if(position[edges2[i]] <= level-min)
						return;
				for(int j=0;j<n;j++)
				{
					i = random[j];
					if(used[i] == 0)
					{
						used[i] = 1;
						vertices[level] = (int) i;
						position[i] = (int) level;
						
						findSolution(level+1);
						used[i] = 0;
					}
				}
			}
		}
	}
}
