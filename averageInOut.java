package hw4;

import java.util.LinkedList;

public class averageInOut {
	public double getInOutVal(LinkedList<Integer>[] adjList, int vertices) {
		double count = 0.0;
		// the adjacency list is presented as outdegrees
		for(int i=0;i<vertices;i++) {
			for(int j=0;j<adjList[i].size();j++) {
				count++;
			}
		}
		// the average in degree is equal to the average out degree 
		return count/vertices;
	}
}
