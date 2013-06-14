
public class EdgeNode 
{
	private int y; //adjecny info
	private int weight;
	private EdgeNode next;
	EdgeNode(int target, int w)
	{
		y = target;
		weight = w;
	}
	public void addEdge(EdgeNode input)
	{
		EdgeNode cursor = this;
		while(cursor.next != null)
		{
			cursor = cursor.next;
		}
		cursor.next = input;
	}
}
