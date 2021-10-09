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
    int posCount = in.nextInt();
    int diff = in.nextInt();

    int[] positions = new int[posCount];
    for (int i = 0; i < posCount; i++) {
      positions[i] = in.nextInt()-1;
    }

    Tree tree = new Tree(nodeCount);
    for (int i = 1; i < nodeCount; i++) {
      int first = in.nextInt()-1;
      int second = in.nextInt()-1;
      tree.addEdge(first, second);
    }

    tree.setDepths();

    for (int i = 1; i < positions.length; i++) {
      tree.meet(positions[i-1], positions[i]);
    }
    int ans = tree.getAns(diff);
    out.println(ans);

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
  static final int MOD = 998_244_353;
  Node[] nodes;
  Map<Pair<Integer, Integer>, Integer> visitFreq;
  Tree(int nodeCount) {
    nodes = new Node[nodeCount];
    for (int i = 0; i < nodeCount; i++) {
      nodes[i] = new Node(i);
    }
    visitFreq = new HashMap<Pair<Integer, Integer>, Integer>();
  }

  void addEdge(int first, int second) {
    visitFreq.put(new Pair<Integer, Integer>(first, second), 0);
    visitFreq.put(new Pair<Integer, Integer>(second, first), 0);
    nodes[first].connect(nodes[second]);
    nodes[second].connect(nodes[first]);
  }

  void setDepths() {
    nodes[0].setDepths(0, null);
  }

  int getAns(int diff) {
    // for (var en : visitFreq.entrySet()) {
      // Main.out.println(en.getKey().getFirst() + " " + en.getKey().getSecond() + " " + en.getValue());
    // }
    Map<Integer, Integer> diffMap = new HashMap<Integer, Integer>();
    diffMap.put(0, 1);

    int zeroCount = 0;
    for (var entry : visitFreq.entrySet()) {
      Map<Integer, Integer> next = new HashMap<Integer, Integer>();
      int value = entry.getValue();
      if (value == 0) {
        zeroCount++;
        continue;
      }
      if (value < 0) {
        continue;
      }

      for (var prevEntry : diffMap.entrySet()) {
        int prev = prevEntry.getKey();
        int greater = prev + value;
        if (!next.containsKey(greater)) {
          next.put(greater, 0);
        }
        int gval = next.get(greater) + prevEntry.getValue();
        next.put(greater, gval % MOD);

        int lesser = prev - value;
        if (!next.containsKey(lesser)) {
          next.put(lesser, 0);
        }
        int lval = next.get(lesser) + prevEntry.getValue();
        next.put(lesser, lval % MOD);
      }

      diffMap = next;
    }
    zeroCount /= 2;
    if (!diffMap.containsKey(diff)) {
      diffMap.put(diff, 0);
    }

    int ans = diffMap.get(diff);

    ans %= MOD;
    
    return (int) ((long) ans * binpow(2, zeroCount) % MOD);
  }

  void meet(int first, int second) {
    Node fn = nodes[first];
    Node sn = nodes[second];

    while (!fn.equals(sn)) {
      int fd = fn.depth;
      int sd = sn.depth;

      if (fd > sd) {
        Node par = fn.parent;
        int parIdx = par.idx;

        Pair<Integer, Integer> pair = new Pair<Integer, Integer>(parIdx, fn.idx);
        visitFreq.put(pair, visitFreq.get(pair)+1);
        visitFreq.put(new Pair<Integer, Integer>(fn.idx, parIdx), -1);
        fn = par;
      } else if (sd > fd) {
        Node par = sn.parent;
        int parIdx = par.idx;

        Pair<Integer, Integer> pair = new Pair<Integer, Integer>(parIdx, sn.idx);
        visitFreq.put(pair, visitFreq.get(pair)+1);
        visitFreq.put(new Pair<Integer, Integer>(sn.idx, parIdx), -1);
        sn = par;
      } else {
        Node fpar = fn.parent;
        int fpidx = fpar.idx;

        Pair<Integer, Integer> fpair = new Pair<Integer, Integer>(fpidx, fn.idx);
        visitFreq.put(fpair, visitFreq.get(fpair)+1);
        visitFreq.put(new Pair<Integer, Integer>(fn.idx, fpidx), -1);

        fn = fpar;

        Node spar = sn.parent;
        int spidx = spar.idx;

        Pair<Integer, Integer> spair = new Pair<Integer, Integer>(spidx, sn.idx);
        visitFreq.put(spair, visitFreq.get(spair)+1);
        visitFreq.put(new Pair<Integer, Integer>(sn.idx, spidx), -1);
        sn = spar;
      }
    }
  }

  static int binpow(int a, int b) {
    a %= MOD;
    int res = 1;
    while (b > 0) {
      if ((b & 1) > 0) {
        res = (int) ((long) res * a % MOD);
      }
      a = (int) ((long) a * a % MOD);
      b >>= 1;
    }
    return res;
  }
}

class Node {
  int idx, depth;
  Node parent;
  List<Node> neighbours;
  Node(int idx) {
    this.idx = idx;
    parent = null;
    neighbours = new ArrayList<Node>();
  }

  void connect(Node node) {
    neighbours.add(node);
  }

  void setDepths(int depth, Node parent) {
    this.depth = depth;
    this.parent = parent;
    for (var nbr : neighbours) {
      if (nbr.equals(parent)) {
        continue;
      }
      nbr.setDepths(depth+1, this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Node) {
      return ((Node) o).idx == idx;
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