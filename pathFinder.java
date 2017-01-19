/*
	Input format:
		First line contains 'n' a numerical value, i.e number of nodes
		in the graph.
		Following n lines are as:
			For the i(th) line put the nodes connected to it and 
			corresponding wt.
	Sample input: 
	3
	2 1 3 5
	1 1 3 2
	1 5 2 2

	Explaination :
	Line 1: There are three nodes (1, 2, 3), namely.
	Line 2: Node 2 is connected to node 1 and wt. of this edge is 1, similarly wt. of edge 1->3 is 5
	Line 3: Node 1 is connected to node 2 and wt. of this edge is 1, similarly wt. of edge 2->3 is 2
	and so on..
	
*/
import java.util.*;
import java.io.*;
public class pathFinder{
	public static int n,source,dest;
	public static LinkedList<LinkedList<Edge>> graph;
	public static void main(String args[]) throws FileNotFoundException {
		graph = new LinkedList<LinkedList<Edge>>();
		Scanner in = new Scanner(new File(args[0]));
		n = in.nextInt();
		Scanner input;
		String inp = "";
		inp = in.nextLine();
		for(int i = 0;i < n;i++){
			LinkedList<Edge> list = new LinkedList<Edge>();
			int vertex,wt;
			inp = in.nextLine();
			input = new Scanner(inp);
			while(input.hasNext()){
			vertex = input.nextInt();
			wt = input.nextInt();
			Edge edge = new Edge(vertex,wt);
			list.add(edge);
			}
		graph.add(list);
		}
		in = new Scanner(System.in);
		print();
		System.out.println("Source and destination nodes ??");
		source = in.nextInt();
		dest = in.nextInt();
		bfs();
		dfs();
		//shortestPath();
	}
	public static void dfs(){
		boolean visited[] = new boolean[n+1];
		System.out.println("... Depth First Search ...");
		dfsUtil(source,visited);
	}
	
	public static void dfsUtil(int s,boolean visited[]){
		if(visited[0])return;
		visited[s] = true;
		System.out.print(s+" -- ");
		for(Edge edge : graph.get(s-1)){
			int v = edge.node;
			if(v == dest){
				System.out.println(v);
				visited[0] = true;
				break;
			}
			if(!visited[v])
				dfsUtil(v,visited);
		}
	}

	public static void bfs(){
		boolean visited[] = new boolean[n+1];
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(source);
		visited[source] = true;
		int u = 0,v = 0;
		while(!queue.isEmpty()){
			u = queue.remove();
			System.out.print(u+" -- ");
			for(Edge edge: graph.get(u - 1)){
				v = edge.node;
				if(!visited[v]){
					queue.add(v);
					visited[v] = true;
				}
				if(v == dest){
				// Visited[0] has been used as flag
					visited[0] = true;
					break;
				}
			}
			if(visited[0]) break;
		}
		if(visited[0])
			System.out.println(v);
		else 
			System.out.println("/*** No path exists ***/");
	}
	public static void print(){
		for(int i = 0;i < n;i++){
			System.out.print(i+1);
			for(Edge iter : graph.get(i))
				System.out.print(" -> ("+iter.node+", "+iter.wt+")");
			System.out.println();
		}
	}
}
class Edge{
	int node,wt;
	public Edge(int n,int w){
		node = n;
		wt = w;
	}
}
