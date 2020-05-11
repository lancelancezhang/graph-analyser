package hw4;

import java.util.ArrayList;
import java.util.LinkedList;

public class inOutDegrees {
	// initialise fields
	ArrayList<Integer> _list = new ArrayList<Integer>();
	public inOutDegrees(LinkedList<Integer>[] adjListInDegree, LinkedList<Integer>[] adjListOutDegree, int vertices) {
		// initialisation of variables
		ArrayList<Integer> list = new ArrayList<Integer>();
		// find matching in and out degree of vertex
		for(int i=0;i<vertices;i++) {
			if(adjListInDegree[i].size() == adjListOutDegree[i].size()) {
				list.add(i);
			}
		}
		_list = list;
	}
	public ArrayList<Integer> getArray() {
		return _list;
	}
}
