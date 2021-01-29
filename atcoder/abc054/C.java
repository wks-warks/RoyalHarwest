import java.io.*;
import java.util.*;
 
public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));
 
  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    // tests = FR.nextInt();
    for (int t = 0; t < tests; ++t) {
      int vertexCount = FR.nextInt();
      int edgeCount = FR.nextInt();
      List<Set<Integer>> adjacencyList = new ArrayList<Set<Integer>>(vertexCount);
      for (int i = 0; i < vertexCount; i += 1) {
        adjacencyList.add(new HashSet<Integer>());
      }
      
      for (int i = 0; i < edgeCount; i += 1) {
        int v1 = FR.nextInt();
        int v2 = FR.nextInt();
        adjacencyList.get(v1-1).add(v2-1);
        adjacencyList.get(v2-1).add(v1-1);
      }

      Set<Integer> unvisited = new HashSet<Integer>();
      for (int i = 1; i < vertexCount; ++i) unvisited.add(i);
      int ways = getCount(0, unvisited, adjacencyList);
      solution.append(ways + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int getCount(int root, Set<Integer> unvisited, List<Set<Integer>> adjacencyList) {
    if (unvisited.size() == 0) return 1;
    int count = 0;
    for (var vertex : adjacencyList.get(root)) {
      if (unvisited.contains(vertex)) {
        unvisited.remove(vertex);
        count += getCount(vertex, unvisited, adjacencyList);
        unvisited.add(vertex);
      }
    }
    return count;
  }
 
  static class FastReader {
    BufferedReader br;
    StringTokenizer st;
  
    public FastReader() {
      br = new BufferedReader(new InputStreamReader(System.in));
    }
  
    String next() {
      while (st == null || !st.hasMoreElements()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException  e) {
          e.printStackTrace();
        }
      }
      return st.nextToken();
    }
  
    int nextInt() {
      return Integer.parseInt(next());
    }
  
    long nextLong() {
      return Long.parseLong(next());
    }
  
    double nextDouble() {
      return Double.parseDouble(next());
    }
 
    String nextLine() {
      String str = "";
      try {
        str = br.readLine();
      } catch (IOException e)  {
        e.printStackTrace();
      }
      return str;
    }
  }
}