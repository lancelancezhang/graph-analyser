package hw4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Iterator;

public class CTO {

	// initialise fields
	private ArrayList<Pair<LinkedList<Integer>, LinkedList<Integer>>> m_digraph;
	private ArrayList<Integer> m_active;
	private Queue<Integer> m_candidate;
	public ArrayList<Integer> m_topological_order;
	
	public CTO(ArrayList<Pair<LinkedList<Integer>,LinkedList<Integer>>> digraph){
		// set variables as fields as fields
		m_digraph = digraph;
		m_active = new ArrayList<Integer>();
		m_candidate = new LinkedList<Integer>();
		m_topological_order = new ArrayList<Integer>();
	}

	public boolean TopologicalOrder()
	{
		// iterate through the entire linked lists
		Iterator<Pair<LinkedList<Integer>, LinkedList<Integer>>> iter_vertex = m_digraph.iterator();
	    while(iter_vertex.hasNext()) {
	        int counter_in_degree = 0;
	        Iterator<Integer> iter_in_degree = iter_vertex.next().second.iterator();
	        // iterating twice
	        while(iter_in_degree.hasNext()) {
	        	iter_in_degree.next();
	            counter_in_degree++;
	        }
	        m_active.add(counter_in_degree);
	    }
	    // for entire active to m_candidate
	    for(int i = 0; i < m_active.size(); ++i) {
	        if(m_active.get(i) == 0) {
	            m_candidate.add(i);
	        }
	    }
	    
	    while (!m_candidate.isEmpty()) {
	        int vertex = m_candidate.poll();
	        // add to entire topological order
	        m_topological_order.add(vertex);
	        
	        Iterator<Integer> iter_out_degree = m_digraph.get(vertex).first.iterator();
	        while(iter_out_degree.hasNext()) {
	        	int tmp = iter_out_degree.next();
	        	m_active.set(tmp, m_active.get(tmp)-1);
	            if(m_active.get(tmp) == 0) {
	                m_candidate.add(tmp);
	            }
	        }
	    }
	    // when completely traversed every vertex
	    if (m_topological_order.size() == m_digraph.size())
	    	return true;
	    
	    return false;
	}
}
