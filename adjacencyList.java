package hw4;

import java.util.LinkedList;

public class adjacencyList {
	// initialise field
	LinkedList<Integer> list[];
	// initialise the adjacency list
	@SuppressWarnings("unchecked")
	public adjacencyList(int vertices) {
		// create new linked list of size of no. vertices
		list = new LinkedList[vertices];
		for(int i=0;i<vertices;i++) {
			// fill the linked list with linked lists of its own
			list[i] = new LinkedList<>();
		}
	}
	// add required edges
	public void addEdge(int vertex1, int vertex2) {
		list[vertex1].add(vertex2);
	}
	
	public LinkedList<Integer>[] getAdjacencyList() {
		return list;
	}
}
