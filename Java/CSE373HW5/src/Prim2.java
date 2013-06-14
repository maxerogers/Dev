
import java.util.*;


class Prim2
{
public static void main(String args[])
{
Scanner sc = new Scanner(System.in);


System.out.println("enter no. of vertices :  ");
int v=sc.nextInt();


System.out.println("enter no. of edges : ");
int e=sc.nextInt();


int weight[][]=new int[v+1][v+1];


for(int i=1;i<=e;i++)
    {
    System.out.println("enter starting and ending vertices : ");
    int a=sc.nextInt();
    int b=sc.nextInt();
    
    System.out.println("enter weight of this edge else enter zero : ");
    weight[a][b]=sc.nextInt();
    weight[b][a]=weight[a][b];
    }


int visited[]=new int[v+1];
int distance[]=new int[v+1];    
int path[]=new int[v+1];




for(int i=1;i<=v;i++)
    {
    
    path[i]=0;
    visited[i]=0;
    distance[i]=32767;     // arbitrarily assumed no.
    
    }
        
int c=1,current=1,mincost=0;
    
visited[current]=1;


distance[current]=0;




while(c!=v)
    {
    
    for(int i=1;i<=v;i++)
        {
        
        if(weight[current][i]!=0 && visited[i]!=1)
        
            if(distance[i]>weight[current][i])
                {
                
                distance[i]=weight[current][i];
                path[i]=current;
                
                }
                
        }
        
    int min=32767;     // arbitrarily assumed no.
    
        for(int i=1;i<=v;i++)
            {
            
            if(visited[i]!=1 && distance[i]<min)
                {
                
                min=distance[i];
                current=i;
                
                }
                
            }
            
    visited[current]=1;
    c+=1;
    
    }
    
    
for(int i=2;i<=v;i++)
        System.out.println("vertex  "+i+"  connected to  "+path[i]);
        
for(int i=1;i<=v;i++)
    mincost+=distance[i];
    
System.out.println("minimum cost : "+mincost);
}
}
