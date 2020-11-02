//Codeforces 580C 
import java.util.Scanner;
import java.util.Vector;


public class CF580C {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int vertices = SC.nextInt();
        int maxConsecutive = SC.nextInt();
        boolean[] hasCat = new boolean[vertices];
        for (int v = 0; v < vertices; ++v) {
            int has = SC.nextInt();
            if (has == 1)
                hasCat[v] = true;
        }
        Vector<Integer>[] edges = (Vector<Integer>[]) new Vector[vertices];
        for (int v = 0; v < vertices; ++v)
            edges[v] = new Vector<Integer>();
        
        // Setting edges
        for (int i = 0; i < vertices-1; ++i) {
            int vertex1 = SC.nextInt();
            int vertex2 = SC.nextInt();
            edges[vertex1-1].add(vertex2-1);
            edges[vertex2-1].add(vertex1-1);
        }
        boolean[] visited = new boolean[vertices]; // No vertex needs to be visited twice
        
        DFS park = new DFS(vertices, edges, hasCat, maxConsecutive);
        int reachableRestaurants = park.getReachableCount(0, 0); // 0, 0: (Root = Kefa's home, 0 cats before this)
        System.out.println(reachableRestaurants);
    }
}

class DFS {
    int vertices; // Number of vertices
    boolean[] visited; // Notes whether or not a vertex has been visited
    Vector<Integer>[] edges; // Notes the connections between vertices
    boolean[] hasCat; // Notes whether or not a vertex has cats
    int maxConsecutive; // Maximum number of consecutive vertices that may have cats on the way

    public DFS(int vertices, Vector<Integer>[] edges, boolean[] hasCat, int maxConsecutive) {
        this.vertices = vertices;
        this.edges = edges;
        this.hasCat = hasCat;
        this.maxConsecutive = maxConsecutive;
        visited = new boolean[vertices];
    }

    // Computes and returns number of restaurants reachable from a given vertex
    int getReachableCount(int from, int catsBefore) {
        visited[from] = true;
        if (hasCat[from])
            catsBefore += 1;
        else
            catsBefore = 0;
        if (catsBefore > maxConsecutive) // Path terminates here, too scary
            return 0;
        if (from != 0 && edges[from].size() == 1) // Leaf
                return 1;

        int reachable = 0;
        for (int i = 0; i < edges[from].size(); ++i) {
            int nextVertex = edges[from].get(i);
            if (visited[nextVertex])
                    continue;
            reachable += getReachableCount(nextVertex, catsBefore);
        }
        return reachable;
    }
}