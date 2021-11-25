// Author : RegalBeast

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.awt.Point;

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
    int[][] relativePositions = new int[m][3];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < 3; j++) {
        relativePositions[i][j] = in.nextInt();
      }
    }

    Graph graph = new Graph(n, relativePositions);
    out.println(graph.isPossible() ? "Yes" : "No");

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
  static boolean possible = true;
  static int minimum = Integer.MAX_VALUE;
  static int maximum = Integer.MIN_VALUE;

  Node[] nodes;
  Graph (int nodeCount, int[][] relativePositions) {
    nodes = new Node[nodeCount];
    for (int i = 0; i < nodeCount; i++) {
      nodes[i] = new Node();
    }

    for (var rp : relativePositions) {
      int left = rp[0]-1;
      int right = rp[1]-1;
      int dist = rp[2];
      nodes[left].connect(nodes[right], dist);
      nodes[right].connect(nodes[left], -dist);
    }
  }

  boolean isPossible() {
    for (var node : nodes) {
      if (node.position == null) {
        node.dfs(0);
      }
    }
    return possible && maximum - minimum <= 1_000_000_000;
  }
}

class Node {
  Integer position;
  List<Pair<Node, Integer>> edges;
  Node () {
    position = null;
    edges = new ArrayList<Pair<Node, Integer>>();
  }

  void connect(Node neighbour, int dist) {
    edges.add(new Pair<Node, Integer>(neighbour, dist));
  }

  void dfs(int position) {
    if (!Graph.possible) {
      return;
    }

    if (this.position == null) {
      this.position = position;
    }
    Graph.minimum = Math.min(Graph.minimum, position);
    Graph.maximum = Math.max(Graph.maximum, position);

    for (var edge : edges) {
      Node nbr = edge.getFirst();
      int pos = position + edge.getSecond();
      
      Integer nbrpos = nbr.position;
      if (nbrpos == null) {
        nbr.dfs(pos);
      } else if (nbrpos != pos) {
        Graph.possible = false;
        return;
      }
    }
  }
}

class Pair<T1, T2> {
  private T1 first;
  private T2 second;
  public Pair(T1 first, T2 second) {
    setFirst(first);
    setSecond(second);
  }

  public T1 getFirst() {
    return first;
  }
  public T2 getSecond() {
    return second;
  }

  public void setFirst(T1 first) {
    this.first = first;
  }
  public void setSecond(T2 second) {
    this.second = second;
  }
}