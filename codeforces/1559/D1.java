// Author : RegalBeast

import java.io.*;
import java.util.*;
import java.awt.Point;
import java.util.concurrent.*;

public class Main implements Runnable {
  static Input in = new Input();
  static PrintWriter out = Output();
  
  public static void main(String[] args) {
    new Thread(null, new Main(), "King", 1<<25).start();
  }

  public void run() {
    int nodes = in.nextInt();
    DSU mocha = new DSU(nodes);
    int medges = in.nextInt();
    int dedges = in.nextInt();

    int edges = medges;
    for (int i = 0; i < edges; i++) {
      int first = in.nextInt();
      int second = in.nextInt();
      mocha.connect(first, second);
    }

    DSU diana = new DSU(nodes);
    edges = dedges;
    for (int i = 0; i < edges; i++) {
      int first = in.nextInt();
      int second = in.nextInt();
      diana.connect(first, second);
    }

    List<Point> newEdges = new ArrayList<Point>();
    for (int i = 1; i <= nodes; i++) {
      for (int j = 1; j <= nodes; j++) {
        if (mocha.getSet(i) == mocha.getSet(j) || diana.getSet(i) == diana.getSet(j)) {
          continue;
        } else {
          mocha.connect(i, j);
          diana.connect(i, j);
          newEdges.add(new Point(i, j));
        }
      }
    }

    out.println(newEdges.size());
    for (var edge : newEdges) {
      out.println(edge.x + " " + edge.y);
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

class DSU {
  Node[] nodes;
  public DSU(int nodeCount) {
    nodes = new Node[nodeCount];
    for (int i = 0; i < nodeCount; i++) {
      nodes[i] = new Node(i+1);
    }
  }

  int getSet(int idx) {
    return nodes[idx-1].getSet();
  }

  void connect(int idx1, int idx2) {
    int par1 = nodes[idx1-1].getSet();
    int par2 = nodes[idx2-1].getSet();
    if (par1 != par2) {
      if (nodes[par1-1].rank > nodes[par2-1].rank) {
        nodes[par2-1].parent = nodes[par1-1];
      } else {
        nodes[par1-1].parent = nodes[par2-1];
        if (nodes[par1-1].rank == nodes[par2-1].rank) {
          nodes[par2-1].rank++;
        }
      }
    }
  }
}

class Node {
  int idx, rank;
  Node parent;
  Node(int idx) {
    this.idx = idx;
    rank = 1;
    parent = this;
  }

  Node getParent() {
    if (this.idx == parent.idx) {
      return this;
    } else {
      this.parent = parent.getParent();
      return this.parent;
    }
  }

  int getSet() {
    return getParent().idx;
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
