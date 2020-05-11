package hw4;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		ArrayList<String> output = new ArrayList<String>();
		
		// initialise array for text file data
		ArrayList<String> details = new ArrayList<String>();
		readFile readfile = new readFile();
		// get user input for file name
		// will have to figure this part out later...
		System.out.println("Enter file name - do not include '.txt'");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		sc.close();
		// get data from input text file
		details = readfile.read(s + ".txt");
		
		// total amount of vertices
		int vertices = Integer.parseInt(details.get(0));
		
		// create adjacency list that represents the outdegrees
		adjacencyList adjListOutDegree = new adjacencyList(vertices);
		// create adjacency list that represents the indegrees
		adjacencyList adjListInDegree = new adjacencyList(vertices);
		
		for(int i=1;i<details.size();i++) {
			// translate data from file
			String[] edgeNum = details.get(i).split(" ");
			// add data as an edge to adjacency list for both adjacency lists
			// doing one the other way around creates the in-degree adjacency list
			adjListOutDegree.addEdge(Integer.parseInt(edgeNum[0]), Integer.parseInt(edgeNum[1]));
			adjListInDegree.addEdge(Integer.parseInt(edgeNum[1]), Integer.parseInt(edgeNum[0]));
		}
		// find matching in and out degrees - question 1
		inOutDegrees inOut = new inOutDegrees(adjListInDegree.getAdjacencyList(), adjListOutDegree.getAdjacencyList(), vertices);
		ArrayList<Integer> matching = inOut.getArray();
		String inOutMatch = "";
		for(int q=0;q<matching.size();q++) {
			inOutMatch += (matching.get(q));
			if(q!=matching.size()-1) {
				inOutMatch += " ";
			}
		}
		output.add(inOutMatch); 
		// find average amount of in and out degrees - question 2
		averageInOut inOutReport = new averageInOut();
		double avgInOutNum = inOutReport.getInOutVal(adjListOutDegree.getAdjacencyList(), Integer.parseInt(details.get(0)));
		output.add(avgInOutNum + " " + avgInOutNum);
		
		// one object counts the number of cycles and the other outputs one of the cycles
		checkCycles cycles = new checkCycles(vertices, adjListOutDegree.getAdjacencyList());
		checkCycles cycles2 = new checkCycles(vertices, adjListOutDegree.getAdjacencyList());
		// if a cycle does exist 
		if(cycles2.cycleCheck2()) {
			output.add("Cycle(s):");
			// find such cycles
			cycles.cycleCheck();
			// get the cycle path
			boolean cyclePath[] = cycles2.getRepeatedArray();
			ArrayList<Integer> cycleVertices = new ArrayList<Integer>();
			// from where the cycle starts to where it ends is the cycle path
			for(int a=cycles.getCycleStart();a<cyclePath.length;a++) {
				if (cyclePath[a] == true) {
					cycleVertices.add(a);
				}
			}
			// compile into str
			String cyclePathStr = "";
			for(int c=0;c<cycleVertices.size();c++) {
				cyclePathStr += cycleVertices.get(c);
				if(c != cycleVertices.size()-1) {
					cyclePathStr += " ";
				}
			}
			output.add(cyclePathStr);
			// check path count
			if(cycles.getCyclesCount()>3) {
				output.add("No");
			}
			else {
				output.add("Yes");
			}
		}
		// if no cycles - is topological
		else {
			output.add("Order:");
			TopologicalOrder TO = new TopologicalOrder(vertices, adjListOutDegree.getAdjacencyList(), adjListInDegree.getAdjacencyList());
			output.add(TO.getTO());
			output.add("Yes");
		}
		
		// write results into text file
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("output.txt", "UTF-8");
			for(int j=0;j<output.size();j++) {
				writer.write(output.get(j));
				if(j != output.size()) {
					writer.write("\n");
				}
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		finally {
			try {
				writer.close();
				System.out.println("Results generated in output.txt file");
			}
			catch (Exception ex){
			}
		}
	}
}
