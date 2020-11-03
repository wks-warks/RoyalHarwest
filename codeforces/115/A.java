//Codeforces 115A 
import java.util.Scanner;
import java.util.Vector;



public class CF115A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int employees = SC.nextInt();
        int[] managedBy = new int[employees];
        for (int e = 0; e < employees; ++e)
            managedBy[e] = SC.nextInt();
        DFS hierarchy = new DFS(employees, managedBy);
        int groups = hierarchy.getMaxDepth();
        System.out.println(groups);
    }
}

class DFS {
    int vertices;
    Vector<Integer>[] connected;
    boolean[] hasParent; // Notes whether or not an employee is managed by another
    public DFS(int vertices, int[] edges) {
        this.vertices = vertices;
        hasParent = new boolean[vertices];
        this.connected = (Vector<Integer>[]) new Vector[vertices];
        for (int v = 0; v < vertices; ++v)
            this.connected[v] = new Vector<Integer>();
        for (int v = 0; v < vertices; ++v)
            if(edges[v] != -1) {
                connected[v].add(edges[v]-1);
                connected[edges[v]-1].add(v);
                hasParent[v] = true;
            }
    }

    // Computes and returns maximum depth in hierarchy
    public int getMaxDepth() {
        boolean[] visited = new boolean[vertices];
        int maxDepth = 0;
        for (int v = 0; v < vertices; ++v)
            if (!hasParent[v]) // If employee v is not managed we check the depth of branch
                maxDepth = Math.max(maxDepth, depthFrom(v, visited));
        return maxDepth;
    }

    // Computes and returns depth in hierarchy with 'v' as superior-most employee
    public int depthFrom(int v, boolean[] visited) {
        visited[v] = true;
        int depth = 1;
        for (int i = 0; i < connected[v].size(); ++i)
            if (!visited[connected[v].get(i)])
                depth = Math.max(depth, 1 + depthFrom(connected[v].get(i), visited));
        return depth;
    }
}