/*
* Author: Max Rogers
* ID: 107979405
* Class: CSE 373 - Skiena - Spring 2013
* Assignment: HW4 - Bandwidth Problem
* 
*The problem is to build a permutation of a graph that maintains a certain minimum bandwidth
* Example: g-bt-10-9.txt
	G(V,E): V = {1,....,10}, E = {[1,2],[1,3],[2,4],[2,5],[3,6],[3,7],[4,8],[4,9],[5,10]}
	BP(G',P,MB): G'=G, P = 7 6 3 10 1 5 2 4 9 8, MB = 2
* 
* I discovered Dario Vlah's solution when I was doing research for this problem. So this program 
* is inspired by his work. I tried to make it my own but if it is too close, please let me know 
* and I will try to build an alternative solution
*
* Base:
	Set of all Edges, Edge (A,B)
	List of all Vertices
	List of vertice locations in the best solution
	Sequence for Best Solution
	Sequence for Current Solution
	Sequence for Randomized Solution
	
	Subproblem:
	minBand():
		go through all of the edges and calculate the distance between each pairing in the permutation. 
		Storing the highest one used for this solution.
		
		minBand() = Min( distance between A->B for all Edges(A,B))
	
	FindSol(i):
	checks if i is the top level or not. 
	if i is top level then check if there is a new max edge. 
		if there is a new min bandwidth, then overwrite old best permutation
	else go through all edges and check for new valid vertices missing from the solution
	    if you find a vertice that has been left behind return back up the stack
		else go through all of the other vertices and see if this vertex and it have an edge. 
		if they do, then add it to the current permutation and go up a level in the solution
		make sure to also keep track of what vertices have already been used.
		findSol(i+1)
	
		findSol(i)  = 
			if i == n: 
				for all i in Best:
					if minB > minBand():
						Best = Current
			else:
				for all edge pairs check all within the MinBand
					if one isn't within the correct distance pop back up to that point
				randomly pick a new candidate vertex to go on
					
		
	Top Level:
		After building Locations, Random you will build the original solution with the upper bound bandwidth of the permutation
		Then you will use findSol(0) to find the true minimum bandwidth 
		
		Min( findSol(i) for 0->m )
*/// **********************************************************************

#include "stdio.h"
#include "stdlib.h"
#include "unistd.h"

#define MAX_V 256

//Fields
int *vertA,*vertB;   // Stores the element of Edge(A,B) so Edge_i(A,B)= vertA[i] :: vertB[i];
int verts[MAX_V];  // A list of all the vertices
int loct[MAX_V];  // loct[i] = where is vertex i in the solution? 
int best[MAX_V];  // the best partial solution so far
int used[MAX_V];  // tracks vertices used in the latestsolution
int memo[MAX_V];  // used for dynamic randomizing

int n; // number of vertices
int m; // number of edges
int upper; //stores the highest bandwidth
int min;  // Smallest longest edge
int pivot=0; //used in minBand to keep track of the pivoting vertex

//Functions
int minBand();
void initArrays();
void randMemo();
void findSol(int level);
void buildLocations();

int main() 
{
    int i, temp, v1, v2;
    
    fprintf(stderr, "Now reading in Input Graph\n");
    
    scanf("%d", &n);   // Read  numVertices
    scanf("%d", &m);	// Read numEdges
    
    //Allocating VertA/B for m vertices each
    vertA = (int *)malloc(m);  
    vertB = (int *)malloc(m);  
    
    int ecount=0;
    for (i=0; i<m; i++) 
    {        // Start reading edges
        scanf("%d %d", &v1, &v2);
        v1--; v2--;
        if (v1!=v2 && (v1>=0 && v1<n && v2>=0 && v2<n)) //This is used to make sure that there weren't any illegal variables used
        { 
            vertA[ecount]=v1;
            vertB[ecount]=v2;
            ecount++;
        }
        
    }
    m=ecount; //This is important to make sure there aren't any missing edges
    
    initArrays();
    randMemo();
   
   buildLocations; 
   
    min = minBand(); // store the original solution
    for (i=0; i<n; i++) 
    	best[i]=verts[i];
    
    printf( "Now starting to find solution...\n");
    
    printf("Starting minimum: %d\n", min);
    upper = min;
    findSol(0);  // here we go.
    
    printf("Solution: ");
    for (i=0; i<n; i++) 
    	printf( "%d ", best[i]+1);
   	printf("\n");
    
    printf( "Minimum Bandwidth: %d\n", min);
   // printf( "Upper Bound Bandwidth: %d\n", upper);

    return 0;
}

