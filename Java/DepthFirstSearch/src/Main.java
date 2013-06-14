import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Main {
	Node rootNode;
	Tree 
	public Main() {
	}
	public static void main()
	{
		Main m = new Main();
		
	}
	public void bfs() {
		// BFS uses Queue data structure
		Queue queue = new LinkedList();
		queue.add(this.rootNode);
		printNode(this.rootNode);
		rootNode.visited = true;
		while (!queue.isEmpty()) {
			Node node = (Node) queue.remove();
			Node child = null;
			while ((child = getUnvisitedChildNode(node)) != null) {
				child.visited = true;
				printNode(child);
				queue.add(child);
			}
		}
		// Clear visited property of nodes
		clearNodes();
	}

	public void dfs() {
		// DFS uses Stack data structure
		Stack stack = new Stack();
		stack.push(this.rootNode);
		rootNode.visited = true;
		printNode(rootNode);
		while (!stack.isEmpty()) {
			Node node = (Node) s.peek();
			Node child = getUnvisitedChildNode(n);
			if (child != null) {
				child.visited = true;
				printNode(child);
				stack.push(child);
			} else {
				stack.pop();
			}
		}
		// Clear visited property of nodes
		clearNodes();
	}
}

class Node {
	char data;
	Node parent;
	Node 
	public Node(char c) {
		this.data = c;
	}
	
	
}