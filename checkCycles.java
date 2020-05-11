package hw4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class checkCycles {
	// initialise required fields
	private int _vertices;
	private int _cycleStart;
	private int _cyclesCount = 0;
	private boolean[] _recordedCycle;
	// list that stores integers within a list
	private List<List<Integer>> adj;

	public checkCycles(int vertices, LinkedList<Integer>[] adjListOutDegree){
		_vertices = vertices;
		adj = new ArrayList<>(vertices);
		// initialisation, populate a linked list with vertices
		for(int i=0;i<vertices;i++) {
			adj.add(new LinkedList<>());
		}
		// add all edges to the vertices
		for(int k=0;k<vertices;k++) {
			for(int j=0;j<adjListOutDegree[k].size();j++) {
				adj.get(k).add(adjListOutDegree[k].get(j)); 
			}
		}
	}
	
	// this is the recursive function that gets called for ever 'unvisited' edge
	// this method looks counts all the cycles in the program
	public boolean DFSCycleNum(boolean visited[], boolean[] repeated, int i) {
		// if current visited edge has been marked as repeated, must be a cycle
		if (repeated[i]) {
			// add one to the cycle
			if(_cyclesCount == 0) {
				_cycleStart = i;
			}
			//repeated[i] = false;
			_cyclesCount++;
		}
		if (visited[i]) {
			return false;
		}
		// set current position as visited for next iteration
		for(int a=i;a==-1;a--) {
			visited[a] = false;
			repeated[a] = false;
		}
		repeated[i] = true;
		visited[i] = true;
		// create list of integers get edges coming from current vertex
		List<Integer> edges = adj.get(i);
		// run DFS algorithm each of the edges from current vertex
		for(int j=0;j<edges.size();j++) {
			if (DFSCycleNum(visited, repeated, edges.get(j))) {
				return true;
			}
		}
		// current edge not repeated
		repeated[i] = false;
		return false;
	}
	
	// this is same as method above, except that it records a specific cycle - due to complication
	// with variables, two had to be created
	public boolean findCycle(boolean visited[], boolean[] repeated, int i) {
		if (repeated[i]) {
			// record first cycle that occurs
			_recordedCycle = repeated;
			return true;
		}
		if (visited[i]) {
			return false;
		}
		repeated[i] = true;
		visited[i] = true;
		List<Integer> edges = adj.get(i);
		for(int j=0;j<edges.size();j++) {
			if (findCycle(visited, repeated, edges.get(j))) {
				return true;
			}
		}
		repeated[i] = false;
		return false;
	}
	
	public boolean cycleCheck() {
		// initialise two stacks as boolean arrays
		//one for DFS algorithm, visited, one for finding cycles, find repeated explored vertices
		boolean visited[] = new boolean[_vertices];
		boolean repeated[] = new boolean[_vertices];
		// send through the current vertex, and both arrays
		// run for loop for disconnected graphs
		for(int i=0;i<_vertices;i++) {
			DFSCycleNum(visited, repeated, 0);
		}
		if(_cyclesCount > 0) {
			return true;
		}
		return false;
	}
	// initialisation to find a cycle that exists - almost identical to above function
	public boolean cycleCheck2() {
		boolean visited[] = new boolean[_vertices];
		boolean repeated[] = new boolean[_vertices];
		for(int i=0;i<_vertices; i++) {
			if(findCycle(visited, repeated, i)) {
				_recordedCycle = repeated;
				return true;
			}
		}
		return false;
	}
	
	public boolean[] getRepeatedArray() {
		return _recordedCycle;
	}
	public int getCyclesCount() {
		return _cyclesCount;
	}
	public int getCycleStart() {
		return _cycleStart;
	}
}