void initArrays()
{
	int i;
	for (i=0; i<n; i++) 
	{ // Initialize ALL THE arrays
        verts[i]=i;
        used[i]=0;
        loct[i]=i;
        memo[i]=i;
    }
}

void randMemo()
{
	int i,v1,v2,temp;
	for (i=0; i<500; i++)  //Randomize Memo 
    {
        v1 = random() % n;
       	v2 = random() % n;
        temp=memo[v1]; memo[v1]=memo[v2]; memo[v2]=temp;
    }
}
// build the list that stores the location of the permutation which will be later used by minBand 
// to discover the minimum bandwidth between the vertices
void buildLocations()
{
	 int i,v1,v2,temp,oMax, nMax, count;
    for (i=0; i<500; i++) 
    {
        v2 = random() % n;
        oMax = minBand();
        v1=loct[pivot];
        count=0;
        do {
            temp=verts[v1]; 
            verts[v1]=verts[v2]; 
            verts[v2]=temp;
            loct[verts[v1]]=v1;
            loct[verts[v2]]=v2;
            
            nMax=minBand();
            if (nMax>oMax) {
                temp=verts[v1]; 
                verts[v1]=verts[v2]; 
                verts[v2]=temp;
                loct[verts[v1]]=v1;
                loct[verts[v2]]=v2;
            };
            count++;
            v2 = random() % n;
        } while (nMax>=oMax && count<50);
    }
}
/*
minBand()
	go through all of the edges
	calculate the distance between each pairing in the permutation. Storing the highest one used
*/
int minBand() 
{
    int v=0, maxv=0, v1,v2;
    for (int i=0; i<m; i++) 
    {
        v1=vertA[i];
        v2=vertB[i];
        v = abs(loct[v2]-loct[v1]); //calculates the edge bandwidth between v2 and v1
        if (v > maxv) //if it more than the current max, switch them
        { 
            maxv = v;
            pivot=v1; 
            if (random()%2) //sometimes its important to switch it around
            	pivot=v2;
        }
    }
    return maxv;
}

/*
FindSol(i):
	checks if i is the top level or not. 
	if i is top level then check if there is a new max edge. 
		if there is a new min bandwidth, then overwrite old best permutation
	else go through all edges and check for new valid vertices missing from the solution
	    if you find a vertice that has been left behind return back up the stack
		else go through all of the other vertices and see if this vertex and it have an edge. 
		if they do, then add it to the current permutation and go up a level in the solution
		findSol(i+1)
		
*/
void findSol(int level) 
{
    int i;
    
    if (level==n) 
    {
        int max=minBand(); 
        if (max<min) {
            min=max;
            for (int i=0; i<n; i++) best[i]=verts[i]; //stores the lastest partial solution into best
            printf("Min is now: %d\n", min);
        }
        
    } else {
        for (i=0; i<m; i++) 
        {
            if ((used[vertA[i]] == 1) && (used[vertB[i]] != 1))
                if (loct[vertA[i]] <= level-min) 
                	return; //Go back up
            if ( (used[vertA[i]] != 1)&& (used[vertB[i]] == 1))
                if (loct[vertB[i]] <= level-min) 
                	return; //Go back up
        }
        for (int k=0; k<n; k++) 
        {
            i=memo[k];
            if (used[i] != 1) 
            {
                used[i]=1;
                verts[level]=i;
                loct[i]=level;
                
                findSol(level+1);
                
                used[i]=0;
            }
        } // end of main for loop
    }
}