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
    int n = in.nextInt();
    Graph graph = new Graph(n);

    int m = in.nextInt();
    for (int i = 0; i < m; i++) {
      graph.addDirectedEdge(in.nextInt()-1, in.nextInt()-1);
    }
    
    int[] order = graph.getOrder();
    for (var num : order) {
      out.print(num + " ");
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

class Graph {
  Node[] nodes;
  Graph (int nodeCount) {
    nodes = new Node[nodeCount];
    for (int i = 0; i < nodeCount; i++) {
      nodes[i] = new Node(i+1);
    }
  }

  void addDirectedEdge(int first, int second) {
    nodes[first].addChild(nodes[second]);
    nodes[second].addParent(nodes[first]);
  }

  int[] getOrder() {
    int[] order = new int[nodes.length];
    int idx = 0;

    PriorityQueue<Node> pq = new PriorityQueue<Node>((a, b) -> a.idx - b.idx);
    for (var node : nodes) {
      if (node.parents.size() == 0) {
        pq.add(node);
      }
    }

    while (!pq.isEmpty()) {
      Node top = pq.poll();
      order[idx++] = top.idx;

      for (var node : top.children) {
        node.parents.remove(top);
        if (node.parents.size() == 0) {
          pq.add(node);
        }
      }
    }

    if (idx != order.length) {
      return new int[] {-1};
    }
    return order;
  }
}

class Node {
  int idx;
  Set<Node> parents, children;
  static final int firstPrime = 7_243;
  static final int secondPrime = 997;

  Node (int idx) {
    this.idx = idx;
    parents = new HashSet<Node>();
    children = new HashSet<Node>();
  }

  void addChild(Node node) {
    children.add(node);
  }
  void addParent(Node node) {
    parents.add(node);
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Node) {
      return ((Node) o).idx == idx;
    }
    return false;
  }

  @Override
  public int hashCode() {
    int first = idx;
    int second = idx % secondPrime;
    
    int hash = first % firstPrime;
    hash = (int) ((long) hash * second % firstPrime);
    return hash;
  }
}