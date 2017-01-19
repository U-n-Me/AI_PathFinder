# PathFinder
Path Finder Assignmnet

# First AI Assignment
It's our first AI assignment. Given a map our task is to find a corresponding location 
from a given location. We need to do this using BFS, DFS, Shortest Path, Shortest Cost Path etcetra.

# Input:
* Give input as specified
* Note that map input is taken from a text file and start and destination location will be asked at run time through console

	First line contains 'n' a numerical value, i.e number of nodes in the graph.<br />
	Following n lines are as:<br />
	For the i(th) line put the nodes connected to it and corresponding wt.<br />
	Sample input:  
	3<br />
	2 1 3 5<br />
	1 1 3 2<br />
	1 5 2 2<br />

	Explaination :<br />
	Line 1: There are three nodes (1, 2, 3), namely.  
	Line 2: Node 2 is connected to node 1 and wt. of this edge is 1, similarly wt. of edge 1->3 is 5  
	Line 3: Node 1 is connected to node 2 and wt. of this edge is 1, similarly wt. of edge 2->3 is 2<br />
	and so on..<br />
