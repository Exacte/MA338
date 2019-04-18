import java.io.*; 
import java.util.*;

class Graph { 
	private int V;
	private LinkedList<Integer> adj[];
	
	Graph(int v) { 
		V = v; 
		adj = new LinkedList[v];
		for (int i=0; i<v; ++i) 
			adj[i] = new LinkedList();
	} 

	void addEdge(int a, int b) {
		adj[a].add(b);
		adj[b].add(a);
	}
	
	void bubbleSort() {
        int n = adj.length; 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (adj[j].size() < adj[j+1].size()){
                    LinkedList<Integer> temp = adj[j];
                    adj[j] = adj[j+1]; 
                    adj[j+1] = temp; 
                }
    } 

	void greedyColouring() {

		bubbleSort();
		int result[] = new int[V]; 

		Arrays.fill(result, -1); 

		result[0] = 0; 

		boolean available[] = new boolean[V]; 
		
		Arrays.fill(available, true); 

		for (int u = 1; u < V; u++) { 
			Iterator<Integer> it = adj[u].iterator() ; 
			while (it.hasNext()) { 
				int i = it.next(); 
				if (result[i] != -1) 
					available[result[i]] = false; 
			} 
			int cr; 
			for (cr = 0; cr < V; cr++){ 
				if (available[cr]) 
					break; 
			} 
			result[u] = cr;

			Arrays.fill(available, true); 
		} 
		printColouring(result);
	}
	
	void printColouring(int[] r){
		System.out.println("Colouring of the graph");
		for (int i = 0; i < V; i++) 
			System.out.println("Vertex " + i + " ---> Color " + r[i]);
		System.out.println();
	}
	
	public static void main(String args[]) {
		Graph g1 = new Graph(4);
		g1.addEdge(0, 1); 
        g1.addEdge(0, 2); 
        g1.addEdge(1, 2); 
        g1.addEdge(1, 3); 
        g1.addEdge(2, 3);
		g1.greedyColouring(); 
 
		Graph g2 = new Graph(5);
		g2.addEdge(0, 1); 
        g2.addEdge(0, 2); 
        g2.addEdge(1, 2); 
        g2.addEdge(1, 4); 
        g2.addEdge(2, 4); 
        g2.addEdge(4, 3); 
		g2.greedyColouring();
		
		Graph g3 = new Graph(6);
		g3.addEdge(0, 1); 
		g3.addEdge(0, 3); 
        g3.addEdge(1, 2);
        g3.addEdge(3, 4);
        g3.addEdge(2, 5);
		g3.greedyColouring();
		
		Graph g4 = new Graph(4);
		g4.addEdge(0, 1);
		g4.addEdge(0, 2);
        g4.addEdge(1, 2);
        g4.addEdge(1, 3);
        g4.addEdge(2, 3);
        g4.addEdge(3, 0);
		g4.greedyColouring();
	}
}