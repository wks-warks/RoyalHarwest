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

    for (int i = 1; i < n; i++) {
      int first = in.nextInt()-1;
      int second = in.nextInt()-1;
      graph.connect(first, second);
    }

    long[] totalDistances = graph.getTotalDistances();
    for (var td : totalDistances) {
      out.println(td);
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

class Graph {
  Node[] nodes;

  Graph(int n) {
    nodes = new Node[n];

    for (int i = 0; i < n; i++) {
      nodes[i] = new Node(i);
    }
  }

  void connect(int first, int second) {
    nodes[first].connectTo(nodes[second]);
    nodes[second].connectTo(nodes[first]);
  }

  long[] getTotalDistances() {
    nodes[0].setSubtreeDistances(null);
    nodes[0].setTotalDistances(null, nodes.length);

    long[] totalDistances = new long[nodes.length];
    for (int i = 0; i < nodes.length; i++) {
      totalDistances[i] = nodes[i].totalDistances;
    }

    return totalDistances;
  }
}

class Node {
  int idx, subtreeSize;
  long subtreeDistances, totalDistances;
  List<Node> adjacentNodes;

  Node(int idx) {
    this.idx = idx;
    adjacentNodes = new ArrayList<Node>();
  }

  void setTotalDistances(Node parent, int totalNodes) {
    if (parent != null) {
      totalDistances = parent.totalDistances - subtreeSize + totalNodes - (subtreeSize + 2);
    } else {
      totalDistances = subtreeDistances;
    }

    for (var node : adjacentNodes) {
      if (node.equals(parent)) {
        continue;
      }

      node.setTotalDistances(this, totalNodes);
    }
  }

  void setSubtreeDistances(Node parent) {
    subtreeSize = 0;
    subtreeDistances = 0L;

    for (var node : adjacentNodes) {
      if (node.equals(parent)) {
        continue;
      }

      node.setSubtreeDistances(this);
      subtreeSize += node.subtreeSize + 1;
      subtreeDistances += node.subtreeDistances + node.subtreeSize + 1;
    }
  }

  void connectTo(Node node) {
    adjacentNodes.add(node);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Node) {
      return ((Node) obj).idx == this.idx;
    }
    return false;
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
