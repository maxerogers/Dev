
public class Vertex 
{
	Object data;
	int cursor = 0;
	Edge rootEdge = null;
	Edge tailEdge = null;
	public void setData(Object o){data = o;}
	public Object getData(){return data;}
	public Vertex getRoot()
	{
		if(rootEdge == null)
			return null;
		else 
			return rootEdge.node;
	}
	public void addEdge(Vertex v)
	{
		Edge temp = new Edge(v);
		tailEdge.next = temp;
		tailEdge = temp;
	}
	public boolean removeEdge(Vertex v)
	{
		Edge cursor = rootEdge;
		while(cursor != null)
		{
			if(cursor.node == v)
			{
				return true;
			}else{
				cursor = cursor.next;
			}
		}
		return false;
	}
	private class Edge
	{
		Vertex node;
		Edge next;
		Edge(Vertex n){node = n;}
		public Edge getNext(){return next;}
		public void setEdge(Edge e){next = e;}
	}
}
