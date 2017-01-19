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
		print(graph);
		System.out.println("Source and destination nodes ??");
		source = in.nextInt();
		dest = in.nextInt();
		bfs();
		dfs();
		System.out.println("\n... Lowest cost path ...");
		shortestCostPath(graph);
		System.out.println("\n... Shortest path ...");
		shortestPath();
	}
	public static boolean visited(boolean mat[]){
		int n = mat.length;
		for(int t = 1 ;t < n;t++)
				if(!mat[t])return false;
		return true;
	}
	
	public static int getMin(boolean visited[],int dist[]){
		int minI = 0;
		for(int i = 1;i < dist.length;i++){
			if(!visited[i]){
				if(dist[i] < dist[minI])
					minI = i;
			}
		}
		if(minI != 0)visited[minI] = true;
		return minI;
	}
	
	public static void printSP(int parent[],int d){
		if(d == source){
			System.out.print(d+" --> ");
			return;
		}
		printSP(parent,parent[d]);
		System.out.print(d+" --> ");
	}
	public static void shortestPath(){
		LinkedList<LinkedList<Edge>> Graph = new 
					LinkedList<LinkedList<Edge>>();
		for(LinkedList<Edge> lEdge  : graph){
			LinkedList<Edge> list = new LinkedList<Edge>();
			for(Edge edge : lEdge){
				Edge Nedge = new Edge(edge.node,edge.wt);
				list.add(Nedge);
			}
			Graph.add(list);
		}
	
		for(LinkedList<Edge> lEdge : Graph){
			for(Edge edge : lEdge)
				edge.wt = 0;
		}
		shortestCostPath(Graph);
	}	

	public static void shortestCostPath(LinkedList<LinkedList<Edge>> Graph){
		int parent[] = new int[n+1];
		int dist[] = new int[n+1];
		boolean visited[] = new boolean[n+1];
		for(int i = 0 ;i <= n;i++){
			parent[i] = -1;
			dist[i] = Integer.MAX_VALUE;
		}
		dist[source] = 0;
		while(!visited(visited)){
			int u = getMin(visited,dist);
			for(Edge edge : Graph.get(u-1)){
				int v = edge.node;
				if(dist[v] > dist[u] + edge.wt){
					dist[v] = dist[u] + edge.wt;
					parent[v] = u;
				}
			}
		}
		// Using Parent print the shortest path
		printSP(parent,parent[dest]);
		System.out.println(dest);
	}
	
	public static void dfs(){
		boolean visited[] = new boolean[n+1];
		System.out.println("\n... Depth First Search ...");
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
	public static void print(LinkedList<LinkedList<Edge>> graph){
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
