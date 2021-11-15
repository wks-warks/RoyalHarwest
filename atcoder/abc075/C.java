// Author : RegalBeast

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Main implements Runnable {
  static Input in = new Input();
  static PrintWriter out = Output();
  
  public static void main(String[] args) {
    new Thread(null, new Main(), String.join(
                                  "All that is gold does not glitter,",
                                  "Not all those who wander are lost;",
                                  "The old that is strong does not wither,",
                                  "Deep roots are not reached by the frost.",
                                  
                                  "From the ashes a fire shall be woken,",
                                  "A light from the shadows shall spring;",
                                  "Renewed shall be blade that was broken,",
                                  "The crownless again shall be king."
                                ), 1<<28).start();
  }

  public void run() {
    int n = in.nextInt();
    int m = in.nextInt();

    Graph graph = new Graph(n);
    int[][] edges = new int[m][2];
    for (int e = 0; e < m; e++) {
      edges[e][0] = in.nextInt()-1;
      edges[e][1] = in.nextInt()-1;
      graph.addEdge(edges[e]);
    }

    int bridges = 0;
    for (int e = 0; e < m; e++) {
      graph.removeEdge(edges[e]);
      if (!graph.isConnected()) {
        bridges++;
      }
      graph.addEdge(edges[e]);
    }
    out.println(bridges);
    
    in.close();
    out.close();
  }
  
  static PrintWriter Output() {
    return new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  }
  
  static PrintWriter Output(String fileName) {
    PrintWriter pw = null;
    try {
      pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return pw;
  }
}

class Input {
  BufferedReader br;
  StringTokenizer st;
  public Input() {
    br = new BufferedReader(new InputStreamReader(System.in));
  }

  public Input(String fileName) {
    try {
      br = new BufferedReader(new FileReader(fileName));
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public String next() {
    while (st == null || !st.hasMoreElements()) {
      try {
        st = new StringTokenizer(br.readLine());
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return st.nextToken();
  }

  public int nextInt() {
    return Integer.parseInt(next());
  }

  public long nextLong() {
    return Long.parseLong(next());
  }

  public Float nextFloat() {
    return Float.parseFloat(next());
  }

  public Double nextDouble() {
    return Double.parseDouble(next());
  }

  public String nextLine() {
    if (st != null && st.hasMoreElements()) {
      StringBuilder sb = new StringBuilder();
      while (st.hasMoreElements()) {
        sb.append(next());
      }
      return sb.toString();
    }

    String str = null;
    try {
      str = br.readLine();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return str;
  }

  public void close() {
    try {
      br.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}

class Graph {
  int nodes;
  List<Set<Integer>> neighbours;
  Graph (int nodes) {
    this.nodes = nodes;
    neighbours = new ArrayList<Set<Integer>>();
    for (int n = 0; n < nodes; n++) {
      neighbours.add(new HashSet<Integer>());
    }
  }

  void addEdge(int[] edge) {
    neighbours.get(edge[0]).add(edge[1]);
    neighbours.get(edge[1]).add(edge[0]);
  }

  void removeEdge(int[] edge) {
    neighbours.get(edge[0]).remove(edge[1]);
    neighbours.get(edge[1]).remove(edge[0]);
  }

  boolean isConnected() {
    Queue<Integer> indices = new ArrayDeque<Integer>();
    Set<Integer> visited = new HashSet<Integer>();

    indices.add(0);
    visited.add(0);
    while (!indices.isEmpty()) {
      int curr = indices.poll();
      for (var nbr : neighbours.get(curr)) {
        if (!visited.contains(nbr)) {
          indices.add(nbr);
          visited.add(nbr);
        }
      }
    }

    return visited.size() == nodes;
  }
}