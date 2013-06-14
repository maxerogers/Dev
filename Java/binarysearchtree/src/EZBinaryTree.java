/**
 * This is my own binary tree
 * @author max
 *
 */
public class EZBinaryTree 
{
	private Node root;
	public EZBinaryTree(int[] array) 
	{
		for(int i=0;i<array.length;i++)
		{
			insert(array[i]);
		}
	}
	
	public void insert(int x)
	{
		if(root == null)
		{
			root = new Node(x);
		}else{
			insertHelper(root, x);
		}
	}
	public void insertHelper(Node node, int x)
	{
		if(x > node.value)
		{
			if(node.right == null)
				node.right = new Node(x);
			else
				insertHelper(node.right, x);
		}else{
			if(node.left == null)
				node.left = new Node(x);
			else
				insertHelper(node.left, x);
		}
	}
	public int findSmallestValue(int i, int j)
	{
		int result = -1;
		
		
		
		return result;
	}
	private class Node
	{
		private int value;
		private Node left;
		private Node right;
		Node(int i)
		{
			value = i;
		}
	}
}
