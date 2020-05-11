package hw4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;

public class TopologicalOrder {
	
	private String[] _topologicalOrderArray;

	public TopologicalOrder(int vertices, LinkedList<Integer>[] adjListOutDegree, LinkedList<Integer>[] adjListInDegree){
	    ArrayList<Pair<LinkedList<Integer>,LinkedList<Integer>>> digraph = new ArrayList<Pair<LinkedList<Integer>,LinkedList<Integer>>>();
	    // creation of a digraph
	    digraph = new ArrayList<Pair<LinkedList<Integer>,LinkedList<Integer>>>();
	    // initialise linkedlists and digraph
	    for (int i=0; i < vertices; ++i) {
	    	// first and second linked lists
	    	LinkedList<Integer> first = new LinkedList<Integer>();
	    	LinkedList<Integer> second = new LinkedList<Integer>();
	    	digraph.add(new Pair<LinkedList<Integer>, LinkedList<Integer>>(first, second));
	    }
	 // populate the digraph with in and out degrees of each linkedlist
		for(int k=0;k<vertices;k++) {
			for(int j=0;j<adjListOutDegree[k].size();j++) {
				digraph.get(k).second.addFirst(adjListOutDegree[k].get(j));
			}
			for(int j=0;j<adjListInDegree[k].size();j++) {
				digraph.get(k).first.addFirst(adjListInDegree[k].get(j));
			}
		}
        
		// check if topological
	    CTO to = new CTO(digraph);
	    
	    if(to.TopologicalOrder() == true) {
		    String[] topologicalOrderArray = new String[vertices];
	    	Iterator<Integer> iter = to.m_topological_order.iterator();
	    	int i = 0;
	    	// input topological order into an array
	    	while(iter.hasNext()) {
	    		topologicalOrderArray[i] = String.valueOf(iter.next());
		    	i++;
	    	}
	    	_topologicalOrderArray = topologicalOrderArray;
	    }
	}
	
	public String getTO() {
		String TOStr = "";
    	for(int q=_topologicalOrderArray.length-1;q>-1;q--) {
    		TOStr += _topologicalOrderArray[q];
    		if(q != 0) {
    			TOStr += " ";
    		}
    	}
    	return TOStr;
	}
}
