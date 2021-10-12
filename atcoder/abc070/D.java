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
                                ), 1<<25).start();
  }

  public void run() {
    int nodeCount = in.nextInt();
    Tree tree = new Tree(nodeCount);

    for (int e = 1; e < nodeCount; e++) {
      int first = in.nextInt()-1;
      int second = in.nextInt()-1;
      int weight = in.nextInt();
      tree.connect(first, second, weight);
    }

    int queries = in.nextInt();
    int root = in.nextInt()-1;
    tree.setDistancesFromRoot(root);

    for (int q = 0; q < queries; q++) {
      int first = in.nextInt()-1;
      int second = in.nextInt()-1;

      long distance = tree.getDistanceFromRoot(first) + tree.getDistanceFromRoot(second);
      out.println(distance);
    }
    
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

class Tree {
  Node[] nodes;
  Tree (int nodeCount) {
    nodes = new Node[nodeCount];
    for (int n = 0; n < nodeCount; n++) {
      nodes[n] = new Node(n);
    }
  }

  void setDistancesFromRoot(int root) {
    nodes[root].setDistancesFromRoot(null, 0);
  }

  void connect(int first, int second, int weight) {
    nodes[first].addEdge(nodes[second], weight);
    nodes[second].addEdge(nodes[first], weight);
  }

  long getDistanceFromRoot(int idx) {
    return nodes[idx].distanceFromRoot;
  }
}

class Node {
  int idx;
  long distanceFromRoot;
  List<Pair<Node, Integer>> edges;

  Node(int idx) {
    this.idx = idx;
    this.distanceFromRoot = Long.MAX_VALUE;
    edges = new ArrayList<Pair<Node, Integer>>();
  }

  void addEdge(Node to, int weight) {
    edges.add(new Pair<Node, Integer>(to, weight));
  }

  void setDistancesFromRoot(Node parent, long dist) {
    this.distanceFromRoot = dist;
    for (var nbr : edges) {
      if (nbr.getFirst().equals(parent)) {
        continue;
      }
      nbr.getFirst().setDistancesFromRoot(this, dist+nbr.getSecond());
    }
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Node) {
      return ((Node) o).idx == this.idx;
    }
    return false;
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

  @Override
  public int hashCode() {
    final int prime = 7_243;
    int hash = first.hashCode() % prime;
    hash = (int) ((long) hash * second.hashCode() % prime);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Pair) {
      Pair<?, ?> other = (Pair<?, ?>) obj;
      return first.equals(other.getFirst()) && second.equals(other.getSecond());
    }
    return false;
  }
}