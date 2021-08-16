// Author : RegalBeast

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Main implements Runnable {
  static Input in = new Input();
  static PrintWriter out = Output();
  
  public static void main(String[] args) {
    new Thread(null, new Main(), "King", 1<<27).start();
  }

  public void run() {
    int nodeCount = in.nextInt();
    Node[] nodes = new Node[nodeCount];
    for (int i = 0; i < nodeCount; i++) {
      nodes[i] = new Node(i+1);
    }

    for (int i = 1; i < nodeCount; i++) {
      int first = in.nextInt()-1;
      int second = in.nextInt()-1;
      nodes[first].add(nodes[second]);
      nodes[second].add(nodes[first]);
    }

    for (var node : nodes) {
      node.sortAdjacent();
    }

    nodes[0].travel(null);
    out.println();
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

class Node {
  boolean visited;
  Node prev;
  int idx, ptr;
  List<Node> adjacent;

  public Node(int idx) {
    visited = false;
    prev = null;
    this.idx = idx;
    ptr = 0;
    adjacent = new ArrayList<Node>();
  }

  public void add(Node node) {
    adjacent.add(node);
  }

  public void sortAdjacent() {
    Collections.sort(adjacent, (a, b) -> a.idx - b.idx);
  }

  public void travel(Node prev) {
    Main.out.print(idx + " ");
    visited = true;
    if (this.prev == null) {
      this.prev = prev;
    }

    while (ptr < adjacent.size() && adjacent.get(ptr).visited) {
      ptr++;
    }

    if (ptr == adjacent.size()) {
      if (this.idx == 1) {
        return;
      } else {
        this.prev.travel(null);
      }
    } else {
      adjacent.get(ptr).travel(this);
    }
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
